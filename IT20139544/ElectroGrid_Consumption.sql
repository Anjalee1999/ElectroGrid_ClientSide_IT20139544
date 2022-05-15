-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.22-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table electrogridclient.consumption
CREATE TABLE IF NOT EXISTS `consumption` (
  `consumptionID` int(10) NOT NULL,
  `customerID` varchar(50) NOT NULL DEFAULT '',
  `tax` varchar(50) NOT NULL DEFAULT '',
  `presentReading` varchar(50) NOT NULL DEFAULT '',
  `previousReading` varchar(50) NOT NULL DEFAULT '',
  `consumptionUnit` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`consumptionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogridclient.consumption: ~1 rows (approximately)
/*!40000 ALTER TABLE `consumption` DISABLE KEYS */;
INSERT INTO `consumption` (`consumptionID`, `customerID`, `tax`, `presentReading`, `previousReading`, `consumptionUnit`) VALUES
	(1, '1', '34', '134', '23', '5'),
	(2, '2', '35', '156', '112', '15');
/*!40000 ALTER TABLE `consumption` ENABLE KEYS */;

-- Dumping structure for table electrogridclient.logins
CREATE TABLE IF NOT EXISTS `logins` (
  `log_uname` varchar(50) NOT NULL,
  `log_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogridclient.logins: ~0 rows (approximately)
/*!40000 ALTER TABLE `logins` DISABLE KEYS */;
INSERT INTO `logins` (`log_uname`, `log_password`) VALUES
	('sachin', '123'),
	('anjalee', '123');
/*!40000 ALTER TABLE `logins` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
