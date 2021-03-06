package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.domain.BaseForm;
import com.tuziilm.searcher.mvc.listener.SessionListener;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 1008: �û��˳�
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-17
 * Time: ����10:23
 */
@Controller("callbackSoftLogoutController")
public class UserLogoutController extends AbstractCallbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/index/softlogout", method = RequestMethod.POST)
    public void softLogin(@Valid BaseForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.reload(form);
        LogStatistics.log(LogModule.USER_LOGOUT, "index/softlogout", false, request, form.toParams());
        JsonNode node = mapper.readTree(form.getParseD());
        JsonNode jo = node.get("opdata");
        if (jo == null) {
            missParam(response);
            return;
        }
        if (jo.get("uid") == null || jo.get("token") == null) {
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
        session.invalidate();
        response.setContentType("text/json;charset=UTF-8");
        JsonObject data = new JsonObject(1).add("result", 0);
        JsonObject json = new JsonObject(2).add("success", true).add("data", data);
        mapper.writeValue(response.getOutputStream(), json);
    }
}