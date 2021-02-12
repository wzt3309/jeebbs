package jeebbs.restful.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ztwang on 2017/6/23 0023.
 */
public class HttpUtilTest {
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtilTest.class);
    /**
     * 测量多线程专区的速度
     * 每个URL启动一个线程
     * @throws Exception
     */
    @Test
    public void sendGET() throws Exception {
        String[] urisToGet = {
                "http://blog.csdn.net/gaolu/article/details/48466059",
                "http://blog.csdn.net/gaolu/article/details/48243103",
                "http://blog.csdn.net/gaolu/article/details/47656987",
                "http://business.sohu.com/",
                "http://business.sohu.com/",
                "http://blog.csdn.net/gaolu/article/details/47055029",

                "http://blog.csdn.net/gaolu/article/details/46400883",
                "http://blog.csdn.net/gaolu/article/details/46359127",
                "http://blog.csdn.net/gaolu/article/details/46224821",
                "http://blog.csdn.net/gaolu/article/details/45305769",

                "http://blog.csdn.net/gaolu/article/details/43701763",
                "http://blog.csdn.net/gaolu/article/details/43195449",
                "http://blog.csdn.net/gaolu/article/details/42915521",
                "http://blog.csdn.net/gaolu/article/details/41802319",

                "http://blog.csdn.net/gaolu/article/details/41045233",
                "http://blog.csdn.net/gaolu/article/details/40395425",
                "http://blog.csdn.net/gaolu/article/details/40047065",
                "http://blog.csdn.net/gaolu/article/details/39891877",

                "http://blog.csdn.net/gaolu/article/details/39499073",
                "http://blog.csdn.net/gaolu/article/details/39314327",
                "http://blog.csdn.net/gaolu/article/details/38820809",
                "http://blog.csdn.net/gaolu/article/details/38439375",
        };

        long start = System.currentTimeMillis();
        try {
            int pagecount = 5;
            assert pagecount <= urisToGet.length;
            int pussure = 1;
            ExecutorService executors = Executors.newFixedThreadPool(pagecount);
            final CountDownLatch countDownLatch = new CountDownLatch(pagecount * pussure);
            for(int j = 0; j < pussure; j++){
                for(int i = 0; i < pagecount;i++){
                    //启动线程抓取
                    final String url = urisToGet[i];
                    executors.execute(new Runnable() {
                        CountDownLatch count = countDownLatch;
                        @Override
                        public void run() {
                            try {
                                String html = HttpUtil.sendGET(url);
                                LOG.info(html.substring(0, 100).trim().replaceAll("[\r\n\t]", "\0"));
                            } finally {
                                count.countDown();
                            }
                        }
                    });
                }
            }
            countDownLatch.await();
            executors.shutdown();
        } catch (InterruptedException e) {
            LOG.warn(e.getMessage());
        } finally {
            LOG.info("所有线程已完成，开始进入下一步！");
        }

        long end = System.currentTimeMillis();
        LOG.info("共用时: " + (end - start) + "ms");
    }

    @Test
    public void testEncoding() {
        String url = "http://finance.sina.com.cn/";
        System.out.println(HttpUtil.sendGET(url));
    }

//    @Test
//    public void testddx() {
//        Map<String, String> params = new HashMap<>();
//        params.put("getlsdate", "1");
//        params.put("t", String.valueOf(Math.random()));
//        params.put("orderby", "2");
//        params.put("isdesc", "1");
//        Map data = ddxData(params);
//        if (!CollectionUtils.isEmpty(data)) {
//            int numOfUp = 0;
//            int total = (Integer)data.get("total");
//            int pageSize = 20;
//            int pages = 1;
//            if (total > pageSize) {
//                pages = (int)Math.ceil(((double)total) / pageSize);
//            }
//
//            numOfUp += numOfUp(data);
//            numOfUp += binaryNumOfUp(2, 2, pages, pageSize, params);
//            System.out.println(numOfUp);
//        }
//
//
//        if (!CollectionUtils.isEmpty(data)) {
//            List dataOfStocks = (List)data.get("data");
//            for(Object dataOfStock: dataOfStocks) {
//                System.out.println(dataOfStock.getClass());
//                System.out.println(dataOfStock);
//            }
//        }
//    }
//
//    private Map<String, Object> ddxData(Map<String, String> params) {
//        String url = "http://ddx.gubit.cn/xg/ddxlist.php";
//        Map<String, String> header = new HashMap<>();
//        header.put("Referer", "http://ddx.gubit.cn/xg/ddx.html");
//        String json = HttpUtil.sendGET(url, params, header);
//        return JacksonUtil.json2Map(json);
//    }
//
//    private int numOfUp(Map<String, Object> data) {
//        List dataOfStocks = (List)data.get("data");
//        int numOfUp =0;
//        for (Object dataOfStock: dataOfStocks) {
//            Object var2 = ((List)dataOfStock).get(2);
//            if (var2 instanceof Integer && ((Integer)var2) > 0) {
//                numOfUp++;
//            }
//            if (var2 instanceof Double && ((Double)var2) > 0) {
//                numOfUp++;
//            }
//        }
//        return numOfUp;
//    }
//
//    private int binaryNumOfUp(int start, int beg, int end, int pageSize, Map<String, String> params) {
//        if (beg > end) return 0;
//        int mid = (beg + end) / 2;
//        params.put("page", String.valueOf(mid));
//        int numOfUp = numOfUp(ddxData(params));
//        if (numOfUp == pageSize) return binaryNumOfUp(start, mid, end, pageSize, params);
//        if (numOfUp == 0) return binaryNumOfUp(start, beg, mid, pageSize, params);
//        else return (mid - start) * pageSize + numOfUp;
//    }
}