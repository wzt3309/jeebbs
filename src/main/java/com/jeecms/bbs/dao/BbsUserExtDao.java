package com.jeecms.bbs.dao;

import com.jeecms.bbs.entity.BbsUserExt;
import com.jeecms.common.hibernate3.Updater;

public interface BbsUserExtDao {
	public BbsUserExt findById(Integer id);

	public BbsUserExt save(BbsUserExt bean);

	public BbsUserExt updateByUpdater(Updater<BbsUserExt> updater);
}