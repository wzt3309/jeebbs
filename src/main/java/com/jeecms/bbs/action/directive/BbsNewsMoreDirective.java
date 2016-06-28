package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jeecms.bbs.action.directive.abs.AbstractTopicPageDirective;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.manager.impl.BbsNewsMngImpl;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class BbsNewsMoreDirective extends AbstractTopicPageDirective implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		
		//获取model的传值
		String from= DirectiveUtils.getString("From", params);
		System.out.println("inDirective"+from);
//		int bn=DirectiveUtils.getInt("bn", params);
//		int size=DirectiveUtils.getInt("size", params);
//		
//		paramWrap.put("bn", DEFAULT_WRAPPER.wrap(bn));
//		paramWrap.put("size", DEFAULT_WRAPPER.wrap(size));

		List<BbsNews> newsList =bbsTopicMng.getBbsNewsList("",-1);
		BbsTopic bbs=bbsTopicMng.findById(4);
		System.out.println(bbs.getTitle());
		if(newsList.isEmpty()){
			System.out.println("getList is empty");			
		}
		else{
			System.out.println("getList is not empty");
			Iterator<BbsNews> ne=newsList.iterator();
			List<BbsNews> outNewList =new ArrayList<BbsNews>();			
			while(ne.hasNext()){
				BbsNews news=ne.next();
				if(news.getNewsFrom().equals(from)){
					outNewList.add(news);
					System.out.println(news.getNewsFrom()+news.getNewsName());
				}				
			}
			if(outNewList.isEmpty()){
				System.out.println("outList is empty");
				paramWrap.put("news_list",null);
			}
			else{
				int listSize=outNewList.size();
				System.out.println("listSize"+listSize);
				int pageSize=listSize/15;
				if(listSize%15!=0||pageSize==0){
					pageSize+=1;
				}
				System.out.println(pageSize);
				paramWrap.put("news_list", DEFAULT_WRAPPER.wrap(outNewList));
				paramWrap.put("list_size", DEFAULT_WRAPPER.wrap(listSize));
				paramWrap.put("page_size", DEFAULT_WRAPPER.wrap(pageSize));
			}	
				
				
				
			
		}
		
		
		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
	
	protected BbsNewsMngImpl BbsnewsMng=new BbsNewsMngImpl();

}