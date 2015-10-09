package com.tuziilm.searcher.mvc.callback;

import com.google.common.base.Strings;
import com.tuziilm.searcher.common.Config;
import com.tuziilm.searcher.common.IpSeeker;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.RequestUtils;
import com.tuziilm.searcher.common.StringUtils;
import com.tuziilm.searcher.domain.BaseForm;
import com.tuziilm.searcher.domain.BaseUserForm;
import com.tuziilm.searcher.domain.User;
import com.tuziilm.searcher.service.UserService;
import org.apache.http.protocol.HTTP;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@Controller("callbackUserController")
public class UserController extends AbstractCallbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private UserService userService;
    /**
     * 客户端带压缩body的请求接口
     * @Title: registerGzip 
     * @Description: TODO
     * @return void    返回类型 
     * @throws
     */
    @RequestMapping(value = "/user/register_gzip", method = RequestMethod.POST)
    public void registerGzip(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String,Object> map = new HashMap<String, Object>();
    	BindingResult errors= new MapBindingResult(map, "");
    	Form form=form(request, errors);
    	register(form, errors, request, response);
    }
    private Form form(HttpServletRequest request, BindingResult errors) throws Exception {
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	GZIPInputStream gis = new GZIPInputStream(request.getInputStream());
		byte[] buffer = new byte[1024*4];
		int n;
		while((n = gis.read(buffer)) >= 0){
			out.write(buffer, 0, n);
		}
		String result = out.toString();
		String[] paramStr = result.split(Config.SEP);
		Map<String , String> formMap = new HashMap<String , String>();
		for(String str : paramStr){
			//key=value  只截取第一个“=”，防止value里面有=号的可能
			for(int i=0;i<str.length();i++){   
			   if (str.substring(i, i + 1).equals("=")){     
				formMap.put(str.substring(0,i).trim(), URLDecoder.decode(str.substring(i+1,str.length()).trim(), HTTP.UTF_8));
			    break;
			   }   
			}
		} 
		Form form = new Form();
		form.setFrom(formMap.get("from"));
		form.setUid(formMap.get("uid"));
		form.setAndroid_id(formMap.get("android_id"));
		form.setBt_mac(formMap.get("bt_mac"));
		form.setIs_pad(formMap.get("is_pad"));
		form.setMac(formMap.get("mac"));
		form.setImei(formMap.get("imei"));
        form.setWifi(toInteger(formMap.get("wifi")));
		form.setImsi(formMap.get("imsi"));
		form.setVersion(formMap.get("version"));
		form.setAndroid_ver(formMap.get("android_ver"));
		form.setAndroid_level(toInteger(formMap.get("android_level")));
		form.setApk_name(formMap.get("apk_name"));
		form.setVersion_name(formMap.get("version_name"));
		form.setPkgname(formMap.get("pkgname"));
		form.setVersion_code(formMap.get("version_code"));
		form.setMcc(formMap.get("mcc"));
		form.setMnc(formMap.get("mnc"));
		form.setSim_country(formMap.get("sim_country"));
		form.setOperator_name(formMap.get("operator_name"));
        form.setSdcard_count_spare(toInteger(formMap.get("sdcard_count_spare")));
        form.setSdcard_available_spare(toInteger(formMap.get("sdcard_available_spare")));
        form.setSystem_count_spare(toInteger(formMap.get("system_count_spare")));
        form.setSystem_available_spare(toInteger(formMap.get("system_available_spare")));
		form.setResolution(formMap.get("resolution"));
		form.setBrand(formMap.get("brand"));
		form.setModel(formMap.get("model"));
        form.setIn_sys(toInteger(formMap.get("in_sys")));
		form.setSys_apps(formMap.get("sys_apps"));
		form.setUser_apps(formMap.get("user_apps"));
		return form;
	}

    private Integer toInteger(String value){
        if(Strings.isNullOrEmpty(value)){
            return null;
        }
        try{
            return Integer.valueOf(value);
        }catch (Exception e){
        }
        return null;
    }

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public void register(@Valid Form form, BindingResult errors, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LogStatistics.log(LogModule.PUSH_REGISTER, "user/register", false, request, form.toParams());
        response.setContentType("text/json;charset=UTF-8");
        final User user = form.toUser();
        if(Strings.isNullOrEmpty(user.getSimCountry()) || user.isPad()){
            IpSeeker.IpData ipData = IpSeeker.ipData(RequestUtils.getRemoteIp(request));
            if(ipData!=null){
                user.setSimCountry(ipData.shortcut);
            }
        }
        if(Strings.isNullOrEmpty(user.getIdentity())){
            if(errors.hasErrors()){//新注册用户时检查参数合法性
                log.error("invalid parameters for user register, params:{}", Arrays.toString(form.toParams()));
                simpleFailed(response);
                return;
            }
            user.setIdentity(user.getImsi());
            User oldUser = userService.getByIdentity(user.getIdentity());
            if(oldUser==null){
                userService.save(user);
            }else {
                user.setIdentity(oldUser.getIdentity());
                Assert.isTrue(userService.update(user) > 0);
            }
        }else{
            Assert.isTrue(userService.update(user) > 0);
        }

        mapper.writeValue(response.getOutputStream(), new Object() {
            public boolean success = true;
            public String uid = user.getIdentity();
            public int day=10;
        });
    }

    public static class Form extends BaseUserForm {
        @NotEmpty
        private String from;
        @NotEmpty
        private String imsi;
        private String sys_apps;
        private String user_apps;

        public Object[] toParams(){
            return new Object[]{uid,from,android_id,bt_mac,is_pad,mac,imei,imsi,version,android_ver,android_level,wifi,apk_name,pkgname
                    ,version_name,version_code,mcc,mnc,sim_country,operator_name,sdcard_count_spare,sdcard_available_spare,system_count_spare,
                    system_available_spare,resolution,brand,model,in_sys};
        }

        public User toUser() {
            User user = new User();
            user.setIdentity(uid);
            user.setFrom(from);
            user.setAndroidId(android_id);
            user.setBtMac(StringUtils.fixedLength(bt_mac, 17));
            user.setIsPad(is_pad);
            user.setMac(StringUtils.fixedLength(mac,17));
            user.setImei(StringUtils.fixedLength(imei,15));
            user.setImsi(StringUtils.fixedLength(imsi,45));
            user.setVersion(version);
            user.setAndroidVer(StringUtils.fixedLength(android_ver,45));
            user.setAndroidLevel(android_level);
            user.setWifi(wifi);
            user.setApkName(apk_name);
            user.setPkgName(pkgname);
            user.setVersionName(version_name);
            user.setVersionCode(version_code);
            user.setMcc(mcc);
            user.setMnc(mnc);
            user.setSimCountry(StringUtils.fixedLength(sim_country,2));
            user.setOperatorName(StringUtils.fixedLength(operator_name,45));
            user.setSdcardCountSpare(sdcard_count_spare);
            user.setSdcardAvailableSpare(sdcard_available_spare);
            user.setSystemCountSpare(system_count_spare);
            user.setSystemAvailableSpare(system_available_spare);
            user.setResolution(resolution);
            user.setBrand(StringUtils.fixedLength(brand,45));
            user.setModel(StringUtils.fixedLength(model,45));
            user.setSysApps(sys_apps);
            user.setUserApps(user_apps);
            user.setInSys(in_sys);
            return user;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getImsi() {
            return imsi;
        }

        public void setImsi(String imsi) {
            this.imsi = imsi;
        }

        public String getSys_apps() {
            return sys_apps;
        }

        public void setSys_apps(String sys_apps) {
            this.sys_apps = sys_apps;
        }

        public String getUser_apps() {
            return user_apps;
        }

        public void setUser_apps(String user_apps) {
            this.user_apps = user_apps;
        }
    }
}
