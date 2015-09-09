package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.common.LoginContext.User;
import com.tuziilm.searcher.common.RequestUtils;
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

@Controller("callbackSoftLoginController")
public class UserLoginController extends AbstractCallbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/index/softlogin", method = RequestMethod.POST)
    public void softLogin(@Valid BaseForm form,HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.reload(form);
        if (form.getValid()) {
            LogStatistics.log(LogModule.USER_LOGIN, "index/softlogin", false, request, form.toParams());
            JsonNode node = mapper.readTree(form.getParseD());
            JsonNode jo = node.get("opdata");
            String username = jo.get("user").asText();
            String pwd = jo.get("pwd").asText();
            SysUser sysUser = sysUserService.getByUsername(username);
            if (sysUser == null) {
                log.error("{}[{}] login failed!", username, RequestUtils.getRemoteIp(request));
                userNotExist(response);
                return;
            }
            if (!sysUser.getPasswd().equals(SecurityUtils.md5Encode(pwd, username))) {
                log.error("{}[{}] login failed!", username, RequestUtils.getRemoteIp(request));
                errorPwd(response);
                return;
            }
            LoginContext.doLogin(sysUser, session);
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
        return new JsonObject(5).add("uid", user.uid.toString())
                .add("result",0)
                .add("token",user.uuid);
    }

}
