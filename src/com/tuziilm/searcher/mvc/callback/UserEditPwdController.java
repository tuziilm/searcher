package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.common.LoginContext.User;
import com.tuziilm.searcher.common.SecurityUtils;
import com.tuziilm.searcher.domain.BaseForm;
import com.tuziilm.searcher.domain.SysUser;
import com.tuziilm.searcher.mvc.listener.SessionListener;
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

@Controller("callbackSoftEditPwdController")
public class UserEditPwdController extends AbstractCallbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/index/editpwd", method = RequestMethod.POST)
    public void editpwd(@Valid BaseForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.reload(form);
        if (form.getValid()) {
            LogStatistics.log(LogModule.USER_EDITPWD, "index/editpwd", false, request, form.toParams());
            JsonNode node = mapper.readTree(form.getParseD());
            JsonNode jo = node.get("opdata");
            if (jo == null) {
                missParam(response);
                return;
            }
            if (jo.get("uid") == null || jo.get("token") == null || jo.get("username") == null || jo.get("oldPwd") == null || jo.get("newPwd") == null) {
                missParam(response);
                return;
            }
            Integer uid = Integer.parseInt(jo.get("uid").asText());
            String token = jo.get("token").asText();
            HttpSession session = SessionListener.getSession(token);
            if (!LoginContext.checkLogin(session) || uid != LoginContext.getUid()) {
                userNotLogin(response);
                return;
            }
            String username = jo.get("username").asText();
            String oldPwd = jo.get("oldPwd").asText();
            String newPwd = jo.get("newPwd").asText();
            SysUser sysUser = sysUserService.getByUsername(username);
            if(sysUser == null) {
                userExist(response);
                return;
            }
            if(!sysUser.getPasswd().equals(SecurityUtils.md5Encode(oldPwd, username))) {
                errorPwd(response);
                return;
            }
            sysUser.setPasswd(SecurityUtils.md5Encode(newPwd,username));
            sysUserService.update(sysUser);
            response.setContentType("text/json;charset=UTF-8");
            JsonObject json = new JsonObject(2).add("success", true)
                    .add("data", handlerData(LoginContext.get()));
            mapper.writeValue(response.getOutputStream(), json);
        } else {
            errorParam(response);
            return;
        }
    }
    public JsonObject handlerData(User user){
        return new JsonObject(4).add("uid", user.uid.toString())
                .add("username",user.username)
                .add("result",0)
                .add("token",user.uuid);
    }

}
