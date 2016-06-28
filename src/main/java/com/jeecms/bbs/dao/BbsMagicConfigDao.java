package com.jeecms.bbs.dao;

import com.jeecms.bbs.entity.BbsMagicConfig;
import com.jeecms.common.hibernate3.Updater;

public interface BbsMagicConfigDao {
	
	public BbsMagicConfig findById(Integer id);

	public BbsMagicConfig save(BbsMagicConfig bean);

	public BbsMagicConfig updateByUpdater(Updater<BbsMagicConfig> updater);

	public BbsMagicConfig deleteById(Integer id);
}