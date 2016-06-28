package com.jeecms.bbs.manager.impl;

import com.jeecms.bbs.dao.BbsTopicDao;
import com.jeecms.bbs.dao.StockmessageDao;
import com.jeecms.bbs.dao.impl.StockmessageDaoImpl;
import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.bbs.manager.StockmessageMng;
import com.jeecms.common.page.Pagination;

import static com.jeecms.bbs.entity.BbsTopic.TOPIC_VOTE;
import static com.jeecms.bbs.entity.BbsTopic.TOPIC_NORMAL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public class StockmessageMngImpl implements StockmessageMng{
	
	private StockmessageDaoImpl dao;
	
	public StockmessageMngImpl(){
		dao=new StockmessageDaoImpl();
	}

	public Pagination getmess(String GPDM,int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		
		System.out.println("get in Mng ");
		return dao.getmess(GPDM,pageNo,pageSize);
	}
	
	public List<Stockbasicmessage> getlist(String sql){
		
		return dao.getlist(sql);
	}
	

}
