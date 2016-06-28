package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsForumDao;
import com.jeecms.bbs.entity.BbsCategory;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.manager.BbsCategoryMng;
import com.jeecms.bbs.manager.BbsForumMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;

@Service
@Transactional
public class BbsForumMngImpl implements BbsForumMng {

	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public List<BbsForum> getList(Integer siteId) {
		return dao.getList(siteId);
	}

	@Transactional(readOnly = true)
	public List<BbsForum> getList(Integer siteId, Integer categoryId) {
		return dao.getList(siteId, categoryId);
	}

	@Transactional(readOnly = true)
	public int countPath(Integer siteId, String path) {
		return dao.countPath(siteId, path);
	}

	@Transactional(readOnly = true)
	public BbsForum getByPath(Integer siteId, String path) {
		return dao.getByPath(siteId, path);
	}

	@Transactional(readOnly = true)
	public BbsForum findById(Integer id) {
		BbsForum entity = dao.findById(id);
		return entity;
	}

	public BbsForum save(BbsForum bean, Integer categoryId, CmsSite site,
			Integer[] views, Integer[] topics, Integer[] replies) {
		BbsCategory category = bbsCategoryMng.findById(categoryId);
		bean.setCategory(category);
		bean.setSite(site);
		if (views != null && views.length > 0) {
			String v = ",";
			for (Integer vgroupId : views) {
				v = v + vgroupId + ",";
			}
			bean.setGroupViews(v);
		}
		if (topics != null && topics.length > 0) {
			String t = ",";
			for (Integer tgroupId : topics) {
				t = t + tgroupId + ",";
			}
			bean.setGroupTopics(t);
		}
		if (replies != null && replies.length > 0) {
			String r = ",";
			for (Integer rgroupId : replies) {
				r = r + rgroupId + ",";
			}
			bean.setGroupReplies(r);
		}
		bean.init();
		dao.save(bean);
		return bean;
	}

	public BbsForum save(BbsForum bean) {
		dao.save(bean);
		return bean;
	}

	public BbsForum update(BbsForum bean) {
		Updater<BbsForum> updater = new Updater<BbsForum>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsForum update(BbsForum bean, Integer categoryId, CmsSite site,
			Integer[] views, Integer[] topics, Integer[] replies) {
		Updater<BbsForum> updater = new Updater<BbsForum>(bean);
		bean = dao.updateByUpdater(updater);
		BbsCategory category = bbsCategoryMng.findById(categoryId);
		bean.setCategory(category);
		bean.setSite(site);
		if (views != null && views.length > 0) {
			String v = ",";
			for (Integer vgroupId : views) {
				v = v + vgroupId + ",";
			}
			bean.setGroupViews(v);
		} else {
			bean.setGroupViews("");
		}
		if (topics != null && topics.length > 0) {
			String t = ",";
			for (Integer tgroupId : topics) {
				t = t + tgroupId + ",";
			}
			bean.setGroupTopics(t);
		} else {
			bean.setGroupTopics("");
		}
		if (replies != null && replies.length > 0) {
			String r = ",";
			for (Integer rgroupId : replies) {
				r = r + rgroupId + ",";
			}
			bean.setGroupReplies(r);
		} else {
			bean.setGroupReplies("");
		}
		bean.init();
		return bean;
	}

	public BbsForum deleteById(Integer id) {
		BbsForum bean = dao.deleteById(id);
		return bean;
	}

	public BbsForum[] deleteByIds(Integer[] ids) {
		BbsForum[] beans = new BbsForum[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public String getModerators(Integer siteId){
		List<BbsForum>forums=getList(siteId);
		StringBuffer moderatorsBuf=new StringBuffer();
		for(BbsForum forum:forums){
			moderatorsBuf.append(forum.getModerators());
		}
		return moderatorsBuf.toString();
	}

	private BbsCategoryMng bbsCategoryMng;
	private BbsForumDao dao;

	@Autowired
	public void setDao(BbsForumDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setBbsCategoryMng(BbsCategoryMng bbsCategoryMng) {
		this.bbsCategoryMng = bbsCategoryMng;
	}

	public void updateAll_topic_today() {
		// TODO Auto-generated method stub
		dao.updateAll_topic_today();
	}
}