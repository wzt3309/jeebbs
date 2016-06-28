package com.jeecms.bbs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jeecms.bbs.entity.BbsPost;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface BbsPostDao {
	/**
	 * 查询帖子
	 * 
	 * @param webId
	 *            站点ID
	 * @param topicId
	 *            主题ID
	 * @param memberId
	 *            只查看某个会员的帖子，为null则查看所有帖子
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getForTag(Integer webId, Integer topicId, Integer userId,
			int pageNo, int pageSize);

	/**
	 * 获取会员回复帖
	 * 
	 * @param webId
	 * @param memberId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Number> getMemberReply(Integer webId, Integer memberId,
			int pageNo, int pageSize);

	/**
	 * 获取回复帖数量
	 * 
	 * @param webId
	 * @param memberId
	 * @return
	 */
	public int getMemberReplyCount(Integer webId, Integer memberId);

	public List<BbsPost> getPostByTopic(Integer topicId);

	/**
	 * 获得第几楼
	 * 
	 * @param postId
	 *            帖子ID
	 * @param topicId
	 *            主题ID
	 * @return
	 */
	public int getIndexCount(Integer topicId);

	public BbsPost getLastPost(Integer forumId, Integer topicId);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsPost findById(Integer id);

	public BbsPost save(BbsPost bean);

	public BbsPost updateByUpdater(Updater<BbsPost> updater);

	public BbsPost deleteById(Integer id);
	
	public void deleleByForumId(Integer forumId);


}