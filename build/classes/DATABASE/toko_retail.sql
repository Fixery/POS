-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 12, 2015 at 12:07 
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `toko_retail`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_detail_pembelian`
--

CREATE TABLE IF NOT EXISTS `t_detail_pembelian` (
  `id_detail_pembelian` varchar(19) NOT NULL,
  `id_produk` varchar(30) NOT NULL,
  `kuantitas` int(11) NOT NULL,
  `harga` decimal(19,2) NOT NULL,
  `sub_total` decimal(19,2) DEFAULT NULL,
  `id_pembelian` varchar(15) NOT NULL,
  PRIMARY KEY (`id_detail_pembelian`),
  KEY `id_produk` (`id_produk`),
  KEY `id_pembelian` (`id_pembelian`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_detail_pembelian`
--

INSERT INTO `t_detail_pembelian` (`id_detail_pembelian`, `id_produk`, `kuantitas`, `harga`, `sub_total`, `id_pembelian`) VALUES
('13972296472241', 'ROKOK01', 23, '9000.00', '207000.00', '1397229647224');

-- --------------------------------------------------------

--
-- Table structure for table `t_detail_penjualan`
--

CREATE TABLE IF NOT EXISTS `t_detail_penjualan` (
  `id_detail_penjualan` varchar(19) NOT NULL,
  `id_produk` varchar(30) NOT NULL,
  `kuantitas` int(11) NOT NULL,
  `harga` decimal(19,2) NOT NULL,
  `sub_total` decimal(19,2) DEFAULT NULL,
  `id_penjualan` varchar(16) NOT NULL,
  PRIMARY KEY (`id_detail_penjualan`),
  KEY `id_produk` (`id_produk`),
  KEY `id_penjualan` (`id_penjualan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_detail_penjualan`
--

INSERT INTO `t_detail_penjualan` (`id_detail_penjualan`, `id_produk`, `kuantitas`, `harga`, `sub_total`, `id_penjualan`) VALUES
('13139456286251', 'SABUN01', 5, '2300.00', '11500.00', '1313945628625'),
('13139456286252', 'ROKOK03', 2, '12000.00', '24000.00', '1313945628625'),
('13972293102371', 'SABUN01', 1, '2300.00', '2300.00', '1397229310237'),
('14260925170361', 'ROKOK03', 1, '12000.00', '12000.00', '1426092517036'),
('14260925170362', 'SABUN02', 3, '2500.00', '7500.00', '1426092517036'),
('14260925170363', 'SABUN01', 3, '2300.00', '6900.00', '1426092517036');

-- --------------------------------------------------------

--
-- Table structure for table `t_pembelian`
--

CREATE TABLE IF NOT EXISTS `t_pembelian` (
  `id_pembelian` varchar(15) NOT NULL,
  `tanggal` datetime NOT NULL,
  `id_supplier` varchar(20) NOT NULL,
  `total` decimal(19,2) NOT NULL,
  `id_user` varchar(15) NOT NULL,
  PRIMARY KEY (`id_pembelian`),
  KEY `id_supplier` (`id_supplier`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_pembelian`
--

INSERT INTO `t_pembelian` (`id_pembelian`, `tanggal`, `id_supplier`, `total`, `id_user`) VALUES
('1397229647224', '2014-04-11 22:21:22', 'TOKO01', '207000.00', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `t_penjualan`
--

CREATE TABLE IF NOT EXISTS `t_penjualan` (
  `id_penjualan` varchar(16) NOT NULL,
  `tanggal` datetime NOT NULL,
  `total` decimal(19,2) NOT NULL,
  `id_user` varchar(15) NOT NULL,
  PRIMARY KEY (`id_penjualan`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_penjualan`
--

INSERT INTO `t_penjualan` (`id_penjualan`, `tanggal`, `total`, `id_user`) VALUES
('1313945628625', '2011-08-21 23:58:54', '35500.00', 'admin'),
('1397229310237', '2014-04-11 22:15:18', '2300.00', 'admin'),
('1426092517036', '2015-03-11 23:50:49', '26400.00', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `t_produk`
--

CREATE TABLE IF NOT EXISTS `t_produk` (
  `id_produk` varchar(30) NOT NULL,
  `harga_jual` decimal(19,2) NOT NULL,
  `harga_pokok` decimal(19,2) NOT NULL,
  `nama` varchar(90) NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`id_produk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_produk`
--

INSERT INTO `t_produk` (`id_produk`, `harga_jual`, `harga_pokok`, `nama`, `stock`) VALUES
('a', '900000.00', '9000.00', 'aa', 90),
('ROKOK01', '11000.00', '9000.00', 'Jarum 16', 73),
('ROKOK02', '8000.00', '8000.00', 'Jarum 12', 50),
('ROKOK03', '12000.00', '11000.00', 'MARLBORO LIGHT', 47),
('SABUN01', '2300.00', '1500.00', 'LUX', 41),
('SABUN02', '2500.00', '1600.00', 'SHINSUI', 47);

-- --------------------------------------------------------

--
-- Table structure for table `t_supplier`
--

CREATE TABLE IF NOT EXISTS `t_supplier` (
  `id_supplier` varchar(20) NOT NULL,
  `nama_supplier` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `telepon` varchar(15) NOT NULL,
  PRIMARY KEY (`id_supplier`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_supplier`
--

INSERT INTO `t_supplier` (`id_supplier`, `nama_supplier`, `alamat`, `telepon`) VALUES
('TOKO01', 'TOKO SEMBARANG', 'SEMARANG', '0243555111'),
('TOKO02', 'TOKO ADA AJA', 'SALATIGA', '0298000111'),
('TOKO03', 'TOKO BARU', 'KUDUS', '029811111');

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE IF NOT EXISTS `t_user` (
  `id_user` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `telepon` varchar(15) NOT NULL,
  `role` enum('KASIR','ADMIN') NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`id_user`, `password`, `nama`, `alamat`, `telepon`, `role`) VALUES
('admin', 'admin', 'yulias kurniawan', 'semarang', '081391550684', 'ADMIN'),
('KASIR01', 'kasir', 'Budi', 'semarang', '024123123', 'KASIR');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_detail_pembelian`
--
ALTER TABLE `t_detail_pembelian`
  ADD CONSTRAINT `t_detail_pembelian_ibfk_1` FOREIGN KEY (`id_produk`) REFERENCES `t_produk` (`id_produk`),
  ADD CONSTRAINT `t_detail_pembelian_ibfk_2` FOREIGN KEY (`id_pembelian`) REFERENCES `t_pembelian` (`id_pembelian`);

--
-- Constraints for table `t_detail_penjualan`
--
ALTER TABLE `t_detail_penjualan`
  ADD CONSTRAINT `t_detail_penjualan_ibfk_1` FOREIGN KEY (`id_produk`) REFERENCES `t_produk` (`id_produk`),
  ADD CONSTRAINT `t_detail_penjualan_ibfk_2` FOREIGN KEY (`id_penjualan`) REFERENCES `t_penjualan` (`id_penjualan`);

--
-- Constraints for table `t_pembelian`
--
ALTER TABLE `t_pembelian`
  ADD CONSTRAINT `t_pembelian_ibfk_2` FOREIGN KEY (`id_supplier`) REFERENCES `t_supplier` (`id_supplier`),
  ADD CONSTRAINT `t_pembelian_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`);

--
-- Constraints for table `t_penjualan`
--
ALTER TABLE `t_penjualan`
  ADD CONSTRAINT `t_penjualan_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
