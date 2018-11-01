SET MODE MYSQL;

CREATE TABLE `news` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `source` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `href` VARCHAR(255) NOT NULL,
  `profile` VARCHAR(2000),
  `stmp` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `special_analyse_financeradio` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `radio` BIGINT(16) NOT NULL,
  `updateDate` DATE NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `special_analyse_stockradio` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `radio1` DOUBLE NOT NULL,
  `radio2` DOUBLE NOT NULL,
  `updateDate` DATE NOT NULL,
  PRIMARY KEY (`id`)
);

-- 创建资金流向数据表
CREATE TABLE `fund_flow` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `updateDate` DATE NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `index` SMALLINT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `flow_today` DOUBLE NOT NULL,
  `flow_10` DOUBLE NOT NULL,
  `flow_10_avg` DOUBLE NOT NULL,
  `flow_20` DOUBLE NOT NULL,
  `flow_20_avg` DOUBLE NOT NULL,
  `flow_60` DOUBLE NOT NULL,
  `flow_60_avg` DOUBLE NOT NULL,
  `flow_120` DOUBLE NOT NULL,
  `flow_120_avg` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
);
