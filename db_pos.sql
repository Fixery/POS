-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Waktu pembuatan: 02. Juli 2015 jam 20:29
-- Versi Server: 5.1.41
-- Versi PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_pos`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_barang`
--

CREATE TABLE IF NOT EXISTS `tb_barang` (
  `id_produk` int(20) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `harga_produk` varchar(13) NOT NULL,
  `stock` int(10) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `toko` varchar(50) NOT NULL,
  `Harga_A` int(11) NOT NULL,
  `Harga_B` int(11) NOT NULL,
  `Harga_C` int(11) NOT NULL,
  `Harga_D` int(11) NOT NULL,
  `Harga_R` int(11) NOT NULL,
  `Harga_P` int(11) NOT NULL,
  PRIMARY KEY (`id_produk`),
  KEY `nama` (`nama`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data untuk tabel `tb_barang`
--

INSERT INTO `tb_barang` (`id_produk`, `nama`, `harga_produk`, `stock`, `tgl_masuk`, `toko`, `Harga_A`, `Harga_B`, `Harga_C`, `Harga_D`, `Harga_R`, `Harga_P`) VALUES
(1, 'GM Imprezza', '100', 20, '2015-06-25', 'Imam Bonjol', 200, 300000, 350000, 400000, 0, 0),
(2, 'KYT', '500000', 40, '2015-06-19', 'Imam Bonjol', 600000, 650000, 700000, 750000, 800000, 0),
(3, 'NHK', '90000', 50, '2015-06-19', 'Kartini', 95000, 200000, 300000, 350000, 400000, 0),
(4, 'GM Venom', '200000', 200, '2015-06-24', 'Kartini', 300000, 0, 0, 0, 0, 0),
(5, 'GM Imprezza', '200000', 10, '2015-06-25', 'Kartini', 250000, 300000, 0, 0, 0, 0),
(6, 'GM Baja', '300000', 100, '2015-06-25', 'Kartini', 350000, 0, 0, 0, 0, 0),
(7, 'GM Imprezza', '200000', 200, '2015-06-26', 'Mobil Box', 250000, 0, 0, 0, 0, 0),
(8, 'GM Imprezza1', '800000', 90, '2015-06-26', 'Imam Bonjol', 89000, 0, 0, 0, 0, 0),
(9, 'GM Imprezza1', '800000', 90, '2015-06-26', 'Kartini', 1000000, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_detail_pembelian`
--

CREATE TABLE IF NOT EXISTS `tb_detail_pembelian` (
  `id_detail_pembelian` int(13) NOT NULL AUTO_INCREMENT,
  `id_produk` int(20) NOT NULL,
  `nama_produk` varchar(50) NOT NULL,
  `kuantitas` int(3) NOT NULL,
  `harga` varchar(13) NOT NULL,
  `sub_total` varchar(13) NOT NULL,
  `id_pembelian` varchar(13) NOT NULL,
  `Stock_Awal` int(20) NOT NULL,
  `Jumlah_Stock` int(20) NOT NULL,
  `Tanggal` date NOT NULL,
  `Toko` varchar(20) NOT NULL,
  PRIMARY KEY (`id_detail_pembelian`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=203 ;

--
-- Dumping data untuk tabel `tb_detail_pembelian`
--


-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_detail_penjualan`
--

CREATE TABLE IF NOT EXISTS `tb_detail_penjualan` (
  `id_detail_penjualan` int(3) NOT NULL AUTO_INCREMENT,
  `id_produk` int(20) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `kuantitas` varchar(8) NOT NULL,
  `harga` varchar(30) NOT NULL,
  `sub_total` varchar(30) NOT NULL,
  `id_penjualan` varchar(30) NOT NULL,
  `Stock_Awal` int(20) NOT NULL,
  `Stock_Akhir` int(20) NOT NULL,
  `Tanggal` date NOT NULL,
  `Toko` varchar(20) NOT NULL,
  `Diskon` int(20) NOT NULL,
  PRIMARY KEY (`id_detail_penjualan`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=243 ;

--
-- Dumping data untuk tabel `tb_detail_penjualan`
--

INSERT INTO `tb_detail_penjualan` (`id_detail_penjualan`, `id_produk`, `nama_barang`, `kuantitas`, `harga`, `sub_total`, `id_penjualan`, `Stock_Awal`, `Stock_Akhir`, `Tanggal`, `Toko`, `Diskon`) VALUES
(242, 1, 'GM Imprezza', '1', '200', '200', 'FKT 00002', 24, 23, '2015-07-02', 'Imam Bonjol', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_keuntungan`
--

CREATE TABLE IF NOT EXISTS `tb_keuntungan` (
  `Bulan` int(2) NOT NULL,
  `untung_kotor` int(50) NOT NULL,
  `untung_bersih` int(50) NOT NULL,
  `Tahun` int(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_keuntungan`
--

INSERT INTO `tb_keuntungan` (`Bulan`, `untung_kotor`, `untung_bersih`, `Tahun`) VALUES
(7, 100, 50, 2015);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_menu`
--

CREATE TABLE IF NOT EXISTS `tb_menu` (
  `id_menu` int(3) NOT NULL AUTO_INCREMENT,
  `menu` varchar(65) NOT NULL,
  `aksi` varchar(5) NOT NULL,
  `pass` varchar(13) NOT NULL,
  PRIMARY KEY (`id_menu`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data untuk tabel `tb_menu`
--

INSERT INTO `tb_menu` (`id_menu`, `menu`, `aksi`, `pass`) VALUES
(1, 'Modul_Input_Barang', 'true', ''),
(2, 'Modul_Input_Pelanggan', 'true', ''),
(3, 'Modul_Penjualan', 'true', ''),
(4, 'Modul_Pembelian', 'true', ''),
(5, 'Laporan_Penjualan_Per_Periode', 'true', ''),
(6, 'Laporan_Pembelian_Per_Periode', 'true', ''),
(7, 'Laporan_Invetory', 'true', ''),
(8, 'Laporan_Data_Pelanggan', 'true', ''),
(9, 'Modul_Import_Database_From_Ms.Excell', 'true', ''),
(10, 'Modul_Setting_Password', 'true', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pelanggan`
--

CREATE TABLE IF NOT EXISTS `tb_pelanggan` (
  `nama_plg` varchar(50) NOT NULL,
  `tlp` varchar(13) NOT NULL,
  `alamat` text NOT NULL,
  `kategori` varchar(3) NOT NULL,
  UNIQUE KEY `nama_plg` (`nama_plg`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pelanggan`
--

INSERT INTO `tb_pelanggan` (`nama_plg`, `tlp`, `alamat`, `kategori`) VALUES
('Beni', '0821392139', 'Jakarta Selatan', 'B'),
('Doni', '082131992139', 'Lampung', 'A'),
('Admin', '01230120', '21031203120	sadas', 'P');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pembelian`
--

CREATE TABLE IF NOT EXISTS `tb_pembelian` (
  `Id_pembelian` varchar(30) NOT NULL,
  `tanggal` date NOT NULL,
  `total` varchar(13) NOT NULL,
  `Nama_Toko` varchar(15) NOT NULL,
  `id_user` varchar(13) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pembelian`
--


-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pengeluaran`
--

CREATE TABLE IF NOT EXISTS `tb_pengeluaran` (
  `id_pengeluaran` varchar(10) NOT NULL,
  `Tanggal` date NOT NULL,
  `nama_pengeluaran` varchar(50) NOT NULL,
  `Pengeluaran` int(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pengeluaran`
--

INSERT INTO `tb_pengeluaran` (`id_pengeluaran`, `Tanggal`, `nama_pengeluaran`, `Pengeluaran`) VALUES
('PE001', '2015-07-02', 'Listrik', 50);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_penjualan`
--

CREATE TABLE IF NOT EXISTS `tb_penjualan` (
  `Id_Penjualan` varchar(30) NOT NULL,
  `Tgl_jual` date NOT NULL,
  `Id_Pelanggan` varchar(30) NOT NULL,
  `Nama_Pelanggan` varchar(50) NOT NULL,
  `Toko` varchar(20) NOT NULL,
  `Kategori` varchar(3) NOT NULL,
  `Bayar` varchar(23) NOT NULL,
  `Total` varchar(23) NOT NULL,
  `Kembali` varchar(23) NOT NULL,
  PRIMARY KEY (`Id_Penjualan`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_penjualan`
--

INSERT INTO `tb_penjualan` (`Id_Penjualan`, `Tgl_jual`, `Id_Pelanggan`, `Nama_Pelanggan`, `Toko`, `Kategori`, `Bayar`, `Total`, `Kembali`) VALUES
('FKT 00001', '2015-07-02', 'null', 'doni', 'Imam Bonjol', 'A', '10', '10', '0'),
('FKT 00002', '2015-07-02', 'null', 'doni', 'Imam Bonjol', 'A', '200', '200', '0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pindah`
--

CREATE TABLE IF NOT EXISTS `tb_pindah` (
  `id_pindah` int(20) NOT NULL AUTO_INCREMENT,
  `id_produk` int(20) NOT NULL,
  `tanggal` date NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `kuantitas` int(20) NOT NULL,
  `dari_toko` varchar(20) NOT NULL,
  `ke_toko` varchar(20) NOT NULL,
  PRIMARY KEY (`id_pindah`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data untuk tabel `tb_pindah`
--

INSERT INTO `tb_pindah` (`id_pindah`, `id_produk`, `tanggal`, `nama_barang`, `kuantitas`, `dari_toko`, `ke_toko`) VALUES
(12, 5, '2015-07-03', 'GM Imprezza', 10, 'Kartini', 'Imam Bonjol');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE IF NOT EXISTS `tb_user` (
  `id_user` int(3) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `pass` varchar(13) NOT NULL,
  `nama_lengkap` varchar(50) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `user_name`, `pass`, `nama_lengkap`) VALUES
(1, 'Admin', '888', 'Administrator');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
