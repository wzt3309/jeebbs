package jeebbs.restful.service.stockdata.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by ztwang on 2017/7/20 0020.
 */
public class StockUtilTest {
    @Test
    public void getComparator() throws Exception {
        String sort = "code";
        boolean asc = false;
        Comparator<StockDaily> comparator = StockUtil.getComparator("high", StockDaily.class);
        List<StockDaily> list = new ArrayList<>();
        StockDaily o1 = new StockDaily();
        StockDaily o2 = new StockDaily();
        StockDaily o3 = new StockDaily();
        o1.setCode("000001");
        o1.setHigh(new BigDecimal("100"));
        o2.setCode("000002");
        o2.setHigh(new BigDecimal("150"));
        o3.setCode("000003");
        o3.setHigh(new BigDecimal("110"));
        list.add(o1);
        list.add(o3);
        list.add(o2);
        System.out.println(list);
        Collections.sort(list, comparator);
        System.out.println(list);
        if (!asc)
            Collections.reverse(list);
        System.out.println(list);
    }

}