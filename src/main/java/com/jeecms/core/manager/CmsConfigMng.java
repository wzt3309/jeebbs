package com.jeecms.core.manager;

import java.util.Date;

import com.jeecms.core.entity.CmsConfig;

public interface CmsConfigMng {
	public CmsConfig get();

	public void updateCountCopyTime(Date d);

	public void updateCountClearTime(Date d);

	public CmsConfig update(CmsConfig bean);
}