package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.common.page.Pagination;

public interface BbsNewsMng {
	
	public Pagination getNews(Integer Id, int pageNo, int pageSize);
	public List<BbsNews> getList(String sql);
		
	
}
