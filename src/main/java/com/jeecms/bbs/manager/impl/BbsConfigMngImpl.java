package com.jeecms.bbs.manager.impl;

import java.util.List;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.cache.BbsConfigCache;
import com.jeecms.bbs.dao.BbsConfigDao;
import com.jeecms.bbs.entity.BbsConfig;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.bbs.manager.BbsForumMng;
import com.jeecms.common.hibernate3.Updater;

@Service
@Transactional
public class BbsConfigMngImpl implements BbsConfigMng {

	@Transactional(readOnly = true)
	public BbsConfig findById(Integer id) {
		BbsConfig config = dao.findById(id);
		return config;
	}

	public BbsConfig update(BbsConfig bean) {
		BbsConfig entity = findById(bean.getId());
		Updater<BbsConfig> updater = new Updater<BbsConfig>(bean);
		entity = dao.updateByUpdater(updater);
		return entity;
	}

	public BbsConfig updateConfigForDay(Integer siteId) {
		List<BbsForum> flist = bbsForumMng.getList(siteId);
		for (BbsForum forum : flist) {
			forum.setPostToday(0);
			bbsForumMng.update(forum);
		}
		BbsConfig bbsConfig = dao.findById(siteId);
		Element e = cache.get(siteId);
		if (e != null) {
			BbsConfigCache configCache = (BbsConfigCache) e.getValue();
			bbsConfig.setLastUser(configCache.getLastUser());
			bbsConfig.setPostMax(configCache.getPostMax());
			bbsConfig.setPostMaxDate(configCache.getPostMaxDate());
			bbsConfig.setPostToday(0);
			bbsConfig.setPostTotal(configCache.getPostTotal());
			bbsConfig.setPostYesterday(configCache.getPostToday());
			bbsConfig.setTopicTotal(configCache.getTopicTotal());
			bbsConfig.setUserTotal(configCache.getUserTotal());
			configCache.setPostYestoday(configCache.getPostToday());
			configCache.setPostToday(0);
			cache.put(new Element(siteId, configCache));
		}
		update(bbsConfig);
		return bbsConfig;
	}

	private BbsConfigDao dao;
	private Ehcache cache;
	private BbsForumMng bbsForumMng;

	@Autowired
	public void setCache(@Qualifier("bbsconfigCount") Ehcache cache) {
		this.cache = cache;
	}

	@Autowired
	public void setDao(BbsConfigDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setBbsForumMng(BbsForumMng bbsForumMng) {
		this.bbsForumMng = bbsForumMng;
	}

}
