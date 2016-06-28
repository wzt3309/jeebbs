package com.jeecms.bbs.manager;

import com.jeecms.bbs.entity.BbsGrade;
import com.jeecms.bbs.entity.BbsPost;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.common.page.Pagination;

public interface BbsGradeMng {
	public BbsGrade saveGrade(BbsGrade entity, BbsUser bbsuser, BbsPost post);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsGrade findById(Integer id);

	public BbsGrade save(BbsGrade bean);

	public BbsGrade update(BbsGrade bean);

	public BbsGrade deleteById(Integer id);

	public BbsGrade[] deleteByIds(Integer[] ids);
}