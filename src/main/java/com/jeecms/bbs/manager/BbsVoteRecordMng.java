package com.jeecms.bbs.manager;


import com.jeecms.bbs.entity.BbsVoteRecord;


public interface BbsVoteRecordMng {
	public BbsVoteRecord findRecord(Integer userId, Integer topicId);
	
	public BbsVoteRecord save(BbsVoteRecord bean);
}
