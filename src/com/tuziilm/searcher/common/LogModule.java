package com.tuziilm.searcher.common;


/**
 * ��־ͳ��ģ��
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
public enum LogModule {
    USER_LOGIN("�û���¼", "index/softlogin"),
    USER_LOGOUT("�û��˳�", "index/softlogout"),
    USER_REGISTER("�û�ע��", "index/register"),
    USER_FINDPASSWD("�û��һ�����", "index/findpasswd"),
    USER_EDITPWD("�û��޸�����", "index/editpwd"),
    USER_DEFINED("����û��Զ���", "index/userDefined"),
    USER_ISLOGIN("�û��Ƿ��½", "index/isLogin"),
    FLOATING_AD_GET_APP_RULE("�������ͳ��", "get/get_ad"),
    PUSH_REGISTER("Push�û���Ϣ", "user/register"),
    APP_CONFIG("�����б�", "index/appconfig");

	private String title;
    private String link;

	private LogModule(String title,String link) {
		this.title = title;
        this.link = link;
	}

    public String getLink() {
        return link;
    }

    public String getTitle() {
		return title;
	}
}
