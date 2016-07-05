package com.jeecms.common.bbsnews.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.util.NodeList;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.common.bbsnews.BbsNewsCrawlerBase;
import com.jeecms.common.httputil.HtmlResourParserUtil;
import com.jeecms.common.httputil.HtmlResourUtil;

public class XueQiuNews extends BbsNewsCrawlerBase {

	private static final String URL="http://xueqiu.com/today/";
	/**
	 * 找到今日新闻的列表部分的css
	 */
	private static final String TODAY_NEWS_FIELD="news.xueqiu.today";
	/**
	 * 给新闻链接加上前缀
	 */
	private static final String HREF_PREFIX="news.xueqiu.hrefprefix";
	/**
	 * 新闻来源在数据库中的表示
	 */
	private static final String NEWS_FROM="f4";
	
	/**
	 * 今日新闻的列表部分Node元素集合
	 */
	private NodeList fieldNodeList;	
	/**
	 * 获取的完整的页面信息
	 */
	private String htmlStr;
	/**
	 * 获得的新闻Maps
	 */
	private List<Map<String,String>> newsMaps;
	
	
	
	/**
	 * 初始化，获得今日新闻的列表部分
	 */
	protected void init(){
		this.htmlStr=getHtmlByGetMethod(URL);
		this.fieldNodeList=HtmlResourParserUtil.getNodesByConditions(HtmlResourUtil
				.getMessage(TODAY_NEWS_FIELD),htmlStr);
		getNewsMap();
	}
	public XueQiuNews(){
		init();
	}
	
	@Override
	public List<BbsNews> getTodayBbsNews() {
		List<BbsNews> todayBbsNews=new ArrayList<BbsNews>();
		String[] topics=getNewsTopic().split(",");
		String[] hrefs=getNewsHref().split(",");
		Calendar today= Calendar.getInstance();
		for(int i=0;i<topics.length;i++){
			BbsNews bbsNews=new BbsNews();
			bbsNews.setNewsName(topics[i]);
			bbsNews.setNewsHref(hrefs[i]);
			bbsNews.setNewsDate(today);
			bbsNews.setNewsFrom(NEWS_FROM);
			todayBbsNews.add(bbsNews);
		}
		return todayBbsNews;
	}	
	@Override
	public String getNewsTopic() {
		StringBuffer newsAbstractBf=new StringBuffer();
		for(Map<String,String> newsMap:this.newsMaps){
			if(newsAbstractBf.length()>0){
				newsAbstractBf.append(",");
			}
			newsAbstractBf.append(newsMap.get(TOPIC));
		}
		return newsAbstractBf.toString();
	}
	@Override
	public String getNewsHref() {
		StringBuffer newsAbstractBf=new StringBuffer();
		for(Map<String,String> newsMap:this.newsMaps){
			if(newsAbstractBf.length()>0){
				newsAbstractBf.append(",");
			}
			newsAbstractBf.append(newsMap.get(HREF));
		}
		return newsAbstractBf.toString();
	}
	@Override
	public String getNewsAbstract() {		
		StringBuffer newsAbstractBf=new StringBuffer();
		for(Map<String,String> newsMap:this.newsMaps){
			if(newsAbstractBf.length()>0){
				newsAbstractBf.append(",");
			}
			newsAbstractBf.append(newsMap.get(ABSTRACT));
		}
		return newsAbstractBf.toString();
	}
	/**
	 * 返回放置News的Maps（是一个List）
	 * Maps内的每一个Map中统一有三个元素 
	 * abstract（摘要） topic（标题） href（链接）
	 */
	@Override
	public List<Map<String, String>> getNewsMap() {		
		if(fieldNodeList!=null&&fieldNodeList.size()>0){
			this.newsMaps=new ArrayList<Map<String,String>>();
			for(int i=0;i<fieldNodeList.size();i++){
				Map<String,String> newsMap=new HashMap<String,String>();
				Node node=fieldNodeList.elementAt(i);
				NodeList children=node.getChildren();
				if(children!=null&&children.size()>0){
					if(children.size()>1){
						Tag aTag=(Tag)children.elementAt(1);
						if(children.size()>2){
							Tag divTag=(Tag)children.elementAt(2);
							newsMap.put(ABSTRACT, divTag.toPlainTextString());
						}else{
							newsMap.put(ABSTRACT, "");
						}
						newsMap.put(HREF, HtmlResourUtil.getMessage(HREF_PREFIX)
								+aTag.getAttribute("href"));
						newsMap.put(TOPIC, aTag.toPlainTextString());	
					}else{
						newsMap.put(HREF,"");
						newsMap.put(TOPIC,"");
					}
				}
				
				this.newsMaps.add(newsMap);
			}
			
			
		}	
		return this.newsMaps;
	}
	
	//=============================get and set method=============================//
	public NodeList getFieldNodeList() {
		return fieldNodeList;
	}
	public void setFieldNodeList(NodeList fieldNodeList) {
		this.fieldNodeList = fieldNodeList;
	}
	public String getHtmlStr() {
		return htmlStr;
	}
	public void setHtmlStr(String htmlStr) {
		this.htmlStr = htmlStr;
	}
	public static String getUrl() {
		return URL;
	}
	public static String getTodayNewsField() {
		return TODAY_NEWS_FIELD;
	}
	public static String getHrefPrefix() {
		return HREF_PREFIX;
	}
	
	
	
	
}
