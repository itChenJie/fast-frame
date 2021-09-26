/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 50651
 Source Host           : localhost:3306
 Source Schema         : fast_oms

 Target Server Type    : MySQL
 Target Server Version : 50651
 File Encoding         : 65001

 Date: 04/07/2021 16:52:05
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_dept
-- ----------------------------
DROP TABLE IF EXISTS `admin_dept`;
CREATE TABLE `admin_dept`
(
    `dept_id`     int(11) NOT NULL AUTO_INCREMENT,
    `pid`         int(22) DEFAULT '0' COMMENT '父级ID 顶级部门为0',
    `name`        varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '部门名称',
    `num`         int(10) DEFAULT NULL COMMENT '排序',
    `remark`      varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    `type`        int(4) DEFAULT NULL COMMENT '部门类型',
    `create_time` datetime                         NOT NULL COMMENT '创建时间',
    `update_time` datetime                         DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门';

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`
(
    `user_id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `account`         varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '帐户',
    `pass_word`       varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `status`          int(3) DEFAULT '2' COMMENT '状态,0禁用,1正常,2未激活,3离职',
    `mobile`          varchar(20) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '手机号',
    `user_name`       varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户姓名',
    `sex`             int(1) DEFAULT NULL COMMENT '性别 1 男 0女',
    `dept_id`         int(11) DEFAULT NULL COMMENT '部门id',
    `post`            varchar(50) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '岗位',
    `img`             varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
    `last_login_time` datetime                         DEFAULT NULL COMMENT '最后登陆时间',
    `last_login_ip`   varchar(50) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '最后登录IP',
    `email`           varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
    `create_time`     datetime                         NOT NULL COMMENT '创建时间',
    `update_time`     datetime                         DEFAULT NULL COMMENT '更新时间',
    `create_user_id`  bigint(20) DEFAULT NULL COMMENT '创建者ID',
    `update_user_id`  bigint(20) DEFAULT NULL COMMENT '更新者ID',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户';

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`
(
    `role_id`        int(11) NOT NULL AUTO_INCREMENT,
    `name`           varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
    `remark`         varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
    `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新者ID',
    `create_time`    datetime                         NOT NULL COMMENT '创建时间',
    `update_time`    datetime                         DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色';

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`
(
    `menu_id`        int(11) NOT NULL AUTO_INCREMENT,
    `name`           varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
    `pid`            int(22) DEFAULT '0' COMMENT '父级ID 顶级菜单为0',
    `url`            varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单url',
    `perms`          varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '授权 (多个用逗号分隔，如：user:list,user:create)',
    `type`           int(3) DEFAULT '0' COMMENT '类型 0：目录 1：菜单 2：按钮',
    `icon`           varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图标',
    `num`            int(10) DEFAULT NULL COMMENT '排序',
    `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
    `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新者ID',
    `create_time`    datetime                         NOT NULL COMMENT '创建时间',
    `update_time`    datetime                         DEFAULT NULL COMMENT '更新时间',
    `role`           varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限',
    PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单';

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色关联表';

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单角色关联表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `log_id`      int(11) NOT NULL AUTO_INCREMENT,
    `user_name`   varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
    `operation`   varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '用户操作',
    `method`      varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '请求方法',
    `params`      varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '请求参数',
    `time`        bigint(20) DEFAULT NULL COMMENT '执行时长(毫秒)',
    `ip`          varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IP地址',
    `create_time` datetime                         NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统日志';

SET
FOREIGN_KEY_CHECKS = 1;
