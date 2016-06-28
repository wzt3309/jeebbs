package com.jeecms.bbs.action.front;

import static com.jeecms.bbs.Constants.TPLDIR_SPECIAL;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsSite;

@Controller
public class BbsTopicSearchAct {
	public static final String SEARCH_RESULT = "tpl.search";

	@RequestMapping(value = "/topic/search*.jhtml")
	public String searchSubmit(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String keywords = RequestUtils.getQueryParam(request, "keywords");
		String forumIdStr = RequestUtils.getQueryParam(request, "forumId");
		Integer forumId = 0;
		if (forumIdStr != null && StringUtils.isNotBlank(forumIdStr)) {
			forumId = Integer.parseInt(forumIdStr);
		}
		model.put("keywords", keywords);
		model.put("forumId", forumId);
		FrontUtils.frontPageData(request, model);
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_SPECIAL, SEARCH_RESULT);
	}

}
