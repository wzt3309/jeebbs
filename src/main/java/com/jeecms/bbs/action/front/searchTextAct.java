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
public class searchTextAct {
	
	@RequestMapping(value = "/stockmessage/searchText.jhtml")	
	public String searchText(Integer pageNo, HttpServletRequest request,
			ModelMap model) throws TemplateModelException {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String forumIdStr = RequestUtils.getQueryParam(request, "forumId");
		Integer forumId = 0;
		if (forumIdStr != null && StringUtils.isNotBlank(forumIdStr)) {
			forumId = Integer.parseInt(forumIdStr);
		}
		model.put("forumId", forumId);
		
		 
		
		
		
		FrontUtils.frontPageData(request, model);
		return "/WEB-INF/t/cms/www/blue/选股/tool_page/searchTextTool.html";

        }

}