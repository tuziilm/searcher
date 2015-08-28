/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.6.10 : Database - searcher
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`searcher` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `searcher`;

/*Table structure for table `app` */

DROP TABLE IF EXISTS `app`;

CREATE TABLE `app` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `link` varchar(100) NOT NULL COMMENT '链接地址',
  `img_path` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `img_file_name` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `type` tinyint(2) NOT NULL COMMENT '类型 1搜索引擎 2有图app 3无图App',
  `status` tinyint(2) NOT NULL COMMENT '0正常 1删除',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Table structure for table `app_pack` */

DROP TABLE IF EXISTS `app_pack`;

CREATE TABLE `app_pack` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) NOT NULL COMMENT '类别名称',
  `pack_type` tinyint(2) NOT NULL COMMENT '套餐类型 1搜索引擎2有图App3无图App',
  `app_ids` varchar(1000) DEFAULT NULL COMMENT 'app',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `operation_log` */

DROP TABLE IF EXISTS `operation_log`;

CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `type` int(11) NOT NULL COMMENT '操作类型',
  `msg` text NOT NULL COMMENT '操作信息',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `username` varchar(60) NOT NULL,
  `passwd` varchar(96) NOT NULL,
  `sys_user_type` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `privilege` varchar(300) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUENAME` (`username`),
  UNIQUE KEY `UNIQUEEMAIL` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
