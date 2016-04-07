accelarator
===============

公众空间accelarator 项目初始化


INSERT INTO `business` (id,user_name,PASSWORD,real_name,mobile,email,STATUS) SELECT 
id,user_name,PASSWORD,real_name,mobile,email,STATUS FROM USER

UPDATE business SET country=1 ;
UPDATE business SET `area`=2 WHERE id<=100;
UPDATE business SET `area`=3 WHERE id>100 AND id<=116;
UPDATE business SET `area`=4 WHERE id>116 AND id<=120;
UPDATE business SET `area`=5 WHERE id>120 AND id<=127;
UPDATE business SET `area`=6 WHERE id>127 AND id<=135;
UPDATE business SET `area`=7 WHERE id>135 AND id<=145;
UPDATE business SET `area`=8 WHERE id>145 AND id<=150;
UPDATE business SET `area`=9 WHERE id>150 AND id<=153;
UPDATE business SET `area`=10 WHERE id>153;




TRUNCATE TABLE `advertisement`;
TRUNCATE TABLE `advertiser`;
TRUNCATE TABLE `device`;
TRUNCATE TABLE `orders`;
TRUNCATE TABLE `orders_detail`;
TRUNCATE TABLE `place`;
TRUNCATE TABLE `play_list`;
TRUNCATE TABLE `play_list_detail`;


INSERT INTO `advertiser` (id,user_name,PASSWORD,real_name,country_code,area_code,city_code,mobile,email,remark,STATUS)
 SELECT id,user_name,PASSWORD,real_name,country_code,area_code,city_code,mobile,email,remark,STATUS FROM `business` WHERE 
 id IN (83,92,94,95,99,100)