package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.CmsSensitivityDao;
import com.jeecms.bbs.entity.CmsSensitivity;
import com.jeecms.common.hibernate3.HibernateBaseDao;

@Repository
public class CmsSensitivityDaoImpl extends
		HibernateBaseDao<CmsSensitivity, Integer> implements CmsSensitivityDao {
	@SuppressWarnings("unchecked")
	public List<CmsSensitivity> getList(Integer siteId, boolean cacheable) {
		String hql = "from CmsSensitivity bean where bean.site.id=:siteId order by bean.id desc";
		return getSession().createQuery(hql).setParameter("siteId", siteId).setCacheable(cacheable).list();
	}

	public CmsSensitivity findById(Integer id) {
		CmsSensitivity entity = get(id);
		return entity;
	}

	public CmsSensitivity save(CmsSensitivity bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsSensitivity deleteById(Integer id) {
		CmsSensitivity entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsSensitivity> getEntityClass() {
		return CmsSensitivity.class;
	}
}