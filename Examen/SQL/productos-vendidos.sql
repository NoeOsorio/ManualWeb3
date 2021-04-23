-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 23, 2021 at 06:02 PM
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
-- Table structure for table `productos-vendidos`
--

CREATE TABLE `productos-vendidos` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `ventaID` bigint(20) UNSIGNED NOT NULL,
  `productoID` bigint(20) UNSIGNED NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `productos-vendidos`
--

INSERT INTO `productos-vendidos` (`id`, `ventaID`, `productoID`, `cantidad`) VALUES
(1, 5, 13, 1),
(2, 6, 13, 2),
(3, 6, 15, 1),
(4, 6, 14, 4),
(5, 7, 2, 1),
(6, 7, 4, 1),
(7, 7, 3, 2),
(8, 8, 3, 1),
(9, 8, 9, 1),
(10, 9, 3, 1),
(11, 9, 9, 1),
(12, 9, 19, 1),
(13, 9, 17, 1),
(14, 10, 14, 1),
(15, 10, 15, 5),
(16, 11, 1, 10),
(17, 11, 10, 1),
(18, 11, 12, 2),
(19, 11, 11, 1),
(20, 12, 13, 1),
(21, 12, 16, 1),
(22, 12, 14, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `productos-vendidos`
--
ALTER TABLE `productos-vendidos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `vendido-venta` (`ventaID`),
  ADD KEY `vendido-producto` (`productoID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `productos-vendidos`
--
ALTER TABLE `productos-vendidos`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `productos-vendidos`
--
ALTER TABLE `productos-vendidos`
  ADD CONSTRAINT `vendido-producto` FOREIGN KEY (`productoID`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `vendido-venta` FOREIGN KEY (`ventaID`) REFERENCES `ventas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
