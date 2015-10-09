package com.tuziilm.searcher.common;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Redis Keys
 * @author <a href="mailto:pangkunyi@gmail.com">Calvin Pang</a>
 */
public final class RedisKeys {
    /** ���ڼ�¼�û��ֻ�����MT��Ϣ������, ���Դ˿��Լ������Ҫ���͵�MT��Ϣ*/
    public final static String TERMINAL_PRODUCT_mt_RECORD_NUM_HkEY=Config.APP_NAME+":terminal:productMT:recordNum";
    /** ���ڼ�¼��Ĭ��װ��Ӧ�ð�װ�б�, ֵ�磺2,3,4*/
    public final static String SILENT_INSTALL_SUCCESS_HKEY=Config.APP_NAME+":slient_install:success:appIds";
    /** ���ڼ�¼��Ĭ��װ���͵�Ӧ��ID*/
    public final static String SILENT_INSTALL_RECOMMAND_APPID_HKEY=Config.APP_NAME+":slient_install:recommand:appId";
    public final static String TASK_REQUEST_LIMIT_HKEY_PREFIX=Config.APP_NAME+":task:req:task:limit:";
    public final static String LAST_7_DAYS_LESS_CLICK_USERS_SKEY_PREFIX="pusher:last7DaysLessClickUser:";
    public final static String SALES_INFO_REGISTER_TIME_HKEY = Config.APP_NAME+":sales_info:register_time";
    public final static String SALES_INFO_FROM_SIZE_LIMIT_SKEY_PREFIX= Config.APP_NAME+":sales_info:limit:topn:from:";

    public static String getLast7DaysLessClickUsersSkey(){
        return  LAST_7_DAYS_LESS_CLICK_USERS_SKEY_PREFIX + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    }

    /**
     * ���ڸ������ݷ����version key
     * @param clz
     * @param module
     * @return
     */
    public static String moduleServiceVersionKey(Class clz, String module){
        return Config.APP_NAME+":"+clz.getName()+":"+module+":version";
    }

    public static String taskRequestLimitForUserKey(String uid, Integer taskId){
        return String.format("%s:task:req:user:limit:%s:%d", Config.APP_NAME, uid, taskId);
    }


    public static String uploadKey(String id){
        return String.format("%s:upload:progress:%s", Config.APP_NAME, id);
    }

    public static String otaProgressKey(){
        return String.format("%s:ota:project_package:progress:%s", Config.APP_NAME, UUID.randomUUID().toString());
    }

    public static String silentInstallForbiddenKey(String identity){
        return String.format("%s:slient_install:forbidden:%s", Config.APP_NAME, identity);
    }

}
