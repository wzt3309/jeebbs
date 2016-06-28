package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.entity.BbsReportExt;
import com.jeecms.bbs.dao.BbsReportExtDao;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsReportExtDaoImpl extends
		HibernateBaseDao<BbsReportExt, Integer> implements BbsReportExtDao {
	public Pagination getPage(Integer reportId,Integer pageNo, Integer pageSize) {
		String hql = "from BbsReportExt reportext ";
		Finder finder = Finder.create(hql);
		if(reportId!=null){
			finder.append(" where reportext.report.id=:reportId").setParam("reportId",reportId);
		}
		finder.append(" order by reportext.reportTime asc");
		Pagination page = find(finder, pageNo, pageSize);
		return page;
	}
	
	public BbsReportExt findByReportUid(Integer reportId, Integer userId) {
		String hql = "from BbsReportExt ext  where ext.report.id=:reportId and ext.reportUser.id=:userId";
		List li= getSession().createQuery(hql).setParameter("reportId", reportId).setParameter("userId", userId).list();
		if(li!=null&&li.size()>0){
			return (BbsReportExt) li.get(0);
		}else{
			return null;
		}
	}

	public BbsReportExt findById(Integer id) {
		BbsReportExt entity = get(id);
		return entity;
	}

	public BbsReportExt save(BbsReportExt bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsReportExt deleteById(Integer id) {
		BbsReportExt entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	protected Class<BbsReportExt> getEntityClass() {
		return BbsReportExt.class;
	}

	

}