package com.jeecms.bbs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.entity.BbsConfig;
import com.jeecms.bbs.entity.BbsCreditExchange;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.bbs.manager.BbsCreditExchangeMng;
import com.jeecms.bbs.manager.BbsUserGroupMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.WebErrors;
import com.jeecms.core.entity.CmsConfig;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.Ftp;
import com.jeecms.core.entity.Config.ConfigEmailSender;
import com.jeecms.core.entity.Config.ConfigLogin;
import com.jeecms.core.entity.Config.ConfigMessageTemplate;
import com.jeecms.core.manager.CmsConfigMng;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.FtpMng;

@Controller
public class CmsSiteConfigAct {
	private static final Logger log = LoggerFactory
			.getLogger(CmsSiteConfigAct.class);

	@RequestMapping("/site_config/v_system_edit.do")
	public String systemEdit(HttpServletRequest request, ModelMap model) {
		model.addAttribute("cmsConfig", cmsConfigMng.get());
		return "site_config/system_edit";
	}

	@RequestMapping("/site_config/o_system_update.do")
	public String systemUpdate(CmsConfig bean, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSystemUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = cmsConfigMng.update(bean);
		model.addAttribute("message", "global.success");
		log.info("update systemConfig of CmsConfig.");
		return systemEdit(request, model);
	}

	@RequestMapping("/site_config/v_base_edit.do")
	public String baseEdit(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<Ftp> ftpList = ftpMng.getList();
		model.addAttribute("ftpList", ftpList);
		model.addAttribute("cmsSite", site);
		return "site_config/base_edit";
	}

	@RequestMapping("/site_config/o_base_update.do")
	public String baseUpdate(CmsSite bean, Integer uploadFtpId,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateBaseUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsSite site = CmsUtils.getSite(request);
		bean.setId(site.getId());
		bean = manager.update(bean, uploadFtpId);
		model.addAttribute("message", "global.success");
		log.info("update CmsSite success. id={}", site.getId());
		return baseEdit(request, model);
	}

	@RequestMapping("/bbs_config/v_edit.do")
	public String bbsEdit(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsConfig config = bbsConfigMng.findById(site.getId());
		List<BbsUserGroup> groupList = bbsUserGroupMng.getList(site.getId());
		model.addAttribute("config", config);
		model.addAttribute("groupList", groupList);
		return "site_config/bbs_edit";
	}

	@RequestMapping("/bbs_config/o_update.do")
	public String bbsUpdate(BbsConfig bean, Integer registerGroupId,
			Integer defaultGroupId, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		bean.setSite(site);
		bean.setRegisterGroup(bbsUserGroupMng.findById(registerGroupId));
		bean.setDefaultGroup(bbsUserGroupMng.findById(defaultGroupId));
		bbsConfigMng.update(bean);
		return bbsEdit(request, model);
	}
	@RequestMapping("/bbs_config/v_login_edit.do")
	public String loginEdit(HttpServletRequest request, ModelMap model) {
		model.addAttribute("configLogin", configMng.getConfigLogin());
		model.addAttribute("emailSender", configMng.getEmailSender());
		model.addAttribute("forgotPasswordTemplate", configMng.getForgotPasswordMessageTemplate());
		model.addAttribute("registerTemplate", configMng.getRegisterMessageTemplate());
		return "site_config/login_edit";
	}

	@RequestMapping("/bbs_config/o_login_update.do")
	public String loginUpdate(ConfigLogin configLogin,
			ConfigEmailSender emailSender, ConfigMessageTemplate msgTpl,
			HttpServletRequest request, ModelMap model) {
		configMng.updateOrSave(configLogin.getAttr());
		configMng.updateOrSave(emailSender.getAttr());
		configMng.updateOrSave(msgTpl.getAttr());
		model.addAttribute("message", "global.success");
		log.info("update loginCoinfig of Config.");
		return loginEdit(request, model);
	}
	
	@RequestMapping("/bbs_config/v_creditExchangeEdit.do")
	public String bbsCreditExchangeEdit(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsCreditExchange creditExchange =creditExchangeMng.findById(site.getId());
		model.addAttribute("creditExchange",creditExchange);
		return "site_config/credit_exchange_rule";
	}
	@RequestMapping("/bbs_config/v_creditExchangeUpdate.do")
	public String bbsCreditExchangeUpdate(BbsCreditExchange creditExchange ,HttpServletRequest request, ModelMap model) {
		if(creditExchange.getExchangetax()<1.0&&creditExchange.getExchangetax()>=0.0){
			creditExchangeMng.update(creditExchange);
			model.addAttribute("message", "global.success");
			log.info("update BbsCreditExchange");
		}
		return bbsCreditExchangeEdit(request, model);
	}

	private WebErrors validateSystemUpdate(CmsConfig bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateBaseUpdate(CmsSite bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	@Autowired
	private CmsSiteMng manager;
	@Autowired
	private FtpMng ftpMng;
	@Autowired
	private BbsConfigMng bbsConfigMng;
	@Autowired
	private BbsUserGroupMng bbsUserGroupMng;
	@Autowired
	private CmsConfigMng cmsConfigMng;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	private BbsCreditExchangeMng creditExchangeMng;
}