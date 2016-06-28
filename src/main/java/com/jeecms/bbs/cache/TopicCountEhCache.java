package com.jeecms.bbs.cache;

public interface TopicCountEhCache {

	public Long getViewCount(Integer topicId);

	public Long setViewCount(Integer topicId);

	public boolean getLastReply(Integer userId, long time);

}
