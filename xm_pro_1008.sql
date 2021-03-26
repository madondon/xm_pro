/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : xm_pro_1008

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-03-26 17:45:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `gid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `state` int(1) DEFAULT NULL,
  `order_number` int(5) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('2', '手机', '1', '1', '最新款手机，智能机', '2018-12-25 09:53:55');
INSERT INTO `category` VALUES ('3', '智能', '1', '0', '智能设备等等', '2018-12-25 14:20:12');
INSERT INTO `category` VALUES ('4', '家电', '1', '2', '智能设备等等', '2018-12-25 14:20:42');
INSERT INTO `category` VALUES ('6', '智能产品', '1', '2', '', '2018-12-26 14:46:27');
INSERT INTO `category` VALUES ('7', '图书 内容', '1', '55', '', '2018-12-25 09:53:55');
INSERT INTO `category` VALUES ('8', '移动电源 电池 插线板', '1', '33', '1', '2018-12-26 14:48:16');
INSERT INTO `category` VALUES ('9', '耳机 音响', '1', '34', '', '2018-12-26 14:48:31');
INSERT INTO `category` VALUES ('10', '保护套 贴膜', '1', '35', '', '2018-12-26 14:48:47');
INSERT INTO `category` VALUES ('11', '线材 支架 存储卡', '1', '36', '', '2018-12-26 14:49:04');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `cid` int(10) NOT NULL AUTO_INCREMENT,
  `gid` int(10) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `size` varchar(50) DEFAULT NULL,
  `price` double NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `full_description` varchar(1000) DEFAULT NULL,
  `pic` varchar(200) DEFAULT NULL,
  `state` int(5) DEFAULT '0',
  `version` varchar(50) DEFAULT NULL,
  `product_date` datetime DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `gid` (`gid`),
  CONSTRAINT `commodity_ibfk_1` FOREIGN KEY (`gid`) REFERENCES `category` (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('3', '2', '小米 Redmi 9', '白色', '6.53英寸', '799', '大电量大屏幕老年机', '官方旗舰版', 'c133cd34e2e6b584f0caec91d30a1a1.jpg', '2', null, '2020-09-28 15:41:19');
INSERT INTO `commodity` VALUES ('4', '2', '小米 Note 8 pro', '黑色', '6.3英寸', '1299', '4800w超高像素 学生游戏最爱', '官方旗舰版', 'c74bb1b9a68ee609beb75838a738f0d.jpg', '0', null, '2020-09-28 15:43:00');
INSERT INTO `commodity` VALUES ('5', '2', '小米10 Pro', '蓝色', '6.67英寸', '4999', '一亿像素骁龙865处理器', '官方旗舰版', '45b05d05381e432adb69d310eed9d16.jpg', '0', null, '2020-09-28 15:45:02');
INSERT INTO `commodity` VALUES ('6', '2', '小米 Redmi k30', '白色', '6.67英寸', '3399', '骁龙865学生只能游戏手机', '官方旗舰版', '0125cb7bcc31b1c584f58f6d66f9cfd.jpg', '0', null, '2020-09-28 15:47:02');
INSERT INTO `commodity` VALUES ('7', '2', '小米 Redmi k30 Pro', '白色', '7.62', '4299', '配置最新的处理器拥有大电量', '官方旗舰版', 'a88371a00ddbf5c52ad3fc429b36c83.jpg', '0', null, '2020-09-28 15:48:35');
INSERT INTO `commodity` VALUES ('8', '2', '小米 Redmi 8', '蓝色', '6.22英寸', '799', '拥有超大容量电池的老年机此外拥有超高像素', '官方旗舰版', '3f2f1d0c4b901c4acfa191fd1ef36f7.jpg', '0', null, '2020-09-28 15:50:09');
INSERT INTO `commodity` VALUES ('11', '3', '小米负离子吹风机', '白色', '便于携带', '149', '拥有负离子快速吹干的功能', '官方旗舰版', '69ff0734dda38dcb9db5fd6b9fde4a7.jpg', '2', null, '2020-09-28 15:54:22');
INSERT INTO `commodity` VALUES ('12', '3', '小米小爱音箱', '白色', null, '249', '智能AI机器人影响闹钟归控', '官方旗舰版', '8e221c9e91cf6efd32b7812cbd54fc7.jpg', '1', null, '2020-09-01 15:57:48');
INSERT INTO `commodity` VALUES ('13', '3', '小米电视', '黑色', '75英寸', '7999', '金属全面屏网络智能4K高清液晶屏幕家用电视', '官方旗舰版', 'cf06c9d19c418fea486903d5f569032.jpg', '0', null, '2020-09-27 15:59:58');
INSERT INTO `commodity` VALUES ('14', '3', '小米米家扫地机器人', '白色', '12寸', '1799', '多功能全自动家用扫地——一体推地吸尘', '官方旗舰版', '6108d195ff28ab4e278bf882ad08dca.jpg', '1', null, '2020-08-01 16:02:21');
INSERT INTO `commodity` VALUES ('15', '3', '小米C1吸尘器', '白色', null, '999', '手持小型大吸力吸尘器', '官方旗舰版', '30a4fa9c53c3f0f42e0c18052c4ac12.jpg', '1', null, '2020-06-01 16:03:38');
INSERT INTO `commodity` VALUES ('16', '3', '小米米家打印机', '白色', null, '699', '家用小型打印机可以直接连接蓝牙', '官方旗舰版', '4ef71bbeccfb7a623a840880d68efbc.jpg', '0', null, '2020-09-14 16:05:21');
INSERT INTO `commodity` VALUES ('17', '4', '小米电视5', '褐色', '75英寸', '9999', 'PRO量子电视网路智能4K高清液晶家用电视', '官方旗舰版', 'pms_1601200318.14523916.jpg', '0', null, '2020-09-26 16:07:52');
INSERT INTO `commodity` VALUES ('18', '4', '小米电视 E55A', '黑色', '55英寸', '2088', '家用中等尺寸电视机', '官方旗舰版', 'pms_1601200318.14523916.jpg', '1', null, '2020-09-11 16:09:37');
INSERT INTO `commodity` VALUES ('19', '4', '小米电视 4S32', '褐色', '32英寸', '999', '高清联网家用液晶显示屏电视', '官方旗舰版', 'pms_1601200318.14523916.jpg', '0', null, '2020-05-13 16:11:23');
INSERT INTO `commodity` VALUES ('20', '4', '小米电视 4C32', '褐色', '32英寸', '1099', '高清联网家用液晶显示屏电视', '官方旗舰版', 'pms_1593671513.90269727.jpg', '0', null, null);
INSERT INTO `commodity` VALUES ('22', '3', '小米智能闹钟', '白色', null, '149', '学生床头数字时钟多功能电子钟', '官方旗舰版', '038f2eb3a49c1a2eefb7c293c32ec2d.jpg', '1', null, '2020-09-15 16:15:44');
INSERT INTO `commodity` VALUES ('23', '3', '小米智能传感器', '白色', null, '149', '家用多功能传导设备可以检测多种烟雾以及火源', '官方旗舰版', '1c435772f7e61891883821097527c17.jpg', '0', null, '2020-09-15 16:16:58');
INSERT INTO `commodity` VALUES ('24', '2', '红米9A', '蓝色', '6.5英寸', '599', '5000mAh长循环大电量/6.53\"超大护眼屏幕', '官方旗舰版', '508ea1b27f3c04e43bd6de24d7814ef.jpg', '0', null, null);
INSERT INTO `commodity` VALUES ('25', '3', '小米智能窗帘', '蓝色', '4.5米', '389', '99%高度遮光，94.2%环境甲醛去除率。双向隔影窗纱', '官方旗舰版', '9c956d541f3e786995b3ce530394c38.png', '0', null, null);
INSERT INTO `commodity` VALUES ('26', '3', '小米智能烤箱', '白色', '20英寸', '1499', '蒸烤烘炸炖一机多用 / 30秒疾速出蒸汽', '官方旗舰版', 'pms_1597998326.82499251.jpg', '0', null, null);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orders_number` varchar(100) NOT NULL,
  `uid` int(50) NOT NULL,
  `sum_price` double NOT NULL,
  `state` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `count_number` int(10) DEFAULT NULL,
  PRIMARY KEY (`orders_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('114af3c7-af80-4470-8c71-a02642b665dd', '33', '1799', '0', '2020-09-29 23:48:50', null);
INSERT INTO `orders` VALUES ('15dbf7a0-e5ed-424f-8e48-801e253d56a2', '14', '5399', '0', '2018-12-29 16:05:25', '1');
INSERT INTO `orders` VALUES ('2c097d3a-34bc-482e-bb67-4da9372d9a4d', '14', '15000', '0', '2018-12-29 16:28:15', '1');
INSERT INTO `orders` VALUES ('2eba76f2-1174-4274-97c3-9298b247da75', '14', '199', '0', '2018-12-29 16:03:12', '1');
INSERT INTO `orders` VALUES ('3da19777-6d9b-49aa-ac34-5e2bbb49b875', '14', '1999', '1', '2018-12-29 16:30:28', '1');
INSERT INTO `orders` VALUES ('63c905bd-3b5e-4e47-a317-89de3fd35c8f', '33', '4999', '1', '2020-09-30 00:12:48', null);
INSERT INTO `orders` VALUES ('76821aba-8dc3-43ff-bc09-8576ff7a31f3', '14', '199', '0', '2018-12-29 16:12:25', '1');
INSERT INTO `orders` VALUES ('9f22390d-589c-4237-80ec-8ee846995def', '33', '6798', '0', '2020-09-30 00:14:51', null);
INSERT INTO `orders` VALUES ('ad5f0cd3-16be-40eb-8175-7efc32dcb6a0', '15', '1000398', '0', '2018-12-28 17:02:26', '3');
INSERT INTO `orders` VALUES ('b828186e-c1db-45a5-8ccd-de4d6b5b9e73', '33', '9998', '0', '2021-01-29 22:11:05', null);
INSERT INTO `orders` VALUES ('b9f18ad0-421e-40e5-ab53-2147ec347e50', '33', '1799', '0', '2020-09-29 23:48:36', null);
INSERT INTO `orders` VALUES ('bfe27ebd-eb47-40ef-bc98-05f39f015ff1', '14', '8392', '0', '2018-12-28 16:59:46', '10');
INSERT INTO `orders` VALUES ('ccc624d5-621b-423a-a2df-53e3050adba5', '14', '998', '0', '2018-12-29 16:10:28', '1');
INSERT INTO `orders` VALUES ('d533b5f5-eaed-4b31-af7c-e20a316a78d8', '33', '6499', '0', '2021-01-29 20:53:15', null);
INSERT INTO `orders` VALUES ('f16bd560-a697-401f-a751-220c14624e59', '14', '199', '0', '2018-12-29 16:01:08', '1');
INSERT INTO `orders` VALUES ('f8c34aba-79f4-43a8-af19-697b33ea2fe9', '33', '7698', '0', '2021-01-29 20:52:02', null);
INSERT INTO `orders` VALUES ('ffdec432-5b7f-4b61-8b84-c4d007cbe01b', '33', '4999', '0', '2021-01-29 22:10:31', null);

-- ----------------------------
-- Table structure for trolley
-- ----------------------------
DROP TABLE IF EXISTS `trolley`;
CREATE TABLE `trolley` (
  `tid` int(50) NOT NULL AUTO_INCREMENT,
  `uid` int(50) NOT NULL,
  `cid` int(50) NOT NULL,
  `number` int(50) NOT NULL DEFAULT '1',
  `orders_number` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trolley
-- ----------------------------
INSERT INTO `trolley` VALUES ('6', '14', '11', '5', 'bfe27ebd-eb47-40ef-bc98-05f39f015ff1');
INSERT INTO `trolley` VALUES ('7', '15', '4', '1', 'ad5f0cd3-16be-40eb-8175-7efc32dcb6a0');
INSERT INTO `trolley` VALUES ('8', '15', '11', '2', 'ad5f0cd3-16be-40eb-8175-7efc32dcb6a0');
INSERT INTO `trolley` VALUES ('9', '14', '9', '1', 'bfe27ebd-eb47-40ef-bc98-05f39f015ff1');
INSERT INTO `trolley` VALUES ('10', '14', '23', '2', 'bfe27ebd-eb47-40ef-bc98-05f39f015ff1');
INSERT INTO `trolley` VALUES ('11', '14', '3', '2', 'bfe27ebd-eb47-40ef-bc98-05f39f015ff1');
INSERT INTO `trolley` VALUES ('12', '15', '9', '2', null);
INSERT INTO `trolley` VALUES ('13', '14', '11', '1', 'f16bd560-a697-401f-a751-220c14624e59');
INSERT INTO `trolley` VALUES ('14', '14', '9', '1', '15dbf7a0-e5ed-424f-8e48-801e253d56a2');
INSERT INTO `trolley` VALUES ('15', '14', '14', '1', 'ccc624d5-621b-423a-a2df-53e3050adba5');
INSERT INTO `trolley` VALUES ('16', '14', '11', '1', '76821aba-8dc3-43ff-bc09-8576ff7a31f3');
INSERT INTO `trolley` VALUES ('17', '14', '29', '1', '2c097d3a-34bc-482e-bb67-4da9372d9a4d');
INSERT INTO `trolley` VALUES ('18', '14', '10', '1', '3da19777-6d9b-49aa-ac34-5e2bbb49b875');
INSERT INTO `trolley` VALUES ('19', '33', '14', '1', 'b9f18ad0-421e-40e5-ab53-2147ec347e50');
INSERT INTO `trolley` VALUES ('21', '33', '6', '2', '9f22390d-589c-4237-80ec-8ee846995def');
INSERT INTO `trolley` VALUES ('23', '33', '7', '1', 'f8c34aba-79f4-43a8-af19-697b33ea2fe9');
INSERT INTO `trolley` VALUES ('24', '33', '6', '1', 'f8c34aba-79f4-43a8-af19-697b33ea2fe9');
INSERT INTO `trolley` VALUES ('25', '33', '1', '1', 'd533b5f5-eaed-4b31-af7c-e20a316a78d8');
INSERT INTO `trolley` VALUES ('26', '33', '2', '1', 'd533b5f5-eaed-4b31-af7c-e20a316a78d8');
INSERT INTO `trolley` VALUES ('27', '33', '5', '2', 'b828186e-c1db-45a5-8ccd-de4d6b5b9e73');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `sex` int(1) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `area` varchar(100) DEFAULT NULL,
  `manager` int(1) DEFAULT '1',
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_number` (`phone_number`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('33', 'test', '1', '18801193448', 'bj', '0', '17160125', '123456', '6f0ccd59-7947-4ad9-b2ce-c70776a9640030a4fa9c53c3f0f42e0c18052c4ac12.jpg', '2020-09-29 23:46:27');
INSERT INTO `user` VALUES ('35', '18801196524', '1', '18801193446', 'bj', '1', '17160125123', '123456', '3f03359f-8967-4204-a5ca-d70df08abef1git???.JPG', '2021-01-29 20:29:05');
