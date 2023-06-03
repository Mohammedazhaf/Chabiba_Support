CREATE DATABASE IF NOT EXISTS chabiba_support;

USE chabiba_support;

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `nom_entreprise` varchar(255) DEFAULT NULL,
  `id_personne` bigint NOT NULL,
  PRIMARY KEY (`id_personne`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



INSERT INTO `client` VALUES ('azhaf',2);

--
-- Table structure for table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE `commentaire` (
  `id_commentaire` bigint NOT NULL,
  `etoile` int NOT NULL,
  `textec` varchar(100) NOT NULL,
  `id_personne` bigint DEFAULT NULL,
  `id_demande` bigint DEFAULT NULL,
  PRIMARY KEY (`id_commentaire`),
  KEY `FKd1sgx9l7vu6iv94ahb63hgc9j` (`id_demande`),
  KEY `FKslevetghp1wm3jj1oc8bfm7ov` (`id_personne`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `commentaire`
--

-- Table structure for table `commentaire_seq`
--

DROP TABLE IF EXISTS `commentaire_seq`;
CREATE TABLE `commentaire_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `commentaire_seq`
--

INSERT INTO `commentaire_seq` VALUES (1);

--
-- Table structure for table `demande`
--

DROP TABLE IF EXISTS `demande`;
CREATE TABLE `demande` (
  `id_demande` bigint NOT NULL AUTO_INCREMENT,
  `budget` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `documentd` varbinary(255) NOT NULL,
  `etat` varchar(255) NOT NULL,
  `message` varchar(40) NOT NULL,
  `reponse` bit(1) NOT NULL,
  `service` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `id_personne` bigint DEFAULT NULL,
  PRIMARY KEY (`id_demande`),
  KEY `FK6brv71fiqoqaedhvjsl790gid` (`id_personne`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `demande`
--


--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `cin` varchar(255) NOT NULL,
  `id_personne` bigint NOT NULL,
  PRIMARY KEY (`id_personne`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Dumping data for table `employee`
--

--
-- Table structure for table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE `personne` (
  `id_personne` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(80) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `num_tel` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id_personne`),
  UNIQUE KEY `UK_lif11ug7pqkdimuk0beonbfng` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Dumping data for table `personne`
--


--
-- Table structure for table `rapport`
--

DROP TABLE IF EXISTS `rapport`;
CREATE TABLE `rapport` (
  `id_rapport` bigint NOT NULL,
  `contenu` varchar(150) NOT NULL,
  `date` date NOT NULL,
  `documentr` varbinary(255) NOT NULL,
  `id_demande` bigint DEFAULT NULL,
  `id_personne` bigint DEFAULT NULL,
  PRIMARY KEY (`id_rapport`),
  KEY `FK5nvs2ntsyj6xfk5dr59je3wro` (`id_demande`),
  KEY `FK50xnwdp526jlm49y35w7fqtof` (`id_personne`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Dumping data for table `rapport`
--

--
-- Table structure for table `rapport_seq`
--

DROP TABLE IF EXISTS `rapport_seq`;
CREATE TABLE `rapport_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rapport_seq`
--

INSERT INTO `rapport_seq` VALUES (1);


-- Dump completed on 2023-06-01 14:38:33
