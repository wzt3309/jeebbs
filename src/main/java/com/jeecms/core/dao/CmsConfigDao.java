package com.jeecms.core.dao;

import java.util.List;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.core.entity.CmsConfig;

public interface CmsConfigDao {
	public CmsConfig findById(Integer id);

	public CmsConfig updateByUpdater(Updater<CmsConfig> updater);

	public List<CmsConfig> getList();
}