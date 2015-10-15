package com.tuziilm.searcher.mvc;

import com.tuziilm.searcher.common.Paginator;
import com.tuziilm.searcher.common.Query;
import com.tuziilm.searcher.common.RemarkStatusForm;
import com.tuziilm.searcher.common.SdkType;
import com.tuziilm.searcher.domain.AdScreenPageRuleForExtend;
import com.tuziilm.searcher.domain.AdScreenRule;
import com.tuziilm.searcher.domain.InnerAdScreenPageRule;
import com.tuziilm.searcher.service.AdScreenRuleService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ad_screen/rule")
public class AdScreenRuleController extends CRUDController<AdScreenRule, AdScreenRuleService, AdScreenRuleController.Form, Query.NameQuery>{
	public AdScreenRuleController() {
		super("ad_screen/rule");
	}
	@Resource
	public void setAdScreenRuleService(AdScreenRuleService adScreenRuleService){
		this.service=adScreenRuleService;
	}
	@Override
    protected boolean preList(int page, Paginator paginator, Query.NameQuery query, Model model) {
        paginator.setNeedTotal(true);
        return super.preList(page, paginator, query, model);
    }

    @Override
    protected void postCreate(Model model) {
        model.addAttribute("sdkTypeMap", SdkType.map);
        super.postCreate(model);
    }

    @Override
    protected void postModify(int id, AdScreenRule obj, Model model) {
        model.addAttribute("sdkTypeMap", SdkType.map);
        super.postModify(id, obj, model);
    }

    @Override
	public void innerSave(Form form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		AdScreenRule adScreenRule=form.toObj();
		try{
            service.saveOrUpdate(adScreenRule);
		} catch (Exception e) {
			errors.addError(new ObjectError("ex", e.getMessage()));
		} 
	}

	public static class Form extends RemarkStatusForm<AdScreenRule> {
        @NotBlank(message = "名称不能为空")
        private String name;
        @NotBlank(message = "包名不能为空")
        private String pkgName;
        @NotEmpty(message = "页面规则不能为空")
        private AdScreenPageRuleForExtend[] pageRules;
        private InnerAdScreenPageRule[] innerPageRules;

        @Override
        public AdScreenRule newObj() {
            return new AdScreenRule();
        }

        @Override
        public void populateObj(AdScreenRule adScreenRule) {
            adScreenRule.setName(name);
            adScreenRule.setPkgName(pkgName);
            adScreenRule.setPageRulesObject(pageRules);
        }

        public AdScreenPageRuleForExtend[] getPageRulesObject() {
            return pageRules;
        }

        public void setPageRulesObject(AdScreenPageRuleForExtend[] pageRules) {
            this.pageRules = pageRules;
        }

        public String getPageRules() {
            return AdScreenPageRuleForExtend.toJsonWithNoException(pageRules);
        }

        public void setPageRules(String pageRulesJson) {
            this.pageRules = AdScreenPageRuleForExtend.nullOnExceptionValueOf(pageRulesJson, AdScreenPageRuleForExtend[].class);
            if(pageRules==null || pageRules.length<1){
                return;
            }
            innerPageRules = new InnerAdScreenPageRule[pageRules.length];
            for(int i=0;i<pageRules.length;i++){
                innerPageRules[i]=new InnerAdScreenPageRule(pageRules[i]);
            }
        }

        public InnerAdScreenPageRule[] getInnerPageRulesObject() {
            return innerPageRules;
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
