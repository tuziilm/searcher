package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.Config;
import com.tuziilm.searcher.common.JsonObject;
import com.tuziilm.searcher.common.LogModule;
import com.tuziilm.searcher.common.LogStatistics;
import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.domain.App;
import com.tuziilm.searcher.domain.AppPack;
import com.tuziilm.searcher.domain.BaseForm;
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
import java.util.ArrayList;
import java.util.List;

/**
 *  ·µ»ØAppConfig
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-24
 * Time: ÉÏÎç9:55
 */
@Controller("callbackAppConfigController")
public class AppConfigController extends AbstractCallbackController{
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private AppPackService appPackService;
    @Resource
    private AppService appService;
    @RequestMapping(value = "/index/appconfig", method = RequestMethod.POST)
    public void adRules(@Valid BaseForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.reload(form);
        LogStatistics.log(LogModule.APP_CONFIG, "index/appconfig", false, request, form.toParams());
        if (!form.getValid()) {
            errorParam(response);
            return;
        }
        JsonNode node = mapper.readTree(form.getParseD());
        JsonNode opcodeNode = node.get("opcode");
        if (opcodeNode == null) {
            missParam(response);
            return;
        }
        Integer opcode = Integer.parseInt(opcodeNode.asText());
        List<AppPack> appPackList = null;
        if (opcode == 1001) {
            appPackList = appPackService.getAllType1AppsCache();
        } else if (opcode == 1002) {
            appPackList = appPackService.getAllType2AppsCache();
        } else if (opcode == 1003) {
            appPackList = appPackService.getAllType3AppsCache();
        } else if (opcode == 1004) {
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
            } else {
                AppPack appPack = appPackService.getType4ByUid(uid);
                if(appPack!=null){
                    appPackList.add(appPack);
                }
            }
        }
        JsonObject json = new JsonObject(2).add("success", true).add("appPacks", getAppPack(appPackList));

        response.setContentType("text/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(), json);

    }

    public List<JsonObject> getAppPack(List<AppPack> appPackList){
        List<JsonObject> appPacks = new ArrayList<>(appPackList.size());
        for(AppPack appPack:appPackList){
            List<App> appList = new ArrayList<>();
            for(Integer appId:appPack.getAppIdsObject()) {
                appList.add(appService.get(appId));
            }
            appPacks.add(new JsonObject(3).add("name", appPack.getName())
                    .add("packType", appPack.getPackType()).add("apps", getApp(appList)));
        }
        return appPacks;
    }

    public List<JsonObject> getApp(List<App> appList){
        List<JsonObject> apps = new ArrayList<>(appList.size());
        for(App app:appList){
            apps.add(new JsonObject(3)
                    .add("id", app.getId())
                    .add("name", app.getName())
                    .add("link", app.getLink())
                    .add("imgPath", Config.randomDownloadURL("/public/file/", app.getImgPath()))
                    .add("imgFileName", app.getImgFileName()));
        }
        return apps;
    }
}
