DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `attachment_id` int(11) NOT NULL auto_increment,
  `post_id` int(11) NOT NULL,
  `name` varchar(100) default NULL COMMENT '名称',
  `description` varchar(255) default NULL COMMENT '描述',
  `file_path` varchar(100) default NULL COMMENT '路径',
  `file_name` varchar(100) default NULL COMMENT '文件名称',
  `file_size` int(11) default NULL COMMENT '大小',
  `is_pictrue` tinyint(1) default '0' COMMENT '是否是图片',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`attachment_id`),
  KEY `FK_attachment_post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Dumping data for table attachment
#


#
# Source for table bbs_category
#

DROP TABLE IF EXISTS `bbs_category`;
CREATE TABLE `bbs_category` (
  `CATEGORY_ID` int(11) NOT NULL auto_increment,
  `SITE_ID` int(11) NOT NULL,
  `PATH` varchar(20) NOT NULL COMMENT '访问路径',
  `TITLE` varchar(100) NOT NULL COMMENT '标题',
  `PRIORITY` int(11) NOT NULL default '10' COMMENT '排列顺序',
  `FORUM_COLS` int(11) NOT NULL default '1' COMMENT '板块列数',
  `moderators` varchar(100) default NULL,
  PRIMARY KEY  (`CATEGORY_ID`),
  KEY `FK_BBS_CTG_SITE` (`SITE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='论坛分区';

#
# Dumping data for table bbs_category
#

INSERT INTO `bbs_category` VALUES (1,1,'service','≡JEECMS产品服务区≡',0,1,'');
INSERT INTO `bbs_category` VALUES (2,1,'use','≡JEECMS使用交流区≡',0,1,'');

#
# Source for table bbs_category_user
#

DROP TABLE IF EXISTS `bbs_category_user`;
CREATE TABLE `bbs_category_user` (
  `CATEGORY_ID` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY  (`CATEGORY_ID`,`user_id`),
  KEY `FK_BBS_CATEGORY_TO_USER` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分区版主';

#
# Dumping data for table bbs_category_user
#


#
# Source for table bbs_common_magic
#

DROP TABLE IF EXISTS `bbs_common_magic`;
CREATE TABLE `bbs_common_magic` (
  `magicid` smallint(6) NOT NULL auto_increment COMMENT '道具id',
  `available` tinyint(1) NOT NULL default '0' COMMENT '是否可用',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `identifier` varchar(40) NOT NULL COMMENT '唯一标识',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `displayorder` tinyint(3) NOT NULL default '0' COMMENT '顺序',
  `credit` tinyint(1) NOT NULL default '0' COMMENT '使用的积分',
  `price` mediumint(8) unsigned NOT NULL default '0' COMMENT '价格',
  `num` smallint(6) unsigned NOT NULL default '0' COMMENT '数量',
  `salevolume` smallint(6) unsigned NOT NULL default '0' COMMENT '销售量',
  `supplytype` tinyint(1) NOT NULL default '0' COMMENT '自动补货类型',
  `supplynum` smallint(6) unsigned NOT NULL default '0' COMMENT '自动补货数量',
  `useperoid` tinyint(1) NOT NULL default '0' COMMENT '使用周期',
  `usenum` smallint(6) unsigned NOT NULL default '0' COMMENT '周期使用数量',
  `weight` tinyint(3) unsigned NOT NULL default '1' COMMENT '重量',
  `useevent` tinyint(1) NOT NULL default '0' COMMENT '0:只在特定环境使用 1:可以在道具中心使用',
  PRIMARY KEY  (`magicid`),
  UNIQUE KEY `identifier` (`identifier`),
  KEY `displayorder` (`available`,`displayorder`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='道具数据表';

#
# Dumping data for table bbs_common_magic
#

INSERT INTO `bbs_common_magic` VALUES (1,1,'喧嚣卡','open','可以将主题开启，可以回复',0,1,10,0,0,2,0,2,0,10,0);
INSERT INTO `bbs_common_magic` VALUES (2,1,'悔悟卡','repent','可以删除自己的帖子',0,2,10,2,0,1,0,1,0,10,0);
INSERT INTO `bbs_common_magic` VALUES (3,1,'照妖镜','namepost','可以查看一次匿名用户的真实身份。',0,1,10,1,0,1,0,1,0,10,0);
INSERT INTO `bbs_common_magic` VALUES (4,1,'金钱卡','money','可以随机获得一些金币',0,2,10,44,0,1,0,1,0,10,1);
INSERT INTO `bbs_common_magic` VALUES (5,1,'千斤顶','jack','可以将主题顶起一段时间，重复使用可延长帖子被顶起的时间',0,1,10,0,0,0,0,0,0,10,0);
INSERT INTO `bbs_common_magic` VALUES (6,1,'窥视卡','showip','可以查看指定用户的 IP',0,1,10,1,0,1,0,1,0,10,1);
INSERT INTO `bbs_common_magic` VALUES (7,1,'抢沙发','sofa','可以抢夺指定主题的沙发',0,1,10,0,0,0,0,0,0,10,0);
INSERT INTO `bbs_common_magic` VALUES (8,1,'置顶卡','stick','可以将主题置顶',0,1,10,0,0,0,0,0,0,10,0);
INSERT INTO `bbs_common_magic` VALUES (9,1,'变色卡','highlight','可以将帖子或日志的标题高亮，变更颜色',0,1,10,2,0,0,0,0,0,10,0);
INSERT INTO `bbs_common_magic` VALUES (10,1,'雷达卡','checkonline','查看某个用户是否在线',0,1,10,1,0,1,0,1,0,10,1);
INSERT INTO `bbs_common_magic` VALUES (11,1,'沉默卡','close','可以将主题关闭，禁止回复',0,1,10,2,100,1,0,1,2,10,0);
INSERT INTO `bbs_common_magic` VALUES (12,1,'提升卡','bump','可以提升某个主题',0,1,10,0,0,1,0,1,0,10,0);
INSERT INTO `bbs_common_magic` VALUES (13,1,'匿名卡','anonymouspost','在指定的地方，让自己的名字显示为匿名。',0,1,10,0,0,0,0,0,0,10,0);

#
# Source for table bbs_config
#

DROP TABLE IF EXISTS `bbs_config`;
CREATE TABLE `bbs_config` (
  `CONFIG_ID` bigint(20) NOT NULL,
  `DEF_AVATAR` varchar(100) NOT NULL default '' COMMENT '默认会员头像',
  `AVATAR_WIDTH` int(11) NOT NULL default '160' COMMENT '头像最大宽度',
  `AVATAR_HEIGHT` int(11) NOT NULL default '160' COMMENT '头像最大高度',
  `TOPIC_COUNT_PER_PAGE` int(11) NOT NULL default '20' COMMENT '每页主题数',
  `POST_COUNT_PER_PAGE` int(11) NOT NULL default '10' COMMENT '每页帖子数',
  `KEYWORDS` varchar(255) NOT NULL default '' COMMENT '首页关键字',
  `DESCRIPTION` varchar(255) NOT NULL default '' COMMENT '首页描述',
  `REGISTER_STATUS` smallint(6) NOT NULL default '1' COMMENT '注册状态（0：关闭，1：开放，2：邀请）',
  `REGISTER_GROUP_ID` int(11) NOT NULL default '1' COMMENT '注册会员组',
  `REGISTER_RULE` longtext COMMENT '注册协议',
  `CACHE_POST_TODAY` int(11) NOT NULL default '0' COMMENT '今日贴数',
  `CACHE_POST_YESTERDAY` int(11) NOT NULL default '0' COMMENT '昨日帖数',
  `CACHE_POST_MAX` int(11) NOT NULL default '0' COMMENT '最高帖数',
  `CACHE_POST_MAX_DATE` date NOT NULL COMMENT '最高帖数日',
  `CACHE_TOPIC_TOTAL` int(11) NOT NULL default '0' COMMENT '总主题',
  `CACHE_POST_TOTAL` int(11) NOT NULL default '0' COMMENT '总帖数',
  `CACHE_USER_TOTAL` int(11) NOT NULL default '0' COMMENT '总会员',
  `last_user_id` int(11) default NULL COMMENT '最新会员',
  `site_id` int(11) NOT NULL,
  `DEFAULT_GROUP_ID` bigint(20) NOT NULL default '1' COMMENT '默认会员组',
  `TOPIC_HOT_COUNT` int(11) NOT NULL default '10' COMMENT '热帖回复数量',
  `AUTO_REGISTER` tinyint(1) default '1' COMMENT '是否自动注册',
  `EMAIL_VALIDATE` tinyint(1) default '0' COMMENT '开启邮箱验证',
  PRIMARY KEY  (`CONFIG_ID`),
  KEY `FK_BBS_CONFIG_SITE` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛配置';

#
# Dumping data for table bbs_config
#

INSERT INTO `bbs_config` VALUES (1,'1',160,160,20,10,'JEEBBS','JEEBBS',1,1,'',7,0,7,'2011-10-15',7,0,1,10,1,1,10,1,0);

#
# Source for table bbs_credit_exchange
#

DROP TABLE IF EXISTS `bbs_credit_exchange`;
CREATE TABLE `bbs_credit_exchange` (
  `eid` int(11) NOT NULL default '0',
  `expoint` int(11) NOT NULL default '0' COMMENT '兑换比率积分基数',
  `exprestige` int(11) NOT NULL default '0' COMMENT '兑换比率威望基数',
  `pointoutavailable` tinyint(1) NOT NULL default '0' COMMENT '积分是否可以兑出',
  `pointinavailable` tinyint(1) NOT NULL default '0' COMMENT '积分是否允许兑入',
  `prestigeoutavailable` tinyint(3) NOT NULL default '0' COMMENT '威望是否允许兑出',
  `prestigeinavailable` tinyint(1) NOT NULL default '0' COMMENT '威望是否允许兑入',
  `exchangetax` float(2,1) NOT NULL default '0.0' COMMENT '兑换交易税',
  `mini_balance` int(11) NOT NULL default '0' COMMENT '兑换最低余额',
  PRIMARY KEY  (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分交易规则';

#
# Dumping data for table bbs_credit_exchange
#

INSERT INTO `bbs_credit_exchange` VALUES (1,1,10,1,1,1,1,0.2,0);

#
# Source for table bbs_credit_rule
#

DROP TABLE IF EXISTS `bbs_credit_rule`;
CREATE TABLE `bbs_credit_rule` (
  `rid` int(11) NOT NULL auto_increment COMMENT '规则ID',
  `rulename` varchar(20) NOT NULL default '' COMMENT '规则名称',
  `action` varchar(50) NOT NULL default '' COMMENT '规则action唯一KEY',
  `cycletype` tinyint(1) NOT NULL default '0' COMMENT '奖励周期0:一次;1:每天;2:整点;3:间隔分钟;4:不限;',
  `cycletime` int(10) NOT NULL default '0' COMMENT '间隔时间',
  `rewardnum` tinyint(2) NOT NULL default '1' COMMENT '奖励次数',
  `extcredits1` int(10) NOT NULL default '0' COMMENT '扩展1',
  `extcredits2` int(10) NOT NULL default '0' COMMENT '扩展2',
  `extcredits3` int(10) NOT NULL default '0' COMMENT '扩展3',
  `extcredits4` int(10) NOT NULL default '0' COMMENT '扩展4',
  `ext1name` varchar(20) default NULL COMMENT '扩展1别名',
  `ext2name` varchar(20) default NULL COMMENT '扩展2别名',
  `ext3name` varchar(20) default NULL COMMENT '扩展3别名',
  `ext4name` varchar(20) default NULL COMMENT '扩展4别名',
  `ext1avai` tinyint(1) NOT NULL default '0' COMMENT '是否启用\n(0:不启用 1:启用但不显示 2:启用并显示)',
  `ext2avai` tinyint(1) NOT NULL default '0' COMMENT '是否启用\n(0:不启用 1:启用但不显示 2:启用并显示)',
  `ext3avai` tinyint(1) NOT NULL default '0' COMMENT '是否启用\n(0:不启用 1:启用但不显示 2:启用并显示)',
  `ext4avai` tinyint(1) NOT NULL default '0' COMMENT '是否启用\n(0:不启用 1:启用但不显示 2:启用并显示)',
  `ext1exchangeout` tinyint(1) default '0' COMMENT '积分兑出',
  `ext2exchangeout` tinyint(1) default '0' COMMENT '积分兑出',
  `ext3exchangeout` tinyint(1) default '0' COMMENT '积分兑出',
  `ext4exchangeout` tinyint(1) default '0' COMMENT '积分兑出',
  `ext1exchangein` tinyint(1) default '0' COMMENT '积分兑入',
  `ext2exchangein` tinyint(1) default '0' COMMENT '积分兑入',
  `ext3exchangein` tinyint(1) default '0' COMMENT '积分兑入',
  `ext4exchangein` tinyint(1) default '0' COMMENT '积分兑入',
  `credittax` tinyint(2) default NULL COMMENT '积分交易税',
  `minibalance` int(10) default NULL COMMENT '兑换最低余额',
  PRIMARY KEY  (`rid`),
  UNIQUE KEY `action` (`action`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='积分规则表';

#
# Dumping data for table bbs_credit_rule
#


#
# Source for table bbs_forum
#

DROP TABLE IF EXISTS `bbs_forum`;
CREATE TABLE `bbs_forum` (
  `FORUM_ID` int(11) NOT NULL auto_increment,
  `CATEGORY_ID` int(11) NOT NULL COMMENT '分区ID',
  `SITE_ID` int(11) NOT NULL COMMENT '站点ID',
  `POST_ID` int(11) default NULL COMMENT '最后回帖',
  `replyer_id` int(11) default NULL COMMENT '最后回帖会员',
  `PATH` varchar(20) NOT NULL COMMENT '访问路径',
  `TITLE` varchar(150) NOT NULL COMMENT '标题',
  `DESCRIPTION` varchar(255) default NULL COMMENT '描述',
  `KEYWORDS` varchar(255) default NULL COMMENT 'meta-keywords',
  `FORUM_RULE` varchar(255) default NULL COMMENT '版规',
  `PRIORITY` int(11) NOT NULL default '10' COMMENT '排列顺序',
  `TOPIC_TOTAL` int(11) NOT NULL default '0' COMMENT '主题总数',
  `POST_TOTAL` int(11) NOT NULL default '0' COMMENT '帖子总数',
  `POST_TODAY` int(11) NOT NULL default '0' COMMENT '今日新帖',
  `OUTER_URL` varchar(255) default NULL COMMENT '外部链接',
  `POINT_TOPIC` int(11) NOT NULL default '0' COMMENT '发贴加分',
  `POINT_REPLY` int(11) NOT NULL default '0' COMMENT '回帖加分',
  `POINT_PRIME` int(11) NOT NULL default '0' COMMENT '精华加分',
  `LAST_TIME` datetime default NULL COMMENT '最后发贴时间',
  `TOPIC_LOCK_LIMIT` int(11) NOT NULL default '0' COMMENT '锁定主题（天）',
  `moderators` varchar(100) default NULL COMMENT '版主',
  `group_views` varchar(100) default NULL COMMENT '访问会员组',
  `group_topics` varchar(100) default NULL COMMENT '发帖会员组',
  `group_replies` varchar(100) default NULL COMMENT '回复会员组',
  `POINT_AVAILABLE` tinyint(1) default NULL COMMENT '积分是否启用',
  `PRESTIGE_AVAILABLE` tinyint(1) default NULL COMMENT '威望是否启用',
  `PRESTIGE_TOPIC` int(11) default '0' COMMENT '发帖加威望',
  `PRESTIGE_REPLY` int(11) default '0' COMMENT '回帖加威望',
  `PRESTIGE_PRIME1` int(11) default '0' COMMENT '精华1加威望',
  `PRESTIGE_PRIME2` int(11) default '0' COMMENT '精华2加威望',
  `PRESTIGE_PRIME3` int(11) default '0' COMMENT '精华3加威望',
  `PRESTIGE_PRIME0` int(11) default '0' COMMENT '解除精华扣除威望',
  PRIMARY KEY  (`FORUM_ID`),
  KEY `FK_BBS_FORUM_CTG` (`CATEGORY_ID`),
  KEY `FK_BBS_FORUM_USER` (`replyer_id`),
  KEY `FK_BBS_FORUM_POST` (`POST_ID`),
  KEY `FK_BBS_FORUM_WEBSITE` (`SITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛板块';

#
# Dumping data for table bbs_forum
#

INSERT INTO `bbs_forum` VALUES (1,1,1,2,9,'sqzx','系统售前咨询','提供JEECMS商业授权相关信息咨询','jsp cms,站群,开源','如果您需要使用JEECMS进行商业建站等商业性活动，例如：政府单位、教育机构、协会团体、企业等，请您购买我们的商业授权。如有需要，欢迎与我们联系。 QQ：48955621         电话：0791-86538070、13576281815    E-mail:jeecms@126.com',10,1,1,1,'',5,0,5,'2012-05-03 11:59:18',0,'test',',14,1,2,3,4,5,6,7,8,9,10,11,12,13,',',14,1,2,3,4,5,6,7,8,9,10,11,12,13,',',14,1,2,3,4,5,6,7,8,9,10,11,12,13,',1,1,1,0,1,2,3,0);
INSERT INTO `bbs_forum` VALUES (2,2,1,3,10,'bug','BUG反馈','专门收集网友反馈的系统bug信息，及时提供bug解决方案。','jsp cms,站群,java,开源','欢迎大家积极反馈JEECMS中存在的bug，请尽量清楚的描述您的问题以及您的环境信息,我们会核实后给大家提供bug解决方案，相信有大家的支持，JEECMS会越来越好~',10,1,1,1,'',5,0,5,'2012-05-03 12:01:17',0,'test',',14,1,2,3,4,5,6,7,8,9,10,11,12,13,',',14,1,2,3,4,5,6,7,8,9,10,11,12,13,',',14,1,2,3,4,5,6,7,8,9,10,11,12,13,',1,1,1,0,1,2,3,0);

#
# Source for table bbs_forum_group_reply
#

DROP TABLE IF EXISTS `bbs_forum_group_reply`;
CREATE TABLE `bbs_forum_group_reply` (
  `FORUM_ID` int(11) NOT NULL,
  `GROUP_ID` int(11) NOT NULL,
  PRIMARY KEY  (`FORUM_ID`,`GROUP_ID`),
  KEY `FK_BBS_FORUM_GROUP_REPLY` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复权限';

#
# Dumping data for table bbs_forum_group_reply
#


#
# Source for table bbs_forum_group_topic
#

DROP TABLE IF EXISTS `bbs_forum_group_topic`;
CREATE TABLE `bbs_forum_group_topic` (
  `FORUM_ID` int(11) NOT NULL,
  `GROUP_ID` int(11) NOT NULL,
  PRIMARY KEY  (`FORUM_ID`,`GROUP_ID`),
  KEY `FK_BBS_FORUM_GROUP_TOPIC` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发贴权限';

#
# Dumping data for table bbs_forum_group_topic
#


#
# Source for table bbs_forum_group_view
#

DROP TABLE IF EXISTS `bbs_forum_group_view`;
CREATE TABLE `bbs_forum_group_view` (
  `GROUP_ID` int(11) NOT NULL,
  `FORUM_ID` int(11) NOT NULL,
  PRIMARY KEY  (`GROUP_ID`,`FORUM_ID`),
  KEY `FK_BBS_GROUP_FORUM_VIEW` (`FORUM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浏览权限';

#
# Dumping data for table bbs_forum_group_view
#


#
# Source for table bbs_forum_user
#

DROP TABLE IF EXISTS `bbs_forum_user`;
CREATE TABLE `bbs_forum_user` (
  `FORUM_ID` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY  (`FORUM_ID`,`user_id`),
  KEY `FK_BBS_FORUM_TO_USER` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版块版主';

#
# Dumping data for table bbs_forum_user
#


#
# Source for table bbs_grade
#

DROP TABLE IF EXISTS `bbs_grade`;
CREATE TABLE `bbs_grade` (
  `GRADE_ID` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `POST_ID` int(11) NOT NULL,
  `SCORE` int(11) default NULL,
  `REASON` varchar(100) default NULL,
  `GRADE_TIME` datetime default NULL,
  PRIMARY KEY  (`GRADE_ID`),
  KEY `FK_MEMBER_GRADE` (`user_id`),
  KEY `FK_POST_GRADE` (`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table bbs_grade
#


#
# Source for table bbs_group_type
#

DROP TABLE IF EXISTS `bbs_group_type`;
CREATE TABLE `bbs_group_type` (
  `GROUP_ID` int(11) NOT NULL default '0',
  `TYPE_ID` int(11) NOT NULL default '0',
  PRIMARY KEY  (`TYPE_ID`,`GROUP_ID`),
  KEY `FK_BBS_GROUP_TYPE_GROUP` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员组投票分类关联表';

#
# Dumping data for table bbs_group_type
#

INSERT INTO `bbs_group_type` VALUES (1,2);
INSERT INTO `bbs_group_type` VALUES (2,2);
INSERT INTO `bbs_group_type` VALUES (3,2);
INSERT INTO `bbs_group_type` VALUES (4,2);
INSERT INTO `bbs_group_type` VALUES (5,2);
INSERT INTO `bbs_group_type` VALUES (6,2);
INSERT INTO `bbs_group_type` VALUES (7,2);
INSERT INTO `bbs_group_type` VALUES (9,2);
INSERT INTO `bbs_group_type` VALUES (10,2);
INSERT INTO `bbs_group_type` VALUES (11,2);
INSERT INTO `bbs_group_type` VALUES (12,2);
INSERT INTO `bbs_group_type` VALUES (13,2);
INSERT INTO `bbs_group_type` VALUES (14,2);
INSERT INTO `bbs_group_type` VALUES (1,3);
INSERT INTO `bbs_group_type` VALUES (2,3);
INSERT INTO `bbs_group_type` VALUES (3,3);
INSERT INTO `bbs_group_type` VALUES (4,3);
INSERT INTO `bbs_group_type` VALUES (5,3);
INSERT INTO `bbs_group_type` VALUES (6,3);
INSERT INTO `bbs_group_type` VALUES (7,3);
INSERT INTO `bbs_group_type` VALUES (9,3);
INSERT INTO `bbs_group_type` VALUES (10,3);
INSERT INTO `bbs_group_type` VALUES (11,3);
INSERT INTO `bbs_group_type` VALUES (12,3);
INSERT INTO `bbs_group_type` VALUES (13,3);
INSERT INTO `bbs_group_type` VALUES (14,3);

#
# Source for table bbs_login_log
#

DROP TABLE IF EXISTS `bbs_login_log`;
CREATE TABLE `bbs_login_log` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL COMMENT '登录用户',
  `login_time` datetime default NULL COMMENT '登录时间',
  `logout_time` datetime default NULL COMMENT '退出时间',
  `ip` varchar(255) character set gbk default NULL COMMENT '登录ip',
  PRIMARY KEY  (`id`),
  KEY `fk_bbs_login_log_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='登录日志';

#
# Dumping data for table bbs_login_log
#

INSERT INTO `bbs_login_log` VALUES (1,9,'2012-04-14 11:16:03','2012-04-14 11:21:12','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (2,5,'2012-04-14 13:34:41','2012-04-14 13:37:50','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (3,5,'2012-04-14 14:02:55','2012-04-14 14:02:55','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (4,5,'2012-04-14 14:05:08','2012-04-14 14:55:09','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (5,5,'2012-04-14 14:59:49','2012-04-14 14:59:50','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (6,5,'2012-04-14 15:09:36','2012-04-16 14:23:19','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (10,5,'2012-04-16 14:46:07','2012-04-16 14:46:09','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (11,5,'2012-04-16 16:21:24','2012-04-16 16:21:31','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (12,5,'2012-04-16 16:52:11','2012-04-16 18:05:51','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (14,5,'2012-04-17 15:18:40','2012-04-18 16:13:45','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (16,5,'2012-04-19 09:46:23','2012-04-19 10:09:18','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (18,5,'2012-04-19 10:09:28','2012-04-19 11:34:00','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (19,5,'2012-04-19 13:36:14','2012-04-19 17:55:43','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (21,5,'2012-05-02 09:20:53','2012-05-02 15:06:35','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (28,5,'2012-05-03 10:55:56','2012-05-03 13:35:32','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (29,9,'2012-05-03 11:19:16','2012-05-03 11:28:10','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (30,9,'2012-05-03 11:58:56','2012-05-03 12:00:01','127.0.0.1');
INSERT INTO `bbs_login_log` VALUES (31,10,'2012-05-03 12:00:27','2012-05-03 13:31:22','127.0.0.1');

#
# Source for table bbs_magic_config
#

DROP TABLE IF EXISTS `bbs_magic_config`;
CREATE TABLE `bbs_magic_config` (
  `id` int(11) NOT NULL default '1' COMMENT '主键id',
  `magic_switch` tinyint(1) NOT NULL default '0' COMMENT '是否开启道具',
  `magic_discount` int(3) default NULL COMMENT '道具回收折扣',
  `magic_sofa_lines` varchar(255) character set gbk default NULL COMMENT '抢沙发台词',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='道具配置表';

#
# Dumping data for table bbs_magic_config
#

INSERT INTO `bbs_magic_config` VALUES (1,1,80,'O(∩_∩)O哈哈~，沙发是我的啦O(∩_∩)O');

#
# Source for table bbs_magic_log
#

DROP TABLE IF EXISTS `bbs_magic_log`;
CREATE TABLE `bbs_magic_log` (
  `log_id` int(11) NOT NULL auto_increment,
  `magic_id` smallint(5) NOT NULL default '0',
  `user_id` int(11) NOT NULL default '0',
  `log_time` datetime NOT NULL default '0000-00-00 00:00:00',
  `operator` tinyint(2) default NULL COMMENT '操作(0出售道具1使用道具 2丢弃道具 3购买道具,4赠送)',
  `price` int(11) default NULL COMMENT '购买价格',
  `num` int(11) default NULL COMMENT '购买数量或者赠送数量',
  `targetuid` int(11) default '0' COMMENT '赠送目标用户',
  PRIMARY KEY  (`log_id`),
  KEY `fk_magic_log_magic` (`magic_id`),
  KEY `fk_magic_log_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='道具记录表';

#
# Dumping data for table bbs_magic_log
#


#
# Source for table bbs_magic_usergroup
#

DROP TABLE IF EXISTS `bbs_magic_usergroup`;
CREATE TABLE `bbs_magic_usergroup` (
  `magicid` smallint(6) NOT NULL default '0',
  `groupid` int(11) NOT NULL default '0' COMMENT '有权限使用该道具的用户组id',
  PRIMARY KEY  (`magicid`,`groupid`),
  KEY `fk_bbs_magic_usergroup_group` (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='道具组权限';

#
# Dumping data for table bbs_magic_usergroup
#


#
# Source for table bbs_magic_usergroup_to
#

DROP TABLE IF EXISTS `bbs_magic_usergroup_to`;
CREATE TABLE `bbs_magic_usergroup_to` (
  `magicid` smallint(6) NOT NULL default '0',
  `groupid` int(11) NOT NULL default '0' COMMENT '允许被使用的用户组id',
  PRIMARY KEY  (`magicid`,`groupid`),
  KEY `fk_bbs_magic_usergroup_group` (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='允许被使用的用户组';

#
# Dumping data for table bbs_magic_usergroup_to
#


#
# Source for table bbs_member_magic
#

DROP TABLE IF EXISTS `bbs_member_magic`;
CREATE TABLE `bbs_member_magic` (
  `id` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL default '0' COMMENT '用户id',
  `magicid` smallint(6) NOT NULL default '0' COMMENT '道具id',
  `num` int(11) NOT NULL default '0' COMMENT '道具数量',
  PRIMARY KEY  (`id`),
  KEY `fk_bbs_member_magic_user` (`uid`),
  KEY `fk_bbs_member_magic_magic` (`magicid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户道具表';

#
# Dumping data for table bbs_member_magic
#


#
# Source for table bbs_operation
#

DROP TABLE IF EXISTS `bbs_operation`;
CREATE TABLE `bbs_operation` (
  `OPERATOR_ID` int(11) NOT NULL auto_increment,
  `SITE_ID` int(11) NOT NULL,
  `operater_id` int(11) NOT NULL COMMENT '操作会员',
  `REF_TYPE` char(4) NOT NULL COMMENT '引用类型',
  `REF_ID` int(11) NOT NULL default '0' COMMENT '引用ID',
  `OPT_NAME` varchar(100) NOT NULL COMMENT '操作名称',
  `OPT_REASON` varchar(255) default NULL COMMENT '操作原因',
  `OPT_TIME` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY  (`OPERATOR_ID`),
  KEY `FK_BBS_OPEATTION` (`SITE_ID`),
  KEY `FK_BBS_OPERATION_USER` (`operater_id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8 COMMENT='主题、帖子操作记录';

#
# Dumping data for table bbs_operation
#


#
# Source for table bbs_permission
#

DROP TABLE IF EXISTS `bbs_permission`;
CREATE TABLE `bbs_permission` (
  `GROUP_ID` int(11) NOT NULL,
  `PERM_KEY` varchar(20) NOT NULL COMMENT '权限key',
  `PERM_VALUE` varchar(255) default NULL COMMENT '权限value',
  KEY `FK_BBS_PERMISSION_GROUP` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛权限';

#
# Dumping data for table bbs_permission
#

INSERT INTO `bbs_permission` VALUES (1,'allow_attach','true');
INSERT INTO `bbs_permission` VALUES (1,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (1,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (1,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (1,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (1,'attach_type','');
INSERT INTO `bbs_permission` VALUES (1,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (1,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (1,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (1,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (1,'msg_per_day','100');
INSERT INTO `bbs_permission` VALUES (1,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (1,'post_per_day','100');
INSERT INTO `bbs_permission` VALUES (2,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (2,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (2,'attach_type','');
INSERT INTO `bbs_permission` VALUES (2,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (2,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (2,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (2,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (2,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (2,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (2,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (3,'allow_attach','true');
INSERT INTO `bbs_permission` VALUES (3,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (3,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (3,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (3,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (3,'attach_type','');
INSERT INTO `bbs_permission` VALUES (3,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (3,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (3,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (3,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (3,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (3,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (3,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (4,'allow_attach','true');
INSERT INTO `bbs_permission` VALUES (4,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (4,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (4,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (4,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (4,'attach_type','');
INSERT INTO `bbs_permission` VALUES (4,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (4,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (4,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (4,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (4,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (4,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (4,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (5,'allow_attach','true');
INSERT INTO `bbs_permission` VALUES (5,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (5,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (5,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (5,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (5,'attach_type','');
INSERT INTO `bbs_permission` VALUES (5,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (5,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (5,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (5,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (5,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (5,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (5,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (6,'allow_attach','true');
INSERT INTO `bbs_permission` VALUES (6,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (6,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (6,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (6,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (6,'attach_type','');
INSERT INTO `bbs_permission` VALUES (6,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (6,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (6,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (6,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (6,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (6,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (6,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (7,'allow_attach','true');
INSERT INTO `bbs_permission` VALUES (7,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (7,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (7,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (7,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (7,'attach_type','');
INSERT INTO `bbs_permission` VALUES (7,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (7,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (7,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (7,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (7,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (7,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (7,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (9,'allow_attach','true');
INSERT INTO `bbs_permission` VALUES (9,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (9,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (9,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (9,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (9,'attach_type','0');
INSERT INTO `bbs_permission` VALUES (9,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (9,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (9,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (9,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (9,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (9,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (9,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (10,'allow_attach','true');
INSERT INTO `bbs_permission` VALUES (10,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (10,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (10,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (10,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (10,'attach_type','');
INSERT INTO `bbs_permission` VALUES (10,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (10,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (10,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (10,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (10,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (10,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (10,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (11,'allow_attach','true');
INSERT INTO `bbs_permission` VALUES (11,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (11,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (11,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (11,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (11,'attach_type','');
INSERT INTO `bbs_permission` VALUES (11,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (11,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (11,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (11,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (11,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (11,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (11,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (12,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (12,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (12,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (12,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (12,'attach_type','');
INSERT INTO `bbs_permission` VALUES (12,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (12,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (12,'member_prohibit','true');
INSERT INTO `bbs_permission` VALUES (12,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (12,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (12,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (12,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (12,'post_limit','true');
INSERT INTO `bbs_permission` VALUES (12,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (12,'topic_delete','true');
INSERT INTO `bbs_permission` VALUES (12,'topic_edit','true');
INSERT INTO `bbs_permission` VALUES (12,'topic_manage','true');
INSERT INTO `bbs_permission` VALUES (12,'topic_shield','true');
INSERT INTO `bbs_permission` VALUES (12,'topic_top','3');
INSERT INTO `bbs_permission` VALUES (12,'view_ip','true');
INSERT INTO `bbs_permission` VALUES (13,'allow_reply','true');
INSERT INTO `bbs_permission` VALUES (13,'allow_topic','true');
INSERT INTO `bbs_permission` VALUES (13,'attach_max_size','0');
INSERT INTO `bbs_permission` VALUES (13,'attach_per_day','0');
INSERT INTO `bbs_permission` VALUES (13,'attach_type','0');
INSERT INTO `bbs_permission` VALUES (13,'edit_limit_minute','0');
INSERT INTO `bbs_permission` VALUES (13,'favorite_count','0');
INSERT INTO `bbs_permission` VALUES (13,'msg_count','0');
INSERT INTO `bbs_permission` VALUES (13,'msg_interval','0');
INSERT INTO `bbs_permission` VALUES (13,'msg_per_day','0');
INSERT INTO `bbs_permission` VALUES (13,'post_interval','0');
INSERT INTO `bbs_permission` VALUES (13,'post_per_day','0');
INSERT INTO `bbs_permission` VALUES (14,'allow_attach','false');
INSERT INTO `bbs_permission` VALUES (14,'allow_reply','false');
INSERT INTO `bbs_permission` VALUES (14,'allow_topic','false');
INSERT INTO `bbs_permission` VALUES (14,'attach_max_size','500');
INSERT INTO `bbs_permission` VALUES (14,'attach_per_day','50');
INSERT INTO `bbs_permission` VALUES (14,'attach_type','jpg');
INSERT INTO `bbs_permission` VALUES (14,'edit_limit_minute','2');
INSERT INTO `bbs_permission` VALUES (14,'favorite_count','2');
INSERT INTO `bbs_permission` VALUES (14,'msg_count','2');
INSERT INTO `bbs_permission` VALUES (14,'msg_interval','2');
INSERT INTO `bbs_permission` VALUES (14,'msg_per_day','2');
INSERT INTO `bbs_permission` VALUES (14,'post_interval','2');
INSERT INTO `bbs_permission` VALUES (14,'post_per_day','2');

#
# Source for table bbs_post
#

DROP TABLE IF EXISTS `bbs_post`;
CREATE TABLE `bbs_post` (
  `POST_ID` int(11) NOT NULL auto_increment,
  `TOPIC_ID` int(11) NOT NULL COMMENT '主题',
  `SITE_ID` int(11) NOT NULL COMMENT '站点',
  `CONFIG_ID` int(11) NOT NULL,
  `EDITER_ID` int(11) default NULL COMMENT '编辑器会员',
  `CREATER_ID` int(11) NOT NULL COMMENT '发贴会员',
  `CREATE_TIME` datetime NOT NULL COMMENT '发贴时间',
  `POSTER_IP` varchar(20) NOT NULL default '' COMMENT '发贴IP',
  `EDIT_TIME` datetime default NULL COMMENT '编辑时间',
  `EDITER_IP` varchar(20) default '' COMMENT '编辑者IP',
  `EDIT_COUNT` int(11) NOT NULL default '0' COMMENT '编辑次数',
  `INDEX_COUNT` int(11) NOT NULL default '1' COMMENT '第几楼',
  `STATUS` smallint(6) NOT NULL default '0' COMMENT '状态',
  `IS_AFFIX` tinyint(1) NOT NULL default '0' COMMENT '是否上传附件',
  `IS_HIDDEN` tinyint(1) default '0' COMMENT '是否有隐藏内容',
  `TYPE_ID` int(11) NOT NULL COMMENT '帖子分类id',
  `ANONYMOUS` tinyint(1) default NULL COMMENT '是否匿名',
  PRIMARY KEY  (`POST_ID`),
  KEY `FK_BBS_POST_CREATER` (`CREATER_ID`),
  KEY `FK_BBS_POST_EDITOR` (`EDITER_ID`),
  KEY `FK_BBS_POST_TOPIC` (`TOPIC_ID`),
  KEY `FK_BBS_POST_WEBSITE` (`SITE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='论坛帖子';

#
# Dumping data for table bbs_post
#

INSERT INTO `bbs_post` VALUES (2,2,1,1,NULL,9,'2012-05-03 11:59:18','127.0.0.1',NULL,NULL,0,1,0,0,0,2,0);
INSERT INTO `bbs_post` VALUES (3,3,1,1,NULL,10,'2012-05-03 12:01:17','127.0.0.1',NULL,NULL,0,1,0,0,0,3,0);

#
# Source for table bbs_post_text
#

DROP TABLE IF EXISTS `bbs_post_text`;
CREATE TABLE `bbs_post_text` (
  `POST_ID` bigint(20) NOT NULL,
  `POST_TITLE` varchar(100) default NULL COMMENT '帖子标题',
  `POST_CONTENT` longtext COMMENT '帖子内容',
  PRIMARY KEY  (`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛帖子内容';

#
# Dumping data for table bbs_post_text
#

INSERT INTO `bbs_post_text` VALUES (2,'版主帖子','版主帖子[smiley=3]');
INSERT INTO `bbs_post_text` VALUES (3,'bbs是不是有很多bug','bbs是不是有很多bug[smiley=7]');

#
# Source for table bbs_post_type
#

DROP TABLE IF EXISTS `bbs_post_type`;
CREATE TABLE `bbs_post_type` (
  `type_id` int(11) NOT NULL auto_increment,
  `type_name` varchar(255) character set gbk default NULL COMMENT '帖子分类名称',
  `priority` int(11) default NULL COMMENT '排序',
  `site_id` int(11) default NULL COMMENT '站点id',
  `forum_id` int(11) NOT NULL default '0' COMMENT '板块',
  `parent_id` int(11) default NULL COMMENT '父类id',
  PRIMARY KEY  (`type_id`),
  KEY `fk_bbs_post_type_site` (`site_id`),
  KEY `fk_bbs_post_type_parent` (`parent_id`),
  KEY `fk_bbs_type_forum` (`forum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Dumping data for table bbs_post_type
#

INSERT INTO `bbs_post_type` VALUES (2,'系统售前咨询',1,1,1,NULL);
INSERT INTO `bbs_post_type` VALUES (3,'bug反馈',1,1,2,NULL);

#
# Source for table bbs_private_msg
#

DROP TABLE IF EXISTS `bbs_private_msg`;
CREATE TABLE `bbs_private_msg` (
  `PRIVMSG_ID` bigint(20) NOT NULL,
  `TO_USER` bigint(20) NOT NULL COMMENT '收信人',
  `FROM_USER` bigint(20) NOT NULL COMMENT '发信人',
  `MSG_TYPE` smallint(6) NOT NULL default '1' COMMENT '类型（2：已发，1：已阅，0：未阅）',
  `MSG_SUBJECT` varchar(255) default NULL COMMENT '主题',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MSG_IP` varchar(20) NOT NULL default '' COMMENT 'IP地址',
  PRIMARY KEY  (`PRIVMSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人短消息';

#
# Dumping data for table bbs_private_msg
#


#
# Source for table bbs_private_msg_text
#

DROP TABLE IF EXISTS `bbs_private_msg_text`;
CREATE TABLE `bbs_private_msg_text` (
  `PRIVMSG_ID` bigint(20) NOT NULL,
  `MSG_TEXT` longtext COMMENT '个人信息内容',
  PRIMARY KEY  (`PRIVMSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人消息内容';

#
# Dumping data for table bbs_private_msg_text
#


#
# Source for table bbs_report
#

DROP TABLE IF EXISTS `bbs_report`;
CREATE TABLE `bbs_report` (
  `id` int(11) NOT NULL auto_increment,
  `report_url` varchar(255) character set gbk NOT NULL default '' COMMENT '举报地址',
  `process_user` int(11) default NULL COMMENT '处理人',
  `process_time` datetime default NULL COMMENT '处理时间',
  `process_result` varchar(255) character set gbk default NULL COMMENT '处理结果',
  `status` tinyint(1) default NULL COMMENT '处理状态0未处理。1已经处理',
  `report_time` datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '举报时间',
  PRIMARY KEY  (`id`),
  KEY `fk_bbs_report_process_user` (`process_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='举报记录';

#
# Dumping data for table bbs_report
#


#
# Source for table bbs_report_ext
#

DROP TABLE IF EXISTS `bbs_report_ext`;
CREATE TABLE `bbs_report_ext` (
  `id` int(11) NOT NULL auto_increment COMMENT '举报id',
  `report_id` int(11) NOT NULL default '0' COMMENT 'reportid',
  `report_user` int(11) NOT NULL default '0' COMMENT '举报人',
  `report_time` datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '举报时间',
  `report_reason` varchar(255) character set gbk default NULL COMMENT '举报理由',
  PRIMARY KEY  (`id`),
  KEY `fk_bbs_report_ext_report_user` (`report_user`),
  KEY `fk_bbs_report_ext_report` (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='举报扩展';

#
# Dumping data for table bbs_report_ext
#


#
# Source for table bbs_topic
#

DROP TABLE IF EXISTS `bbs_topic`;
CREATE TABLE `bbs_topic` (
  `TOPIC_ID` int(11) NOT NULL auto_increment,
  `FORUM_ID` int(11) NOT NULL COMMENT '板块',
  `LAST_POST_ID` int(11) default NULL COMMENT '最后帖',
  `FIRST_POST_ID` int(11) default NULL COMMENT '主题帖',
  `SITE_ID` int(11) NOT NULL COMMENT '站点',
  `CREATER_ID` int(11) NOT NULL COMMENT '发帖会员',
  `REPLYER_ID` int(11) NOT NULL COMMENT '最后回帖会员',
  `TITLE` varchar(100) NOT NULL COMMENT '标题',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `LAST_TIME` datetime NOT NULL COMMENT '最后回帖时间',
  `SORT_TIME` datetime NOT NULL COMMENT '用于排序',
  `VIEW_COUNT` bigint(20) NOT NULL default '0' COMMENT '浏览次数',
  `REPLY_COUNT` int(11) NOT NULL default '0' COMMENT '恢复次数',
  `TOP_LEVEL` smallint(6) NOT NULL default '0' COMMENT '固定级别',
  `PRIME_LEVEL` smallint(6) NOT NULL default '0' COMMENT '精华级别',
  `STATUS` smallint(6) NOT NULL default '0' COMMENT '状态',
  `OUTER_URL` varchar(255) default NULL COMMENT '外部链接',
  `STYLE_BOLD` tinyint(1) NOT NULL default '0' COMMENT '粗体',
  `STYLE_ITALIC` tinyint(1) NOT NULL default '0' COMMENT '斜体',
  `STYLE_COLOR` char(6) default NULL COMMENT '颜色',
  `STYLE_TIME` datetime default NULL COMMENT '样式有效时间',
  `IS_AFFIX` tinyint(1) NOT NULL default '0' COMMENT '是否上传附件',
  `HAVA_REPLY` longtext COMMENT '回复列表',
  `moderator_reply` tinyint(1) default '0' COMMENT '版主是否回复',
  `TYPE_ID` int(11) NOT NULL COMMENT '主题分类id',
  `ALLAY_REPLY` tinyint(1) default NULL COMMENT '主题是否允许回复',
  `HAS_SOFAED` tinyint(1) default NULL COMMENT '主题是否已经被抢走沙发',
  `CATEGORY` tinyint(1) default NULL COMMENT '帖子类型(0:普通帖;1:投票贴)',
  `TOTAL_COUNT` int(11) default NULL COMMENT '总票数',
  PRIMARY KEY  (`TOPIC_ID`),
  KEY `BBS_SORT_TIME` (`SORT_TIME`),
  KEY `FK_BBS_FIRST_POST` (`FIRST_POST_ID`),
  KEY `FK_BBS_LAST_POST` (`LAST_POST_ID`),
  KEY `FK_BBS_TOPIC_FORUM` (`FORUM_ID`),
  KEY `FK_BBS_TOPIC_USER_CREATE` (`CREATER_ID`),
  KEY `FK_BBS_TOPIC_USER_LAST` (`REPLYER_ID`),
  KEY `FK_BBS_TOPIC_SITE` (`SITE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='论坛主题';

#
# Dumping data for table bbs_topic
#

INSERT INTO `bbs_topic` VALUES (2,1,NULL,2,1,9,9,'版主帖子','2012-05-03 11:59:18','2012-05-03 11:59:18','2012-05-03 11:59:18',1,0,0,0,0,NULL,0,0,NULL,NULL,0,',',0,2,1,0,0,NULL);
INSERT INTO `bbs_topic` VALUES (3,2,NULL,3,1,10,10,'bbs是不是有很多bug','2012-05-03 12:01:17','2012-05-03 12:01:17','2012-05-03 12:01:17',1,0,0,0,0,NULL,0,0,NULL,NULL,0,',',0,3,1,0,1,0);

#
# Source for table bbs_topic_text
#

DROP TABLE IF EXISTS `bbs_topic_text`;
CREATE TABLE `bbs_topic_text` (
  `topic_id` int(11) NOT NULL,
  `title` varchar(100) default NULL COMMENT '主题标题',
  PRIMARY KEY  (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛主题内容';

#
# Dumping data for table bbs_topic_text
#

INSERT INTO `bbs_topic_text` VALUES (2,'版主帖子');
INSERT INTO `bbs_topic_text` VALUES (3,'bbs是不是有很多bug');

#
# Source for table bbs_user_group
#

DROP TABLE IF EXISTS `bbs_user_group`;
CREATE TABLE `bbs_user_group` (
  `GROUP_ID` int(11) NOT NULL auto_increment,
  `site_id` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL COMMENT '头衔',
  `GROUP_TYPE` smallint(6) NOT NULL default '0' COMMENT '组类别',
  `GROUP_IMG` varchar(100) default NULL COMMENT '图片',
  `GROUP_POINT` int(11) NOT NULL default '0' COMMENT '升级积分',
  `IS_DEFAULT` tinyint(1) NOT NULL default '0' COMMENT '是否默认组',
  `GRADE_NUM` int(11) default '0' COMMENT '评分',
  `magic_packet_size` int(11) default NULL COMMENT '用户组道具包容量',
  PRIMARY KEY  (`GROUP_ID`),
  KEY `FK_BBS_GROUP_SITE` (`site_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='论坛会员组';

#
# Dumping data for table bbs_user_group
#

INSERT INTO `bbs_user_group` VALUES (1,1,'白丁',1,'1',0,1,0,0);
INSERT INTO `bbs_user_group` VALUES (2,1,'童生',1,'2',10,0,0,20);
INSERT INTO `bbs_user_group` VALUES (3,1,'秀才',1,'3',50,0,0,30);
INSERT INTO `bbs_user_group` VALUES (4,1,'举人',1,'4',100,0,0,40);
INSERT INTO `bbs_user_group` VALUES (5,1,'解元',1,'5',200,0,0,50);
INSERT INTO `bbs_user_group` VALUES (6,1,'贡士',1,'6',400,0,0,60);
INSERT INTO `bbs_user_group` VALUES (7,1,'会元',1,'7',800,0,0,70);
INSERT INTO `bbs_user_group` VALUES (8,1,'进士',1,'8',1600,0,0,0);
INSERT INTO `bbs_user_group` VALUES (9,1,'探花',1,'9',3200,0,0,80);
INSERT INTO `bbs_user_group` VALUES (10,1,'榜眼',1,'10',6400,0,0,90);
INSERT INTO `bbs_user_group` VALUES (11,1,'状元',1,'11',12800,0,0,100);
INSERT INTO `bbs_user_group` VALUES (12,1,'版主',2,'21',0,0,0,0);
INSERT INTO `bbs_user_group` VALUES (13,1,'VIP会员',3,'10',0,0,0,100);
INSERT INTO `bbs_user_group` VALUES (14,1,'游客',0,'1',0,0,2,10);

#
# Source for table bbs_user_online
#

DROP TABLE IF EXISTS `bbs_user_online`;
CREATE TABLE `bbs_user_online` (
  `user_id` int(11) NOT NULL auto_increment,
  `online_latest` double(10,2) default NULL COMMENT '最后登录时长',
  `online_day` double(10,2) default NULL COMMENT '今日在线时长',
  `online_week` double(10,2) default NULL COMMENT '本周在线',
  `online_month` double(10,2) default NULL COMMENT '本月在线',
  `online_year` double(10,2) default NULL COMMENT '本年在线',
  `online_total` double(10,2) default NULL COMMENT '总在线时长',
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=gbk COMMENT='用户在线时长统计';

#
# Dumping data for table bbs_user_online
#

INSERT INTO `bbs_user_online` VALUES (5,25,225.02,225.02,225.02,225.02,225.02);
INSERT INTO `bbs_user_online` VALUES (9,1.08,6.08,6.08,6.08,6.08,6.08);
INSERT INTO `bbs_user_online` VALUES (10,90,90,90,90,90,90);

#
# Source for table bbs_vote_item
#

DROP TABLE IF EXISTS `bbs_vote_item`;
CREATE TABLE `bbs_vote_item` (
  `item_id` int(11) NOT NULL auto_increment,
  `topic_id` int(11) default NULL,
  `name` varchar(255) default NULL,
  `vote_count` int(11) NOT NULL default '0' COMMENT '票数',
  PRIMARY KEY  (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table bbs_vote_item
#

INSERT INTO `bbs_vote_item` VALUES (1,3,'非常多',0);
INSERT INTO `bbs_vote_item` VALUES (2,3,'很多',0);
INSERT INTO `bbs_vote_item` VALUES (3,3,'一般般',0);
INSERT INTO `bbs_vote_item` VALUES (4,3,'少',0);

#
# Source for table bbs_vote_record
#

DROP TABLE IF EXISTS `bbs_vote_record`;
CREATE TABLE `bbs_vote_record` (
  `record_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL,
  `topic_id` int(11) default NULL,
  `vote_time` datetime default NULL COMMENT '投票时间',
  PRIMARY KEY  (`record_id`),
  KEY `fk_vote_record_user` (`user_id`),
  KEY `fk_vote_record_topic` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table bbs_vote_record
#


#
# Source for table jb_friendship
#

DROP TABLE IF EXISTS `jb_friendship`;
CREATE TABLE `jb_friendship` (
  `friendship_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `friend_id` int(11) NOT NULL default '0',
  `status` tinyint(1) NOT NULL default '0' COMMENT '好友状态(0:申请中;1:接受;2:拒绝)',
  PRIMARY KEY  (`friendship_id`),
  KEY `fk_jb_friendship_friend` (`friend_id`),
  KEY `fk_jb_friendship_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Dumping data for table jb_friendship
#


#
# Source for table jb_message
#

DROP TABLE IF EXISTS `jb_message`;
CREATE TABLE `jb_message` (
  `msg_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `sender` int(11) default NULL COMMENT '发送人',
  `receiver` int(11) NOT NULL default '0' COMMENT '接收人',
  `content` longtext character set gbk NOT NULL COMMENT '内容',
  `create_time` datetime default NULL COMMENT '发送时间',
  `is_sys` tinyint(1) NOT NULL default '0' COMMENT '是否为系统消息(0:不是;1:是)',
  `msg_type` int(2) NOT NULL default '1' COMMENT '1消息，2留言,3打招呼',
  `is_read` tinyint(1) default '0' COMMENT '信息状态 0未读 1已读',
  PRIMARY KEY  (`msg_id`),
  KEY `fk_jb_message_user` (`user_id`),
  KEY `fk_jb_message_receiver` (`receiver`),
  KEY `fk_jb_message_sender` (`sender`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

#
# Dumping data for table jb_message
#


#
# Source for table jb_message_reply
#

DROP TABLE IF EXISTS `jb_message_reply`;
CREATE TABLE `jb_message_reply` (
  `reply_id` int(11) NOT NULL auto_increment,
  `msg_id` int(11) NOT NULL default '0',
  `sender` int(11) default NULL,
  `receiver` int(11) NOT NULL default '0',
  `content` longtext NOT NULL,
  `create_time` datetime default NULL,
  PRIMARY KEY  (`reply_id`),
  KEY `fk_jb_reply_sender` (`sender`),
  KEY `fk_jb_reply_receiver` (`receiver`),
  KEY `fk_jb_reply_msg` (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

#
# Dumping data for table jb_message_reply
#


#
# Source for table jb_user
#

DROP TABLE IF EXISTS `jb_user`;
CREATE TABLE `jb_user` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `email` varchar(100) default NULL COMMENT '邮箱',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `register_ip` varchar(50) NOT NULL default '127.0.0.1' COMMENT '注册IP',
  `last_login_time` datetime NOT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) NOT NULL default '127.0.0.1' COMMENT '最后登录IP',
  `login_count` int(11) NOT NULL default '0' COMMENT '登录次数',
  `upload_total` bigint(20) NOT NULL default '0' COMMENT '上传总大小',
  `upload_size` int(11) NOT NULL default '0' COMMENT '上传大小',
  `upload_date` date default NULL COMMENT '上传日期',
  `is_admin` tinyint(1) NOT NULL default '0' COMMENT '是否管理员',
  `is_disabled` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  `PROHIBIT_POST` smallint(6) NOT NULL default '0' COMMENT '禁言(0:不禁言;1:永久禁言;2:定期禁言)',
  `PROHIBIT_TIME` datetime default NULL COMMENT '解禁时间',
  `GRADE_TODAY` int(11) default '0' COMMENT '今日评分',
  `UPLOAD_TODAY` int(11) default '0' COMMENT '今日上传',
  `POINT` bigint(20) default '0' COMMENT '积分',
  `INTRODUCTION` varchar(255) default NULL COMMENT '个人介绍',
  `SIGNED` varchar(255) default NULL COMMENT '个性签名',
  `AVATAR` varchar(100) default NULL COMMENT '个人头像',
  `AVATAR_TYPE` smallint(6) default '0' COMMENT '头像类型',
  `TOPIC_COUNT` int(11) default '0' COMMENT '主题总数',
  `REPLY_COUNT` int(11) default '0' COMMENT '回复总数',
  `PRIME_COUNT` int(11) default '0' COMMENT '精华总数',
  `POST_TODAY` int(11) default '0' COMMENT '今日发帖',
  `LAST_POST_TIME` datetime default NULL COMMENT '最后回帖时间',
  `PRESTIGE` bigint(20) default '0' COMMENT '威望',
  `magic_packet_size` int(11) default NULL COMMENT '用户道具包现有容量',
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `ak_username` (`username`),
  KEY `FK_BBS_MEMBER_MEMBERGROUP` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='BBS用户表';

#
# Dumping data for table jb_user
#

INSERT INTO `jb_user` VALUES (5,1,'admin','wudily2010@1631.com','2011-03-17 12:02:04','127.0.0.1','2012-05-03 11:40:45','127.0.0.1',137,0,0,'2011-03-17',1,0,0,NULL,NULL,0,22,NULL,NULL,'/jeebbs3beta/user/images/201204/19171421xnzd.jpg',0,2,12,0,14,NULL,0,0);
INSERT INTO `jb_user` VALUES (9,12,'test','123@123.com','2011-04-11 00:38:08','127.0.0.1','2012-05-03 11:58:56','127.0.0.1',62,0,0,'2011-04-11',0,0,0,NULL,5,0,113,NULL,'','none.gif',0,22,6,0,28,NULL,1,0);
INSERT INTO `jb_user` VALUES (10,1,'chen','chen@163.com','2012-05-03 11:38:45','127.0.0.1','2012-05-03 12:00:27','127.0.0.1',1,0,0,'2012-05-03',0,0,0,NULL,NULL,0,5,NULL,NULL,NULL,0,1,0,0,1,NULL,1,0);

#
# Source for table jb_user_ext
#

DROP TABLE IF EXISTS `jb_user_ext`;
CREATE TABLE `jb_user_ext` (
  `user_id` int(11) NOT NULL,
  `realname` varchar(100) default NULL COMMENT '真实姓名',
  `gender` tinyint(1) default NULL COMMENT '性别',
  `avatar` varchar(100) default NULL COMMENT '用户头像',
  `birthday` datetime default NULL COMMENT '出生日期',
  `intro` varchar(255) default NULL COMMENT '个人介绍',
  `comefrom` varchar(150) default NULL COMMENT '来自',
  `qq` varchar(100) default NULL COMMENT 'QQ',
  `msn` varchar(100) default NULL COMMENT 'MSN',
  `phone` varchar(50) default NULL COMMENT '电话',
  `moble` varchar(50) default NULL COMMENT '手机',
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='BBS用户扩展信息表';

#
# Dumping data for table jb_user_ext
#

INSERT INTO `jb_user_ext` VALUES (5,'abc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `jb_user_ext` VALUES (9,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `jb_user_ext` VALUES (10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

#
# Source for table jc_config
#

DROP TABLE IF EXISTS `jc_config`;
CREATE TABLE `jc_config` (
  `config_id` int(11) NOT NULL,
  `context_path` varchar(20) default '/JeeCms' COMMENT '部署路径',
  `servlet_point` varchar(20) default NULL COMMENT 'Servlet挂载点',
  `port` int(11) default NULL COMMENT '端口',
  `db_file_uri` varchar(50) NOT NULL default '/dbfile.svl?n=' COMMENT '数据库附件访问地址',
  `is_upload_to_db` tinyint(1) NOT NULL default '0' COMMENT '上传附件至数据库',
  `def_img` varchar(255) NOT NULL default '/JeeCms/r/cms/www/default/no_picture.gif' COMMENT '图片不存在时默认图片',
  `login_url` varchar(255) NOT NULL default '/login.jspx' COMMENT '登录地址',
  `process_url` varchar(255) default NULL COMMENT '登录后处理地址',
  `mark_on` tinyint(1) NOT NULL default '1' COMMENT '开启图片水印',
  `mark_width` int(11) NOT NULL default '120' COMMENT '图片最小宽度',
  `mark_height` int(11) NOT NULL default '120' COMMENT '图片最小高度',
  `mark_image` varchar(100) default '/r/cms/www/watermark.png' COMMENT '图片水印',
  `mark_content` varchar(100) NOT NULL default 'www.jeecms.com' COMMENT '文字水印内容',
  `mark_size` int(11) NOT NULL default '20' COMMENT '文字水印大小',
  `mark_color` varchar(10) NOT NULL default '#FF0000' COMMENT '文字水印颜色',
  `mark_alpha` int(11) NOT NULL default '50' COMMENT '水印透明度（0-100）',
  `mark_position` int(11) NOT NULL default '1' COMMENT '水印位置(0-5)',
  `mark_offset_x` int(11) NOT NULL default '0' COMMENT 'x坐标偏移量',
  `mark_offset_y` int(11) NOT NULL default '0' COMMENT 'y坐标偏移量',
  `count_clear_time` date NOT NULL COMMENT '计数器清除时间',
  `count_copy_time` datetime NOT NULL COMMENT '计数器拷贝时间',
  `download_code` varchar(32) NOT NULL default 'jeecms' COMMENT '下载防盗链md5混淆码',
  `download_time` int(11) NOT NULL default '12' COMMENT '下载有效时间（小时）',
  `email_host` varchar(50) default NULL COMMENT '邮件发送服务器',
  `email_encoding` varchar(20) default NULL COMMENT '邮件发送编码',
  `email_username` varchar(100) default NULL COMMENT '邮箱用户名',
  `email_password` varchar(100) default NULL COMMENT '邮箱密码',
  `email_personal` varchar(100) default NULL COMMENT '邮箱发件人',
  PRIMARY KEY  (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS配置表';

#
# Dumping data for table jc_config
#

INSERT INTO `jc_config` VALUES (1,'',NULL,8888,'/dbfile.svl?n=',0,'/r/cms/www/no_picture.gif','/login.jspx',NULL,1,120,120,'/r/cms/www/watermark.png','www.jeecms.com',20,'#FF0000',50,1,0,0,'2011-12-26','2011-12-26 13:32:26','jeecms',12,NULL,NULL,NULL,NULL,NULL);

#
# Source for table jc_friendlink
#

DROP TABLE IF EXISTS `jc_friendlink`;
CREATE TABLE `jc_friendlink` (
  `friendlink_id` int(11) NOT NULL auto_increment,
  `site_id` int(11) NOT NULL,
  `friendlinkctg_id` int(11) NOT NULL,
  `site_name` varchar(150) NOT NULL COMMENT '网站名称',
  `domain` varchar(255) NOT NULL COMMENT '网站地址',
  `logo` varchar(150) default NULL COMMENT '图标',
  `email` varchar(100) default NULL COMMENT '站长邮箱',
  `description` varchar(255) default NULL COMMENT '描述',
  `views` int(11) NOT NULL default '0' COMMENT '点击次数',
  `is_enabled` char(1) NOT NULL default '1' COMMENT '是否显示',
  `priority` int(11) NOT NULL default '10' COMMENT '排列顺序',
  PRIMARY KEY  (`friendlink_id`),
  KEY `fk_jc_ctg_friendlink` (`friendlinkctg_id`),
  KEY `fk_jc_friendlink_site` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS友情链接';

#
# Dumping data for table jc_friendlink
#


#
# Source for table jc_friendlink_ctg
#

DROP TABLE IF EXISTS `jc_friendlink_ctg`;
CREATE TABLE `jc_friendlink_ctg` (
  `friendlinkctg_id` int(11) NOT NULL auto_increment,
  `site_id` int(11) NOT NULL,
  `friendlinkctg_name` varchar(50) NOT NULL COMMENT '名称',
  `priority` int(11) NOT NULL default '10' COMMENT '排列顺序',
  PRIMARY KEY  (`friendlinkctg_id`),
  KEY `fk_jc_friendlinkctg_site` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS友情链接类别';

#
# Dumping data for table jc_friendlink_ctg
#


#
# Source for table jc_sensitivity
#

DROP TABLE IF EXISTS `jc_sensitivity`;
CREATE TABLE `jc_sensitivity` (
  `sensitivity_id` int(11) NOT NULL auto_increment,
  `site_id` int(11) default NULL,
  `search` varchar(255) NOT NULL COMMENT '敏感词',
  `replacement` varchar(255) NOT NULL COMMENT '替换词',
  PRIMARY KEY  (`sensitivity_id`),
  KEY `fk_sensitivity_site` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS敏感词表';

#
# Dumping data for table jc_sensitivity
#


#
# Source for table jc_site
#

DROP TABLE IF EXISTS `jc_site`;
CREATE TABLE `jc_site` (
  `site_id` int(11) NOT NULL auto_increment,
  `config_id` int(11) NOT NULL COMMENT '配置ID',
  `ftp_upload_id` int(11) default NULL COMMENT '上传ftp',
  `domain` varchar(50) NOT NULL COMMENT '域名',
  `site_path` varchar(20) NOT NULL COMMENT '路径',
  `site_name` varchar(100) NOT NULL COMMENT '网站名称',
  `short_name` varchar(100) NOT NULL COMMENT '简短名称',
  `protocol` varchar(20) NOT NULL default 'http://' COMMENT '协议',
  `dynamic_suffix` varchar(10) NOT NULL default '.jhtml' COMMENT '动态页后缀',
  `static_suffix` varchar(10) NOT NULL default '.html' COMMENT '静态页后缀',
  `static_dir` varchar(50) default NULL COMMENT '静态页存放目录',
  `is_index_to_root` char(1) NOT NULL default '0' COMMENT '是否使用将首页放在根目录下',
  `is_static_index` char(1) NOT NULL default '0' COMMENT '是否静态化首页',
  `locale_admin` varchar(10) NOT NULL default 'zh_CN' COMMENT '后台本地化',
  `locale_front` varchar(10) NOT NULL default 'zh_CN' COMMENT '前台本地化',
  `tpl_solution` varchar(50) NOT NULL default 'default' COMMENT '模板方案',
  `final_step` tinyint(4) NOT NULL default '2' COMMENT '终审级别',
  `after_check` tinyint(4) NOT NULL default '2' COMMENT '审核后(1:不能修改删除;2:修改后退回;3:修改后不变)',
  `is_relative_path` char(1) NOT NULL default '1' COMMENT '是否使用相对路径',
  `is_recycle_on` char(1) NOT NULL default '1' COMMENT '是否开启回收站',
  `domain_alias` varchar(255) default NULL COMMENT '域名别名',
  `domain_redirect` varchar(255) default NULL COMMENT '域名重定向',
  `creditex_id` int(11) default '1' COMMENT '积分交易规则id',
  PRIMARY KEY  (`site_id`),
  UNIQUE KEY `ak_domain` (`domain`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='站点表';

#
# Dumping data for table jc_site
#

INSERT INTO `jc_site` VALUES (1,1,NULL,'localhost','www','JEEBBS论坛','jeebbs','http://','.jhtml','.html',NULL,'0','0','zh_CN','zh_CN','blue',2,2,'1','1','','',1);

#
# Source for table jo_authentication
#

DROP TABLE IF EXISTS `jo_authentication`;
CREATE TABLE `jo_authentication` (
  `authentication_id` char(32) NOT NULL COMMENT '认证ID',
  `userid` int(11) NOT NULL COMMENT '用户ID',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `email` varchar(100) default NULL COMMENT '邮箱',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `login_ip` varchar(50) NOT NULL COMMENT '登录ip',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY  (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证信息表';

#
# Dumping data for table jo_authentication
#

INSERT INTO `jo_authentication` VALUES ('21b96bacb7f34947b219fc57e5feff5e',5,'admin','wudily2010@1631.com','2012-04-19 10:09:28','127.0.0.1','2012-04-19 11:34:00');
INSERT INTO `jo_authentication` VALUES ('22cc4c8f46b841d4934272523d9e3d33',18,'xinyonghu','pass@pa.comaaa','2012-05-02 10:28:18','127.0.0.1','2012-05-02 12:10:35');
INSERT INTO `jo_authentication` VALUES ('48d01318dae84b70b8ee6025719b3171',9,'test','123@123.com','2012-05-03 11:19:16','127.0.0.1','2012-05-03 11:28:10');
INSERT INTO `jo_authentication` VALUES ('69b54e32ffaf48409bbfdb60bb733000',5,'admin',NULL,'2012-05-03 10:55:56','127.0.0.1','2012-05-03 11:27:09');
INSERT INTO `jo_authentication` VALUES ('759d42085c5749cfa7e76de5c4e1d72e',19,'xinxin','ad@la.com','2012-04-19 09:48:58','127.0.0.1','2012-04-19 11:33:13');
INSERT INTO `jo_authentication` VALUES ('7f9dcf59208041188cddd7d35787a849',6,'korven','jeecms@163.com','2012-05-02 17:50:06','127.0.0.1','2012-05-02 18:04:09');
INSERT INTO `jo_authentication` VALUES ('84b9c779add84c339d0fc870cb92311d',19,'xinxin','ad@la.com','2012-04-18 09:41:29','127.0.0.1','2012-04-18 16:43:50');
INSERT INTO `jo_authentication` VALUES ('92020a1ede2247348883184184e1b37b',5,'admin',NULL,'2012-05-02 09:20:53','127.0.0.1','2012-05-02 12:18:54');
INSERT INTO `jo_authentication` VALUES ('a2b4a46a0f684a35877e96860c4b1282',5,'admin',NULL,'2012-05-03 11:40:45','127.0.0.1','2012-05-03 13:35:57');
INSERT INTO `jo_authentication` VALUES ('bcf1e070828f4d5cad7f162575c8249e',5,'admin',NULL,'2012-05-02 15:12:38','127.0.0.1','2012-05-02 15:26:25');
INSERT INTO `jo_authentication` VALUES ('bd59a64dbe4041feb2f889699d48bfea',18,'xinyonghu','pass@pa.comaaa','2012-05-02 17:14:46','127.0.0.1','2012-05-02 18:04:55');
INSERT INTO `jo_authentication` VALUES ('dd54b46b56514a4ab4210abb7fdde92c',10,'chen','chen@163.com','2012-05-03 12:00:27','127.0.0.1','2012-05-03 13:31:22');
INSERT INTO `jo_authentication` VALUES ('f38daf4ce4764861ba087ac8107ff01e',5,'admin','wudily2010@1631.com','2012-04-19 13:36:14','127.0.0.1','2012-04-19 18:00:43');

#
# Source for table jo_config
#

DROP TABLE IF EXISTS `jo_config`;
CREATE TABLE `jo_config` (
  `cfg_key` varchar(50) NOT NULL COMMENT '配置KEY',
  `cfg_value` varchar(255) default NULL COMMENT '配置VALUE',
  PRIMARY KEY  (`cfg_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置表';

#
# Dumping data for table jo_config
#

INSERT INTO `jo_config` VALUES ('email_encoding','utf-8');
INSERT INTO `jo_config` VALUES ('email_host','smtp.163.com');
INSERT INTO `jo_config` VALUES ('email_password','jeecms2012strong');
INSERT INTO `jo_config` VALUES ('email_personal','jeecms');
INSERT INTO `jo_config` VALUES ('email_port',NULL);
INSERT INTO `jo_config` VALUES ('email_username','jeecms2012@163.com');
INSERT INTO `jo_config` VALUES ('login_error_interval','30');
INSERT INTO `jo_config` VALUES ('login_error_times','2');
INSERT INTO `jo_config` VALUES ('message_forgotpassword_subject','JEECMS会员密码找回信息');
INSERT INTO `jo_config` VALUES ('message_forgotpassword_text','感谢您使用JEECMS系统会员密码找回功能，请记住以下找回信息：\r\n用户ID：${uid}\r\n用户名：${username}\r\n您的新密码为：${resetPwd}\r\n请访问如下链接新密码才能生效：\r\nhttp://localhost:8080/jeebbs3beta/member/password_reset.jspx?uid=${uid}&key=${resetKey}\r\n');
INSERT INTO `jo_config` VALUES ('message_register_subject','JEECMS会员注册信息');
INSERT INTO `jo_config` VALUES ('message_register_text','${username}您好：\r\n欢迎您注册JEECMS系统会员\r\n请点击以下链接进行激活\r\nhttp://localhost:8080/jeebbs3beta/active.jspx?username=${username}&key=${activationCode}\r\n');
INSERT INTO `jo_config` VALUES ('message_subject','JEECMS会员密码找回信息');
INSERT INTO `jo_config` VALUES ('message_text','感谢您使用JEECMS系统会员密码找回功能，请记住以下找回信息：\r\n用户ID：${uid}\r\n用户名：${username}\r\n您的新密码为：${resetPwd}\r\n请访问如下链接新密码才能生效：\r\nhttp://localhost/member/password_reset.jspx?uid=${uid}&key=${resetKey}\r\n');

#
# Source for table jo_ftp
#

DROP TABLE IF EXISTS `jo_ftp`;
CREATE TABLE `jo_ftp` (
  `ftp_id` int(11) NOT NULL auto_increment,
  `ftp_name` varchar(100) NOT NULL COMMENT '名称',
  `ip` varchar(50) NOT NULL COMMENT 'IP',
  `port` int(11) NOT NULL default '21' COMMENT '端口号',
  `username` varchar(100) default NULL COMMENT '登录名',
  `password` varchar(100) default NULL COMMENT '登陆密码',
  `encoding` varchar(20) NOT NULL default 'UTF-8' COMMENT '编码',
  `timeout` int(11) default NULL COMMENT '超时时间',
  `ftp_path` varchar(255) default NULL COMMENT '路径',
  `url` varchar(255) NOT NULL COMMENT '访问URL',
  PRIMARY KEY  (`ftp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='FTP表';

#
# Dumping data for table jo_ftp
#


#
# Source for table jo_template
#

DROP TABLE IF EXISTS `jo_template`;
CREATE TABLE `jo_template` (
  `tpl_name` varchar(150) NOT NULL COMMENT '模板名称',
  `tpl_source` longtext COMMENT '模板内容',
  `last_modified` bigint(20) NOT NULL COMMENT '最后修改时间',
  `is_directory` tinyint(1) NOT NULL default '0' COMMENT '是否目录',
  PRIMARY KEY  (`tpl_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板表';

#
# Dumping data for table jo_template
#


#
# Source for table jo_upload
#

DROP TABLE IF EXISTS `jo_upload`;
CREATE TABLE `jo_upload` (
  `filename` varchar(150) NOT NULL COMMENT '文件名',
  `length` int(11) NOT NULL COMMENT '文件大小(字节)',
  `last_modified` bigint(20) NOT NULL COMMENT '最后修改时间',
  `content` longblob NOT NULL COMMENT '内容',
  PRIMARY KEY  (`filename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传附件表';

#
# Dumping data for table jo_upload
#


#
# Source for table jo_user
#

DROP TABLE IF EXISTS `jo_user`;
CREATE TABLE `jo_user` (
  `user_id` int(11) NOT NULL auto_increment COMMENT '用户ID',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `email` varchar(100) default NULL COMMENT '电子邮箱',
  `password` char(32) NOT NULL COMMENT '密码',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `register_ip` varchar(50) NOT NULL default '127.0.0.1' COMMENT '注册IP',
  `last_login_time` datetime NOT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) NOT NULL default '127.0.0.1' COMMENT '最后登录IP',
  `login_count` int(11) NOT NULL default '0' COMMENT '登录次数',
  `reset_key` char(32) default NULL COMMENT '重置密码KEY',
  `reset_pwd` varchar(10) default NULL COMMENT '重置密码VALUE',
  `activation` tinyint(1) NOT NULL default '0' COMMENT '激活状态',
  `activation_code` char(32) default NULL COMMENT '激活码',
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `ak_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Dumping data for table jo_user
#

INSERT INTO `jo_user` VALUES (5,'admin',NULL,'5f4dcc3b5aa765d61d8327deb882cf99','2011-03-17 12:02:04','127.0.0.1','2012-05-03 11:40:45','127.0.0.1',137,NULL,NULL,1,NULL);
INSERT INTO `jo_user` VALUES (9,'test','123@123.com','5f4dcc3b5aa765d61d8327deb882cf99','2011-04-11 00:38:08','127.0.0.1','2012-05-03 11:58:56','127.0.0.1',62,NULL,NULL,1,NULL);
INSERT INTO `jo_user` VALUES (10,'chen','chen@163.com','a1a8887793acfc199182a649e905daab','2012-05-03 11:38:45','127.0.0.1','2012-05-03 12:00:27','127.0.0.1',1,NULL,NULL,1,NULL);

#
#  Foreign keys for table attachment
#

ALTER TABLE `attachment`
ADD CONSTRAINT `FK_attachment_post` FOREIGN KEY (`post_id`) REFERENCES `bbs_post` (`POST_ID`);

#
#  Foreign keys for table bbs_category
#

ALTER TABLE `bbs_category`
ADD CONSTRAINT `FK_BBS_CTG_SITE` FOREIGN KEY (`SITE_ID`) REFERENCES `jc_site` (`site_id`);

#
#  Foreign keys for table bbs_category_user
#

ALTER TABLE `bbs_category_user`
ADD CONSTRAINT `FK_BBS_CATEGORY_TO_USER` FOREIGN KEY (`user_id`) REFERENCES `jb_user` (`user_id`),
ADD CONSTRAINT `FK_BBS_USER_TO_CATEGORY` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `bbs_category` (`CATEGORY_ID`);

#
#  Foreign keys for table bbs_config
#

ALTER TABLE `bbs_config`
ADD CONSTRAINT `FK_BBS_CONFIG_SITE` FOREIGN KEY (`site_id`) REFERENCES `jc_site` (`site_id`);

#
#  Foreign keys for table bbs_forum
#

ALTER TABLE `bbs_forum`
ADD CONSTRAINT `FK_BBS_FORUM_CTG` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `bbs_category` (`CATEGORY_ID`),
ADD CONSTRAINT `FK_BBS_FORUM_POST` FOREIGN KEY (`POST_ID`) REFERENCES `bbs_post` (`POST_ID`),
ADD CONSTRAINT `FK_BBS_FORUM_USER` FOREIGN KEY (`replyer_id`) REFERENCES `jb_user` (`user_id`),
ADD CONSTRAINT `FK_BBS_FORUM_WEBSITE` FOREIGN KEY (`SITE_ID`) REFERENCES `jc_site` (`site_id`);

#
#  Foreign keys for table bbs_forum_group_reply
#

ALTER TABLE `bbs_forum_group_reply`
ADD CONSTRAINT `FK_BBS_FORUM_GROUP_REPLY` FOREIGN KEY (`GROUP_ID`) REFERENCES `bbs_user_group` (`GROUP_ID`),
ADD CONSTRAINT `FK_BBS_GROUP_FORUM_REPLY` FOREIGN KEY (`FORUM_ID`) REFERENCES `bbs_forum` (`FORUM_ID`);

#
#  Foreign keys for table bbs_forum_group_topic
#

ALTER TABLE `bbs_forum_group_topic`
ADD CONSTRAINT `FK_BBS_FORUM_GROUP_TOPIC` FOREIGN KEY (`GROUP_ID`) REFERENCES `bbs_user_group` (`GROUP_ID`),
ADD CONSTRAINT `FK_BBS_GROUP_FORUM_TOPIC` FOREIGN KEY (`FORUM_ID`) REFERENCES `bbs_forum` (`FORUM_ID`);

#
#  Foreign keys for table bbs_forum_group_view
#

ALTER TABLE `bbs_forum_group_view`
ADD CONSTRAINT `FK_BBS_FORUM_GROUP_VIEW` FOREIGN KEY (`GROUP_ID`) REFERENCES `bbs_user_group` (`GROUP_ID`),
ADD CONSTRAINT `FK_BBS_GROUP_FORUM_VIEW` FOREIGN KEY (`FORUM_ID`) REFERENCES `bbs_forum` (`FORUM_ID`);

#
#  Foreign keys for table bbs_forum_user
#

ALTER TABLE `bbs_forum_user`
ADD CONSTRAINT `FK_BBS_FORUM_TO_USER` FOREIGN KEY (`user_id`) REFERENCES `jb_user` (`user_id`),
ADD CONSTRAINT `FK_BBS_USER_TO_FORUM` FOREIGN KEY (`FORUM_ID`) REFERENCES `bbs_forum` (`FORUM_ID`);

#
#  Foreign keys for table bbs_grade
#

ALTER TABLE `bbs_grade`
ADD CONSTRAINT `FK_MEMBER_GRADE` FOREIGN KEY (`user_id`) REFERENCES `jb_user` (`user_id`),
ADD CONSTRAINT `FK_POST_GRADE` FOREIGN KEY (`POST_ID`) REFERENCES `bbs_post` (`POST_ID`);

#
#  Foreign keys for table bbs_group_type
#

ALTER TABLE `bbs_group_type`
ADD CONSTRAINT `FK_BBS_GROUP_TYPE_GROUP` FOREIGN KEY (`GROUP_ID`) REFERENCES `bbs_user_group` (`GROUP_ID`),
ADD CONSTRAINT `FK_BBS_GROUP_TYPE_TYPE` FOREIGN KEY (`TYPE_ID`) REFERENCES `bbs_post_type` (`type_id`);

#
#  Foreign keys for table bbs_login_log
#

ALTER TABLE `bbs_login_log`
ADD CONSTRAINT `fk_bbs_login_log_user` FOREIGN KEY (`user_id`) REFERENCES `jb_user` (`user_id`);

#
#  Foreign keys for table bbs_magic_log
#

ALTER TABLE `bbs_magic_log`
ADD CONSTRAINT `fk_magic_log_magic` FOREIGN KEY (`magic_id`) REFERENCES `bbs_common_magic` (`magicid`),
ADD CONSTRAINT `fk_magic_log_user` FOREIGN KEY (`user_id`) REFERENCES `jb_user` (`user_id`);

#
#  Foreign keys for table bbs_operation
#

ALTER TABLE `bbs_operation`
ADD CONSTRAINT `FK_BBS_OPEATTION` FOREIGN KEY (`SITE_ID`) REFERENCES `jc_site` (`site_id`),
ADD CONSTRAINT `FK_BBS_OPERATION_USER` FOREIGN KEY (`operater_id`) REFERENCES `jb_user` (`user_id`);

#
#  Foreign keys for table bbs_permission
#

ALTER TABLE `bbs_permission`
ADD CONSTRAINT `FK_BBS_PERMISSION_GROUP` FOREIGN KEY (`GROUP_ID`) REFERENCES `bbs_user_group` (`GROUP_ID`);

#
#  Foreign keys for table bbs_post
#

ALTER TABLE `bbs_post`
ADD CONSTRAINT `FK_BBS_POST_CREATER` FOREIGN KEY (`CREATER_ID`) REFERENCES `jb_user` (`user_id`),
ADD CONSTRAINT `FK_BBS_POST_EDITOR` FOREIGN KEY (`EDITER_ID`) REFERENCES `jb_user` (`user_id`),
ADD CONSTRAINT `FK_BBS_POST_TOPIC` FOREIGN KEY (`TOPIC_ID`) REFERENCES `bbs_topic` (`TOPIC_ID`),
ADD CONSTRAINT `FK_BBS_POST_WEBSITE` FOREIGN KEY (`SITE_ID`) REFERENCES `jc_site` (`site_id`);

#
#  Foreign keys for table bbs_post_type
#

ALTER TABLE `bbs_post_type`
ADD CONSTRAINT `fk_bbs_post_type_parent` FOREIGN KEY (`parent_id`) REFERENCES `bbs_post_type` (`type_id`),
ADD CONSTRAINT `fk_bbs_post_type_site` FOREIGN KEY (`site_id`) REFERENCES `jc_site` (`site_id`),
ADD CONSTRAINT `fk_bbs_type_forum` FOREIGN KEY (`forum_id`) REFERENCES `bbs_forum` (`FORUM_ID`);

#
#  Foreign keys for table bbs_report
#

ALTER TABLE `bbs_report`
ADD CONSTRAINT `fk_bbs_report_process_user` FOREIGN KEY (`process_user`) REFERENCES `jb_user` (`user_id`);

#
#  Foreign keys for table bbs_report_ext
#

ALTER TABLE `bbs_report_ext`
ADD CONSTRAINT `fk_bbs_report_ext_report` FOREIGN KEY (`report_id`) REFERENCES `bbs_report` (`id`),
ADD CONSTRAINT `fk_bbs_report_ext_report_user` FOREIGN KEY (`report_user`) REFERENCES `jb_user` (`user_id`);

#
#  Foreign keys for table bbs_topic
#

ALTER TABLE `bbs_topic`
ADD CONSTRAINT `FK_BBS_FIRST_POST` FOREIGN KEY (`FIRST_POST_ID`) REFERENCES `bbs_post` (`POST_ID`),
ADD CONSTRAINT `FK_BBS_LAST_POST` FOREIGN KEY (`LAST_POST_ID`) REFERENCES `bbs_post` (`POST_ID`),
ADD CONSTRAINT `FK_BBS_TOPIC_FORUM` FOREIGN KEY (`FORUM_ID`) REFERENCES `bbs_forum` (`FORUM_ID`),
ADD CONSTRAINT `FK_BBS_TOPIC_SITE` FOREIGN KEY (`SITE_ID`) REFERENCES `jc_site` (`site_id`),
ADD CONSTRAINT `FK_BBS_TOPIC_USER_CREATE` FOREIGN KEY (`CREATER_ID`) REFERENCES `jb_user` (`user_id`),
ADD CONSTRAINT `FK_BBS_TOPIC_USER_LAST` FOREIGN KEY (`REPLYER_ID`) REFERENCES `jb_user` (`user_id`);

#
#  Foreign keys for table bbs_user_group
#

ALTER TABLE `bbs_user_group`
ADD CONSTRAINT `FK_BBS_GROUP_SITE` FOREIGN KEY (`site_id`) REFERENCES `jc_site` (`site_id`);

#
#  Foreign keys for table bbs_user_online
#

ALTER TABLE `bbs_user_online`
ADD CONSTRAINT `fk_bbs_user_online_user` FOREIGN KEY (`user_id`) REFERENCES `jb_user` (`user_id`);

#
#  Foreign keys for table jb_user
#

ALTER TABLE `jb_user`
ADD CONSTRAINT `FK_BBS_MEMBER_MEMBERGROUP` FOREIGN KEY (`group_id`) REFERENCES `bbs_user_group` (`GROUP_ID`);