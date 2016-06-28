package com.jeecms.bbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserExt;
import com.jeecms.bbs.manager.BbsUserExtMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.WebErrors;
import com.jeecms.common.web.ResponseUtils;

@Controller
public class PersonalAct {
	@RequestMapping("/personal/v_profile.do")
	public String profileEdit(HttpServletRequest request, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		model.addAttribute("user", user);
		return "personal/profile";
	}

	@RequestMapping("/personal/o_profile.do")
	public String profileUpdate(String origPwd, String newPwd, String email,
			String realname, HttpServletRequest request, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		WebErrors errors = validatePasswordSubmit(user.getId(), origPwd,
				newPwd, email, realname, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		BbsUserExt ext = user.getUserExt();
		ext.setRealname(realname);
		bbsUserExtMng.update(ext, user);
		bbsUserMng.updatePwdEmail(user.getId(), newPwd, email);
		model.addAttribute("message", "global.success");
		return profileEdit(request, model);
	}

	/**
	 * 验证密码是否正确
	 * 
	 * @param origPwd
	 *            原密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/personal/v_checkPwd.do")
	public void checkPwd(String origPwd, HttpServletRequest request,
			HttpServletResponse response) {
		BbsUser user = CmsUtils.getUser(request);
		boolean pass = bbsUserMng.isPasswordValid(user.getId(), origPwd);
		ResponseUtils.renderJson(response, pass ? "true" : "false");
	}

	private WebErrors validatePasswordSubmit(Integer id, String origPwd,
			String newPwd, String email, String realname,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifBlank(origPwd, "origPwd", 32)) {
			return errors;
		}
		if (errors.ifMaxLength(newPwd, "newPwd", 32)) {
			return errors;
		}
		if (errors.ifMaxLength(email, "email", 100)) {
			return errors;
		}
		if (errors.ifMaxLength(realname, "realname", 100)) {
			return errors;
		}
		if (!bbsUserMng.isPasswordValid(id, origPwd)) {
			errors.addErrorCode("member.origPwdInvalid");
			return errors;
		}
		return errors;
	}

	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private BbsUserExtMng bbsUserExtMng;
}
