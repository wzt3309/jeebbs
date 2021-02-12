package jeebbs.restful.service.news.mng;

import com.github.pagehelper.PageHelper;
import jeebbs.restful.Application;
import jeebbs.restful.service.news.model.News;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ztwang on 2017/7/7 0007.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class NewsMapperTest {
    @Autowired
    private NewsMapper newsMapper;

    private News testNews = new News("JT source1",
                                     "JT title1",
                                     "JT http://JT1",
                                     "java测试1",
                                      Timestamp.valueOf("2017-07-07 06:00:00"));

    @Test
    @Rollback
    public void testNewsMapperSelect() throws Exception {
        final Long id = 1L;
        final String expectSource = "搜狐财经";
        final String expectTitle ="测试test1";
        final String expectHref ="http://test.com";
        final String expectProfile = "这是一个测试test1";
        final Timestamp expectStmp = Timestamp.valueOf("2017-07-07 15:03:59");
        News myNews;
        List<News> list;
        News expectNews = new News(id, expectSource, expectTitle, expectHref, expectProfile, expectStmp);

        /*-----------------------测试 数据查询方法-----------------------*/
        List<News> newsFinds = new ArrayList<>();
        //Test findById
        myNews = newsMapper.findById(expectNews.getId());
        newsFinds.add(myNews);
        //Test findByTitle
        myNews = newsMapper.findByTitle(expectNews.getTitle()).get(0);
        newsFinds.add(myNews);
        //Test findByHref
        myNews = newsMapper.findByHref(expectNews.getHref()).get(0);
        newsFinds.add(myNews);
       /* //Test findByProfile
        myNews = newsMapper.findByProfile(expectNews.getProfile());
        newsFinds.add(myNews);*/
        for (News temp: newsFinds) {
            assertNewsEqual(temp, expectNews);
        }

        //Test findBySearchMap
        Map<String, String> map = new HashMap<>();
        String source1 = "搜狐";
        String title1 = "测试";
        map.put("source", "%" + source1 + "%");
        map.put("title", "%" + title1 + "%");
        list = newsMapper.findBySearchMap(map);
        for (News news: list) {
            Assert.assertTrue(news != null
                    && StringUtils.countOccurrencesOf(news.getSource(), source1) > 0
                    && StringUtils.countOccurrencesOf(news.getTitle(), title1) > 0
                    && !StringUtils.isEmpty(news.getHref())
                    && !StringUtils.isEmpty(news.getProfile())
                    && news.getStmp() != null);
        }

        /*//Test findBySource
        list = newsMapper.findBySource("%" + source1 + "%");
        for (News news: list) {
            Assert.assertTrue(news != null
                    && StringUtils.countOccurrencesOf(news.getSource(), source1) > 0
                    && !StringUtils.isEmpty(news.getTitle())
                    && !StringUtils.isEmpty(news.getHref())
                    && !StringUtils.isEmpty(news.getProfile())
                    && news.getStmp() != null);
        }*/

        //Test findByStmp
        String beg = "2017-7-1";
        String end = "2017-7-5";
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
        list = newsMapper.findByStmp(beg, end);
        for (News news: list) {
            assertNewsNotEmpty(news);
            Timestamp stmp = news.getStmp();
            Assert.assertTrue(new Timestamp(sdft.parse(beg).getTime()).compareTo(stmp) <= 0
                    && new Timestamp(sdft.parse(end).getTime()).compareTo(stmp) >= 0);
        }
        //Test findByStmpTo
        list = newsMapper.findByStmpTo(end);
        for (News news: list) {
            assertNewsNotEmpty(news);
            Timestamp stmp = news.getStmp();
            Assert.assertTrue(new Timestamp(sdft.parse(end).getTime()).compareTo(stmp) >= 0);
        }
        //Test findByStmpFrom
        list = newsMapper.findByStmpFrom(beg);
        for (News news: list) {
            assertNewsNotEmpty(news);
            Timestamp stmp = news.getStmp();
            Assert.assertTrue(new Timestamp(sdft.parse(beg).getTime()).compareTo(stmp) <= 0);
        }

        //Test findToday
        final Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);
        today.set(Calendar.MILLISECOND, 999);   //today.23:59:59:999
        final Calendar preToday = Calendar.getInstance();
        list = newsMapper.findToday();
        for (News news: list) {
            assertNewsNotEmpty(news);
            Calendar newsDay = Calendar.getInstance();
            newsDay.setTimeInMillis(news.getStmp().getTime());
            Assert.assertTrue(newsDay.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                    && newsDay.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                    && newsDay.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH));
        }
        //Test findForDays
        int days = 3;
        preToday.add(Calendar.DAY_OF_MONTH, -days);    //日期转化为3天前 0:0:0:000
        preToday.set(Calendar.HOUR_OF_DAY, 0);
        preToday.set(Calendar.MINUTE, 0);
        preToday.set(Calendar.SECOND, 0);
        preToday.set(Calendar.MILLISECOND, 0);
        list = newsMapper.findForDays(days);
        for (News news: list) {
            assertNewsNotEmpty(news);
            Calendar newsDay = Calendar.getInstance();
            newsDay.setTimeInMillis(news.getStmp().getTime());
            Assert.assertTrue(newsDay.compareTo(preToday) >= 0
                    && newsDay.compareTo(today) <= 0);
        }
        //Test findMonth
        list = newsMapper.findMonth();
        for (News news: list) {
            assertNewsNotEmpty(news);
            Calendar newsDay = Calendar.getInstance();
            newsDay.setTimeInMillis(news.getStmp().getTime());
            Assert.assertTrue(newsDay.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                    && newsDay.get(Calendar.MONTH) == today.get(Calendar.MONTH));
        }
        //Test findStockRadioAll(int)
        int limit = 3;
        list = newsMapper.findAllByLimit(limit);
        Assert.assertTrue(list.size() == limit);
        for (News news: list) {
            assertNewsNotEmpty(news);
        }
        //Test findStockRadioAll
        list = newsMapper.findAll();
        for (News news: list) {
            assertNewsNotEmpty(news);
        }

        /*-----------------------测试 数据插入方法-----------------------*/
        expectNews = testNews;
        int res = newsMapper.insert(expectNews);
        Assert.assertTrue(res > 0);
        myNews = newsMapper.findByTitle("JT title1").get(0);
        assertNewsEqual(myNews, expectNews);
    }

    @Test
    @Rollback
    public void testNewsMapDelete() {
        News myNews;
        newsMapper.delete(1L);
        myNews = newsMapper.findById(1L);
        Assert.assertTrue(myNews == null);

        int total = newsMapper.count();
        int addSize = 1000;
        int subSize = 100;
        addSize += subSize;
        Calendar lastDay = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        lastDay.add(Calendar.DAY_OF_MONTH, -1);
        myNews = testNews;
        newsMapper.deleteFirstNews(total);  //先清空原来的记录，以便于测试
        for (int i = 0;i < addSize;i++) {
           Timestamp stmp;
           if (i < addSize - subSize) {
               stmp = new Timestamp(lastDay.getTimeInMillis());
           } else {
               stmp = new Timestamp(today.getTimeInMillis());
           }
           myNews.setStmp(stmp);
           newsMapper.insert(myNews);
        }

        total = newsMapper.count();
        int limit = addSize - subSize;
        if (total > limit) {
            if (newsMapper.countForDays(1) > limit) {
                newsMapper.deleteDaysAgo(1);
                Assert.assertTrue(newsMapper.count() == addSize);
            }

            if (newsMapper.countForDays(0) < limit) {
                int del = newsMapper.count() - limit;
                newsMapper.deleteFirstNews(del);
                Assert.assertTrue(newsMapper.count() == limit);
            }
        }

    }

    @Test
    @Rollback
    public void testPageHelper() {
        int pageNum = 1;
        int pageSize = 3;
        PageHelper.startPage(pageNum, pageSize);
        // list 类型转变为 com.github.pagehelper.Page, ArrayList的子类
        List<News> list = newsMapper.findAll();
        Assert.assertTrue(list.size() == pageSize);
        for (News news: list) {
            System.out.println(news);
            assertNewsNotEmpty(news);
        }
//        PageInfo<News> pageInfo = new PageInfo<>(list);
//        System.out.println(pageInfo);
    }

    public void assertNewsNotEmpty(News news) {
        Assert.assertNotNull(news);
        Assert.assertTrue(!StringUtils.isEmpty(news.getSource())
                && !StringUtils.isEmpty(news.getTitle())
                && !StringUtils.isEmpty(news.getHref())
                && !StringUtils.isEmpty(news.getProfile())
                && news.getStmp() != null);
    }

    public void assertNewsEqual(News actual, News expect) {
        Assert.assertNotNull(actual);
        Long expectId = expect.getId();
        if (expectId != null)
            Assert.assertEquals(expectId, actual.getId());
        Assert.assertEquals(expect.getSource(), actual.getSource());
        Assert.assertEquals(expect.getTitle(), actual.getTitle());
        Assert.assertEquals(expect.getHref(), actual.getHref());
        Assert.assertEquals(expect.getProfile(), actual.getProfile());
        Assert.assertEquals(expect.getStmp(), actual.getStmp());
    }

}