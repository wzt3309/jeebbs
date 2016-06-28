package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jeecms.bbs.action.directive.abs.AbstractTopicPageDirective;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.manager.impl.BbsNewsMngImpl;
import com.jeecms.bbs.manager.impl.StockmessageMngImpl;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class BbsNewsDirective extends AbstractTopicPageDirective implements TemplateDirectiveModel{

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		String limit1=DirectiveUtils.getString("limit1", params);
		String newsfrom=DirectiveUtils.getString("newsFrom", params);
		
		Integer l1=Integer.parseInt(limit1);
		
		
		System.out.println("财经数据每个box显示"+l1+"条");
		
		List<BbsNews> newsList1 =bbsTopicMng.getBbsNewsList(newsfrom,l1);		
		
		BbsTopic bbs=bbsTopicMng.findById(4);
		System.out.println("财经头条主页");
		
		//newsList1
		if(newsList1.isEmpty()){
			System.out.println(newsfrom+"List is empty");
			paramWrap.put("news_list1",null);
		}
		else{
			paramWrap.put("news_list", DEFAULT_WRAPPER.wrap(newsList1));
			int size=0;//一个box显示的记录数目
			System.out.println(newsfrom+"List is not empty");
				Iterator<BbsNews> ne=newsList1.iterator();
				while(ne.hasNext()&&size<6){
					BbsNews news=ne.next();
					if(news.getNewsFrom()=="f1"){
						System.out.println(news.getNewsName());
						paramWrap.put("news1", DEFAULT_WRAPPER.wrap(news));
					}
					size++;
				}		
		}		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
	
	protected BbsNewsMngImpl BbsnewsMng=new BbsNewsMngImpl();

}
