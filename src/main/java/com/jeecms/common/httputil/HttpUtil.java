package com.jeecms.common.httputil;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Args;
import org.apache.http.util.ByteArrayBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpUtil {
	private static int DEFAULT_STOCK_TIME_OUT=5000;
	private static int DEFAULT_CONN_TIME_OUT=5000;
	/**
	 * 默认编码格式为utf-8
	 */
	private static String DEFAULT_CHARSET="UTF-8";
	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
	/**
	 * 用Get方法获取Html原代码
	 * @return
	 */
	public static String getHtmlByGetMethod(String url){
		String resultHtml="";			
		
		CloseableHttpClient httpClient=null;
		HttpGet httpGet=null;	
		CloseableHttpResponse response=null;
		try {
			httpClient=createCloseableHttpClient();
			httpGet=createDefaultHttpGet(url);
			response=httpClient.execute(httpGet);
			//记录响应信息
//			log.info("---------------HttpUtil Response BEGIN---------------");
//			log.info("Location: " + response.getLastHeader("Location"));
//			log.info("StatusCode: "+response.getStatusLine().getStatusCode());
//			log.info("Content-Type: "+response.getLastHeader("Content-Type"));
//			log.info("Content-Length: "+response.getLastHeader("Content-Length"));
//			log.info("---------------HttpUtil Response END---------------");
			int statusCode=response.getStatusLine().getStatusCode();
			if ((statusCode == HttpStatus.SC_MOVED_PERMANENTLY) ||  
		            (statusCode == HttpStatus.SC_MOVED_TEMPORARILY) ||  
		            (statusCode == HttpStatus.SC_SEE_OTHER) ||  
		            (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {  
		         // 此处重定向处理   此处还未验证  
		         String newUrl = response.getLastHeader("Location").getValue();  
		         httpClient = createCloseableHttpClient();  
		         httpGet=createDefaultHttpGet(newUrl);  
		         response = httpClient.execute(httpGet);  
		       }  
			HttpEntity httpEntity=response.getEntity();
			//记录Response Header信息
			Header[] headers=response.getAllHeaders();
//			log.info("---------------HttpUtil Response Header BEGIN---------------");
//			for(Header header:headers){
//				log.info(header.getName()+":\t"+header.getValue());
//			}
//			log.info("---------------HttpUtil Response Header END---------------");
			if(httpEntity!=null){
				String charset="";
				Header contentType=httpEntity.getContentType();
				HeaderElement[] hes=(contentType!=null?contentType.getElements():null);
				if(hes!=null){
					for(HeaderElement he:hes){
						for(NameValuePair p:he.getParameters()){
							if("charset".equalsIgnoreCase(p.getName())){
								charset=p.getValue();
							}
						}
					}
				}
				byte[] bytes=toByteArray(httpEntity);
				if("".equals(charset)){					
					charset=getCharsetFromPageHeader(bytes);
				}			
//				brHtml=new BufferedReader(new InputStreamReader(inHtml,charset));
//				StringBuffer htmlBuffer=new StringBuffer();
//				String tempStr=null;
//				while((tempStr=brHtml.readLine())!=null){
//					htmlBuffer.append(tempStr);
//				}
				resultHtml=new String(bytes,charset);				
				//System.out.println(resultHtml);
			}
		} catch (ClientProtocolException e) {			
			log.error(e.getMessage(), e);
		} catch (IOException e) {			
			log.error(e.getMessage(), e);
		} catch (Exception e){
			log.error(e.getMessage(), e);
		}finally{
			try {							
				if(response!=null){
					response.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			
		}
		return resultHtml;
	}
	/**
	 * 用Post方法获取Html原代码
	 * @return
	 */
	public static String getHtmlByPostMethod(String url){
		return null;
	}
	
	
	public static int getDEFAULT_STOCK_TIME_OUT() {
		return DEFAULT_STOCK_TIME_OUT;
	}
	public static void setDEFAULT_STOCK_TIME_OUT(int dEFAULT_STOCK_TIME_OUT) {
		DEFAULT_STOCK_TIME_OUT = dEFAULT_STOCK_TIME_OUT;
	}
	public static int getDEFAULT_CONN_TIME_OUT() {
		return DEFAULT_CONN_TIME_OUT;
	}
	public static void setDEFAULT_CONN_TIME_OUT(int dEFAULT_CONN_TIME_OUT) {
		DEFAULT_CONN_TIME_OUT = dEFAULT_CONN_TIME_OUT;
	}
	
	/**
	 * 设置默认请求头
	 */
	public static void setDefaultRequestHeader(HttpRequestBase request)throws Exception{
		request.setHeader("Accept"
				, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		request.setHeader("User-Agent"
				, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 " +
						"(KHTML, like Gecko) Chrome/48.0.2560.0 Safari/537.36");
		request.setHeader("Accept-Charset", "utf-8,GB2312;q=0.7,*;q=0.7");
		request.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		request.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		
		
	}
	/**
	 * 设置自定义请求头
	 * @param request
	 * @param headerMap
	 */
	public static void setRequestHeader(HttpRequestBase request,Map<String,String> headerMap)
			throws Exception{
		if(headerMap!=null&&headerMap.size()>1){
			Iterator<Entry<String, String>> it=headerMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String,String> entry = it.next();
				request.setHeader(entry.getKey(),entry.getValue());
			}
		}		
	}
	/**
	 * 获取CloseableHttpClient
	 * @return
	 */
	public static CloseableHttpClient createCloseableHttpClient(){
		return HttpClients.createDefault();
	}
	/**
	 * 获取设置默认超时的HttpGet
	 * @param url
	 * @return
	 */
	public static HttpGet createDefaultHttpGet(String url)throws Exception{
		url=url.trim();
		HttpGet httpGet=new HttpGet(url);
		RequestConfig requestConfig=RequestConfig.custom()
				.setSocketTimeout(DEFAULT_STOCK_TIME_OUT)
				.setConnectTimeout(DEFAULT_CONN_TIME_OUT).build();
		httpGet.setConfig(requestConfig);
		setDefaultRequestHeader(httpGet);
		return httpGet;
	}
	/**
	 * 从页面中的meta charset元素获取页面编码
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public static String getCharsetFromPageHeader(byte[] bytes)throws Exception{
		String charset=DEFAULT_CHARSET;		
		String regEx="(?=<meta).*?(?<=charset=[\\'|\\\"]?)([[a-z]|[A-Z]|[0-9]|-]*)";
		Pattern p=Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
		Matcher m=p.matcher(new String(bytes));
		if(m.groupCount()==1&&m.find()){
			String tmpCharset=m.group(1);
			if(tmpCharset!=null&&!"".equals(tmpCharset))
				charset=tmpCharset;
			//gbk与gb2312兼容且编码范围大于gb2312 比如“珺”gb2312是乱码gbk是正常
			if(charset.equalsIgnoreCase("gb2312"))
				charset="GBK";
		}
		return charset;
	}
	/**
	 * 解析返回的HttpEntity 
	 * 虽然EntityUtils里有这个方法，
	 * 但此处列出来的原因是当调用此方法时 entity的流就被关闭了，
	 * 这个应该是对response操作得最后一步
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	private static byte[] toByteArray(final HttpEntity entity) throws IOException {
        Args.notNull(entity, "Entity");
        final InputStream instream = entity.getContent();
        if (instream == null) {
            return null;
        }
        try {
            Args.check(entity.getContentLength() <= Integer.MAX_VALUE,
                    "HTTP entity too large to be buffered in memory");
            int i = (int)entity.getContentLength();
            if (i < 0) {
                i = 4096;
            }
            final ByteArrayBuffer buffer = new ByteArrayBuffer(i);
            final byte[] tmp = new byte[4096];
            int l;
            while((l = instream.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toByteArray();
        } finally {
            instream.close();
        }
    }
}
	
	