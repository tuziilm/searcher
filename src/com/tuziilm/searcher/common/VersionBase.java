package com.tuziilm.searcher.common;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tuziilm.searcher.domain.Rule;

import java.util.Collections;
import java.util.Map;

/**
 * 定义push版本推相应广告平台
 *
 */
public final class VersionBase {
	public final static Map<Integer,Rule> versionBase = initBase();
    public static enum AdType{
        BANNER,SCREEN
    }

	public static Map<Integer,Rule> initBase(){
		Map<Integer,Rule> version =  Maps.newHashMap();
		addVersion(version,1,new Rule(Sets.newHashSet(SdkType.GOOGLE), Collections.EMPTY_SET));
		addVersion(version,2,new Rule(Sets.newHashSet(SdkType.GOOGLE),Collections.EMPTY_SET));
		addVersion(version,3,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.YEAHMOBI), Collections.EMPTY_SET));
		addVersion(version,4,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.YEAHMOBI, SdkType.INMOBI),
										Sets.newHashSet(SdkType.GOOGLE, SdkType.YEAHMOBI, SdkType.INMOBI, SdkType.AIRPUSH)));
		addVersion(version,5,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.YEAHMOBI),
										Sets.newHashSet(SdkType.GOOGLE, SdkType.YEAHMOBI, SdkType.INMOBI, SdkType.AIRPUSH)));
		addVersion(version,6,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.YEAHMOBI, SdkType.STARTAPP),
										Sets.newHashSet(SdkType.GOOGLE, SdkType.YEAHMOBI, SdkType.INMOBI, SdkType.AIRPUSH, SdkType.STARTAPP)));
		addVersion(version,7,new Rule(Collections.EMPTY_SET, Sets.newHashSet(SdkType.CHARTBOOST)));
		addVersion(version,8,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
										Sets.newHashSet(SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP)));
        addVersion(version,9,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
                Sets.newHashSet(SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP)));
		addVersion(version,10,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
				Sets.newHashSet(SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP)));
		addVersion(version,11,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
				Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP)));
		addVersion(version,12,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
				Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP)));
		addVersion(version,13,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
				Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP)));
        addVersion(version,14,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
                Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP)));
        addVersion(version,15,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
                Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP)));
		addVersion(version,16,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
				Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP)));
		addVersion(version,17,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP),
				Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP, SdkType.APPNEXT)));
        addVersion(version,18,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP, SdkType.AIRPUSH),
                Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP, SdkType.APPNEXT)));
		addVersion(version,19,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP, SdkType.AIRPUSH),
				Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP, SdkType.APPNEXT, SdkType.LEADBOLT, SdkType.MKMOB)));
		addVersion(version,20,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP, SdkType.AIRPUSH),
				Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP, SdkType.APPNEXT, SdkType.LEADBOLT, SdkType.MKMOB, SdkType.LIFESTREET)));
		addVersion(version,21,new Rule(Sets.newHashSet(SdkType.GOOGLE, SdkType.INMOBI, SdkType.STARTAPP, SdkType.AIRPUSH),
				Sets.newHashSet(SdkType.GOOGLE, SdkType.AIRPUSH, SdkType.INMOBI, SdkType.STARTAPP, SdkType.APPNEXT, SdkType.LEADBOLT, SdkType.MKMOB, SdkType.LIFESTREET, SdkType.FACEBOOK)));
		return version;
	}
	public static void addVersion(Map<Integer,Rule> version,Integer i,Rule rule){
		version.put(i, rule);
	}
    public static boolean screenSupport(int version, int sdkType){
        return support(AdType.SCREEN, version, sdkType);
    }
    public static boolean bannerSupport(int version, int sdkType){
        return support(AdType.BANNER, version, sdkType);
    }
    public static boolean support(AdType adType, int version, int sdkType){
        Rule rule = versionBase.get(version);
        if(rule==null){
            return false;
        }
        return (adType== AdType.BANNER?rule.getBanner():rule.getScreen()).contains(sdkType);
    }
}
