#---------------------------------------------------------#
#
#                    2017-07-07 14:59:47
#
#---------------------------------------------------------#

/*创建资金流向数据表*/
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
