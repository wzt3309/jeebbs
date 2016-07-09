package com.jeecms.bbs.entity.base;

import java.text.SimpleDateFormat;

public class BaseBbsNews {
	
	private java.lang.Integer Id;	//自增的ID
	private java.lang.String NewsFrom;	//新闻出处
	private java.lang.String NewsHref;	//新闻链接地址
	private java.lang.String NewsName;	//新闻的标题
	private java.util.Calendar NewsDate;	//新闻获取的时间
	private java.lang.String NewsAbstract;	//新闻的摘要
	
	
	
	public BaseBbsNews(){
		
	}
	public BaseBbsNews(java.lang.Integer Id,String NewsFrom,String NewsHref,String NewsName,
			java.util.Calendar NewsDate,String NewsAbstract){
		this.setId(Id);
		this.setNewsFrom(NewsFrom);
		this.setNewsHref(NewsHref);
		this.setNewsName(NewsName);
		this.setNewsDate(NewsDate);
		this.setNewsAbstract(NewsAbstract);
		
	}
	public java.lang.Integer getId() {
		return Id;
	}
	public void setId(java.lang.Integer id) {
		Id = id;
	}
	public java.lang.String getNewsName() {
		return NewsName;
	}
	public void setNewsName(java.lang.String newsName) {
		NewsName = newsName;
	}
	public  java.util.Calendar getNewsDate() {		
		return NewsDate;
	}
	public void setNewsDate(java.util.Calendar newsDate) {
		NewsDate = newsDate;
	}
	public String getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(NewsDate.getTime());
		//System.out.println(dateStr);
		return dateStr;
	}
	public java.lang.String getNewsFrom() {
		return NewsFrom;
	}
	public void setNewsFrom(java.lang.String newsFrom) {
		NewsFrom = newsFrom;
	}
	public java.lang.String getNewsHref() {
		return NewsHref;
	}
	public void setNewsHref(java.lang.String newsHref) {
		NewsHref = newsHref;
	}
	
	public java.lang.String getNewsAbstract() {
		return NewsAbstract;
	}
	public void setNewsAbstract(java.lang.String newsAbstract) {
		NewsAbstract = newsAbstract;
	}
	@Override
	public String toString() {
		return "BaseBbsNews [Id=" + Id + ", NewsFrom=" + NewsFrom
				+ ", NewsHref=" + NewsHref + ", NewsName=" + NewsName
				+ ", NewsDate=" + getTime() + ", NewsAbstract=" + NewsAbstract
				+ "]";
	}
	
	

}
	