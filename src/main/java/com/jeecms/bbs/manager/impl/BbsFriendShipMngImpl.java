package com.jeecms.bbs.manager.impl;

import static com.jeecms.bbs.entity.BbsFriendShip.APPLYING;
import static com.jeecms.bbs.entity.BbsFriendShip.ACCEPT;
import static com.jeecms.bbs.entity.BbsFriendShip.REFUSE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsFriendShipDao;
import com.jeecms.bbs.entity.BbsFriendShip;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsFriendShipMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class BbsFriendShipMngImpl implements BbsFriendShipMng {
	@Transactional(readOnly = true)
	public BbsFriendShip findById(Integer id) {
		BbsFriendShip entity = dao.findById(id);
		return entity;
	}

	public BbsFriendShip save(BbsFriendShip bean) {
		dao.save(bean);
		return bean;
	}

	public BbsFriendShip update(BbsFriendShip bean) {
		Updater<BbsFriendShip> updater = new Updater<BbsFriendShip>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsFriendShip deleteById(Integer id) {
		BbsFriendShip bean = dao.deleteById(id);
		return bean;
	}

	public BbsFriendShip[] deleteByIds(Integer[] ids) {
		BbsFriendShip[] beans = new BbsFriendShip[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public BbsFriendShip getFriendShip(Integer userId, Integer friendId) {
		return dao.getFriendShip(userId, friendId);
	}

	public void apply(BbsUser user, BbsUser friend) {
		BbsFriendShip bean = getFriendShip(user.getId(), friend.getId());
		if (bean != null) {
			bean.setStatus(APPLYING);
		}else{
			bean = new BbsFriendShip();
			bean.setUser(user);
			bean.setFriend(friend);
			bean.init();
			save(bean);
		}
	}
	
	public void accept(BbsFriendShip friendShip) {
		friendShip.setStatus(ACCEPT);
		BbsFriendShip bean = new BbsFriendShip();
		bean.setUser(friendShip.getFriend());
		bean.setFriend(friendShip.getUser());
		bean.setStatus(ACCEPT);
		save(bean);
	}
	
	public void refuse(BbsFriendShip friendShip) {
		friendShip.setStatus(REFUSE);
	}

	public Pagination getPageByUserId(Integer userId, Integer pageNo,
			Integer pageSize) {
		return dao.getPageByUserId(userId, pageNo, pageSize);
	}
	
	public Pagination getApplyByUserId(Integer userId, Integer pageNo,
			Integer pageSize) {
		return dao.getApplyByUserId(userId, pageNo, pageSize);
	}

	private BbsFriendShipDao dao;

	@Autowired
	public void setDao(BbsFriendShipDao dao) {
		this.dao = dao;
	}
}