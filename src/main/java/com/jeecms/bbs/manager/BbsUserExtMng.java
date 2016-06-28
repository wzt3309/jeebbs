package com.jeecms.bbs.manager;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserExt;

public interface BbsUserExtMng {
	public BbsUserExt save(BbsUserExt ext, BbsUser user);

	public BbsUserExt update(BbsUserExt ext, BbsUser user);
}