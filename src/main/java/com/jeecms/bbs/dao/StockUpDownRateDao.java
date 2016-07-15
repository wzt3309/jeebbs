package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.StockUpDownRate;
import com.jeecms.common.hibernate3.Updater;
/**
 * @ClassName StockUpDownRateDao
 * @Description 股票涨跌及强弱比的DAO接口
 * @author wzt3309 *
 */
public interface StockUpDownRateDao {
	//根据日期获得5日内的涨跌率
	public List<StockUpDownRate> getList(int day);
	//根据自增id获得涨跌率
	public StockUpDownRate findById(Integer id);
	//保存新的涨跌率
	public StockUpDownRate save(StockUpDownRate bean);	
	//删除
	public StockUpDownRate deleteById(Integer id);
	public StockUpDownRate updateByUpdater(Updater<StockUpDownRate> updater);
}
