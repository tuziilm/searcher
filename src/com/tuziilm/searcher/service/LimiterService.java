package com.tuziilm.searcher.service;

import com.google.common.base.Strings;
import com.tuziilm.searcher.common.Config;
import com.tuziilm.searcher.common.RedisKeys;
import com.tuziilm.searcher.domain.BaseUserForm;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * 用来做一些限制访问的服务
 */
@Component
public class LimiterService {
    private final static long DEFAULT_LIMIT_MILLSECONDS=1000*60*60*24*15;//默认最初15天不推广
    private final static long MILLSECONDS_PER_DAY=1000*60*60*24;
    @Resource
    private RedisSupport redisSupport;

    public interface AccessLimitedCallback{
        public void callback(Long registerTime);
    }

    public boolean needHideAd(BaseUserForm form, String shortcutFromIp){
        return Config.BLOCKING_FROMS.contains(form.getFrom()) || locateAtChina(form, shortcutFromIp);
    }

    public boolean locateAtChina(BaseUserForm form, String shortcutFromIp){
        if(Config.NO_AD_COUNTRIES.contains(shortcutFromIp)){//中国IP直接返回
            return true;
        }else if("n".equals(form.getIs_pad())){//如果是手机，则需判断imsi
            String imsi = form.getImsi();
            if(imsi!=null){
                if(imsi.startsWith("460") || imsi.startsWith("461") || imsi.startsWith("454") || imsi.startsWith("455") || imsi.startsWith("466")){
                    return true;
                }
            }
            String sim_country = form.getSim_country();
            if(sim_country!=null && Config.NO_AD_COUNTRIES.contains(sim_country)){
                return true;
            }
        }
        return false;
    }
}
