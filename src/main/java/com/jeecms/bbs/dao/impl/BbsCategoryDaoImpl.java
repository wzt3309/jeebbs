package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsCategoryDao;
import com.jeecms.bbs.entity.BbsCategory;
import com.jeecms.common.hibernate3.HibernateBaseDao;

@Repository
public class BbsCategoryDaoImpl extends HibernateBaseDao<BbsCategory, Integer> implements
		BbsCategoryDao {
	@SuppressWarnings("unchecked")
	public List<BbsCategory> getList(Integer webId) {
		String hql = "select bean from BbsCategory bean where bean.site.id=? order by bean.priority";
		return find(hql, webId);
	}

	public int countPath(Integer webId, String path) {
		String hql = "select count(*) from BbsCategory bean where bean.site.id=? and bean.path=?";
		return ((Number) getSession().createQuery(hql).setParameter(0, webId)
				.setParameter(1, path).iterate().next()).intValue();
	}

	public BbsCategory getByPath(Integer webId, String path) {
		String hql = "select bean from BbsCategory bean where bean.site.id=? and bean.path=?";
		return (BbsCategory) findUnique(hql, webId, path);
	}
	
	public BbsCategory findById(Integer id) {
		BbsCategory entity = get(id);
		return entity;
	}
	
	public BbsCategory save(BbsCategory bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsCategory deleteById(Integer id) {
		BbsCategory entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsCategory> getEntityClass() {
		return BbsCategory.class;
	}
}