package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.StockUpDownRate;
/**
 * 股票涨跌、强弱指数的Mng接口
 * @author wzt3309
 *
 */
public interface StockUpDownRateMng {	
	//保存涨跌率
	public StockUpDownRate saveRate(StockUpDownRate bean);
	//找到涨跌率
	public StockUpDownRate findRate(Integer id);
	//找到涨跌率共day日集合
	public List<StockUpDownRate> findRateList(int day);
	//删除
	public StockUpDownRate deleteRateById(Integer id);
	public StockUpDownRate updateRate(StockUpDownRate bean);
}
