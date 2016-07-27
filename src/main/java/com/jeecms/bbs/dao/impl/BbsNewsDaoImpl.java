package com.jeecms.bbs.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsNewsDao;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsNewsDaoImpl
	extends HibernateBaseDao<BbsNews, Integer> implements BbsNewsDao {
	
	@Override
	public Pagination getPage(Map<String,String> params,String orderBy,int pageNo, int pageSize) {
		Finder finder=Finder.create(" select bean from BbsNews bean ");
		finder.append(" where 1=1 ");
		if(params!=null&&!params.isEmpty()){
			String newsFrom=params.get("newsFrom");
			if(!StringUtils.isBlank(newsFrom)){
				finder.append(" and bean.NewsFrom =:newsFrom ");
				finder.setParam("newsFrom", newsFrom);				
			}
			String newsHref=params.get("newsHref");
			if(!StringUtils.isBlank(newsHref)){
				finder.append(" and bean.NewsHref like:newsHref ");
				finder.setParam("newsHref", newsHref);
			}
			String newsName=params.get("newsName");
			if(!StringUtils.isBlank(newsName)){
				finder.append(" and bean.NewsName like:newsName ");
				finder.setParam("newsName", newsName);
			}
			String newsDate=params.get("newsDate");
			if(!StringUtils.isBlank(newsDate)){
				finder.append(" and bean.NewsDate >=:newsDate ");
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				try {
					date = sdf.parse(newsDate);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					finder.setParam("newsDate", calendar);
				} catch (ParseException e) {
					log.error(e.getMessage(),e);
				}				
			}
		}
		if(orderBy!=null&&!"".equals(orderBy)){
			if("0".equals(orderBy)){
				finder.append(" order by bean.NewsFrom desc ");
			}else if("1".equals(orderBy)){
				finder.append(" order by bean.NewsName desc ");
			}
			finder.append(" and bean.NewsDate desc ");
		}else{
			finder.append(" order by bean.NewsDate desc ");
		}		
		
		return find(finder,pageNo,pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsNews> getList(String newsFrom) {
		String hql="select bean from BbsNews bean where bean.NewsFrom = ? order by bean.NewsDate desc";
		return find(hql,newsFrom);
	}

	@Override
	public BbsNews findById(Integer id) {
		BbsNews entity=get(id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized BbsNews save(BbsNews bean) {
		String hqlIsExist="select bean from BbsNews bean where bean.NewsFrom = ? and bean.NewsName = ?";
		List<BbsNews> exist=find(hqlIsExist,bean.getNewsFrom(),bean.getNewsName());
		if(exist==null||exist.size()<1){
			getSession().save(bean);
//			log.info("-----向Bbsnews插入："+bean.getNewsName()+"["+bean.getNewsHref()+"]");
//			System.out.println("-----向Bbsnews插入："+bean.getNewsName()+"["+bean.getNewsHref()+"]");
			return bean;
		}
//		log.info("-----news："+bean.getNewsName()+"已经存在");
//		System.out.println("-----news："+bean.getNewsName()+"已经存在");
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
