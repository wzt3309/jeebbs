package com.jeecms.bbs.dao.impl;

import com.jeecms.bbs.dao.StockmessageDao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.StockmessageDao;
import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

public class StockmessageDaoImpl extends HibernateBaseDao<Stockbasicmessage, Integer> implements StockmessageDao{

	
	public StockmessageDaoImpl(){
		
	}
	@Override
	public Pagination getmess(String GPDM,int pageNO,int pageSize) {
		// TODO Auto-generated method stub
		
		System.out.println("get in Dao");
		Finder f = Finder.create("select bean from Stockbasicmessage bean where 1=1");
		
		System.out.println("successed in creation");
		
		if(GPDM!=null){
			f.append(" and bean.GPDM=:GPDM").setParam("GPDM", GPDM);
			
			System.out.println("successed in setParam");
		}
		
		//System.out.println("sql:"+f.getOrigHql());
		
		return find(f,pageNO,pageSize);
	}
	
public List<Stockbasicmessage> getlist(String sql){
		
		return find(sql);
	}

	@Override
	protected Class<Stockbasicmessage> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
