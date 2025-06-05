-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: systempriority_db
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accion` varchar(255) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlokcpcj85w8sm6ye61wq2w2vn` (`usuario_id`),
  CONSTRAINT `FKlokcpcj85w8sm6ye61wq2w2vn` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
INSERT INTO `historial` VALUES (1,'Tarea creada: accion registrar','2025-06-03 21:17:27.646647',1),(2,'Tarea eliminada: accion registrar','2025-06-03 21:24:45.473114',1),(3,'Tarea restaurada: accion registrar','2025-06-03 21:28:08.298298',1),(4,'Tarea creada: accion registrar','2025-06-03 21:37:42.391173',1),(5,'Tarea creada: accion registrar','2025-06-03 21:38:23.030394',1),(6,'Tarea creada: Confirmar revision ','2025-06-03 21:39:00.926128',1),(7,'Tarea eliminada: accion registrar','2025-06-03 21:40:52.375137',1),(8,'Tarea restaurada: accion registrar','2025-06-03 21:41:04.034453',1),(9,'Tarea actualizada: Planificar evento actualizado','2025-06-03 21:41:53.040814',1),(10,'Tarea programada: Atender cliente Malo','2025-06-03 21:43:30.044005',1),(11,'Tarea creada: Ver partido de futbol','2025-06-03 22:51:34.532997',3),(12,'Tarea creada: Confirmar revision ','2025-06-03 22:51:49.143506',3),(13,'Tarea eliminada: Confirmar revision ','2025-06-03 23:03:15.143889',3),(14,'Tarea restaurada: Confirmar revision ','2025-06-03 23:03:34.153242',3);
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-04 10:35:18
