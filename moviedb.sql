/*
 Navicat Premium Data Transfer

 Source Server         : first
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : localhost:3306
 Source Schema         : moviedb

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 20/05/2024 17:56:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 往世乐土
-- ----------------------------
DROP TABLE IF EXISTS `往世乐土`;
CREATE TABLE `往世乐土`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` datetime(0) NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of 往世乐土
-- ----------------------------
INSERT INTO `往世乐土` VALUES (1, 'Kevin Kaslana', '2022-12-25 16:23:28', '救世');
INSERT INTO `往世乐土` VALUES (2, 'Elysia', '2022-12-23 19:53:00', '真我');
INSERT INTO `往世乐土` VALUES (3, 'Aponia', '2022-12-25 16:24:53', '戒律');
INSERT INTO `往世乐土` VALUES (4, 'Eden', '2022-12-25 16:25:25', '黄金');
INSERT INTO `往世乐土` VALUES (5, 'VILL-V', '2022-12-25 16:26:03', '螺旋');
INSERT INTO `往世乐土` VALUES (6, 'Kalpas', '2022-12-25 16:26:39', '鏖灭');
INSERT INTO `往世乐土` VALUES (7, 'Su', '2022-12-25 16:28:04', '天慧');
INSERT INTO `往世乐土` VALUES (8, 'Sakura', '2022-12-25 16:28:34', '刹那');
INSERT INTO `往世乐土` VALUES (9, 'Kosma', '2022-12-25 16:28:57', '旭光');
INSERT INTO `往世乐土` VALUES (10, 'Mobius', '2022-12-25 16:29:32', '无限');
INSERT INTO `往世乐土` VALUES (11, 'Griseo', '2022-12-21 16:29:58', '繁星');
INSERT INTO `往世乐土` VALUES (12, 'Hua', '2022-12-25 16:30:45', '浮生');
INSERT INTO `往世乐土` VALUES (13, 'Pardofelis', '2022-12-25 16:31:19', '空梦');

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
-- Table structure for chamber
-- ----------------------------
DROP TABLE IF EXISTS `chamber`;
CREATE TABLE `chamber`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `volumn` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of chamber
-- ----------------------------
INSERT INTO `chamber` VALUES (1, 'Avalon', 'Images/cover/20221223215207780.png', '123', 123);
INSERT INTO `chamber` VALUES (2, '1号厅', 'Images/cover/20221226162658364.jpg', '东', 123);
INSERT INTO `chamber` VALUES (3, '2号厅', 'Images/cover/20221226162726164.jpg', '西', 1223);
INSERT INTO `chamber` VALUES (4, '3号厅', 'Images/cover/2022122616274132.jpg', '南', 123);
INSERT INTO `chamber` VALUES (5, '4号厅', 'Images/cover/20221226162750787.jpg', '北', 231);
INSERT INTO `chamber` VALUES (7, '往世乐土', 'Images/cover/20221226204409882.png', 'wweeee', 121);

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
INSERT INTO `elite` VALUES (1000000007, '123456', '370781200304176378', 0, '1', 1, 25, 0, 15153380271, 'major', '356303328@qq.com', '1', '1', '1', '1');
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES (1000000004, '企业级1', '370581200304176378', 12345678900, 15788855666, 'name1', 0);
INSERT INTO `enterprise` VALUES (1000000005, '企业级2', '370581200305176378', 12345678901, 15788855667, 'name2', 0);
INSERT INTO `enterprise` VALUES (1000000006, '企业级2', '370581200306176378', 12345678902, 15788855668, 'name3', 0);

-- ----------------------------
-- Table structure for jobs
-- ----------------------------
DROP TABLE IF EXISTS `jobs`;
CREATE TABLE `jobs`  (
  `id` bigint(18) NOT NULL,
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jobs
-- ----------------------------
INSERT INTO `jobs` VALUES (1, '清洁工', 1000000004, 60, 0, 07, '1', '1', 1000, '123@qq.com', '清洁工');
INSERT INTO `jobs` VALUES (2, '前台', 1000000004, 40, 1, 06, '1', '1', 1800, '124@qq.com', '前台');
INSERT INTO `jobs` VALUES (3, '食堂阿姨', 1000000005, 60, 0, 05, '1', '1', 1600, '125@qq.com', '食堂阿姨');
INSERT INTO `jobs` VALUES (4, '宿管阿姨', 1000000005, 55, 1, 07, '1', '1', 1200, '126@qq.com', '宿管阿姨');
INSERT INTO `jobs` VALUES (5, '保安', 1000000006, 50, 2, 06, '1', '1', 1500, '127@qq.com', '保安');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `typeId` int(11) NOT NULL,
  `balance` float(255, 0) UNSIGNED NOT NULL,
  `regdate` date NOT NULL,
  `tel` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idNumber` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` int(2) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idNumber`(`idNumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, 'andy', 'andyliu', 1, 187, '2021-08-01', '13374645654', '300312199506150011', 00);
INSERT INTO `member` VALUES (2, 'ada', 'ada123', 3, 617, '2021-10-26', '13678096544', '430311199909120054', 00);
INSERT INTO `member` VALUES (3, 'tom', 'tom123', 1, 307, '2021-11-19', '13374645654', '430311199909120053', 00);
INSERT INTO `member` VALUES (4, 'kitty', '123', 1, 200, '2021-12-12', '13678096546', '331091199802140011', 01);
INSERT INTO `member` VALUES (5, 'super', '123', 1, 140, '2022-12-20', '123', '123', 01);
INSERT INTO `member` VALUES (6, 'lf', '123', 2, 106, '2022-12-26', '15012341666', '371111', 00);

-- ----------------------------
-- Table structure for membertype
-- ----------------------------
DROP TABLE IF EXISTS `membertype`;
CREATE TABLE `membertype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `amount` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `recharge` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of membertype
-- ----------------------------
INSERT INTO `membertype` VALUES (1, '普通用户', 5, 100, 0);
INSERT INTO `membertype` VALUES (2, '白银会员', 8, 95, 100);
INSERT INTO `membertype` VALUES (3, '黄金会员', 10, 80, 500);

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeId` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` float(10, 2) NOT NULL,
  `desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pic` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stock` int(10) UNSIGNED NOT NULL,
  `address` int(11) NOT NULL,
  `date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES (1, 6, '罗小黑战记', 39.90, '雨夜，一只流落街头的小黑猫被少女罗小白带回家，起名罗小黑。\r\n\r\n　　罗小黑不是一只普通的猫咪，它极通人性，会蹲马桶，不吃猫粮，长长的尾巴甚至能分裂成多个名为“黑咻”的生物体。与此同时，名为“谛听”的神秘人物，发动手下三匹长着翅膀的狼，搜寻着罗小黑的下落。\r\n\r\n　　不久，罗小白带着小黑到乡下探望堂哥阿根和爷爷，由此发生了种种离奇玄幻事件……', 'Images/cover/20221226160654955.jpg', 'MTJJ', '山新、刘明月、郝祥海、皇贞季、杨凝', 99, 3, '2022-12-28 10:54:33');
INSERT INTO `movie` VALUES (2, 16, '暴力之夜', 36.80, '该片讲述一群雇佣兵袭击一个富裕家庭的庄园，圣诞老人挺身而出，拯救他们和圣诞节和故事。', 'Images/cover/20221226160730446.jpg', '托米·维尔科拉', '大卫·哈伯', 22, 4, '2022-12-27 10:54:36');
INSERT INTO `movie` VALUES (3, 17, '独行月球', 39.60, '该片改编自韩国漫画家赵石创作的同名漫画，讲述了人类为抵御小行星的撞击，拯救地球而部署的“月盾计划”因陨石提前来袭而失败后，独孤月成为了“宇宙最后的人类”，开始了他在月球上破罐子破摔的生活的故事', 'Images/cover/2022122620391818.png', '张迟昱', '沈腾、马丽', 122, 5, '2022-12-28 10:54:43');
INSERT INTO `movie` VALUES (4, 2, '人生大事', 38.00, '该片讲述刑满释放的殡葬师三哥在一次出殡中遇见了孤儿武小文，意外地改变了三哥对职业和生活态度的故事。', 'Images/cover/20221226160813764.jpg', '韩延', '朱一龙', 12, 4, '2022-12-29 10:54:47');
INSERT INTO `movie` VALUES (5, 16, '狙击手', 35.00, '该片以抗美援朝战争中的“冷枪冷炮运动”为背景，讲述了中国志愿军在敌我军备力量悬殊的境地下，与美军精英狙击小队展开殊死较量的故事', 'Images/cover/20221226160830989.jpg', '张艺谋、张末', '陈永胜、章宇、刘奕铁、黄炎、王梓屹、陈铭杨', 12, 3, '2022-12-29 10:54:51');
INSERT INTO `movie` VALUES (7, 4, '万里归途', 29.50, '该片根据真实事件改编，讲述了前驻地外交官宗大伟与外交部新人成朗受命前往协助撤侨，任务顺利结束，却得知还有一批被困同胞，正在白婳的带领下，前往边境撤离点的故事', 'Images/cover/20221226160853638.jpg', '饶晓志', '张译、王俊凯、殷桃', 30, 4, '2022-12-31 10:54:57');
INSERT INTO `movie` VALUES (8, 14, '坠落', 39.90, '该片讲述了两个爱好极限运动的姑娘被困于2000英尺的电视塔上后奋力求生的故事', 'Images/cover/20221226160904248.jpg', '斯科特·曼', '格蕾丝·富尔顿、维吉尼亚·加德纳', 2, 3, '2022-12-30 10:55:02');
INSERT INTO `movie` VALUES (9, 14, '危笑', 39.90, '在目睹一名病人诡异的创伤经验之后，萝丝·卡特医师（索茜·贝肯 饰）开始经历一些她无法解释的恐怖事件。当一股令人不寒而栗的可怕力量开始占据她的生活，萝丝就必须面对她令人不安的阴暗过去，才能够存活并逃离她充满恐惧的全新现实。', 'Images/cover/20221226160915708.jpg', '帕克·芬恩', '索茜·贝肯、杰西·厄舍', 12, 3, '2022-12-30 10:55:07');
INSERT INTO `movie` VALUES (10, 18, '往世乐土', 29.00, '为了团结地球上存活的人类，续燃起对抗崩坏的信念，于此前梅博士和英桀的会议上揭示了始源之律者身份的爱莉希雅，对外谎称自己是第十三律者并发起了冠以“宴会”之名的自我讨伐，出席“宴会”的英桀只有凯文、维尔薇、阿波尼亚及伊甸，”离席的人们，是因为相信爱莉希雅而拒绝讨伐；出席的人们，是因为相信爱莉希雅而出席宴会。”', 'Images/cover/20221224174956784.png', '爱莉希雅', '逐火十三英桀', 100, 4, '2022-12-31 19:32:07');
INSERT INTO `movie` VALUES (11, 7, '分手的决心', 49.90, '该片讲述了刑警海俊在山上调查死亡事件的过程中，与死者的妻子宋瑞莱见面后发生的一系列故事。', 'Images/cover/20221226160957843.jpg', '朴赞郁', '由汤唯、朴海日、李贞贤、朴勇宇、高庚杓', 99, 4, '2022-12-29 15:10:00');
INSERT INTO `movie` VALUES (14, 2, '往世乐土', 1.00, '1', 'Images/cover/20221226185639928.png', '1', '1', 100, 1, '2022-12-27 18:56:00');
INSERT INTO `movie` VALUES (15, 2, '往世乐土', 123.00, '12', 'Images/cover/20221226210137938.png', '1', '123', 100, 1, '2022-12-25 21:01:00');

-- ----------------------------
-- Table structure for pardofelis
-- ----------------------------
DROP TABLE IF EXISTS `pardofelis`;
CREATE TABLE `pardofelis`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` datetime(0) NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pardofelis
-- ----------------------------
INSERT INTO `pardofelis` VALUES (1, 'Elysia', '2022-12-23 19:53:00', '不错');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `memberId` int(11) NOT NULL,
  `movieId` int(11) NOT NULL,
  `buyDate` datetime(0) NOT NULL,
  `backDate` datetime(0) DEFAULT NULL,
  `deposit` decimal(5, 0) UNSIGNED ZEROFILL NOT NULL,
  `userId` int(11) NOT NULL,
  `bname` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `chamberId` int(11) UNSIGNED ZEROFILL NOT NULL,
  `seat` int(4) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (00000000001, 1, 1, '2021-12-13 00:00:00', '2021-12-14 00:00:00', 00000, 1, '罗小黑战记', 00000000001, 0001);
INSERT INTO `record` VALUES (00000000002, 5, 1, '2022-12-23 19:10:16', '2022-12-23 19:32:58', 00032, 5, '罗小黑战记', 00000000001, 0001);
INSERT INTO `record` VALUES (00000000003, 5, 1, '2022-12-23 19:32:07', '2022-12-23 19:32:58', 00032, 5, '罗小黑战记', 00000000001, 0005);
INSERT INTO `record` VALUES (00000000004, 6, 11, '2022-12-26 15:48:18', NULL, 00044, 5, '分手的决心', 00000000001, 0012);
INSERT INTO `record` VALUES (00000000005, 5, 10, '2022-12-26 19:58:41', '2022-12-26 20:53:50', 00028, 5, '往世乐土', 00000000004, 0003);
INSERT INTO `record` VALUES (00000000006, 5, 1, '2022-12-26 20:49:14', '2022-12-26 20:53:50', 00036, 5, '罗小黑战记', 00000000003, 0012);
INSERT INTO `record` VALUES (00000000007, 3, 1, '2022-12-27 14:55:07', NULL, 00033, 3, '罗小黑战记', 00000000003, 0013);

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
INSERT INTO `sp` VALUES (0000000001, 1000000014);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '犯罪', 0);
INSERT INTO `type` VALUES (2, '剧情', 0);
INSERT INTO `type` VALUES (3, '科幻', 0);
INSERT INTO `type` VALUES (4, '战争', 0);
INSERT INTO `type` VALUES (5, '文艺', 0);
INSERT INTO `type` VALUES (6, '动漫', 0);
INSERT INTO `type` VALUES (7, '惊悚', 0);
INSERT INTO `type` VALUES (8, '轻松', 0);
INSERT INTO `type` VALUES (13, '家庭', 0);
INSERT INTO `type` VALUES (14, '恐怖', 0);
INSERT INTO `type` VALUES (16, '动作', 0);
INSERT INTO `type` VALUES (17, '喜剧', 0);
INSERT INTO `type` VALUES (18, '治愈', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id规格为id+1e9,比如你是17号id就是1000000017',
  `pwd` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `type` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '0是个人,1是公司,2是管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000000014 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- View structure for erecordview
-- ----------------------------
DROP VIEW IF EXISTS `erecordview`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY INVOKER VIEW `erecordview` AS select `r`.`id` AS `id`,`r`.`senddate` AS `senddate`,`r`.`backdate` AS `backdate`,`r`.`comment` AS `message`,`e`.`name` AS `ename`,`e`.`resume` AS `resume`,`c`.`name` AS `cname`,`j`.`name` AS `jname`,`r`.`state` AS `state`,`e`.`degrees` AS `degrees`,`e`.`major` AS `major`,`e`.`certificate` AS `certificate`,`e`.`intention` AS `intention`,`e`.`selfevaluation` AS `selfevaluation`,`e`.`experience` AS `experience` from (((`e2c_record` `r` join `elite` `e`) join `jobs` `j`) join `enterprise` `c`) where ((`r`.`eliteid` = `e`.`id`) and (`r`.`jobid` = `j`.`id`) and (`j`.`place` = `c`.`id`));

-- ----------------------------
-- View structure for recordview
-- ----------------------------
DROP VIEW IF EXISTS `recordview`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY INVOKER VIEW `recordview` AS select `r`.`id` AS `id`,`r`.`chamberId` AS `cid`,`r`.`seat` AS `seat`,`m`.`name` AS `memberName`,`b`.`name` AS `movieName`,`r`.`buyDate` AS `buyDate`,`r`.`backDate` AS `backDate`,`b`.`date` AS `returnDate`,`r`.`deposit` AS `deposit` from (((`record` `r` join `movie` `b`) join `member` `m`) join `membertype` `mt`) where ((`r`.`memberId` = `m`.`id`) and (`r`.`movieId` = `b`.`id`) and (`m`.`typeId` = `mt`.`id`));

SET FOREIGN_KEY_CHECKS = 1;
