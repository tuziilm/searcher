package com.tuziilm.searcher.common;

import org.codehaus.jackson.map.ObjectMapper;

public interface JsonSupport {
	/** ObjectMapper是线程安全的*/
	final static ObjectMapper mapper=new ObjectMapper();
	final static String FAIL_JSON="{\"success\":false}";
	final static String SIMPLE_SUCCESS_JSON="{\"success\":true}";
	final static String FAIL_JSONP="({\"success\":false})";
	final static String SIMPLE_SUCCESS_JSONP="({\"success\":true})";
	final static String MISS_PARAM = "{{\"errorcode\":1}}";
	final static String ERROR_PARAM = "{{\"errorcode\":2}}";
	final static String USER_NOT_LOGIN = "{{\"errorcode\":3}}";
	final static String ERROR_PWD = "{{\"result\":1}}";
	final static String USER_NOT_EXIST = "{{\"result\":2}}";
}
