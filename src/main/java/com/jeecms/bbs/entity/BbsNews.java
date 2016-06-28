package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseBbsNews;

public class BbsNews extends BaseBbsNews {

	public BbsNews(){
		super();
		
	}
	public BbsNews(java.lang.Integer Id,String NewsFrom ,String NewsHref,String NewsName,
			java.util.Calendar NewsDate){
		
		super(	Id,
				NewsFrom,
				NewsHref,
				NewsName,
				NewsDate);
	}
}
