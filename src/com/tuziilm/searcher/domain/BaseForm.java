package com.tuziilm.searcher.domain;

import com.tuziilm.searcher.common.Base64Util;
import com.tuziilm.searcher.common.SecurityUtils;

import javax.validation.constraints.NotNull;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-9
 * Time: ÏÂÎç5:17
 * */
public class BaseForm {
    @NotNull
    protected String c;
    @NotNull
    protected String d;
    protected String parseD;
    protected boolean valid;
    public Object[] toParams(){
        return new Object[]{c,d,parseD,valid};
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getParseD() {
        return parseD;
    }

    public void setParseD(String parseD) {
        this.parseD = parseD;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public BaseForm reload(BaseForm form){
        if(form.getC()==null || form.getD() == null){
            form.setValid(false);
        }else{
            form.setParseD(Base64Util.decode(Base64Util.decodeByKey(form.getD())));
            form.setValid(form.getC().equalsIgnoreCase(SecurityUtils.Get32CodeModel(form.getD())));
        }
        return form;
    }
}
