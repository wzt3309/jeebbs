#---------------------------------------------------------#
#
#                    2017-08-17 16:48:30
#
#---------------------------------------------------------#

# 创建 special_analyse 表
CREATE TABLE `special_analyse` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `radio1` DOUBLE NOT NULL,
  `radio2` DOUBLE NOT NULL,
  `radio3` DOUBLE NOT NULL,
  `radio4` DOUBLE NOT NULL,
  `updateDate` DATE NOT NULL,
  PRIMARY KEY (`id`)
);