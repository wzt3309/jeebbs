package com.jeecms.bbs.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.jeecms.common.web.RequestUtils;

public class CategoryUtil {
	
	public static int wichItemToShow(HttpServletRequest request,
			ModelMap model){
		int num=0;
		Integer check=1;
		Integer uncheck=0;
		
		String category=RequestUtils.getQueryParam(request, "category");
		
		if(category!=null && category.equals("GPJBM")){
			model.put("category", "GPJBM");
			
			if(RequestUtils.getQueryParam(request, "MGSY")!=null){
				
				model.put("MGSY", check);
				System.out.println("MGSY IS MOT NULL");
				num++;
			}
			else{
				model.put("MGSY", uncheck);
				System.out.println("MGSY IS NULL");
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
	    }
		
		
	    else{
	    	
	    	model.put("category", "GPXX");
	    	
			if(RequestUtils.getQueryParam(request, "ZF")!=null){
				
				model.put("ZF", check);
				num++;
			}
			else{
				model.put("ZF", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "ZXJ")!=null){
				
				model.put("ZXJ", check);
				num++;
			}
			else{
				model.put("ZXJ", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "HSL")!=null){
				
				model.put("HSL", check);
				num++;
			}
			else{
				model.put("HSL", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "LB")!=null){
				
				model.put("LB", check);
				num++;
			}
			else{
				model.put("LB", uncheck);
			}	   
            if(RequestUtils.getQueryParam(request, "DDX")!=null){
				
				model.put("DDX", check);
				num++;
			}
			else{
				model.put("DDX", uncheck);
			}
			if(RequestUtils.getQueryParam(request, "DDY")!=null){
				
				model.put("DDY", check);
				num++;
			}
			else{
				model.put("DDY", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDZ")!=null){
				
				model.put("DDZ", check);
				num++;
			}
			else{
				model.put("DDZ", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDX3")!=null){
				
				model.put("DDX3", check);
				num++;
			}
			else{
				model.put("DDX3", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDX5")!=null){
				
				model.put("DDX5", check);
				num++;
			}
			else{
				model.put("DDX5", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDX10")!=null){
				
				model.put("DDX10", check);
				num++;
			}
			else{
				model.put("DDX10", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDX60")!=null){
				
				model.put("DDX60", check);
				num++;
			}
			else{
				model.put("DDX60", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDX5H")!=null){
				
				model.put("DDX5H", check);
				num++;
			}
			else{
				model.put("DDX5H", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDX10H")!=null){
				
				model.put("DDX10H", check);
				num++;
			}
			else{
				model.put("DDX10H", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDXLH")!=null){
				
				model.put("DDXLH", check);
				num++;
			}
			else{
				model.put("DDXLH", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDXLZ")!=null){
				
				model.put("DDXLZ", check);
				num++;
			}
			else{
				model.put("DDXLZ", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "ZF3R")!=null){
				
				model.put("ZF3R", check);
				num++;
			}
			else{
				model.put("ZF3R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "ZF5R")!=null){
				
				model.put("ZF5R", check);
				num++;
			}
			else{
				model.put("ZF5R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "ZF10R")!=null){
				
				model.put("ZF10R", check);
				num++;
			}
			else{
				model.put("ZF10R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDY3")!=null){
				
				model.put("DDY3", check);
				num++;
			}
			else{
				model.put("DDY3", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDY5")!=null){
				
				model.put("DDY5", check);
				num++;
			}
			else{
				model.put("DDY5", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDY10")!=null){
				
				model.put("DDY10", check);
				num++;
			}
			else{
				model.put("DDY10", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDY60")!=null){
				
				model.put("DDY60", check);
				num++;
			}
			else{
				model.put("DDY60", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "CJL")!=null){
				
				model.put("CJL", check);
				num++;
			}
			else{
				model.put("CJL", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "BBD")!=null){
				
				model.put("BBD", check);
				num++;
			}
			else{
				model.put("BBD", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "TCL1R")!=null){
				
				model.put("TCL1R", check);
				num++;
			}
			else{
				model.put("TCL1R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "TCL5R")!=null){
				
				model.put("TCL5R", check);
				num++;
			}
			else{
				model.put("TCL5R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "TCL10R")!=null){
				
				model.put("TCL10R", check);
				num++;
			}
			else{
				model.put("TCL10R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "TCL20R")!=null){
				
				model.put("TCL20R", check);
				num++;
			}
			else{
				model.put("TCL20R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DSB")!=null){
				
				model.put("DSB", check);
				num++;
			}
			else{
				model.put("DSB", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "TDC")!=null){
				
				model.put("TDC", check);
				num++;
			}
			else{
				model.put("TDC", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "DDC")!=null){
				
				model.put("DDC", check);
				num++;
			}
			else{
				model.put("DDC", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "ZDC")!=null){
				
				model.put("ZDC", check);
				num++;
			}
			else{
				model.put("ZDC", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "XDC")!=null){
				
				model.put("XDC", check);
				num++;
			}
			else{
				model.put("XDC", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "ZDL1R")!=null){
				
				model.put("ZDL1R", check);
				num++;
			}
			else{
				model.put("ZDL1R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "ZDL5R")!=null){
				
				model.put("ZDL5R", check);
				num++;
			}
			else{
				model.put("ZDL5R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "ZDL10R")!=null){
				
				model.put("ZDL10R", check);
				num++;
			}
			else{
				model.put("ZDL10R", uncheck);
			}	    	
			if(RequestUtils.getQueryParam(request, "LTP")!=null){
				
				model.put("LTP", check);
				num++;
			}
			else{
				model.put("LTP", uncheck);
			}	    	
			  	

	    }
		
		
		return num;
	}

}
