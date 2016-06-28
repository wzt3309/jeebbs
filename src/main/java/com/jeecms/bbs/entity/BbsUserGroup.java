package com.jeecms.bbs.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.jeecms.bbs.entity.base.BaseBbsUserGroup;

public class BbsUserGroup extends BaseBbsUserGroup {
	private static final long serialVersionUID = 1L;
	/**
	 * 普通组
	 */
	public static final short NORMAL = 1;
	/**
	 * 系统组
	 */
	public static final short SYSTEM = 2;
	/**
	 * 特殊组
	 */
	public static final short SPECIAL = 3;
	/**
	 * 默认组
	 */
	public static final short DEFAULT = 0;

	/**
	 * 是否有版主权限
	 * 
	 * @param forum
	 *            相应的板块
	 * @return
	 */
	public boolean hasRight(BbsForum forum, BbsUser user) {
		if (forum == null) {
			return false;
		}
		if (superModerator()) {
			return true;
		}
		if (user == null) {
			return false;
		}
		if (("," + forum.getCategory().getModerators() + ",").indexOf(","
				+ user.getUsername() + ",") > -1
				|| ("," + forum.getModerators() + ",").indexOf(","
						+ user.getUsername() + ",") > -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否允许发表主题
	 * 
	 * @return
	 */
	public boolean allowTopic() {
		String s = getPerms().get("allow_topic");
		return StringUtils.equals(s, "true");
	}

	/**
	 * 是否允许发表回复
	 * 
	 * @return
	 */
	public boolean allowReply() {
		String s = getPerms().get("allow_reply");
		return StringUtils.equals(s, "true");
	}

	/**
	 * 检查今日发贴数
	 * 
	 * @param count
	 *            今日已发贴数
	 * @return true:允许发帖；false:已达最大发贴数
	 */
	public boolean checkPostToday(int count) {
		int max = getPostPerDay();
		if (max == 0) {
			return true;
		} else {
			return max > count;
		}
	}

	/**
	 * 每日最大发贴数
	 * 
	 * @return
	 */
	public int getPostPerDay() {
		return NumberUtils.toInt(getPerms().get("post_per_day"));
	}

	/**
	 * 发帖时间间隔
	 * 
	 * @return
	 */
	public int getPostInterval() {
		return NumberUtils.toInt(getPerms().get("post_interval"));
	}

	/**
	 * 超级版主。无需指定成为板块版主即有管理权限
	 * 
	 * @return
	 */
	public boolean superModerator() {
		String s = getPerms().get("super_moderator");
		return StringUtils.equals(s, "true");
	}

	/**
	 * 发贴不受限制
	 * 
	 * @return
	 */
	public boolean postLimit() {
		String s = getPerms().get("post_limit");
		return StringUtils.equals(s, "true");
	}

	/**
	 * 获得置顶权限级别
	 * 
	 * @return 0：无权限；1：本版置顶；2：分区置顶；3：全局置顶
	 */
	public short topicTop() {
		short top = (short) NumberUtils.toInt(getPerms().get("topic_top"));
		if (top < 1 || top > 3) {
			return 0;
		} else {
			return top;
		}
	}

	/**
	 * 主题管理权限。精、锁、提、亮、压
	 * 
	 * @return
	 */
	public boolean topicManage() {
		String s = getPerms().get("post_limit");
		return StringUtils.equals(s, "true");
	}

	/**
	 * 编辑帖子
	 * 
	 * @return
	 */
	public boolean topicEdit() {
		String s = getPerms().get("topic_edit");
		return StringUtils.equals(s, "true");
	}

	/**
	 * 删除帖子
	 * 
	 * @return
	 */
	public boolean topicDelete() {
		String s = getPerms().get("topic_delete");
		return StringUtils.equals(s, "true");
	}

	/**
	 * 屏蔽帖子,移动帖子
	 * 
	 * @return
	 */
	public boolean topicShield() {
		String s = getPerms().get("topic_shield");
		return StringUtils.equals(s, "true");
	}

	/**
	 * 查看IP
	 * 
	 * @return
	 */
	public boolean viewIp() {
		String s = getPerms().get("view_ip");
		return StringUtils.equals(s, "true");
	}

	/**
	 * 屏蔽帖子
	 * 
	 * @return
	 */
	public boolean memberProhibit() {
		String s = getPerms().get("member_prohibit");
		return StringUtils.equals(s, "true");
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public BbsUserGroup() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsUserGroup(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsUserGroup(java.lang.Integer id,
			com.jeecms.core.entity.CmsSite site, java.lang.String name,
			java.lang.Short type, java.lang.Long point,
			java.lang.Boolean m_default, java.lang.Integer gradeNum) {

		super(id, site, name, type, point, m_default, gradeNum);
	}

	public void addToPostTypes(BbsPostType postType) {
		Set<BbsPostType> postTypes = getPostTypes();
		if (postTypes == null) {
			postTypes = new HashSet<BbsPostType>();
			setPostTypes(postTypes);
		}
		postTypes.add(postType);
	}

	public static Integer[] fetchIds(Collection<BbsPostType> types) {
		if (types == null) {
			return null;
		}
		Integer[] ids = new Integer[types.size()];
		int i = 0;
		for (BbsPostType t : types) {
			ids[i++] = t.getId();
		}
		return ids;
	}

	/* [CONSTRUCTOR MARKER END] */

}