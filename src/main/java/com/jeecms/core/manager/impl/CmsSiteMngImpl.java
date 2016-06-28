package com.jeecms.core.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.core.dao.CmsSiteDao;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.manager.FtpMng;

@Service
@Transactional
public class CmsSiteMngImpl implements CmsSiteMng {
//	private static final Logger log = LoggerFactory
//			.getLogger(CmsSiteMngImpl.class);

	@Transactional(readOnly = true)
	public List<CmsSite> getList() {
		return dao.getList(false);
	}

	@Transactional(readOnly = true)
	public List<CmsSite> getListFromCache() {
		return dao.getList(true);
	}

	@Transactional(readOnly = true)
	public CmsSite findByDomain(String domain, boolean cacheable) {
		return dao.findByDomain(domain, cacheable);
	}

	@Transactional(readOnly = true)
	public CmsSite findById(Integer id) {
		CmsSite entity = dao.findById(id);
		return entity;
	}

	public CmsSite update(CmsSite bean, Integer uploadFtpId) {
		CmsSite entity = findById(bean.getId());
		if (uploadFtpId != null) {
			entity.setUploadFtp(ftpMng.findById(uploadFtpId));
		} else {
			entity.setUploadFtp(null);
		}
		Updater<CmsSite> updater = new Updater<CmsSite>(bean);
		entity = dao.updateByUpdater(updater);
		return entity;
	}

	public void updateTplSolution(Integer siteId, String solution) {
		CmsSite site = findById(siteId);
		site.setTplSolution(solution);
	}

	private FtpMng ftpMng;
	private CmsSiteDao dao;

	@Autowired
	public void setFtpMng(FtpMng ftpMng) {
		this.ftpMng = ftpMng;
	}

	@Autowired
	public void setDao(CmsSiteDao dao) {
		this.dao = dao;
	}

}