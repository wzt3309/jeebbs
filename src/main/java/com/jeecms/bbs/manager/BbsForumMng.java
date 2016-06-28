package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;

public interface BbsForumMng {
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

	public BbsForum save(BbsForum bean, Integer categoryId, CmsSite site,
			Integer[] views, Integer[] topics, Integer[] replies);

	public BbsForum update(BbsForum bean, Integer categoryId, CmsSite site,
			Integer[] views, Integer[] topics, Integer[] replies);

	public BbsForum deleteById(Integer id);

	public BbsForum update(BbsForum bean);

	public BbsForum[] deleteByIds(Integer[] ids);
	
	public void updateAll_topic_today();
	
	public String getModerators(Integer siteId);
}