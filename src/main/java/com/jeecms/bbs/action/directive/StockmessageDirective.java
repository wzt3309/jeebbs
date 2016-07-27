package com.jeecms.bbs.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_PAGINATION;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.action.directive.abs.AbstractTopicPageDirective;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.entity.Stockmessage;
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

public class StockmessageDirective extends AbstractTopicPageDirective implements TemplateDirectiveModel{
	
	
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		
		
		@SuppressWarnings("unchecked")
		String gpdm= DirectiveUtils.getString("GPDM", params);
		if(gpdm!=null&&!"".equals(gpdm)){
			Integer nowPage=DirectiveUtils.getInt("nowPage", params);
			Integer pageSize=10;
			String sql=" select bean from Stockbasicmessage " +
					"bean where 1=1 and bean.GPDM="+gpdm;
			
			//System.out.println("inDirective:"+gpdm);
			
			List<Stockbasicmessage> stocklist =null;
			Pagination pagination =stockmessageMng.getmess(sql,nowPage,pageSize);
			paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(pagination));
			if(pagination!=null){
				stocklist=(List<Stockbasicmessage>) pagination.getList();
			}	
			if(stocklist.isEmpty()){			
				paramWrap.put("stock_list",null);		
			}
			else{
				paramWrap.put("stock_list", DEFAULT_WRAPPER.wrap(stocklist));
				
				//System.out.println("List is not empty");
					Iterator<Stockbasicmessage> sto=stocklist.iterator();
					Stockbasicmessage stock=sto.next();
					paramWrap.put("stock", DEFAULT_WRAPPER.wrap(stock));
					
				
			}	
		}
		
		
		
				
				
		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		
		
        
	}
	
	@Autowired
	protected StockmessageMng stockmessageMng;

}
