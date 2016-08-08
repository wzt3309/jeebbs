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
import static com.jeecms.bbs.Constants.TPLDIR_STOCK;
import freemarker.template.TemplateModelException;


@Controller
public class StockCollectionAct {

	private final String TPL_STOCKMESSAGE="tpl.stockmessage";
	
	@RequestMapping(value = "/stockmessage/collection.jhtml")	
	public String StockCollection(Integer pageNo, HttpServletRequest request,
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
		//"/WEB-INF/t/cms/www/blue/选股/test.html"
		return FrontUtils.getTplPath(request, site.getSolutionPath()
				, TPLDIR_STOCK,TPL_STOCKMESSAGE);
		
	}
	@RequestMapping(value="/stockmessage/creater.jhtml")
	public String searchStock(Integer pageNo, HttpServletRequest request,
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
		return "/WEB-INF/t/cms/www/blue/选股/tool_page/SeledTableTool.html";
	}
	@RequestMapping(value="/stockmessage/example.jhtml")
	public String example(HttpServletRequest request,
			ModelMap model) throws TemplateModelException {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);		
		String forumIdStr = RequestUtils.getQueryParam(request, "forumId");
		Integer nowPage = RequestUtils.getIntParam(request, "nowPage");
		nowPage=(nowPage==0?1:nowPage);
		Integer forumId = 0;
		if (forumIdStr != null && StringUtils.isNotBlank(forumIdStr)) {
			forumId = Integer.parseInt(forumIdStr);
		}
		String sql=RequestUtils.getQueryParam(request, "sql");
		if(sql!=null&&!"".equals(sql)){
			
			sql=sql.replaceAll(";", " ");
			model.put("sql",sql);
			System.out.println("sql:"+sql);
		}		
		String reccomendation=RequestUtils.getQueryParam(request, "reccomendation");
		System.out.println("rec:"+reccomendation);
		if(reccomendation!=null){
			model.put("Reccomendation",reccomendation);
			if(reccomendation.equals("1")){
				model.put("datatype", "股票推荐：");
				model.put("datatypename", "最新看涨");
			}else{
				model.put("datatype", "股票信息：");
				model.put("datatypename", "最新数据");
			}
		}
		else{
			model.put("Reccomendation",2);
			model.put("datatype", "股票信息：");
			model.put("datatypename", "最新数据");
		}
		model.put("forumId", forumId);
		model.put("nowPage", nowPage);
		FrontUtils.frontPageData(request, model);
		return "/WEB-INF/t/cms/www/blue/选股/tool_page/exampleTool.html";
	}
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
	@RequestMapping(value = "/stockmessage/historic.jhtml")	
	public String Historic(HttpServletRequest request,
			ModelMap model) throws TemplateModelException {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		Integer nowPage = RequestUtils.getIntParam(request, "nowPage");
		nowPage=(nowPage==0?1:nowPage);
		String forumIdStr = RequestUtils.getQueryParam(request, "forumId");
		Integer forumId = 0;
		if (forumIdStr != null && StringUtils.isNotBlank(forumIdStr)) {
			forumId = Integer.parseInt(forumIdStr);
		}
		if(RequestUtils.getQueryParam(request, "gpdm")!=null){
			
			model.put("GPDM", RequestUtils.getQueryParam(request, "gpdm"));
		}
		else{
			model.put("GPDM", null);
		}
		
		model.put("forumId", forumId);
		System.out.println(RequestUtils.getQueryParam(request, "category"));
		int num=CategoryUtil.wichItemToShow(request, model);
		
	    //System.out.println(RequestUtils.getQueryParam(request, "gpdm"));
	    //System.out.println(RequestUtils.getQueryParam(request, "MGWFP"));
	    
	    
		
		model.put("NUM",num);
		model.put("nowPage", nowPage);
		
		
		
		FrontUtils.frontPageData(request, model);
		return "/WEB-INF/t/cms/www/blue/选股/tool_page/HistoricTool.html";
		
	}
}
