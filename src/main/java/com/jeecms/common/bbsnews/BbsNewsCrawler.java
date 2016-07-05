package com.jeecms.common.bbsnews;

import java.util.List;
import java.util.Map;

import com.jeecms.bbs.entity.BbsNews;
/**
 * 新闻抓取接口
 * @author wzt3309
 *
 */
public interface BbsNewsCrawler {

	public List<BbsNews> getTodayBbsNews();
	public String getNewsTopic();
	public String getNewsHref();
	public String getNewsAbstract();
	public List<Map<String,String>> getNewsMap();
}
