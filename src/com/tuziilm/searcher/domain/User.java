package com.tuziilm.searcher.domain;

/**
 * 
 * �ն��û���
 *
 */
public class User extends Id {
    /**
     * ��������Ӧ����imsi��ʶ�û�Ψһ��ݣ��ͻ���Ҫ��֤��������������������ˣ�������û��ͨ�����ܵĵ�ƽ�����ҲҪ���������(�������ƽ����Կͻ��˻��������һ��UUID��Ϊimsiֵ����������
     */
    private String identity;
    /**
     * ������KEY����ĸ�����ֵ���ϣ�����32λ���磺845c155316284c3982ef08a1dd86aa1f�����ύ������ʱ�Ĳ�������
     */
    private String from;
    /**
     * ��һ��64λ�ı��루ʮ�����Ƶ��ַ���������������ɵġ��û�����ˢ����ָ���������ʱ�����ܻ����·���
     */
    private String androidId;
    /**
     * �����������ַ
     */
    private String btMac;
    private String isPad;//�����ƽ����ԵĻ���ֵΪy������Ϊn���ͻ�����߰�û��ͨ��Ӳ��ģ����豸����ƽ����ԣ���ͨ��Ӳ��ģ��ľ͵����ֻ�
    private String mac;//wifi�������ַ
    private String imei;//IMEI��
    private String imsi;//�ֻ�sim����Ӧ�Ĵ���
    private String version;//����汾�����������İ汾�ţ�����������Լ������
    private String androidVer;//android;//ϵͳ�汾������2.3.5
    private Integer androidLevel;//Android  API  Level������ 10
    private Integer wifi;//��ѡ����ʾ�ֻ��˴�ʱ�˿��ڵ�������ӿ�ʱʹ�õ��������ͣ�1��ʾWIFI��0��ʾGPRS
    private String apkName;//������������APK�����ƣ���  ����΢��1.2
    private String pkgName;//��ʾ��Ӧ�õİ���������������Ա�ʶ�����Ʒ��Ψһ��
    private String versionName;//��ʾ�������apkӦ�õİ汾���ַ��������Ǹ��û����ģ��û���Ӧ�ù������￴����Ӧ�ð汾����ϵAndroidManifest.xml�ﶨ��� android;//versionName="12.1"
    private String versionCode;//��ʾӦ��ʵ�ʵİ�ţ�����������Ǹ�������ʹ�õģ��ڼ������ʱ�õ�����ϵAndroidManifest.xml�ﶨ��� android;//versionCode="12"
    private String mcc;//ΪSIM����Ӧ���ƶ����Һ��룬��3λ������ɣ�Ψһ��ʶ���ƶ��ͻ������Ĺ��ң��й�Ϊ460
    private String mnc;//ΪSIM����Ӧ������id����2λ�������(�еĹ��ҿ�����3λ?)������ʶ���ƶ��ͻ����������ƶ����磬���� �й��ƶ�Ϊ00���й���ͨΪ01,�й�����Ϊ03
    private String simCountry;//SIM�����ڹ��ҵ���д������ cn
    private String operatorName;//SIM��ʹ�õ�������Ӫ�����ƣ����� �й��ƶ�
    private Integer sdcardCountSpare;//��ʾSDCARD�ռ��ܴ�С,��λΪMB
    private Integer sdcardAvailableSpare;//��ʾSDCARD�ռ�ʣ���С����λΪMB
    private Integer systemCountSpare;//��ʾϵͳ�ռ��ܴ�С,��λΪMB
    private Integer systemAvailableSpare;//��ʾϵͳ�ռ�ʣ���С,��λΪMB
    private String resolution;//��ʾ�ֻ��ֱ���
    private String brand;//��ʾ�ֻ�Ʒ��
    private String model;//��ʾ�ֻ��ͺ�
    private String sysApps;//ϵͳ�ռ�(/system/app/)���Ӧ����Ϣ�����ݸ�ʽ����ο� ��ע1
    private String userApps;//�û��ռ�(/data/app/)���Ӧ����Ϣ�������ݸ�ʽ����ο� ��ע1
    private String smsc;//��ʾ�������ĺ���
    private Integer inSys;//��ʾ�������apk�ǲ�����ϵͳ�ռ����/system/app/�����ǵĻ���1��ʾ�����ǵĻ���0��ʾ
    private String loc;//��ʾgps����
    public String getNetwork() {
        return wifi == null ? "δ֪" : (wifi == 1 ? "wifi" : "gprs");
    }

    public boolean isPad(){
        return "y".equals(isPad);
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getBtMac() {
        return btMac;
    }

    public void setBtMac(String btMac) {
        this.btMac = btMac;
    }

    public String getIsPad() {
        return isPad;
    }

    public void setIsPad(String isPad) {
        this.isPad = isPad;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAndroidVer() {
        return androidVer;
    }

    public void setAndroidVer(String androidVer) {
        this.androidVer = androidVer;
    }

    public Integer getAndroidLevel() {
        return androidLevel;
    }

    public void setAndroidLevel(Integer androidLevel) {
        this.androidLevel = androidLevel;
    }

    public Integer getWifi() {
        return wifi;
    }

    public void setWifi(Integer wifi) {
        this.wifi = wifi;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getSimCountry() {
        return simCountry;
    }

    public void setSimCountry(String simCountry) {
        this.simCountry = simCountry;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getSdcardCountSpare() {
        return sdcardCountSpare==null?-1:sdcardCountSpare;
    }

    public void setSdcardCountSpare(Integer sdcardCountSpare) {
        this.sdcardCountSpare = sdcardCountSpare;
    }

    public Integer getSdcardAvailableSpare() {
        return sdcardAvailableSpare==null?-1:sdcardAvailableSpare;
    }

    public void setSdcardAvailableSpare(Integer sdcardAvailableSpare) {
        this.sdcardAvailableSpare = sdcardAvailableSpare;
    }

    public Integer getSystemCountSpare() {
        return systemCountSpare==null?-1:systemCountSpare;
    }

    public void setSystemCountSpare(Integer systemCountSpare) {
        this.systemCountSpare = systemCountSpare;
    }

    public Integer getSystemAvailableSpare() {
        return systemAvailableSpare==null?-1:systemAvailableSpare;
    }

    public void setSystemAvailableSpare(Integer systemAvailableSpare) {
        this.systemAvailableSpare = systemAvailableSpare;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSysApps() {
        return sysApps;
    }

    public void setSysApps(String sysApps) {
        this.sysApps = sysApps;
    }

    public String getUserApps() {
        return userApps;
    }

    public void setUserApps(String userApps) {
        this.userApps = userApps;
    }

    public String getSmsc() {
        return smsc;
    }

    public void setSmsc(String smsc) {
        this.smsc = smsc;
    }

    public Integer getInSys() {
        return inSys;
    }

    public void setInSys(Integer inSys) {
        this.inSys = inSys;
    }

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
    
}
