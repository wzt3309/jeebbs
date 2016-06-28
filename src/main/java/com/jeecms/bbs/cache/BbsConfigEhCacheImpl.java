package com.jeecms.bbs.cache;

import java.util.Date;
import java.util.List;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jeecms.bbs.entity.BbsConfig;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsConfigMng;

@Service
public class BbsConfigEhCacheImpl implements BbsConfigEhCache, DisposableBean {

	public void setBbsConfigCache(int postToday, int topicTotal, int postTotal,
			int userTotal, BbsUser lastUser, Integer siteId) {
		BbsConfigCache configCache = new BbsConfigCache();
		Element e = cache.get(siteId);
		if (e != null) {
			configCache = (BbsConfigCache) e.getObjectValue();
			configCache.setPostToday(configCache.getPostToday() + postToday);
			configCache.setTopicTotal(configCache.getTopicTotal() + topicTotal);
			if (configCache.getPostMax() < configCache.getPostToday()) {
				configCache.setPostMax(configCache.getPostToday());
				configCache.setPostMaxDate(new Date());
			}
			configCache.setPostTotal(configCache.getPostTotal() + postTotal);
			configCache.setUserTotal(configCache.getUserTotal() + userTotal);
			if (lastUser != null) {
				configCache.setLastUser(lastUser);
			}
			cache.put(new Element(siteId, configCache));
		} else {
			BbsConfig bbsConfig = bbsConfigMng.findById(siteId);
			if (bbsConfig.getLastUser() != null) {
				configCache.setLastUser(bbsConfig.getLastUser());
			} else {
				configCache.setLastUser(lastUser);
			}
			if (bbsConfig.getPostMax() != null) {
				configCache.setPostMax(bbsConfig.getPostMax());
			} else {
				configCache.setPostMax(1);
			}
			if (bbsConfig.getPostMaxDate() != null) {
				configCache.setPostMaxDate(bbsConfig.getPostMaxDate());
			} else {
				configCache.setPostMaxDate(new Date());
			}
			if (bbsConfig.getPostToday() != null) {
				configCache.setPostToday(bbsConfig.getPostToday());
			} else {
				configCache.setPostToday(1);
			}
			if (bbsConfig.getPostTotal() != null) {
				configCache.setPostTotal(bbsConfig.getPostTotal());
			} else {
				configCache.setPostTotal(1);
			}
			if (bbsConfig.getPostYesterday() != null) {
				configCache.setPostYestoday(bbsConfig.getPostYesterday());
			} else {
				configCache.setPostYestoday(0);
			}
			if (bbsConfig.getTopicTotal() != null) {
				configCache.setTopicTotal(bbsConfig.getTopicTotal());
			} else {
				configCache.setTopicTotal(1);
			}
			if (bbsConfig.getUserTotal() != null) {
				configCache.setUserTotal(bbsConfig.getUserTotal());
			} else {
				configCache.setUserTotal(1);
			}
			cache.put(new Element(siteId, configCache));
		}
		refreshToDB(siteId);
	}

	public BbsConfigCache getBbsConfigCache(Integer siteId) {
		Element e = cache.get(siteId);
		if (e != null) {
			BbsConfigCache configCache = (BbsConfigCache) e.getValue();
			return configCache;
		} else {
			BbsConfig bbsConfig = bbsConfigMng.findById(siteId);
			BbsConfigCache configCache = new BbsConfigCache();
			if (bbsConfig.getLastUser() != null) {
				configCache.setLastUser(bbsConfig.getLastUser());
			} else {
				configCache.setLastUser(null);
			}
			if (bbsConfig.getPostMax() != null) {
				configCache.setPostMax(bbsConfig.getPostMax());
			} else {
				configCache.setPostMax(1);
			}
			if (bbsConfig.getPostMaxDate() != null) {
				configCache.setPostMaxDate(bbsConfig.getPostMaxDate());
			} else {
				configCache.setPostMaxDate(new Date());
			}
			if (bbsConfig.getPostToday() != null) {
				configCache.setPostToday(bbsConfig.getPostToday());
			} else {
				configCache.setPostToday(1);
			}
			if (bbsConfig.getPostTotal() != null) {
				configCache.setPostTotal(bbsConfig.getPostTotal());
			} else {
				configCache.setPostTotal(1);
			}
			if (bbsConfig.getPostYesterday() != null) {
				configCache.setPostYestoday(bbsConfig.getPostYesterday());
			} else {
				configCache.setPostYestoday(0);
			}
			if (bbsConfig.getTopicTotal() != null) {
				configCache.setTopicTotal(bbsConfig.getTopicTotal());
			} else {
				configCache.setTopicTotal(1);
			}
			if (bbsConfig.getUserTotal() != null) {
				configCache.setUserTotal(bbsConfig.getUserTotal());
			} else {
				configCache.setUserTotal(1);
			}
			cache.put(new Element(siteId, configCache));
			return configCache;
		}
	}

	private void refreshToDB(Integer siteId) {
		long time = System.currentTimeMillis();
		if (time > refreshTime + interval) {
			refreshTime = time;
			BbsConfig bbsConfig = bbsConfigMng.findById(siteId);
			Element e = cache.get(siteId);
			BbsConfigCache configCache = (BbsConfigCache) e.getValue();
			bbsConfig.setLastUser(configCache.getLastUser());
			bbsConfig.setPostMax(configCache.getPostMax());
			bbsConfig.setPostMaxDate(configCache.getPostMaxDate());
			bbsConfig.setPostToday(configCache.getPostToday());
			bbsConfig.setPostTotal(configCache.getPostTotal());
			bbsConfig.setPostYesterday(configCache.getPostYestoday());
			bbsConfig.setTopicTotal(configCache.getTopicTotal());
			bbsConfig.setUserTotal(configCache.getUserTotal());
			bbsConfigMng.update(bbsConfig);
		}
	}

	/**
	 * 销毁BEAN时，缓存入库。
	 */
	@SuppressWarnings("unchecked")
	public void destroy() throws Exception {
		List<Integer> keys = cache.getKeys();
		for (Integer siteId : keys) {
			refreshToDB(siteId);
		}
	}

	// 间隔时间
	private int interval = 60 * 60 * 1000; // 1个小时
	// 最后刷新时间
	private long refreshTime = System.currentTimeMillis();
	private BbsConfigMng bbsConfigMng;
	private Ehcache cache;

	@Autowired
	public void setCache(@Qualifier("bbsconfigCount") Ehcache cache) {
		this.cache = cache;
	}

	@Autowired
	public void setBbsConfigMng(BbsConfigMng bbsConfigMng) {
		this.bbsConfigMng = bbsConfigMng;
	}

}
