package com.jeecms.bbs.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.CmsSensitivity;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.manager.CmsSensitivityMng;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.manager.CmsSiteMng;

/**
 * BBS上下文信息拦截器
 * 
 * 包括登录信息、权限信息、站点信息
 * 
 * @author liqiang
 * 
 */
public class FrontContextInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {
		CmsSite site = null;
		List<CmsSite> list = cmsSiteMng.getListFromCache();
		int size = list.size();
		if (size == 0) {
			throw new RuntimeException("no site record in database!");
		} else if (size == 1) {
			site = list.get(0);
		} else {
			String server = request.getServerName();
			String alias, redirect;
			for (CmsSite s : list) {
				// 检查域名
				if (s.getDomain().equals(server)) {
					site = s;
					break;
				}
				// 检查域名别名
				alias = s.getDomainAlias();
				if (!StringUtils.isBlank(alias)) {
					for (String a : StringUtils.split(alias, ',')) {
						if (a.equals(server)) {
							site = s;
							break;
						}
					}
				}
				// 检查重定向
				redirect = s.getDomainRedirect();
				if (!StringUtils.isBlank(redirect)) {
					for (String r : StringUtils.split(redirect, ',')) {
						if (r.equals(server)) {
							try {
								response.sendRedirect(s.getUrl());
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
							return false;
						}
					}
				}
			}
			if (site == null) {
				throw new SiteNotFoundException(server);
			}
		}
		List<CmsSensitivity> sensitivityList =cmsSensitivityMng.getList(site.getId(), true);
		CmsUtils.setSite(request, site);
		// 敏感词加入线程变量
		CmsThreadVariable.setSensitivityList(sensitivityList);

		BbsUser user = null;
		Integer userId = authMng.retrieveUserIdFromSession(session, request);
		if (userId != null) {
			user = bbsUserMng.findById(userId);
		}

		if (user != null) {
			CmsUtils.setUser(request, user);
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//清空敏感词
		CmsThreadVariable.removeSensitivityList();
	}



	private SessionProvider session;
	private CmsSiteMng cmsSiteMng;
	private BbsUserMng bbsUserMng;
	private AuthenticationMng authMng;
	private CmsSensitivityMng cmsSensitivityMng;

	@Autowired
	public void setSession(SessionProvider session) {
		this.session = session;
	}

	@Autowired
	public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
		this.cmsSiteMng = cmsSiteMng;
	}
	
	@Autowired
	public void setBbsUserMng(BbsUserMng bbsUserMng) {
		this.bbsUserMng = bbsUserMng;
	}

	@Autowired
	public void setAuthMng(AuthenticationMng authMng) {
		this.authMng = authMng;
	}
	
	@Autowired
	public void setCmsSensitivityMng(CmsSensitivityMng cmsSensitivityMng) {
		this.cmsSensitivityMng = cmsSensitivityMng;
	}
}