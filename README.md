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


INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (1,'系统管理',NULL,'系统管理',-1,1,'icon-application-system',1);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (7,'用户列表','/user/gotoList','用户列表',1,1,'icon-system-user',7);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (8,'角色列表','/role/gotoList','角色列表',1,1,'icon-system-user',8);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (9,'资源列表','/resource/gotoList','资源列表',1,1,'icon-system-user',9);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (10,'地域列表','/area/gotoList','地域列表',1,1,'icon-system-area',10);

INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (2,'商家管理',NULL,'商家管理',-1,1,'icon-application-business',2);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (11,'商家列表','/business/gotoList','商家列表',2,1,'icon-business-user',11);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (12,'商铺列表','/place/gotoList','商铺列表',2,1,'icon-business-shop',12);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (13,'设备列表','/device/gotoList','设备列表',2,1,'icon-business-device',13);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (14,'商家广告列表','/system_advertisement/gotoList','商家广告列表',2,1,'icon-business-ads',14);

INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (3,'广告管理',NULL,'广告管理',-1,1,'icon-application-ads',3);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (15,'广告商列表','/advertiser/gotoList','广告商列表',3,1,'icon-ads-user',15);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (16,'广告列表','/advertisement/gotoList','广告列表',3,1,'icon-ads-video',16);

INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (4,'播控管理',NULL,'播控管理',-1,1,'icon-application-play',4);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (17,'可用广告位','/orders/gotoAvailableList','可用广告位',4,1,'icon-play-free',17);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (18,'在播广告','/orders/gotoPlayList','在播广告',4,1,'icon-play-play',18);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (19,'订单列表','/orders/gotoList','订单列表',4,1,'icon-play-order',19);

INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (5,'排播组合',NULL,'排播组合',-1,1,'icon-application-statictics',5);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (20,'在播广告组合','/play_list/gotoOnlineAdList','在播广告组合',5,1,'icon-statictics-play',20);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (21,'设备播放列表','/play_list/gotoDeviceList','设备播放列表',5,1,'icon-statictics-device',21);


INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (6,'监控管理',NULL,'监控管理',-1,1,'icon-application-monitor',6);
 INSERT INTO resource(id,TEXT,url,remark,parent_id,is_menu,menu_cls,sort) VALUES (22,'设备实时监控','/deviceImage/gotoList','设备实时监控',6,1,'icon-monitor-list',22);
