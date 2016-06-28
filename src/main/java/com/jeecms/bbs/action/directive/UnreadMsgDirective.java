package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsFriendShipMng;
import com.jeecms.bbs.manager.BbsMessageMng;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class UnreadMsgDirective implements TemplateDirectiveModel {

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		BbsUser user = FrontUtils.getUser(env);
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put("applyFriend", DEFAULT_WRAPPER
				.wrap(hasUnProcessApplyFriend(user)));
		paramWrap.put("unReadMsg", DEFAULT_WRAPPER.wrap(hasUnReadMsg(user)));
		paramWrap.put("unReadGuestbook", DEFAULT_WRAPPER
				.wrap(hasUnReadGuestbook(user)));
		paramWrap
				.put("unReadGreet", DEFAULT_WRAPPER.wrap(hasUnReadGreet(user)));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	private boolean hasUnProcessApplyFriend(BbsUser user) {
		if (friendShipMng.getApplyByUserId(user.getId(), 1, 5).getTotalCount() > 0) {
			return true;
		}
		return false;
	}

	private boolean hasUnReadMsg(BbsUser user) {
		if (messageMng.hasUnReadMessage(user.getId(), 1)) {
			return true;
		}
		return false;
	}

	private boolean hasUnReadGuestbook(BbsUser user) {
		if (messageMng.hasUnReadMessage(user.getId(), 2)) {
			return true;
		}
		return false;
	}

	private boolean hasUnReadGreet(BbsUser user) {
		if (messageMng.hasUnReadMessage(user.getId(), 3)) {
			return true;
		}
		return false;
	}

	@Autowired
	private BbsFriendShipMng friendShipMng;
	@Autowired
	private BbsMessageMng messageMng;
}
