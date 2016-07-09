package com.jeecms.bbs.action.front;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsSite;
import static com.jeecms.bbs.Constants.TPLDIR_BBSNEWS;
import static com.jeecms.bbs.Constants.TEMP_SOLUTION_TPL_PATH;
import freemarker.template.TemplateModelException;

@Controller
public class BbsNewsAct {
	private static final Logger log = LoggerFactory
			.getLogger(BbsNewsAct.class);
	private static final String TPL_BBSNEWS_INDEX="tpl_bbsnews_index";
	
	@RequestMapping(value="/bbsnews/index.jhtml")	 
	public String bbsnewsIndex(HttpServletRequest request,
			ModelMap model) throws TemplateModelException{				
		CmsSite site =CmsUtils.getSite(request);		
		FrontUtils.frontDataNew(request, model, site);
		
		Integer pageNo=RequestUtils.getIntParam(request, "pageNo");
		pageNo=(pageNo==0?1:pageNo);
		Integer pageSize=RequestUtils.getIntParam(request, "pageSize");	
		String newsFrom=RequestUtils.getQueryParam(request, "newsFrom");
		model.put("pageNo", pageNo);	
		model.put("pageSize", pageSize);
		model.put("newsFrom", newsFrom);
		
		return FrontUtils.getTplPath(request, TEMP_SOLUTION_TPL_PATH
				, TPLDIR_BBSNEWS, TPL_BBSNEWS_INDEX);
		
	}
}
