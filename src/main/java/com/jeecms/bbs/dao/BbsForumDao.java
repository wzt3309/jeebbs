package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface BbsForumDao {
	public Pagination getPage(int pageNo, int pageSize);

	/**
	 * 板块列表
	 * 
	 * 先按分区优先级排列，再按板块优先级排列
	 * 
	 * @param webId
	 * @return
	 */
	public List<BbsForum> getList(Integer siteId);

	/**
	 * 获得列表
	 * 
	 * 按板块优先级排列
	 * 
	 * @param webId
	 * @param categoryId
	 * @return
	 */
	public List<BbsForum> getList(Integer siteId, Integer categoryId);

	/**
	 * 板块路径
	 * 
	 * @param webId
	 * @param path
	 * @return
	 */
	public int countPath(Integer siteId, String path);

	public BbsForum getByPath(Integer siteId, String path);

	public BbsForum findById(Integer id);

	public BbsForum save(BbsForum bean);

	public BbsForum updateByUpdater(Updater<BbsForum> updater);

	public BbsForum deleteById(Integer id);

	public void updateAll_topic_today();
}