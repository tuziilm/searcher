package com.tuziilm.searcher.domain;

public class AdScreenRule extends RemarkStatusId{
    private String name;
    private String pkgName;
    private AdScreenPageRuleForExtend[] pageRules;
    private InnerAdScreenPageRule[] innerPageRules;

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

    public InnerAdScreenPageRule[] getInnerPageRulesObject() {
        return innerPageRules;
    }
    public AdScreenPageRuleForExtend[] getPageRulesObject() {
        return pageRules;
    }
    public void setPageRulesObject(AdScreenPageRuleForExtend[] pageRules) {
        this.pageRules = pageRules;
        if(pageRules==null || pageRules.length<1){
            return;
        }
        innerPageRules = new InnerAdScreenPageRule[pageRules.length];
        for(int i=0;i<pageRules.length;i++){
            innerPageRules[i]=new InnerAdScreenPageRule(pageRules[i]);
        }
    }

    public String getPageRules() {
        return AdScreenPageRuleForExtend.toJsonWithNoException(pageRules);
    }

    /**
     * �ڲ�ʹ�õ�page rules����from��countries����������api���ŵ��ֶ�
     * @return
     */
    public String getInnerPageRules(){
        if(innerPageRules==null || innerPageRules.length<1){
            return null;
        }
        return InnerAdScreenPageRule.toJsonWithNoException(innerPageRules);
    }

    public void setInnerPageRules(String pageRuleJson) {
        setPageRules(pageRuleJson);
    }

    public void setPageRules(String pageRuleJson) {
        setPageRulesObject(AdScreenPageRuleForExtend.nullOnExceptionValueOf(pageRuleJson, AdScreenPageRuleForExtend[].class));
    }
}
