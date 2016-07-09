package com.jeecms.common.bbsnews.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.common.bbsnews.BbsNewsCrawlerBase;
import com.jeecms.common.httputil.HtmlResourParserUtil;
import com.jeecms.common.httputil.HtmlResourUtil;

public class SohuNews extends BbsNewsCrawlerBase{
	private static final String URL="http://business.sohu.com/";
	/**
	 * 找到今日新闻的列表部分的css
	 */
	private static final String TODAY_NEWS_FIELD="news.sohu.today";
	/**
	 * 给新闻链接加上前缀
	 */
	private static final String HREF_PREFIX="news.sohu.hrefprefix";
	/**
	 * 找到每个新闻摘要的css
	 */
	private static final String ABSTRACT_FILED="news.sohu.abstract";
	/**
	 * 每个新闻摘要的字数限制
	 */
	private static final String ABSTRACT_TEXT_NUM="news.sohu.abstract.num";
	/**
	 * 新闻来源在数据库中的表示
	 */
	private static final String NEWS_FROM="f2";
	
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
	public SohuNews(){
		init();
	}

	@Override
	public List<BbsNews> getTodayBbsNews() {
		if(this.newsMaps==null){
			return null;
		}
		List<BbsNews> todayBbsNews=new ArrayList<BbsNews>();
		String[] topics=getNewsTopic().split("&&");
		String[] hrefs=getNewsHref().split("&&");
		String[] abstracts=getNewsAbstract().split("&&");
		Calendar today= Calendar.getInstance();
		for(int i=0;i<topics.length;i++){
			String topic=topics[i];
			String href=hrefs[i];
			if(topic!=null&&!"".equals(topic)
					&&href!=null&&!"".equals(href)){
				BbsNews bbsNews=new BbsNews();
				bbsNews.setNewsName(cutString(topics[i]));
				bbsNews.setNewsHref(hrefs[i]);
				bbsNews.setNewsDate(today);
				bbsNews.setNewsFrom(NEWS_FROM);
				bbsNews.setNewsAbstract(abstracts[i]);
				todayBbsNews.add(bbsNews);
			}
			
		}
		return todayBbsNews;
	}	
	@Override
	public String getNewsTopic() {
		if(this.newsMaps==null){
			return "";
		}
		StringBuffer newsAbstractBf=new StringBuffer();
		for(Map<String,String> newsMap:this.newsMaps){
			if(newsAbstractBf.length()>0){
				newsAbstractBf.append("&&");
			}
			newsAbstractBf.append(newsMap.get(TOPIC));
		}
		return newsAbstractBf.toString();
	}
	@Override
	public String getNewsHref() {
		if(this.newsMaps==null){
			return "";
		}
		StringBuffer newsAbstractBf=new StringBuffer();
		for(Map<String,String> newsMap:this.newsMaps){
			if(newsAbstractBf.length()>0){
				newsAbstractBf.append("&&");
			}
			newsAbstractBf.append(newsMap.get(HREF));
		}
		return newsAbstractBf.toString();
	}
	@Override
	public String getNewsAbstract() {
		if(this.newsMaps==null){
			return "";
		}
		StringBuffer newsAbstractBf=new StringBuffer();
		for(Map<String,String> newsMap:this.newsMaps){
			if(newsAbstractBf.length()>0){
				newsAbstractBf.append("&&");
			}
			newsAbstractBf.append(newsMap.get(ABSTRACT));
		}
		return newsAbstractBf.toString();
	}

	@Override
	public List<Map<String, String>> getNewsMap() {
		if(fieldNodeList!=null&&fieldNodeList.size()>0){
			this.newsMaps=new ArrayList<Map<String,String>>();
			for(int i=0;i<fieldNodeList.size();i++){
				Map<String,String> newsMap=new HashMap<String,String>();
				Node node=fieldNodeList.elementAt(i);
				NodeList children=node.getChildren();				
				if(children!=null&&children.size()>0){			 	
					SimpleNodeIterator it=children.elements();
					while(it.hasMoreNodes()){
						Node aNode=it.nextNode();
						if(aNode instanceof LinkTag){
							String text=aNode.toPlainTextString();
							String href=((LinkTag) aNode).getLink();
							String Abstract=DEFAULT_ABSTRACT;
							if(text!=null&&text.length()>2
									&&href!=null&&href.length()>2){								
								Abstract=getTextContent(href
										,HtmlResourUtil.getMessage(ABSTRACT_FILED)
										,Integer.parseInt(HtmlResourUtil
												.getMessage(ABSTRACT_TEXT_NUM)));
								if(Abstract==null||"".equals(Abstract)){
									Abstract=DEFAULT_ABSTRACT;
								}
								newsMap.put(HREF, HtmlResourUtil.getMessage(HREF_PREFIX)
										+href);
								newsMap.put(TOPIC, text);							
								newsMap.put(ABSTRACT, Abstract);
							}							
						}					
					}					
				}				
				this.newsMaps.add(newsMap);
			}
			
			
		}	
		return this.newsMaps;
	}
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
	public List<Map<String, String>> getNewsMaps() {
		return newsMaps;
	}
	public void setNewsMaps(List<Map<String, String>> newsMaps) {
		this.newsMaps = newsMaps;
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
	public static String getNewsFrom() {
		return NEWS_FROM;
	}
	
}
