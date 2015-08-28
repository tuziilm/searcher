package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
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

/**
 * 1002: PC端用户退出
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-17
 * Time: 上午10:23
 */
@Controller("callbackSoftLogoutController")
public class UserLogoutController extends AbstractCallbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/index/softlogout", method = RequestMethod.POST)
    public void softLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BaseForm form = (BaseForm)request.getAttribute("baseForm");
        String result = form.getParseD();//先反替换，再解码
        LogStatistics.log(LogModule.USER_LOGOUT, "index/softlogout", result);
        JsonNode node = mapper.readTree(result);
        if(node.get("token")==null) {
            errorParam(response);
            return;
        }
        String token = node.get("token").asText();
        HttpSession session = SessionListener.getSession(token);
        session.invalidate();
        response.setContentType("text/json;charset=UTF-8");
        JsonObject data = new JsonObject(1).add("result", 0);
        JsonObject json = new JsonObject(3).add("opcode",1002).add("errorcode",0).add("data", data);
        mapper.writeValue(response.getOutputStream(), json);
    }
}