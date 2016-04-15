accelarator
===============

公众空间accelarator 项目初始化

TRUNCATE TABLE `advertisement`;
TRUNCATE TABLE `advertiser`;
TRUNCATE TABLE `device`;
TRUNCATE TABLE `orders`;
TRUNCATE TABLE `orders_detail`;
TRUNCATE TABLE `place`;
TRUNCATE TABLE `play_list`;
TRUNCATE TABLE `play_list_detail`;

ALTER TABLE `user` MODIFY COLUMN `create_time` DATETIME;
ALTER TABLE `system_advertisement` MODIFY COLUMN `create_time` DATETIME;
ALTER TABLE `place` MODIFY COLUMN `create_time` DATETIME;
ALTER TABLE `orders` MODIFY COLUMN `create_time` DATETIME;
ALTER TABLE `device` MODIFY COLUMN `create_time` DATETIME;
ALTER TABLE `business` MODIFY COLUMN `create_time` DATETIME;
ALTER TABLE `advertiser` MODIFY COLUMN `create_time` DATETIME;


ALTER TABLE `play_list` MODIFY COLUMN `id` BIGINT(20);
ALTER TABLE `play_list_detail` MODIFY COLUMN `play_list_id` BIGINT(20);