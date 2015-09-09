package com.tuziilm.searcher.service;

import com.tuziilm.searcher.domain.AppPack;
import com.tuziilm.searcher.persistence.AppPackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-2
 * Time: 下午3:53
 */
@Service
public class AppPackService extends ObjectBasedGroupCacheSupportService<AppPack> {
    private final static String LIST_ALL_KEY="list_all_key";
    private final static String MAP_ALL_KEY="map_all_key";
    private final static String LIST_TYPE_1_KEY = "list_type_1_key";
    private final static String LIST_TYPE_2_KEY = "list_type_2_key";
    private final static String LIST_TYPE_3_KEY = "list_type_3_key";
    private final static String LIST_TYPE_4_KEY = "list_type_4_key";//自定义有图
    private final static String MAP_TYPE_1_KEY = "map_type_1_key";
    private final static String MAP_TYPE_2_KEY = "map_type_2_key";
    private final static String MAP_TYPE_3_KEY = "map_type_3_key";
    private final static String MAP_TYPE_4_KEY = "map_type_4_key";
    private AppPackMapper appPackMapper;

    @Autowired
    public void setAppMapper(AppPackMapper appPackMapper) {
        this.mapper = appPackMapper;
        this.appPackMapper = appPackMapper;
    }
    @Override
    public String[] cacheGroupKeys() {
        return new String[]{LIST_ALL_KEY, MAP_ALL_KEY,LIST_TYPE_1_KEY,LIST_TYPE_2_KEY,LIST_TYPE_3_KEY,LIST_TYPE_4_KEY,MAP_TYPE_1_KEY,MAP_TYPE_2_KEY,MAP_TYPE_3_KEY,MAP_TYPE_4_KEY};
    }

    @Override
    public Object newObject(String cacheGrouppey) {
        if(cacheGrouppey.startsWith("map")){
            return new HashMap<String, AppPack>();
        }else{
            return new ArrayList<AppPack>();
        }
    }

    @Override
    public void updateCacheList(Map<String, Object> update, AppPack appPack) {
        if(appPack.getPackType()==1){
            ((List<AppPack>)update.get(LIST_TYPE_1_KEY)).add(appPack);
            ((Map<Integer, AppPack>)update.get(MAP_TYPE_1_KEY)).put(appPack.getId(), appPack);
        }else if(appPack.getPackType()==2){
            ((List<AppPack>)update.get(LIST_TYPE_2_KEY)).add(appPack);
            ((Map<Integer, AppPack>)update.get(MAP_TYPE_2_KEY)).put(appPack.getId(), appPack);
        }else if(appPack.getPackType()==3){
            ((List<AppPack>)update.get(LIST_TYPE_3_KEY)).add(appPack);
            ((Map<Integer, AppPack>)update.get(MAP_TYPE_3_KEY)).put(appPack.getId(), appPack);
        }else if(appPack.getPackType()==4){
            ((List<AppPack>)update.get(LIST_TYPE_4_KEY)).add(appPack);
            ((Map<Integer, AppPack>)update.get(MAP_TYPE_4_KEY)).put(appPack.getId(), appPack);
        }
        ((List<AppPack>)update.get(LIST_ALL_KEY)).add(appPack);
        ((Map<Integer, AppPack>)update.get(MAP_ALL_KEY)).put(appPack.getId(), appPack);
    }
    public List<AppPack> getAllAppsCache(){
        return (List<AppPack>)getCache(LIST_ALL_KEY);
    }

    public Map<Integer, AppPack> getAllAppsMapCache(){
        return (Map<Integer, AppPack>)getCache(MAP_ALL_KEY);
    }

    public List<AppPack> getAllType1AppsCache(){
        return (List<AppPack>)getCache(LIST_TYPE_1_KEY);
    }

    public Map<Integer, AppPack> getAllType1AppsMapCache(){
        return (Map<Integer, AppPack>)getCache(MAP_TYPE_1_KEY);
    }

    public List<AppPack> getAllType2AppsCache(){
        return (List<AppPack>)getCache(LIST_TYPE_2_KEY);
    }

    public Map<Integer, AppPack> getAllType2AppsMapCache(){
        return (Map<Integer, AppPack>)getCache(MAP_TYPE_2_KEY);
    }

    public List<AppPack> getAllType3AppsCache(){
        return (List<AppPack>)getCache(LIST_TYPE_3_KEY);
    }

    public Map<Integer, AppPack> getAllType3AppsMapCache(){
        return (Map<Integer, AppPack>)getCache(MAP_TYPE_3_KEY);
    }

    public List<AppPack> getAllType4AppsCache(){
        return (List<AppPack>)getCache(LIST_TYPE_4_KEY);
    }

    public Map<Integer, AppPack> getAllType4AppsMapCache(){
        return (Map<Integer, AppPack>)getCache(MAP_TYPE_4_KEY);
    }
    public AppPack getType4ByUid(Integer uid) {
        return appPackMapper.getType4ByUid(uid);
    }

}
