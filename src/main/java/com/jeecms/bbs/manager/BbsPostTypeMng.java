package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.BbsPostType;
import com.jeecms.common.page.Pagination;

public interface BbsPostTypeMng {
	public Pagination getPage(Integer siteId,Integer forumId,Integer parentId,int pageNo, int pageSize);
	
	public List getList(Integer siteId,Integer forumId,Integer parentId);

	public BbsPostType findById(Integer id);

	public BbsPostType save(BbsPostType bean);

	public BbsPostType update(BbsPostType bean);

	public BbsPostType deleteById(Integer id);

	public BbsPostType[] deleteByIds(Integer[] ids);
}