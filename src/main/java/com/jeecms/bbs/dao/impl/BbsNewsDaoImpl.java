package com.jeecms.bbs.dao.impl;

import java.util.List;

import com.jeecms.bbs.dao.BbsNewsDao;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

public class BbsNewsDaoImpl
extends HibernateBaseDao<BbsNews, Integer> implements BbsNewsDao {
	
	public BbsNewsDaoImpl(){
		
	}
	@Override
	public Pagination getNews(Integer Id, int pageNO, int pageSize) {
		// TODO Auto-generated method stub
		//System.out.println("get in Dao");
		
		Finder f=Finder.create("select bean from BbsNews bean where 1=1");
		
		//System.out.println("successed in creation");
		
		if(Id!=null){
			f.append("and bean.Id=:Id").setParam("Id", Id);
			System.out.println("successed in setParam");
		}
		//System.out.println("sql:"+f.getOrigHql());
		
		return find(f,pageNO,pageSize);
	}

	@Override
	public List<BbsNews> getList(String sql) {
		// TODO Auto-generated method stub
		return find(sql);
	}

	@Override
	protected Class<BbsNews> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
