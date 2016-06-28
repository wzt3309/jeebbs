package com.jeecms.bbs.action.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsSite;

import freemarker.template.TemplateModelException;

@Controller
public class exampleAct {

	@RequestMapping(value="/stockmessage/example.jhtml")
	public String example(Integer pageNo, HttpServletRequest request,
			ModelMap model) throws TemplateModelException {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String forumIdStr = RequestUtils.getQueryParam(request, "forumId");
		Integer forumId = 0;
		if (forumIdStr != null && StringUtils.isNotBlank(forumIdStr)) {
			forumId = Integer.parseInt(forumIdStr);
		}
		String sql=RequestUtils.getQueryParam(request, "sql");
		if(sql!=null){
			
			sql=sql.replaceAll(";", " ");
			model.put("sql",sql);
			System.out.println("sql:"+sql);
		}
		else{
			model.put("sql","1=1");
		}
		String reccomendation=RequestUtils.getQueryParam(request, "reccomendation");
		System.out.println("rec:"+reccomendation);
		if(reccomendation!=null){
		model.put("Reccomendation",reccomendation);
		if(reccomendation.equals("1")){
			model.put("datatype", "股票推荐：");
			model.put("datatypename", "今日看涨");
		}else{
			model.put("datatype", "股票信息：");
			model.put("datatypename", "今日数据");
		}
		}
		else{
			model.put("Reccomendation",2);
			model.put("datatype", "股票信息：");
			model.put("datatypename", "今日数据");
		}
		model.put("forumId", forumId);
		FrontUtils.frontPageData(request, model);
		return "/WEB-INF/t/cms/www/blue/选股/tool_page/exampleTool.html";
	}
	
}
