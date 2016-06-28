package com.jeecms.bbs.manager.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsUserDao;
import com.jeecms.bbs.entity.BbsCommonMagic;
import com.jeecms.bbs.entity.BbsMemberMagic;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserExt;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsCommonMagicMng;
import com.jeecms.bbs.manager.BbsMemberMagicMng;
import com.jeecms.bbs.manager.BbsUserExtMng;
import com.jeecms.bbs.manager.BbsUserGroupMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;

@Service
@Transactional
public class BbsUserMngImpl implements BbsUserMng {

	@Transactional(readOnly = true)
	public Pagination getPage(String username, String email, Integer groupId,
			Boolean disabled, Boolean admin, Long pointMin, Long pointMax,
			Long prestigeMin, Long prestigeMax, Integer orderBy, int pageNo,
			int pageSize) {
		Pagination page = dao.getPage(username, email, groupId, disabled,
				admin, pointMin, pointMax, prestigeMin, prestigeMax, orderBy,
				pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public List<BbsUser> getAdminList(Integer siteId, Boolean allChannel,
			Boolean disabled, Integer rank) {
		return dao.getAdminList(siteId, allChannel, disabled, rank);
	}

	@Transactional(readOnly = true)
	public BbsUser findById(Integer id) {
		BbsUser entity = dao.findById(id);
		return entity;
	}

	@Transactional(readOnly = true)
	public BbsUser findByUsername(String username) {
		BbsUser entity = dao.findByUsername(username);
		return entity;
	}

	public BbsUser registerMember(String username, String email,String telephone,
			String password, String ip, Integer groupId, BbsUserExt userExt) {
		UnifiedUser unifiedUser = unifiedUserMng.save(username, email,telephone,
				password, ip);
		BbsUser user = new BbsUser();
		user.forMember(unifiedUser);
		BbsUserGroup group = null;
		if (groupId != null) {
			group = bbsUserGroupMng.findById(groupId);
		} else {
			group = bbsUserGroupMng.getRegDef();
		}
		if (group == null) {
			throw new RuntimeException(
					"register default member group not found!");
		}
		user.setGroup(group);
		user.init();
		System.out.println(user.getTelephone()+"reg2");
		dao.save(user);
		bbsUserExtMng.save(userExt, user);
		return user;
	}

	public BbsUser registerMember(String username, String email,String telephone,
			String password, String ip, Integer groupId, BbsUserExt userExt,
			Boolean activation, EmailSender sender, MessageTemplate msgTpl) {
		UnifiedUser unifiedUser = unifiedUserMng.save(username, email,telephone,
				password, ip, activation, sender, msgTpl);
		BbsUser user = new BbsUser();
		user.forMember(unifiedUser);
		BbsUserGroup group = null;
		if (groupId != null) {
			group = bbsUserGroupMng.findById(groupId);
		} else {
			group = bbsUserGroupMng.getRegDef();
		}
		if (group == null) {
			throw new RuntimeException(
					"register default member group not found!");
		}
		System.out.println("regest success");
		user.setGroup(group);
		user.init();
		System.out.println(user.getTelephone()+"reg1");
		dao.save(user);
		bbsUserExtMng.save(userExt, user);
		return user;
	}

	public void updateLoginInfo(Integer userId, String ip) {
		Date now = new Timestamp(System.currentTimeMillis());
		BbsUser user = findById(userId);
		if (user != null) {
			user.setLoginCount(user.getLoginCount() + 1);
			user.setLastLoginIp(ip);
			user.setLastLoginTime(now);
		}
	}

	public void updateUploadSize(Integer userId, Integer size) {
		BbsUser user = findById(userId);
		user.setUploadTotal(user.getUploadTotal() + size);
		if (user.getUploadDate() != null) {
			if (BbsUser.isToday(user.getUploadDate())) {
				size += user.getUploadSize();
			}
		}
		user.setUploadDate(new java.sql.Date(System.currentTimeMillis()));
		user.setUploadSize(size);
	}

	public boolean isPasswordValid(Integer id, String password) {
		return unifiedUserMng.isPasswordValid(id, password);
	}

	public void updatePwdEmail(Integer id, String password, String email) {
		BbsUser user = findById(id);
		if (!StringUtils.isBlank(email)) {
			user.setEmail(email);
		} else {
			user.setEmail(null);
		}
		unifiedUserMng.update(id, password, email,user.getTelephone());
	}

	public BbsUser saveAdmin(String username, String email, String telephone,String password,
			String ip, boolean viewOnly, boolean selfAdmin, int rank,
			Integer groupId, Integer[] roleIds, Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels,
			BbsUserExt userExt) {
		UnifiedUser unifiedUser = unifiedUserMng.save(username, email,telephone,
				password, ip);
		BbsUser user = new BbsUser();
		user.forAdmin(unifiedUser, viewOnly, selfAdmin, rank);
		BbsUserGroup group = null;
		if (groupId != null) {
			group = bbsUserGroupMng.findById(groupId);
		} else {
			group = bbsUserGroupMng.getRegDef();
		}
		if (group == null) {
			throw new RuntimeException(
					"register default member group not setted!");
		}
		user.setGroup(group);
		user.init();
		dao.save(user);
		bbsUserExtMng.save(userExt, user);
		return user;
	}

	public BbsUser updateAdmin(BbsUser bean, BbsUserExt ext, String password,
			Integer groupId, Integer[] roleIds, Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels) {
		Updater<BbsUser> updater = new Updater<BbsUser>(bean);
		updater.include("email");
		BbsUser user = dao.updateByUpdater(updater);
		user.setGroup(bbsUserGroupMng.findById(groupId));
		bbsUserExtMng.update(ext, user);
		unifiedUserMng.update(bean.getId(), password, bean.getEmail(),bean.getTelephone());
		return user;
	}

	public BbsUser updateMember(Integer id, String email,String telephone, String password,
			Boolean isDisabled, String signed, String avatar, BbsUserExt ext,
			Integer groupId) {
		BbsUser entity = findById(id);
		if (!StringUtils.isBlank(email)) {
			entity.setEmail(email);
		}
		if (!StringUtils.isBlank(telephone)) {
			entity.setTelephone(telephone);
		}
		if (isDisabled != null) {
			entity.setDisabled(isDisabled);
		}
		if (signed != null) {
			entity.setSigned(signed);
		}
		if (avatar != null) {
			entity.setAvatar(avatar);
		}
		if (groupId != null) {
			entity.setGroup(bbsUserGroupMng.findById(groupId));
		}
		bbsUserExtMng.update(ext, entity);
		unifiedUserMng.update(id, password, email,telephone);
		return entity;
	}
	public BbsUser updateMemberPwd(Integer id,String password){
		BbsUser entity = findById(id);
		System.out.println("获得BbsUser"+entity.getTelephone());
		unifiedUserMng.update(id, password, entity.getEmail(),entity.getTelephone());
		return entity;
	}
	public BbsUser deleteById(Integer id) {
		unifiedUserMng.deleteById(id);
		BbsUser bean = dao.deleteById(id);
		return bean;
	}

	public BbsUser[] deleteByIds(Integer[] ids) {
		BbsUser[] beans = new BbsUser[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public boolean usernameNotExist(String username) {
		return dao.countByUsername(username) <= 0;
	}

	public boolean emailNotExist(String email) {
		return dao.countByEmail(email) <= 0;
	}

	@Transactional(readOnly = true)
	public List<BbsUser> getSuggestMember(String username, Integer count) {
		return dao.getSuggestMember(username, count);
	}

	public void updatePoint(Integer userId, Integer point, Integer prestige,
			String mid, int num, int operator) {
		BbsUser user = findById(userId);
		if (point != null) {
			user.setPoint(user.getPoint() + point);
		}
		if (prestige != null) {
			user.setPrestige(user.getPrestige() + prestige);
		}
		// operator=-1无须下面操作
		if (StringUtils.isNotBlank(mid) && operator != -1) {
			BbsMemberMagic magic;
			BbsCommonMagic comMagic = magicMng.findByIdentifier(mid);
			magic = user.getMemberMagic(mid);
			// operator==0出售道具，=1使用道具 =2丢弃道具 =3购买道具=4系统赠送道具
			if (operator == 0) {
				// 用户存在该道具---减少数量
				if (magic != null) {
					magic.setNum(magic.getNum() - num);
					// 减少包容量
					user.setMagicPacketSize(user.getMagicPacketSize() - num
							* comMagic.getWeight());
					// 增加系统包数量
					comMagic.setNum(num + comMagic.getNum());
					magicMng.update(comMagic);
				}
			} else if (operator == 1) {
				// 减少数量
				if (magic != null) {
					magic.setNum(magic.getNum() - num);
					// 减少包容量
					user.setMagicPacketSize(user.getMagicPacketSize() - num
							* comMagic.getWeight());
				}
			} else if (operator == 2) {
				// 减少数量
				if (magic != null) {
					magic.setNum(magic.getNum() - num);
					// 减少包容量
					user.setMagicPacketSize(user.getMagicPacketSize() - num
							* comMagic.getWeight());
				}
			} else if (operator == 3) {
				// 增加数量
				if (magic != null) {
					magic.setNum(magic.getNum() + num);
					// 增加包容量
					user.setMagicPacketSize(user.getMagicPacketSize() + num
							* comMagic.getWeight());
					// 减少系统包数量
					comMagic.setNum(comMagic.getNum() - num);
					magicMng.update(comMagic);
				} else {
					magic = new BbsMemberMagic();
					magic.setMagic(comMagic);
					magic.setNum(num);
					magic.setUser(user);
					memberMagicMng.save(magic);
					user.addToMemberMagics(magic);
				}
			} else if (operator == 4) {
				// 增加数量
				if (magic != null) {
					magic.setNum(magic.getNum() + num);
					// 增加包容量
					user.setMagicPacketSize(user.getMagicPacketSize() + num
							* comMagic.getWeight());
					magicMng.update(comMagic);
				} else {
					magic = new BbsMemberMagic();
					magic.setMagic(comMagic);
					magic.setNum(num);
					magic.setUser(user);
					memberMagicMng.save(magic);
					user.addToMemberMagics(magic);
				}
			}

		}
	}

	// private CmsSiteMng cmsSiteMng;
	private BbsUserGroupMng bbsUserGroupMng;
	private UnifiedUserMng unifiedUserMng;
	private BbsUserExtMng bbsUserExtMng;
	private BbsUserDao dao;
	@Autowired
	private BbsCommonMagicMng magicMng;
	@Autowired
	private BbsMemberMagicMng memberMagicMng;

	// @Autowired
	// public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
	// this.cmsSiteMng = cmsSiteMng;
	// }

	@Autowired
	public void setUnifiedUserMng(UnifiedUserMng unifiedUserMng) {
		this.unifiedUserMng = unifiedUserMng;
	}

	@Autowired
	public void setBbsUserExtMng(BbsUserExtMng bbsUserExtMng) {
		this.bbsUserExtMng = bbsUserExtMng;
	}

	@Autowired
	public void setDao(BbsUserDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setBbsUserGroupMng(BbsUserGroupMng bbsUserGroupMng) {
		this.bbsUserGroupMng = bbsUserGroupMng;
	}

	
}