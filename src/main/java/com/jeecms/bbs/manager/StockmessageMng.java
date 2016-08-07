package com.jeecms.bbs.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.common.page.Pagination;


public interface StockmessageMng {
	
	public Pagination getmess(String sql,int pageNo,int pageSize);
	public List<Stockmessage> getlist(String sql);	
	public Stockmessage save(Stockmessage bean);
	public List getReccomendation_detail(String type,int pageNO,int pageSize);

	public Pagination getReccomendation_simp(String type,int pageNO,int pageSize);

	public Pagination getReccomendation_simp2(String type,int pageNO,int pageSize);
}
