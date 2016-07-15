package com.jeecms.common.bbsaly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * @ClassName SpiderBase
 * @Description 作为解析融资融券(Fin)、期指领先指数(Fur)网页的基类
 * @author wzt3309 
 */
public abstract class SpiderBase{
	
	static protected Document doc;
	/**
	 * @Description 和指定的URL建立连接 
	 * @return void  
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Document getDoc(String URL){
		try{
			doc=Jsoup.connect(URL).get();
			return doc;
		}catch(Exception e){
			System.out.println("连接到"+URL+"失败！");
			return null;
		}
	}
	/**
	 * @Description 根据ID获取html元素
	 * @param @param id
	 * @param @return   
	 * @return Element  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Element getById(String id){
		return doc.getElementById(id);
	}
	/**
	 * @Description 根据元素名获取html元素
	 * @param @param tag
	 * @param @return   
	 * @return Elements  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Elements getByTag(String tag){
		return doc.getElementsByTag(tag);
	}
	/**
	 * @Description 根据class名获取html元素
	 * @param @param className
	 * @param @return   
	 * @return Elements  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Elements getByClassName(String className){
		return doc.getElementsByClass(className);
	}
	/**
	 * @Description 根据属性名获取html元素
	 * @param @param attr
	 * @param @return   
	 * @return Elements  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Elements getByAttribute(String attr){
		return doc.getElementsByAttribute(attr);
	}
	/**
	 * @Description 根据css选择器获取html元素
	 * @param @param selector
	 * @param @return   
	 * @return Elements  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Elements getBySelect(String selector){
		return doc.select(selector);
	}
	/**
	 * @Description 获取元素父辈元素
	 * @param @param e
	 * @param @return   
	 * @return Element  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Element getParent(Element e){
		return e.parent();
	}
	/**
	 * @Description 获取元素所有子代元素
	 * @param @param e
	 * @param @return   
	 * @return Elements  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Elements getChilds(Element e){
		return e.children();
	}
	/**
	 * @Description 获取元素第index个子代元素
	 * @param @param e
	 * @param @param index
	 * @param @return   
	 * @return Element  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Element getChild(Element e,int index){
		return e.child(index);
	}
	/**
	 * @Description 获取单个html元素某属性值
	 * @param @param e
	 * @param @param key
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected String getEleAttr(Element e,String key){
		return e.attr(key);
	}
	/**
	 * @Description 获取多个html元素某属性值
	 * @param @param es
	 * @param @param key
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected List<String> getElesAttr(Elements es,String key){
		List<String> res=new ArrayList<String>();
		for(Element e:es){
			res.add(getEleAttr(e,key));
		}
		return res;
	}
	/**
	 * @Description 获取html元素的所有属性
	 * @param @param e
	 * @param @return   
	 * @return Map<String,String>  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected Map<String,String> getEleAttrs(Element e){
		Map<String,String> res=new HashMap<String,String>();
		for(Attribute attr:e.attributes().asList()){
			res.put(attr.getKey(), attr.getValue());
		}
		return res;
	}
	/**
	 * @Description 获取html元素内的文本内容
	 * @param @param e
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected String getEleText(Element e){
		return e.text();
	}
	/**
	 * @Description  获取多个html元素内的文本内容
	 * @param @param es
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected List<String> getElesText(Elements es){
		List<String> res=new ArrayList<String>();
		for(Element e:es){
			res.add(getEleText(e));
		}
		return res;
	}
	/**
	 * @Description 获取html内的html内容
	 * @param @param e
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected String getEleHtml(Element e){
		return e.html();
	}
	/**
	 * @Description 获取多个html内的html内容
	 * @param @param es
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author wzt3309
	 * @date 2015-11-9
	 */
	static protected List<String> getElesHtml(Elements es){
		List<String> res=new ArrayList<String>();
		for(Element e:es){
			res.add(getEleHtml(e));
		}
		return res;
	}
	static protected String[][] getTableText(Element table){
		System.out.println(table.text());
		Element tbody=table.getElementsByTag("tbody").get(0);
		Elements trs=tbody.getElementsByTag("tr");
		String[][] res=new String[trs.size()][];
		for(int i=0;i<trs.size();i++){
			Element tr=trs.get(i);
			Elements tds=tr.getElementsByTag("td");
			for(int j=0;j<tds.size();j++){
				res[i][j]=tds.get(j).text();
			}			
		}
		return res;
	}	
}
