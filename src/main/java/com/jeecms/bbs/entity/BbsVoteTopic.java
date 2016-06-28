package com.jeecms.bbs.entity;

/**
 * 
 * 投票贴
 * 
 */
public class BbsVoteTopic extends BbsTopic {
	/**
	 * 总票数
	 */
	private Integer totalCount;

	public void init() {
		super.init();
		if (totalCount == null) {
			totalCount = 0;
		}
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getCategory() {
		return TOPIC_VOTE;
	}
}
