package com.jeecms.common.httputil;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DdxGet {
	
	private static String url="http://ddx.gubit.cn/xg/ddxlist.php?&lsdate=2016-07-28&getlsdate=1"; 
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int k=131;
		for(int i=131;i<=k;i++){
			String url="";
			if(i==1){
				url=DdxGet.url;
			}else{
				url=DdxGet.url+"&page="+i;
				k=132;
			}
			getContent(url);
		}
	}
	public static String getContent(String url) throws Exception{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet get=createDefaultHttpGet(url);
		CloseableHttpResponse response=httpClient.execute(get);
		HttpEntity httpEntity=response.getEntity();
		String s=EntityUtils.toString(httpEntity);
		System.out.println(s);
		return s;
	}
	/**
	 * 设置默认请求头
	 */
	public static void setDefaultRequestHeader(HttpRequestBase request)throws Exception{
		request.setHeader("Accept"
				, "*/*");
		request.setHeader("User-Agent"
				, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 " +
						"(KHTML, like Gecko) Chrome/48.0.2560.0 Safari/537.36");
		request.setHeader("Referer", "http://ddx.gubit.cn/xg/ddx.html");
		request.setHeader("Host", "ddx.gubit.cn");
		request.setHeader("X-Requested-With", "XMLHttpRequest");
		
		
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
	private static int DEFAULT_STOCK_TIME_OUT=5000;
	private static int DEFAULT_CONN_TIME_OUT=5000;

}
