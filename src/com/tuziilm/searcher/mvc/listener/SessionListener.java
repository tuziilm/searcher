package com.tuziilm.searcher.mvc.listener;

import com.google.common.base.Strings;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.ConcurrentHashMap;

@WebListener()
public class SessionListener implements HttpSessionListener{
    public final static ConcurrentHashMap<String, HttpSession> sessionMap = new ConcurrentHashMap<>();
    public SessionListener() {
    }

    public void sessionCreated(HttpSessionEvent se) {
      sessionMap.putIfAbsent(se.getSession().getId(), se.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      sessionMap.remove(se.getSession().getId());
    }

    public final static HttpSession getSession(String token){
        if(Strings.isNullOrEmpty(token)){
            return null;
        }
        return sessionMap.get(token);
    }
}
