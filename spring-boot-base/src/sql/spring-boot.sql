/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : spring-boot

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-08-28 22:33:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `phone` varchar(50) NOT NULL COMMENT '手机号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '12', 'zero', '188', '123', '2017-08-18 11:56:46');
INSERT INTO `user` VALUES ('2', '1', 'hello world', '110', '123', '2017-08-18 12:01:46');
INSERT INTO `user` VALUES ('3', '15', 'string', 'string', 'string', null);

-- ----------------------------
-- Table structure for user_check_count
-- ----------------------------
DROP TABLE IF EXISTS `user_check_count`;
CREATE TABLE `user_check_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '关联的用户id',
  `check_time` datetime NOT NULL COMMENT '签到时间',
  `continue_count` int(11) NOT NULL COMMENT '连续签到天数',
  `max_count` int(11) NOT NULL COMMENT '最长连续签到天数',
  `history` bigint(11) NOT NULL COMMENT '签到历史记录',
  `sum` int(11) NOT NULL COMMENT '总的签到天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_check_count
-- ----------------------------
INSERT INTO `user_check_count` VALUES ('1', '1', '2017-08-18 11:07:31', '1', '3', '29', '4');

-- ----------------------------
-- Table structure for user_point
-- ----------------------------
DROP TABLE IF EXISTS `user_point`;
CREATE TABLE `user_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `point` int(11) DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_point
-- ----------------------------
INSERT INTO `user_point` VALUES ('1', '1', '13');
INSERT INTO `user_point` VALUES ('2', '2', '2');
INSERT INTO `user_point` VALUES ('3', '3', '0');

-- ----------------------------
-- Table structure for user_point_record
-- ----------------------------
DROP TABLE IF EXISTS `user_point_record`;
CREATE TABLE `user_point_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `type` varchar(11) DEFAULT NULL COMMENT '积分类型',
  `gain_point` int(11) DEFAULT NULL COMMENT '获得的积分',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_point_record
-- ----------------------------
INSERT INTO `user_point_record` VALUES ('1', '1', '签到', '2', '2017-08-18 11:02:54');
INSERT INTO `user_point_record` VALUES ('2', '1', '签到', '2', '2017-08-18 11:06:02');
INSERT INTO `user_point_record` VALUES ('3', '1', '连续签到', '5', '2017-08-18 11:06:54');
INSERT INTO `user_point_record` VALUES ('4', '1', '签到', '2', '2017-08-18 11:06:54');
INSERT INTO `user_point_record` VALUES ('5', '1', '签到', '2', '2017-08-18 11:07:31');
INSERT INTO `user_point_record` VALUES ('6', '2', '登录', '2', '2017-08-18 12:01:46');
