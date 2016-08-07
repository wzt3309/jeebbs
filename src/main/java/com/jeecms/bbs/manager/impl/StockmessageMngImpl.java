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
	@Override
	public Stockmessage save(Stockmessage bean) {
		dao.save(bean);
		return bean;
	}
	@Override
	public List getReccomendation_detail(String type,int pageNO,int pageSize) {
		// TODO Auto-generated method stub
		return dao.getReccomendation_detail(type, pageNO, pageSize);
	}

	@Override
	public Pagination getReccomendation_simp(String type,int pageNO,int pageSize) {
		// TODO Auto-generated method stub
		return dao.getReccomendation_simp(type, pageNO, pageSize);
	}

	@Override
	public Pagination getReccomendation_simp2(String type,int pageNO,int pageSize) {
		// TODO Auto-generated method stub
		return dao.getReccomendation_simp2(type, pageNO, pageSize);
	}
	

}
