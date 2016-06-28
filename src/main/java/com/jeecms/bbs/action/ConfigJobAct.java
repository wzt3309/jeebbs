package com.jeecms.bbs.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.manager.CmsSiteMng;

public class ConfigJobAct {

	public void dayClear() {
		List<CmsSite> siteList = cmsSiteMng.getList();
		if (siteList != null && siteList.size() > 0) {
			for (CmsSite site : siteList) {
				bbsConfigMng.updateConfigForDay(site.getId());
			}
		}
	}

	@Autowired
	private BbsConfigMng bbsConfigMng;
	@Autowired
	private CmsSiteMng cmsSiteMng;
}
