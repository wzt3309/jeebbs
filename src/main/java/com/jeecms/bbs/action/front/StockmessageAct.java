package com.jeecms.bbs.action.front;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;
import static com.jeecms.bbs.Constants.TPLDIR_SPECIAL;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.bbs.manager.impl.StockmessageMngImpl;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsSite;

import freemarker.template.TemplateModelException;



@Controller
public class StockmessageAct {
	
	@RequestMapping(value = "/stockmessage/search.jhtml")	
	public String searchStock(Integer pageNo, HttpServletRequest request,
			ModelMap model) throws TemplateModelException {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String gpdm = RequestUtils.getQueryParam(request, "gpdm");
		//System.out.println(gpdm);
		String forumIdStr = RequestUtils.getQueryParam(request, "forumId");
		Integer forumId = 0;
		if (forumIdStr != null && StringUtils.isNotBlank(forumIdStr)) {
			forumId = Integer.parseInt(forumIdStr);
		}
		
		//String MGSY = RequestUtils.getQueryParam(request, "MGSY");
		//System.out.println(MGSY+"MGSY");
		
		int num=0;
		Integer check=1;
		Integer uncheck=0;
		
		if(RequestUtils.getQueryParam(request, "MGSY")!=null){
			
			model.put("MGSY", check);
			num++;
		}
		else{
			model.put("MGSY", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "MGWFP")!=null){
			
			model.put("MGWFP", check);
			num++;
		}
		else{
			model.put("MGWFP", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "JTSYL")!=null){
			
			model.put("JTSYL", check);
			num++;
		}
		else{
			model.put("JTSYL", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "MGJZC")!=null){
			
			model.put("MGJZC", check);
			num++;
		}
		else{
			model.put("MGJZC", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "SJL")!=null){
			
			model.put("SJL", check);
			num++;
		}
		else{
			model.put("SJL", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "JZCSYL")!=null){
			
			model.put("JZCSYL", check);
			num++;
		}
		else{
			model.put("JZCSYL", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "MGZYSY")!=null){
			
			model.put("MGZYSY", check);
			num++;
		}
		else{
			model.put("MGZYSY", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "MGJYXJL")!=null){
			
			model.put("MGJYXJL", check);
			num++;
		}
		else{
			model.put("MGJYXJL", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "MGGJJ")!=null){
			
			model.put("MGGJJ", check);
			num++;
		}
		else{
			model.put("MGGJJ", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "GDQYB")!=null){
			
			model.put("GDQYB", check);
			num++;
		}
		else{
			model.put("GDQYB", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "LTGB")!=null){
			
			model.put("LTGB", check);
			num++;
		}
		else{
			model.put("LTGB", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "ZGB")!=null){
			
			model.put("ZGB", check);
			num++;
		}
		else{
			model.put("ZGB", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "LTSZ")!=null){
			
			model.put("LTSZ", check);
			num++;
		}
		else{
			model.put("LTSZ", uncheck);
		}
		
		if(RequestUtils.getQueryParam(request, "ZSZ")!=null){
			
			model.put("ZSZ", check);
			num++;
		}
		else{
			model.put("ZSZ", uncheck);
		}
		String ret=RequestUtils.getQueryParam(request, "return");
		//System.out.println("ret:"+ret);
		if(RequestUtils.getQueryParam(request, "return")!=null){
		if(!ret.trim().equals("")){
			
			String getreturn=RequestUtils.getQueryParam(request, "return");
			getreturn=getreturn.replaceAll(";", " ");
		//System.out.println(getreturn+"herehere");
		String sql="select bean from Stockbasicmessage bean where "+getreturn;
		
		
		List<String> seledReal=RequestUtils.seledReal(ret);
		List<String> seled=RequestUtils.seled(ret);
		
		
		model.put("seled", DEFAULT_WRAPPER.wrap(seled));
		model.put("seledReal", DEFAULT_WRAPPER.wrap(seledReal));
		model.put("sql", sql);
		}
		else{
			model.put("sql", null);
		}
		}
		else{
			model.put("sql", null);
		}
		
		if(RequestUtils.getQueryParam(request, "show")!=null&&RequestUtils.getQueryParam(request, "show").equals("submit")){
			//System.out.println("show from submit");
			model.put("show", " ");
			model.put("current","current" );
			model.put("submit", "submit");
		}
		else{
			model.put("show", "none");
			model.put("current"," " );
			model.put("submit", " ");
			//System.out.println("!=");
		}
		//String sql="select bean from Stockbasicmessage bean where bean.MGSY=1.37";
		//List<Stockbasicmessage> list=stockmessageMng.getlist(sql);
		
		//if(list.isEmpty()){
		//	System.out.println("list is null");
		//}
		//else{
		//	Iterator<Stockbasicmessage> stol=list.iterator();
		//	System.out.println(stol.next().getGPMC());
		//}
		
		model.put("NUM",num);
	
		model.put("GPDM", gpdm);
		model.put("forumId", forumId);
		//System.out.println(model.get("res"));
		FrontUtils.frontPageData(request, model);
		return "/WEB-INF/t/cms/www/blue/选股/股票搜索页面.html";
		//return "/WEB-INF/t/cms/www/blue/include/广告栏.html";
	     //return "/creater/foldEps.html" ;
	}
	
	

}
