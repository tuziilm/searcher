package com.tuziilm.searcher.common;


/**
 * 日志统计模块
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
public enum LogModule {
    USER_LOGIN("用户登录", "index/softlogin"),
    USER_LOGOUT("用户退出", "index/softlogout"),
    USER_REGISTER("用户注册", "index/register"),
    USER_FINDPASSWD("用户找回密码", "index/findpasswd"),
    USER_EDITPWD("用户修改密码", "index/editpwd"),
    USER_DEFINED("添加用户自定义", "index/userDefined"),
    USER_ISLOGIN("用户是否登陆", "index/isLogin"),
    FLOATING_AD_GET_APP_RULE("悬浮广告统计", "get/get_ad"),
    PUSH_REGISTER("Push用户信息", "user/register"),
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
