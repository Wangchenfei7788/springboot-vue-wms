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

 Date: 23/08/2023 20:01:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

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
