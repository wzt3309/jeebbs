package com.jeecms.bbs.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsMemberMagicDao;
import com.jeecms.bbs.entity.BbsMemberMagic;
import com.jeecms.bbs.manager.BbsMemberMagicMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class BbsMemberMagicMngImpl implements BbsMemberMagicMng {

	@Transactional(readOnly = true)
	public Pagination getPage(Integer userId, int pageNo, int pageSize) {
		Pagination page = dao.getPage(userId, pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public BbsMemberMagic findById(Integer id) {
		BbsMemberMagic entity = dao.findById(id);
		return entity;
	}

	public BbsMemberMagic save(BbsMemberMagic bean) {
		dao.save(bean);
		return bean;
	}

	public BbsMemberMagic update(BbsMemberMagic bean) {
		Updater<BbsMemberMagic> updater = new Updater<BbsMemberMagic>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsMemberMagic deleteById(Integer id) {
		BbsMemberMagic bean = dao.deleteById(id);
		return bean;
	}

	public BbsMemberMagic[] deleteByIds(Integer[] ids) {
		BbsMemberMagic[] beans = new BbsMemberMagic[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private BbsMemberMagicDao dao;

	@Autowired
	public void setDao(BbsMemberMagicDao dao) {
		this.dao = dao;
	}

}
