package com.jeecms.bbs.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsConfigDao;
import com.jeecms.bbs.entity.BbsConfig;
import com.jeecms.common.hibernate3.HibernateBaseDao;

@Repository
public class BbsConfigDaoImpl extends HibernateBaseDao<BbsConfig, Integer> implements
		BbsConfigDao {

	public void clearTodayData() {
		// 最高发贴数
		String hql = "update BbsConfig bean set bean.postMax=bean.postToday, bean.postMaxDate=:currentDate where bean.postToday>bean.postMax";
		getSession().createQuery(hql).setParameter("currentDate", new Date())
				.executeUpdate();
		// 昨日发帖数
		hql = "update BbsConfig bean set bean.postYesterday=bean.postToday";
		getSession().createQuery(hql).executeUpdate();
		// 今日发帖数清零
		hql = "update BbsConfig bean set bean.postToday=0";
		getSession().createQuery(hql).executeUpdate();
	}
	
	public BbsConfig findById(Integer id) {
		BbsConfig entity = get(id);
		return entity;
	}
	
	public BbsConfig save(BbsConfig bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsConfig deleteById(Integer id) {
		BbsConfig entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsConfig> getEntityClass() {
		return BbsConfig.class;
	}
}