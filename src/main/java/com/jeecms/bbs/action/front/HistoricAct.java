package com.jeecms.bbs.action.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.web.CategoryUtil;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsSite;

import freemarker.template.TemplateModelException;

@Controller
public class HistoricAct {
	
	@RequestMapping(value = "/stockmessage/historic.jhtml")	
	public String Historic(Integer pageNo, HttpServletRequest request,
			ModelMap model) throws TemplateModelException {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String forumIdStr = RequestUtils.getQueryParam(request, "forumId");
		Integer forumId = 0;
		if (forumIdStr != null && StringUtils.isNotBlank(forumIdStr)) {
			forumId = Integer.parseInt(forumIdStr);
		}
		if(RequestUtils.getQueryParam(request, "gpdm")!=null){
			
			model.put("GPDM", RequestUtils.getQueryParam(request, "gpdm"));
		}
		else{
			model.put("GPDM", "000001");
		}
		
		model.put("forumId", forumId);
		System.out.println(RequestUtils.getQueryParam(request, "category"));
		int num=CategoryUtil.wichItemToShow(request, model);
		
	    //System.out.println(RequestUtils.getQueryParam(request, "gpdm"));
	    //System.out.println(RequestUtils.getQueryParam(request, "MGWFP"));
	    
	    
		
		model.put("NUM",num);
		 
		
		
		
		FrontUtils.frontPageData(request, model);
		return "/WEB-INF/t/cms/www/blue/选股/tool_page/HistoricTool.html";
		
	}

}
