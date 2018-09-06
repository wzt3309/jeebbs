SET MODE MYSQL;

CREATE TABLE `news` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `source` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `href` VARCHAR(255) NOT NULL,
  `profile` VARCHAR(255),
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