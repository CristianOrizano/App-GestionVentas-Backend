CREATE DATABASE  IF NOT EXISTS `gestionventas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestionventas`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: gestionventas
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `tb_boleta`
--

DROP TABLE IF EXISTS `tb_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_boleta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha_emision` date DEFAULT NULL,
  `id_cliente` bigint DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  `total` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfv4utqxuu0017lcvdckxfng9o` (`id_cliente`),
  KEY `FKck9nxiwf5e2os2svcv0qdqj7j` (`id_usuario`),
  CONSTRAINT `FKck9nxiwf5e2os2svcv0qdqj7j` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`),
  CONSTRAINT `FKfv4utqxuu0017lcvdckxfng9o` FOREIGN KEY (`id_cliente`) REFERENCES `tb_cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_boleta`
--

LOCK TABLES `tb_boleta` WRITE;
/*!40000 ALTER TABLE `tb_boleta` DISABLE KEYS */;
INSERT INTO `tb_boleta` VALUES (1,'2024-12-20',1,1,1000.00),(26,'2024-12-05',2,1,700.00),(28,'2024-12-05',6,1,700.00);
/*!40000 ALTER TABLE `tb_boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_categoria`
--

DROP TABLE IF EXISTS `tb_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_categoria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categoria`
--

LOCK TABLES `tb_categoria` WRITE;
/*!40000 ALTER TABLE `tb_categoria` DISABLE KEYS */;
INSERT INTO `tb_categoria` VALUES (1,'Esta categoría incluye teléfonos inteligentes tableta','Refrigeracion',_binary ''),(2,'Esta categoría incluye teléfonos inteligentes tabletas','Multimedia',_binary ''),(3,'productos todo celulares de alta gama','Celulares y Smartphones',_binary ''),(4,'Productos para la oficina','Laptops',_binary ''),(5,'Productos para estudiar','Tablets',_binary ''),(6,'Productos de entretenimiento','Consolas',_binary ''),(7,'Productos para pc','Teclados',_binary '');
/*!40000 ALTER TABLE `tb_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ciudad`
--

DROP TABLE IF EXISTS `tb_ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_ciudad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ciudad`
--

LOCK TABLES `tb_ciudad` WRITE;
/*!40000 ALTER TABLE `tb_ciudad` DISABLE KEYS */;
INSERT INTO `tb_ciudad` VALUES (1,'Lima'),(2,'Arequipa'),(3,'Trujillo'),(4,'Pisco'),(6,'Cusco'),(7,'ch');
/*!40000 ALTER TABLE `tb_ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `ndocumento` int DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES (1,'Emma','Rous','lima',78945612,987456121,_binary ''),(2,'cristian','orizano','lima',78945612,987456121,_binary ''),(3,'olivia','rodrigo','lima',78945612,987456121,_binary ''),(4,'fgh','taylor','Av larco',78945612,987456121,_binary '\0'),(5,'taylor','swift','times sq',78945612,987456121,_binary '\0'),(6,'alex','laruso rus','lima',78945678,987456121,_binary '');
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_detalle_boleta`
--

DROP TABLE IF EXISTS `tb_detalle_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_detalle_boleta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_boleta` bigint NOT NULL,
  `id_producto` bigint NOT NULL,
  `cantidad` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3x1vxy897wop8j0rya71xfrs3` (`id_boleta`,`id_producto`),
  KEY `FKro5u6h28w0mjvvkq26c3c5av9` (`id_producto`),
  CONSTRAINT `FK28ta0uy5rm7vklq1vk76ddxy8` FOREIGN KEY (`id_boleta`) REFERENCES `tb_boleta` (`id`),
  CONSTRAINT `FKro5u6h28w0mjvvkq26c3c5av9` FOREIGN KEY (`id_producto`) REFERENCES `tb_producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_detalle_boleta`
--

LOCK TABLES `tb_detalle_boleta` WRITE;
/*!40000 ALTER TABLE `tb_detalle_boleta` DISABLE KEYS */;
INSERT INTO `tb_detalle_boleta` VALUES (1,1,2,4),(2,1,3,2),(31,26,2,5),(32,26,6,2),(35,28,2,5),(36,28,7,2);
/*!40000 ALTER TABLE `tb_detalle_boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_empleado`
--

DROP TABLE IF EXISTS `tb_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_empleado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fechanacimiento` date DEFAULT NULL,
  `id_ciudad` bigint DEFAULT NULL,
  `sueldo` double NOT NULL,
  `telefono` int NOT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrm8tvisbpkms4fmhkfb7m6j3u` (`id_ciudad`),
  CONSTRAINT `FKrm8tvisbpkms4fmhkfb7m6j3u` FOREIGN KEY (`id_ciudad`) REFERENCES `tb_ciudad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_empleado`
--

LOCK TABLES `tb_empleado` WRITE;
/*!40000 ALTER TABLE `tb_empleado` DISABLE KEYS */;
INSERT INTO `tb_empleado` VALUES (1,'cristian','orizano','Av. lima','2001-09-10',1,1800,987456123,_binary ''),(2,'cristian','Pérez','Av. Siempre Viva 123','1995-08-14',1,2500.5,987654321,_binary ''),(3,'cristian','Pérez','Av. Siempre Viva 123','2000-08-14',1,2500.5,987654321,_binary ''),(4,'Lucas','Pérez','Av. Siempre Viva 123','2000-08-14',1,2500.5,987654321,_binary '\0'),(5,'cristi','orizano','Av. lima','2024-11-29',1,500,0,_binary ''),(6,'cristi','orizano','Av. lima','2024-11-29',1,500,999999999,_binary ''),(7,'Kin','Salaz','Av. lima','2024-11-29',1,500,999999999,_binary ''),(8,'nini','salas','Av. lima','2024-11-29',6,500,999999999,_binary ''),(9,'emma','salas','Av. lima','2024-11-29',7,500,999999999,_binary ''),(11,'Asaf','luther','Av. lima','2024-11-29',3,500,999999999,_binary '');
/*!40000 ALTER TABLE `tb_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_producto`
--

DROP TABLE IF EXISTS `tb_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `id_categoria` bigint DEFAULT NULL,
  `nimagen` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7tyog6khc1m2yuqvcswna17fd` (`id_categoria`),
  CONSTRAINT `FK7tyog6khc1m2yuqvcswna17fd` FOREIGN KEY (`id_categoria`) REFERENCES `tb_categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_producto`
--

LOCK TABLES `tb_producto` WRITE;
/*!40000 ALTER TABLE `tb_producto` DISABLE KEYS */;
INSERT INTO `tb_producto` VALUES (1,'CONGELADORA COLDEX 247 LTS CH08','Coldex',1,'2refri.jpg',1000,20,_binary ''),(2,'ASPIRADORA 2000W 25L ELECTRICA','Forte',1,'10001.PNG',2000,20,_binary ''),(3,'ASPIRADORA ELECTROLUX SON10 1400W','Forte',2,'2007.jpg',2000,20,_binary ''),(4,'IPHONE 13 6.1 - AZUL MEDIANOCHE','Apple',3,'2065287752910_2.jpg',2660,50,_binary ''),(5,'SMARTPHONE SAMSUNG GALAXY S24 ULTRA 512G','Samsung',3,'2065329700244_2.jpg',3999,50,_binary ''),(6,'APPLE IPHONE 12 AZUL 64 GB REACONDICIONADO','Apple',3,'full_image-1.jpg',1455,50,_binary ''),(7,'APPLE IPHONE 14 6.1 128G+12MP MEDIANOCHE','Apple',3,'2065300885090_2.jpg',1966,50,_binary ''),(8,'LAPTOP LENOVO IDEAPAD 1 AMD RYZEN 7 5700U 16GB 1TB SSD','Lenovo',4,'2004326027909_2.jpg',1860,50,_binary ''),(9,'LAPTOP ASUS TUF GAMING F15 FX507ZC4','Asus',4,'2004317213762_2.jpg',2060,50,_binary ''),(10,'LAPTOP ACER INTEL CORE I3 8GB GB RAM','Acer',4,'2004330098650.jpg',2659,50,_binary ''),(11,'TABLET SAMSUNG TAB S6 LITE 2024 10.4\" 128GB','Sansung',5,'2004334581943.jpg',1600,50,_binary ''),(12,'APPLE IPAD AIR (5TA GENERACIÓN) WI-FI 64GB MORADO','Apple',5,'2004319125704.jpg',1999,50,_binary ''),(13,'CONSOLA SONY PLAY STATION 5 HW STANDARD SPIDERMAN 2','Sony',6,'2031324246483.jpg',2100,50,_binary ''),(14,'PLAY STATION 4 (PS4) 1TB GOW RAGNAROK','Sony',6,'2031304917457.jpg',1900,50,_binary ''),(15,'CONSOLA NINTENDO SWITCH MODELO OLED','Sony',6,'prod-usemirakl.jpg',2050,50,_binary ''),(16,'COMBO DE TECLADO Y MOUSE CHONCHOW PARA JUEGOS MECÁNICOS','Asus',7,'mediprousmir.jpg',1000,50,_binary ''),(17,'TELEVISOR LG NANOCELL 4K 55\" SMART TV CON THINQ','LG',2,'201831124336.jpg',1600,50,_binary ''),(18,'AUDIFONOSINDY EVO XT SKULLCANDY','Evo',2,'206525477083.jpg',1200,50,_binary '');
/*!40000 ALTER TABLE `tb_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (1,'Admin'),(2,'User');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `nimagen` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'cristian','orizano','nombre.jpg','CristianOrizano','$2a$10$0gBxewHfhSu9R4YF/B8/M.yGIibs9CiBeTgA3nOikdeKNq18OoOFC',_binary ''),(2,'nini','salaz','img.png','NiniSalaz','$2a$10$0gBxewHfhSu9R4YF/B8/M.yGIibs9CiBeTgA3nOikdeKNq18OoOFC',_binary ''),(3,'Juan','Pérez','juan-perez.jpg','juanperez123','$2a$10$O8F8MFFmCMjOmNi7HXMtAu1W3sW5Irq1OfihHwk6T/jiFXcPmHXta',_binary '');
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario_role`
--

DROP TABLE IF EXISTS `tb_usuario_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario_role` (
  `id_usuario` bigint NOT NULL,
  `id_rol` bigint NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_rol`),
  KEY `FK73j06sttnss8wfpbi3nneqecq` (`id_rol`),
  CONSTRAINT `FK73j06sttnss8wfpbi3nneqecq` FOREIGN KEY (`id_rol`) REFERENCES `tb_role` (`id`),
  CONSTRAINT `FKq88807wwkx7h5neicih51cq70` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario_role`
--

LOCK TABLES `tb_usuario_role` WRITE;
/*!40000 ALTER TABLE `tb_usuario_role` DISABLE KEYS */;
INSERT INTO `tb_usuario_role` VALUES (1,1),(2,1),(1,2),(3,2);
/*!40000 ALTER TABLE `tb_usuario_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gestionventas'
--

--
-- Dumping routines for database 'gestionventas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-06 18:09:48
