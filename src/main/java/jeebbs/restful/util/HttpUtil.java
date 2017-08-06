package jeebbs.restful.util;

import jeebbs.restful.util.model.Throttle;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * HTTP 请求工具类
 * Created by ztwang on 2017/6/23 0023.
 */
public final class HttpUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

    private static final Throttle DEFAULT_THROTTLE = new Throttle();

    private static final String HEADER_DEFAULT_ACCEPT = "text/html,application/xhtml+xml," +
                                                        "application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
    private static final String HEADER_DEFAULT_ACCEPT_ENCODING = "gzip, deflate,sdch";
    private static final String HEADER_DEFAULT_ACCEPT_LANGUAGE = "zh-CN,zh;q=0.8";
    private static final String HEADER_DEFAULT_CONNECTION = "keep-alive";
    private static final String HEADER_DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) " +
                                                            "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                                            "Chrome/58.0.3029.110 Safari/537.36";
    /*有关http请求的参数*/
    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int SOCKET_TIMEOUT = 5000;

    /*有关http连接工厂的参数*/
    private static final int DEFAULT_MAX_RETRIES = 5;
    private static final int DEFAULT_MAX_CONN_NUM = 200;
    private static final int DEFAULT_MAX_PER_ROUTE = 20;
    //httpClient连接池
    private static final PoolingHttpClientConnectionManager POOLING_HTTP_CLIENT_CONNECTION_MANAGER;
    //httpClient重连处理
    private static final HttpRequestRetryHandler RETRY_HANDLER;
    //单例模式
    private static CloseableHttpClient HTTPCLIENT;
    static {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();

        POOLING_HTTP_CLIENT_CONNECTION_MANAGER = new PoolingHttpClientConnectionManager(registry);
        POOLING_HTTP_CLIENT_CONNECTION_MANAGER.setMaxTotal(DEFAULT_MAX_CONN_NUM);
        POOLING_HTTP_CLIENT_CONNECTION_MANAGER.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        // 启动监视并清理在连接池中超时的httpclient连接
        new Thread(new IdleConnectionMonitorService(POOLING_HTTP_CLIENT_CONNECTION_MANAGER)).start();
        RETRY_HANDLER = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
                // 设置最大重连次数为
                if (i > DEFAULT_MAX_RETRIES) {
                    return false;
                }
                if (e instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (e instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (e instanceof InterruptedIOException) {// 超时
                    return true;
                }
                if (e instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (e instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (e instanceof SSLException) {// ssl握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    // 如果请求被认为是幂等的，那么就重试。即重复执行不影响程序其他效果的
                    return true;
                }
                return false;
            }
        };
        HTTPCLIENT = getHttpclient();

    }

    public static CloseableHttpClient getHttpclient() {
        if (HTTPCLIENT == null) {
            HTTPCLIENT = HttpClients.custom()
                    .setConnectionManager(POOLING_HTTP_CLIENT_CONNECTION_MANAGER)
                    .setRetryHandler(RETRY_HANDLER)
                    .build();
        }
        return HTTPCLIENT;
    }

    private HttpUtil(){}

    public static String sendGET(String url) {
        return sendGET(url, null,null, DEFAULT_THROTTLE);
    }

    public static String sendGET(String url, Map<String, String> params) {
        return sendGET(url, params,null, DEFAULT_THROTTLE);
    }

    /**
     * 使用GET 方式获取数据
     * @param url
     * @param params
     * @param header
     * @param throttle
     * @return
     */
    public static String sendGET(final String url, Map<String, String> params,
                                 Map<String, String> header, final Throttle throttle) {
        HttpGet httpGet = null;
        CloseableHttpResponse response;
        String html = null;
        String combineURL = combineURL(url, params);
        try {
            httpGet = createHttpGet(combineURL, header);
//            LOG.info(combineURL + " wait");
            throttle.wait(combineURL);
//            response = HttpClients.createDefault().execute(httpGet);
//            System.out.println(Thread.currentThread() + HTTPCLIENT.toString());
            response = getHttpclient().execute(httpGet);
            if (response != null) {
                try {
                    HttpEntity entity = response.getEntity();
                    html = EntityUtils.toString(entity, "utf-8");
                    Elements metaEncods = HtmlUtil.getElementsBySelector(html, "meta[http-equiv=\"Content-Type\"]");
                    Element metaEncod = metaEncods != null ? metaEncods.first() : null;
                    String charset = metaEncod != null ? metaEncod.attr("content") : "utf-8";
                    int at = charset.indexOf("charset=");
                    if (at > 0) {
                        charset = charset.substring(at + "charset=".length());
                    }

                    if (!"utf-8".equals(charset)) {
                        try {
                            entity = getHttpclient().execute(httpGet).getEntity();
                            html = EntityUtils.toString(entity, charset);
                        } catch (UnsupportedEncodingException e) {
                            //ignore
                        }
                    }
                    EntityUtils.consume(entity);
                }finally {
                    response.close();
                }
            }
        } catch (ClientProtocolException | IllegalArgumentException e) {
            LOG.error(
                    errMsg(combineURL,
                        httpGet == null ? null : httpGet.getAllHeaders(),
                        e.getMessage())
                    );
        } catch (IOException e) {
            LOG.error(
                    errMsg(combineURL,
                        httpGet == null ? null : httpGet.getAllHeaders(),
                        e.getMessage())
                    );
        }

        return html;
    }

    public static List<Map<String, String>> downCVS(String url, String delim) {
        HttpGet httpGet = null;
        CloseableHttpResponse response;
        List<Map<String, String>> result = new ArrayList<>();
        try {
            httpGet = createHttpGet(url, null);
            DEFAULT_THROTTLE.wait(url);
            response = getHttpclient().execute(httpGet);
            if (response != null) {
                try {
                    HttpEntity entity = response.getEntity();
                    InputStream in = entity.getContent();
                    long length = entity.getContentLength();
                    if (length < 0) {
                        LOG.info("cvs has no content to download");
                        return null;
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String headLine = reader.readLine();
                    String dataLine;
                    StringTokenizer tkHead;
                    StringTokenizer tkData;
                    while ((dataLine = reader.readLine()) != null) {
                        tkHead = new StringTokenizer(headLine, delim);
                        tkData = new StringTokenizer(dataLine, delim);
                        Map<String, String> map = new HashMap<>();
                        while (tkHead.hasMoreTokens() && tkData.hasMoreTokens()) {
                            map.put(tkHead.nextToken(), tkData.nextToken());
                        }
                        result.add(map);
                    }
                }finally {
                    response.close();
                }
            }
        } catch (ClientProtocolException e) {
            LOG.error(
                    errMsg(url,
                            httpGet == null ? null : httpGet.getAllHeaders(),
                            e.getMessage())
            );
        } catch (IOException e) {
            LOG.error(
                    errMsg(url,
                            httpGet == null ? null : httpGet.getAllHeaders(),
                            e.getMessage())
            );
        }
        return result;
    }

    private static String combineURL(final String url, final Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) return url;
        StringBuilder resURL = new StringBuilder(url);
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> entry: params.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            if (!StringUtils.isEmpty(name)
                    && !StringUtils.isEmpty(value)) {
                nvps.add(new BasicNameValuePair(name, value));
            }
        }
        try {
            String tmp = EntityUtils.toString(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            resURL.append("?").append(tmp);
        } catch (IOException e) {
            LOG.error(String.format("URL \'%s\' combine with params \'%s\' fail", url, params));
        }
        return resURL.toString();
    }

    private static HttpGet createHttpGet(String url, Map<String, String> header) {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            setHeader(httpGet, header);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();
            httpGet.setConfig(requestConfig);
        }catch (IllegalArgumentException e) {
            LOG.error(String.format("URL: \'%s\' is incorrect", url));
        }catch (Exception e) {
            LOG.error("Unknown Exception: " + e.getMessage());
        }
        return httpGet;
    }

    private static void setHeader(HttpGet httpGet, Map<String, String> header) {
       if (httpGet == null || header == null || header.isEmpty()) {
           setDefaultHeader(httpGet);
           return;
       }
       setDefaultHeader(httpGet);
       for (Map.Entry<String, String> entry: header.entrySet()) {
           String key = entry.getKey();
           String value = entry.getValue();
           if (!StringUtils.isEmpty(key)
                   && !StringUtils.isEmpty(value)) {
                httpGet.setHeader(key, value);
           }
       }
    }

    private static void setDefaultHeader(HttpGet httpGet) {
        if (httpGet == null) return;
        httpGet.setHeader("Accept", HEADER_DEFAULT_ACCEPT);
        httpGet.setHeader("Accept-Encoding", HEADER_DEFAULT_ACCEPT_ENCODING);
        httpGet.setHeader("Accept-Language", HEADER_DEFAULT_ACCEPT_LANGUAGE);
        httpGet.setHeader("Connection", HEADER_DEFAULT_CONNECTION);
        httpGet.setHeader("User-agent", HEADER_DEFAULT_USER_AGENT);
    }

    private static String resolveHeader(Header[] headers) {
        int len;
        if (headers == null || (len = headers.length) < 1) {
            return "{}";
        }
        StringBuilder sbd = new StringBuilder("{");
        for (Header header: headers) {
            String name = header.getName();
            String value = header.getValue();
            if (!StringUtils.isEmpty(name)
                    && !StringUtils.isEmpty(value)) {
                sbd.append(name).append(": ").append(value);
            }
            if (--len != 0) sbd.append(";");
        }
        return sbd.append("}").toString();
    }

    private static String errMsg(String url, Header[] headers, String eMsg) {
        return String.format("eMsg: %s, URL: %s, Headers: %s", eMsg, url, resolveHeader(headers));
    }

    private static class IdleConnectionMonitorService implements Runnable {
        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorService(HttpClientConnectionManager connMgr) {
            this.connMgr = connMgr;
        }
        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // 关闭过期连接
                        connMgr.closeExpiredConnections();
                        // 关闭超时120s未其他动作的连接
                        connMgr.closeIdleConnections(120, TimeUnit.SECONDS);
//                        LOG.info("过期连接关闭");
                    }
                }
            } catch (InterruptedException e) {
                LOG.warn("HttpClient 空闲连接检查服务被终止");
            }
        }
    }
}
