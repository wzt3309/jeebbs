package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.CmsSensitivityDao;
import com.jeecms.bbs.entity.CmsSensitivity;
import com.jeecms.bbs.manager.CmsSensitivityMng;
import com.jeecms.core.manager.CmsSiteMng;

@Service
@Transactional
public class CmsSensitivityMngImpl implements CmsSensitivityMng {
	@Transactional(readOnly = true)
	public String replaceSensitivity(Integer siteId, String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		List<CmsSensitivity> list = getList(siteId, true);
		for (CmsSensitivity sen : list) {
			s = StringUtils.replace(s, sen.getSearch(), sen.getReplacement());
		}
		return s;
	}

	@Transactional(readOnly = true)
	public List<CmsSensitivity> getList(Integer siteId, boolean cacheable) {
		return dao.getList(siteId, cacheable);
	}

	@Transactional(readOnly = true)
	public CmsSensitivity findById(Integer id) {
		CmsSensitivity entity = dao.findById(id);
		return entity;
	}

	public CmsSensitivity save(CmsSensitivity bean, Integer siteId) {
		bean.setSite(cmsSiteMng.findById(siteId));
		dao.save(bean);
		return bean;
	}

	public void updateEnsitivity(Integer[] ids, String[] searchs,
			String[] replacements) {
		CmsSensitivity ensitivity;
		for (int i = 0, len = ids.length; i < len; i++) {
			ensitivity = findById(ids[i]);
			ensitivity.setSearch(searchs[i]);
			ensitivity.setReplacement(replacements[i]);
		}
	}

	public CmsSensitivity deleteById(Integer id) {
		CmsSensitivity bean = dao.deleteById(id);
		return bean;
	}

	public CmsSensitivity[] deleteByIds(Integer[] ids) {
		CmsSensitivity[] beans = new CmsSensitivity[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private CmsSensitivityDao dao;
	
	private CmsSiteMng cmsSiteMng;

	@Autowired
	public void setDao(CmsSensitivityDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
		this.cmsSiteMng = cmsSiteMng;
	}
	
	
}