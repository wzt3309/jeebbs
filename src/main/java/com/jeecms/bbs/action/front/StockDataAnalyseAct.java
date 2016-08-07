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
public class StockDataAnalyseAct {
	
	@RequestMapping(value = "/stockmessage/analyse.jhtml")	
	public String searchStock(Integer pageNo, HttpServletRequest request,
			ModelMap model) throws TemplateModelException {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String gpdm = RequestUtils.getQueryParam(request, "gpdm");
		//System.out.println(gpdm);
		String forumIdStr = RequestUtils.getQueryParam(request, "forumId");
		String type = RequestUtils.getQueryParam(request, "TYPE");
		Integer nowPage = RequestUtils.getIntParam(request, "nowPage");
		nowPage=(nowPage==0?1:nowPage);
		
		System.out.println("type in act:"+type);
		Integer forumId = 0;
		if (forumIdStr != null && StringUtils.isNotBlank(forumIdStr)) {
			forumId = Integer.parseInt(forumIdStr);
		}
		model.put("TYPE", type);
		model.put("forumId", forumId);
		model.put("RECCOMENDATION", 1);
		model.put("nowPage", nowPage);
		 
		
		
		
		FrontUtils.frontPageData(request, model);
		return "/WEB-INF/t/cms/www/blue/选股/数据分析.html";
		
	}

}
