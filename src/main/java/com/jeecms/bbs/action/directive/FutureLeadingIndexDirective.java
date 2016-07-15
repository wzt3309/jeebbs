package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.action.directive.abs.AbstractTopicPageDirective;
import com.jeecms.bbs.entity.FutureLeadingIndex;
import com.jeecms.bbs.manager.FutureLeadingIndexMng;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * @ClassName FutureLeadingIndexDirective
 * @Description 期指领先指数freemaker模板
 * @author wzt3309
 * @date 2015-11-4
 */
public class FutureLeadingIndexDirective extends AbstractTopicPageDirective
		implements TemplateDirectiveModel {
	
	@Autowired
	private FutureLeadingIndexMng mng;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		String dayStr=DirectiveUtils.getString("day", params);		
		int dayInt=Integer.parseInt(dayStr);
		List<FutureLeadingIndex> FutureLeadingIndexList=mng.getList(dayInt);
		if(FutureLeadingIndexList.isEmpty()){
			System.out.println("FutureLeadingIndex get list is empty!");
			paramWrap.put("FutureLeadingIndexList", null);
		}else{
			System.out.println("FutureLeadingIndex get list is not empty!");
			paramWrap.put("FutureLeadingIndexList", DEFAULT_WRAPPER.wrap(FutureLeadingIndexList));
		}
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		
	}

}
