CREATE DATABASE  IF NOT EXISTS `electrodash` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `electrodash`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: electrodash
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
  `fecha_emision` datetime DEFAULT NULL,
  `id_cliente` bigint DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  `tipo_venta` varchar(255) DEFAULT NULL,
  `total` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfv4utqxuu0017lcvdckxfng9o` (`id_cliente`),
  KEY `FKck9nxiwf5e2os2svcv0qdqj7j` (`id_usuario`),
  CONSTRAINT `FKck9nxiwf5e2os2svcv0qdqj7j` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`),
  CONSTRAINT `FKfv4utqxuu0017lcvdckxfng9o` FOREIGN KEY (`id_cliente`) REFERENCES `tb_cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_boleta`
--

LOCK TABLES `tb_boleta` WRITE;
/*!40000 ALTER TABLE `tb_boleta` DISABLE KEYS */;
INSERT INTO `tb_boleta` VALUES (1,'2025-01-24 13:33:57',1,1,'Contado',1000.00),(26,'2025-01-05 13:33:57',2,2,'Contado',700.00),(28,'2025-01-05 13:33:57',6,2,'Credito',700.00),(29,'2025-01-18 13:33:57',3,3,'Credito',4991.00),(30,'2025-01-18 00:00:00',3,4,'Contado',3075.00),(38,'2025-01-18 13:33:57',3,6,'Contado',4991.00),(43,'2025-01-19 13:33:57',3,1,'Credito',1845.00),(44,'2025-01-20 00:00:00',2,2,'Contado',1845.00),(45,'2025-01-20 16:33:57',5,7,'Credito',1230.00),(46,'2025-01-20 15:33:57',1,8,'Credito',13182.00),(47,'2025-01-20 13:33:57',1,1,'Credito',11982.00),(48,'2025-01-20 00:00:00',2,1,'Contado',4800.00),(49,'2025-01-20 16:33:57',2,2,'Contado',6000.00),(50,'2025-02-20 16:33:57',1,2,'Contado',4000.00),(51,'2025-02-20 16:33:57',4,3,'Credito',6000.00),(52,'2025-02-20 00:00:00',3,4,'Credito',7182.00),(53,'2025-02-16 15:33:57',1,4,'Credito',6000.00),(54,'2025-02-15 14:33:57',4,5,'Credito',6000.00),(55,'2025-02-19 15:33:57',3,1,'Credito',7998.00),(56,'2025-02-20 16:33:57',2,6,'Credito',4000.00),(57,'2025-02-20 16:33:57',5,7,'Credito',13896.00),(58,'2025-02-20 16:33:57',5,8,'Credito',20253.33),(59,'2025-02-23 16:33:57',2,10,'Credito',12390.00),(60,'2025-02-23 16:33:57',5,1,'Credito',15465.33),(61,'2025-02-24 16:33:57',1,1,'Credito',2880.00),(68,'2025-03-02 13:10:19',1,1,'Contado',4630.50),(69,'2025-03-02 13:10:56',4,1,'Credito',4510.00),(70,'2025-03-02 13:14:01',1,1,'Credito',5633.20),(71,'2025-03-02 13:14:41',5,1,'Credito',4050.00),(72,'2025-03-03 11:27:23',8,1,'Credito',2394.00),(73,'2025-03-03 11:29:20',3,1,'Credito',820.00),(74,'2025-03-03 13:18:08',3,1,'Credito',2819.00),(75,'2025-03-05 16:54:54',9,1,'Contado',7181.20),(76,'2025-03-06 13:25:40',12,2,'Contado',3941.20);
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
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nimagen` varchar(255) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categoria`
--

LOCK TABLES `tb_categoria` WRITE;
/*!40000 ALTER TABLE `tb_categoria` DISABLE KEYS */;
INSERT INTO `tb_categoria` VALUES (1,'Refrigeracion','Esta categoría incluye teléfonos inteligentes tableta','a8dd2afc-99de-48d2-b733-e7e2a1fc52a7',_binary ''),(2,'Multimedia','Esta categoría incluye teléfonos inteligentes tabletas','9faa7ae9-ed40-43bf-b291-1b12af1ff310',_binary ''),(3,'Celulares y Smartphones','productos todo celulares de alta gama','d3e0b2b8-8dee-45c3-9696-c2114ec56ced',_binary ''),(4,'Laptops','Productos para la oficina','d9585ed5-1858-47ce-be2a-d89fccb665a2',_binary ''),(5,'Tablets','Productos para estudiar','dc27d174-a8f4-4638-b42d-c5df687a33c3',_binary ''),(6,'Consolas','Productos de entretenimiento','90eba94e-c85b-473f-9326-60d16fbf6bdf',_binary ''),(7,'Teclados','Productos para pc','12761873-062d-4594-8c22-186dc9ff7931',_binary ''),(56,'Accesorios Electrónicos ','Incluye audífonos, cargadores, fundas, cables, power banks, etc.','8847a1bc-29d7-42c0-a3cc-2d3b05d8dc51',_binary ''),(63,'Monitores y Periféricos','Monitores, ratones, impresoras, webcams, parlantes, etc.','93235938-105e-455b-843c-c137720cb524',_binary ''),(64,'Componentes de PC ','Tarjetas gráficas, procesadores, discos duros, SSD, fuentes de poder, etc.',NULL,_binary '');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES (1,'Emmaa','Rous','Jr. de la Unión 567, Cercado de Lima',78945612,987456121,_binary ''),(2,'Cristian','Orizano Huyhua','Calle Los Pinos 789, Surco',78945612,987456121,_binary ''),(3,'Maria','Gomez Lopez','Av. Javier Prado Este 246, San Isidro lima av',78945612,987456121,_binary ''),(4,'Andrea','Alarcon Rojas','Calle Las Orquídeas 101, La Molina',78945612,987456121,_binary '\0'),(5,'Taylor','Dias Fernandes','Av. La Marina 3456, Pueblo Libre',78945612,987456121,_binary '\0'),(6,'Sofia','Hernandez Garcia','Jr. Amazonas 789, Miraflores',78945678,987456121,_binary ''),(7,'Pedro','Rosales Sanchez','Calle Los Girasoles 234, San Borja',78965421,987456321,_binary ''),(8,'Carlos','Espinoza Lopez','Av. Brasil 456, Breña',789123456,921519949,_binary ''),(9,'Luis','Alberto Salazar','Avenida 890, lima',74123568,987456123,_binary ''),(10,'Maria','Pascal Alvarez','Calle Los Jazmines 678, Barranco',789456123,987456123,_binary ''),(11,'Alfredo','Velasco Robles','Av. los posts 123. Lima',78945612,987456123,_binary ''),(12,'Juan','Garcia Alcantara','Av aviacion 123, San Miguel',789654231,987652341,_binary '');
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
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_detalle_boleta`
--

LOCK TABLES `tb_detalle_boleta` WRITE;
/*!40000 ALTER TABLE `tb_detalle_boleta` DISABLE KEYS */;
INSERT INTO `tb_detalle_boleta` VALUES (1,1,2,4),(2,1,3,2),(31,26,2,5),(32,26,6,2),(35,28,2,5),(36,28,7,2),(37,29,1,1),(38,29,2,1),(39,29,7,1),(40,30,1,3),(41,43,1,3),(42,44,1,3),(43,45,1,2),(44,46,3,3),(45,46,4,3),(46,47,2,3),(47,47,4,3),(48,48,2,3),(49,49,3,3),(50,50,3,2),(51,51,3,3),(52,52,4,3),(53,53,3,3),(54,54,3,3),(55,55,5,2),(56,56,3,2),(57,57,5,2),(58,57,7,3),(59,59,5,3),(60,58,4,4),(61,59,8,3),(62,59,15,2),(63,59,13,1),(64,60,1,1),(65,60,4,2),(66,60,5,3),(67,61,1,1),(68,61,9,1),(81,68,1,2),(82,68,6,1),(83,68,15,1),(84,69,2,1),(85,69,6,2),(86,70,5,1),(87,70,18,1),(88,70,21,1),(89,71,3,1),(90,71,15,1),(91,72,4,1),(92,73,1,1),(93,74,12,1),(94,74,1,1),(95,75,5,1),(96,75,1,1),(97,75,3,2),(98,76,1,1),(99,76,5,1);
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
-- Table structure for table `tb_marca`
--

DROP TABLE IF EXISTS `tb_marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_marca` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `nimagen` varchar(250) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_marca`
--

LOCK TABLES `tb_marca` WRITE;
/*!40000 ALTER TABLE `tb_marca` DISABLE KEYS */;
INSERT INTO `tb_marca` VALUES (2,'Samsung','51e9f8dc-d3da-4b4f-ae4c-90984a8ccd53',_binary ''),(3,'Apple','a1deb841-d9e2-4b66-a7fb-ba9c51ed44d5',_binary ''),(4,'Lenovo','a625938a-4d89-48e1-8fe8-e589fd51514c',_binary ''),(5,'HP','eb18fe74-fd3b-49a3-ad35-38b506303c90',_binary ''),(6,'Asus','9d602856-1941-4178-a134-dfcc3d2fcfad',_binary ''),(7,'Acer','db1ef37e-8b02-4702-be62-c03d3b2ffbf6',_binary ''),(8,'Huawei','9cbb133a-bdb5-4c71-ab72-864fc9aefaf2',_binary ''),(9,'Sony','dcdb8d7f-b64f-4b4c-a6e8-75c01797275d',_binary ''),(10,'Dell','313d18a5-afcd-467c-8323-0b159d01e6d0',_binary ''),(11,'Nintendo','ded05a4e-6809-44d0-8ca1-4a4db92e3dee',_binary '');
/*!40000 ALTER TABLE `tb_marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_notificacion`
--

DROP TABLE IF EXISTS `tb_notificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_notificacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mensaje` varchar(255) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `state` bit(1) DEFAULT NULL,
  `idProducto` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `_idx` (`idProducto`),
  CONSTRAINT `fk_producto_id` FOREIGN KEY (`idProducto`) REFERENCES `tb_producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_notificacion`
--

LOCK TABLES `tb_notificacion` WRITE;
/*!40000 ALTER TABLE `tb_notificacion` DISABLE KEYS */;
INSERT INTO `tb_notificacion` VALUES (2,'ASPIRADORA ELECTROLUX SON10 1400W, poco stock','2025-02-20 16:45:00',_binary '\0',1),(5,'ASPIRADORA ELECTROLUX SON10 1400W, poco stock','2025-02-20 22:14:26',_binary '\0',3),(6,'SMARTPHONE SAMSUNG GALAXY S24 ULTRA 512G, poco stock','2025-02-20 22:50:47',_binary '\0',5),(7,'ASPIRADORA ELECTROLUX SON10 1400W, poco stock','2025-02-20 22:51:46',_binary '\0',3),(8,'SMARTPHONE SAMSUNG GALAXY S24 ULTRA 512G, poco stock','2025-02-20 23:04:55',_binary '\0',5),(9,'SMARTPHONE SAMSUNG GALAXY S24 ULTRA 512G, poco stock','2025-02-20 23:09:07',_binary '\0',5),(10,'IPHONE 13 6.1 - AZUL MEDIANOCHE, poco stock','2025-02-20 23:09:07',_binary '\0',4),(11,'LAPTOP LENOVO IDEAPAD 1 AMD RYZEN 7 5700U 16GB 1TB SSD, poco stock','2025-02-23 19:13:50',_binary '\0',8),(12,'IPHONE 13 6.1 - AZUL MEDIANOCHE, poco stock','2025-02-23 19:18:24',_binary '\0',4),(13,'SMARTPHONE SAMSUNG GALAXY S24 ULTRA 512G, poco stock','2025-02-23 19:18:24',_binary '\0',5),(14,'SMARTPHONE SAMSUNG GALAXY S24 ULTRA 512G, poco stock','2025-02-24 21:36:37',_binary '\0',5),(15,'IPHONE 13 6.1 - AZUL MEDIANOCHE, poco stock','2025-03-01 20:42:00',_binary '\0',4),(16,'SONY EQUIPO DE SONIDO BLUETOOTH, HDMI, DVD Y KARAOKE MHC-V43D, poco stock','2025-03-01 20:44:04',_binary '\0',2),(17,'SMARTPHONE SAMSUNG GALAXY S24 ULTRA 512G, poco stock','2025-03-01 20:44:23',_binary '\0',5),(18,'CONGELADORA COLDEX 247 LTS CH08, poco stock','2025-03-02 18:10:19',_binary '\0',1),(19,'SONY EQUIPO DE SONIDO BLUETOOTH, HDMI, DVD Y KARAOKE MHC-V43D, poco stock','2025-03-02 18:10:56',_binary '\0',2),(20,'SMARTPHONE SAMSUNG GALAXY S24 ULTRA 512G, poco stock','2025-03-02 18:14:01',_binary '\0',5),(21,'CONGELADORA COLDEX 247 LTS CH08, poco stock','2025-03-05 21:54:54',_binary '\0',1),(22,'CONGELADORA COLDEX 247 LTS CH08, poco stock','2025-03-06 18:25:40',_binary '\0',1);
/*!40000 ALTER TABLE `tb_notificacion` ENABLE KEYS */;
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
  `id_marca` bigint DEFAULT NULL,
  `nimagen` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `descuento` int DEFAULT '0',
  `stock` int DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7tyog6khc1m2yuqvcswna17fd` (`id_categoria`),
  KEY `fk_marca_idx` (`id_marca`),
  CONSTRAINT `FK7tyog6khc1m2yuqvcswna17fd` FOREIGN KEY (`id_categoria`) REFERENCES `tb_categoria` (`id`),
  CONSTRAINT `fk_marca` FOREIGN KEY (`id_marca`) REFERENCES `tb_marca` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_producto`
--

LOCK TABLES `tb_producto` WRITE;
/*!40000 ALTER TABLE `tb_producto` DISABLE KEYS */;
INSERT INTO `tb_producto` VALUES (1,'CONGELADORA COLDEX 247 LTS CH08','Coldex',2,2,'a89dc9c4-d737-4c8f-890c-88edb9851c5b',1025,20,10,_binary ''),(2,'SONY EQUIPO DE SONIDO BLUETOOTH, HDMI, DVD Y KARAOKE MHC-V43D','Forte',1,9,'cac64920-b1fc-4af3-923e-78994b8401f9',2000,20,10,_binary '\0'),(3,'ASPIRADORA ELECTROLUX SON10 1400W','Forte',2,2,'abb49edf-8157-4430-82de-1ff8ef4be779',2000,10,17,_binary '\0'),(4,'IPHONE 13 6.1 - AZUL MEDIANOCHE','Apple',3,3,'84446272-dfc5-44b1-ac61-52d87cb14921',2660,10,14,_binary ''),(5,'SMARTPHONE SAMSUNG GALAXY S24 ULTRA 512G','Samsung',3,2,'f6ac7739-2176-46ae-a82c-3b3c1287897e',3999,11,13,_binary ''),(6,'APPLE IPHONE 12 AZUL 64 GB REACONDICIONADO','Apple',3,3,'ec572125-6954-47c5-b923-53a8e1a82547',1455,0,44,_binary ''),(7,'APPLE IPHONE 14 6.1 128G+12MP MEDIANOCHE','Apple',3,3,'4b97c284-f97a-44ae-b172-16a316763c64',1966,0,12,_binary ''),(8,'LAPTOP LENOVO IDEAPAD 1 AMD RYZEN 7 5700U 16GB 1TB SSD','Lenovo',4,4,'9ff61f8e-0f18-4ff7-ad41-fa3e2076c602',1860,0,10,_binary ''),(9,'LAPTOP ASUS TUF GAMING F15 FX507ZC4','Asus',4,6,'ffd1029f-8167-4713-bbc4-dc7bf732a6f5',2060,0,49,_binary ''),(10,'LAPTOP ACER INTEL CORE I3 8GB GB RAM','Acer',4,7,'36926f8c-8e0c-4139-afdf-32ace5dc9534',2659,0,50,_binary ''),(11,'TABLET SAMSUNG TAB S6 LITE 2024 10.4\" 128GB','Sansung',5,2,'96f76224-5628-4d3a-8230-f1c7fc01c3ae',1600,0,50,_binary ''),(12,'APPLE IPAD AIR (5TA GENERACIÓN) WI-FI 64GB MORADO','Apple',5,3,'8639d2fb-2366-4ff6-b9f6-f7aef0121ea1',1999,0,46,_binary ''),(13,'CONSOLA SONY PLAY STATION 5 HW STANDARD SPIDERMAN 2','Sony',6,9,'052b855f-bee5-4865-b418-438a73c99d89',2100,10,49,_binary ''),(14,'PLAY STATION 4 (PS4) 1TB GOW RAGNAROK','Sony',6,9,'c07305f3-3855-4901-80ec-f9b038611664',1900,0,49,_binary ''),(15,'CONSOLA NINTENDO SWITCH MODELO OLED','Sony',6,11,'15d28d39-027a-4e6a-91db-2ab4eaf57a8a',2050,0,45,_binary ''),(16,'COMBO DE TECLADO Y MOUSE CHONCHOW PARA JUEGOS MECÁNICOS','Asus',7,10,'f8ce47b7-3f88-4a9e-959a-0487b25545b3',1000,12,50,_binary ''),(17,'TELEVISOR LG NANOCELL 4K 55\" SMART TV CON THINQ','LG',2,2,'497264bf-bd0c-402e-822b-153a85562d4e',1600,0,50,_binary ''),(18,'AUDIFONOSINDY EVO XT SKULLCANDY','Evo',2,8,'dad91c99-a763-4ca6-b640-07b105630aa7',1200,0,49,_binary ''),(21,'AUDÍFONOS BLUETOOTH TWS HUAWEI FREEBUDS SE 3 | BEIGE','Samnsung XD',2,8,'975136da-f24e-4e09-9393-70d47b1c2446',1500,0,39,_binary ''),(22,'LAPTOP HP 15-FD0061LA INTEL CORE I5 12VA 10 NÚCLEOS 12 GB RAM 512 GB SSD 15.6\"','oster',1,5,'a6bf811c-9931-45c9-9dc3-79f0bafa7aad',800,0,45,_binary '');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'Cristian','Orizano','e018a16d-c905-4d8c-9917-b5c767b30cc0','cristian@gmail.com','$2a$10$0gBxewHfhSu9R4YF/B8/M.yGIibs9CiBeTgA3nOikdeKNq18OoOFC',_binary ''),(2,'Nini','Salazar Robert','ccf6b12b-af86-4626-b21b-b0d1063a9880','nini@gmail.com','$2a$10$0gBxewHfhSu9R4YF/B8/M.yGIibs9CiBeTgA3nOikdeKNq18OoOFC',_binary ''),(3,'Juan','Pérez Alva',NULL,'juanperez123','$2a$10$O8F8MFFmCMjOmNi7HXMtAu1W3sW5Irq1OfihHwk6T/jiFXcPmHXta',_binary ''),(4,'Lucas','Alvarez Contreras','09bfb2e6-a27b-4e7c-a357-35f838300e23','lucas@gmail.com','$2a$10$0CodTTXC2.GuDkOAvhtri.BUT2k2tBbQeZK7JmbaPdVSAVQVhg.cG',_binary ''),(5,'Martina','Rios Fernandez','a429e15d-dfe6-4a93-9b17-308d18281919','martina@gmail.com','$2a$10$xA1zfb1kqUTN67t1smHIAunuLX0xzK.tOakz9xHD/HU/9YcxSmNFS',_binary ''),(6,'Javier','Mendez Guzman',NULL,'javier@gmail.com','$2a$10$r51pXhZtNM9hqFvMqVuxV.0gF6eNP5mYC33g0XGqa8SqmbAG3H/MG',_binary ''),(7,'Sebastian','Torres Alarcon',NULL,'sebastian@gmail.com','$2a$10$XG0tnOLfKxUIe6kd79l/NeeM2.iuGSfB0IGFgQVQT3CplaNKlEM1W',_binary ''),(8,'Isabella','Contreras Herrera','d1de0563-a702-4e0d-b4d9-3ce06da46b60','isabela@gmail.com','$2a$10$PUJEutK1UyyTXD1oz/Nsbere3zjKqfJ7xjMtkkDeSIGyAn9BoOLm6',_binary ''),(10,'Tomás','Herrera Salazar',NULL,'tomas@gmail.com','$2a$10$1hLTg0qu/eKsw31WhmMMLuOntUlPn9bNV7l4jRotMUXiaHBQvLnCW',_binary ''),(11,'Emilio','Vargas Paredes',NULL,'emilio@gmail.com','$2a$10$DkGNTVLRwd1oiFqIJ2DpzumAG16qnGFajcipwQ1CjtTK1sfcYuzDO',_binary ''),(12,'Asaf','Rodriguez Aguirre',NULL,'asaf@gmail.com','$2a$10$wBpUNrixo0CXT7iKPKDCqu.Y4M3XCr.DgoR09xilCwInflrEBCxjm',_binary ''),(13,'Alfonso','Cevallos Herrera',NULL,'alfonso@gmail.com','$2a$10$FxOPOWIfgfmdq2bJAscvduExsh8rT0W8.67XY1.lc/FZa7pFB9rdG',_binary '');
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
INSERT INTO `tb_usuario_role` VALUES (1,1),(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(10,2),(11,2),(12,2),(13,2);
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

-- Dump completed on 2025-03-06 14:15:52
