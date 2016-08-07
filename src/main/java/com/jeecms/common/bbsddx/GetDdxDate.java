package com.jeecms.common.bbsddx;

import java.util.List;

import com.jeecms.bbs.entity.Stockmessage;

public interface GetDdxDate {
	final static String URL_PREFIX="http://ddx.gubit.cn/xg/ddxlist.php";
	/**
	 * 获取当日股票ddx信息
	 * @return List<Stockmessage>
	 */
	public List<Stockmessage> getStockmessages();
	/**	 
	 * 获取指定日期股票ddx信息
	 * @param date date形如2016-07-08
	 * @return List<Stockmessage>
	 */
	public List<Stockmessage> getStockmessages(String date);
	/**
	 * 获取指定页面的ddx信息
	 * @param pageNo
	 * @return
	 */
	public List<Stockmessage> getStockmessages(int pageNo);
	/**
	 * 获取指定stockId的内容
	 * @param stockId
	 * @return
	 */
	public Stockmessage getStockmessage(String stockId);
	
}
