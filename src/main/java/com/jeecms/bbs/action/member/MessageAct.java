package com.jeecms.bbs.action.member;

import static com.jeecms.bbs.Constants.TPLDIR_MEMBER;
import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.bbs.entity.BbsMessage;
import com.jeecms.bbs.entity.BbsMessageReply;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsMessageMng;
import com.jeecms.bbs.manager.BbsMessageReplyMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.springmvc.MessageResolver;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.MagicMessage;

@Controller
public class MessageAct {
	public static final String SEND_MSG = "tpl.sendMsg";
	public static final String MY_MSG = "tpl.myMsg";
	public static final String REPLY = "tpl.reply";
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	public static final String MY_REMIND = "tpl.myremind";

	@RequestMapping(value = "/member/sendMsg.jhtml", method = RequestMethod.GET)
	public String message(String username, Integer type,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		model.addAttribute("username", username);
		model.addAttribute("type", type);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, SEND_MSG);
	}

	@RequestMapping(value = "/member/sendMsg.jhtml", method = RequestMethod.POST)
	public String messageSubmit(HttpServletRequest request,HttpServletResponse response, String u,
			BbsMessage msg, ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		if (validateSendMsg(request,user, u, model)) {
			bbsMessageMng.sendMsg(user, bbsUserMng.findByUsername(u), msg);
		}
		response.sendRedirect("myMsg.jhtml");
		return null;
	}
	
	@RequestMapping(value = "/member/sendMsgJson.jhtml", method = RequestMethod.POST)
	public void messageJsonSubmit(HttpServletRequest request,HttpServletResponse response, String username,
			String content,Integer msgType, ModelMap model) throws JSONException {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		JSONObject object = new JSONObject();
		BbsUser receiver=bbsUserMng.findByUsername(username);
		MagicMessage messageResource = MagicMessage.create(request);
		if (user == null) {
			object.put("message", messageResource
					.getMessage("friend.apply.nologin"));
		}
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		if (validateSendMsg(request,user, username, model)) {
			BbsMessage msg=new BbsMessage();
			msg.setContent(content);
			msg.setCreateTime(new Date());
			msg.setReceiver(receiver);
			msg.setSender(user);
			msg.setUser(user);
			msg.setMsgType(msgType);
			msg.init();
			bbsMessageMng.sendMsg(user, receiver, msg);
			object.put("message", messageResource
					.getMessage("greet.success"));
		}else{
			object.put("message", messageResource
					.getMessage("greet.fail"));
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	//我的信息
	@RequestMapping(value = "/member/myMsg*.jhtml")
	public String myMsg(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MY_MSG);
	}

	//我的留言
	@RequestMapping(value = "/member/myguestbook*.jhtml")
	public String myguestbook(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		model.put("typeId", 2);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MY_MSG);
	}
	//我的提醒
	@RequestMapping(value = "/member/myremind*.jhtml")
	public String myremind(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		model.put("typeId", 3);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MY_REMIND);
	}

	@RequestMapping(value = "/member/reply*.jhtml")
	public String reply(HttpServletRequest request, Integer mid, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		if (validateReply(user, mid, model)) {
			model.put("msg", bbsMessageMng.findById(mid));
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, REPLY);
		}
		return "redirect:/member/myMsg.jhtml";
	}

	@RequestMapping(value = "/member/ajax_delete_msg.jhtml")
	public void deleteMessage(HttpServletRequest request,
			HttpServletResponse response, Integer mid, ModelMap model)
			throws JSONException {
		JSONObject json = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		if (validateDeleteMessage(user, mid)) {
			bbsMessageMng.deleteById(mid);
			json.put("success", true);
		} else {
			json.put("success", false);
		}
		ResponseUtils.renderJson(response, json.toString());
	}

	@RequestMapping(value = "/member/ajax_delete_reply.jhtml")
	public void deleteReply(HttpServletRequest request,
			HttpServletResponse response, Integer rid, ModelMap model)
			throws JSONException {
		JSONObject json = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		if (validateDeleteReply(user, rid)) {
			bbsMessageReplyMng.deleteById(rid);
			json.put("success", true);
		} else {
			json.put("success", false);
		}
		ResponseUtils.renderJson(response, json.toString());
	}

	private boolean validateDeleteMessage(BbsUser user, Integer rid) {
		if (user == null) {
			return false;
		}
		BbsMessage bean = bbsMessageMng.findById(rid);
		if (bean == null) {
			return false;
		}
		if (!bean.getUser().equals(user)) {
			return false;
		}
		return true;
	}

	private boolean validateDeleteReply(BbsUser user, Integer rid) {
		if (user == null) {
			return false;
		}
		BbsMessageReply bean = bbsMessageReplyMng.findById(rid);
		if (bean == null) {
			return false;
		}
		if (!bean.getMessage().getUser().equals(user)) {
			return false;
		}
		return true;
	}

	private boolean validateReply(BbsUser user, Integer mid, ModelMap model) {
		BbsMessage bean = bbsMessageMng.findById(mid);
		if (bean == null) {
			return false;
		}
		if (!bean.getUser().equals(user)) {
			return false;
		}
		return true;
	}

	private boolean validateSendMsg(HttpServletRequest request,BbsUser user, String receiver,
			ModelMap model) {
		// 用户名为空
		if (StringUtils.isBlank(receiver)) {
			model.addAttribute("message", MessageResolver.getMessage(request, "friend.send.noname"));
			return false;
		}
		// 不允许发送消息给自己
		if (receiver.equals(user.getUsername())) {
			model.addAttribute("message", MessageResolver.getMessage(request, "friend.sendmyself"));
			return false;
		}
		// 用户名不存在
		if (bbsUserMng.findByUsername(receiver) == null) {
			model.addAttribute("message", MessageResolver.getMessage(request, "friend.send.noexistname"));
			return false;
		}
		return true;
	}

	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private BbsMessageMng bbsMessageMng;
	@Autowired
	private BbsMessageReplyMng bbsMessageReplyMng;
}
