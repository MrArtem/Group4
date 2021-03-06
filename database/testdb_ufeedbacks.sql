-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ufeedbacks`
--

DROP TABLE IF EXISTS `ufeedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ufeedbacks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assessment` int(11) NOT NULL,
  `attendance` bit(1) NOT NULL,
  `attitude` bit(1) NOT NULL,
  `comm_skills` bit(1) NOT NULL,
  `date` datetime DEFAULT NULL,
  `focus_on_result` bit(1) NOT NULL,
  `level` int(11) NOT NULL,
  `motivation` bit(1) NOT NULL,
  `other` varchar(255) DEFAULT NULL,
  `questions` bit(1) NOT NULL,
  `feedbacker` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `news` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gccxtvxdi2s1gm618neuqkrct` (`feedbacker`),
  KEY `FK_gro8es0fu6pnrwbbcqo245naa` (`user`),
  KEY `FK_7mgcpdn0wdypwxgdmo1xc6v2c` (`news`),
  CONSTRAINT `FK_7mgcpdn0wdypwxgdmo1xc6v2c` FOREIGN KEY (`news`) REFERENCES `news` (`id`),
  CONSTRAINT `FK_gccxtvxdi2s1gm618neuqkrct` FOREIGN KEY (`feedbacker`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_gro8es0fu6pnrwbbcqo245naa` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ufeedbacks`
--

LOCK TABLES `ufeedbacks` WRITE;
/*!40000 ALTER TABLE `ufeedbacks` DISABLE KEYS */;
/*!40000 ALTER TABLE `ufeedbacks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-03 11:03:28
