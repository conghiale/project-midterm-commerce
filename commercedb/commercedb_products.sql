-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: commercedb
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Red','Product description 1','image1.jpg','Product name 1',10,100,1,1),(2,'Blue','Product description 2','image1.jpg','Product name 2',20,200,2,2),(3,'Green','Product description 3','image1.jpg','Product name 3',30,300,3,3),(4,'Yellow','Product description 4','image1.jpg','Product name 4',40,400,4,4),(5,'Black','Product description 5','image1.jpg','Product name 5',50,500,5,5),(6,'White','Product description 6','image1.jpg','Product name 6',60,600,1,6),(7,'Orange','Product description 7','image1.jpg','Product name 7',70,700,2,7),(8,'Purple','Product description 8','image1.jpg','Product name 8',80,800,3,8),(9,'Gray','Product description 9','image1.jpg','Product name 9',90,900,4,9),(10,'Brown','Product description 10','image1.jpg','Product name 10',100,1000,5,10),(11,'Red','Product description 11','image1.jpg','Product name 11',110,1100,1,11),(12,'Blue','Product description 12','image1.jpg','Product name 12',120,1200,2,12),(13,'Green','Product description 13','image1.jpg','Product name 13',130,1300,3,13),(14,'Yellow','Product description 14','image1.jpg','Product name 14',140,1400,4,14),(15,'Black','Product description 15','image1.jpg','Product name 15',150,1500,5,15);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
