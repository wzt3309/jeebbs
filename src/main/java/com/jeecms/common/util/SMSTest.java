package com.jeecms.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
public class SMSTest {
 
private static final String addr = "http://api.sms.cn/mt/";
private static final String userId = "lzc2120547";		//在短信提供商注册的账号
 
/*
* 如uid是：test，登录密码是：123123 
  pwd=md5(123123test),即
       pwd=b9887c5ebb23ebb294acab183ecf0769
       
        线生成地址：http://www.sms.cn/password
*/
private static final String pwd = "6ab1fd03814ecb10ab18fe7d47fc5760";  //在短信提供商注册的密码
 
private static final String encode = "utf8";  
 
public static void send(String msgContent, String mobile) throws Exception {
 
//组建请求
String straddr = addr + 
"?uid="+userId+
"&pwd="+pwd+
"&mobile="+mobile+
"&encode="+encode+
"&content=" +java.net.URLEncoder.encode(msgContent,  "utf-8");
 
StringBuffer sb = new StringBuffer(straddr);
System.out.println("URL:"+sb);
 
//发送请求
URL url = new URL(sb.toString());
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("POST");
BufferedReader in = new BufferedReader(new InputStreamReader(
url.openStream()));
 
//返回结果
String inputline = in.readLine();
System.out.println("Response:"+inputline);
 
}
 
 
 
 
 
}