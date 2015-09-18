package com.tuziilm.searcher.mvc;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tuziilm.searcher.common.Country;
import com.tuziilm.searcher.common.OperationLogType;
import com.tuziilm.searcher.common.Paginator;
import com.tuziilm.searcher.common.Query;
import com.tuziilm.searcher.common.Query.NameQuery;
import com.tuziilm.searcher.common.RemarkForm;
import com.tuziilm.searcher.domain.App;
import com.tuziilm.searcher.domain.AppPack;
import com.tuziilm.searcher.domain.OperationLog;
import com.tuziilm.searcher.mvc.annotation.Ids;
import com.tuziilm.searcher.service.AppPackService;
import com.tuziilm.searcher.service.AppService;
import com.tuziilm.searcher.service.OperationLogService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * App套餐管理
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-2
 * Time: 上午9:51
 */
@Controller
@RequestMapping("/app/app_pack")
public class AppPackController extends CRUDController<AppPack,AppPackService,AppPackController.AppPackForm, NameQuery>{
    private final Logger log= LoggerFactory.getLogger(getClass());

    @Resource
    private AppService appService;
    public AppPackController() {
        super("app/app_pack");
    }
    @Resource
    private OperationLogService operationLogService;
    @Resource
    public void setAppPackService(AppPackService appPackService){
        this.service = appPackService;
    }

    @Override
    protected boolean preList(int page, Paginator paginator, Query.NameQuery query, Model model) {
        paginator.setNeedTotal(true);
        model.addAttribute("appMap", appService.getAllAppsMapCache());
        model.addAttribute("countryMap", Country.shortcut2CountryMap);
        return super.preList(page, paginator, query, model);
    }

    @Override
    protected void postCreate(Model model) {
        List<App> appType1List = appService.getAllType1AppsCache();
        List<App> appType2List = appService.getAllType2AppsCache();
        List<App> appType3List = appService.getAllType3AppsCache();
        model.addAttribute("apps1", appType1List);
        model.addAttribute("apps2", appType2List);
        model.addAttribute("apps3", appType3List);
        model.addAttribute("countries", Country.countries);
    }

    @Override
    protected void postModify(int id, AppPack obj, Model model) {
        postCreate(model);
    }

    @Override
    protected void onSaveError(AppPackForm form, BindingResult errors, Model model, HttpServletRequest request, HttpServletResponse response) {
        postCreate(model);
    }

    @Override
    public void innerSave(AppPackController.AppPackForm form, BindingResult errors, Model model, HttpServletRequest request, HttpServletResponse response) {
        AppPack appPack = form.toObj();
        if(appPack.getAppIds()==null || appPack.getAppIds().isEmpty()){
            errors.addError(new ObjectError("appIds", "app不能为空"));
            return;
        }
        service.saveOrUpdate(appPack);
        operationLogService.save(OperationLog.valueOf(OperationLogType.APP_PACK, String.format("%s套餐[ID:%s, 名称:%s]", form.isModified() ? "修改" : "新增", appPack.getId(), appPack.getName())));
    }
    @Override
    public String delete(@Ids("ids") int[] ids, HttpServletRequest request) {
        String page = super.delete(ids, request);
        operationLogService.save(OperationLog.valueOf(OperationLogType.APP_PACK, String.format("删除套餐[ID:%s]", Arrays.toString(ids))));
        return page;
    }

    public static class AppPackForm extends RemarkForm<AppPack> {
        @NotBlank(message = "名称不能为空")
        private String name;
        private Integer type;
        private Set<Integer> appIds;
        @NotEmpty(message = "国家不能为空")
        private Set<String> countriesObject;

        public Set<Integer> getAppIds() {
            return appIds;
        }

        public void setAppIds(Set<Integer> appIds) {
            this.appIds = appIds;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<String> getCountriesObject() {
            return countriesObject;
        }

        public void setCountriesObject(Set<String> countriesObject) {
            this.countriesObject = countriesObject;
        }

        @Override
        public AppPack newObj() {
            return new AppPack();
        }

        @Override
        public void populateObj(AppPack appPack) {
            appPack.setName(name);
            appPack.setPackType(type);
            appPack.setAppIdsObject(appIds);
            appPack.setUid(0);
            appPack.setCountriesObject(countriesObject);
        }
    }

}
