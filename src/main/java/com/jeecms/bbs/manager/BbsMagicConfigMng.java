package com.jeecms.bbs.manager;

import com.jeecms.bbs.entity.BbsMagicConfig;

public interface BbsMagicConfigMng {

	public BbsMagicConfig findById(Integer id);

	public BbsMagicConfig update(BbsMagicConfig bean);

}