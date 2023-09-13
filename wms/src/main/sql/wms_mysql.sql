/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : wms_mysql

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 13/09/2023 21:52:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '货名',
  `storage` int NOT NULL COMMENT '仓库',
  `goodType` int NOT NULL COMMENT '分类',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES (1, 'Xiaomi 13', 1, 1, 45, '手机');
INSERT INTO `good` VALUES (2, 'Xiaomi 11', 2, 1, 45, '手机');
INSERT INTO `good` VALUES (3, 'Xiaomi 12', 3, 1, 40, '手机');
INSERT INTO `good` VALUES (4, '足球', 2, 2, 40, '球类');

-- ----------------------------
-- Table structure for goodtype
-- ----------------------------
DROP TABLE IF EXISTS `goodtype`;
CREATE TABLE `goodtype`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分类名',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goodtype
-- ----------------------------
INSERT INTO `goodtype` VALUES (1, '数码产品', '手机，电脑');
INSERT INTO `goodtype` VALUES (2, '体育用品', '球类');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL,
  `menuCode` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menuName` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `menuLevel` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单的父code',
  `menuClick` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '点击触发的函数',
  `menuRight` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `menuComponent` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `menuIcon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '001', '超级管理员管理', '1', NULL, 'RootManger', '0', 'root/RootManger.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (2, '002', '管理员管理', '1', NULL, 'AdminManger', '0,1', 'admin/AdminManger.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (3, '003', '用户管理', '1', NULL, 'UserManger', '0,1', 'user/UserManger.vue', 'el-icon-user-solid');
INSERT INTO `menu` VALUES (4, '004', '仓库管理', '1', NULL, 'StorageManger', '0,1', 'storage/StorageManger.vue', 'el-icon-s-shop');
INSERT INTO `menu` VALUES (5, '005', '产品分类管理 ', '1', NULL, 'GoodTypeManger', '0,1', 'goodtype/GoodTypeManger.vue', 'el-icon-s-grid');
INSERT INTO `menu` VALUES (6, '006', '产品管理', '1', NULL, 'GoodManger', '0,1,2', 'good/GoodManger.vue', 'el-icon-s-goods');
INSERT INTO `menu` VALUES (7, '007', '记录管理', '1', NULL, 'RecordManger', '0,1,2', 'record/RecordManger.vue', 'el-icon-s-order');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `good` int NOT NULL COMMENT '货品id',
  `userId` int NULL DEFAULT NULL COMMENT '取货人/补货人',
  `adminId` int NULL DEFAULT NULL COMMENT '操作人id',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 1, 3, 2, 20, '2023-09-09 22:01:37', NULL);
INSERT INTO `record` VALUES (2, 3, 3, 1, 5, '2023-09-12 21:46:30', '入库');
INSERT INTO `record` VALUES (3, 2, 3, 1, 5, '2023-09-12 21:46:33', '');
INSERT INTO `record` VALUES (4, 4, 3, 1, 20, '2023-09-12 21:46:36', '足球入库');
INSERT INTO `record` VALUES (5, 1, 3, 1, 5, '2023-09-12 21:46:39', 'Xiaomi 13入库');
INSERT INTO `record` VALUES (6, 3, 3, 1, 5, '2023-09-12 21:46:43', '');
INSERT INTO `record` VALUES (7, 3, 3, 1, 5, '2023-09-12 21:46:46', '');
INSERT INTO `record` VALUES (8, 4, 3, 1, 5, '2023-09-12 21:46:49', '');
INSERT INTO `record` VALUES (9, 3, 3, 1, 5, '2023-09-12 21:46:51', '');
INSERT INTO `record` VALUES (10, 1, 3, 1, 5, '2023-09-12 21:46:53', 'Xiaomi 13入库');
INSERT INTO `record` VALUES (11, 1, 3, 1, 5, '2023-09-12 21:46:56', 'Xiaomi 13入库');
INSERT INTO `record` VALUES (12, 2, 3, 1, 5, '2023-09-12 21:45:29', '');
INSERT INTO `record` VALUES (13, 1, 3, 1, 5, '2023-09-12 21:47:42', 'Xiaomi 13入库');
INSERT INTO `record` VALUES (14, 1, 4, 1, 5, '2023-09-13 10:15:19', '');
INSERT INTO `record` VALUES (15, 3, 10, 1, 5, '2023-09-13 10:17:01', '入库');
INSERT INTO `record` VALUES (16, 4, 5, 1, 5, '2023-09-13 12:34:28', '');
INSERT INTO `record` VALUES (17, 1, 4, 1, -5, '2023-09-13 17:16:35', '出库');
INSERT INTO `record` VALUES (18, 2, 5, 1, -5, '2023-09-13 17:30:30', '出库');
INSERT INTO `record` VALUES (19, 2, 4, 1, -5, '2023-09-13 17:35:05', '');
INSERT INTO `record` VALUES (20, 1, 3, 1, 5, '2023-09-13 17:43:38', '');
INSERT INTO `record` VALUES (21, 1, 9, 1, -5, '2023-09-13 17:44:10', '');
INSERT INTO `record` VALUES (22, 2, 6, 2, 5, '2023-09-13 20:42:50', '');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '仓库名',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES (1, '滁州仓库', '滁州地区');
INSERT INTO `storage` VALUES (2, '南京仓库', '南京地区');
INSERT INTO `storage` VALUES (3, '上海仓库', '上海地区');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名字',
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `age` int NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话',
  `role_id` int NULL DEFAULT NULL COMMENT '⻆⾊ 0超级管理员，1管理员，2普通账号',
  `isValid` varchar(4) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'Y' COMMENT '是否有效，Y有效，其他⽆效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '超级管理员', '1234', 20, 1, '19965505788', 0, 'Y');
INSERT INTO `user` VALUES (2, 'admin', '管理员', '123', 19, 1, '17316358828', 1, 'Y');
INSERT INTO `user` VALUES (3, 'user', '用户', '123', 21, 0, '13837226382', 2, 'Y');
INSERT INTO `user` VALUES (4, 'user01', '用户01', '123', 21, 1, '15676897643', 2, 'Y');
INSERT INTO `user` VALUES (5, 'user02', '用户02', '123', 22, 0, '176576555543', 2, 'Y');
INSERT INTO `user` VALUES (6, 'user03', '用户03', '123', 21, 1, '17899886577', 2, 'Y');
INSERT INTO `user` VALUES (7, 'admin01', '管理员01', '123', 22, 0, '18977654568', 1, 'Y');
INSERT INTO `user` VALUES (8, 'admin02', '管理员02', '123', 18, 0, '17877669844', 1, 'Y');
INSERT INTO `user` VALUES (9, 'user04', '用户04', '123', 19, 0, '17655630988', 2, 'Y');
INSERT INTO `user` VALUES (10, 'user05', '用户05', '123', 20, 1, '19899785543', 2, 'Y');
INSERT INTO `user` VALUES (11, 'admin04', '管理员04', '123', 23, 1, '19876889052', 1, 'Y');
INSERT INTO `user` VALUES (12, 'admin05', '管理员05', '123', 24, 0, '15877900056', 1, 'Y');

SET FOREIGN_KEY_CHECKS = 1;
