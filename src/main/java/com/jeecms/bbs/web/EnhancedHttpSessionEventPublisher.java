package com.jeecms.bbs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class EnhancedHttpSessionEventPublisher extends
		HttpSessionEventPublisher {
	private static List OnlineUserList = new ArrayList<String>();  
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// 将用户加入到在线用户列表中
		saveOrDeleteOnlineUser(event, Type.SAVE);
		HttpSessionCreatedEvent e = new HttpSessionCreatedEvent(event.getSession());
        getContext(event.getSession().getServletContext()).publishEvent(e);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// 将用户从在线用户列表中移除
		saveOrDeleteOnlineUser(event, Type.DELETE);
		 HttpSessionDestroyedEvent e = new HttpSessionDestroyedEvent(event.getSession());
		 getContext(event.getSession().getServletContext()).publishEvent(e);
	}
	
	ApplicationContext getContext(ServletContext servletContext) {
	        return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	    }

	public void saveOrDeleteOnlineUser(HttpSessionEvent event, Type type) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		SecurityContext ctx=SecurityContextHolder.getContext();
		if (auth != null) {
			Object principal = auth.getPrincipal();
			if (principal instanceof User) {
				User user = (User) principal;

				switch (type) {
				case SAVE:
					OnlineUserList.add(user.getUsername());// List<String>
					break;
				case DELETE:
					OnlineUserList.remove(user.getUsername());
					break;
				}
			}
		}
	}

	/**
	 * 定义一个简单的内部枚举
	 */
	private static enum Type {
		SAVE, DELETE;
	}

}
