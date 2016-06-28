package com.jeecms.bbs.manager;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jeecms.bbs.entity.BbsPost;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.common.page.Pagination;

public interface BbsPostMng {
	public BbsPost post(Integer userId, Integer siteId, Integer topicId,Integer postTypeId,
			String title, String content, String ip, List<MultipartFile> file,
			List<String> code);

	public BbsPost updatePost(Integer id, String title, String content,
			BbsUser editor, String ip, List<MultipartFile> file,
			List<String> code);
	public BbsPost updatePost(Integer id, String title, String content,
			BbsUser editor, String ip);

	public BbsPost shield(Integer id, String reason, BbsUser operator,Short status);

	public BbsPost reply(Integer userId, Integer siteId, Integer topicId,Integer postTypeId,
			String title, String content, String ip, List<MultipartFile> file,
			List<String> code);

	public List<BbsPost> getPostByTopic(Integer topicId);

	public Pagination getForTag(Integer siteId, Integer topicId,
			Integer userId, int pageNo, int pageSize);

	public Pagination getMemberReply(Integer webId, Integer memberId,
			int pageNo, int pageSize);

	public int getMemberReplyCount(Integer webId, Integer memberId);

	public BbsPost getLastPost(Integer forumId, Integer topicId);

	public int getIndexCount(Integer topicId);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsPost findById(Integer id);

	public BbsPost save(BbsPost bean);

	public BbsPost update(BbsPost bean);

	public BbsPost deleteById(Integer id);

	public BbsPost[] deleteByIds(Integer[] ids);
}