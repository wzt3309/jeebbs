package com.jeecms.bbs.manager;

import java.util.List;
import java.util.Map;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.common.page.Pagination;

public interface BbsNewsMng {
	/**
	 * 分页获取新闻
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(Map<String,String> params,String orderBy,int pageNo, int pageSize);
	/**
	 * 根据新闻来源获取新闻列表
	 * @param newsFrom
	 * @return
	 */
	public List<BbsNews> getList(String newsFrom);
	public BbsNews findById(Integer id);
	public BbsNews save(BbsNews bean);
	public BbsNews update(BbsNews bean);
	public BbsNews deleteById(Integer id);
	public BbsNews[] deleteByIds(Integer[] ids);
		
	
}
