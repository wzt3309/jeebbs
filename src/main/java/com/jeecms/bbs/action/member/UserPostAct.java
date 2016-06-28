package com.jeecms.bbs.action.member;

import static com.jeecms.bbs.Constants.TPLDIR_MEMBER;
import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.bbs.entity.BbsCreditExchange;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserExt;
import com.jeecms.bbs.entity.BbsUserOnline;
import com.jeecms.bbs.manager.BbsCreditExchangeMng;
import com.jeecms.bbs.manager.BbsForumMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.manager.BbsUserOnlineMng;
import com.jeecms.bbs.manager.impl.BbsUserOnlineMngImpl;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.security.encoder.Md5PwdEncoder;
import com.jeecms.common.security.encoder.PwdEncoder;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.springmvc.MessageResolver;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;

@Controller
public class UserPostAct {

	public static final String MEMBER_CENTER = "tpl.memberCenter";
	public static final String MEMBER_INFORM = "tpl.information";
	public static final String MEMBER_TOPIC = "tpl.memberTopic";
	public static final String MEMBER_POST = "tpl.memberPost";
	public static final String SEARCH = "tpl.search";
	public static final String SEARCH_RESULT = "tpl.searchResult";
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	public static final String TPL_CREDIT = "tpl.creditMng";
	public static final String TPL_EDIT_USERIMG = "tpl.edituserimg";
	private static final String PASSWORD_RESET_OK = "tpl.passwordResetOk";
	

	@RequestMapping("/member/index.jhtml")
	public String index(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_CENTER);
	}

	@RequestMapping("/member/information.jhtml")
	public String information(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
        System.out.println(FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_INFORM));
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_INFORM);
	}
	
	
	@RequestMapping("/member/editUserImg.jhtml")
	public String editUserImg(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, TPL_EDIT_USERIMG);
	}

	@RequestMapping("/member/update.jspx")
	public String informationSubmit(String email,String telephone,
			String newPassword, String signed, String avatar, BbsUserExt ext,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		user = manager.updateMember(user.getId(), email,telephone, newPassword, null, signed,
				avatar, ext, null);
	
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_INFORM);
	}
	//密码找回将临时密码修改完成
		@RequestMapping(value ="/member/password_reset_ok.jspx", method = RequestMethod.POST)
		public String forgetPasswordReset(String userName, String oldpassword, String newPassword,
				HttpServletRequest request, HttpServletResponse response,ModelMap model){
			CmsSite site = CmsUtils.getSite(request);
			UnifiedUser user=unifiedUserMng.getByUsername(userName);	
			Integer userId=user.getId();
			System.out.println(userId);	
			BbsUser u=null;
			if(user==null){
				// 用户不存在
				model.addAttribute("status", 1);
			}else {
				if(unifiedUserMng.isPasswordValid(userId, oldpassword)){				
					//找回并修改密码成功
					System.out.println("临时密码验证成功");
					u=manager.updateMemberPwd(userId, newPassword);
					System.out.println(user.toString()+user.getEmail());
					model.addAttribute("status", 0);
					model.put("u", user);
					
				}else{
					//临时密码输入错误
					model.addAttribute("status", 2);
				}
				
				
			}
			FrontUtils.frontData(request, model, site);
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_MEMBER,PASSWORD_RESET_OK);
		}
	@RequestMapping("/member/mytopic*.jhtml")
	public String mytopic(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_TOPIC);
	}

	@RequestMapping("/member/mypost*.jhtml")
	public String mypost(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_POST);
	}

	@RequestMapping(value = "/member/inputSearch.jhtml", method = RequestMethod.GET)
	public String search(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, SEARCH);
	}

	@RequestMapping(value = "/member/search*.jhtml")
	public String searchSubmit( Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		String keywords = RequestUtils.getQueryParam(request, "keywords");
		
		Integer forumId=Integer.parseInt(RequestUtils.getQueryParam(request, "forumId"));
		
		model.put("keywords", keywords);
		model.put("forumId", forumId);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, SEARCH_RESULT);
	}

	@RequestMapping("/member/creditManager.jhtml")
	public String creditManager(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsCreditExchange creditExchangeRule = creditExchangeMng.findById(site
				.getId());
		Boolean exchangeAvailable = false;
		if (creditExchangeRule.getPointinavailable()
				&& creditExchangeRule.getPrestigeoutavailable()
				|| creditExchangeRule.getPointoutavailable()
				&& creditExchangeRule.getPrestigeinavailable()) {
			exchangeAvailable = true;
		} else {
			exchangeAvailable = false;
		}
		if (exchangeAvailable) {
			if (!creditExchangeRule.getExpoint().equals(0)
					&& !creditExchangeRule.getExprestige().equals(0)) {
				exchangeAvailable = true;
			} else {
				exchangeAvailable = false;
			}
		}
		List<BbsForum> forums = forumMng.getList(site.getId());
		model.put("user", user);
		model.put("exchangeAvailable", exchangeAvailable);
		model.put("pointInAvail", creditExchangeRule.getPointinavailable());
		model.put("pointOutAvail", creditExchangeRule.getPointoutavailable());
		model.put("prestigeInAvail", creditExchangeRule
				.getPrestigeinavailable());
		model.put("prestigeOutAvail", creditExchangeRule
				.getPrestigeoutavailable());
		model.put("forums", forums);
		model.put("creditExchangeRule", creditExchangeRule);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, TPL_CREDIT);
	}

	@RequestMapping(value = "/member/getCreditOutValue.jspx")
	public void getCreditOutValue(Integer creditIn, Integer creditInType,
			Integer creditOutType, double exchangetax,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject object = new JSONObject();
		Long creditOutValue = null;
		Double temp = 0.0;
		BbsCreditExchange rule = CmsUtils.getSite(request).getCreditExchange();
		if (creditInType.equals(1) && creditOutType.equals(2)) {
			// 积分换取威望
			temp = creditIn * rule.getExpoint() * 1.0 / rule.getExprestige()
					* (1.0 + exchangetax);
		} else if (creditInType.equals(2) && creditOutType.equals(1)) {
			// 威望换积分
			temp = creditIn * rule.getExprestige() * 1.0 / rule.getExpoint()
					* (1.0 + exchangetax);
		}
		creditOutValue = Long.valueOf((long) Math.ceil(temp));
		try {
			object.put("creditOutValue", creditOutValue);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequestMapping(value = "/member/creditExchange.jspx")
	public void creditExchange(Integer creditIn, Integer creditOut,
			Integer creditOutType, Integer miniBalance, String password,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject object = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		Boolean result=true;
		Boolean balance=false;
		Integer flag=0;
		String message=MessageResolver.getMessage(request, "cmspoint.success");;
		//兑出的积分是否充足
		if(user!=null&&creditOutType.equals(1)){
			if(user.getPoint()-creditOut>miniBalance){
				balance=true;
			}else{
				flag=1;
			}
		}
		//兑出的威望是否充足
		if(user!=null&&creditOutType.equals(2)){
			if(user.getPrestige()-creditOut>miniBalance){
				balance=true;
			}else{
				flag=2;
			}
		}
		if (!pwdEncoder.isPasswordValid(unifiedUserMng.getByUsername(
				user.getUsername()).getPassword(), password)) {
			result = false;
			message=MessageResolver.getMessage(request, "cmscredit.passworderror");
		} else if (!balance) {
			result = false;
			if(flag.equals(1)){
				message=MessageResolver.getMessage(request, "cmscredit.pointisnotenough",miniBalance);
			}else if(flag.equals(2)){
				message=MessageResolver.getMessage(request, "cmscredit.prestigeisnotenough",miniBalance);
			}
		}else{
			if(creditOutType.equals(1)){
				user.setPoint(user.getPoint()-creditOut);
				user.setPrestige(user.getPrestige()+creditIn);
			}else if(creditOutType.equals(2)){
				user.setPrestige(user.getPrestige()-creditOut);
				user.setPoint(user.getPoint()+creditIn);
			}
			//此处更新用户积分威望信息
			manager.updatePwdEmail(user.getId(), password, user.getEmail());
		}
		try {
			object.put("message", message);
			object.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@Autowired
	private BbsUserMng manager;
	@Autowired
	private BbsCreditExchangeMng creditExchangeMng;
	@Autowired
	private BbsForumMng forumMng;
	@Autowired
	private PwdEncoder pwdEncoder;
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	
	private BbsUserOnlineMng bbsUserOnlineMng;
}
