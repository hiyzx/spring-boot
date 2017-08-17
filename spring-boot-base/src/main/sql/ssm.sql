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
  `last_login_time` DATETIME             DEFAULT NULL
  COMMENT '最后登陆时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 181
  DEFAULT CHARSET = latin1;

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
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `user_point` (
  `id`      INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11)          DEFAULT NULL
  COMMENT '用户id',
  `point`   INT(11)          DEFAULT NULL
  COMMENT '积分',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `user_point_record` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`     INT(11)          DEFAULT NULL
  COMMENT '用户id',
  `type`        INT(11)          DEFAULT NULL
  COMMENT '积分类型',
  `gain_point`  INT(11)          DEFAULT NULL
  COMMENT '获得的积分',
  `create_time` DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

