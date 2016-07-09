package com.jeecms.bbs.entity;

import java.util.Calendar;

import com.jeecms.bbs.entity.base.BaseBbsNews;

public class BbsNews extends BaseBbsNews {

	public BbsNews(){
		super();
		
	}

	public BbsNews(Integer Id, String NewsFrom, String NewsHref,
			String NewsName, Calendar NewsDate, String NewsAbstract) {
		super(Id, NewsFrom, NewsHref, NewsName, NewsDate, NewsAbstract);
		// TODO Auto-generated constructor stub
	}
	
}
