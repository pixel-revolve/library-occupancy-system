/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.11 : Database - library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `library`;

/*Table structure for table `reservation` */

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
                               `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '预约表id主键',
                               `time` datetime NOT NULL COMMENT '预约时间',
                               `gmt_created` datetime NOT NULL COMMENT '创建时间',
                               `user_id` int(11) NOT NULL COMMENT '预约的人id',
                               `seat_id` int(11) NOT NULL COMMENT '预约的座位',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `reservation` */

insert  into `reservation`(`id`,`time`,`gmt_created`,`user_id`,`seat_id`) values
                                                                              (1,'2021-11-20 00:00:00','2021-11-20 00:00:00',1,1),
                                                                              (2,'2021-11-20 00:00:00','2021-11-20 00:00:00',2,2),
                                                                              (3,'2021-11-20 00:00:00','2021-11-20 00:00:00',3,3),
                                                                              (4,'2021-11-20 00:00:00','2021-11-20 00:00:00',4,4),
                                                                              (5,'2021-11-20 12:30:01','2021-11-20 13:20:05',4,4),
                                                                              (6,'2021-11-23 08:57:19','2021-11-23 08:57:19',1,1),
                                                                              (7,'2021-11-23 08:57:19','2021-11-23 08:57:19',3,3),
                                                                              (8,'2021-11-23 08:57:19','2021-11-23 08:57:19',5,4),
                                                                              (9,'2021-11-23 08:57:19','2021-11-23 08:57:19',3,4),
                                                                              (10,'2021-11-23 09:05:46','2021-11-23 09:05:46',6,2),
                                                                              (11,'2021-11-23 09:05:46','2021-11-23 09:05:46',2,3),
                                                                              (12,'2021-12-07 00:00:20','2021-12-07 00:10:36',3,1),
                                                                              (13,'2021-12-25 08:30:43','2021-12-18 08:29:40',7,3),
                                                                              (14,'2021-12-23 13:59:22','2021-12-02 13:57:18',11,4),
                                                                              (15,'2021-12-11 10:02:58','2021-12-03 11:01:55',7,6);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
                        `id` int(10) NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(20) DEFAULT NULL,
                        `description` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `role` */

insert  into `role`(`id`,`role_name`,`description`) values
                                                        (1,'ROLE_STUDENT','学生'),
                                                        (2,'ROLE_ADMIN','管理员');

/*Table structure for table `seat` */

DROP TABLE IF EXISTS `seat`;

CREATE TABLE `seat` (
                        `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '交流主键',
                        `floor` varchar(50) NOT NULL COMMENT '座位楼层',
                        `is_empty` bit(1) NOT NULL COMMENT '座位是否为空',
                        `seat_id` int(10) NOT NULL COMMENT '座位编号',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `seat` */

insert  into `seat`(`id`,`floor`,`is_empty`,`seat_id`) values
                                                           (1,'三楼','\0',0),
                                                           (2,'四楼','\0',1),
                                                           (3,'五楼','\0',2),
                                                           (4,'六楼','\0',3),
                                                           (5,'一楼','\0',4),
                                                           (6,'一楼','\0',3);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '交流主键',
                        `username` varchar(40) NOT NULL COMMENT '用户名',
                        `mobile` varchar(11) DEFAULT NULL COMMENT '电话号码',
                        `password` varchar(100) NOT NULL COMMENT '密码',
                        `avatar` varchar(100) DEFAULT NULL COMMENT '头像链接地址',
                        `isAtLibrary` tinyint(1) DEFAULT NULL COMMENT '预约者是否在图书馆',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`mobile`,`password`,`avatar`,`isAtLibrary`) values
                                                                                    (1,'dyh','13770808113','$2a$10$DQ6CaJqSb6qs/fY5Srd9JOcSDlwWYFBrASebEyagRxsXA60Zc2fH.','/avatar/user/20211207220126617.png',0),
                                                                                    (2,'chy','13770808114','$2a$10$DQ6CaJqSb6qs/fY5Srd9JOcSDlwWYFBrASebEyagRxsXA60Zc2fH.','default',0),
                                                                                    (3,'shr','13770808116','$2a$10$DQ6CaJqSb6qs/fY5Srd9JOcSDlwWYFBrASebEyagRxsXA60Zc2fH.','default',0),
                                                                                    (4,'lzw','13770808116','$2a$10$DQ6CaJqSb6qs/fY5Srd9JOcSDlwWYFBrASebEyagRxsXA60Zc2fH.','default',0),
                                                                                    (5,'张林','12345678901','$2a$10$DQ6CaJqSb6qs/fY5Srd9JOcSDlwWYFBrASebEyagRxsXA60Zc2fH.','/avatar/user/20211206213150991.png',0),
                                                                                    (6,'钱亚东','13770808113','$2a$10$DQ6CaJqSb6qs/fY5Srd9JOcSDlwWYFBrASebEyagRxsXA60Zc2fH.','default',0),
                                                                                    (7,'杜宇豪','13770801022','$2a$10$DQ6CaJqSb6qs/fY5Srd9JOcSDlwWYFBrASebEyagRxsXA60Zc2fH.','/avatar/user/20211208194419549.png',0),
                                                                                    (8,'陈浩宇','13141551514','$2a$10$DQ6CaJqSb6qs/fY5Srd9JOcSDlwWYFBrASebEyagRxsXA60Zc2fH.','default',0),
                                                                                    (9,'孙浩然',NULL,'$2a$10$F8VxpPJ5RonCbHpQoqtS0u/2k34.CU4cnJx.DDxl7sapbL51vZdbq','/avatar/user/20211207214613134.gif',0),
                                                                                    (11,'穿山甲',NULL,'$2a$10$v6fqgYzAT5qyDJzREmbeIOsYBevolO8rUQVHWeoeQnE9M0a0udqku','/avatar/user/20211207215754047.png',0);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `user_id` int(11) NOT NULL,
                             `role_id` int(11) NOT NULL,
                             PRIMARY KEY (`id`),
                             KEY `user_id` (`user_id`),
                             KEY `role_id` (`role_id`),
                             CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                             CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values
                                                       (1,1,2),
                                                       (2,2,1),
                                                       (3,3,1),
                                                       (4,4,1),
                                                       (5,5,1),
                                                       (6,6,1),
                                                       (7,7,1),
                                                       (8,8,1),
                                                       (9,9,1),
                                                       (11,11,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;