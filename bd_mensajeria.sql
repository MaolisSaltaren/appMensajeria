CREATE DATABASE  IF NOT EXISTS `bd_mensajeria` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_mensajeria`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_mensajeria
-- ------------------------------------------------------
-- Server version	5.6.42-log

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
-- Table structure for table `tbl_ciudades`
--

DROP TABLE IF EXISTS `tbl_ciudades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_ciudades` (
  `CIUD_NOMBRE` varchar(45) NOT NULL,
  `DEPA_NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`CIUD_NOMBRE`),
  KEY `fk_TBL_CIUDADES_TBL_DEPARTAMENTOS1_idx` (`DEPA_NOMBRE`),
  CONSTRAINT `fk_TBL_CIUDADES_TBL_DEPARTAMENTOS1` FOREIGN KEY (`DEPA_NOMBRE`) REFERENCES `tbl_departamentos` (`DEPA_NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ciudades`
--

LOCK TABLES `tbl_ciudades` WRITE;
/*!40000 ALTER TABLE `tbl_ciudades` DISABLE KEYS */;
INSERT INTO `tbl_ciudades` VALUES ('DUITAMA','BOYACA'),('PAIPA','BOYACA'),('SOGAMOSO','BOYACA'),('BOGOTA','CUNDINAMARCA'),('IBAGUE','TOLIMA');
/*!40000 ALTER TABLE `tbl_ciudades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_clien_servicios`
--

DROP TABLE IF EXISTS `tbl_clien_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_clien_servicios` (
  `CLIE_ID` int(11) NOT NULL,
  `SERVI_ID` int(11) NOT NULL,
  `TICI_ID` int(11) NOT NULL,
  PRIMARY KEY (`CLIE_ID`,`SERVI_ID`),
  KEY `fk_TBL_CLIENTES_has_TBL_SERVICIOS_TBL_SERVICIOS1_idx` (`SERVI_ID`),
  KEY `fk_TBL_CLIENTES_has_TBL_SERVICIOS_TBL_CLIENTES1_idx` (`CLIE_ID`),
  KEY `FK_TIPO_CLIENSERVICIO_idx` (`TICI_ID`),
  CONSTRAINT `FK_TIPO_CLIENSERVICIO` FOREIGN KEY (`TICI_ID`) REFERENCES `tipo_cliente` (`TICI_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TBL_CLIENTES_has_TBL_SERVICIOS_TBL_CLIENTES1` FOREIGN KEY (`CLIE_ID`) REFERENCES `tbl_clientes` (`CLIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TBL_CLIENTES_has_TBL_SERVICIOS_TBL_SERVICIOS1` FOREIGN KEY (`SERVI_ID`) REFERENCES `tbl_servicios` (`SERVI_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clien_servicios`
--

LOCK TABLES `tbl_clien_servicios` WRITE;
/*!40000 ALTER TABLE `tbl_clien_servicios` DISABLE KEYS */;
INSERT INTO `tbl_clien_servicios` VALUES (1099765,21,1),(3214567,29,1),(3214567,30,1),(16789045,22,1),(17685247,23,1),(17685247,34,1),(36789065,27,1),(36789065,32,1),(88562478,24,1),(88562478,26,1),(1117456123,25,1),(1117456123,28,1),(1117456123,31,1),(1117456123,33,1),(1099765,31,2),(1099765,32,2),(3214567,21,2),(3214567,22,2),(3214567,23,2),(16789045,26,2),(16789045,28,2),(17685247,24,2),(17685247,33,2),(36789065,25,2),(36789065,29,2),(88562478,27,2),(88562478,30,2),(88562478,34,2);
/*!40000 ALTER TABLE `tbl_clien_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_clientes`
--

DROP TABLE IF EXISTS `tbl_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_clientes` (
  `CLIE_ID` int(11) NOT NULL,
  `CLIE_NOMBRE` varchar(45) NOT NULL,
  `CLIE_TELEFONO` varchar(45) DEFAULT NULL,
  `CLIE_FECHA_NA` date DEFAULT NULL,
  `CLIE_DIRECCION` varchar(45) NOT NULL,
  `CIUD_NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`CLIE_ID`),
  KEY `FK_CLIETES_USUARIOS_idx` (`CIUD_NOMBRE`),
  CONSTRAINT `FK_CLIETES_USUARIOS` FOREIGN KEY (`CIUD_NOMBRE`) REFERENCES `tbl_ciudades` (`CIUD_NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clientes`
--

LOCK TABLES `tbl_clientes` WRITE;
/*!40000 ALTER TABLE `tbl_clientes` DISABLE KEYS */;
INSERT INTO `tbl_clientes` VALUES (1099765,'Lucia Acuña','6789054','1980-09-25','Cra 25 Nro 45-125','BOGOTA'),(3214567,'Zulia Vega','7245678','1983-04-07','AV 115 25-40','BOGOTA'),(16789045,'Elizabeth Rincón','7896543','1985-10-10','Cra 25 Nro 76-25','DUITAMA'),(17685247,'Carlos Esteban Franco','6875241','1982-02-01','Manzana 25 Casa 545','IBAGUE'),(36789065,'Alexander Otálora','6543213','1980-05-25','Cra 24 Nro 38-18','IBAGUE'),(88562478,'Francisco Medina','9638524','1984-01-22','Cra 10 No12-05','SOGAMOSO'),(109845678,'Carlos Medina','6441934','1978-04-25','Cra 21 15-02','PAIPA'),(1117456123,'Taliana Vargas','6789032','2019-08-10','Diag 25 Nro 12-45','IBAGUE');
/*!40000 ALTER TABLE `tbl_clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_departamentos`
--

DROP TABLE IF EXISTS `tbl_departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_departamentos` (
  `DEPA_NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`DEPA_NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_departamentos`
--

LOCK TABLES `tbl_departamentos` WRITE;
/*!40000 ALTER TABLE `tbl_departamentos` DISABLE KEYS */;
INSERT INTO `tbl_departamentos` VALUES ('BOYACA'),('CUNDINAMARCA'),('TOLIMA');
/*!40000 ALTER TABLE `tbl_departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_paquetes`
--

DROP TABLE IF EXISTS `tbl_paquetes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_paquetes` (
  `PAQUE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PAQUE_NOMBRE` varchar(45) NOT NULL,
  `PAQUE_DESCRIPCION` varchar(45) DEFAULT NULL,
  `PAQUE_PRECIO` double NOT NULL,
  PRIMARY KEY (`PAQUE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_paquetes`
--

LOCK TABLES `tbl_paquetes` WRITE;
/*!40000 ALTER TABLE `tbl_paquetes` DISABLE KEYS */;
INSERT INTO `tbl_paquetes` VALUES (1,'invitaciones','paquete pequeño',3000),(3,'cartas','',2000);
/*!40000 ALTER TABLE `tbl_paquetes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_sedes`
--

DROP TABLE IF EXISTS `tbl_sedes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_sedes` (
  `SEDES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SEDES_NOMBRE` varchar(45) NOT NULL,
  `SEDES_DIRECCION` varchar(45) DEFAULT NULL,
  `CIUD_NOMBRE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SEDES_ID`),
  KEY `FK_SEDES_CIUDAD_idx` (`CIUD_NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_sedes`
--

LOCK TABLES `tbl_sedes` WRITE;
/*!40000 ALTER TABLE `tbl_sedes` DISABLE KEYS */;
INSERT INTO `tbl_sedes` VALUES (1,'TRANSPORTES DE COLOMBIA SEDE UNO ','CALLE 149','BOGOTA'),(2,'sede 2','calle 900','paipa'),(5,'sede cuatro','calle av','SOGAMOSO');
/*!40000 ALTER TABLE `tbl_sedes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_servicios`
--

DROP TABLE IF EXISTS `tbl_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_servicios` (
  `SERVI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SERVI_FECHA` datetime DEFAULT NULL,
  `TRABA_ID` int(11) NOT NULL,
  `PAQUE_ID` int(11) NOT NULL,
  `SERVI_ESTADO` varchar(45) DEFAULT 'Ingresado al sistema',
  `SERVI_DIRECCION` varchar(45) DEFAULT NULL,
  `SERV_CIUDAD_DESTINO` varchar(45) DEFAULT NULL,
  `SERVI_FECHA_ENTREGA` datetime DEFAULT NULL,
  `SERVI_OBSERVACION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SERVI_ID`),
  KEY `FK_TRABAJADORES_idx` (`TRABA_ID`),
  KEY `FK_PAQUETES_idx` (`PAQUE_ID`),
  CONSTRAINT `FK_PAQUETES` FOREIGN KEY (`PAQUE_ID`) REFERENCES `tbl_paquetes` (`PAQUE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_TRABAJADORES` FOREIGN KEY (`TRABA_ID`) REFERENCES `tbl_trabajadores` (`TRABA_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_servicios`
--

LOCK TABLES `tbl_servicios` WRITE;
/*!40000 ALTER TABLE `tbl_servicios` DISABLE KEYS */;
INSERT INTO `tbl_servicios` VALUES (21,'2019-04-01 13:08:36',1119,3,'Entregado','calle 50','BOGOTA','2019-04-01 17:16:18',NULL),(22,'2019-04-01 13:29:51',1119,1,'Entregado','1','IBAGUE','2019-04-01 17:27:24',NULL),(23,'2019-04-01 15:46:36',1119,3,'Entregado','calle 89','IBAGUE','2019-04-01 17:27:11',NULL),(24,'2019-04-01 15:49:16',11120,3,'Entregado','1223','SOGAMOSO','2019-04-01 18:18:02',NULL),(25,'2019-04-01 17:25:36',1119,3,'Entregado','sss','BOGOTA','2019-04-01 18:18:27',NULL),(26,'2019-04-01 17:49:14',1119,3,'Entregado','444','BOGOTA','2019-04-03 15:20:22',NULL),(27,'2019-04-03 15:09:59',1118,3,'Entregado','calle 50','SOGAMOSO','2019-04-03 15:28:34',NULL),(28,'2019-04-03 15:22:07',1119,3,'Entregado','3030','PAIPA','2019-04-03 15:40:04',NULL),(29,'2019-04-03 15:32:26',1119,1,'Despachado','66','PAIPA','2019-04-03 15:33:10',NULL),(30,'2019-04-03 18:26:42',1119,3,'Entregado','ccc','DUITAMA','2019-04-03 20:02:44',NULL),(31,'2019-04-03 18:32:51',1119,3,'Entregado','55','IBAGUE','2019-04-03 20:12:19',NULL),(32,'2019-04-03 19:24:38',1119,3,'Despachado','calle 59','BOGOTA','2019-04-03 19:57:31',NULL),(33,'2019-04-03 20:01:34',1119,3,'Despachado','calle 98','BOGOTA','2019-04-03 20:01:56',NULL),(34,'2019-04-03 20:11:30',1119,3,'Despachado','calle 56','BOGOTA','2019-04-03 20:11:43',NULL);
/*!40000 ALTER TABLE `tbl_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_trabajadores`
--

DROP TABLE IF EXISTS `tbl_trabajadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_trabajadores` (
  `TRABA_ID` int(11) NOT NULL,
  `TRABA_NOMBRE` varchar(45) NOT NULL,
  `TRABA_TELEFONO` varchar(45) NOT NULL,
  `TRABA_USU` varchar(45) NOT NULL,
  `TRABA_PASS` varchar(45) NOT NULL,
  `TRABA_ROL` varchar(45) NOT NULL,
  `SEDE_ID` int(11) NOT NULL,
  `TRAB_FECHA_NAC` date DEFAULT NULL,
  `TRABA_DIRECCION` varchar(45) DEFAULT NULL,
  `TRABA_LAST_SESION` datetime DEFAULT NULL,
  PRIMARY KEY (`TRABA_ID`),
  UNIQUE KEY `TRABA_USU_UNIQUE` (`TRABA_USU`),
  KEY `FK_CIUDADES_idx` (`SEDE_ID`),
  CONSTRAINT `FK_CIUDADES` FOREIGN KEY (`SEDE_ID`) REFERENCES `tbl_sedes` (`SEDES_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_trabajadores`
--

LOCK TABLES `tbl_trabajadores` WRITE;
/*!40000 ALTER TABLE `tbl_trabajadores` DISABLE KEYS */;
INSERT INTO `tbl_trabajadores` VALUES (1118,'juan redondo','3015555566','juan','b49a5780a99ea81284fc0746a78f84a30e4d5c73','Admin',1,'1990-04-30','calle 49','2019-04-03 15:24:39'),(1119,'maolis saltaren','7285010','mao','f3d17dc763f95e629a7e71f9e2a2ee9df43c8746','Admin',1,'1990-05-13','calle 50','2019-04-03 20:25:00'),(3030,'kedris johana','5656','kendris','3d0cef9d4536a461b0be2488d21ee9982d223af2','Cajero',1,'2019-04-18','5656',NULL),(11120,'santiago redondo ','123','santi','8da22c45572ff7532871766932ae66e63da3e927','Admin',2,'2019-04-19','123','2019-04-01 15:47:36'),(369874,'Sebastián Cortes','9857411','scortes','9e6a16966f4eb131b0a705a245a37f32eb8e2298','Bodeguero',1,'1985-10-05','calle 20','2000-01-01 00:00:00'),(526478,'Pepita Mendieta','5478921','pmendieta','a28b5516d12a084cb08cffb2653e551f3b1fe7ea','Cajero',1,'1985-10-05','calle 50','2000-01-01 00:00:00'),(578925,'Salomón González','6897462','sgonzalez','9e6a16966f4eb131b0a705a245a37f32eb8e2298','Mensajero',1,'1978-04-07','6897462','2000-01-01 00:00:00'),(635789,'Jorge Pérez','3685478','jperez','87f178e94b3fdd36214ec284a05ae162e59fefe4','Cajero',2,'1982-02-01','calle 1','2000-01-01 00:00:00'),(3245689,'pedro fernandez','3333333','pfernandez','1bb4cd88d9fa74d44bfafd6dc37e242d4de40a8e','Bodeguero',1,'1983-04-03','calle 25','2019-04-01 12:42:46'),(9852471,'Carlos Gómez','2065874','cagomez','7cc2d569ab9d7156b0c9396a76e277ccd9392f4f','Conductor',1,'1978-04-20','calle 20','2000-01-01 00:00:00');
/*!40000 ALTER TABLE `tbl_trabajadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_cliente`
--

DROP TABLE IF EXISTS `tipo_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_cliente` (
  `TICI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TICI_NOMBRE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TICI_ID`),
  UNIQUE KEY `TICI_NOMBRE_UNIQUE` (`TICI_NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cliente`
--

LOCK TABLES `tipo_cliente` WRITE;
/*!40000 ALTER TABLE `tipo_cliente` DISABLE KEYS */;
INSERT INTO `tipo_cliente` VALUES (1,'Cliente Emisor'),(2,'Cliente Receptor');
/*!40000 ALTER TABLE `tipo_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bd_mensajeria'
--

--
-- Dumping routines for database 'bd_mensajeria'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-03 20:29:24
