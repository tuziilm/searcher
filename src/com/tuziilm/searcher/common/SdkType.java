package com.tuziilm.searcher.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sdkType对应的广告平台
 */
public final class SdkType {
	/** google平台*/
	public static final int GOOGLE = 1; 
	/** yeahmobi平台*/
	public static final int YEAHMOBI = 2; 
	/** inmobi平台*/
	public static final int INMOBI = 3; 
	/** airpush平台*/
	public static final int AIRPUSH = 4; 
	/** startapp平台*/
	public static final int STARTAPP = 5; 
	/** chartboost平台*/
	public static final int CHARTBOOST = 6;
	/** appnext平台*/
	public static final int APPNEXT = 7;
    /** Leadbolt*/
    public static final int LEADBOLT = 8;
    /** Mkmob*/
    public static final int MKMOB = 9;
    /** lifestreet*/
    public static final int LIFESTREET = 10;
    /** facebook*/
    public static final int FACEBOOK = 11;

    public final static Map<Integer, SdkType> map = initRuleMap();
    private int sdkType;
    private String name;
    private List<Field> fields=new ArrayList<>();

    private static Map<Integer,SdkType> initRuleMap() {
        Map<Integer, SdkType> map = new HashMap<>();
        map.put(GOOGLE, new SdkType(GOOGLE, "google admob")
                        .addField("admob_key", "key", "admob_key")
        );
        map.put(YEAHMOBI, new SdkType(YEAHMOBI, "yeahmobile")
                        .addField("app_id","app_id","yeah_mobile_app_id")
                        .addField("slot_id", "slot_id", "yeah_mobile_slot_id")
        );
        map.put(INMOBI, new SdkType(INMOBI, "inmobi")
                        .addField("id", "id", "inmobi_id")
        );
        map.put(AIRPUSH, new SdkType(AIRPUSH, "airpush")
                        .addField("app_id","appid","airpush_app_id")
                        .addField("api_key", "apikey", "airpush_api_key")
        );
        map.put(STARTAPP, new SdkType(STARTAPP, "startApp")
                        .addField("app_id","start_appid","startapp_app_id")
                        .addField("developerid","start_developerid","startapp_developerid")
        );
        map.put(CHARTBOOST, new SdkType(CHARTBOOST, "chartboost")
                        .addField("app_id","video_app_id","chartboost_video_app_id")
                        .addField("app_signature", "video_app_signature", "chartboost_video_app_signature")
                        .addField(new Field("app_type","video_app_type","chartboost_video_app_type")
                                        .addOption("1","插屏")
                                        .addOption("2","视频")
                        )
        );
        map.put(APPNEXT, new SdkType(APPNEXT, "appnext")
                        .addField("id", "appnext_id", "appnext_id")
        );
        map.put(LEADBOLT, new SdkType(LEADBOLT, "leadbolt")
                        .addField("id","leadbolt_id","leadbolt_id")
        );
        map.put(MKMOB, new SdkType(MKMOB, "mkmob")
                        .addField("id","mkmob_id","mkmob_id")
        );
        map.put(LIFESTREET, new SdkType(LIFESTREET, "lifestreet")
                        .addField("id","lifestreet_id","lifestreet_id")
        );
        map.put(FACEBOOK, new SdkType(FACEBOOK, "facebook")
                .addField("id", "facebook_id", "facebook_id")
        );
        return map;
    }

    public SdkType(int sdkType, String name){
        this.sdkType = sdkType;
        this.name = name;
    }

    public SdkType addField(String displayName, String jsonName, String formId){
        fields.add(new Field(displayName, jsonName, formId));
        return this;
    }


    public SdkType addField(Field field){
        fields.add(field);
        return this;
    }

    public static class Field{
        private String displayName;
        private String jsonName;
        private String formId;
        private String remark;
        private boolean selectable;
        private List<Option> options;
        private Map<String,String> optionsKVMap;

        public Field(String displayName, String jsonName, String formId) {
            this.displayName = displayName;
            this.jsonName = jsonName;
            this.formId = formId;
        }

        public Field addOption(String value, String text){
            this.selectable = true;
            if(options==null){
                options=new ArrayList<>();
                optionsKVMap = new HashMap<>();
            }
            options.add(new Option(value, text));
            optionsKVMap.put(value, text);
            return this;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<Option> getOptions() {
            return options;
        }

        public void setOptions(List<Option> options) {
            this.options = options;
        }

        public Map<String, String> getOptionsKVMap() {
            return optionsKVMap;
        }

        public void setOptionsKVMap(Map<String, String> optionsKVMap) {
            this.optionsKVMap = optionsKVMap;
        }

        public boolean isSelectable() {
            return selectable;
        }

        public void setSelectable(boolean selectable) {
            this.selectable = selectable;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getJsonName() {
            return jsonName;
        }

        public void setJsonName(String jsonName) {
            this.jsonName = jsonName;
        }

        public String getFormId() {
            return formId;
        }

        public void setFormId(String formId) {
            this.formId = formId;
        }
    }

    public static class Option {
        public String value;
        public String text;

        public Option(String value, String text) {
            this.value = value;
            this.text = text;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public int getSdkType() {
        return sdkType;
    }

    public void setSdkType(int sdkType) {
        this.sdkType = sdkType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
