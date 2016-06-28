package com.jeecms.bbs.cache;

import com.jeecms.bbs.entity.BbsUser;

public interface BbsConfigEhCache {
	
	public void setBbsConfigCache(int postToday, int topicTotal, int postTotal,
			int userTotal, BbsUser lastUser, Integer siteId);

	public BbsConfigCache getBbsConfigCache(Integer siteId);
}
