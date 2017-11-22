-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: acesse
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` varchar(255) NOT NULL,
  `label` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES ('AAAAAAA','Bar/Restaurante'),('BBBBBBB','Cinema'),('CCCCCCC','Parque'),('DDDDDDD','Instituição'),('EEEEEEE','Hipermecado'),('FFFFFFF','Loja');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluations`
--

DROP TABLE IF EXISTS `evaluations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluations` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `local_id` varchar(255) NOT NULL,
  `comment` varchar(140) DEFAULT NULL,
  `wheelchair_access_value` varchar(255) NOT NULL,
  `wheelchair_wc_value` varchar(255) NOT NULL,
  `braile_value` varchar(255) NOT NULL,
  `tatil_floor_value` varchar(255) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `evaluation_users_fk` (`user_id`),
  KEY `evaluation_locals_fk` (`local_id`),
  CONSTRAINT `evaluation_locals_fk` FOREIGN KEY (`local_id`) REFERENCES `locals` (`id`) ON DELETE CASCADE,
  CONSTRAINT `evaluation_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluations`
--

LOCK TABLES `evaluations` WRITE;
/*!40000 ALTER TABLE `evaluations` DISABLE KEYS */;
INSERT INTO `evaluations` VALUES ('B6YT7YPY','VDK22V9M','TE5C52O5','','5','4','2','4','2017-11-22 03:10:23'),('JU0X6JE5','60FOMMYI','DAL7IAX2','','5','3','1','4','2017-11-22 04:47:50'),('K1NNI7YY','60FOMMYI','TE5C52O5','Mauris nec dolor in eros commodo tempor. Aenean aliquam molestie leo, vitae iaculis nisl.','5','5','4','3','2017-11-22 02:06:00'),('ZIFT6JDR','VDK22V9M','DAL7IAX2','','3','3','3','3','2017-11-22 04:47:15');
/*!40000 ALTER TABLE `evaluations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locals`
--

DROP TABLE IF EXISTS `locals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locals` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `neighbourhood` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `lat` varchar(255) NOT NULL,
  `lng` varchar(255) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `category_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `local_users_fk` (`user_id`),
  KEY `locals_categories_fk` (`category_id`),
  CONSTRAINT `local_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `locals_categories_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locals`
--

LOCK TABLES `locals` WRITE;
/*!40000 ALTER TABLE `locals` DISABLE KEYS */;
INSERT INTO `locals` VALUES ('AW0SFSGN','60FOMMYI','Home','Rua Antônio Maciel de Oliveira','my home','06757-030','SP','Taboão da Serra','Cidade Intercap','123','-23.6086469','-46.7784076','2017-11-15 17:24:19',NULL),('DAL7IAX2','VDK22V9M','Carrefour - Villa Lobos','Avenida José César de Oliveira','Tenho um BK lá, ruim de mais.','05317000','SP','São Paulo','Vila Leopoldina','123','-23.540429','-46.7326036','2017-11-22 04:46:57','EEEEEEE'),('TE5C52O5','60FOMMYI','Pq. Ibirapuera','Rua Otávio Nébias','O Parque Ibirapuera é o mais importante parque urbano da cidade de São Paulo, no Brasil. Foi inaugurado em 21 de agosto de 1954 para a comemoração do quarto centenário da cidade','04002-010','SP','São Paulo','Paraíso','000','-23.5720177','-46.6516528','2017-11-22 04:04:38','CCCCCCC'),('X9G58NBE','60FOMMYI','Home Mari','Avenida Edmundo Amaral','lar doce lar','06230150','SP','Osasco','Piratininga','123','-23.5149899','-46.8036084','2017-11-21 02:08:17',NULL);
/*!40000 ALTER TABLE `locals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('3A25G39Y','Mari','mari@teste.com','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2017-11-09 00:27:32'),('60FOMMYI','Gabriel','gabriel@acesse.com','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2017-11-08 18:28:14'),('VDK22V9M','Mari','mari@acesse.com','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2017-11-08 19:53:37');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-22  2:50:16
