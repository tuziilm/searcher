package com.tuziilm.searcher.mvc;

import com.tuziilm.searcher.common.Country;
import com.tuziilm.searcher.common.Paginator;
import com.tuziilm.searcher.common.Query;
import com.tuziilm.searcher.common.RemarkStatusForm;
import com.tuziilm.searcher.common.SdkType;
import com.tuziilm.searcher.domain.FloatingAdAppPageRule3V;
import com.tuziilm.searcher.domain.FloatingAdAppRule;
import com.tuziilm.searcher.domain.InnerFloatingAdAppPageRule;
import com.tuziilm.searcher.service.FloatingAdAppRuleService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/floating_ad/app_rule")
public class FloatingAdAppRuleController extends CRUDController<FloatingAdAppRule, FloatingAdAppRuleService, FloatingAdAppRuleController.Form, Query.NameQuery>{
	public FloatingAdAppRuleController() {
		super("floating_ad/app_rule");
	}
	@Resource
	public void setFloatingAdAppRuleService(FloatingAdAppRuleService floatingAdAppRuleService){
		this.service=floatingAdAppRuleService;
	}

    @Override
    protected boolean preList(int page, Paginator paginator, Query.NameQuery query, Model model) {
        paginator.setNeedTotal(true);
        return super.preList(page, paginator, query, model);
    }
    @Override
    protected void postCreate(Model model) {
        model.addAttribute("sdkTypeMap", SdkType.map);
    	model.addAttribute("countries", Country.countries);
    	model.addAttribute("countryMap", Country.shortcut2CountryMap);
    }
    @Override
    protected void postModify(int id, FloatingAdAppRule obj, Model model) {
        postCreate(model);
    }

    @Override
	public void innerSave(Form form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		FloatingAdAppRule floatingAdAppRule=form.toObj();
		try{
            service.saveOrUpdate(floatingAdAppRule);
		} catch (Exception e) {
			errors.addError(new ObjectError("ex", e.getMessage()));
		} 
	}
    @RequestMapping("/copy/{id}")
	public String copy(@PathVariable("id") int id,Model model){
    	FloatingAdAppRule obj = service.get(id);
    	obj.setId(null);
		model.addAttribute("form", obj);
		return CREATE_PAGE;
	}
	public static class Form extends RemarkStatusForm<FloatingAdAppRule> {
        @NotBlank(message = "名称不能为空")
        private String name;
        @NotBlank(message = "包名不能为空")
        private String pkgName;
        @NotEmpty(message = "页面规则不能为空")
        private FloatingAdAppPageRule3V[] pageRules;
        private InnerFloatingAdAppPageRule[] innerPageRules;

        @Override
        public FloatingAdAppRule newObj() {
            return new FloatingAdAppRule();
        }

        @Override
        public void populateObj(FloatingAdAppRule floatingAdAppRule) {
            floatingAdAppRule.setName(name);
            floatingAdAppRule.setPkgName(pkgName);
            floatingAdAppRule.setPageRulesObject(pageRules);
        }

        public InnerFloatingAdAppPageRule[] getInnerPageRulesObject() {
            return innerPageRules;
        }
        public FloatingAdAppPageRule3V[] getPageRulesObject() {
            return pageRules;
        }

        public void setPageRulesObject(FloatingAdAppPageRule3V[] pageRules) {
            this.pageRules = pageRules;
        }

        public String getInnerPageRules() {
            return InnerFloatingAdAppPageRule.toJsonWithNoException(innerPageRules);
        }

        public String getPageRules() {
            return FloatingAdAppPageRule3V.toJsonWithNoException(pageRules);
        }

        public void setPageRules(String pageRulesJson) {
            this.pageRules = FloatingAdAppPageRule3V.nullOnExceptionValueOf(pageRulesJson, FloatingAdAppPageRule3V[].class);
            if(this.pageRules==null || this.pageRules.length<1){
                return;
            }
            this.innerPageRules = new InnerFloatingAdAppPageRule[this.pageRules.length];
            for(int i=0;i<pageRules.length;i++){
                innerPageRules[i]=new InnerFloatingAdAppPageRule(pageRules[i]);
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPkgName() {
            return pkgName;
        }

        public void setPkgName(String pkgName) {
            this.pkgName = pkgName;
        }
    }
}
