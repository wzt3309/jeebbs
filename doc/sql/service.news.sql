#---------------------------------------------------------#
#
#                    2017-07-07 14:59:47
#
#---------------------------------------------------------#

# 创建news表
CREATE TABLE `news` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `source` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `href` VARCHAR(255) NOT NULL,
  `profile` VARCHAR(2000),
  `stmp` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);