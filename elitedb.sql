/*
 Navicat Premium Data Transfer

 Source Server         : first
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : localhost:3306
 Source Schema         : elitedb

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 13/06/2024 20:32:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(10) UNSIGNED NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tel` bigint(12) NOT NULL,
  `state` int(1) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1000000011, 'admin0', 15153312457, 1);
INSERT INTO `admin` VALUES (1000000012, 'admin1', 15153312458, 1);
INSERT INTO `admin` VALUES (1000000013, 'admin2', 15153312459, 1);

-- ----------------------------
-- Table structure for e2c_record
-- ----------------------------
DROP TABLE IF EXISTS `e2c_record`;
CREATE TABLE `e2c_record`  (
  `id` int(8) NOT NULL,
  `eliteid` bigint(15) DEFAULT NULL,
  `jobid` bigint(15) DEFAULT NULL,
  `senddate` datetime(0) DEFAULT NULL,
  `backdate` datetime(0) DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e2c_record
-- ----------------------------
INSERT INTO `e2c_record` VALUES (1, 1000000000, 1, '2021-12-13 00:00:00', '2021-12-13 00:00:01', '求职', 0);
INSERT INTO `e2c_record` VALUES (2, 1000000000, 1, '2021-12-13 00:01:00', '2021-12-13 00:00:21', '不录用', 1);
INSERT INTO `e2c_record` VALUES (3, 1000000001, 2, '2021-12-15 00:00:00', '2021-12-13 00:00:11', '录用', 1);
INSERT INTO `e2c_record` VALUES (4, 1000000002, 3, '2021-12-13 00:00:00', '2021-12-13 06:00:01', '求职', 0);
INSERT INTO `e2c_record` VALUES (5, 1000000003, 5, '2021-12-13 00:01:00', '2021-12-13 05:00:21', '不录用', 1);
INSERT INTO `e2c_record` VALUES (6, 1000000007, 4, '2021-12-15 00:00:00', '2021-12-13 00:11:11', '录用', 1);
INSERT INTO `e2c_record` VALUES (7, 1000000008, 5, '2021-12-13 00:00:00', '2021-12-13 00:00:01', '你好', 0);
INSERT INTO `e2c_record` VALUES (8, 1000000010, 4, '2021-12-13 00:01:00', '2021-12-13 00:01:21', '不录用', 1);
INSERT INTO `e2c_record` VALUES (9, 1000000009, 3, '2021-12-15 00:00:00', '2021-12-13 00:02:11', '录用', 1);

-- ----------------------------
-- Table structure for elite
-- ----------------------------
DROP TABLE IF EXISTS `elite`;
CREATE TABLE `elite`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idnumber` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` int(1) UNSIGNED ZEROFILL NOT NULL,
  `resume` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` int(1) UNSIGNED ZEROFILL DEFAULT NULL,
  `age` int(2) UNSIGNED NOT NULL,
  `degrees` int(2) DEFAULT NULL,
  `tel` bigint(14) DEFAULT NULL,
  `major` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `certificate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `intention` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `selfevaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `experience` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`, `age`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of elite
-- ----------------------------
INSERT INTO `elite` VALUES (1000000000, 'name0', '370581200304176378', 0, '1', 1, 26, 0, 15153380272, 'major', '356303329@qq.com', '1', '1', '1', '1');
INSERT INTO `elite` VALUES (1000000001, 'name1', '370681200304176378', 0, '1', 1, 27, 0, 15153380273, 'major', '356303339@qq.com', '1', '1', '1', '1');
INSERT INTO `elite` VALUES (1000000002, 'name2', '370481200304176378', 0, '1', 1, 24, 0, 15153380275, 'major', '356303349@qq.com', '1', '1', '1', '1');
INSERT INTO `elite` VALUES (1000000003, 'name3', '370381200304176378', 0, '1', 1, 29, 0, 15153380274, 'major', '356303529@qq.com', '1', '1', '1', '1');
INSERT INTO `elite` VALUES (1000000007, '123456', '371202200303138619', 0, '1', 0, 25, 0, 15153380271, 'major', '356303328@qq.com', '2', '1', '1', '1');
INSERT INTO `elite` VALUES (1000000008, 'name8', '370682200304176378', 0, '1', 1, 27, 0, 15154560273, 'major', '356243339@qq.com', '1', '1', '1', '1');
INSERT INTO `elite` VALUES (1000000009, 'name9', '370483200304176378', 0, '1', 1, 24, 0, 15153346275, 'major', '356303114@qq.com', '1', '1', '1', '1');
INSERT INTO `elite` VALUES (1000000010, 'name10', '370384200304176378', 0, '1', 1, 29, 0, 15153380894, 'major', '351203529@qq.com', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for enterprise
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise`  (
  `id` bigint(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idnumber` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `license` bigint(14) UNSIGNED NOT NULL COMMENT '注册号',
  `tel` bigint(14) UNSIGNED NOT NULL,
  `ename` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称',
  `state` int(1) UNSIGNED ZEROFILL DEFAULT NULL,
  `addr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES (1000000004, '企业级1', '370581200304176378', 12345678900, 15788855666, 'name1', 0, '即墨');
INSERT INTO `enterprise` VALUES (1000000005, '企业级2', '370781200304176379', 12345678901, 15788855667, 'name2', 0, '即墨');
INSERT INTO `enterprise` VALUES (1000000006, '企业级2', '370581200306176378', 12345678902, 15788855668, 'name3', 0, '即墨');

-- ----------------------------
-- Table structure for jobs
-- ----------------------------
DROP TABLE IF EXISTS `jobs`;
CREATE TABLE `jobs`  (
  `id` bigint(18) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `place` bigint(20) NOT NULL COMMENT '所属单位',
  `age` int(3) UNSIGNED DEFAULT NULL,
  `gender` int(1) UNSIGNED ZEROFILL DEFAULT NULL COMMENT '0代表不设限,1表示限定男性,2表示限定女性',
  `degrees` int(2) UNSIGNED ZEROFILL NOT NULL,
  `major` varchar(50) CHARACTER SET utf8 COLLATE utf8_german2_ci DEFAULT NULL,
  `certificates` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `salary` int(8) NOT NULL COMMENT '最低工资',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jobs
-- ----------------------------
INSERT INTO `jobs` VALUES (1, '清洁工', 1000000004, 60, 0, 07, '1', '1', 1000, '123@qq.com', '清洁工');
INSERT INTO `jobs` VALUES (2, '前台', 1000000004, 40, 1, 06, '1', '1', 1800, '124@qq.com', '前台');
INSERT INTO `jobs` VALUES (3, '食堂阿姨', 1000000005, 60, 0, 05, '1', '1', 1600, '125@qq.com', '食堂阿姨');
INSERT INTO `jobs` VALUES (4, '宿管阿姨', 1000000005, 55, 1, 07, '1', '1', 1200, '126@qq.com', '宿管阿姨');
INSERT INTO `jobs` VALUES (5, '保安', 1000000006, 50, 2, 06, '1', '1', 1500, '127@qq.com', '保安');
INSERT INTO `jobs` VALUES (13, '2121', 1000000005, 21, 0, 00, '1', '1', 2, '2', '1');

-- ----------------------------
-- Table structure for sp
-- ----------------------------
DROP TABLE IF EXISTS `sp`;
CREATE TABLE `sp`  (
  `id` bigint(10) UNSIGNED ZEROFILL NOT NULL,
  `name` bigint(15) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sp
-- ----------------------------
INSERT INTO `sp` VALUES (0000000001, 1000000015);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id规格为id+1e9,比如你是17号id就是1000000017',
  `pwd` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `type` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '0是个人,1是公司,2是管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000000015 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1000000000, '1212', 0);
INSERT INTO `user` VALUES (1000000001, '1212', 0);
INSERT INTO `user` VALUES (1000000002, '123', 0);
INSERT INTO `user` VALUES (1000000003, '1717', 0);
INSERT INTO `user` VALUES (1000000004, '542', 1);
INSERT INTO `user` VALUES (1000000005, '999', 1);
INSERT INTO `user` VALUES (1000000006, '5217', 1);
INSERT INTO `user` VALUES (1000000007, '123', 0);
INSERT INTO `user` VALUES (1000000008, '123', 0);
INSERT INTO `user` VALUES (1000000009, '123', 0);
INSERT INTO `user` VALUES (1000000010, '123', 0);
INSERT INTO `user` VALUES (1000000011, '123', 2);
INSERT INTO `user` VALUES (1000000012, '123', 2);
INSERT INTO `user` VALUES (1000000013, '123', 2);
INSERT INTO `user` VALUES (1000000014, '123', 0);

-- ----------------------------
-- View structure for erecordview
-- ----------------------------
DROP VIEW IF EXISTS `erecordview`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY INVOKER VIEW `erecordview` AS select `r`.`id` AS `id`,`r`.`senddate` AS `senddate`,`r`.`backdate` AS `backdate`,`r`.`comment` AS `message`,`e`.`name` AS `ename`,`e`.`resume` AS `resume`,`c`.`name` AS `cname`,`j`.`name` AS `jname`,`r`.`state` AS `state`,`e`.`degrees` AS `degrees`,`e`.`major` AS `major`,`e`.`certificate` AS `certificate`,`e`.`intention` AS `intention`,`e`.`selfevaluation` AS `selfevaluation`,`e`.`experience` AS `experience` from (((`e2c_record` `r` join `elite` `e`) join `jobs` `j`) join `enterprise` `c`) where ((`r`.`eliteid` = `e`.`id`) and (`r`.`jobid` = `j`.`id`) and (`j`.`place` = `c`.`id`));

SET FOREIGN_KEY_CHECKS = 1;
