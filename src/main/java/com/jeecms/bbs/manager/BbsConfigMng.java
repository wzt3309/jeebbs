package com.jeecms.bbs.manager;

import com.jeecms.bbs.entity.BbsConfig;

public interface BbsConfigMng {
	public BbsConfig findById(Integer id);

	public BbsConfig updateConfigForDay(Integer siteId);

	public BbsConfig update(BbsConfig bean);
}