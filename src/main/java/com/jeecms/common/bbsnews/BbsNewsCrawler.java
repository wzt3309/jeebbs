package com.jeecms.common.bbsnews;

import java.util.List;

import com.jeecms.bbs.entity.BbsNews;
/**
 * 新闻抓取接口
 * @author wzt3309
 *
 */
public interface BbsNewsCrawler {

	public List<BbsNews> getTodayBbsNews();
}
