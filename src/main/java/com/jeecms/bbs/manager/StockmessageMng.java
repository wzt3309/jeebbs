package com.jeecms.bbs.manager;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.common.page.Pagination;


public interface StockmessageMng {
	
	public Pagination getmess(String GPDM,int pageNo,int pageSize);
	public List<Stockbasicmessage> getlist(String sql);	
	

}
