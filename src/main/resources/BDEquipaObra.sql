-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: equipa_obra
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `adm_id` int NOT NULL AUTO_INCREMENT,
  `adm_administrador` varchar(45) NOT NULL,
  `contraseña` varchar(60) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  PRIMARY KEY (`adm_id`),
  UNIQUE KEY `idusuario_UNIQUE` (`adm_id`),
  UNIQUE KEY `usuario_UNIQUE` (`adm_administrador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'admin','admin','test','admin');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `cat_id` int NOT NULL AUTO_INCREMENT,
  `cat_nombre` varchar(100) NOT NULL,
  `cat_descripcion` longtext,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `idcategoria_UNIQUE` (`cat_id`),
  UNIQUE KEY `nombre_UNIQUE` (`cat_nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Compresores de aire','Son ideales para darle potencia a otras herramientas neumáticas o bien realizar múltiples tareas como inflar neumáticos de coches y bicicletas, limpiar o hasta rociar pintura.'),(2,'Mezcladoras de cemento','Mezcla los componentes del concreto, tales como el cemento, la arena, la piedra y el agua. La ventaja de usar una mezcladora en vez de hacer el batido a mano, es que la mezcla de concreto queda uniforme y homogénea.'),(3,'Apisonadores','Especiales para trabajos de compactación, mayor rendimiento y durabilidad en la construccion.'),(4,'Maquinaria semipesada','Son maquinarias de tamaño mediano utilizados generalmente en la construcción por ejemplo: Camión volqueta, carros Cisternas o Aguateros, camiones escalera. El peso y volumen de estas unidades es mediano.'),(5,'Cortadoras de concreto','se utilizan para realizar cortes lineales y con precisión en concreto y asfalto. Son muy usadas para realizar juntas de dilatación, trabajos de reparación y pavimentación. ');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `cli_id` int NOT NULL AUTO_INCREMENT,
  `cli_nombre` varchar(60) NOT NULL,
  `cli_apellido` varchar(60) NOT NULL,
  `cli_email` varchar(100) NOT NULL,
  `cli_contrasena` varchar(45) NOT NULL,
  `cli_telefono` varchar(45) NOT NULL,
  `cli_direccion` varchar(100) NOT NULL,
  `cli_ciudad` varchar(45) NOT NULL,
  `cli_cedula` int NOT NULL,
  PRIMARY KEY (`cli_id`),
  UNIQUE KEY `idcliente_UNIQUE` (`cli_id`),
  UNIQUE KEY `email_UNIQUE` (`cli_email`),
  UNIQUE KEY `cedula_UNIQUE` (`cli_cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Carolina','Becerra','cbddd@gmail.com','123456','444457556','dskfasd 88 sad 57','Bogotá',2222222),(2,'Abril','Diaz','abrildiaz@gmail.com','123','222','sakdd','mdsdn',2244);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagen_producto`
--

DROP TABLE IF EXISTS `imagen_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagen_producto` (
  `ima_id` int unsigned NOT NULL AUTO_INCREMENT,
  `ima_idproducto` int NOT NULL,
  `ima_link` varchar(800) NOT NULL,
  `ima_descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ima_id`),
  UNIQUE KEY `idimagen_prod_UNIQUE` (`ima_id`),
  KEY `fk_idproducto_idimagen_idx` (`ima_idproducto`),
  CONSTRAINT `fk_idproducto_idimagen` FOREIGN KEY (`ima_idproducto`) REFERENCES `producto` (`pro_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagen_producto`
--

LOCK TABLES `imagen_producto` WRITE;
/*!40000 ALTER TABLE `imagen_producto` DISABLE KEYS */;
INSERT INTO `imagen_producto` VALUES (1,1,'https://equipmaster.com.co/wp-content/uploads/2021/05/Apisonador_Motor_Honda_GX100_Equipmaster.jpg','pend'),(2,1,'https://equipmaster.com.co/wp-content/uploads/2021/05/Apisonador_Motor_Honda_GX100_Equipmaster-.jpg','pend'),(3,1,'https://equipmaster.com.co/wp-content/uploads/2021/05/Apisonador-Motor-EM-ACH120-HONDA-GX120R-gasolina-Equipmaser.jpg','pend'),(4,2,'https://equipmaster.com.co/wp-content/uploads/2021/05/Mezcaladora-de-Concreto-Master-Equipmaster.png','pend'),(5,2,'https://equipmaster.com.co/wp-content/uploads/2021/05/Mezcladora_de_Concreto_Equipmaster.jpg','pend'),(6,2,'https://equipmaster.com.co/wp-content/uploads/2021/05/Despiece-Mezcladora-de-medio-y-1-bulto-Equipmaster.jpg','pend'),(7,3,'https://globalmotor.vtexassets.com/arquivos/ids/158387-1200-1200?v=637853018879900000&width=1200&height=1200&aspect=true','pend'),(8,3,'https://globalmotor.vtexassets.com/arquivos/ids/158388-1200-1200?v=637853019094970000&width=1200&height=1200&aspect=true','pend'),(9,3,'https://globalmotor.vtexassets.com/arquivos/ids/158389-1200-1200?v=637853019241500000&width=1200&height=1200&aspect=true','pend'),(10,4,'https://globalmotor.vtexassets.com/arquivos/ids/178127-1200-1200?v=638259015744400000&width=1200&height=1200&aspect=true','pend'),(11,4,'https://globalmotor.vtexassets.com/arquivos/ids/178128-1200-1200?v=638259015908530000&width=1200&height=1200&aspect=true','pend'),(12,4,'https://globalmotor.vtexassets.com/arquivos/ids/178129-1200-1200?v=638259016079770000&width=1200&height=1200&aspect=true','pend');
/*!40000 ALTER TABLE `imagen_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `pro_id` int NOT NULL AUTO_INCREMENT,
  `pro_nombre` varchar(300) NOT NULL,
  `pro_codigo` varchar(150) NOT NULL,
  `pro_descripcion` longtext NOT NULL,
  `pro_precio` double NOT NULL,
  `pro_cantidad` int NOT NULL,
  `pro_idcategoria` int NOT NULL,
  PRIMARY KEY (`pro_id`),
  UNIQUE KEY `codigo_UNIQUE` (`pro_codigo`),
  UNIQUE KEY `idproducto_UNIQUE` (`pro_id`),
  UNIQUE KEY `nombre_UNIQUE` (`pro_nombre`),
  KEY `fk_producto_categoria1_idx` (`pro_idcategoria`),
  CONSTRAINT `fk_producto_categoria1` FOREIGN KEY (`pro_idcategoria`) REFERENCES `categoria` (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Apisonador (canguro) Motor HONDA GX120R Gasolina','ML 10001','Motor Gasolina: Honda GXR120. Potencia: 4 Hp. Masa operacional: 75 Kg ',50000,3,3),(2,'Mezcladora de Concreto MASTER 1.5 Bultos (325litros/11ft³) (con diferentes opciones de motor)','ML 10002','MASTER 1.5 Bultos (325litros/11ft³ ) (con diferentes opciones de tipo de Motor). Capacidad litros 325 litros. Capacidad pies 3 11,4. Calibre lamina 3/16″. ',80000,5,2),(3,'Cortadora de concreto FORTE a gasolina 13 HP, conducción automática','CR 10001','La cortadora de concreto de conducción automática, realiza cortes rectilíneos en pisos de concreto, asfalto, adoquín y mármol entre otros proporcionando al operario facilidad en el manejo y mayor precisión, sus ruedas facilitan el desplazamiento y maniobrabilidad de la máquina. Motor FORTE 13 HP.',50,5,5),(4,'Cortadora de concreto de conducción automática, realiza cortes rectilíneos en pisos de concreto, asfalto, adoquín y mármol entre otros proporcionando al operario facilidad en el manejo y mayor precisión, sus ruedas facilitan el desplazamiento y maniobrabilidad de la máquina. Motor FORTE 13 HP.','CR 10002','Capacidad tanque de agua: 35 L. Dimensiones: 105x58x94 cm. Material de tanque de agua: Metálico. Peso: 128 Kg. Motor: Forte GM390. Ajuste de profundidad: Manija de Rotación. Conducción: MANUAL. DIÁMETRO de disco: 5-50 cm (14-20”)',43,2,5),(5,'Mezcladora de Concreto MASTER 2 Bultos (390litros/13.7ft³) (con diferentes opciones de motor)','MZ 10002','Pending',36,6,2);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `res_id` int unsigned NOT NULL,
  `res_fecha_desde` date NOT NULL,
  `res_fecha_hasta` date NOT NULL,
  `res_idcliente` int NOT NULL,
  PRIMARY KEY (`res_id`),
  UNIQUE KEY `idreserva_UNIQUE` (`res_id`),
  KEY `fk_reserva_cliente1_idx` (`res_idcliente`),
  CONSTRAINT `fk_reserva_cliente` FOREIGN KEY (`res_idcliente`) REFERENCES `cliente` (`cli_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_detalle`
--

DROP TABLE IF EXISTS `reserva_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_detalle` (
  `resd_id` int unsigned NOT NULL AUTO_INCREMENT,
  `resd_codigo` varchar(45) NOT NULL,
  `resd_precio_unitario` double NOT NULL,
  `resd_cantidad` int NOT NULL,
  `resd_idproducto` int NOT NULL,
  `resd_idreserva` int unsigned NOT NULL,
  PRIMARY KEY (`resd_id`),
  UNIQUE KEY `usuario_UNIQUE` (`resd_codigo`),
  UNIQUE KEY `id_reserva_UNIQUE` (`resd_id`),
  KEY `fk_reserva_detalle_producto1_idx` (`resd_idproducto`),
  KEY `fk_reserva_detalle_reservadetalle_idx` (`resd_idreserva`),
  CONSTRAINT `fk_reserva_detalle_producto1` FOREIGN KEY (`resd_idproducto`) REFERENCES `producto` (`pro_id`),
  CONSTRAINT `fk_reserva_detalle_reservadetalle` FOREIGN KEY (`resd_idreserva`) REFERENCES `reserva` (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favoritos`
--

DROP TABLE IF EXISTS `favoritos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favoritos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `producto` (`pro_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`usu_id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_detalle`
--

LOCK TABLES `reserva_detalle` WRITE;
/*!40000 ALTER TABLE `reserva_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva_detalle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-22 20:44:25


