package com.jeecms.bbs.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.jeecms.bbs.entity.base.BaseBbsUser;
import com.jeecms.common.hibernate3.PriorityInterface;
import com.jeecms.common.util.DateUtils;
import com.jeecms.core.entity.UnifiedUser;

public class BbsUser extends BaseBbsUser implements PriorityInterface {
	private static final long serialVersionUID = 1L;

	/**
	 * 本地头像
	 */
	public static final short AVATAR_LOCAL = 0;
	/**
	 * 链接头像
	 */
	public static final short AVATAR_LINK = 1;

	/**
	 * 不禁言
	 */
	public static final short PROHIBIT_NO = 0;
	/**
	 * 永久禁言
	 */
	public static final short PROHIBIT_FOREVER = 1;
	/**
	 * 暂时禁言
	 */
	public static final short PROHIBIT_TEMPORARY = 2;

	public boolean getProhibit() {
		if (getProhibitPost() == PROHIBIT_FOREVER) {
			return true;
		}
		if (getProhibitPost() == PROHIBIT_TEMPORARY) {
			Date time = new Date();
			if (time.before(getProhibitTime())) {
				return true;
			}
		}
		return false;
	}

	public Boolean getModerator() {
		if (getGroup().getType().equals(BbsUserGroup.SYSTEM)) {
			return true;
		} else {
			return false;
		}
	}

	public String getRealname() {
		BbsUserExt ext = getUserExt();
		if (ext != null) {
			return ext.getRealname();
		} else {
			return null;
		}
	}

	public Boolean getGender() {
		BbsUserExt ext = getUserExt();
		if (ext != null) {
			return ext.getGender();
		} else {
			return null;
		}
	}

	public Date getBirthday() {
		BbsUserExt ext = getUserExt();
		if (ext != null) {
			return ext.getBirthday();
		} else {
			return null;
		}
	}

	public String getIntro() {
		BbsUserExt ext = getUserExt();
		if (ext != null) {
			return ext.getIntro();
		} else {
			return null;
		}
	}

	public String getComefrom() {
		BbsUserExt ext = getUserExt();
		if (ext != null) {
			return ext.getComefrom();
		} else {
			return null;
		}
	}

	public String getQq() {
		BbsUserExt ext = getUserExt();
		if (ext != null) {
			return ext.getQq();
		} else {
			return null;
		}
	}

	public String getMsn() {
		BbsUserExt ext = getUserExt();
		if (ext != null) {
			return ext.getMsn();
		} else {
			return null;
		}
	}

	public String getPhone() {
		BbsUserExt ext = getUserExt();
		if (ext != null) {
			return ext.getPhone();
		} else {
			return null;
		}
	}

	public String getMoble() {
		BbsUserExt ext = getUserExt();
		if (ext != null) {
			return ext.getMoble();
		} else {
			return null;
		}
	}

	public BbsUserExt getUserExt() {
		Set<BbsUserExt> set = getUserExtSet();
		if (set != null && set.size() > 0) {
			return set.iterator().next();
		} else {
			return null;
		}
	}

	public BbsLoginLog getUserLaestLoginLog() {
		Set<BbsLoginLog> set = getLoginLogs();
		if (set != null && set.size() > 0) {
			return set.iterator().next();
		} else {
			return null;
		}
	}

	public int getLaestOnLineMinute() {
		BbsLoginLog log = getUserLaestLoginLog();
		int timout;
		if (log == null) {
			timout = 0;
		} else {
			timout = (int) ((log.getLogoutTime().getTime() - log.getLoginTime()
					.getTime()) / 1000 / 60);
		}
		return timout;
	}

	public Double getOnlineLatest() {
		if (getUserOnline() != null) {
			return splitDouble(getUserOnline().getOnlineLatest()/60.0);
		} else {
			return 0d;
		}
	}

	public int getDayOnLineMinute() {
		Set<BbsLoginLog> logs = getLoginLogs();
		Iterator<BbsLoginLog> it = logs.iterator();
		BbsLoginLog log;
		int timout = 0;
		while (it.hasNext()) {
			log = it.next();
			if (DateUtils.isToday(log.getLoginTime())) {
				timout += (int) ((log.getLogoutTime().getTime() - log
						.getLoginTime().getTime()) / 1000 / 60);
			}
		}
		return timout;
	}

	public Double getOnlineDay() {
		if (getUserOnline() != null) {
			return splitDouble(getUserOnline().getOnlineDay()/60.0);
		} else {
			return 0d;
		}
	}

	public int getWeekOnLineMinute() {
		Set<BbsLoginLog> logs = getLoginLogs();
		Iterator<BbsLoginLog> it = logs.iterator();
		BbsLoginLog log;
		int timout = 0;
		while (it.hasNext()) {
			log = it.next();
			if (DateUtils.isThisWeek(log.getLoginTime())) {
				timout += (int) ((log.getLogoutTime().getTime() - log
						.getLoginTime().getTime()) / 1000 / 60);
			}
		}
		return timout;
	}

	public Double getOnlineWeek() {
		if (getUserOnline() != null) {
			return splitDouble(getUserOnline().getOnlineWeek()/60.0);
		} else {
			return 0d;
		}
	}

	public int getMonthOnLineMinute() {
		Set<BbsLoginLog> logs = getLoginLogs();
		Iterator<BbsLoginLog> it = logs.iterator();
		BbsLoginLog log;
		int timout = 0;
		while (it.hasNext()) {
			log = it.next();
			if (DateUtils.isThisMonth(log.getLoginTime())) {
				timout += (int) ((log.getLogoutTime().getTime() - log
						.getLoginTime().getTime()) / 1000 / 60);
			}
		}
		return timout;
	}

	public Double getOnlineMonth() {
		if (getUserOnline() != null) {
			return splitDouble(getUserOnline().getOnlineMonth()/60.0);
		} else {
			return 0d;
		}
	}

	public int getYearOnLineMinute() {
		Set<BbsLoginLog> logs = getLoginLogs();
		Iterator<BbsLoginLog> it = logs.iterator();
		BbsLoginLog log;
		int timout = 0;
		while (it.hasNext()) {
			log = it.next();
			if (DateUtils.isThisYear(log.getLoginTime())) {
				timout += (int) ((log.getLogoutTime().getTime() - log
						.getLoginTime().getTime()) / 1000 / 60);
			}
		}
		return timout;
	}

	public Double getOnlineYear() {
		if (getUserOnline() != null) {
			return splitDouble(getUserOnline().getOnlineYear()/60.0);
		} else {
			return 0d;
		}
	}
	
	private double splitDouble(double d){
		return ((double)Math.round(d*100))/100;
	}

	public Set<BbsPostType> getPostTypeByForum(BbsForum forum) {
		Set<BbsPostType> forumPostTypes = forum.getPostTypes();
		Iterator<BbsPostType> uit = getGroup().getPostTypes().iterator();
		Set<BbsPostType> result = new HashSet<BbsPostType>();
		BbsPostType type;
		while (uit.hasNext()) {
			type = uit.next();
			if (forumPostTypes.contains(type)) {
				result.add(type);
			}
		}
		return result;
	}

	public BbsMemberMagic getMemberMagic(String mid) {
		Set<BbsMemberMagic> magics = getMemberMagics();
		Iterator<BbsMemberMagic> it = magics.iterator();
		BbsMemberMagic magic;
		while (it.hasNext()) {
			magic = it.next();
			if (magic.getMagic().getIdentifier().equals(mid)) {
				return magic;
			}
		}
		return null;
	}

	public void addToMemberMagics(BbsMemberMagic magic) {
		Set<BbsMemberMagic> magics = getMemberMagics();
		if (magics == null) {
			magics = new HashSet<BbsMemberMagic>();
			setMemberMagics(magics);
		}
		magics.add(magic);
	}

	public void setMemberMagicNum(String mid, int num, int operator) {
		Set<BbsMemberMagic> magics = getMemberMagics();
		Iterator<BbsMemberMagic> it = magics.iterator();
		BbsMemberMagic magic;
		BbsCommonMagic commomMagic;
		while (it.hasNext()) {
			magic = it.next();
			commomMagic = magic.getMagic();
			if (commomMagic.getIdentifier().equals(mid)) {
				if (operator == 0) {
					// 减少数量
					commomMagic.setNum(commomMagic.getNum() - num);
				} else if (operator == 1) {
					// 增加数量
					commomMagic.setNum(commomMagic.getNum() + num);
				}
				break;
			}
		}
	}

	public void forMember(UnifiedUser u) {
		forUser(u);
		setAdmin(false);
	}

	public void forAdmin(UnifiedUser u, boolean viewonly, boolean selfAdmin,
			int rank) {
		forUser(u);
		setAdmin(true);
	}

	public void forUser(UnifiedUser u) {
		setDisabled(false);
		setId(u.getId());
		setUsername(u.getUsername());
		setInvitename(u.getInvitename());
		setEmail(u.getEmail());
		setTelephone(u.getTelephone());
		setRegisterIp(u.getRegisterIp());
		setRegisterTime(u.getRegisterTime());
		setLastLoginIp(u.getLastLoginIp());
		setLastLoginTime(u.getLastLoginTime());
		setLoginCount(0);
	}

	public void init() {
		if (getUploadTotal() == null) {
			setUploadTotal(0L);
		}
		if (getUploadSize() == null) {
			setUploadSize(0);
		}
		if (getUploadDate() == null) {
			setUploadDate(new java.sql.Date(System.currentTimeMillis()));
		}
		if (getAdmin() == null) {
			setAdmin(false);
		}
		if (getProhibitPost() == null) {
			setProhibitPost(PROHIBIT_NO);
		}
		if (getDisabled() == null) {
			setDisabled(false);
		}
		if (getUploadToday() == null) {
			setUploadToday(0);
		}
		if (getPoint() == null) {
			setPoint(0L);
		}
		if(getPrestige()==null){
			setPrestige(0L);
		}
		if(getMagicPacketSize()==null){
			setMagicPacketSize(0);
		}
		if (getAvatarType() == null) {
			setAvatarType(AVATAR_LOCAL);
		}
		if (getTopicCount() == null) {
			setTopicCount(0);
		}
		if (getReplyCount() == null) {
			setReplyCount(0);
		}
		if (getPrimeCount() == null) {
			setPrimeCount(0);
		}
		if (getPostToday() == null) {
			setPostToday(0);
		}
	}

	public static Integer[] fetchIds(Collection<BbsUser> users) {
		if (users == null) {
			return null;
		}
		Integer[] ids = new Integer[users.size()];
		int i = 0;
		for (BbsUser u : users) {
			ids[i++] = u.getId();
		}
		return ids;
	}

	/**
	 * 用于排列顺序。此处优先级为0，则按ID升序排。
	 */
	public Number getPriority() {
		return 0;
	}

	/**
	 * 是否是今天。根据System.currentTimeMillis() / 1000 / 60 / 60 / 24计算。
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		long day = date.getTime() / 1000 / 60 / 60 / 24;
		long currentDay = System.currentTimeMillis() / 1000 / 60 / 60 / 24;
		return day == currentDay;
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public BbsUser() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsUser(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsUser(java.lang.Integer id,
			com.jeecms.bbs.entity.BbsUserGroup group,
			java.lang.String username,java.lang.String invitename, java.util.Date registerTime,
			java.lang.String registerIp, java.lang.Integer loginCount,
			java.lang.Long uploadTotal, java.lang.Integer uploadToday,
			java.lang.Integer uploadSize, java.lang.Boolean admin,
			java.lang.Boolean disabled, java.lang.Long point,
			java.lang.Short avatarType, java.lang.Integer topicCount,
			java.lang.Integer replyCount, java.lang.Integer primeCount,
			java.lang.Integer postToday, java.lang.Short prohibitPost) {

		super(id, group, username,invitename, registerTime, registerIp, loginCount,
				uploadTotal, uploadToday, uploadSize, admin, disabled, point,
				avatarType, topicCount, replyCount, primeCount, postToday,
				prohibitPost);
	}

	/* [CONSTRUCTOR MARKER END] */

}