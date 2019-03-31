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
INSERT INTO `tbl_clien_servicios` VALUES (1,13,1),(1,14,1),(1,15,1),(2,14,2),(2,15,2);
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
INSERT INTO `tbl_clientes` VALUES (1,'rosana acosta de leon ','000','1980-01-01','111','PAIPA'),(2,'JULIAN PEREZ','000','1980-01-01','111','SOGAMOSO'),(5,'santiago rafael ','1515','1990-01-18','111','DUITAMA'),(6,'jose maria muñoz','000','1980-01-01','111','BOGOTA'),(7,'juan david redondo robles','72711010','1991-05-23','calle 49','DUITAMA'),(8,'kendris joana','730','1993-11-06','730','SOGAMOSO');
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
INSERT INTO `tbl_paquetes` VALUES (1,'juan','paquete pequeño',15000),(3,'carta','',2000);
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
INSERT INTO `tbl_sedes` VALUES (1,'sede 1','CALLE 149','BOGOTA'),(2,'sede 2','calle 900','paipa'),(5,'sede cuatro','calle av','SOGAMOSO');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_servicios`
--

LOCK TABLES `tbl_servicios` WRITE;
/*!40000 ALTER TABLE `tbl_servicios` DISABLE KEYS */;
INSERT INTO `tbl_servicios` VALUES (6,'2019-03-31 05:15:59',1119,1,'Ingresado a  Bodega','11','SOGAMOSO',NULL,NULL),(8,'2019-03-31 05:19:57',1119,1,'Ingresado a  Bodega','1','IBAGUE',NULL,NULL),(9,'2019-03-31 05:24:23',1119,1,'Ingresado a  Bodega','1','BOGOTA',NULL,NULL),(10,'2019-03-31 05:25:56',1119,1,'Ingresado a  Bodega','1','SOGAMOSO',NULL,NULL),(12,'2019-03-31 05:30:48',1119,1,'Ingresado a  Bodega','1','DUITAMA',NULL,NULL),(13,'2019-03-31 05:32:03',1118,1,'Ingresado a  Bodega','1','SOGAMOSO',NULL,NULL),(14,'2019-03-31 05:33:08',1118,1,'Ingresado a  Bodega','','PAIPA',NULL,NULL),(15,'2019-03-31 05:37:04',1118,1,'Ingresado a  Bodega','1','PAIPA',NULL,NULL);
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
  `TRABA_LAST_SESION` datetime DEFAULT '2000-01-01 00:00:00',
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
INSERT INTO `tbl_trabajadores` VALUES (1118,'juan david redondo robles','1','juan','b49a5780a99ea81284fc0746a78f84a30e4d5c73','Cajero',1,'1991-05-23','calle 49','2019-03-31 05:36:40'),(1119,'maolis saltaren','1','mao','f3d17dc763f95e629a7e71f9e2a2ee9df43c8746','Admin',1,'2019-03-14','calle 50','2019-03-31 05:35:36'),(1120,'Carlos Gómez','1','carlos','ab5e2bca84933118bbc9d48ffaccce3bac4eeb64','Conductor',1,'1978-04-25','calle1','2019-03-26 23:48:57'),(1121,'Carlos Gómez','3','kendris','3d0cef9d4536a461b0be2488d21ee9982d223af2','Cajero',2,'1978-04-25','555','2019-03-28 00:53:27'),(1122,'Carlos Gómez','2','cesar','CAGOMEZ','Conductor',2,'1978-04-25','22','2019-03-26 23:48:57'),(1123,'Carlos Gómez','2','ximena','CAGOMEZ','Conductor',2,'1978-04-25','1','2019-03-26 23:48:57'),(1124,'Carlos Gómez','2','rioka','CAGOMEZ','Conductor',2,'1978-04-25','6','2019-03-26 23:48:57'),(1125,'jesus alberto redondo','222','jesu','dc72056b56f05659a45bfc2ae38db18ab387e28a','Mensajero',1,'2019-08-08','222','2019-03-28 15:40:12'),(3636,'empleado sede dos','2','sede2','b9a085bda1b6003f50e59d320b705222c60d02d3','Admin',2,'2019-03-08','direccion emple sede 2','2019-03-28 01:03:28'),(5050,'cicuenta','50','usu','7c174401f411ad683cb590ea3a5b73688defd95b','Admin',2,'2019-03-05','50','2019-03-27 16:47:16'),(100000,'santi','4545','santi','8da22c45572ff7532871766932ae66e63da3e927','Bodeguero',1,'2016-07-19','54','2019-03-28 00:47:00');
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

-- Dump completed on 2019-03-31  6:14:35
