/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-06 21:25:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for buyer
-- ----------------------------
DROP TABLE IF EXISTS `buyer`;
CREATE TABLE `buyer` (
  `buyerId` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `wallet` int(11) NOT NULL DEFAULT '0' COMMENT '钱包余额',
  `address` varchar(200) NOT NULL,
  PRIMARY KEY (`buyerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of buyer
-- ----------------------------
INSERT INTO `buyer` VALUES ('111111', 'e10adc3949ba59abbe56e057f20f883e', '11', '15', '2000-10-10', '0', '成都市龙泉');
INSERT INTO `buyer` VALUES ('123456', 'e10adc3949ba59abbe56e057f20f883e', '777', '123@qq.com', '1000-10-10', '-94', '成都市天府新区');
INSERT INTO `buyer` VALUES ('13320812430', '6512bd43d9caa6e02c990b0a82652dca', '11', '123@qq.com', '1000-10-10', '0', '1111');
INSERT INTO `buyer` VALUES ('13384737807', 'b59c67bf196a4758191e42f76670ceba', '1111', '123@qq.com', '1000-10-10', '0', '1111');
INSERT INTO `buyer` VALUES ('1838473780', 'c81e728d9d4c2f636f067f89cc14862c', '2', '123@qq.com', '1000-10-10', '0', '1');
INSERT INTO `buyer` VALUES ('18384737807', 'c4ca4238a0b923820dcc509a6f75849b', '1', '123@qq.com', '1000-10-10', '0', '1');
INSERT INTO `buyer` VALUES ('23333', 'c4ca4238a0b923820dcc509a6f75849b', '1', '123@qq.com', '1000-10-10', '0', '1');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodId` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name` varchar(50) NOT NULL,
  `city` varchar(100) NOT NULL COMMENT '产地',
  `price` int(11) NOT NULL COMMENT '价格',
  `number` int(11) NOT NULL COMMENT '库存',
  `sellerId` varchar(50) NOT NULL,
  `url` varchar(100) NOT NULL,
  PRIMARY KEY (`goodId`),
  KEY `sellerId` (`sellerId`),
  CONSTRAINT `sellerId` FOREIGN KEY (`sellerId`) REFERENCES `seller` (`sellerId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '三星 Galaxy S9+ 谜夜黑', '四川成都', '6699', '10', '123456', '三星 Galaxy S9.jpg');
INSERT INTO `goods` VALUES ('2', '三星 Galaxy S8 谜夜黑', '四川成都', '4499', '7', '123456', '三星 Galaxy S8.jpg');
INSERT INTO `goods` VALUES ('3', '三星 Galaxy S 勃艮第红', '四川绵阳', '3299', '5', '123456', '三星 Galaxy S.jpg');
INSERT INTO `goods` VALUES ('4', '小米 红米5 Plus', '北京', '899', '6', '123456', '小米 红米5 Plus.jpg');
INSERT INTO `goods` VALUES ('6', '三星 Galaxy A9 夜空黑', '四川绵阳', '1999', '7', '123456', '三星 Galaxy A9.jpg');
INSERT INTO `goods` VALUES ('7', '小米 红米6 Pro  曜石黑', '四川乐至', '999', '20', '123456', '小米 红米6 Pro.jpg');
INSERT INTO `goods` VALUES ('8', '小米 红米6  铂银灰', '贵州', '799', '15', '123456', '小米 红米6.jpg');
INSERT INTO `goods` VALUES ('9', '小米6X  曜石黑', '贵州', '1399', '9', '111111', '小米6X 曜石黑.jpg');
INSERT INTO `goods` VALUES ('10', 'vivo X21i 极夜黑', '上海', '2598', '4', '333333', 'vivo X21i 极夜黑.jpg');
INSERT INTO `goods` VALUES ('11', 'vivo Z1', '北京', '1598', '20', '333333', 'vivo Z1.jpg');
INSERT INTO `goods` VALUES ('12', 'vivo X21', '上海', '3598', '15', '333333', 'vivo X21.jpg');
INSERT INTO `goods` VALUES ('13', 'OPPO R15', '浙江', '2999', '12', '444444', 'OPPO R15.jpg');
INSERT INTO `goods` VALUES ('14', 'OPPO A3 石榴红', '浙江', '1999', '3', '444444', 'OPPO A3.jpg');
INSERT INTO `goods` VALUES ('15', 'OPPO R11s', '南京', '2299', '6', '444444', 'OPPO R11s.jpg');
INSERT INTO `goods` VALUES ('16', '华为 Galaxy S9+ 谜夜黑', '四川成都', '6699', '10', '111111', '三星 Galaxy S9.jpg');
INSERT INTO `goods` VALUES ('17', '华为 Galaxy S8 谜夜黑', '四川成都', '4499', '7', '111111', '三星 Galaxy S8.jpg');
INSERT INTO `goods` VALUES ('18', '华为 Galaxy S 勃艮第红', '四川绵阳', '3299', '5', '111111', '三星 Galaxy S.jpg');
INSERT INTO `goods` VALUES ('19', '小米 红米5 Plus', '北京', '899', '6', '123456', '小米 红米5 Plus.jpg');
INSERT INTO `goods` VALUES ('20', 'vivo NEX 星钻黑', '上海', '4498', '11', '333333', 'vivo NEX 星钻黑.jpg');
INSERT INTO `goods` VALUES ('21', '111', '111', '111', '11', '111111', '4af58bca4bff8d5cbf09e65a.gif');
INSERT INTO `goods` VALUES ('23', '222', '22', '22', '22', '111111', '2W3@@JIJIX[1G$}1WA27$FI.jpg');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `managerId` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`managerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('admin', '96e79218965eb72c92a549dd5a330112');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `createdate` datetime NOT NULL,
  `status` varchar(50) NOT NULL,
  `buyerId` varchar(50) NOT NULL,
  `goodId` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `allmoney` int(11) NOT NULL,
  PRIMARY KEY (`orderId`),
  KEY `buyerId_2` (`buyerId`),
  KEY `goodId_2` (`goodId`),
  CONSTRAINT `buyerId_2` FOREIGN KEY (`buyerId`) REFERENCES `buyer` (`buyerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `goodId_2` FOREIGN KEY (`goodId`) REFERENCES `goods` (`goodId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '2018-06-06 23:19:25', '已付款', '123456', '7', '1', '999');
INSERT INTO `orders` VALUES ('2', '2018-06-15 18:43:37', '已付款', '123456', '2', '2', '8998');
INSERT INTO `orders` VALUES ('3', '2018-06-20 18:44:23', '已付款', '111111', '2', '1', '4499');
INSERT INTO `orders` VALUES ('4', '2018-06-29 12:25:10', '未支付', '123456', '1', '1', '6699');
INSERT INTO `orders` VALUES ('5', '2018-06-29 12:29:47', '未支付', '123456', '4', '1', '899');
INSERT INTO `orders` VALUES ('6', '2018-06-29 12:32:07', '未支付', '123456', '7', '1', '999');
INSERT INTO `orders` VALUES ('8', '2018-06-29 12:37:40', '未支付', '123456', '6', '1', '1999');
INSERT INTO `orders` VALUES ('9', '2018-06-29 12:45:02', '未支付', '123456', '7', '1', '999');
INSERT INTO `orders` VALUES ('10', '2018-06-29 12:48:42', '未支付', '123456', '9', '1', '1399');
INSERT INTO `orders` VALUES ('11', '2018-06-29 12:58:42', '已支付', '123456', '10', '1', '2598');
INSERT INTO `orders` VALUES ('12', '2018-06-29 12:59:43', '已支付', '123456', '11', '1', '1598');
INSERT INTO `orders` VALUES ('13', '2018-06-29 13:23:06', '已支付', '123456', '2', '1', '4499');
INSERT INTO `orders` VALUES ('14', '2018-06-29 14:05:58', '未支付', '123456', '2', '1', '4499');
INSERT INTO `orders` VALUES ('15', '2018-06-29 14:50:40', '未支付', '123456', '2', '1', '4499');
INSERT INTO `orders` VALUES ('16', '2018-06-29 15:07:03', '已支付', '123456', '9', '1', '1399');

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `sellerId` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `shopname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `power` int(11) NOT NULL DEFAULT '0' COMMENT '权限0为审核 权限1为审核了',
  `wallet` int(11) NOT NULL DEFAULT '0' COMMENT '钱包余额',
  PRIMARY KEY (`sellerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES ('111111', '96e79218965eb72c92a549dd5a330112', '华为', '11@qq.com', '1998-11-11', '1', '890');
INSERT INTO `seller` VALUES ('1111111111111', 'b0baee9d279d34fa1dfd71aadb908c3f', '111', '123@qq.com', '1000-10-10', '0', '0');
INSERT INTO `seller` VALUES ('123456', '96e79218965eb72c92a549dd5a330112', '小米', '123@qq.com', '1998-11-11', '1', '1000');
INSERT INTO `seller` VALUES ('18384737807', 'c4ca4238a0b923820dcc509a6f75849b', '1', '123@qq.com', '1000-10-10', '0', '0');
INSERT INTO `seller` VALUES ('18388', '96e79218965eb72c92a549dd5a330112', '11111', '123@qq.com', '1000-10-10', '0', '0');
INSERT INTO `seller` VALUES ('183881', 'b59c67bf196a4758191e42f76670ceba', '11111', '123@qq.com', '1000-10-10', '0', '0');
INSERT INTO `seller` VALUES ('1838811', 'b59c67bf196a4758191e42f76670ceba', '1111', '123@qq.com', '1000-10-10', '0', '0');
INSERT INTO `seller` VALUES ('183881111', '698d51a19d8a121ce581499d7b701668', '1111', '123@qq.com', '1000-10-10', '0', '0');
INSERT INTO `seller` VALUES ('222222', '96e79218965eb72c92a549dd5a330112', '苹果官网', '111@qq.com', '2000-10-10', '1', '100');
INSERT INTO `seller` VALUES ('333333', '96e79218965eb72c92a549dd5a330112', 'vivo官网', '333@qq.com', '2018-06-12', '1', '300');
INSERT INTO `seller` VALUES ('444444', '96e79218965eb72c92a549dd5a330112', 'oppo官网', '111@qq.com', '2018-06-11', '1', '0');

-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `buyerId` varchar(50) NOT NULL,
  `goodId` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`buyerId`,`goodId`),
  KEY `goodId_1` (`goodId`),
  CONSTRAINT `buyerId_1` FOREIGN KEY (`buyerId`) REFERENCES `buyer` (`buyerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `goodId_1` FOREIGN KEY (`goodId`) REFERENCES `goods` (`goodId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopcart
-- ----------------------------
INSERT INTO `shopcart` VALUES ('111111', '2', '22');
INSERT INTO `shopcart` VALUES ('123456', '2', '1');
INSERT INTO `shopcart` VALUES ('123456', '9', '1');
INSERT INTO `shopcart` VALUES ('123456', '10', '1');
INSERT INTO `shopcart` VALUES ('123456', '11', '1');
INSERT INTO `shopcart` VALUES ('123456', '21', '5');
