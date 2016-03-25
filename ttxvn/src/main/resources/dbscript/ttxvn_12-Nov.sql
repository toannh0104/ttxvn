-- --------------------------------------------------------
-- Host:                         192.168.0.138
-- Server version:               5.6.20 - MySQL Community Server (GPL)
-- Server OS:                    linux-glibc2.5
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for ttxvndb
DROP DATABASE IF EXISTS `ttxvndb`;
CREATE DATABASE IF NOT EXISTS `ttxvndb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ttxvndb`;


-- Dumping structure for table ttxvndb.category
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_USER_ID` bigint(20) DEFAULT NULL,
  `IS_GLOBAL` bit(1) DEFAULT NULL,
  `LANG_CODE` int(11) DEFAULT NULL,
  `LAST_MODIFIED` datetime DEFAULT NULL,
  `LAST_MODIFIED_USER_ID` bigint(20) DEFAULT NULL,
  `NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_6pr2m7ajyeu61poaebw0f0jq1` (`USER_ID`),
  CONSTRAINT `FK_6pr2m7ajyeu61poaebw0f0jq1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.category: ~12 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`ID`, `CREATED_DATE`, `CREATED_USER_ID`, `IS_GLOBAL`, `LANG_CODE`, `LAST_MODIFIED`, `LAST_MODIFIED_USER_ID`, `NAME`, `USER_ID`) VALUES
	(1, '2014-10-13 00:00:00', 1, b'1', 1, '2014-11-11 00:00:00', 1, 'Xã hội', NULL),
	(34, '2014-10-14 00:00:00', 1, b'1', 1, '2014-11-11 00:00:00', 1, 'Thế giới', NULL),
	(35, '2014-10-14 00:00:00', 1, b'1', 1, '2014-11-11 00:00:00', 1, 'Kinh tế', NULL),
	(36, '2014-10-14 00:00:00', 1, b'1', 0, '2014-10-14 00:00:00', 1, 'news', NULL),
	(37, '2014-10-14 00:00:00', 1, b'1', 0, '2014-10-14 00:00:00', 1, 'politics', NULL),
	(38, '2014-10-14 00:00:00', 1, b'1', 1, '2014-10-14 00:00:00', 1, 'Văn hóa', NULL),
	(39, '2014-10-14 00:00:00', 1, b'1', 1, '2014-11-11 00:00:00', 1, 'Tinh tế', NULL),
	(40, '2014-10-23 00:00:00', 1, b'1', 0, '2014-10-23 00:00:00', 1, 'sport', NULL),
	(41, '2014-10-23 00:00:00', 1, b'1', 0, '2014-10-23 00:00:00', 1, 'world', NULL),
	(42, '2014-10-23 00:00:00', 1, b'1', 0, '2014-10-23 00:00:00', 1, 'economic', NULL),
	(43, '2014-10-23 00:00:00', 1, b'1', 0, '2014-10-23 00:00:00', 1, 'culuture', NULL),
	(44, '2014-10-23 00:00:00', 1, b'1', 0, '2014-10-23 00:00:00', 1, 'history', NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.category_keyword
DROP TABLE IF EXISTS `category_keyword`;
CREATE TABLE IF NOT EXISTS `category_keyword` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoryId` bigint(20) DEFAULT NULL,
  `CATEGORY_NAME` varchar(250) CHARACTER SET utf8 NOT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `keywordId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_lum0xoe2lw413ykpl488arsji` (`categoryId`),
  KEY `FK_r09ou9lo0ai4h82b2iims7tnp` (`keywordId`),
  KEY `FK_4i1tuaeeisvnbkmtg8o7t6wa0` (`userId`),
  CONSTRAINT `FK_4i1tuaeeisvnbkmtg8o7t6wa0` FOREIGN KEY (`userId`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK_lum0xoe2lw413ykpl488arsji` FOREIGN KEY (`categoryId`) REFERENCES `category` (`ID`),
  CONSTRAINT `FK_r09ou9lo0ai4h82b2iims7tnp` FOREIGN KEY (`keywordId`) REFERENCES `keyword` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.category_keyword: ~29 rows (approximately)
/*!40000 ALTER TABLE `category_keyword` DISABLE KEYS */;
INSERT INTO `category_keyword` (`ID`, `categoryId`, `CATEGORY_NAME`, `CREATED_DATE`, `keywordId`, `userId`) VALUES
	(23, 1, 'Xã hội', '2014-10-13', 23, 1),
	(24, 1, 'Xã hội', '2014-10-14', 24, 1),
	(25, 34, 'The gioi', '2014-10-14', 25, 1),
	(26, 35, 'kinh tế', '2014-10-14', 26, 1),
	(27, 39, 'giai tri', '2014-10-14', 27, 1),
	(28, 1, 'Xã hội', '2014-10-14', 28, 1),
	(29, 1, 'Xã hội', '2014-10-14', 29, 1),
	(30, 36, 'news', '2014-10-14', 30, 1),
	(31, 36, 'news', '2014-10-14', 31, 1),
	(32, 36, 'news', '2014-10-14', 32, 1),
	(33, 36, 'news', '2014-10-14', 33, 1),
	(34, 1, 'Xã hội', '2014-10-14', 34, 56),
	(35, 1, 'Xã hội', '2014-10-14', 35, 54),
	(36, 35, 'kinh tế', '2014-10-14', 36, 54),
	(37, 1, 'Xã hội', '2014-10-14', 37, 56),
	(38, 1, 'Xã hội', '2014-10-23', 38, 1),
	(39, 36, 'news', '2014-10-23', 39, 1),
	(40, 1, 'Xã hội', '2014-10-29', 40, 1),
	(41, 1, 'Xã hội', '2014-10-29', 41, 1),
	(46, 1, 'Xã hội', '2014-11-03', 46, 1),
	(51, 34, 'The gioi', '2014-11-03', 51, 1),
	(57, 1, 'Xã hội', '2014-11-04', 57, 1),
	(71, 1, 'Xã hội', '2014-11-04', 71, 1),
	(78, 1, 'Xã hội', '2014-11-05', 78, 1),
	(91, 1, 'Xã hội', '2014-11-07', 91, 1),
	(92, 36, 'news', '2014-11-11', 92, 1),
	(94, 1, 'Xã hội', '2014-11-11', 94, 1),
	(95, 1, 'Xã hội', '2014-11-11', 95, 65),
	(96, 1, 'Xã hội', '2014-11-11', 96, 1),
	(97, 1, 'Xã hội', '2014-11-11', 97, 1);
/*!40000 ALTER TABLE `category_keyword` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.fetch_frequency
DROP TABLE IF EXISTS `fetch_frequency`;
CREATE TABLE IF NOT EXISTS `fetch_frequency` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` date NOT NULL,
  `CREATED_USER_ID` bigint(20) NOT NULL,
  `FREQUENCY` int(11) NOT NULL,
  `NAME_CODE` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_my53orb62j0v0cmq9vm7ptnb4` (`NAME_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.fetch_frequency: ~3 rows (approximately)
/*!40000 ALTER TABLE `fetch_frequency` DISABLE KEYS */;
INSERT INTO `fetch_frequency` (`ID`, `CREATED_DATE`, `CREATED_USER_ID`, `FREQUENCY`, `NAME_CODE`) VALUES
	(1, '2014-10-07', 1, 15, '1'),
	(2, '2014-10-07', 1, 60, '2'),
	(3, '2014-10-07', 1, 120, '3');
/*!40000 ALTER TABLE `fetch_frequency` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.groups
DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_USER_ID` bigint(20) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `STATUS` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_i7rcwfmf61xtaxs6on3j24ls5` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- Dumping data for table ttxvndb.groups: ~8 rows (approximately)
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` (`ID`, `CREATED_DATE`, `CREATED_USER_ID`, `description`, `NAME`, `STATUS`) VALUES
	(1, '2014-09-30 00:00:00', 1, 'System Admin', 'System Admin', 1),
	(2, '2014-09-30 00:00:00', 1, 'TTXVN User', 'TTXVN User', 1),
	(29, '2014-10-13 00:00:00', 1, 'Quản lý hệ thống thông tin', 'Quản lý hệ thống thông tin group', 1),
	(30, '2014-10-14 00:00:00', 1, 'Quản lý chủ đề', 'Quản lý chủ đề', 1),
	(31, '2014-10-14 00:00:00', 1, 'Thống kê báo cáo', 'Thống kê báo cáo', 1),
	(32, '2014-10-14 00:00:00', 1, 'Quản lý httt', 'Quản lý user', 1),
	(40, '2014-11-07 00:00:00', 1, '122', 'tesst01', 0),
	(41, '2014-11-07 00:00:00', 1, 'test02', 'test02', 1);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.group_role
DROP TABLE IF EXISTS `group_role`;
CREATE TABLE IF NOT EXISTS `group_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_USER_ID` bigint(20) NOT NULL,
  `GROUP_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ai5bcxq7knpcu1hvw4xfcurce` (`GROUP_ID`),
  KEY `FK_h78aywafwqb6nqic4hlk2wqqn` (`ROLE_ID`),
  CONSTRAINT `FK_ai5bcxq7knpcu1hvw4xfcurce` FOREIGN KEY (`GROUP_ID`) REFERENCES `groups` (`ID`),
  CONSTRAINT `FK_h78aywafwqb6nqic4hlk2wqqn` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.group_role: ~9 rows (approximately)
/*!40000 ALTER TABLE `group_role` DISABLE KEYS */;
INSERT INTO `group_role` (`ID`, `CREATED_DATE`, `CREATED_USER_ID`, `GROUP_ID`, `ROLE_ID`) VALUES
	(1, '2014-10-11 00:00:00', 1, 1, 6),
	(2, '2014-10-11 00:00:00', 1, 2, 1),
	(58, '2014-10-13 00:00:00', 1, 29, 4),
	(59, '2014-10-13 00:00:00', 1, 29, 1),
	(60, '2014-10-14 00:00:00', 1, 30, 3),
	(61, '2014-10-14 00:00:00', 1, 31, 5),
	(63, '2014-10-14 00:00:00', 1, 32, 2),
	(74, '2014-11-07 00:00:00', 1, 40, 5),
	(75, '2014-11-07 00:00:00', 1, 41, 5);
/*!40000 ALTER TABLE `group_role` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.job_tracker
DROP TABLE IF EXISTS `job_tracker`;
CREATE TABLE IF NOT EXISTS `job_tracker` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `isDone` bit(1) DEFAULT NULL,
  `jobName` varchar(255) DEFAULT NULL,
  `lastFired` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.job_tracker: ~1 rows (approximately)
/*!40000 ALTER TABLE `job_tracker` DISABLE KEYS */;
INSERT INTO `job_tracker` (`ID`, `isDone`, `jobName`, `lastFired`) VALUES
	(49, b'1', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', '2014-11-12');
/*!40000 ALTER TABLE `job_tracker` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.keyword
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE IF NOT EXISTS `keyword` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `LAST_MODIFIED` date DEFAULT NULL,
  `LAST_MODIFIED_USER_ID` bigint(20) DEFAULT NULL,
  `NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `START_DATE` date DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.keyword: ~30 rows (approximately)
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` (`ID`, `CREATED_DATE`, `END_DATE`, `LAST_MODIFIED`, `LAST_MODIFIED_USER_ID`, `NAME`, `START_DATE`, `STATUS`) VALUES
	(23, '2014-10-13', '2014-10-30', '2014-11-11', 1, 'Ghi nhớ', '2014-10-13', 2),
	(24, '2014-10-14', '2014-10-22', '2014-10-22', 1, 'kinh doanh', '2014-10-14', 2),
	(25, '2014-10-14', '2014-10-14', NULL, 1, 'hoi giao is', '2014-10-14', 2),
	(26, '2014-10-14', '2014-10-15', NULL, 1, 'chứng khoán', '2014-10-14', 2),
	(27, '2014-10-14', '2014-10-16', NULL, 1, 'mariah carey', '2014-10-14', 2),
	(28, '2014-10-14', '2014-10-22', '2014-10-15', 1, 'Tài chính', '2014-10-14', 2),
	(29, '2014-10-14', '2014-10-25', '2014-10-24', 1, 'virus ebola', '2014-10-14', 2),
	(30, '2014-10-14', '2014-10-23', '2014-10-23', 1, 'Internet Explorer 8', '2014-10-14', 2),
	(31, '2014-10-14', '2014-10-23', '2014-10-23', 1, 'Women', '2014-10-14', 2),
	(32, '2014-10-14', '2014-10-30', '2014-10-23', 1, 'video', '2014-10-14', 2),
	(33, '2014-10-14', '2014-10-24', '2014-10-24', 1, 'candidate', '2014-10-14', 2),
	(34, '2014-10-14', '2014-10-16', NULL, 56, 'mía đường', '2014-10-14', 2),
	(35, '2014-10-14', '2014-10-15', NULL, 54, 'mía đường', '2014-10-14', 2),
	(36, '2014-10-14', '2014-10-16', NULL, 54, 'chứng khoán', '2014-10-14', 2),
	(37, '2014-10-14', '2014-10-16', NULL, 56, 'Dang cong san', '2014-10-14', 2),
	(38, '2014-10-23', '2014-10-24', '2014-10-24', 1, 'giá xăng', '2014-10-23', 2),
	(39, '2014-10-23', '2014-10-24', NULL, 1, 'life', '2014-10-23', 2),
	(40, '2014-10-29', '2014-10-31', NULL, 1, 'Tự thiết kế thiệp cưới', '2014-10-29', 2),
	(41, '2014-10-29', '2014-10-30', NULL, 1, 'giấy khai sinh', '2014-10-29', 2),
	(46, '2014-11-03', '2014-11-04', NULL, 1, 'kế toán', '2014-11-03', 2),
	(51, '2014-11-03', '2014-11-20', NULL, 1, 'EBOLa', '2014-11-03', 0),
	(57, '2014-11-04', '2014-11-05', NULL, 1, 'kinh tế', '2014-11-04', 2),
	(71, '2014-11-04', '2014-11-05', NULL, 1, 'doanh nhân', '2014-11-04', 2),
	(78, '2014-11-05', '2014-11-06', '2014-11-06', 1, 'công sở', '2014-11-05', 2),
	(91, '2014-11-07', '2014-11-10', NULL, 1, 'lao động', '2014-11-07', 2),
	(92, '2014-11-11', '2014-11-12', NULL, 1, 'nigeria', '2014-11-11', 0),
	(94, '2014-11-11', '2014-11-13', NULL, 1, 'Nhật Bản', '2014-11-11', 0),
	(95, '2014-11-11', '2014-11-12', NULL, 65, 'Kinh Doanh', '2014-11-11', 0),
	(96, '2014-11-11', '2014-11-13', NULL, 1, 'nhật bản', '2014-11-11', 0),
	(97, '2014-11-11', '2014-11-12', NULL, 1, 'Lawrence', '2014-11-11', 0);
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.permission
DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_USER_ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_hh146wkedpc77b91jxtku7s9h` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.permission: ~6 rows (approximately)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` (`ID`, `CREATED_DATE`, `CREATED_USER_ID`, `DESCRIPTION`, `NAME`) VALUES
	(1, '2014-09-30 00:00:00', 1, NULL, 'USER'),
	(2, '2014-09-30 00:00:00', 1, NULL, 'MANAGE_USER'),
	(3, '2014-09-30 00:00:00', 1, NULL, 'MANAGE_CATEGORY'),
	(4, '2014-09-30 00:00:00', 1, NULL, 'MANAGE_SOURCE_URL'),
	(5, '2014-09-30 00:00:00', 1, NULL, 'REPORTER'),
	(6, '2014-09-30 00:00:00', 1, NULL, 'SYSTEM_ADMIN');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.persistent_logins
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `username` varchar(250) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ttxvndb.persistent_logins: ~0 rows (approximately)
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_BLOB_TRIGGERS
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE IF NOT EXISTS `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_BLOB_TRIGGERS: ~0 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_CALENDARS
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE IF NOT EXISTS `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_CALENDARS: ~0 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_CALENDARS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_CRON_TRIGGERS
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE IF NOT EXISTS `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_CRON_TRIGGERS: ~0 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES
	('quartzScheduler', '53e41f82b4ff-cc74bc4d-fdc0-465f-8e97-5f4122c337c4com.vsii.ttxvn.keywordsearching.schedule.KeywordStatusJob', 'com.vsii.ttxvn.keywordsearching.schedule.KeywordStatusJob', '0 0 0 * * ?', 'Asia/Saigon');
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_FIRED_TRIGGERS
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE IF NOT EXISTS `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_FIRED_TRIGGERS: ~1 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_JOB_DETAILS
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE IF NOT EXISTS `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_JOB_DETAILS: ~4 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` DISABLE KEYS */;
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES
	('quartzScheduler', '1', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', NULL, 'com.vsii.ttxvn.keywordsearching.schedule.FetchFrequenceJobHandler', '0', '1', '0', '0', _binary 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000F770800000010000000007800),
	('quartzScheduler', '2', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', NULL, 'com.vsii.ttxvn.keywordsearching.schedule.FetchFrequenceJobHandler', '0', '1', '0', '0', _binary 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000F770800000010000000007800),
	('quartzScheduler', '3', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', NULL, 'com.vsii.ttxvn.keywordsearching.schedule.FetchFrequenceJobHandler', '0', '1', '0', '0', _binary 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000F770800000010000000007800),
	('quartzScheduler', 'com.vsii.ttxvn.keywordsearching.schedule.KeywordStatusJob', 'com.vsii.ttxvn.keywordsearching.schedule.KeywordStatusJob', NULL, 'com.vsii.ttxvn.keywordsearching.schedule.KeywordStatusJob', '0', '1', '0', '0', _binary 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000F770800000010000000007800);
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_LOCKS
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE IF NOT EXISTS `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_LOCKS: ~0 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_LOCKS` DISABLE KEYS */;
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES
	('quartzScheduler', 'TRIGGER_ACCESS');
/*!40000 ALTER TABLE `QRTZ_LOCKS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_PAUSED_TRIGGER_GRPS
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE IF NOT EXISTS `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_PAUSED_TRIGGER_GRPS: ~0 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_SCHEDULER_STATE
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE IF NOT EXISTS `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_SCHEDULER_STATE: ~0 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_SIMPLE_TRIGGERS
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE IF NOT EXISTS `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_SIMPLE_TRIGGERS: ~3 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_SIMPLE_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `REPEAT_COUNT`, `REPEAT_INTERVAL`, `TIMES_TRIGGERED`) VALUES
	('quartzScheduler', '070ace3fc2e7-410deae7-0340-41d0-a598-8606eaca771b1', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', -1, 60000, 332),
	('quartzScheduler', '070ace3fc2e7-41511a66-5108-4431-a5d6-f87121af68e73', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', -1, 7200000, 3),
	('quartzScheduler', '070ace3fc2e7-d444055c-66df-4b10-bf82-8ba11f956eb72', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', -1, 3600000, 6);
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_SIMPROP_TRIGGERS
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE IF NOT EXISTS `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_SIMPROP_TRIGGERS: ~0 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.QRTZ_TRIGGERS
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE IF NOT EXISTS `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.QRTZ_TRIGGERS: ~4 rows (approximately)
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES
	('quartzScheduler', '070ace3fc2e7-410deae7-0340-41d0-a598-8606eaca771b1', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', '1', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', NULL, 1415785732378, 1415785672378, 5, 'WAITING', 'SIMPLE', 1415765812378, 0, NULL, 0, _binary ''),
	('quartzScheduler', '070ace3fc2e7-41511a66-5108-4431-a5d6-f87121af68e73', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', '3', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', NULL, 1415787412790, 1415780212790, 5, 'WAITING', 'SIMPLE', 1415765812790, 0, NULL, 0, _binary ''),
	('quartzScheduler', '070ace3fc2e7-d444055c-66df-4b10-bf82-8ba11f956eb72', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', '2', 'com.vsii.ttxvn.keywordsearching.entity.FetchFrequency', NULL, 1415787412650, 1415783812650, 5, 'WAITING', 'SIMPLE', 1415765812650, 0, NULL, 0, _binary ''),
	('quartzScheduler', '53e41f82b4ff-cc74bc4d-fdc0-465f-8e97-5f4122c337c4com.vsii.ttxvn.keywordsearching.schedule.KeywordStatusJob', 'com.vsii.ttxvn.keywordsearching.schedule.KeywordStatusJob', 'com.vsii.ttxvn.keywordsearching.schedule.KeywordStatusJob', 'com.vsii.ttxvn.keywordsearching.schedule.KeywordStatusJob', NULL, 1415811600000, 1415757933072, 5, 'WAITING', 'CRON', 1412653296000, 0, NULL, 0, _binary '');
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_USER_ID` bigint(20) DEFAULT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_l5owutjid2yl0vvu8rh95r0nc` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.role: ~6 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`ID`, `CREATED_DATE`, `CREATED_USER_ID`, `DESCRIPTION`, `NAME`) VALUES
	(1, '2014-09-30 00:00:00', 1, 'TTXVN User', 'User'),
	(2, '2014-09-30 00:00:00', 1, 'Manage User', 'Manage User'),
	(3, '2014-09-30 00:00:00', 1, 'Manage Category', 'Manage Category'),
	(4, '2014-09-30 00:00:00', 1, 'Manage Source URL', 'Manage Source URL'),
	(5, '2014-09-30 00:00:00', 1, 'Reporter', 'Reporter'),
	(6, '2014-09-30 00:00:00', 1, 'System Admin', 'System Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.role_permission
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE IF NOT EXISTS `role_permission` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_USER_ID` bigint(20) NOT NULL,
  `PERMISSION_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_aayrec1nvkp6fdj811b770gwa` (`PERMISSION_ID`),
  KEY `FK_gl4juord6px1bnbl0bwuwprit` (`ROLE_ID`),
  CONSTRAINT `FK_aayrec1nvkp6fdj811b770gwa` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FK_gl4juord6px1bnbl0bwuwprit` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.role_permission: ~11 rows (approximately)
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` (`ID`, `CREATED_DATE`, `CREATED_USER_ID`, `PERMISSION_ID`, `ROLE_ID`) VALUES
	(1, '2014-09-30 00:00:00', 1, 1, 6),
	(2, '2014-09-30 00:00:00', 1, 2, 6),
	(3, '2014-09-30 00:00:00', 1, 3, 6),
	(4, '2014-09-30 00:00:00', 1, 4, 6),
	(5, '2014-09-30 00:00:00', 1, 5, 6),
	(6, '2014-09-30 00:00:00', 1, 6, 6),
	(7, '2014-09-30 00:00:00', 1, 1, 1),
	(9, '2014-10-13 00:00:00', 1, 2, 2),
	(10, '2014-10-13 00:00:00', 1, 3, 3),
	(11, '2014-10-13 00:00:00', 1, 4, 4),
	(12, '2014-10-13 00:00:00', 1, 5, 5);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.source_url
DROP TABLE IF EXISTS `source_url`;
CREATE TABLE IF NOT EXISTS `source_url` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRAWLING_STATUS` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_USER_ID` bigint(20) NOT NULL,
  `DOMAIN` varchar(250) NOT NULL,
  `LANG_CODE` int(11) NOT NULL,
  `LAST_FETCHED` datetime DEFAULT NULL,
  `LAST_MODIFIED` datetime DEFAULT NULL,
  `LAST_MODIFIED_USER_ID` bigint(20) DEFAULT NULL,
  `RELIABILITY` int(11) DEFAULT NULL,
  `SOURCE_TYPE` int(11) DEFAULT NULL,
  `URL` longtext NOT NULL,
  `CATEGORY_ID` bigint(20) DEFAULT NULL,
  `FETCH_FREQUENCY_ID` bigint(20) DEFAULT NULL,
  `FETCH_DEEP` int(11) DEFAULT NULL,
  `DEEP` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_s1cqkk41axmt32bcb84efncet` (`CATEGORY_ID`),
  KEY `FK_aa5461fykl1jfhyhbvs6s43ek` (`FETCH_FREQUENCY_ID`),
  CONSTRAINT `FK_aa5461fykl1jfhyhbvs6s43ek` FOREIGN KEY (`FETCH_FREQUENCY_ID`) REFERENCES `fetch_frequency` (`ID`),
  CONSTRAINT `FK_s1cqkk41axmt32bcb84efncet` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.source_url: ~3 rows (approximately)
/*!40000 ALTER TABLE `source_url` DISABLE KEYS */;
INSERT INTO `source_url` (`ID`, `CRAWLING_STATUS`, `CREATED_DATE`, `CREATED_USER_ID`, `DOMAIN`, `LANG_CODE`, `LAST_FETCHED`, `LAST_MODIFIED`, `LAST_MODIFIED_USER_ID`, `RELIABILITY`, `SOURCE_TYPE`, `URL`, `CATEGORY_ID`, `FETCH_FREQUENCY_ID`, `FETCH_DEEP`, `DEEP`) VALUES
	(28, 1, '2014-10-14 00:00:00', 1, 'bbc.co.uk', 0, '2014-11-12 15:02:55', NULL, NULL, 90, 0, 'www.bbc.co.uk/news/', 37, 1, 2, NULL),
	(30, 1, '2014-10-14 00:00:00', 56, 'dangcongsan.vn', 1, '2014-11-12 15:02:55', NULL, NULL, 100, 0, 'dangcongsan.vn/cpv/', 38, 1, 2, NULL),
	(36, 1, '2014-10-23 00:00:00', 1, 'doisongphapluat.com', 1, '2014-11-12 15:02:55', NULL, NULL, 70, 0, 'www.doisongphapluat.com', 1, 1, 2, NULL),
	(38, 0, '2014-10-23 00:00:00', 1, 'www.xinhuanet.com', 0, '2014-11-12 15:02:55', NULL, NULL, 100, 0, 'www.xinhuanet.com/english', 36, 1, 2, NULL),
	(39, 0, '2014-10-23 00:00:00', 1, 'www.afp.com', 0, '2014-11-12 15:02:55', NULL, NULL, 100, 0, 'www.afp.com/en/home', 36, 1, 2, NULL),
	(40, 1, '2014-10-24 00:00:00', 1, 'ngoisao.net', 1, '2014-11-12 15:02:55', NULL, NULL, 100, 0, 'ngoisao.net/', 39, 1, 2, NULL),
	(42, 1, '2014-10-24 00:00:00', 1, 'vietnamnet.vn', 1, '2014-11-12 15:02:55', NULL, NULL, 100, 0, 'vietnamnet.vn/', 35, 1, 2, NULL),
	(43, 1, '2014-10-24 00:00:00', 1, 'vnmedia.vn', 1, '2014-11-12 15:02:55', NULL, NULL, 100, 0, 'vnmedia.vn/', 34, 1, 2, NULL),
	(44, 0, '2014-10-24 00:00:00', 1, 'news.zing.vn', 1, '2014-11-12 15:02:55', NULL, NULL, 100, 0, 'www.news.zing.vn', 39, 1, 2, NULL),
	(45, 1, '2014-10-24 00:00:00', 1, 'laodong.com.vn', 1, '2014-11-12 15:02:55', NULL, NULL, 100, 0, 'laodong.com.vn', 35, 1, 2, NULL),
	(59, 1, '2014-10-27 00:00:00', 1, 'facebook.com', 1, '2014-11-12 15:02:56', '2014-11-11 00:00:00', 1, 50, 1, 'www.facebook.com/barackobama', 1, 1, 2, NULL),
	(71, 1, '2014-11-04 00:00:00', 1, 'vnexpress.net', 1, '2014-11-12 15:02:55', NULL, NULL, 70, 0, 'vnexpress.net', 1, 1, 2, NULL),
	(76, 1, '2014-11-10 00:00:00', 1, 'vneconomy.vn', 1, '2014-11-12 15:02:55', NULL, NULL, 70, 0, 'vneconomy.vn/', 35, 1, 2, NULL);
/*!40000 ALTER TABLE `source_url` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(250) NOT NULL,
  `BITHDAY` datetime NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_USER_ID` bigint(20) NOT NULL,
  `DISTRICT` varchar(250) NOT NULL,
  `EMAIL` varchar(250) CHARACTER SET latin1 NOT NULL,
  `FIRST_NAME` varchar(250) NOT NULL,
  `GENDER` int(11) NOT NULL,
  `LAST_NAME` varchar(250) NOT NULL,
  `MIDDLE_NAME` varchar(250) NOT NULL,
  `PASSWORD` varchar(250) CHARACTER SET latin1 NOT NULL,
  `PHONE` varchar(250) CHARACTER SET latin1 NOT NULL,
  `PROVINCE` varchar(250) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `USERNAME` varchar(250) CHARACTER SET latin1 NOT NULL,
  `LAST_MODIFIED` datetime DEFAULT NULL,
  `LAST_MODIFIED_USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_c23jkj330b0khhv3v45lscqkp` (`EMAIL`),
  UNIQUE KEY `UK_j8pqu7twm7ekoe3orxctxr9f4` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- Dumping data for table ttxvndb.user: ~12 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`ID`, `ADDRESS`, `BITHDAY`, `CREATED_DATE`, `CREATED_USER_ID`, `DISTRICT`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `MIDDLE_NAME`, `PASSWORD`, `PHONE`, `PROVINCE`, `STATUS`, `USERNAME`, `LAST_MODIFIED`, `LAST_MODIFIED_USER_ID`) VALUES
	(1, 'HN', '1961-01-01 00:00:00', '2014-01-01 00:00:00', 1, 'Cau Giay', 'vietsoftware@vnanet.vn', 'ha', 1, 'nguyen', 'thanh', 'e10adc3949ba59abbe56e057f20f883e', '0123456789', 'Ha Noi', 1, 'admin', '2014-10-11 00:00:00', 1),
	(2, 'TN', '2000-10-10 00:00:00', '2014-10-10 00:00:00', 1, 'DHY', 'user@vnanet.vn', 'User', 0, 'Mr', 'de', 'e10adc3949ba59abbe56e057f20f883e', '9876543210', 'HN', 1, 'user', NULL, 1),
	(54, 'phuong123', '1988-09-30 00:00:00', '2014-10-14 00:00:00', 1, 'phuong123', 'sun745@gmail.com', 'ly', 1, 'Le', 'thi', '06dc67758e6bd6f8b089aee4a915441e', '12243433', 'phuong123', 1, 'phuong123', '2014-10-15 00:00:00', 1),
	(55, 'thongkebaocao', '1987-10-01 00:00:00', '2014-10-14 00:00:00', 1, 'thongkebaocao', 'adad@dadd.dgfs', 'Hung', 0, 'tran', 'duc', 'e3908c693371a4cf2f5e095fa9bfc88d', '123131312', 'thongkebaocao', 1, 'thongkebaocao', '2014-10-15 00:00:00', 1),
	(56, 'qlnguontin', '1966-09-30 00:00:00', '2014-10-14 00:00:00', 1, 'qlnguontin', 'asadda@dasdad.sss', 'hung', 0, 'Nguyen', 'ba', 'acc683bccde36ab16dc621dec8b8f291', '23131313', 'qlnguontin', 1, 'qlnguontin', '2014-10-15 00:00:00', 1),
	(57, 'asdad', '1999-10-07 00:00:00', '2014-10-15 00:00:00', 1, 'adad', 'qlchude@yahoo.com', 'hanh', 1, 'nguyen', 'thi ', '7f366a4401d9ae807f35961f6af5d9ea', '1234567', 'adda', 1, 'qlchude', NULL, 1),
	(60, 'Hà Nội 1', '1990-07-12 00:00:00', '2014-11-07 00:00:00', 1, 'Hà Nội 2', 'ducvq@gmail.com', 'đức', 0, 'võ', 'quý', 'e10adc3949ba59abbe56e057f20f883e', '0912345678', 'Hà Nội 3', 1, 'anhduc', '2014-11-07 00:00:00', 1),
	(61, 'subString', '2014-11-04 00:00:00', '2014-11-07 00:00:00', 1, 'subString', 'subString@gmail.com', 'subString', 0, 'subString', 'subString', '987c66b5d4815e94e27920c2aeed4efb', '01234567891', 'subString', 1, 'subString', NULL, 1),
	(62, 'sontran', '2014-11-03 00:00:00', '2014-11-07 00:00:00', 1, 'sontran', 'sontran@gmail.com', 'sontran', 0, 'sontran', 'sontran', 'bb801fb402baecf81b7f13229635e710', '01234567991', 'sontran', 1, 'sontran', NULL, 1),
	(63, 'trananh', '2014-11-02 00:00:00', '2014-11-07 00:00:00', 1, 'trananh', 'trananh@gmail.com', 'trananh', 0, 'trananh', 'trananh', 'ec7ab01f22c7291f3d6b10c753dff6e5', '01234567811', 'trananh', 1, 'trananh', NULL, 1),
	(64, 'anhson', '2014-11-02 00:00:00', '2014-11-07 00:00:00', 1, 'anhson', 'anhson@gmial.com', 'anhson', 0, 'anhson', 'anhson', '9f3e0d3fcd4b9c0f6d32509d24c4b430', '0123457855', 'anhson', 1, 'anhson', NULL, 1),
	(65, 'hà nội', '2014-11-04 00:00:00', '2014-11-11 00:00:00', 1, 'hà đông', 'sontrananh@gmail.com', 'sơn', 0, 'trần', 'anh ', 'e10adc3949ba59abbe56e057f20f883e', '0918218691', 'hà nội', 1, 'sontrananh', NULL, 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.user_group
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE IF NOT EXISTS `user_group` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_USER_ID` bigint(20) NOT NULL,
  `GROUP_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_p6k3th5vyj3dsp7frtxieiu0v` (`GROUP_ID`),
  KEY `FK_ikq0kim8mei8t1hh4q0tpfkvi` (`USER_ID`),
  CONSTRAINT `FK_ikq0kim8mei8t1hh4q0tpfkvi` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK_p6k3th5vyj3dsp7frtxieiu0v` FOREIGN KEY (`GROUP_ID`) REFERENCES `groups` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=latin1;

-- Dumping data for table ttxvndb.user_group: ~27 rows (approximately)
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` (`ID`, `CREATED_DATE`, `CREATED_USER_ID`, `GROUP_ID`, `USER_ID`) VALUES
	(2, '2014-10-11 00:00:00', 1, 2, 2),
	(145, '2014-10-14 00:00:00', 1, 31, 55),
	(146, '2014-10-14 00:00:00', 1, 2, 55),
	(148, '2014-10-14 00:00:00', 1, 2, 54),
	(149, '2014-10-14 00:00:00', 1, 2, 56),
	(150, '2014-10-14 00:00:00', 1, 29, 56),
	(152, '2014-10-15 00:00:00', 1, 30, 57),
	(153, '2014-10-15 00:00:00', 1, 32, 2),
	(154, '2014-10-15 00:00:00', 1, 2, 57),
	(155, '2014-11-06 00:00:00', 1, 1, 1),
	(156, '2014-11-06 00:00:00', 1, 2, 1),
	(157, '2014-11-06 00:00:00', 1, 29, 1),
	(158, '2014-11-06 00:00:00', 1, 30, 1),
	(159, '2014-11-06 00:00:00', 1, 31, 1),
	(160, '2014-11-06 00:00:00', 1, 32, 1),
	(241, '2014-11-07 00:00:00', 1, 40, 55),
	(242, '2014-11-07 00:00:00', 1, 40, 56),
	(243, '2014-11-07 00:00:00', 1, 40, 57),
	(244, '2014-11-07 00:00:00', 1, 40, 60),
	(245, '2014-11-07 00:00:00', 1, 40, 61),
	(246, '2014-11-07 00:00:00', 1, 40, 62),
	(247, '2014-11-07 00:00:00', 1, 40, 63),
	(249, '2014-11-07 00:00:00', 1, 41, 55),
	(250, '2014-11-07 00:00:00', 1, 41, 56),
	(252, '2014-11-07 00:00:00', 1, 41, 60),
	(257, '2014-11-07 00:00:00', 1, 29, 64),
	(259, '2014-11-11 00:00:00', 1, 1, 65);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;


-- Dumping structure for table ttxvndb.user_notification
DROP TABLE IF EXISTS `user_notification`;
CREATE TABLE IF NOT EXISTS `user_notification` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `hasNotification` bit(1) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Dumping data for table ttxvndb.user_notification: ~15 rows (approximately)
/*!40000 ALTER TABLE `user_notification` DISABLE KEYS */;
INSERT INTO `user_notification` (`ID`, `hasNotification`, `userId`) VALUES
	(1, b'1', 1),
	(2, b'1', 2),
	(4, b'1', 53),
	(5, b'1', 54),
	(6, b'1', 55),
	(7, b'1', 56),
	(8, b'1', 57),
	(9, b'1', 58),
	(10, b'1', 59),
	(11, b'1', 60),
	(12, b'1', 61),
	(13, b'1', 62),
	(14, b'1', 63),
	(15, b'1', 64),
	(16, b'1', 65);
/*!40000 ALTER TABLE `user_notification` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
