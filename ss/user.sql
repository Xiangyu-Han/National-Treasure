/*
Navicat MySQL Data Transfer

Source Server         : mybatis
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-22 15:59:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `test` (
`test_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '测试ID',
`test_name` char(255) DEFAULT NULL COMMENT '测试名称',
`info` text COMMENT '各种信息',
`other` varchar(255) DEFAULT NULL,
PRIMARY KEY (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `museum`(
`museum_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '博物馆ID',
`museum_name` varchar(40)  COMMENT '馆名',
`museum_url1` varchar(40) COMMENT '图片地址1',
`museum_url2` varchar(40) COMMENT '图片地址2',
`museum_url3` varchar(40) COMMENT '图片地址3',
`museum_info1` text COMMENT '详细信息1',
`museum_info2` text COMMENT '详细信息2',
`museum_info3` text COMMENT '详细信息3',
PRIMARY KEY (`museum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user`(
`user_id`  int(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
`password` varchar(40)  COMMENT '密码',
`mail` varchar(40) COMMENT '邮箱',
`user_name` varchar(40) COMMENT '昵称',
`dynasty` varchar(40)  COMMENT '朝代',
`category` varchar(40) COMMENT '类别',
`area` varchar(40) COMMENT '地区',
PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




