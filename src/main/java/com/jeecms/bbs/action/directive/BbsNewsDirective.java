package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;
import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_PAGINATION;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.manager.BbsNewsMng;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;
import com.jeecms.core.entity.CmsSite;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class BbsNewsDirective  implements TemplateDirectiveModel{

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {	
		Integer pageNo=DirectiveUtils.getInt("pageNo", params);
		Integer pageSize=DirectiveUtils.getInt("pageSize", params);		
		Pagination pagination =mng.getPage(params,null,pageNo, pageSize);
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		System.out.println("财经数据当前为"+pageNo+"页");
		System.out.println("财经数据当前显示"+pageSize+"条");	
		paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(pagination));		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
	@Autowired
	private BbsNewsMng mng;

}
