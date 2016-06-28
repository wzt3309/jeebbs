package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.bbs.entity.reccomendstock;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface BbsTopicDao {
	/**
	 * 查找置顶贴
	 * 
	 * @param webId
	 * @param ctgId
	 * @param forumId
	 * @return
	 */
	public List<BbsTopic> getTopTopic(Integer webId, Integer ctgId,
			Integer forumId);

	/**
	 * 获得主题分页
	 * 
	 * @param webId
	 *            站点ID
	 * @param forumId
	 *            板块ID
	 * @param postTypeId
	 *            帖子子类ID
	 * @param parentPostTypeId
	 *            帖子父类ID           
	 * @param prime
	 *            是否精华
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getForTag(Integer siteId, Integer forumId,Integer parentPostTypeId, Integer postTypeId,Short status,
			Short primeLevel, String keyWords, String creater,
			Integer createrId, Short topLevel, int pageNo, int pageSize,String jinghua);

	/**
	 * 获取会员发表的主题
	 * 
	 * @param webId
	 * @param memberId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getMemberTopic(Integer webId, Integer memberId,
			int pageNo, int pageSize);

	/**
	 * 获取会员已回复的主题
	 * 
	 * @param webId
	 * @param memberId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getMemberReply(Integer webId, Integer memberId,
			int pageNo, int pageSize);

	/**
	 * 获取最近回复主题
	 * 
	 * @param webId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getTopicByTime(Integer webId, int pageNo, int pageSize);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsTopic findById(Integer id);

	public BbsTopic save(BbsTopic bean);

	public BbsTopic updateByUpdater(Updater<BbsTopic> updater);

	public BbsTopic deleteById(Integer id);

	public Pagination getForSearchDate(Integer siteId, Integer forumId,
			Short primeLevel, Integer day, int pageNo, int pageSize);

	public List<BbsTopic> getListByForumId(Integer forumId);
	
	public List<BbsTopic> getNewList(Integer count);
	
	//stock
	public List<Stockbasicmessage> getmess(String GPDM);
	public List<Stockbasicmessage> getlist(String sql);
	public List getGPMC();
	
	//webNews
	public List<BbsNews> getWebNews(Integer id);
	public List<BbsNews> getWebNewsList(String sql ,int limit);

	public List<Stockmessage> getReccomendation(String date,String type);

	public List<Stockmessage> getmessage(String sql);

	public List<Stockmessage> getReccomendation_detail(String type);

	public List<reccomendstock> getReccomendation_simp(String type);

	public List<reccomendstock> getReccomendation_simp2(String type);
}