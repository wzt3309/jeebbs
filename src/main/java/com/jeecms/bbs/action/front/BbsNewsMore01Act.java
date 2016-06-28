package com.jeecms.bbs.action.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsSite;

import freemarker.template.TemplateModelException;

@Controller
public class BbsNewsMore01Act {
	@RequestMapping(value="/BbsNews/read/more01.jhtml")	
	public String readNews(Integer pageNo,HttpServletRequest request,
			ModelMap model) throws TemplateModelException{
		CmsSite site =CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String before=RequestUtils.getQueryParam(request, "before");
		String after=RequestUtils.getQueryParam(request, "after");
		String from=RequestUtils.getQueryParam(request, "From");
		System.out.println("inAct"+from);
		String bnStr=RequestUtils.getQueryParam(request, "page");
		String listSize=RequestUtils.getQueryParam(request,"listSize");
		String pagenum=RequestUtils.getQueryParam(request,"pagenum");
		model.put("From", from);
		
		
		int bn=Integer.parseInt(bnStr);	//开始的list的index
		int size=15;	//每页显示的记录条数
		int listS=Integer.parseInt(listSize);
		
		if(pagenum!=null){
			int pagen=Integer.parseInt(pagenum);
			int an=(pagen-1)*size;
			if(an<=listS){
				bn=an;
			}
		}
		else{
			if(after==null||before==null){
				//默认的初始情况，未提交submit
				bn=0;
			}
			 if(after.equalsIgnoreCase("1")&&before.equalsIgnoreCase("0")&&listS>bn){
				//翻下一页
				bn+=size;
			
			
			}else if(after.equalsIgnoreCase("0")&&before.equalsIgnoreCase("1")&&bn>=size){
				//翻上一页
				bn-=size;
			
			}
		}
		int en=bn+size;
		int page=(int)bn/size+1;	//返回页码
		model.put("bn", bn);
		model.put("en", en);
		model.put("page", page);
		FrontUtils.frontPageData(request, model);
		
		
		return "/WEB-INF/t/cms/www/blue/新闻页面/more_web01.html";
		
	}
}
