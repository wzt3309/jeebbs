package com.jeecms.bbs;

/**
 * 道具常量
 * 
 */
public class MagicConstants {
	/**
	 * 道具喧嚣卡--可以将主题开启，可以回复---主题相关（不能用于版主的主题）
	 */
	public static final String MAGIC_OPEN = "open";
	/**
	 * 道具沉默卡--可以将主题关闭，禁止回复---主题相关（属性）
	 */
	public static final String MAGIC_CLOSE = "close";
	/**
	 * 道具抢沙发--可以抢夺指定主题的沙发---主题相关（属性），特定主题只能有一个沙发(插入一条帖子)
	 */
	public static final String MAGIC_SOFA = "sofa";
	/**
	 * 道具变色卡--可以将帖子或日志的标题高亮，变更颜色---主题相关（属性）
	 */
	public static final String MAGIC_HIGHTLIGHT= "highlight";
	/**
	 * 道具提升卡--可以提升某个主题---主题相关（排序）
	 */
	public static final String MAGIC_BUMP = "bump";
	/**
	 * 道具千斤顶--可以将主题顶起一段时间，重复使用可延长帖子被顶起的时间---主题相关（排序）
	 */
	public static final String MAGIC_JACK = "jack";
	/**
	 * 道具置顶卡--可以将主题置顶---主题相关（排序）
	 */
	public static final String MAGIC_STICK = "stick";
	
	
	/**
	 * 道具照妖镜--可以查看一次匿名用户的真实身份。---用户相关，作用于帖子上
	 */
	public static final String MAGIC_NAMEPOST = "namepost";
	/**
	 * 道具悔悟卡--可以删除自己的帖子---作用于自己的帖子
	 */
	public static final String MAGIC_REPENT = "repent";
	/**
	 * 道具匿名卡--在指定的地方，让自己的名字显示为匿名。---帖子相关（属性）
	 */
	public static final String MAGIC_ANONYMOUSPOST = "anonymouspost";

	
	/**
	 * 道具窥视卡--可以查看指定用户的 IP---用户相关
	 */
	public static final String MAGIC_SHOWIP = "showip";
	/**
	 * 道具雷达卡--查看某个用户是否在线---用户相关
	 */
	public static final String MAGIC_CHECKONLINE = "checkonline";
	
	
	/**
	 * 道具金钱卡--可以随机获得一些金币
	 */
	public static final String MAGIC_MONEY = "money";
	/**
	 * 不能操作版主的主题
	 */
	public static final String MAGIC_OPEN_ERROR_NOIN_MODERATORS = "magic.open.error.noinmoderators";
	/**
	 * 沙发已经被他人抢夺
	 */
	public static final String MAGIC_SOFEED_ERROR = "magic.sofeed.error";
	/**
	 * 成功打开主题
	 */
	public static final String MAGIC_OPEN_SUCCESS = "magic open success";
	/**
	 * 帖子顶起等级
	 */
	public static final Short TOP_LEVEL_BUMP=1;
	public static final Short TOP_LEVEL_JACK=2;
	public static final Short TOP_LEVEL_STICK=3;
	/**
	 * 照妖镜显示成功
	 */
	public static final String MAGIC_NAMEPOST_SUCCESS="magic.namepost.success";
	/**
	 * 窥视卡显示ip成功
	 */
	public static final String MAGIC_SHOWIP_SUCCESS="magic.showip.success";
	/**
	 * 在线
	 */
	public static final String MAGIC_CHECKONLINE_ONLINE="magic.checkonline.online";
	/**
	 * 离线
	 */
	public static final String MAGIC_CHECKONLINE_OFFLINE="magic.checkonline.offline";
	
	public static final String MAGIC_FIND_USER_ERROR="magic.finduser.error";
	
	public static final String MAGIC_NO_USERNAME="magic.nousername.error";
	
	public static final String MAGIC_MONEY_SUCCESS="magic.money.success";
	
	//道具操作常量
	public static final Byte MAGIC_OPERATOR_SELL=0;
	public static final Byte MAGIC_OPERATOR_USE=1;
	public static final Byte MAGIC_OPERATOR_DROP=2;
	public static final Byte MAGIC_OPERATOR_BUY=3;
	public static final Byte MAGIC_OPERATOR_GIVE=4;
	

	
	
}
