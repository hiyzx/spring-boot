/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-08-19 16:52:14
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id`          INT(11) NOT NULL,
  `name`        VARCHAR(255) DEFAULT NULL,
  `url`         VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`          BIGINT(19)  NOT NULL AUTO_INCREMENT
  COMMENT '主键id',
  `name`        VARCHAR(64) NOT NULL
  COMMENT '角色名',
  `seq`         TINYINT(2)  NOT NULL DEFAULT '0'
  COMMENT '排序号',
  `description` VARCHAR(255)         DEFAULT NULL
  COMMENT '简介',
  `status`      TINYINT(2)  NOT NULL DEFAULT '0'
  COMMENT '状态',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8
  COMMENT ='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '0', '超级管理员', '0');
INSERT INTO `role` VALUES ('2', 'normal', '1', '普通', '0');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id`            INT(11) NOT NULL,
  `role_id`       INT(11) DEFAULT NULL,
  `permission_id` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`              INT(11)     NOT NULL AUTO_INCREMENT,
  `age`             INT(11)              DEFAULT NULL
  COMMENT '年龄',
  `name`            VARCHAR(50)          DEFAULT NULL
  COMMENT '名字',
  `phone`           VARCHAR(50) NOT NULL
  COMMENT '手机号',
  `password`        VARCHAR(50)          DEFAULT NULL
  COMMENT '密码',
  `role_id`         INT(11)              DEFAULT NULL
  COMMENT '用户对应的角色',
  `last_login_time` DATETIME             DEFAULT NULL
  COMMENT '最后登陆时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '12', 'zero', '188', '123', NULL, '2017-08-18 11:56:46');
INSERT INTO `user` VALUES ('2', '1', 'hello world', '110', '123', NULL, '2017-08-18 12:01:46');
INSERT INTO `user` VALUES ('3', '15', 'string', 'string', 'string', NULL, NULL);

-- ----------------------------
-- Table structure for user_check_count
-- ----------------------------
DROP TABLE IF EXISTS `user_check_count`;
CREATE TABLE `user_check_count` (
  `id`             INT(11)    NOT NULL AUTO_INCREMENT,
  `user_id`        INT(11)    NOT NULL
  COMMENT '关联的用户id',
  `check_time`     DATETIME   NOT NULL
  COMMENT '签到时间',
  `continue_count` INT(11)    NOT NULL
  COMMENT '连续签到天数',
  `max_count`      INT(11)    NOT NULL
  COMMENT '最长连续签到天数',
  `history`        BIGINT(11) NOT NULL
  COMMENT '签到历史记录',
  `sum`            INT(11)    NOT NULL
  COMMENT '总的签到天数',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of user_check_count
-- ----------------------------
INSERT INTO `user_check_count` VALUES ('1', '1', '2017-08-18 11:07:31', '1', '3', '29', '4');

-- ----------------------------
-- Table structure for user_point
-- ----------------------------
DROP TABLE IF EXISTS `user_point`;
CREATE TABLE `user_point` (
  `id`      INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11)          DEFAULT NULL
  COMMENT '用户id',
  `point`   INT(11)          DEFAULT NULL
  COMMENT '积分',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

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
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`     INT(11)          DEFAULT NULL
  COMMENT '用户id',
  `type`        VARCHAR(11)      DEFAULT NULL
  COMMENT '积分类型',
  `gain_point`  INT(11)          DEFAULT NULL
  COMMENT '获得的积分',
  `create_time` DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user_point_record
-- ----------------------------
INSERT INTO `user_point_record` VALUES ('1', '1', '签到', '2', '2017-08-18 11:02:54');
INSERT INTO `user_point_record` VALUES ('2', '1', '签到', '2', '2017-08-18 11:06:02');
INSERT INTO `user_point_record` VALUES ('3', '1', '连续签到', '5', '2017-08-18 11:06:54');
INSERT INTO `user_point_record` VALUES ('4', '1', '签到', '2', '2017-08-18 11:06:54');
INSERT INTO `user_point_record` VALUES ('5', '1', '签到', '2', '2017-08-18 11:07:31');
INSERT INTO `user_point_record` VALUES ('6', '2', '登录', '2', '2017-08-18 12:01:46');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id`      INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11)          DEFAULT NULL,
  `role_id` INT(11)          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
