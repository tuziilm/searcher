package com.tuziilm.searcher.common;

import java.util.HashMap;
import java.util.Map;

public enum OperationLogType {
    APP(1,"APP"),APP_PACK(2,"�ײ�"),SYSUSER(3,"ϵͳ�û�");
    private int value;
    private String name;
    private final static Map<Integer, OperationLogType> map = initMap();
    private static Map<Integer, OperationLogType> initMap(){
        Map<Integer, OperationLogType> map = new HashMap<>(OperationLogType.values().length);
        for(OperationLogType olt : OperationLogType.values()){
            map.put(olt.value, olt);
        }
        return map;
    }
    private OperationLogType(int value, String name){
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static Map<Integer, OperationLogType> asMap() {
        return map;
    }
}
