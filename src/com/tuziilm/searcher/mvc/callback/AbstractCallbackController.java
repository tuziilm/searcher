package com.tuziilm.searcher.mvc.callback;

import com.tuziilm.searcher.common.JsonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class AbstractCallbackController implements JsonSupport {
	protected final Logger log=LoggerFactory.getLogger(getClass());
	@ExceptionHandler(value=Throwable.class)
	protected void fail(Throwable throwable, HttpServletResponse response){
		response.reset();
		try {
			log.error("exception handler catch exception!", throwable);
			response.getWriter().write(FAIL_JSON);
		} catch (IOException e1) {
		}
	}

	protected void simpleSuccess(HttpServletResponse response){
		try {
			response.getWriter().write(SIMPLE_SUCCESS_JSON);
		} catch (IOException e1) {
		}
	}
	protected void simpleFailed(HttpServletResponse response){
		try {
			response.getWriter().write(FAIL_JSON);
		} catch (IOException e1) {
		}
	}
	/**
	 * ȷ�ٲ���
	 * */
	protected void missParam(HttpServletResponse response){
		try {
			response.getWriter().write(MISS_PARAM);
		} catch (IOException e1) {
		}
	}
	/**
	 * �û�δ��½
	 * */
	protected void userNotLogin(HttpServletResponse response){
		try {
			response.getWriter().write(JsonSupport.USER_NOT_LOGIN);
		} catch (IOException e1) {
		}
	}
	/**
	 * ��������
	 * */
	protected void errorParam(HttpServletResponse response){
		try {
			response.getWriter().write(ERROR_PARAM);
		} catch (IOException e1) {
		}
	}
	/**
	 * �������
	 * */
	protected void errorPwd(HttpServletResponse response){
		try {
			response.getWriter().write(ERROR_PWD);
		} catch (IOException e1) {
		}
	}
	/**
	 * �û�������
	 * */
	protected void userNotExist(HttpServletResponse response){
		try {
			response.getWriter().write(USER_NOT_EXIST);
		} catch (IOException e1) {
		}
	}

	/**
	 * ��������ע��
	 * */
	protected void emailExist(HttpServletResponse response){
		try {
			response.getWriter().write(EMAIL_ALREADY_REGISTER);
		} catch (IOException e1) {
		}
	}
	/**
	 * ���û�����ע��
	 * */
	protected void userExist(HttpServletResponse response){
		try {
			response.getWriter().write(USER_ALREADY_REGISTER);
		} catch (IOException e1) {
		}
	}
}

