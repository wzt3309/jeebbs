package com.jeecms.bbs.manager.impl;

import java.util.List;

import com.jeecms.bbs.dao.impl.BbsNewsDaoImpl;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.manager.BbsNewsMng;
import com.jeecms.common.page.Pagination;

public class BbsNewsMngImpl implements BbsNewsMng {
	
	private BbsNewsDaoImpl dao;
	
	public BbsNewsMngImpl(){
		dao=new BbsNewsDaoImpl();
	}
	@Override
	public Pagination getNews(Integer Id, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		System.out.println("get in Mng ");
		return dao.getNews(Id,pageNo,pageSize);
	}

	@Override
	public List<BbsNews> getList(String sql) {
		// TODO Auto-generated method stub
		return dao.getList(sql);
	}

}
