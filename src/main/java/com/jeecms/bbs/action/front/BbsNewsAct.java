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
	private static final String TPL_BBSNEWS_MORE="tpl_bbsnews_more";
	
	@RequestMapping(value="/bbsnews/index.jhtml")	 
	public String bbsnewsIndex(HttpServletRequest request,
			ModelMap model) throws TemplateModelException{				
		CmsSite site =CmsUtils.getSite(request);
		//修改资源路线
//		FrontUtils.frontDataNew(request, model, site);
		FrontUtils.frontData(request, model, site);
		
		Integer pageNo=RequestUtils.getIntParam(request, "pageNo");
		pageNo=(pageNo==0?1:pageNo);
		Integer pageSize=RequestUtils.getIntParam(request, "pageSize");	
		String newsFrom=RequestUtils.getQueryParam(request, "newsFrom");
		model.put("pageNo", pageNo);	
		model.put("pageSize", pageSize);
		model.put("newsFrom", newsFrom);
		
		return FrontUtils.getTplPath(request, site.getSolutionPath()
				, TPLDIR_BBSNEWS, TPL_BBSNEWS_INDEX);
		
	}
	@RequestMapping(value="/bbsnews/more.jhtml")
	public String bbsnewsmore(HttpServletRequest request,
			ModelMap model)throws TemplateModelException{
		CmsSite site =CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		Integer pageNo=RequestUtils.getIntParam(request, "pageNo");
		pageNo=(pageNo==0?1:pageNo);
		Integer pageSize=RequestUtils.getIntParam(request, "pageSize");	
		String newsFrom=RequestUtils.getQueryParam(request, "newsFrom");
		String newsFromName="";
		if(newsFrom.equals("f1")){
			newsFromName="新浪财经";
		}else if(newsFrom.equals("f2")){
			newsFromName="搜狐财经";
		}else if(newsFrom.equals("f3")){
			newsFromName="东方财经";
		}else if(newsFrom.equals("f4")){
			newsFromName="雪球财经";
		}
		model.put("pageNo", pageNo);	
		model.put("pageSize", pageSize);
		model.put("newsFrom", newsFrom);
		model.put("hrefFormer", "bbsnews/more.jhtml");
		model.put("newsFromName", newsFromName);
	
		return FrontUtils.getTplPath(request, site.getSolutionPath()
				, TPLDIR_BBSNEWS, TPL_BBSNEWS_MORE);
	}
}
