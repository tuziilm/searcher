package com.tuziilm.searcher.mvc.callback;

import com.google.common.base.Strings;

import com.tuziilm.searcher.common.IpSeeker;
import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.RequestUtils;
import com.tuziilm.searcher.common.Tuple;
import com.tuziilm.searcher.common.VersionBase;
import com.tuziilm.searcher.domain.BaseUserForm;
import com.tuziilm.searcher.domain.FloatingAdAppPageRule2V;
import com.tuziilm.searcher.domain.FloatingAdAppPageRule3V;
import com.tuziilm.searcher.domain.FloatingAdAppRule;
import com.tuziilm.searcher.service.FloatingAdAppRuleService;
import com.tuziilm.searcher.service.FloatingAdSettingsService;
import com.tuziilm.searcher.service.LimiterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Controller("cbFloatingAdAppRuleController")
public class FloatingAdAppRuleController extends AbstractCallbackController{
    private final static Random random = new Random(System.currentTimeMillis());
    @Resource
    private FloatingAdAppRuleService floatingAdAppRuleService;
    @Resource
    private FloatingAdSettingsService floatingAdSettingsService;
    @Resource
    private LimiterService limiterService;

    @RequestMapping(value = "/get/get_ad_3", method = RequestMethod.POST)
    public void getAd3(@Valid Form form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LogStatistics.log(LogModule.FLOATING_AD_GET_APP_RULE, "get/get_ad_3", false, request, form.toParams());

        IpSeeker.IpData ipData = IpSeeker.ipData(RequestUtils.getRemoteIp(request));
        String shortcut = ipData==null?"cn":ipData.shortcut;
        int versionCode = Strings.isNullOrEmpty(form.getVersion_code())?3:Integer.valueOf(form.getVersion_code());//发现在version_code为空的现象,默认为3版本
        JsonObject json=new JsonObject(4).add("success",true)
                .add("is_open_net",true)
                .add("day", 1)
                .add("app_rules", toAppRules(form, versionCode, shortcut));
        mapper.writeValue(response.getOutputStream(), json);
    }
    @RequestMapping(value = "/get/get_ad", method = RequestMethod.POST)
    public void getAd(@Valid Form form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LogStatistics.log(LogModule.FLOATING_AD_GET_APP_RULE, "get/get_ad", false, request, form.toParams());

        IpSeeker.IpData ipData = IpSeeker.ipData(RequestUtils.getRemoteIp(request));
        String shortcut = ipData==null?"cn":ipData.shortcut;
        JsonObject json=new JsonObject(5).add("success",true)
                .add("is_open_net",true)
                .add("day", 10)
                .add("ad_key", FloatingAdAppPageRule3V.ADMOB_KEY)
                .add("app_rules", toAppRules(form, 2 , shortcut));
        mapper.writeValue(response.getOutputStream(), json);
    }
    private List<JsonObject> toAppRules(Form form,int version, String shortcut){
        boolean testing = form.testing();
        Collection<FloatingAdAppRule> floatingAdAppRules = testing?floatingAdAppRuleService.getTestFloatingAdAppRulesCache():floatingAdAppRuleService.getPromotionFloatingAdAppRulesCache();
        if(floatingAdAppRules==null || floatingAdAppRules.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        if(!testing && limiterService.needHideAd(form,shortcut)){
            return Collections.EMPTY_LIST;
        }
        List<JsonObject> appRules = new ArrayList<>(floatingAdAppRules.size());
        int percent = floatingAdSettingsService.getfloatingAdSettingsCache().getPercent();
        for(FloatingAdAppRule rule : floatingAdAppRules){
            Tuple<?, Boolean> tuple = filterPageRule(rule, percent, version, shortcut, form.getFrom());
            if (tuple.second) {//只返回有页面规则的应用
                appRules.add(new JsonObject(2).add("pkg_name", rule.getPkgName())
                        .add("page_rules", tuple.getFirst()));
            }
        }
        return appRules;
    }
    
    public Tuple<?, Boolean> filterPageRule(FloatingAdAppRule appRule,int percent,int version, String shortcut,String from){
        FloatingAdAppPageRule3V[] rules=appRule.getPageRulesObject();
        if(rules==null){
            log.error("floating ad app rule[{}] has no page rules[{}]", appRule.getId(),appRule.getPageRules());
            return Tuple.valueOf(null, false);
        }
        boolean versionLt3 = version < 3;
        List<FloatingAdAppPageRule2V> pageRules2v = new ArrayList<>(3);
    	List<FloatingAdAppPageRule3V> pageRules3v= new ArrayList<>(3);
    	for(FloatingAdAppPageRule3V rule : rules){
            Set<String> countries = rule.getCountries();
            if(countries !=null && !countries.isEmpty() && !countries.contains(shortcut)){
    			continue;
    		}
            Set<String> ruleFrom = rule.getFrom();
            if(ruleFrom!=null && !ruleFrom.isEmpty() && !ruleFrom.contains(from)){
            	continue;
            }
    		rule.setClose(percent>random.nextInt(100));
            if(VersionBase.bannerSupport(version, rule.getSdk_type())){
                if(versionLt3){
                    pageRules2v.add(rule);
                }else{
                    pageRules3v.add(rule);
                }
            }
    	}
        if(versionLt3){
            return Tuple.valueOf(pageRules2v, pageRules2v.size()>0);
        }
        return Tuple.valueOf(pageRules3v, pageRules3v.size()>0);
    }

    public static class Form extends BaseUserForm {
        @NotNull
        private String uid;
        private String sdk_type;

        public Object[] toParams(){
            return new Object[]{uid,from,android_id,bt_mac,is_pad,mac,imei,imsi,version,android_ver,android_level,wifi,apk_name,pkgname
                    ,version_name,version_code,mcc,mnc,sim_country,operator_name,sdcard_count_spare,sdcard_available_spare,system_count_spare,
                    system_available_spare,resolution,brand,model,in_sys,sdk_type};
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getSdk_type() {
            return sdk_type;
        }

        public void setSdk_type(String sdk_type) {
            this.sdk_type = sdk_type;
        }
    }
}
