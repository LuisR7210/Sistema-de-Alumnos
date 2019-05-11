-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: alumnos
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `alumno` (
  `matricula` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `a_paterno` varchar(20) NOT NULL,
  `a_materno` varchar(20) NOT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES ('S16084392','Alejandra','Mora','Rojas'),('S17012936','Luis Roberto','Herrera','Hernández'),('S18082361','Aron','Gutierrez','Mendoza');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dia_materia`
--

DROP TABLE IF EXISTS `dia_materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dia_materia` (
  `id_dia` int(11) NOT NULL AUTO_INCREMENT,
  `dia` varchar(10) NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `salon` varchar(5) NOT NULL,
  `id_horario` int(11) NOT NULL,
  PRIMARY KEY (`id_dia`),
  KEY `id_horario` (`id_horario`),
  CONSTRAINT `dia_materia_ibfk_1` FOREIGN KEY (`id_horario`) REFERENCES `horario_materia` (`id_horario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia_materia`
--

LOCK TABLES `dia_materia` WRITE;
/*!40000 ALTER TABLE `dia_materia` DISABLE KEYS */;
INSERT INTO `dia_materia` VALUES (112,'Lunes','07:00:00','08:59:00','CC3',1),(113,'Martes','11:00:00','12:59:00','CC3',1),(114,'Miércoles','07:00:00','08:59:00','CC3',1),(115,'Martes','09:00:00','10:59:00','113',3),(116,'Jueves','11:00:00','12:59:00','CC3',3),(117,'Viernes','07:00:00','08:59:00','108',3),(118,'Lunes','09:00:00','10:59:00','CC2',4),(119,'Miércoles','09:00:00','10:59:00','CC2',4),(120,'Jueves','09:00:00','10:59:00','105',4),(121,'Lunes','11:00:00','12:59:00','104',5),(122,'Miércoles','11:00:00','12:59:00','CC1',5),(123,'Viernes','11:00:00','12:59:00','108',5),(124,'Martes','07:00:00','08:59:00','108',6),(125,'Jueves','07:00:00','08:59:00','CC2',6),(126,'Viernes','09:00:00','10:59:00','108',6);
/*!40000 ALTER TABLE `dia_materia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario_alumno`
--

DROP TABLE IF EXISTS `horario_alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `horario_alumno` (
  `matricula` varchar(9) NOT NULL,
  `id_horario` int(11) NOT NULL,
  KEY `matricula` (`matricula`),
  KEY `id_horario` (`id_horario`),
  CONSTRAINT `horario_alumno_ibfk_1` FOREIGN KEY (`matricula`) REFERENCES `alumno` (`matricula`),
  CONSTRAINT `horario_alumno_ibfk_2` FOREIGN KEY (`id_horario`) REFERENCES `horario_materia` (`id_horario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario_alumno`
--

LOCK TABLES `horario_alumno` WRITE;
/*!40000 ALTER TABLE `horario_alumno` DISABLE KEYS */;
INSERT INTO `horario_alumno` VALUES ('S18082361',6),('S18082361',5),('S17012936',4),('S17012936',3),('S16084392',1);
/*!40000 ALTER TABLE `horario_alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario_materia`
--

DROP TABLE IF EXISTS `horario_materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `horario_materia` (
  `id_horario` int(11) NOT NULL AUTO_INCREMENT,
  `profesor` varchar(70) NOT NULL,
  `nrc` int(11) NOT NULL,
  `id_materia` int(11) NOT NULL,
  PRIMARY KEY (`id_horario`),
  KEY `id_materia` (`id_materia`),
  CONSTRAINT `horario_materia_ibfk_1` FOREIGN KEY (`id_materia`) REFERENCES `materia` (`id_materia`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario_materia`
--

LOCK TABLES `horario_materia` WRITE;
/*!40000 ALTER TABLE `horario_materia` DISABLE KEYS */;
INSERT INTO `horario_materia` VALUES (1,'Cruz Reyes Oscar José Luis',74465,5),(3,'Hernández González Lizbeth Alejandra',74289,4),(4,'Benitez Guerrero Edgard Iván',74282,3),(5,'Cortés Verdín María Karen',74284,2),(6,'Castañeda Sanchez Fredy',74286,1);
/*!40000 ALTER TABLE `horario_materia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materia`
--

DROP TABLE IF EXISTS `materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `materia` (
  `id_materia` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `horas_teoria` int(11) NOT NULL,
  `horas_practica` int(11) NOT NULL,
  `creditos` int(11) NOT NULL,
  PRIMARY KEY (`id_materia`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materia`
--

LOCK TABLES `materia` WRITE;
/*!40000 ALTER TABLE `materia` DISABLE KEYS */;
INSERT INTO `materia` VALUES (1,'Principios de Construcción de Software',4,2,10),(2,'Principios de Diseño de Software',4,2,10),(3,'Paradigmas de programación',4,2,10),(4,'Procesos de Software',4,2,10),(5,'Redes',4,2,10),(6,'iouoi',1,1,1);
/*!40000 ALTER TABLE `materia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-11 15:38:01
