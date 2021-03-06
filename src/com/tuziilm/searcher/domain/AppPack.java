package com.tuziilm.searcher.domain;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-8-25
 * Time: ����4:26
 */
public class AppPack extends RemarkId{
    private String name;
    private Integer packType;
    private Set<Integer> appIds;
    private Integer uid;
    private Set<String> countries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPackType() {
        return packType;
    }

    public void setPackType(Integer packType) {
        this.packType = packType;
    }

    public Set<Integer> getAppIdsObject() {
        return appIds;
    }

    public String getAppIds() {
        if(appIds==null || appIds.isEmpty()){
            return null;
        }
        return Joiner.on(",").join(appIds);
    }

    public void setAppIds(String appIds) {
        if(Strings.isNullOrEmpty(appIds)){
            return;
        }
        Iterator<String> it= Splitter.on(",").omitEmptyStrings().split(appIds).iterator();
        this.appIds = new HashSet<>();
        while (it.hasNext()){
            try {
                this.appIds.add(Integer.valueOf(it.next()));
            }catch (Exception e){
                //do nothings
            }
        }
    }
    @JsonIgnore
    public void setAppIdsObject(Set<Integer> appIds) {
        this.appIds = appIds;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCountries() {
        if(countries==null || countries.isEmpty()){
            return "";
        }
        return Joiner.on(",").skipNulls().join(countries);
    }

    public void setCountries(String countries) {
        if(Strings.isNullOrEmpty(countries)){
            return;
        }
        this.countries = Sets.newHashSet(Splitter.on(",").omitEmptyStrings().trimResults().split(countries));
    }

    public Set<String> getCountriesObject(){
        return this.countries;
    }

    public void setCountriesObject(Set<String> countries){
        this.countries = countries;
    }
}
