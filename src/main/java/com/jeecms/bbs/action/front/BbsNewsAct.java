package com.jeecms.bbs.action.front;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.dao.impl.BbsNewsDaoImpl;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.manager.BbsNewsMng;
import com.jeecms.bbs.manager.impl.BbsNewsMngImpl;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsSite;

import freemarker.template.TemplateModelException;

@Controller
public class BbsNewsAct {
	
	@RequestMapping(value="/BbsNews/read.jhtml")	
	 
	public String readNews(Integer pageNo,HttpServletRequest request,
			ModelMap model) throws TemplateModelException{
				
		CmsSite site =CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String limit1=RequestUtils.getQueryParam(request, "limit1");
		String newsFrom=RequestUtils.getQueryParam(request, "newsFrom");		
		model.put("limit1", limit1);
//		model.put("newsFrom", newsFrom);
		FrontUtils.frontPageData(request, model);
		
		
	
		
		return "/WEB-INF/t/cms/www/blue/新闻页面/财经新闻首页.html";
		
	}
}
