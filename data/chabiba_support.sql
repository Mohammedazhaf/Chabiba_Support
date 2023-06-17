-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2023 at 08:37 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chabiba_support`
--

-- --------------------------------------------------------

--
-- Table structure for table `affectation`
--

CREATE TABLE `affectation` (
  `id_affectation` bigint(20) NOT NULL,
  `delai_date` date NOT NULL,
  `mission` varchar(255) NOT NULL,
  `id_demande` bigint(20) DEFAULT NULL,
  `id_employee` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `affectation`
--

INSERT INTO `affectation` (`id_affectation`, `delai_date`, `mission`, `id_demande`, `id_employee`) VALUES
(1, '2023-06-30', 'Support and troubleshooting', 4, 18),
(2, '2023-06-30', 'Support and troubleshooting', 4, 12),
(5, '2023-07-05', 'UI/UX Design', 4, 17),
(6, '2023-07-05', 'example of affectation mission', 3, 14),
(7, '2023-07-05', 'example of affectation mission', 6, 17),
(8, '2023-07-05', 'example of affectation mission', 4, 13),
(10, '2023-06-13', 'jgh', 5, 2),
(11, '2023-06-22', 'jgh', 3, 17),
(12, '2023-06-20', 'dorian yates', 3, 2),
(13, '2023-06-20', 'sdvdfvdfv', 5, 13);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id_client` bigint(20) NOT NULL,
  `nom_entreprise` varchar(255) DEFAULT NULL,
  `id_personne` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id_client`, `nom_entreprise`, `id_personne`) VALUES
(1, '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_commentaire` bigint(20) NOT NULL,
  `etoile` int(11) NOT NULL,
  `textec` varchar(100) NOT NULL,
  `id_client` bigint(20) DEFAULT NULL,
  `id_demande` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `demande`
--

CREATE TABLE `demande` (
  `id_demande` bigint(20) NOT NULL,
  `budget` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `documentd` longblob DEFAULT NULL,
  `etat` varchar(255) NOT NULL,
  `message` varchar(200) NOT NULL,
  `response_responsable` varchar(50) NOT NULL DEFAULT 'pending',
  `service` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `ver_secretaire` bit(1) NOT NULL DEFAULT b'0',
  `id_client` bigint(20) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `demande`
--

INSERT INTO `demande` (`id_demande`, `budget`, `date`, `documentd`, `etat`, `message`, `response_responsable`, `service`, `titre`, `type`, `ver_secretaire`, `id_client`, `creation_date`, `file_path`) VALUES
(3, '1000', '2023-06-08', '', 'EnCours', 'We are seeking a competent and reliable web agency to undertake a crucial project for our organization. Our current website requires a complete overhaul and we are also looking to migrate to a new hos', 'acceptable', 'Developpement', 'Remake my website UI, and change my hosting ', 'Urgent', b'1', 1, '2023-06-08 07:29:28', NULL),
(4, '1000', '2023-06-08', '', 'EnCours', 'Sample message', 'acceptable', 'Maintenance', 'Sample Title', 'Urgent', b'1', 1, '2023-06-08 07:31:07', NULL),
(5, '1000', '2023-06-08', '', 'EnAttente', 'Sample message', 'rejected', 'Maintenance', 'Sample Title', 'Urgent', b'1', 1, '2023-06-08 10:05:39', NULL),
(6, '1000', '2023-06-08', '', 'Termine', 'Sample message', 'acceptable', 'Maintenance', 'Sample Title', 'Urgent', b'1', 1, '2023-06-08 10:05:40', NULL),
(7, '1000', '2023-06-08', '', 'EnCours', 'Need Backlinks for our website', 'acceptable', 'Seo', 'Hello, We need white hat backlinks for our Website.', 'Urgent', b'1', 1, '2023-06-08 10:05:41', NULL),
(8, '500', '2023-06-16', '', 'EnAttente', 'Example message', 'pending', 'Developpement', 'Example Demande', 'NonUrgent', b'0', 1, '2023-06-16 18:06:41', NULL),
(9, '500', '2023-06-16', 0x76657273696f6e3a2027332e37270d0a0d0a73657276696365733a0d0a20207461667369722d6261636b3a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f746166737369720d0a20202020726573746172743a20616c776179730d0a20202020706f7274733a0d0a2020202020202d2022383038303a38303830220d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d20737072696e672e64617461736f757263652e75726c3d6a6462633a6d7973716c3a2f2f64623a333330362f746166737369720d0a20202020646570656e64735f6f6e3a0d0a2020202020202d2064620d0a0d0a20207461667369722d66726f6e743a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f66726f6e74456e640d0a20202020706f7274733a0d0a2020202020202d2022343230303a3830220d0a0d0a202064623a0d0a20202020696d6167653a206d7973716c0d0a20202020726573746172743a20616c776179730d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d204d5953514c5f524f4f545f50415353574f52443d726f6f740d0a2020202020202d204d5953514c5f44415441424153453d746166737369720d0a20202020706f7274733a0d0a2020202020202d2022333330363a33333036220d0a20202020766f6c756d65733a0d0a2020202020202d202e2f646174612e73716c3a2f646f636b65722d656e747279706f696e742d696e697464622e642f646174612e73716c0d0a0d0a2020736f6c723a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f736f6c720d0a20202020706f7274733a0d0a2020202020202d2022383938333a38393833220d0a20202020636f6d6d616e643a2062617368202d6320222f6f70742f736f6c722f62696e2f736f6c7220737461727420262620736c656570203130202626202f6f70742f736f6c722f62696e2f736f6c72206372656174655f636f7265202d632074616661736972207c7c2074727565202626207461696c202d66202f6465762f6e756c6c220d0a, 'EnAttente', 'Example message', 'pending', 'Developpement', 'Example Demande', 'NonUrgent', b'0', 1, '2023-06-16 18:06:51', 'C:/Users/issam/Desktop/BackendNew/uploads/docker-compose.yml'),
(10, '500', '2023-06-16', 0x76657273696f6e3a2027332e37270d0a0d0a73657276696365733a0d0a20207461667369722d6261636b3a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f746166737369720d0a20202020726573746172743a20616c776179730d0a20202020706f7274733a0d0a2020202020202d2022383038303a38303830220d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d20737072696e672e64617461736f757263652e75726c3d6a6462633a6d7973716c3a2f2f64623a333330362f746166737369720d0a20202020646570656e64735f6f6e3a0d0a2020202020202d2064620d0a0d0a20207461667369722d66726f6e743a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f66726f6e74456e640d0a20202020706f7274733a0d0a2020202020202d2022343230303a3830220d0a0d0a202064623a0d0a20202020696d6167653a206d7973716c0d0a20202020726573746172743a20616c776179730d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d204d5953514c5f524f4f545f50415353574f52443d726f6f740d0a2020202020202d204d5953514c5f44415441424153453d746166737369720d0a20202020706f7274733a0d0a2020202020202d2022333330363a33333036220d0a20202020766f6c756d65733a0d0a2020202020202d202e2f646174612e73716c3a2f646f636b65722d656e747279706f696e742d696e697464622e642f646174612e73716c0d0a0d0a2020736f6c723a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f736f6c720d0a20202020706f7274733a0d0a2020202020202d2022383938333a38393833220d0a20202020636f6d6d616e643a2062617368202d6320222f6f70742f736f6c722f62696e2f736f6c7220737461727420262620736c656570203130202626202f6f70742f736f6c722f62696e2f736f6c72206372656174655f636f7265202d632074616661736972207c7c2074727565202626207461696c202d66202f6465762f6e756c6c220d0a, 'EnAttente', 'Example message', 'pending', 'Developpement', 'Example Demande', 'NonUrgent', b'0', 1, '2023-06-16 18:08:38', './docker-compose.yml'),
(11, '500', '2023-06-16', 0x76657273696f6e3a2027332e37270d0a0d0a73657276696365733a0d0a20207461667369722d6261636b3a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f746166737369720d0a20202020726573746172743a20616c776179730d0a20202020706f7274733a0d0a2020202020202d2022383038303a38303830220d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d20737072696e672e64617461736f757263652e75726c3d6a6462633a6d7973716c3a2f2f64623a333330362f746166737369720d0a20202020646570656e64735f6f6e3a0d0a2020202020202d2064620d0a0d0a20207461667369722d66726f6e743a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f66726f6e74456e640d0a20202020706f7274733a0d0a2020202020202d2022343230303a3830220d0a0d0a202064623a0d0a20202020696d6167653a206d7973716c0d0a20202020726573746172743a20616c776179730d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d204d5953514c5f524f4f545f50415353574f52443d726f6f740d0a2020202020202d204d5953514c5f44415441424153453d746166737369720d0a20202020706f7274733a0d0a2020202020202d2022333330363a33333036220d0a20202020766f6c756d65733a0d0a2020202020202d202e2f646174612e73716c3a2f646f636b65722d656e747279706f696e742d696e697464622e642f646174612e73716c0d0a0d0a2020736f6c723a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f736f6c720d0a20202020706f7274733a0d0a2020202020202d2022383938333a38393833220d0a20202020636f6d6d616e643a2062617368202d6320222f6f70742f736f6c722f62696e2f736f6c7220737461727420262620736c656570203130202626202f6f70742f736f6c722f62696e2f736f6c72206372656174655f636f7265202d632074616661736972207c7c2074727565202626207461696c202d66202f6465762f6e756c6c220d0a, 'EnAttente', 'Example message', 'pending', 'Developpement', 'Example Demande', 'NonUrgent', b'0', 1, '2023-06-16 18:14:54', 'C:\\Users\\issam\\Desktop\\BackendNew\\Chabiba_Support/uploads/docker-compose.yml'),
(12, '500', '2023-06-16', 0x48454c502e6d640a7461726765742f0a212e6d766e2f777261707065722f6d6176656e2d777261707065722e6a61720a212a2a2f7372632f6d61696e2f2a2a2f7461726765742f0a212a2a2f7372632f746573742f2a2a2f7461726765742f0a0a23232320535453202323230a2e6170745f67656e6572617465640a2e636c617373706174680a2e666163746f7279706174680a2e70726f6a6563740a2e73657474696e67730a2e737072696e674265616e730a2e737473342d63616368650a0a23232320496e74656c6c694a2049444541202323230a2e696465610a2a2e6977730a2a2e696d6c0a2a2e6970720a0a232323204e65744265616e73202323230a2f6e6270726f6a6563742f707269766174652f0a2f6e626275696c642f0a2f646973742f0a2f6e62646973742f0a2f2e6e622d677261646c652f0a6275696c642f0a212a2a2f7372632f6d61696e2f2a2a2f6275696c642f0a212a2a2f7372632f746573742f2a2a2f6275696c642f0a0a23232320565320436f6465202323230a2e7673636f64652f0a, 'EnAttente', 'Example message', 'pending', 'Developpement', 'Example Demande', 'NonUrgent', b'0', 1, '2023-06-16 18:15:46', 'C:\\Users\\issam\\Desktop\\BackendNew\\Chabiba_Support/uploads/.gitignore');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id_employee` bigint(20) NOT NULL,
  `cin` varchar(255) NOT NULL,
  `id_personne` bigint(20) DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id_employee`, `cin`, `id_personne`, `speciality`) VALUES
(2, '1234567890', 3, 'Java Developer'),
(10, '7777777777', 19, 'Database Administrator'),
(12, '8888888888', 21, 'DevOps Engineer'),
(13, '9999999999', 22, 'Quality Assurance Engineer'),
(14, '1111111111', 23, 'Network Administrator'),
(15, '2222222222', 24, 'Software Engineer'),
(16, '3333333333', 25, 'Systems Analyst'),
(17, '4444444444', 26, 'Cybersecurity Analyst'),
(18, '5555555555', 27, 'Web Developer'),
(19, '6666666666', 28, 'UI/UX Designer'),
(21, '123456789', 30, 'Software Tester');

-- --------------------------------------------------------

--
-- Table structure for table `personne`
--

CREATE TABLE `personne` (
  `id_personne` bigint(20) NOT NULL,
  `email` varchar(80) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `num_tel` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `profil_picture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id_personne`, `email`, `mot_de_passe`, `nom`, `num_tel`, `prenom`, `role`, `profil_picture`) VALUES
(1, 'client@chabiba.com', '$2a$10$ZqbirDLxW8dRCWEZ0w6Mue3uvn9e8dkEkIFedYfoXTqZLaHh4LAqS', 'client', '0632728332', 'client', 'client', 'http://localhost:8080/personnes/image\\az.png'),
(3, 'johndoe@example.com', '$2a$10$CxYlIkKViCWWzm98WzQ57O.tCiEtEShuFM6BnUCN4ORS5j.Tfhx0O', 'John', '123456789', 'Doe', 'Technicien', 'http://localhost:8080/personnes/image\\khar.png'),
(19, 'alexrobinson@example.com', '$2a$10$xT7ksIAQJ533EH8aH9oa5uvv3iTGsm2mD80yKodf09VLG3Ib1.wq.', 'Alex', '777777777', 'Robinson', 'Technicien', 'http://localhost:8080/personnes/image\\av2.png'),
(21, 'emilytaylor@example.com', '$2a$10$23o3olKBPBOW/bReL/e8c.d2QDVvkI5s/sFz3N3JEgH0F2Axo1x7.', 'Emily', '888888888', 'Taylor', 'Technicien', 'http://localhost:8080/personnes/image\\av1.png'),
(22, 'michaelanderson@example.com', '$2a$10$dhtQUEnW9vKJiYizHyNWi.6zozsmTfiWF0gYjcZ.jFszHltdgrT6y', 'Michael', '999999999', 'Anderson', 'Technicien', 'http://localhost:8080/personnes/image\\av4.png'),
(23, 'sophiajohnson@example.com', '$2a$10$LTDlEyR00xdAeGfyL.7esenr.Oa0jh5AMPmiPV4A0qlb/ITItqNMq', 'Sophia', '111111111', 'Johnson', 'Technicien', 'http://localhost:8080/personnes/image\\av8.png'),
(24, 'oliverdavis@example.com', '$2a$10$dObNbxmGSWmAUss/e7pEs.mKmOXtPW0CXhPMJZmjntuQ.n9rQne.m', 'Oliver', '222222222', 'Davis', 'Technicien', 'http://localhost:8080/personnes/image\\av6.png'),
(25, 'emmaclark@example.com', '$2a$10$r/dV6SOX3Bh10Mxkn/nqtuk4MZWDcStyaBxahNnuI0Q/K3PQhHChO', 'Emma', '333333333', 'Clark', 'Technicien', 'http://localhost:8080/personnes/image\\mry.png'),
(26, 'williamharris@example.com', '$2a$10$LgR34ZNWYiG9rWvi1Ph8jOJ3N5m.JpgjVWpO5DPQh7Gi7iDkw5Y.G', 'William', '444444444', 'Harris', 'Technicien', 'http://localhost:8080/personnes/image\\ach.png'),
(27, 'avamartin@example.com', '$2a$10$8l1Q28Zzzi6VQe0uZcWlxeaDVIA.1ErCTzYit5AoQEw1hksWBdyrO', 'Ava', '555555555', 'Martin', 'Technicien', 'http://localhost:8080/personnes/image\\av3.png'),
(28, 'jamesthompson@example.com', '$2a$10$4krVidMnX8cxEfFq76EkH.aC/4sTlYOfUCKM9zx6UeDYUReHn5jJi', 'James', '666666666', 'Thompson', 'Technicien', 'http://localhost:8080/personnes/image\\issam.png'),
(30, 'isabellawalker@example.com', '$2a$10$U.vLL8TLvR4N2ixh7GRaIu5Q.RfSOv8PlK0r2liXCDaPiV8lmvqnK', 'Isabella', '123456789', 'Walker', 'Technicien', 'http://localhost:8080/personnes/image\\iw.png');

-- --------------------------------------------------------

--
-- Table structure for table `rapport`
--

CREATE TABLE `rapport` (
  `id_rapport` bigint(20) NOT NULL,
  `contenu` varchar(150) NOT NULL,
  `date` date NOT NULL,
  `documentr` longblob DEFAULT NULL,
  `id_demande` bigint(20) DEFAULT NULL,
  `id_employee` bigint(20) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rapport`
--

INSERT INTO `rapport` (`id_rapport`, `contenu`, `date`, `documentr`, `id_demande`, `id_employee`, `file_path`) VALUES
(3, 'This is the content of the rapport.', '2023-06-13', '', 4, 18, NULL),
(4, 'This is the content of the rapport.', '2023-06-13', 0x76657273696f6e3a2027332e37270d0a0d0a73657276696365733a0d0a20207461667369722d6261636b3a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f746166737369720d0a20202020726573746172743a20616c776179730d0a20202020706f7274733a0d0a2020202020202d2022383038303a38303830220d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d20737072696e672e64617461736f757263652e75726c3d6a6462633a6d7973716c3a2f2f64623a333330362f746166737369720d0a20202020646570656e64735f6f6e3a0d0a2020202020202d2064620d0a0d0a20207461667369722d66726f6e743a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f66726f6e74456e640d0a20202020706f7274733a0d0a2020202020202d2022343230303a3830220d0a0d0a202064623a0d0a20202020696d6167653a206d7973716c0d0a20202020726573746172743a20616c776179730d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d204d5953514c5f524f4f545f50415353574f52443d726f6f740d0a2020202020202d204d5953514c5f44415441424153453d746166737369720d0a20202020706f7274733a0d0a2020202020202d2022333330363a33333036220d0a20202020766f6c756d65733a0d0a2020202020202d202e2f646174612e73716c3a2f646f636b65722d656e747279706f696e742d696e697464622e642f646174612e73716c0d0a0d0a2020736f6c723a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f736f6c720d0a20202020706f7274733a0d0a2020202020202d2022383938333a38393833220d0a20202020636f6d6d616e643a2062617368202d6320222f6f70742f736f6c722f62696e2f736f6c7220737461727420262620736c656570203130202626202f6f70742f736f6c722f62696e2f736f6c72206372656174655f636f7265202d632074616661736972207c7c2074727565202626207461696c202d66202f6465762f6e756c6c220d0a, 6, 16, 'C:\\Users\\issam\\Desktop\\BackendNew\\uploadsdocker-compose.yml'),
(5, 'This is the content of the rapport.', '2023-06-13', 0x76657273696f6e3a2027332e37270d0a0d0a73657276696365733a0d0a20207461667369722d6261636b3a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f746166737369720d0a20202020726573746172743a20616c776179730d0a20202020706f7274733a0d0a2020202020202d2022383038303a38303830220d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d20737072696e672e64617461736f757263652e75726c3d6a6462633a6d7973716c3a2f2f64623a333330362f746166737369720d0a20202020646570656e64735f6f6e3a0d0a2020202020202d2064620d0a0d0a20207461667369722d66726f6e743a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f66726f6e74456e640d0a20202020706f7274733a0d0a2020202020202d2022343230303a3830220d0a0d0a202064623a0d0a20202020696d6167653a206d7973716c0d0a20202020726573746172743a20616c776179730d0a20202020656e7669726f6e6d656e743a0d0a2020202020202d204d5953514c5f524f4f545f50415353574f52443d726f6f740d0a2020202020202d204d5953514c5f44415441424153453d746166737369720d0a20202020706f7274733a0d0a2020202020202d2022333330363a33333036220d0a20202020766f6c756d65733a0d0a2020202020202d202e2f646174612e73716c3a2f646f636b65722d656e747279706f696e742d696e697464622e642f646174612e73716c0d0a0d0a2020736f6c723a0d0a202020206275696c643a0d0a202020202020636f6e746578743a202e2f736f6c720d0a20202020706f7274733a0d0a2020202020202d2022383938333a38393833220d0a20202020636f6d6d616e643a2062617368202d6320222f6f70742f736f6c722f62696e2f736f6c7220737461727420262620736c656570203130202626202f6f70742f736f6c722f62696e2f736f6c72206372656174655f636f7265202d632074616661736972207c7c2074727565202626207461696c202d66202f6465762f6e756c6c220d0a, 4, 17, 'C:/Users/issam/Desktop/BackendNew/uploads/docker-compose.yml');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `affectation`
--
ALTER TABLE `affectation`
  ADD PRIMARY KEY (`id_affectation`),
  ADD KEY `FK3uraxwe84ao7kft6xcekbhi45` (`id_demande`),
  ADD KEY `FKa7kp9w2h4jkh099p2hc7tvxl9` (`id_employee`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`),
  ADD KEY `FKr4qk0sy5ju8g8f52rf73dn7qg` (`id_personne`);

--
-- Indexes for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_commentaire`),
  ADD KEY `FKo6xalwd6fqdg64h5exjjfu4ej` (`id_client`),
  ADD KEY `FKd1sgx9l7vu6iv94ahb63hgc9j` (`id_demande`);

--
-- Indexes for table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id_demande`),
  ADD KEY `FKi8poo5mr8r46mf3y7vmb97ryw` (`id_client`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id_employee`),
  ADD UNIQUE KEY `UK_qtfor7d5mkkf26elj9m0amnmv` (`cin`),
  ADD KEY `FKgtsqg5i8jtmb3gcnnhvp8awo7` (`id_personne`);

--
-- Indexes for table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id_personne`),
  ADD UNIQUE KEY `UK_lif11ug7pqkdimuk0beonbfng` (`email`);

--
-- Indexes for table `rapport`
--
ALTER TABLE `rapport`
  ADD PRIMARY KEY (`id_rapport`),
  ADD KEY `FK5nvs2ntsyj6xfk5dr59je3wro` (`id_demande`),
  ADD KEY `FKsu66w81j0yigm40jcreakxud9` (`id_employee`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `affectation`
--
ALTER TABLE `affectation`
  MODIFY `id_affectation` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_commentaire` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `demande`
--
ALTER TABLE `demande`
  MODIFY `id_demande` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id_employee` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `personne`
--
ALTER TABLE `personne`
  MODIFY `id_personne` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `rapport`
--
ALTER TABLE `rapport`
  MODIFY `id_rapport` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `affectation`
--
ALTER TABLE `affectation`
  ADD CONSTRAINT `FK3uraxwe84ao7kft6xcekbhi45` FOREIGN KEY (`id_demande`) REFERENCES `demande` (`id_demande`),
  ADD CONSTRAINT `FKa7kp9w2h4jkh099p2hc7tvxl9` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`);

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKr4qk0sy5ju8g8f52rf73dn7qg` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`);

--
-- Constraints for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FKd1sgx9l7vu6iv94ahb63hgc9j` FOREIGN KEY (`id_demande`) REFERENCES `demande` (`id_demande`),
  ADD CONSTRAINT `FKo6xalwd6fqdg64h5exjjfu4ej` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Constraints for table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `FKi8poo5mr8r46mf3y7vmb97ryw` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FKgtsqg5i8jtmb3gcnnhvp8awo7` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`);

--
-- Constraints for table `rapport`
--
ALTER TABLE `rapport`
  ADD CONSTRAINT `FK5nvs2ntsyj6xfk5dr59je3wro` FOREIGN KEY (`id_demande`) REFERENCES `demande` (`id_demande`),
  ADD CONSTRAINT `FKsu66w81j0yigm40jcreakxud9` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
