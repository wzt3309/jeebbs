package jeebbs.restful.service.stockdata.mng;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jeebbs.restful.service.stockdata.model.StockDaily;
import jeebbs.restful.service.stockdata.model.StockTop;
import jeebbs.restful.service.stockdata.model.StockTrade;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import static org.junit.Assert.*;

/**
 * Created by ztwang on 2017/7/18 0018.
 */
public class TradingMngTest {
    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm:ss");
    @Test
    public void getHistoryData() throws Exception {
        String code = "600848";
        String start = "2017-1-1";
        String end = "2017-5-9";
        int pageNum = 1, pageSize = 10;
        String sort = "date";
        boolean asc = false;

        code = "";
        PageInfo<StockDaily> pageInfo = TradingMng.getHistoryData(code, start, end, pageNum, pageSize, sort, asc);
        System.out.println(pageInfo);
    }

    @Test
    public void getTodayAll() throws Exception {
        int pageNum = 1;
        int pageSize = 20;
        String sort = null;
        boolean asc = true;
        PageInfo<StockTrade> pageInfo = TradingMng.getTodayAll(pageNum, pageSize, sort, asc);
        System.out.println(pageInfo);
    }

    @Test
    public void searchCodeByName() throws Exception {
        String input = "";
        List list = TradingMng.searchCodeByName(input);
        System.out.println(list);
    }

    @Test
    public void getTodayAllNum() throws Exception {
        int total = TradingMng.getTodayAllNum();
        assertEquals(total, 80 * 40 + 34);
    }

    @Test
    public void getTopList() throws Exception {
        List<StockTop> list = TradingMng.getTopList(null);
        System.out.println(list);
    }

    /*@Test
    public void parseDayPriceJson() throws Exception {
        int pageNum = 1;
        int pageSize = 20;
        int total = TradingMng.getTodayAllNum();
        List<StockTrade> res = TradingMng.parseDayPriceJson(pageNum, pageSize, "code", true);

        Page<StockTrade> page = new Page<>(pageNum, pageSize);
        if (!CollectionUtils.isEmpty(res)) page.addAll(res);

        page.setTotal(total);
        page.setOrderBy("code asc");
        PageInfo<StockTrade> pageInfo = new PageInfo<>(page);
        System.out.println(pageInfo);
        System.out.println(res == null ? "null" : res.size());
    }

    @Test
    public void codeToSymbol() throws Exception {
        assertEquals(TradingMng.codeToSymbol("578111"), "sh578111");
        assertEquals(TradingMng.codeToSymbol("678111"), "sh678111");
        assertEquals(TradingMng.codeToSymbol("978111"), "sh978111");
        assertEquals(TradingMng.codeToSymbol("478111"), "sz478111");
    }

    @Test
    public void isHoliday() throws Exception {
        assertTrue(TradingMng.isHoliday("2016-04-04"));
        assertTrue(TradingMng.isHoliday("2016-04-03"));
        assertTrue(TradingMng.isHoliday("2016-04-02"));
        assertTrue(TradingMng.isHoliday("2017-09-09"));

        assertFalse(TradingMng.isHoliday("2017-04-28"));
        assertFalse(TradingMng.isHoliday("2018-04-28"));
    }

    @Test
    public void lastTradeDay() throws Exception {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(SDF_DATE.parse("2016-04-04"));
        String res = TradingMng.lastTradeDay(cal1);
        assertEquals(res, "2016-04-01");
    }*/
}