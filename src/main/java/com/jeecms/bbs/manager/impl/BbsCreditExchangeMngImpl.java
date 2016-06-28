package com.jeecms.bbs.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsCreditExchangeDao;
import com.jeecms.bbs.entity.BbsCreditExchange;
import com.jeecms.bbs.manager.BbsCreditExchangeMng;
import com.jeecms.common.hibernate3.Updater;

@Service
@Transactional
public class BbsCreditExchangeMngImpl implements BbsCreditExchangeMng {

	@Transactional(readOnly = true)
	public BbsCreditExchange findById(Integer id) {
		BbsCreditExchange config = dao.findById(id);
		return config;
	}

	public BbsCreditExchange update(BbsCreditExchange bean) {
		BbsCreditExchange entity = findById(bean.getId());
		Updater<BbsCreditExchange> updater = new Updater<BbsCreditExchange>(
				bean);
		entity = dao.updateByUpdater(updater);
		return entity;
	}

	private BbsCreditExchangeDao dao;

	@Autowired
	public void setDao(BbsCreditExchangeDao dao) {
		this.dao = dao;
	}

}
