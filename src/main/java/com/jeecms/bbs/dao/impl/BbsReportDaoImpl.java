package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.entity.BbsReport;
import com.jeecms.bbs.dao.BbsReportDao;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsReportDaoImpl extends HibernateBaseDao<BbsReport, Integer>
		implements BbsReportDao {
	public Pagination getPage(Boolean status,Integer pageNo, Integer pageSize) {
		String hql = "from BbsReport report";
		Finder finder = Finder.create(hql);
		if(status!=null){
			if(status){
				finder.append(" where report.status=true");
			}else{
				finder.append(" where report.status=false");
			}
		}
		finder.append(" order by report.reportTime desc");
		Pagination page = find(finder, pageNo, pageSize);
		return page;
	}
	
	public BbsReport findByUrl(String url) {
		String hql = "from BbsReport report  where report.reportUrl=:url";
		List li= getSession().createQuery(hql).setParameter("url", url).list();
		if(li!=null&&li.size()>0){
			return (BbsReport) li.get(0);
		}else{
			return null;
		}
	}

	public BbsReport findById(Integer id) {
		BbsReport entity = get(id);
		return entity;
	}
	

	public BbsReport save(BbsReport bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsReport deleteById(Integer id) {
		BbsReport entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	protected Class<BbsReport> getEntityClass() {
		return BbsReport.class;
	}

	

}