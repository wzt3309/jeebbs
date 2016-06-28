package com.jeecms.bbs.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_LIST;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsPostType;
import com.jeecms.bbs.manager.BbsPostTypeMng;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.freemarker.DirectiveUtils;
import com.jeecms.core.entity.CmsSite;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class BbsPostTypeDirective implements TemplateDirectiveModel {

	public static final String FORUM_ID = "forumId";
	public static final String CHILD_LIST = "child_list";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		Integer forumId = DirectiveUtils.getInt(FORUM_ID, params);
		List<BbsPostType> postTypes = manager.getList(site.getId(), forumId,
				null);
		Map<String, List<BbsPostType>> childPostTypes = new HashMap<String, List<BbsPostType>>();
		List<BbsPostType> tempList;
		for (BbsPostType parent : postTypes) {
			tempList = manager.getList(site.getId(), forumId, parent.getId());
			childPostTypes.put(parent.getId().toString(), tempList);
		}

		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(postTypes));
		paramWrap.put(CHILD_LIST, DEFAULT_WRAPPER.wrap(childPostTypes));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	@Autowired
	private BbsPostTypeMng manager;
}
