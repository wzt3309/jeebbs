package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsPostTypeDao;
import com.jeecms.bbs.entity.BbsPostType;
import com.jeecms.bbs.manager.BbsPostTypeMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class BbsPostTypeMngImpl implements BbsPostTypeMng {

	@Transactional(readOnly = true)
	public Pagination getPage(Integer siteId,Integer forumId,Integer parentId,int pageNo, int pageSize) {
		Pagination page = dao.getPage(siteId,forumId,parentId,pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List getList(Integer siteId,Integer forumId,Integer parentId) {
		List list = dao.getList(siteId,forumId,parentId);
		return list;
	}

	@Transactional(readOnly = true)
	public BbsPostType findById(Integer id) {
		BbsPostType entity = dao.findById(id);
		return entity;
	}

	public BbsPostType save(BbsPostType bean) {
		dao.save(bean);
		return bean;
	}

	public BbsPostType update(BbsPostType bean) {
		Updater<BbsPostType> updater = new Updater<BbsPostType>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsPostType deleteById(Integer id) {
		BbsPostType bean = dao.deleteById(id);
		return bean;
	}

	public BbsPostType[] deleteByIds(Integer[] ids) {
		BbsPostType[] beans = new BbsPostType[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private BbsPostTypeDao dao;

	@Autowired
	public void setDao(BbsPostTypeDao dao) {
		this.dao = dao;
	}

}
