package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.common.page.Pagination;


public interface StockmessageDao {
	
	public Pagination getmess(String sql,int pageNO,int pageSize);
	public List<Stockmessage> getlist(String sql);
	public Stockmessage save(Stockmessage bean);
	public List getReccomendation_detail(String type,int pageNO,int pageSize);
	public Pagination getReccomendation_simp(String type,int pageNO,int pageSize);
	public Pagination getReccomendation_simp2(String type,int pageNO,int pageSize);
	public Stockmessage getStockmsgById(String id);
}
