package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsNewsDao;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsNewsDaoImpl
	extends HibernateBaseDao<BbsNews, Integer> implements BbsNewsDao {
	
	@Override
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsNews> getList(String newsFrom) {
		String hql="select bean from BbsNews bean where bean.NewsFrom = ? order by bean.NewsDate";
		return find(hql,newsFrom);
	}

	@Override
	public BbsNews findById(Integer id) {
		BbsNews entity=get(id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BbsNews save(BbsNews bean) {
		String hqlIsExist="select bean from BbsNews bean where bean.NewsFrom = ? and bean.NewsName = ?";
		List<BbsNews> exist=find(hqlIsExist,bean.getNewsFrom(),bean.getNewsName());
		if(exist==null||exist.size()<1){
			getSession().save(bean);
			return bean;
		}		
		return null;
	}
	@Override
	public BbsNews deleteById(Integer id) {
		BbsNews entity=super.get(id);
		if(entity!=null){
			getSession().delete(entity);
		}
		return entity;
	}	

	@Override
	protected Class<BbsNews> getEntityClass() {		
		return BbsNews.class;
	}
	
	

}
