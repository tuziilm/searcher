package com.tuziilm.searcher.domain;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-8-25
 * Time: ÏÂÎç4:26
 */
public class AppPack extends RemarkId{
    private String name;
    private Integer packType;
    private Set<Integer> appIds;

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
    public void setAppIdsObject(Set<Integer> appIds) {
        this.appIds = appIds;
    }
}
