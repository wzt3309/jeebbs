package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.StockmessageDao;
import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class StockmessageDaoImpl extends HibernateBaseDao<Stockmessage, Integer> implements StockmessageDao{

		
	@Override
	public Pagination getmess(String sql,int pageNO,int pageSize) {		
		Finder f=null;
		if(StringUtils.isBlank(sql)){
			sql=" select bean from Stockmessage bean where 1=1 ";
		}
		f=Finder.create(sql);
		f.append(" order by bean.RIQI desc, bean.GPDM asc ");
		return find(f,pageNO,pageSize);
		
	}	
	public List<Stockmessage> getlist(String sql){
		
		return find(sql);
	}

	@Override
	protected Class<Stockmessage> getEntityClass() {
		// TODO Auto-generated method stub
		return Stockmessage.class;
	}

}
