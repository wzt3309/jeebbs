package com.jeecms.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.entity.BbsMemberMagic;
import com.jeecms.bbs.dao.BbsMemberMagicDao;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsMagicLogDaoImpl extends
		HibernateBaseDao<BbsMemberMagic, Integer> implements BbsMemberMagicDao {
	public Pagination getPage(Integer userId, int pageNo, int pageSize) {
		String hql = "from BbsMemberMagic magic ";
		Finder finder = Finder.create(hql);
		Pagination page = find(finder, pageNo, pageSize);
		return page;
	}

	public BbsMemberMagic findById(Integer id) {
		BbsMemberMagic entity = get(id);
		return entity;
	}

	public BbsMemberMagic save(BbsMemberMagic bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsMemberMagic deleteById(Integer id) {
		BbsMemberMagic entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	protected Class<BbsMemberMagic> getEntityClass() {
		return BbsMemberMagic.class;
	}
}