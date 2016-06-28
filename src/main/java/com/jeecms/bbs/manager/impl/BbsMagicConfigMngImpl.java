package com.jeecms.bbs.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsMagicConfigDao;
import com.jeecms.bbs.entity.BbsMagicConfig;
import com.jeecms.bbs.manager.BbsMagicConfigMng;
import com.jeecms.common.hibernate3.Updater;

@Service
@Transactional
public class BbsMagicConfigMngImpl implements BbsMagicConfigMng {

	@Transactional(readOnly = true)
	public BbsMagicConfig findById(Integer id) {
		BbsMagicConfig config = dao.findById(id);
		return config;
	}

	public BbsMagicConfig update(BbsMagicConfig bean) {
		BbsMagicConfig entity = findById(bean.getId());
		Updater<BbsMagicConfig> updater = new Updater<BbsMagicConfig>(bean);
		entity = dao.updateByUpdater(updater);
		return entity;
	}

	private BbsMagicConfigDao dao;

	@Autowired
	public void setDao(BbsMagicConfigDao dao) {
		this.dao = dao;
	}

}
