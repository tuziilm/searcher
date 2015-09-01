package com.tuziilm.searcher.common;


/**
 * ��־ͳ��ģ��
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
public enum LogModule {
    USER_LOGIN("�û���¼", "index/softlogin"),
    USER_LOGOUT("�û��˳�", "index/softlogout"),
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
