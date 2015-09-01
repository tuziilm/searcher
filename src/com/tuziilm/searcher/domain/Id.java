package com.tuziilm.searcher.domain;

import com.tuziilm.searcher.common.SqlFunc;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

public abstract class Id {
	/** ID*/
    protected Integer id;
    /** 创建时间*/
    protected Date gmtCreate;
    /** 修改时间*/
    protected Date gmtModified;
	@JsonIgnore
    public SqlFunc getFn(){
        return SqlFunc.get();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JsonIgnore
	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	@JsonIgnore
	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
