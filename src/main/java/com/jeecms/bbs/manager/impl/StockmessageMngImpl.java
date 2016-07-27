package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.StockmessageDao;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.bbs.manager.StockmessageMng;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class StockmessageMngImpl implements StockmessageMng{
	
	@Autowired
	private StockmessageDao dao;

	public Pagination getmess(String sql,int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		
		System.out.println("get in Mng ");
		return dao.getmess(sql,pageNo,pageSize);
	}
	@Override
	public List<Stockmessage> getlist(String sql){
		
		return dao.getlist(sql);
	}
	
	

}
