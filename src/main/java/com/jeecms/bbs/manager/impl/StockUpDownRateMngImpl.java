package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.StockUpDownRateDao;
import com.jeecms.bbs.entity.StockUpDownRate;
import com.jeecms.bbs.manager.StockUpDownRateMng;
import com.jeecms.common.hibernate3.Updater;

/**
 * 股票涨跌、强弱指数的Mng接口
 * @author wzt3309
 *
 */
@Service
@Transactional
public class StockUpDownRateMngImpl implements StockUpDownRateMng {	


	private StockUpDownRateDao dao;	
	@Autowired
	public void setDao(StockUpDownRateDao dao) {
		this.dao = dao;
	}
	@Override
	public StockUpDownRate saveRate(StockUpDownRate bean) {
		dao.save(bean);
		return bean;
	}

	@Override
	public StockUpDownRate findRate(Integer id) {
		StockUpDownRate entity=dao.findById(id);
		return entity;
	}

	@Override
	public List<StockUpDownRate> findRateList(int day) {
		List<StockUpDownRate> list=dao.getList(day);
		return list;
	}

	@Override
	public StockUpDownRate deleteRateById(Integer id) {
		StockUpDownRate bean=dao.deleteById(id);
		return bean;
	}

	@Override
	public StockUpDownRate updateRate(StockUpDownRate bean) {
		Updater<StockUpDownRate> updater=new Updater<StockUpDownRate>(bean);
		bean=dao.updateByUpdater(updater);
		return bean;
	}

}
