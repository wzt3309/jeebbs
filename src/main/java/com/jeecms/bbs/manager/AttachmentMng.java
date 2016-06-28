package com.jeecms.bbs.manager;

import com.jeecms.common.page.Pagination;
import com.jeecms.bbs.entity.Attachment;

public interface AttachmentMng {
	public Pagination getPage(int pageNo, int pageSize);

	public Attachment findById(Integer id);

	public Attachment save(Attachment bean);

	public Attachment update(Attachment bean);

	public Attachment deleteById(Integer id);
	
	public Attachment[] deleteByIds(Integer[] ids);
}