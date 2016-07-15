package com.jeecms.bbs.action.front;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import static com.jeecms.bbs.Constants.TPLDIR_BBSALY;
import com.jeecms.core.entity.CmsSite;


import freemarker.template.TemplateModelException;

@Controller
public class StockUpAndDownRateAct {
	
	public static final String TPL_BBSALY_INDEX="tpl.bbsaly.index";
	
	@RequestMapping(value="/bbsaly/list.jhtml")	
	public String bbsalyindex(Integer pageNo,HttpServletRequest request,
			ModelMap model) throws TemplateModelException{
				
		CmsSite site =CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		
		return FrontUtils.getTplPath(request, site.getSolutionPath()
				,TPLDIR_BBSALY,TPL_BBSALY_INDEX);
		
	}
}
