package com.jeecms.bbs.dao;

import com.jeecms.bbs.entity.BbsConfig;
import com.jeecms.common.hibernate3.Updater;

public interface BbsConfigDao {
	/**
	 * 清理当日数据
	 */
	public void clearTodayData();
	
	public BbsConfig findById(Integer id);

	public BbsConfig save(BbsConfig bean);

	public BbsConfig updateByUpdater(Updater<BbsConfig> updater);

	public BbsConfig deleteById(Integer id);
}