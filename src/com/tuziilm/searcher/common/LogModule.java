package com.tuziilm.searcher.common;


/**
 * 日志统计模块
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
public enum LogModule {
    USER_LOGIN("用户登录", "index/softlogin"),
    USER_LOGOUT("用户退出", "index/softlogout"),
    APP_CONFIG("分类列表", "index/appconfig");

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
