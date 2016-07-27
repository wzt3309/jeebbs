package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.common.page.Pagination;


public interface StockmessageDao {
	
	public Pagination getmess(String sql,int pageNO,int pageSize);
	public List<Stockmessage> getlist(String sql);

}
