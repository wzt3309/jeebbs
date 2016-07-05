package com.jeecms.common.httputil;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HtmlRulesReader {

	private static String PROPERTIES_DEFAULT_NAME="bbsnews-crawlrules.properties";
	private Properties prop;
	private Properties[] props;
	private static final Logger log = LoggerFactory
			.getLogger(HtmlRulesReader.class); 
	public String getPROPERTIES_DEFAULT_NAME() {
		return PROPERTIES_DEFAULT_NAME;
	}

	public void setPROPERTIES_DEFAULT_NAME(String PROPERTIES_DEFAULT_NAME) {
		HtmlRulesReader.PROPERTIES_DEFAULT_NAME = PROPERTIES_DEFAULT_NAME;
	}	
	public HtmlRulesReader(String...properties){
		if(properties!=null&&properties.length==1){
			PROPERTIES_DEFAULT_NAME=properties[0];
		}
		init(properties);
	}
	public void init(String...properties){		
		if(properties==null||properties.length<=1){
			String filename=PROPERTIES_DEFAULT_NAME;
			prop = new Properties(); 
			try {
				prop.load(HtmlRulesReader.class.getClassLoader().getResourceAsStream(filename));
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}else{
			String[] filenames=new String[properties.length];
			props=new Properties[properties.length];
			try{
				for(int i=0;i<properties.length;i++){
					props[i].load(HtmlRulesReader.class.getClassLoader()
							.getResourceAsStream(filenames[i]));
				}
			}catch (IOException e) {
				log.error(e.getMessage(),e);
			}
			
		}
	}
	public String getMessage(String code){
		String msg="";
		if(props==null||props.length<=1){
			if(prop!=null){
				msg=prop.getProperty(code);
			}
		}else{			
			for(Properties prop:props){				
				if(prop.containsKey(code)){
					msg=prop.getProperty(code);
					break;
				}
			}
		}
		return msg;
	}
	/**
	 * 静态方法
	 * 根据code和properties文件名获取value
	 * 不传入fileName则采用默认的PROPERTIES_DEFAULT_NAME
	 * @param code
	 * @param fileName
	 * @return
	 */
	public static String getMessage(String code,String... fileName){		
		String msg="";		
		try {
			if(fileName==null||fileName.length<1){
				fileName=new String[1];
				fileName[0]=PROPERTIES_DEFAULT_NAME;
			}
			for(int i=0;i<fileName.length;i++){
				Properties prop=new Properties();
				prop.load(HtmlRulesReader.class.getClassLoader()
						.getResourceAsStream(fileName[i]));					
				if(prop.contains(code)){
					msg=prop.getProperty(code);
					break;
				}
			}					
					
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		return msg;
	}
}
