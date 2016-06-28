package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsCategoryDao;
import com.jeecms.bbs.entity.BbsCategory;
import com.jeecms.bbs.manager.BbsCategoryMng;
import com.jeecms.common.hibernate3.Updater;

@Service
@Transactional
public class BbsCategoryMngImpl implements BbsCategoryMng {

	@Transactional(readOnly = true)
	public List<BbsCategory> getList(Integer siteId) {
		List<BbsCategory> list = dao.getList(siteId);
		return list;
	}

	@Transactional(readOnly = true)
	public BbsCategory findById(Integer id) {
		BbsCategory category = dao.findById(id);
		return category;
	}

	public BbsCategory save(BbsCategory bean) {
		dao.save(bean);
		return bean;
	}

	public BbsCategory update(BbsCategory bean) {
		Updater<BbsCategory> updater = new Updater<BbsCategory>(bean);
		BbsCategory entity = dao.updateByUpdater(updater);
		return entity;
	}

	public BbsCategory deleteById(Integer id) {
		BbsCategory bean = dao.deleteById(id);
		return bean;
	}

	public BbsCategory[] deleteByIds(Integer[] ids) {
		BbsCategory[] beans = new BbsCategory[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public BbsCategory[] updatePriority(Integer[] ids, Integer[] priority) {
		int len = ids.length;
		BbsCategory[] beans = new BbsCategory[len];
		for (int i = 0; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}

	private BbsCategoryDao dao;

	@Autowired
	public void setDao(BbsCategoryDao dao) {
		this.dao = dao;
	}
}
