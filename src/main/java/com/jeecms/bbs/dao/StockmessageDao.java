package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.bbs.entity.Stockbasicmessage;


public interface StockmessageDao {
	
	public Pagination getmess(String GPDM,int pageNO,int pageSize);
	public List<Stockbasicmessage> getlist(String sql);

}
