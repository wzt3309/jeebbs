package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.action.directive.abs.AbstractTopicPageDirective;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.bbs.manager.StockmessageMng;
import com.jeecms.bbs.manager.impl.StockmessageMngImpl;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class StockIdDirective extends AbstractTopicPageDirective implements TemplateDirectiveModel{

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		
		@SuppressWarnings("unchecked")
		
		
		String sql= DirectiveUtils.getString("sql", params);
		//System.out.println("before:"+sql);
		if(sql!=null){
			String sqlrepalced=DirectiveUtils.replace(sql);
			//System.out.println("after:"+sqlrepalced);
			
			List<Stockbasicmessage> list=bbsTopicMng.getlist(sqlrepalced);
			if(list.isEmpty()){
						//System.out.println("list is null");
					}
					else{
						Iterator<Stockbasicmessage> stol=list.iterator();
						//System.out.println(stol.next().getGPMC());
						paramWrap.put("list", DEFAULT_WRAPPER.wrap(list));
					}
		}
		
		
		Map<String, TemplateModel> origMap = DirectiveUtils
		.addParamsToVariable(env, paramWrap);
body.render(env.getOut());
DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		
		
	}

}
