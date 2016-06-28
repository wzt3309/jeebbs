package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.action.directive.abs.AbstractTopicPageDirective;
import com.jeecms.bbs.manager.BbsUserOnlineMng;

import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.jeecms.bbs.entity.BbsUserOnline;

public class UseronlineDrective  implements TemplateDirectiveModel{

	
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		
		@SuppressWarnings("unchecked")
		String id= DirectiveUtils.getString("id", params);
		BbsUserOnline useronline=bbsUserOnlineMng.findById(Integer.valueOf(id));
		System.out.println(useronline.getOnlineDay());
		
		paramWrap.put("useronline", DEFAULT_WRAPPER.wrap(useronline));
		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		
	}

	@Autowired
	private BbsUserOnlineMng bbsUserOnlineMng;
	
}
