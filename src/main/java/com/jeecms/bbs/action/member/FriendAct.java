package com.jeecms.bbs.action.member;

import static com.jeecms.bbs.Constants.TPLDIR_MEMBER;
import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;
import static com.jeecms.bbs.entity.BbsFriendShip.REFUSE;
import static com.jeecms.bbs.entity.BbsFriendShip.ACCEPT;
import static com.jeecms.bbs.entity.BbsFriendShip.APPLYING;

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

import com.jeecms.bbs.entity.BbsFriendShip;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsFriendShipMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.MagicMessage;


@Controller
public class FriendAct {
	public static final String FRIEND_SEARCH = "tpl.friendSearch";
	public static final String FRIEND_SEARCH_RESULT = "tpl.friendSearchResult";
	public static final String SUGGEST = "tpl.suggest";
	public static final String FRIEND_APPLY_RESULT = "tpl.friendApplyResult";
	public static final String MYFRIEND = "tpl.myfriend";
	public static final String FRIEND_APPLY = "tpl.friendApply";
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	public static final String TPL_GET_MSG_SEND = "tpl.msgsendpage";

	@RequestMapping(value = "/member/friendSearch*.jhtml", method = RequestMethod.GET)
	public String search(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, FRIEND_SEARCH);
	}

	@RequestMapping("/member/suggest.jhtml")
	public String suggest(HttpServletRequest request,
			HttpServletResponse response, String username, Integer count,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<BbsUser> list = bbsUserMng.getSuggestMember(username, count);
		model.addAttribute("suggests", list);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, SUGGEST);
	}

	@RequestMapping(value = "/member/apply.jhtml")
	public String apply(HttpServletRequest request,
			HttpServletResponse response, String u, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		BbsUser friend = bbsUserMng.findByUsername(u);
		if (validateApply(user, friend)) {
			bbsFriendShipMng.apply(user, friend);
			model.addAttribute("message", "friend.applyed");
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, FRIEND_APPLY_RESULT);
		}
		return null;
	}

	@RequestMapping(value = "/member/applyJson.jhtml")
	public void applyJson(HttpServletRequest request,
			HttpServletResponse response, String u, ModelMap model)
			throws JSONException {
		BbsUser user = CmsUtils.getUser(request);
		BbsUser friend = bbsUserMng.findByUsername(u);
		JSONObject object = new JSONObject();
		MagicMessage magicMessage = MagicMessage.create(request);
		if (user == null) {
			object.put("message", magicMessage
					.getMessage("friend.apply.nologin"));
		} else if (validateApply(user, friend)) {
			bbsFriendShipMng.apply(user, friend);
			object.put("message", magicMessage.getMessage("friend.applyed"));
		} else {
			int status = validateApplyJson(user, friend);
			if (status == 0) {
				object.put("message", magicMessage
						.getMessage("friend.apply.nologin"));
			} else if (status == 1) {
				object.put("message", magicMessage
						.getMessage("friend.apply.noexistuser"));
			} else if (status == 2) {
				object.put("message", magicMessage
						.getMessage("friend.apply.yourself"));
			} else if (status == 3) {
				object.put("message", magicMessage
						.getMessage("friend.apply.success"));
			} else if (status == 4) {
				object.put("message", magicMessage
						.getMessage("friend.apply.applying"));
			}
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequestMapping("/member/getsendmsgpage.jhtml")
	public String getmagicpage(String username, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("username",username);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, TPL_GET_MSG_SEND);
	}

	@RequestMapping(value = "/member/accept.jhtml")
	public String accept(HttpServletRequest request,
			HttpServletResponse response, Integer id, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		BbsFriendShip friendShip = bbsFriendShipMng.findById(id);
		if (validateAccept(user, friendShip)) {
			bbsFriendShipMng.accept(friendShip);
			model.addAttribute("message", "friend.accepted");
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, FRIEND_APPLY_RESULT);
		}
		return null;
	}

	@RequestMapping(value = "/member/refuse.jhtml")
	public String refuse(HttpServletRequest request,
			HttpServletResponse response, Integer id, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		BbsFriendShip friendShip = bbsFriendShipMng.findById(id);
		if (validateRefuse(user, friendShip)) {
			bbsFriendShipMng.refuse(friendShip);
			model.addAttribute("message", "friend.refused");
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, FRIEND_APPLY_RESULT);
		}
		return null;
	}

	@RequestMapping("/member/myfriend*.jhtml")
	public String myfriend(Integer pageNo, HttpServletRequest request,
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
				TPLDIR_MEMBER, MYFRIEND);
	}

	@RequestMapping("/member/friendApply.jhtml")
	public String friendApply(Integer pageNo, HttpServletRequest request,
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
				TPLDIR_MEMBER, FRIEND_APPLY);
	}

	private boolean validateApply(BbsUser user, BbsUser friend) {
		// 用户未登录
		if (user == null) {
			return false;
		}
		// 好友不存在
		if (friend == null) {
			return false;
		}
		// 不允许加自己为好友
		if (user.equals(friend)) {
			return false;
		}
		// 申请被拒绝则可以重新申请好友
		BbsFriendShip bean = bbsFriendShipMng.getFriendShip(user.getId(),
				friend.getId());
		if (bean != null && bean.getStatus() != REFUSE) {
			return false;
		}
		return true;
	}

	private int validateApplyJson(BbsUser user, BbsUser friend) {
		int returnValue = -1;
		// 用户未登录
		if (user == null) {
			returnValue = 0;
		}
		// 好友不存在
		if (friend == null) {
			returnValue = 1;
		}
		// 不允许加自己为好友
		if (user.equals(friend)) {
			returnValue = 2;
		}
		BbsFriendShip bean = bbsFriendShipMng.getFriendShip(user.getId(),
				friend.getId());
		if (bean != null && bean.getStatus() == ACCEPT) {
			returnValue = 3;
		}
		if (bean != null && bean.getStatus() == APPLYING) {
			returnValue = 4;
		}
		return returnValue;
	}

	private boolean validateAccept(BbsUser user, BbsFriendShip friendShip) {
		// 用户未登录
		if (user == null) {
			return false;
		}
		// 好友申请不存在
		if (friendShip == null) {
			return false;
		}
		// 只处理申请中的好友关系
		if (friendShip.getStatus() != APPLYING) {
			return false;
		}
		// 只处理自己的好友关系
		if (!user.equals(friendShip.getFriend())) {
			return false;
		}
		return true;
	}

	private boolean validateRefuse(BbsUser user, BbsFriendShip friendShip) {
		// 用户未登录
		if (user == null) {
			return false;
		}
		// 好友申请不存在
		if (friendShip == null) {
			return false;
		}
		// 只处理申请中的好友关系
		if (friendShip.getStatus() != APPLYING) {
			return false;
		}
		// 只处理自己的好友关系
		if (!user.equals(friendShip.getFriend())) {
			return false;
		}
		return true;
	}

	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private BbsFriendShipMng bbsFriendShipMng;
}
