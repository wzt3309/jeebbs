package jeebbs.restful.service.stockdata.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ztwang on 2017/7/18 0018.
 */
public class StockDataConstantTest {

    @Test
    public void testConstruct() {
        assertNotNull(StockDataConstant.INDEX_SYMBOL);
        assertEquals(StockDataConstant.INDEX_SYMBOL.get("399990"), "sz399990");
    }
}