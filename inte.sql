/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.23 : Database - inte
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`inte` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `inte`;

/*Table structure for table `t_perms` */

DROP TABLE IF EXISTS `t_perms`;

CREATE TABLE `t_perms` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_perms` */

insert  into `t_perms`(`id`,`name`,`url`) values 
(1,'user:*',NULL),
(2,'product:*:01',NULL),
(3,'order:*:*',NULL),
(4,'admin:*',NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`roleName`) values 
(1,'admin'),
(2,'user'),
(3,'product');

/*Table structure for table `t_role_perms` */

DROP TABLE IF EXISTS `t_role_perms`;

CREATE TABLE `t_role_perms` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleid` int DEFAULT NULL,
  `permsid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_role_perms` */

insert  into `t_role_perms`(`id`,`roleid`,`permsid`) values 
(1,1,1),
(2,1,2),
(3,2,1),
(4,3,2),
(5,1,3),
(6,1,4);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`salt`) values 
(1,'admin','571ebba33af6a34bbae51861e72ca392','qyWy&uHh'),
(3,'han','38d6ac6cb0618085edc4a60bd505d0dd','%DpnolCJ'),
(4,'root','19554a9427fabd4692830dbb4d372bd1','Jaw8p$99'),
(5,'test01','a5b479f7105efca50922aba7f974052d','UUyS9!Sm'),
(8,'test02','56d87bf00744a8a23accedaf412c774e','xFTaExvb'),
(9,'test03','e4c9d1fb2456eae305cdc1abcf55d0d3','F1aAj4re'),
(16,'test11','449501d02a8b3ef61fe191f91759a427',')fPjclKV');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `roleid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`id`,`userid`,`roleid`) values 
(1,1,1),
(2,2,2),
(3,2,3),
(4,3,2),
(5,4,3),
(6,5,2),
(7,8,2),
(8,9,3),
(9,11,3),
(10,12,2),
(12,16,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
