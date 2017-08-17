SELECT * FROM special_analyse;
INSERT INTO `special_analyse` (`radio1`, `radio2`, `radio3`, `radio4`, `updateDate`) VALUE (1.2, 2.5, -10, 4.56, '2017-5-6');
INSERT INTO `special_analyse` (`radio1`, `radio2`, `radio3`, `radio4`, `updateDate`) VALUE (4.5, -3.25, -0.58, 4.56, current_date());
SELECT * FROM `special_analyse` where date_sub(curdate(), INTERVAL 110 DAY) <= date(updateDate) ORDER BY updateDate DESC