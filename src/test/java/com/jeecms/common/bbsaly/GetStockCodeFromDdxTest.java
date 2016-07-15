package com.jeecms.common.bbsaly;

import java.util.List;

import org.junit.Test;

import com.jeecms.common.bbsaly.GetStockCodeFromDdx.StockCode;

public class GetStockCodeFromDdxTest {

	@Test
	public void test() {
		
		List<StockCode> stockCodeList=GetStockCodeFromDdx.getStockCodeList();
		for(StockCode sc:stockCodeList){
			 System.out.println(sc.getStockCode()+":"+sc.getStockName());
		 }
		
	}

}
