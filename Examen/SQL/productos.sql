-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 23, 2021 at 06:01 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `examen`
--

-- --------------------------------------------------------

--
-- Table structure for table `productos`
--

CREATE TABLE `productos` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nombre` text NOT NULL,
  `marca` text NOT NULL,
  `categoriaID` bigint(20) UNSIGNED NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `marca`, `categoriaID`, `cantidad`, `precio`) VALUES
(1, 'Agua embotellada', 'Epura', 2, 50, 14.99),
(2, 'Juego de manzana', 'El Valle', 2, 35, 12.99),
(3, 'Red Cola', 'Red Cola', 2, 15, 23.5),
(4, 'Agua mineral', 'Bonafont', 2, 27, 18.99),
(9, 'Chips sal de mar', 'Barcel', 1, 67, 35.5),
(10, 'Rancheritos', 'Sabritas', 1, 45, 35),
(11, 'Toluqueñas', 'Toluqueñas', 1, 22, 15),
(12, 'Takis fuego', 'Barcel', 1, 21, 18),
(13, 'Manzana', 'Sin marca', 3, 10, 9.99),
(14, 'Plátano', 'Sin marca', 3, 11, 12.5),
(15, 'Naranja', 'Sin marca', 3, 29, 9.99),
(16, 'Uvas', 'Sin marca', 3, 5, 25.3),
(17, 'Leche', 'Alpura', 4, 41, 17),
(18, 'Yogurt', 'Lala', 4, 16, 14),
(19, '\r\nQuesillo', 'Sin marca', 4, 7, 20),
(20, 'Queso manchego', 'Fud', 4, 30, 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `categoria-foreign` (`categoriaID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `productos`
--
ALTER TABLE `productos`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `categoria-foreign` FOREIGN KEY (`categoriaID`) REFERENCES `categorias` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
