package com.tuziilm.searcher.mvc.interceptor;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.tuziilm.searcher.common.Base64Util;
import com.tuziilm.searcher.common.JsonSupport;
import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.common.SecurityUtils;
import com.tuziilm.searcher.common.SystemUserType;
import com.tuziilm.searcher.common.Tuple;
import com.tuziilm.searcher.domain.BaseForm;
import com.tuziilm.searcher.mvc.listener.SessionListener;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Set;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
	protected final Logger log= LoggerFactory.getLogger(getClass());
	private final static Set<String> openApis= Sets.newHashSet("/login", "/index/appconfig");
	private final static Set<String> softApis = Sets.newHashSet();
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
        boolean isLogin= LoginContext.checkLogin(request.getSession());
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (uri.startsWith(contextPath)) {
			uri = uri.substring(contextPath.length());
		}
		if (uri.startsWith("/callback") || uri.startsWith("/get/") || uri.startsWith("/static") || uri.startsWith("/public") || uri.startsWith("/notice/detail") || uri.startsWith("/my_zone")|| openApis.contains(uri)) {//static resource or login page or callback interface, not authorize
			if(uri.startsWith("/index/appconfig")) {
				loadForm(request);
			}
			return true;
		}else if(softApis.contains(uri)) {
			return checkApiUserIsLogin(request, response);
		}else {
			if(!isLogin)
				response.sendRedirect(contextPath+"/login");
			else{
				SystemUserType sut = LoginContext.get().systemUserType;
				if(sut==SystemUserType.ADMIN){
					return isLogin;
				}else{
					if(uri.equals("/")){
						return true;
					}
					for(String res : sut.getResources()){
						if(uri.startsWith(res)){
							return isLogin;
						}
					}
					response.sendRedirect(contextPath+"/login");
					return false;
				}
			}
			return isLogin;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LoginContext.clear();
	}

	/**
	 * 参数错误
	 * */
	protected void errorParam(HttpServletResponse response){
		try {
			response.getWriter().write(JsonSupport.ERROR_PARAM);
		} catch (IOException e1) {
		}
	}
	/**
	 * 用户未登陆
	 * */
	protected void userNotLogin(HttpServletResponse response){
		try {
			response.getWriter().write(JsonSupport.USER_NOT_LOGIN);
		} catch (IOException e1) {
		}
	}
	protected Boolean checkApiUserIsLogin(HttpServletRequest request,HttpServletResponse response){
		Tuple<BaseForm,Boolean> tuple = loadForm(request);
		BaseForm form ;
		if(tuple.second){
			form = tuple.getFirst();
		}else{
			errorParam(response);
			return false;
		}
		try {
			if(form.getValid()){
				ObjectMapper mapper = new ObjectMapper();
				if (Strings.isNullOrEmpty(form.getParseD())) {
					errorParam(response);
					return false;
				}
				JsonNode node = mapper.readTree(form.getParseD());
				if (node.get("uid") == null || node.get("token") == null) {
					errorParam(response);
					return false;
				}
				String userId = node.get("uid").asText();
				String token = node.get("token").asText();
				HttpSession session = SessionListener.getSession(token);
				if(LoginContext.checkLogin(session) && userId.equalsIgnoreCase(LoginContext.getUid().toString())) {
					return true;
				}else {
					userNotLogin(response);
					return false;
				}
			}else{
				errorParam(response);
				return false;
			}
		} catch (IOException e) {
			log.error("parse body error!", e);
			return false;
		}
	}

	public Tuple<BaseForm,Boolean> loadForm(HttpServletRequest request){
		Boolean flag = true;
		BaseForm form = new BaseForm();
		try {
			String body = URLDecoder.decode(IOUtils.toString(request.getInputStream()),"UTF-8");
			String[] param = body.split("&");
			if(param==null || param.length<2) {
				flag = false;
			}
			for(int i=0;i<param.length;i++){
				if(param[i].startsWith("d=")){
					form.setD(param[i].substring(2));
					form.setParseD(Base64Util.decode(Base64Util.decodeByKey(form.getD())));
				}else if(param[i].startsWith("c=")){
					form.setC(param[i].substring(2));
				}
			}
			if(form.getC()==null||form.getD()==null){
				flag = false;
			}else{
				form.setValid(form.getC().equalsIgnoreCase(SecurityUtils.Get32CodeModel(form.getD())));
			}
			request.setAttribute("baseForm",form);
			return Tuple.valueOf(form,flag);
		} catch (IOException e) {
			log.error("parse body error!", e);
			return Tuple.valueOf(form,false);
		}
	}


}
