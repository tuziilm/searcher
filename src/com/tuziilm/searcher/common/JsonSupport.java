package com.tuziilm.searcher.common;

import org.codehaus.jackson.map.ObjectMapper;

public interface JsonSupport {
	/** ObjectMapper是线程安全的*/
	final static ObjectMapper mapper=new ObjectMapper();
	final static String FAIL_JSON="{\"success\":false}";
	final static String SIMPLE_SUCCESS_JSON="{\"success\":true}";
	final static String MISS_PARAM = "{\"success\":false,\"result\":1}";
	final static String ERROR_PARAM = "{\"success\":false,\"result\":2}";
	final static String USER_NOT_LOGIN = "{\"success\":false,\"result\":3}";
	final static String ERROR_PWD = "{\"success\":false,\"result\":4}";
	final static String USER_NOT_EXIST = "{\"success\":false,\"result\":5}";
	final static String EMAIL_ALREADY_REGISTER = "{\"success\":false,\"result\":6}";
	final static String USER_ALREADY_REGISTER = "{\"success\":false,\"result\":7}";
}
