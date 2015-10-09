package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.Config;
import com.tuziilm.searcher.common.IpSeeker;
import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.common.LoginContext.User;
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
import org.codehaus.jackson.JsonParser;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            JsonNode node = mapper.readTree(form.getParseD());
            JsonNode jo = node.get("opdata");
            if (jo == null) {
                missParam(response);
                return;
            }
            if (jo.get("uid") == null || jo.get("token") == null || jo.get("links") == null ) {
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
            //用户自定义列表
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
            //需同步列表
            String linkStr = jo.get("links").asText();
            String[] links = linkStr.split(",");
            Paginator paginator = new Paginator();
            AppUniqueKeyQuery unique = new AppUniqueKeyQuery();
            Integer appid;
            if(links.length>0){
                for(String str : links) {
                    String[] linkInfo = str.split(Config.SEP);
                    if (linkInfo == null || linkInfo.length<=0) {
                        errorParam(response);
                        return;
                    }
                    String name = linkInfo[0];
                    String link = linkInfo[1];
                    unique.setLink(link);
                    unique.setName(name);
                    unique.setType(3);
                    paginator.setQuery(unique);
                    App app = appService.getAppByUniqueKey(paginator);
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
                    appIds.add(appid);
                }
            }
            appPack.setAppIdsObject(appIds);
            appPackService.saveOrUpdate(appPack);
            response.setContentType("text/json;charset=UTF-8");
            JsonObject json = new JsonObject(2).add("success", true)
                    .add("data", handlerData(LoginContext.get(),appIds));
            mapper.writeValue(response.getOutputStream(), json);
        } else {
            errorParam(response);
            return;
        }
    }
    public JsonObject handlerData(User user,Set<Integer> appIds){
        return new JsonObject(3).add("uid", user.uid.toString())
                .add("token",user.uuid).add("links",getApps(appIds));
    }
    public List<JsonObject> getApps(Set<Integer> appIds){
        if(appIds==null||appIds.isEmpty()) {
            return java.util.Collections.emptyList();
        }
        List<JsonObject> apps = new ArrayList<>(appIds.size());
        for(Integer appId:appIds) {
            App app = appService.get(appId);
            if(app==null) {
                continue;
            }
            apps.add(new JsonObject(2)
                    .add("name", app.getName())
                    .add("link", app.getLink()));
        }
        return apps;
    }
}
