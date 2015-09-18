package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.IpSeeker;
import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.common.Paginator;
import com.tuziilm.searcher.common.RequestUtils;
import com.tuziilm.searcher.domain.App;
import com.tuziilm.searcher.domain.AppPack;
import com.tuziilm.searcher.domain.BaseForm;
import com.tuziilm.searcher.mvc.AppController.AppUniqueKeyQuery;
import com.tuziilm.searcher.mvc.listener.SessionListener;
import com.tuziilm.searcher.service.AppPackService;
import com.tuziilm.searcher.service.AppService;
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
import java.util.HashSet;
import java.util.Set;

@Controller("callbackUserDefinedController")
public class AddUserDefinedController extends AbstractCallbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private AppPackService appPackService;
    @Resource
    private AppService appService;

    @RequestMapping(value = "/index/userDefined", method = RequestMethod.POST)
    public void userDefined(@Valid BaseForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.reload(form);
        IpSeeker.IpData ipData = IpSeeker.ipData(RequestUtils.getRemoteIp(request));
        String shortcut = ipData==null?"cn":ipData.shortcut;
        if (form.getValid()) {
            LogStatistics.log(LogModule.USER_DEFINED, "index/userDefined", false, request, form.toParams());
            JsonNode node = mapper.readTree(form.getParseD());
            JsonNode jo = node.get("opdata");
            if (jo == null) {
                missParam(response);
                return;
            }
            if (jo.get("uid") == null || jo.get("token") == null || jo.get("name") == null || jo.get("link") == null) {
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
            String name = jo.get("name").asText();
            String link = jo.get("link").asText();
            Paginator paginator = new Paginator();
            AppUniqueKeyQuery unique = new AppUniqueKeyQuery();
            unique.setLink(link);
            unique.setName(name);
            unique.setType(3);
            paginator.setQuery(unique);
            App app = appService.getAppByUniqueKey(paginator);
            Integer appid;
            if(app == null){
                app = new App();
                app.setName(name);
                app.setLink(link);
                app.setStatus(0);
                app.setType(3);
                app.setUid(uid);
                appService.save(app);
                appid = app.getId();
            }else {
                appid = app.getId();
            }
            AppPack appPack = appPackService.getType4ByUid(uid);
            Set<Integer> appIds = new HashSet<>();
            if(appPack == null) {
                appPack = new AppPack();
                appPack.setName("uid:"+uid);
                appPack.setUid(uid);
                appPack.setPackType(4);
                appPack.setCountries(shortcut);
                appPack.setAppIdsObject(appIds);
            }
            appIds = appPack.getAppIdsObject();
            appIds.add(appid);
            appPack.setAppIdsObject(appIds);
            appPackService.saveOrUpdate(appPack);
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
