CREATE DATABASE  IF NOT EXISTS `gpedidos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gpedidos`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gpedidos
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
-- Table structure for table `comida`
--

DROP TABLE IF EXISTS `comida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comida` (
  `codigo` int NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `fecha_caducidad` date DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `perecedero` tinyint(1) DEFAULT NULL,
  `calorias` float DEFAULT NULL,
  `vegano` float DEFAULT NULL,
  `fecha_envase` date DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comida`
--

LOCK TABLES `comida` WRITE;
/*!40000 ALTER TABLE `comida` DISABLE KEYS */;
INSERT INTO `comida` VALUES (3224,'Ensalada',4.95,'2023-06-03','Buen estado',1,300,0,'2023-05-24'),(3225,'Ensaladilla',2.45,'2023-06-03','Buen estado',1,400,0,'2023-05-24'),(4148,'Huevos rotos',4.55,'2023-06-03','Buen estado',1,500,1,'2023-05-24'),(6873,'Chuletas',15.95,'2023-06-03','Buen estado',1,500,1,'2023-05-24'),(8312,'Gambas',12.95,'2023-06-03','Buen estado',1,300,1,'2023-05-24'),(8438,'Hamburguesa',8.85,'2023-06-03','Buen estado',1,600,1,'2023-05-24'),(8474,'Pat. bravas',3.95,'2023-06-03','Buen estado',1,400,0,'2023-05-24'),(9122,'Pizza',7.95,'2023-06-03','Buen estado',1,600,1,'2023-05-24'),(9279,'Pulpo',9.99,'2023-06-03','Buen estado',1,300,1,'2023-05-24'),(9477,'Pat. fritas',2.45,'2023-06-03','Buen estado',1,300,0,'2023-05-24');
/*!40000 ALTER TABLE `comida` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-30 21:29:41
