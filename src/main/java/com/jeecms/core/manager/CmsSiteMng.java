package com.jeecms.core.manager;

import java.util.List;

import com.jeecms.core.entity.CmsSite;

public interface CmsSiteMng {
	public List<CmsSite> getList();

	public List<CmsSite> getListFromCache();

	public CmsSite findByDomain(String domain, boolean cacheable);

	public CmsSite findById(Integer id);

	public CmsSite update(CmsSite bean, Integer uploadFtpId);

	public void updateTplSolution(Integer siteId, String solution);
}