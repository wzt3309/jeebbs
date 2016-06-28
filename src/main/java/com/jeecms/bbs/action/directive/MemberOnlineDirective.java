package com.jeecms.bbs.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_LIST;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jeecms.bbs.web.OnlineCountListener;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class MemberOnlineDirective implements TemplateDirectiveModel {

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		List members = OnlineCountListener.getSessions();
		int userSum=OnlineCountListener.getSum();
		paramWrap.put("onlineMemSize", DEFAULT_WRAPPER.wrap(members.size()));
		paramWrap.put("userSum", DEFAULT_WRAPPER.wrap(userSum));
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(members));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
}
