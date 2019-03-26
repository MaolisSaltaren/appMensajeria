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
  `TICI_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CLIE_ID`,`SERVI_ID`),
  KEY `fk_TBL_CLIENTES_has_TBL_SERVICIOS_TBL_SERVICIOS1_idx` (`SERVI_ID`),
  KEY `fk_TBL_CLIENTES_has_TBL_SERVICIOS_TBL_CLIENTES1_idx` (`CLIE_ID`),
  KEY `FK_TIPO_CLIENSERVICIO_idx` (`TICI_ID`),
  CONSTRAINT `FK_TIPO_CLIENSERVICIO` FOREIGN KEY (`TICI_ID`) REFERENCES `tbl_tipo_cliente` (`TICI_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TBL_CLIENTES_has_TBL_SERVICIOS_TBL_CLIENTES1` FOREIGN KEY (`CLIE_ID`) REFERENCES `tbl_clientes` (`CLIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TBL_CLIENTES_has_TBL_SERVICIOS_TBL_SERVICIOS1` FOREIGN KEY (`SERVI_ID`) REFERENCES `tbl_servicios` (`SERVI_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clien_servicios`
--

LOCK TABLES `tbl_clien_servicios` WRITE;
/*!40000 ALTER TABLE `tbl_clien_servicios` DISABLE KEYS */;
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
  `CLIE_FECHA_NA` date DEFAULT '0000-00-00',
  `CLIE_DIRECCION` varchar(45) NOT NULL,
  PRIMARY KEY (`CLIE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clientes`
--

LOCK TABLES `tbl_clientes` WRITE;
/*!40000 ALTER TABLE `tbl_clientes` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `tbl_departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_paquetes`
--

DROP TABLE IF EXISTS `tbl_paquetes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_paquetes` (
  `PAQUE_ID` int(11) NOT NULL,
  `PAQUE_NOMBRE` varchar(45) NOT NULL,
  `PAQUE_DESCRIPCION` varchar(45) DEFAULT NULL,
  `PAQUE_PRECIO` double NOT NULL,
  PRIMARY KEY (`PAQUE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_paquetes`
--

LOCK TABLES `tbl_paquetes` WRITE;
/*!40000 ALTER TABLE `tbl_paquetes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_paquetes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_sedes`
--

DROP TABLE IF EXISTS `tbl_sedes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_sedes` (
  `SEDES_ID` int(11) NOT NULL,
  `SEDES_NOMBRE` varchar(45) NOT NULL,
  `SEDES_DIRECCION` varchar(45) DEFAULT NULL,
  `CIUD_NOMBRE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SEDES_ID`),
  KEY `FK_SEDES_CIUDAD_idx` (`CIUD_NOMBRE`),
  CONSTRAINT `FK_SEDES_CIUDAD` FOREIGN KEY (`CIUD_NOMBRE`) REFERENCES `tbl_ciudades` (`CIUD_NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_sedes`
--

LOCK TABLES `tbl_sedes` WRITE;
/*!40000 ALTER TABLE `tbl_sedes` DISABLE KEYS */;
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
  `SERVI_FECHA` datetime DEFAULT '0000-00-00 00:00:00',
  `TRABA_ID` int(11) NOT NULL,
  `PAQUE_ID` int(11) DEFAULT NULL,
  `SERVI_ESTADO` varchar(45) NOT NULL,
  `SERVI_DIRECCION` varchar(45) NOT NULL,
  `SERV_CIUDAD_DESTINO` varchar(45) NOT NULL,
  `SERVI_FECHA_ENTREGA` date DEFAULT '0000-00-00',
  `SERVI_OBSERVACION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SERVI_ID`),
  KEY `FK_TRABAJADORES_idx` (`TRABA_ID`),
  KEY `FK_PAQUETES_idx` (`PAQUE_ID`),
  CONSTRAINT `FK_PAQUETES` FOREIGN KEY (`PAQUE_ID`) REFERENCES `tbl_paquetes` (`PAQUE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_TRABAJADORES` FOREIGN KEY (`TRABA_ID`) REFERENCES `tbl_trabajadores` (`TRABA_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_servicios`
--

LOCK TABLES `tbl_servicios` WRITE;
/*!40000 ALTER TABLE `tbl_servicios` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_tipo_cliente`
--

DROP TABLE IF EXISTS `tbl_tipo_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_tipo_cliente` (
  `TICI_ID` int(11) NOT NULL,
  `TICI_NOMBRE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TICI_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_tipo_cliente`
--

LOCK TABLES `tbl_tipo_cliente` WRITE;
/*!40000 ALTER TABLE `tbl_tipo_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_tipo_cliente` ENABLE KEYS */;
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
  `TRAB_FECHA_NAC` date DEFAULT '0000-00-00',
  `TRABA_ULTIMA_SESION` datetime DEFAULT '0000-00-00 00:00:00',
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
/*!40000 ALTER TABLE `tbl_trabajadores` ENABLE KEYS */;
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

-- Dump completed on 2019-03-25 20:12:18
