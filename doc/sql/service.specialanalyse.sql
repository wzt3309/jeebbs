#---------------------------------------------------------#
#
#                    2017-08-19 19:41:14
#
#---------------------------------------------------------#
# 创建 special_analyse_financeradio 表
CREATE TABLE `special_analyse_financeradio` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `radio` BIGINT(16) NOT NULL,
  `updateDate` DATE NOT NULL,
  PRIMARY KEY (`id`)
);

#---------------------------------------------------------#
#
#                    2017-08-17 16:48:30
#
#---------------------------------------------------------#

# 创建 special_analyse_stockradio 表
CREATE TABLE `special_analyse_stockradio` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `radio1` DOUBLE NOT NULL,
  `radio2` DOUBLE NOT NULL,
  `updateDate` DATE NOT NULL,
  PRIMARY KEY (`id`)
);