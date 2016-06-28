package com.jeecms.bbs.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class OnlineCountListener implements HttpSessionAttributeListener ,HttpSessionListener{
	/**
	 * 定义监听的session属性名.
	 */
	public final static String LISTENER_NAME = "user_name";

	/**
	 * 定义存储客户登录session的集合.
	 */
	private static List sessions = new ArrayList();
	
	private static int userSum=0;

	/**
	 * 加入session时的监听方法.
	 * 
	 * @param HttpSessionBindingEvent
	 *            session事件
	 */
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		if (LISTENER_NAME.equals(sbe.getName())) {
			sessions.add(sbe.getValue());
		}
	}

	/**
	 * session失效时的监听方法.
	 * 
	 * @param HttpSessionBindingEvent
	 *            session事件
	 */
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		if (LISTENER_NAME.equals(sbe.getName())) {
			sessions.remove(sbe.getValue());
		}
	}

	/**
	 * session覆盖时的监听方法.
	 * 
	 * @param HttpSessionBindingEvent
	 *            session事件
	 */
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		if (LISTENER_NAME.equals(sbe.getName())) {
			sessions.remove(sbe.getValue());
			sessions.add(sbe.getValue());
		}
	}
	public void sessionCreated(HttpSessionEvent arg) {
		userSum++;
	}

	public void sessionDestroyed(HttpSessionEvent arg) {
		userSum--;
	}

	/**
	 * 返回客户登录session的集合.
	 * 
	 * @return
	 */
	public static List getSessions() {
		return sessions;
	}
	
	public static int getMemberSum() {
		return sessions.size();
	}
	
	public static int getSum() {
		return userSum;
	}

	public static Boolean isExistInSessions(String username) {
		String user_name;
		for (Integer i = 0; i < sessions.size(); i++) {
			user_name = (String) sessions.get(i);
			if (user_name.equals(username)) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}

}
