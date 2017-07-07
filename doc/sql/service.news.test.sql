CREATE TABLE `news` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `source` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `href` VARCHAR(255) NOT NULL,
  `profile` VARCHAR(255),
  `stmp` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
INSERT INTO `news` (id, source, title, href, profile, stmp) VALUE (null, '搜狐财经', '测试test', 'http://test.com', '这是一个测试test1', now());
INSERT INTO `news` (id, source, title, href, profile, stmp) VALUE (null, '搜狐财经', '测试test2', 'http://test2.com', '这是一个测试test2', '2017-7-4');
INSERT INTO `news` (id, source, title, href, profile, stmp) VALUE (null, '搜狐财经', '测试test3', 'http://test3.com', '这是一个测试test3', '2017-7-2');
INSERT INTO `news` (id, source, title, href, profile, stmp) VALUE (null, '新浪财经', '测试test4', 'http://test4.com', '这是一个测试test4', '2017-7-5');
INSERT INTO `news` (id, source, title, href, profile, stmp) VALUE (null, '雪球财经', '测试test', 'http://test5.com', '这是一个测试test5', now());
INSERT INTO `news` (id, source, title, href, profile, stmp) VALUE (null, '雪球财经', '测试test', 'http://test6.com', '这是一个测试test6', '2017-7-1');
INSERT INTO `news` (id, source, title, href, profile, stmp) VALUE (null, '雪球财经', '测试test', 'http://test7.com', '这是一个测试test7', '2017-6-5');
SELECT * FROM `news` ORDER BY stmp DESC;
SELECT * FROM `news` WHERE stmp BETWEEN '2017-7-1' AND '2017-7-5' ORDER BY stmp DESC;
SELECT * FROM `news` WHERE stmp >= '2017-7-1' ORDER BY stmp DESC;
SELECT * FROM `news` WHERE stmp <= '2017-7-5' ORDER BY stmp DESC;
SELECT * FROM `news` where date_sub(curdate(), INTERVAL 3 DAY) <= date(stmp) ORDER BY stmp DESC;
SELECT * FROM `news` WHERE datediff(stmp, now()) = 0 ORDER BY stmp DESC;
SELECT * FROM `news` WHERE date_sub(curdate(), INTERVAL 7 DAY) <= date(stmp) ORDER BY stmp DESC;
SELECT * FROM `news` WHERE date_format(stmp, '%Y%m') = date_format(curdate(), '%Y%m') ORDER BY stmp DESC;
SELECT * FROM `news` WHERE source like '%搜狐财经%' AND title like '%测试%' ORDER BY stmp DESC;