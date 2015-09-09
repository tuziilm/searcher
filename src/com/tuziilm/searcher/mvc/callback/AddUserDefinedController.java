package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.common.LoginContext.User;
import com.tuziilm.searcher.common.SecurityUtils;
import com.tuziilm.searcher.domain.AppPack;
import com.tuziilm.searcher.domain.BaseForm;
import com.tuziilm.searcher.domain.SysUser;
import com.tuziilm.searcher.mvc.listener.SessionListener;
import com.tuziilm.searcher.service.AppPackService;
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
import java.util.Set;

@Controller("callbackUserDefinedController")
public class AddUserDefinedController extends AbstractCallbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private AppPackService appPackService;

    @RequestMapping(value = "/index/userDefined", method = RequestMethod.POST)
    public void userDefined(@Valid BaseForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.reload(form);
        if (form.getValid()) {
            LogStatistics.log(LogModule.USER_DEFINED, "index/userDefined", false, request, form.toParams());
            JsonNode node = mapper.readTree(form.getParseD());
            JsonNode jo = node.get("opdata");
            if (jo == null) {
                missParam(response);
                return;
            }
            if (jo.get("uid") == null || jo.get("token") == null || jo.get("appid")==null) {
                missParam(response);
                return;
            }
            Integer uid = Integer.parseInt(jo.get("uid").asText());
            Integer appid = Integer.parseInt(jo.get("appid").asText());
            String token = jo.get("token").asText();
            HttpSession session = SessionListener.getSession(token);
            if (!LoginContext.checkLogin(session) || uid != LoginContext.getUid()) {
                userNotLogin(response);
                return;
            }
            AppPack appPack = appPackService.getType4ByUid(uid);
            Set<Integer> appIds = appPack.getAppIdsObject();
            appIds.add(appid);
            appPack.setAppIdsObject(appIds);
            appPackService.update(appPack);
            response.setContentType("text/json;charset=UTF-8");
            JsonObject data = new JsonObject(1).add("result", 0);
            JsonObject json = new JsonObject(2).add("success", true)
                    .add("data", data);
            mapper.writeValue(response.getOutputStream(), json);
        } else {
            errorParam(response);
            return;
        }
    }
}
