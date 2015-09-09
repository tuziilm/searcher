package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.common.LoginContext.User;
import com.tuziilm.searcher.common.MailSend;
import com.tuziilm.searcher.common.SecurityUtils;
import com.tuziilm.searcher.domain.BaseForm;
import com.tuziilm.searcher.domain.SysUser;
import com.tuziilm.searcher.service.SysUserService;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller("callbackSoftFindpasswdController")
public class UserFindpasswdController extends AbstractCallbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private SysUserService sysUserService;
    private final static MailSend mailSend = new MailSend();
    @RequestMapping(value = "/index/findpasswd", method = RequestMethod.POST)
    public void softLogin(@Valid BaseForm form,HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.reload(form);
        if (form.getValid()) {
            LogStatistics.log(LogModule.USER_FINDPASSWD, "index/findpasswd", false, request, form.toParams());
            JsonNode node = mapper.readTree(form.getParseD());
            JsonNode jo = node.get("opdata");
            if (jo == null) {
                missParam(response);
                return;
            }
            if (jo.get("email") == null) {
                missParam(response);
                return;
            }
            String email = jo.get("email").asText();
            SysUser sysUser = sysUserService.getByEmail(email);
            if(sysUser == null) {
                userNotExist(response);
                return;
            }
            String[] text = {"abdefghijkmnqrtwy","ABDEFGHIJKLMNQRTWY","23456789"};
            int location = rand(8,10);
            String passwd = "";
            for(int i=0; i<location; ++i){
                int loc = rand(0, 2);
                passwd += text[loc].charAt(rand(0, text[loc].length()-1));
            }
            sysUser.setPasswd(SecurityUtils.md5Encode(passwd, sysUser.getUsername()));
            sysUserService.update(sysUser);
            sendPasswd(email, passwd);
            response.setContentType("text/json;charset=UTF-8");
            JsonObject data = new JsonObject(1).add("result", 0);
            JsonObject json = new JsonObject(2).add("success", true).add("data", data);
            mapper.writeValue(response.getOutputStream(), json);
        } else {
            errorParam(response);
            return;
        }
    }
    /**
     * 发送邮件
     * @author tuziilm
     * @param user
     * 2015年2月10日
     */
    public void sendPasswd(String email,String passwd){
        StringBuffer sb = new StringBuffer("您的新密码是：");
        sb.append(passwd).append("，请尽快修改密码。");
        try {
            mailSend.send(email, sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int rand(int min,int max){
        return (int) Math.floor(Math.max(min, Math.random()*(max+1)));
    }

}
