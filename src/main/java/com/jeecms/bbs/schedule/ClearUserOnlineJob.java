package com.jeecms.bbs.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsUserOnline;
import com.jeecms.bbs.manager.BbsUserOnlineMng;

public class ClearUserOnlineJob {
	private static final Logger log = LoggerFactory
			.getLogger(ClearUserOnlineJob.class);

	/**
	 * 日在线时长统计清零
	 */
	public void executeByDay() {
		List<BbsUserOnline> onlines = manager.getList();
		for (BbsUserOnline online : onlines) {
			online.setOnlineDay(0d);
			manager.update(online);
		}
		log.info("clear userOnline by day BbsUserOnline success!");
	}
	/**
	 * 周在线时长统计清零
	 */
	public void executeByWeek() {
		List<BbsUserOnline> onlines = manager.getList();
		for (BbsUserOnline online : onlines) {
			online.setOnlineWeek(0d);
			manager.update(online);
		}
		log.info("clear userOnline by week BbsUserOnline success!");
	}
	/**
	 * 月在线时长统计清零
	 */
	public void executeByMonth() {
		List<BbsUserOnline> onlines = manager.getList();
		for (BbsUserOnline online : onlines) {
			online.setOnlineMonth(0d);
			manager.update(online);
		}
		log.info("clear userOnline by month BbsUserOnline success!");
	}
	/**
	 * 年在线时长统计清零
	 */
	public void executeByYear() {
		List<BbsUserOnline> onlines = manager.getList();
		for (BbsUserOnline online : onlines) {
			online.setOnlineYear(0d);
			manager.update(online);
		}
		log.info("clear userOnline by year BbsUserOnline success!");
	}

	@Autowired
	private BbsUserOnlineMng manager;

}