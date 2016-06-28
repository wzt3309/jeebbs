package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.BbsCategory;
import com.jeecms.common.hibernate3.Updater;

public interface BbsCategoryDao {
	/**
	 * 获得分区列表
	 * 
	 * @param webId
	 * @return
	 */
	public List<BbsCategory> getList(Integer siteId);

	/**
	 * 分区路径
	 * 
	 * @param webId
	 * @param path
	 * @return
	 */
	public int countPath(Integer siteId, String path);

	/**
	 * 通过路径获得栏目分区
	 * 
	 * @param webId
	 * @param path
	 * @return
	 */
	public BbsCategory getByPath(Integer siteId, String path);

	public BbsCategory findById(Integer id);

	public BbsCategory save(BbsCategory bean);

	public BbsCategory updateByUpdater(Updater<BbsCategory> updater);

	public BbsCategory deleteById(Integer id);
}