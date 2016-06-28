package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.common.page.Pagination;

public interface BbsNewsDao {

	public Pagination getNews(Integer Id,int pageNO,int pageSize);
	public List<BbsNews> getList(String sql);
}
