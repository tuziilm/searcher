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

@Controller("callbackSoftRegisterController")
public class UserRegisterController extends AbstractCallbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/index/register", method = RequestMethod.POST)
    public void softLogin(@Valid BaseForm form,HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.reload(form);
        if (form.getValid()) {
            LogStatistics.log(LogModule.USER_REGISTER, "index/register", false, request, form.toParams());
            JsonNode node = mapper.readTree(form.getParseD());
            JsonNode jo = node.get("opdata");
            if (jo == null) {
                missParam(response);
                return;
            }
            if (jo.get("user") == null || jo.get("pwd") == null || jo.get("email") == null) {
                missParam(response);
                return;
            }
            String username = jo.get("user").asText();
            String pwd = jo.get("pwd").asText();
            String email = jo.get("email").asText();
            SysUser sysUser = sysUserService.getByEmail(email);
            if(sysUser != null) {
                emailExist(response);
                return;
            }
            SysUser sysUser1 = sysUserService.getByUsername(username);
            if(sysUser1 != null) {
                userExist(response);
                return;
            }
            SysUser user = new SysUser();
            user.setEmail(email);
            user.setPasswd(SecurityUtils.md5Encode(pwd, username));
            user.setUsername(username);
            user.setSysUserType((byte) 3);
            user.setStatus((byte) 0);//¼¤»î×´Ì¬
            user.setPrivilege("3");
            sysUserService.save(user);
            LoginContext.doLogin(user, session);
            response.setContentType("text/json;charset=UTF-8");
            JsonObject json = new JsonObject(3).add("success", true)
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
