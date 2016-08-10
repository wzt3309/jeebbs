package com.jeecms.bbs.action.front;

import static com.jeecms.bbs.Constants.TPLDIR_MEMBER;

import java.io.IOException;
import java.util.Calendar;

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

import com.jeecms.bbs.cache.BbsConfigEhCache;
import com.jeecms.bbs.entity.BbsConfig;
import com.jeecms.bbs.entity.BbsLoginLog;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserExt;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.entity.BbsUserOnline;
import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.bbs.manager.BbsLoginLogMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.manager.BbsUserOnlineMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.bbs.web.WebErrors;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.util.SMSTest;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 前台会员注册Action
 * 
 * @author liqiang
 * 
 */
@Controller
public class RegisterAct {
	private static final Logger log = LoggerFactory
			.getLogger(RegisterAct.class);

	public static final String REGISTER = "tpl.register";
	public static final String REGISTER_RESULT = "tpl.registerResult";
	public static final String REGISTER_ACTIVE_SUCCESS = "tpl.registerActiveSuccess";
	public static final String LOGIN_INPUT = "tpl.loginInput";

	@RequestMapping(value = "/register.jspx", method = RequestMethod.GET)
	public String input(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String username=RequestUtils.getQueryParam(request, "username");
		String password=RequestUtils.getQueryParam(request, "password");
		String telephone=RequestUtils.getQueryParam(request, "telephone");
		String email=RequestUtils.getQueryParam(request, "email");
		//邀请人用户名
		String invitename=RequestUtils.getQueryParam(request, "invitename");
		model.put("username", username);
		model.put("password", password);
		model.put("telephone", telephone);
		model.put("email", email);
		model.put("invitename", invitename);
		System.out.println(FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, REGISTER));
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, REGISTER);
	}

	@RequestMapping(value = "/register_reset.jspx", method = RequestMethod.GET)
	public String submit_reset(String username, String email, String password,String telephone,
			BbsUserExt userExt, String captcha, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		System.out.println("reset get in");
		model.put("username", username);
		model.put("password", password);
		model.put("telephone", telephone);
		model.put("email", email);
		
		CmsSite site = CmsUtils.getSite(request);
		BbsConfig config = bbsConfigMng.findById(site.getId());
		
		double captche=Math.random()*9000+1000;
		String cap=String.valueOf(captche);
		String capture="欢迎注册牵牛网，您的验证码为："+cap.substring(0, 4)+"。【牵牛网】";
		try {
			SMSTest.send(capture, telephone);
			model.put("capture", cap.substring(0, 4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		


		
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		model.addAttribute("success",true);
		
		return "/WEB-INF/t/cms/www/blue/member/会员注册页after.html";
		
		
		
		
	}
	
	
	
	@RequestMapping(value = "/register_after.jspx", method = RequestMethod.POST)
	public String submit_after(String username, String email, String invitename,String password,String telephone,
			BbsUserExt userExt, String captcha, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		System.out.println(telephone);
		CmsSite site = CmsUtils.getSite(request);
		BbsConfig config = bbsConfigMng.findById(site.getId());
		WebErrors errors = validateSubmit_after(username, email,invitename, password, captcha,
				site, request, response);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		String ip = RequestUtils.getIpAddr(request);
		Integer groupId = null;
		BbsUserGroup group = bbsConfigMng.findById(site.getId())
				.getRegisterGroup();
		if (group != null) {
			groupId = group.getId();
		}
		BbsUser user = null;
		if(config.getEmailValidate()){
			EmailSender sender = configMng.getEmailSender();
			MessageTemplate msgTpl = configMng.getRegisterMessageTemplate();
			if (sender == null) {
				// 邮件服务器没有设置好
				model.addAttribute("status", 4);
			} else if (msgTpl == null) {
				// 邮件模板没有设置好
				model.addAttribute("status", 5);
			} else {
				try {
					System.out.println("here1");
					user = bbsUserMng.registerMember(username, email,invitename,telephone, password, ip,
							groupId, userExt, false, sender, msgTpl);
					bbsConfigEhCache.setBbsConfigCache(0, 0, 0, 1, user, site.getId());
					model.addAttribute("status", 0);
				} catch (Exception e) {
					// 发送邮件异常
					System.out.println("here2");
					model.addAttribute("status", 100);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception.", e);
					errors.addErrorString("邮件发送不成功！");
					return FrontUtils.showError(request, response, model, errors);
				}
			}
			log.info("member register success. username={}", username);
			if (!StringUtils.isBlank(nextUrl)) {
				System.out.println("here3");				
				response.sendRedirect(nextUrl);
				System.out.println("null");
				return null;
			} else {
				System.out.println("here4");
				FrontUtils.frontData(request, model, site);
				FrontUtils.frontPageData(request, model);
				System.out.println(FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_MEMBER, REGISTER_RESULT));
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_MEMBER, REGISTER_RESULT);
			}
		}else{ 
			System.out.println("转登陆");
			user = bbsUserMng.registerMember(username, email,invitename, telephone,password,
			  ip, groupId, userExt);
			bbsConfigEhCache.setBbsConfigCache(0, 0, 0, 1, user, site.getId());
			log.info("member register success. username={}", username);
			FrontUtils.frontData(request, model, site);
			FrontUtils.frontPageData(request, model);
			model.addAttribute("success",true);
			System.out.println(FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, LOGIN_INPUT));
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, LOGIN_INPUT);
		}
		
		/*
		 * BbsUser user = bbsUserMng.registerMember(username, email, password,
		 * ip, groupId, userExt);
		 */
	}
	
	
	
	
	@RequestMapping(value = "/register.jspx", method = RequestMethod.POST)
	public String submit(String username, String email,String invitename, String password,String telephone,
			BbsUserExt userExt, String captcha, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		System.out.println(telephone);
		CmsSite site = CmsUtils.getSite(request);
		BbsConfig config = bbsConfigMng.findById(site.getId());
		WebErrors errors = validateSubmit(username, email, invitename,password, captcha,
				site, request, response);
		if (errors.hasErrors()) {
			System.out.println(FrontUtils.showError(request, response, model, errors));
			return FrontUtils.showError(request, response, model, errors);
		}
		String ip = RequestUtils.getIpAddr(request);
		Integer groupId = null;
		BbsUserGroup group = bbsConfigMng.findById(site.getId())
				.getRegisterGroup();
		if (group != null) {
			groupId = group.getId();
		}
		BbsUser user = null;
		if(telephone.length()!=11){
			model.put("username", username);
			model.put("password", password);
			model.put("email", email);
			model.put("telephone", telephone);
			model.put("invitename", invitename);
			model.put("statue", "手机号码格式错误");
			
			FrontUtils.frontData(request, model, site);
			FrontUtils.frontPageData(request, model);
			model.addAttribute("success",true);
			
			return "/WEB-INF/t/cms/www/blue/member/会员注册页.html";
			
		}else{ 
			System.out.println("转登陆");
			
			model.put("username", username);
			model.put("password", password);
			model.put("email", email);
			model.put("telephone", telephone);
			model.put("invitename", invitename);
			/*手机验证码方式*/
//			double captche=Math.random()*9000+1000;
//			String cap=String.valueOf(captche);
//			
//			String capture="欢迎注册牵牛网，您的验证码为："+cap.substring(0, 4)+"。【牵牛网】";
//			try {
//				SMSTest.send(capture, telephone);
//				model.put("capture",cap.substring(0, 4));
//				model.put("statue", "发送验证码成功");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				model.put("statue", "发送验证码失败，请重试");
//			}
			
			
			


			
			FrontUtils.frontData(request, model, site);
			FrontUtils.frontPageData(request, model);
			model.addAttribute("success",true);
			System.out.println(FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, LOGIN_INPUT));
			return "/WEB-INF/t/cms/www/blue/member/会员注册页after.html";
		}
		
		
//	 BbsUser user = bbsUserMng.registerMember(username, email, password,
//		  ip, groupId, userExt);
		 
	}

	// 激活账号
	@RequestMapping(value = "/active.jspx", method = RequestMethod.GET)
	public String active(String username, String key,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateActive(username, key, request, response);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		UnifiedUser user = unifiedUserMng.active(username, key);
		BbsUser bbsUser = bbsUserMng.findById(user.getId());
		String ip = RequestUtils.getIpAddr(request);
		authMng.activeLogin(user, ip, request, response, session);
		// 登录记录
		BbsLoginLog loginLog = new BbsLoginLog();
		loginLog.setIp(RequestUtils.getIpAddr(request));
		Calendar calendar = Calendar.getInstance();
		loginLog.setLoginTime(calendar.getTime());
		loginLog.setUser(bbsUser);
		bbsLoginMng.save(loginLog);
		// 在线时长统计
		BbsUserOnline online = bbsUser.getUserOnline();
		// 首次登陆
		online = new BbsUserOnline();
		online.setUser(bbsUser);
		online.initial();
		userOnlineMng.save(online);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, REGISTER_ACTIVE_SUCCESS);
	}

	@RequestMapping(value = "/username_unique.jspx")
	public void usernameUnique(HttpServletRequest request,
			HttpServletResponse response) {
		String username = RequestUtils.getQueryParam(request, "username");
		// 用户名为空，返回false。
		if (StringUtils.isBlank(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// 用户名存在，返回false。
		if (unifiedUserMng.usernameExist(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}
	
	@RequestMapping(value = "/invitename_exist.jspx")
	public void invitenameExist(HttpServletRequest request,
			HttpServletResponse response) {
		String invitename = RequestUtils.getQueryParam(request, "invitename");
		// 用户名为空，返回false。
		if (StringUtils.isBlank(invitename)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// 用户名不存在，返回false。
		if (!unifiedUserMng.usernameExist(invitename)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}

	@RequestMapping(value = "/email_unique.jspx")
	public void emailUnique(HttpServletRequest request,
			HttpServletResponse response) {
		String email = RequestUtils.getQueryParam(request, "email");
		// email为空，返回false。
		if (StringUtils.isBlank(email)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// email存在，返回false。
		if (unifiedUserMng.emailExist(email)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}

	private WebErrors validateSubmit(String username, String email,String invitename,
			String password, String captcha, CmsSite site,
			HttpServletRequest request, HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
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
		if (errors.ifMaxLength(email, "email", 100)) {
			return errors;
		}
		// 用户名存在，返回false。
		if (unifiedUserMng.usernameExist(username)) {
			errors.addErrorCode("error.usernameExist");
			return errors;
		}
		//如果邀请人不存在，返回false
		if (!unifiedUserMng.usernameExist(invitename)) {
			errors.addErrorCode("error.invitenameNotExist");
			return errors;
		}
		return errors;
	}
	
	private WebErrors validateSubmit_after(String username, String email,String invitename,
			String password, String captcha, CmsSite site,
			HttpServletRequest request, HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		
		if (errors.ifMaxLength(email, "email", 100)) {
			return errors;
		}
		// 用户名存在，返回false。
		if (unifiedUserMng.usernameExist(username)) {
			errors.addErrorCode("error.usernameExist");
			return errors;
		}
		//如果邀请人不存在，返回false
		if (!unifiedUserMng.usernameExist(invitename)) {
			errors.addErrorCode("error.invitenameNotExist");
			return errors;
		}
		return errors;
	}
	

	private WebErrors validateActive(String username, String activationCode,
			HttpServletRequest request, HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		if (StringUtils.isBlank(username)
				|| StringUtils.isBlank(activationCode)) {
			errors.addErrorCode("error.exceptionParams");
			return errors;
		}
		UnifiedUser user = unifiedUserMng.getByUsername(username);
		if (user == null) {
			errors.addErrorCode("error.usernameNotExist");
			return errors;
		}
		if (user.getActivation()
				|| StringUtils.isBlank(user.getActivationCode())) {
			errors.addErrorCode("error.usernameActivated");
			return errors;
		}
		if (!user.getActivationCode().equals(activationCode)) {
			errors.addErrorCode("error.exceptionActivationCode");
			return errors;
		}
		return errors;
	}

	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private BbsConfigMng bbsConfigMng;
	@Autowired
	private BbsConfigEhCache bbsConfigEhCache;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	private AuthenticationMng authMng;
	@Autowired
	private BbsLoginLogMng bbsLoginMng;
	@Autowired
	private BbsUserOnlineMng userOnlineMng;

}
