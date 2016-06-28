package com.jeecms.bbs.action.login;

import static com.jeecms.core.action.front.LoginAct.MESSAGE;
import static com.jeecms.core.action.front.LoginAct.PROCESS_URL;
import static com.jeecms.core.action.front.LoginAct.RETURN_URL;
import static com.jeecms.core.manager.AuthenticationMng.AUTH_KEY;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserOnline;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.manager.BbsUserOnlineMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.DisabledException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.web.WebErrors;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class CmsLoginAct {
	private static final Logger log = LoggerFactory
			.getLogger(CmsLoginAct.class);
	private static String LAST_KEEP_SESSION_TIME = "last_keep_session_time";

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String input(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String processUrl = RequestUtils.getQueryParam(request, PROCESS_URL);
		String returnUrl = RequestUtils.getQueryParam(request, RETURN_URL);
		String message = RequestUtils.getQueryParam(request, MESSAGE);
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId != null) {
			// 存在认证ID
			Authentication auth = authMng.retrieve(authId);
			// 存在认证信息，且未过期
			if (auth != null) {
				String view = getView(processUrl, returnUrl, auth.getId());
				if (view != null) {
					return view;
				} else {
					model.addAttribute("auth", auth);
					return "logon";
				}
			}
		}
		if (!StringUtils.isBlank(processUrl)) {
			model.addAttribute(PROCESS_URL, processUrl);
		}
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
		}
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return "login";
	}
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String submit(String username, String password, String captcha,
			String processUrl, String returnUrl, String message,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		WebErrors errors = validateSubmit(username, password, captcha, request,
				response);
		if (!errors.hasErrors()) {
			try {
				String ip = RequestUtils.getIpAddr(request);
				Authentication auth = authMng.login(username, password, ip,
						request, response, session);
				// 是否需要在这里加上登录次数的更新？按正常的方式，应该在process里面处理的，不过这里处理也没大问题。
				bbsUserMng.updateLoginInfo(auth.getUid(), ip);
				BbsUser user = bbsUserMng.findById(auth.getUid());
				if (user.getDisabled() != null && user.getDisabled()) {
					// 如果已经禁用，则推出登录。
					authMng.deleteById(auth.getId());
					session.logout(request, response);
					throw new DisabledException("user disabled");
				}
				BbsUserOnline online = user.getUserOnline();
				Calendar calendar = Calendar.getInstance();
				if (online != null) {
					// 更新在线信息(这里对最后一次登陆时长做初始化，其余初始化用定时任务)
					online.setOnlineLatest(0d);
					userOnlineMng.update(online);
				} else {
					// 首次登陆
					online = new BbsUserOnline();
					online.setUser(user);
					online.initial();
					userOnlineMng.save(online);
				}
				session.setAttribute(request, response, LAST_KEEP_SESSION_TIME, calendar.getTime());
				String view = getView(processUrl, returnUrl, auth.getId());
				if (view != null) {
					return view;
				} else {
					return "redirect:login.jspx";
				}
			} catch (UsernameNotFoundException e) {
				errors.addErrorString(e.getMessage());
			} catch (BadCredentialsException e) {
				errors.addErrorString(e.getMessage());
			} catch (DisabledException e) {
				errors.addErrorString(e.getMessage());
			}
		}
		// 登录失败
		errors.toModel(model);
		if (!StringUtils.isBlank(processUrl)) {
			model.addAttribute(PROCESS_URL, processUrl);
		}
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
		}
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return "login";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		BbsUser user = CmsUtils.getUser(request);
		Calendar calendar = Calendar.getInstance();
		BbsUserOnline online = user.getUserOnline();
		Date lastKeepSessionTime = (Date) session.getAttribute(request,
				LAST_KEEP_SESSION_TIME);
		online.updateOnline(DateUtils.diffTwoDate(lastKeepSessionTime, calendar
				.getTime()));
		userOnlineMng.update(online);
		if (authId != null) {
			authMng.deleteById(authId);
			session.logout(request, response);
		}
		String processUrl = RequestUtils.getQueryParam(request, PROCESS_URL);
		String returnUrl = RequestUtils.getQueryParam(request, RETURN_URL);
		String view = getView(processUrl, returnUrl, authId);
		if (view != null) {
			return view;
		} else {
			return "redirect:login.jspx";
		}
	}

	private WebErrors validateSubmit(String username, String password,
			String captcha, HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifBlank(captcha, "captcha", 100)) {
			return errors;
		}
		try {
			if (!imageCaptchaService.validateResponseForID(session
					.getSessionId(request, response), captcha)) {
				errors.addErrorCode("error.invalidCaptcha");
				return errors;
			}
		} catch (CaptchaServiceException e) {
			errors.addErrorCode("error.exceptionCaptcha");
			log.warn("", e);
			return errors;
		}
		if (errors.ifOutOfLength(username, "username", 1, 100)) {
			return errors;
		}
		if (errors.ifOutOfLength(password, "password", 1, 32)) {
			return errors;
		}
		return errors;
	}

	/**
	 * 获得地址
	 * 
	 * @param processUrl
	 * @param returnUrl
	 * @param authId
	 * @param defaultUrl
	 * @return
	 */
	private String getView(String processUrl, String returnUrl, String authId) {
		if (!StringUtils.isBlank(processUrl)) {
			StringBuilder sb = new StringBuilder("redirect:");
			sb.append(processUrl).append("?").append(AUTH_KEY).append("=")
					.append(authId);
			if (!StringUtils.isBlank(returnUrl)) {
				sb.append("&").append(RETURN_URL).append("=").append(returnUrl);
			}
			return sb.toString();
		} else if (!StringUtils.isBlank(returnUrl)) {
			StringBuilder sb = new StringBuilder("redirect:");
			sb.append(returnUrl);
			return sb.toString();
		} else {
			return null;
		}
	}

	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private AuthenticationMng authMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private BbsUserOnlineMng userOnlineMng;
}
