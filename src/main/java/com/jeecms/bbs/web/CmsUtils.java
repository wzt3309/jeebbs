package com.jeecms.bbs.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.core.entity.CmsSite;

/**
 * 提供一些CMS系统中使用到的共用方法
 * 
 * 比如获得会员信息,获得后台站点信息
 * 
 * @author liufang
 * 
 */
public class CmsUtils {
	/**
	 * 用户KEY
	 */
	public static final String USER_KEY = "_user_key";
	/**
	 * 站点KEY
	 */
	public static final String SITE_KEY = "_site_key";

	/**
	 * 获得用户
	 * 
	 * @param request
	 * @return
	 */
	public static BbsUser getUser(HttpServletRequest request) {
		return (BbsUser) request.getAttribute(USER_KEY);
	}

	/**
	 * 获得用户ID
	 * 
	 * @param request
	 * @return
	 */
	public static Integer getUserId(HttpServletRequest request) {
		BbsUser user = getUser(request);
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}

	/**
	 * 设置用户
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUser(HttpServletRequest request, BbsUser user) {
		request.setAttribute(USER_KEY, user);
	}

	/**
	 * 获得站点
	 * 
	 * @param request
	 * @return
	 */
	public static CmsSite getSite(HttpServletRequest request) {
		System.out.println(request.getAttribute(SITE_KEY));
		return (CmsSite) request.getAttribute(SITE_KEY);
	}

	/**
	 * 设置站点
	 * 
	 * @param request
	 * @param site
	 */
	public static void setSite(HttpServletRequest request, CmsSite site) {
		request.setAttribute(SITE_KEY, site);
	}

	/**
	 * 获得站点ID
	 * 
	 * @param request
	 * @return
	 */
	public static Integer getSiteId(HttpServletRequest request) {
		return getSite(request).getId();
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}
}
