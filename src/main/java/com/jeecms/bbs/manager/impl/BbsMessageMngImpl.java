package com.jeecms.bbs.manager.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jeecms.bbs.dao.BbsMessageDao;
import com.jeecms.bbs.entity.BbsMessage;
import com.jeecms.bbs.entity.BbsMessageReply;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsMessageMng;
import com.jeecms.bbs.manager.BbsMessageReplyMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class BbsMessageMngImpl implements BbsMessageMng {
	@Transactional(readOnly = true)
	public BbsMessage findById(Integer id) {
		BbsMessage entity = dao.findById(id);
		return entity;
	}

	public BbsMessage save(BbsMessage bean) {
		dao.save(bean);
		return bean;
	}

	public BbsMessage update(BbsMessage bean) {
		Updater<BbsMessage> updater = new Updater<BbsMessage>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsMessage deleteById(Integer id) {
		BbsMessage bean = dao.deleteById(id);
		return bean;
	}

	public BbsMessage[] deleteByIds(Integer[] ids) {
		BbsMessage[] beans = new BbsMessage[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private BbsMessage getSendRelation(Integer userId, Integer senderId, Integer receiverId,Integer typeId) {
		return dao.getSendRelation(userId, senderId, receiverId,typeId);
	}

	public void sendMsg(BbsUser sender, BbsUser receiver, BbsMessage sMsg) {
		Integer senderId = sender.getId();
		Integer receiverId = receiver.getId();
		Integer typeId=sMsg.getMsgType();
		//处理发送端
		BbsMessage msg = getSendRelation(senderId, senderId, receiverId,typeId);
		BbsMessage rMsg = sMsg.putDataAndClone(sender, receiver);
		saveOrUpdate(msg, sMsg);
		//处理接收端
		msg = getSendRelation(receiverId, senderId, receiverId,typeId);
		saveOrUpdate(msg, rMsg);
	}
	
	public Pagination getPageByUserId(Integer userId, Integer typeId,Integer pageNo, Integer pageSize) {
		return dao.getPageByUserId(userId,typeId, pageNo, pageSize);
	}
	
	public boolean hasUnReadMessage(Integer userId, Integer typeId) {
		if(dao.getListByUserIdStatus(userId, typeId, false).size()>0){
			return true;
		}
		return false;
	}
	public List getListUserIdStatus(Integer userId, Integer typeId,
			Boolean status) {
		return dao.getListByUserIdStatus(userId, typeId, status);
	}
	
	private void saveOrUpdate(BbsMessage msg, BbsMessage bean){
		//无论是更新还是新的留言或者回复都需要设置未读
		bean.setStatus(false);
		if (msg == null) {
			save(bean);
		}else{
			bean.setId(msg.getId());
			update(bean);
		}
		BbsMessageReply reply = bean.createReply();
		replyMng.save(reply);
	}

	private BbsMessageDao dao;
	private BbsMessageReplyMng replyMng;

	@Autowired
	public void setDao(BbsMessageDao dao) {
		this.dao = dao;
	}
	@Autowired
	public void setReplyMng(BbsMessageReplyMng replyMng) {
		this.replyMng = replyMng;
	}
	
}