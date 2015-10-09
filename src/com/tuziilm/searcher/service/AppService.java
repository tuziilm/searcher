package com.tuziilm.searcher.service;

import com.tuziilm.searcher.common.Paginator;
import com.tuziilm.searcher.domain.App;
import com.tuziilm.searcher.mvc.AppController.AppUniqueKeyQuery;
import com.tuziilm.searcher.persistence.AppMapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-2
 * Time: ÉÏÎç10:04
 */
@Service
public class AppService extends ObjectBasedGroupCacheSupportService<App>{
    private final static String LIST_ALL_KEY="list_all_key";
    private final static String MAP_ALL_KEY="map_all_key";
    private final static String LIST_TYPE_1_KEY = "list_type_1_key";
    private final static String LIST_TYPE_2_KEY = "list_type_2_key";
    private final static String LIST_TYPE_3_KEY = "list_type_3_key";
    private final static String MAP_TYPE_1_KEY = "map_type_1_key";
    private final static String MAP_TYPE_2_KEY = "map_type_2_key";
    private final static String MAP_TYPE_3_KEY = "map_type_3_key";
    private AppMapper appMapper;
    @Resource
    private MyBatisBatchItemWriter writer;
    @Autowired
    public void setAppMapper(AppMapper appMapper) {
        this.mapper = appMapper;
        this.appMapper =appMapper;
    }

    @Override
    public String[] cacheGroupKeys() {
        return new String[]{LIST_ALL_KEY, MAP_ALL_KEY,LIST_TYPE_1_KEY,LIST_TYPE_2_KEY,LIST_TYPE_3_KEY,MAP_TYPE_1_KEY,MAP_TYPE_2_KEY,MAP_TYPE_3_KEY};
    }

    @Override
    public Object newObject(String cacheGrouppey) {
        if(cacheGrouppey.startsWith("map")){
            return new HashMap<String, App>();
        }else{
            return new ArrayList<App>();
        }
    }

    @Override
    public void updateCacheList(Map<String, Object> update, App app) {
        if(app.getType()==1){
            ((List<App>)update.get(LIST_TYPE_1_KEY)).add(app);
            ((Map<Integer, App>)update.get(MAP_TYPE_1_KEY)).put(app.getId(), app);
        }else if(app.getType()==2){
            ((List<App>)update.get(LIST_TYPE_2_KEY)).add(app);
            ((Map<Integer, App>)update.get(MAP_TYPE_2_KEY)).put(app.getId(), app);
        }else if(app.getType()==3){
            ((List<App>)update.get(LIST_TYPE_3_KEY)).add(app);
            ((Map<Integer, App>)update.get(MAP_TYPE_3_KEY)).put(app.getId(), app);
        }
        ((List<App>)update.get(LIST_ALL_KEY)).add(app);
        ((Map<Integer, App>)update.get(MAP_ALL_KEY)).put(app.getId(), app);
    }
    public List<App> getAllAppsCache(){
        return (List<App>)getCache(LIST_ALL_KEY);
    }

    public Map<Integer, App> getAllAppsMapCache(){
        return (Map<Integer, App>)getCache(MAP_ALL_KEY);
    }

    public List<App> getAllType1AppsCache(){
        return (List<App>)getCache(LIST_TYPE_1_KEY);
    }

    public Map<Integer, App> getAllType1AppsMapCache(){
        return (Map<Integer, App>)getCache(MAP_TYPE_1_KEY);
    }

    public List<App> getAllType2AppsCache(){
        return (List<App>)getCache(LIST_TYPE_2_KEY);
    }

    public Map<Integer, App> getAllType2AppsMapCache(){
        return (Map<Integer, App>)getCache(MAP_TYPE_2_KEY);
    }

    public List<App> getAllType3AppsCache(){
        return (List<App>)getCache(LIST_TYPE_3_KEY);
    }

    public Map<Integer, App> getAllType3AppsMapCache(){
        return (Map<Integer, App>)getCache(MAP_TYPE_3_KEY);
    }

    public int countAll() {
        return appMapper.countAll();
    }

    public App getAppByUniqueKey(Paginator paginator) {
        return appMapper.getAppByUniqueKey(paginator);
    }

    public void insertAllApps(@Param("apps")List<App> apps) {
       writer.write(apps);
//        appMapper.insertBatch(apps);
    }

}
