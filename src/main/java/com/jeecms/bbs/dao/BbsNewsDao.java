package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface BbsNewsDao {

	/**
	 * 分页获取新闻
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(int pageNo, int pageSize);
	/**
	 * 根据新闻来源获取新闻列表
	 * @param newsFrom
	 * @return
	 */
	public List<BbsNews> getList(String newsFrom);
	public BbsNews findById(Integer id);
	public BbsNews save(BbsNews bean);	
	public BbsNews updateByUpdater(Updater<BbsNews> updater);
	public BbsNews deleteById(Integer id);
	
}
