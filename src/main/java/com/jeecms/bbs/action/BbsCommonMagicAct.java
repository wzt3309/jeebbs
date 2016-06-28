package com.jeecms.bbs.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.MagicConstants;
import com.jeecms.bbs.entity.BbsCommonMagic;
import com.jeecms.bbs.entity.BbsMagicConfig;
import com.jeecms.bbs.entity.BbsMagicLog;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsCommonMagicMng;
import com.jeecms.bbs.manager.BbsMagicConfigMng;
import com.jeecms.bbs.manager.BbsMagicLogMng;
import com.jeecms.bbs.manager.BbsUserGroupMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.MagicMessage;

@Controller
public class BbsCommonMagicAct {
	private static final Logger log = LoggerFactory
			.getLogger(BbsCommonMagicAct.class);

	@RequestMapping("/magic/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		List<BbsCommonMagic> magics = manager.getList();
		model.addAttribute("magics", magics);
		return "magic/list";
	}

	@RequestMapping("/magic/user_list.do")
	public String user_list(String username, Integer groupId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Pagination pagination = userMng.getPage(username, null, groupId, false,
				null, null, null, null, null, null, SimplePage.cpn(pageNo),
				CookieUtils.getPageSize(request));
		List<BbsUserGroup> groupList = bbsUserGroupMng.getList(site.getId());
		model.addAttribute("groupList", groupList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("groupId", groupId);
		model.addAttribute("username", username);
		return "magic/userlist";
	}

	@RequestMapping("/magic/select_magic.do")
	public String select_magic(Integer userIds[], Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		List<BbsCommonMagic> magics = manager.getList();
		model.addAttribute("magics", magics);
		model.addAttribute("userIds", userIds);
		return "magic/selectmagic";
	}

	@RequestMapping("/magic/give_magic.do")
	public String give_magic(Integer userIds[], Integer ids[], Integer nums[],
			Integer pageNo, HttpServletRequest request, ModelMap model) {
		MagicMessage magicMessage = MagicMessage.create(request);
		String msg = magicMessage.getMessage("magic.give.success");
		List<Integer> numList = new ArrayList<Integer>();
		BbsCommonMagic magic;
		BbsUser user = CmsUtils.getUser(request);
		BbsUser targetUser;
		Integer id;
		Integer numTemp;
		// 去除无用的道具数量
		for (Integer num : nums) {
			if (!num.equals(0)) {
				numList.add(num);
			}
		}
		// 给用户赠送道具
		for (Integer userId : userIds) {
			targetUser = userMng.findById(userId);
			for (int i = 0; i < ids.length; i++) {
				id = ids[i];
				numTemp = numList.get(i);
				magic = manager.findById(id);
				userMng.updatePoint(userId, null, null, magic.getIdentifier(),
						numTemp, 4);
				saveMagicLog(magic, user, targetUser, numTemp,
						MagicConstants.MAGIC_OPERATOR_GIVE);
			}
		}
		model.addAttribute("message", "magic.give.success");
		model.addAttribute("msg", msg);
		model.addAttribute("userIds", userIds);
		model.addAttribute("ids", ids);
		model.addAttribute("nums", nums);
		return "magic/givesucc";
	}

	@RequestMapping("/magic/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Integer siteId = CmsUtils.getSiteId(request);
		List<BbsUserGroup> groups = bbsUserGroupMng.getList(siteId);
		BbsCommonMagic magic = manager.findById(id);
		boolean hasBeUsedGroups=hasBeUsedGroups(magic);
		model.addAttribute("magic", magic);
		model.addAttribute("groupIds", magic.getGroupIds());
		model.addAttribute("beUsedGroupIds", magic.getToUseGroupIds());
		model.addAttribute("groups", groups);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("hasBeUsedGroups", hasBeUsedGroups);
		return "magic/edit";
	}

	@RequestMapping("/magic/o_update.do")
	public String update(BbsCommonMagic bean, Integer[] groupIds,
			Integer[] beUsedGroupIds, HttpServletRequest request, ModelMap model) {
		manager.updateByGroup(bean, groupIds, beUsedGroupIds);
		log.info("update BbsCommonMagic id={}", bean.getId());
		return list(request, model);
	}

	@RequestMapping("/magic/o_priority.do")
	public String priorityUpdate(Integer[] mids, Byte[] prioritys,
			Integer[] magicAvail, HttpServletRequest request, ModelMap model) {
		if (mids == null || mids.length <= 0) {
			return "redirect:v_list.do";
		}
		BbsCommonMagic magic;
		Integer id;
		Byte priority;
		Integer magicAvailable;
		for (int i = 0; i < mids.length; i++) {
			id = mids[i];
			priority = prioritys[i];
			magicAvailable = magicAvail[i];
			if (id != null && priority != null) {
				magic = manager.findById(id);
				if (magic != null) {
					magic.setDisplayorder(priority);
					if (magicAvailable.equals(1)) {
						magic.setAvailable(true);
					} else {
						magic.setAvailable(false);
					}
					manager.update(magic);
				}
			}
		}
		return list(request, model);
	}

	@RequestMapping("/magic/v_config.do")
	public String config_edit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsMagicConfig config = magicConfigMng.findById(site.getId());
		model.addAttribute("config", config);
		return "magic/config";
	}

	@RequestMapping("/magic/o_config.do")
	public String config_update(HttpServletRequest request,
			HttpServletResponse response, BbsMagicConfig bean, ModelMap model) {
		bean = magicConfigMng.update(bean);
		model.addAttribute("config", bean);
		model.addAttribute("message", "global.success");
		return "magic/config";
	}

	private void saveMagicLog(BbsCommonMagic magic, BbsUser user,
			BbsUser targetUser, Integer buynum, Byte Operate) {
		BbsMagicLog log = new BbsMagicLog();
		log.setLogTime(new Date());
		log.setMagic(magic);
		log.setOperator(Operate);
		log.setPrice(magic.getPrice());
		log.setNum(buynum);
		log.setUser(user);
		log.setTargetUser(targetUser);
		magicLogMng.save(log);
	}

	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id, site.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		BbsCommonMagic entity = manager.findById(id);
		if (errors.ifNotExist(entity, BbsCommonMagic.class, id)) {
			return true;
		}
		return false;
	}

	private boolean hasBeUsedGroups(BbsCommonMagic magic) {
		String identifier = magic.getIdentifier();
		if (identifier.equals(MagicConstants.MAGIC_CHECKONLINE)
				|| identifier.equals(MagicConstants.MAGIC_CLOSE)
				|| identifier.equals(MagicConstants.MAGIC_NAMEPOST)
				|| identifier.equals(MagicConstants.MAGIC_OPEN)
				|| identifier.equals(MagicConstants.MAGIC_SHOWIP)
				|| identifier.equals(MagicConstants.MAGIC_SOFA)) {
			return true;
		}
		return false;
	}

	@Autowired
	private BbsCommonMagicMng manager;
	@Autowired
	private BbsMagicConfigMng magicConfigMng;
	@Autowired
	private BbsUserMng userMng;
	@Autowired
	private BbsUserGroupMng bbsUserGroupMng;
	@Autowired
	private BbsMagicLogMng magicLogMng;
}