/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entitas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class manager {
Connection con = null;
Statement sta = null;

    public manager() {
       try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/db_pos","root","");
            sta=con.createStatement();
            //JOptionPane.showMessageDialog(null, "Berhasil Koneksi");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

     //===================================================== DATA BARANG ========================================
     public void simpanBarangA(entitas_produk A){
        try {
            String simpan = "INSERT INTO tb_barang VALUES(null,'"+A.getNama()+"','"+A.getHarga_produk()+"','"+A.getStock()+"','"+A.getTgl_masuk()+"','"+A.getToko()+"','"+A.getHarga_a()+"','0','0','0','0','0')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

     public void ubahBarangB(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET Harga_B='"+A.getHarga_b()+"' WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Harga Kategori B Pada Produk dengan Nsms "+A.getNama()+" Di Tambah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahBarangC(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET Harga_C='"+A.getHarga_c()+"' WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Harga Kategori C Pada Produk Bernama "+A.getNama()+" Di Tambah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahBarangD(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET Harga_D='"+A.getHarga_d()+"' WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Harga Kategori D Pada Produk Bernama "+A.getNama()+" Di Tambah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahBarangR(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET Harga_R='"+A.getHarga_r()+"' WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Harga Kategori R Pada Produk Bernama "+A.getNama()+" Di Tambah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahBarangP(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET Harga_P='"+A.getHarga_p()+"' WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Harga Kategori P Pada Produk Bernama "+A.getNama()+" Di Tambah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahDataBarangA(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET nama='"+A.getNama()+"',Harga_A='"+A.getHarga_a()+"',harga_produk='"+A.getHarga_produk()+"'"
                    + ",stock='"+A.getStock()+"',tgl_masuk='"+A.getTgl_masuk()+"',toko='"+A.getToko()+"'"
                    + " WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Edit Pada Produk Bernama "+A.getNama()+" Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahDataBarangB(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET nama='"+A.getNama()+"',Harga_B='"+A.getHarga_b()+"',harga_produk='"+A.getHarga_produk()+"'"
                    + ",stock='"+A.getStock()+"',tgl_masuk='"+A.getTgl_masuk()+"',toko='"+A.getToko()+"'"
                    + " WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Edit Pada Produk Bernama "+A.getNama()+" Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahDataBarangC(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET nama='"+A.getNama()+"',Harga_C='"+A.getHarga_c()+"',harga_produk='"+A.getHarga_produk()+"'"
                    + ",stock='"+A.getStock()+"',tgl_masuk='"+A.getTgl_masuk()+"',toko='"+A.getToko()+"'"
                    + " WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Edit Pada Produk Bernama "+A.getNama()+" Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahDataBarangD(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET nama='"+A.getNama()+"',Harga_D='"+A.getHarga_d()+"',harga_produk='"+A.getHarga_produk()+"'"
                    + ",stock='"+A.getStock()+"',tgl_masuk='"+A.getTgl_masuk()+"',toko='"+A.getToko()+"'"
                    + " WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Edit Pada Produk Bernama "+A.getNama()+" Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahDataBarangR(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET nama='"+A.getNama()+"',Harga_R='"+A.getHarga_r()+"',harga_produk='"+A.getHarga_produk()+"'"
                    + ",stock='"+A.getStock()+"',tgl_masuk='"+A.getTgl_masuk()+"',toko='"+A.getToko()+"'"
                    + " WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Edit Pada Produk Bernama "+A.getNama()+" Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void ubahDataBarangP(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET nama='"+A.getNama()+"',Harga_P='"+A.getHarga_p()+"',harga_produk='"+A.getHarga_produk()+"'"
                    + ",stock='"+A.getStock()+"',tgl_masuk='"+A.getTgl_masuk()+"',toko='"+A.getToko()+"'"
                    + " WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Edit Pada Produk Bernama "+A.getNama()+" Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void hapusBarang(entitas_produk A){
         try {
             String hapus = "DELETE FROM tb_barang WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
             sta =  con.createStatement();
             int UA = sta.executeUpdate(hapus);
            JOptionPane.showMessageDialog(null, "Data Produk Bernama "+A.getNama()+" di Hapus");
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Menghapus Data", "POS GM",JOptionPane.ERROR_MESSAGE);
         }
     }

     //====================================== ADD to CART - DATA PEMBELIAN ======================================================
      public void ubahStock(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET stock=stock+"+A.getStock()+" WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Data Produk Bernama "+A.getNama()+" Ditambah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

      public void simpanKeranjang(entitas_detail_pembelian A){
          try {
            String simpan = "INSERT INTO tb_detail_pembelian VALUES(null,'"+A.getId_produk()+"','"+A.getNama()+"','"+A.getKuantitas()+
                    "','"+A.getHarga()+"','"+A.getSub_total()+"','"+A.getId_pembelian()+"','"+A.getStok()+"','"+A.getSisa()+"','"+A.getTanggal()+"','"+A.getToko()+"')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
      }


      public void hapusKeranjangBeli(entitas_detail_pembelian A){
           try {
            String simpan = "DELETE FROM tb_detail_pembelian WHERE id_detail_pembelian='"+A.getId_detail()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Produk Pembelian di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
      }
      //================================================== DATA PELANGGAN =============================================
      public void simpanPelanggan(entitas_pelanggan A){
           try {
            String simpan = "INSERT INTO tb_pelanggan VALUES('"+A.getNama_plg()+
                    "','"+A.getTlp()+"','"+A.getAlamat()+"','"+A.getKategori()+"')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
      }


      public void ubahPelanggan(entitas_pelanggan A){
          try {
            String simpan = "UPDATE tb_pelanggan SET tlp='"+A.getTlp()+"',alamat='"+A.getAlamat()+"',kategori='"+A.getKategori()+
                    "' WHERE nama_plg='"+A.getNama_plg()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Pelanggan Berhasil di Ubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
      }

      public void hapusPelanggan(entitas_pelanggan A){
          try {
            String simpan = "DELETE FROM tb_pelanggan WHERE nama_plg='"+A.getNama_plg()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Pelanggan Berhasil di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
      }
     //================= DATA DETAIL PEMBELIAN =============================================
     /**public void simpanDetailPembelian(entitas_detail_pembelian A){
        try {
            String simpan = "INSERT INTO tb_detail_pembelian VALUES('"+A.getId_detail_pembelian()+
                    "','"+A.getId_produk()+"','"+A.getnama+"')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

     public void ubahBarang(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET nama='"+A.getNama()+
                    "',harga_produk='"+A.getHarga_produk()+
                    "',stock='"+A.getStock()+"',tgl_masuk='"+A.getTgl_masuk()+
                    "' WHERE id_produk='"+A.getId_produk()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Data Produk dengan ID "+A.getId_produk()+" diUbah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public void hapusBarang(entitas_produk A){
         try {
             String hapus = "DELETE FROM tb_barang WHERE id_produk='"+A.getId_produk()+"'";
             sta =  con.createStatement();
             int UA = sta.executeUpdate(hapus);
            JOptionPane.showMessageDialog(null, "Data Produk dengan ID "+A.getId_produk()+" di Hapus");
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Menghapus Data", "POS GM",JOptionPane.ERROR_MESSAGE);
         }
     }*/

      //=============================================== DATA PEMEBALIAN =====================================================
      public void simpanPembelian(entitas_pembelian A){
           try {
            String simpan = "INSERT INTO tb_pembelian VALUES('"+A.getId_pembelian()+
                    "','"+A.getTanggal()+"','"+A.getTotal()+"','"+A.getToko()+"','"+A.getId_user()+"')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
      }
// ==================================================== Data untung bersih ======================================
      //public void hapusUtgBersih
      //=============================================== Add to Cart Penjualan ================================================
      public void simpanKeranjangJual(entitas_detail_penjualan A){
          try {
            String simpan = "INSERT INTO tb_detail_penjualan VALUES(null,'"+A.getId_produk()+"','"+A.getNama_barang()+"','"+A.getKuantitas()+"','"+A.getHarga()+"','"+A.getSub_total()+"','"+A.getId_penjualan()+"','"+A.getAwal()+"','"+A.getSisa()+"','"+A.getTanggal()+"','"+A.getToko()+"','"+A.getDiskon()+"')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            //JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }

      }

      public void kurangiStock(entitas_produk A){
        try {
            String ubah = "UPDATE tb_barang SET stock=stock-"+A.getStock()+" WHERE nama='"+A.getNama()+"' AND toko='"+A.getToko()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            //JOptionPane.showMessageDialog(null, "Data Produk dengan ID "+A.getId_produk()+" diUbah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
}

      public void hapusKeranjangJual(entitas_detail_penjualan A){
          try {
            String simpan = "DELETE FROM tb_detail_penjualan WHERE id_detail_penjualan ='"+A.getId_detail()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Produk Penjualan di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
      }

      //================================================= DATA PENJUALAN ===========================================================
      public void simpanPenjualan(entitas_penjualan A){
          try {
            String simpan = "INSERT INTO tb_penjualan VALUES ('"+A.getId_Penjualan()+"', '"+A.getTgl_jual()+"', '"+A.getId_Pelanggan()+"', '"+A.getNama_Pelanggan()+"', '"+A.getToko()+"', '"+A.getKategori()+"', '"+A.getBayar()+"', '"+A.getTotal()+"', '"+A.getKembali()+"')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
}

      //================================================ DATA USER ====================================================
      public void settingUser(entitas_user A){
          try {
            String ubah = "UPDATE tb_user SET user_name='"+A.getUser()+
                    "',pass='"+A.getPass()+"',nama_lengkap='"+A.getNama()+"' WHERE id_user='1'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Setting Login Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Mengubah Data", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
      }

      //============================================ DATA MANAGEMENT MENU =============================================
      public void manageMenu(entitas_manage_menu A){
           try {
            String ubah = "UPDATE tb_menu SET aksi='"+A.getAksi()+"',pass='"+A.getPass()+"' WHERE menu='"+A.getMenu()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Managemen Menu Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Pengesettan Menu", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
      }
       //============================================ DATA Pengeluaran =============================================
      public void updatePeng(entitas_pengeluaran A){
           try {
            String ubah = "UPDATE tb_pengeluaran SET Tanggal='"+A.getTgl()+
                    "',nama_pengeluaran='"+A.getNama()+
                    "',Pengeluaran='"+A.getBiaya()+"' WHERE id_pengeluaran='"+A.getId()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(ubah);
            JOptionPane.showMessageDialog(null, "Update Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Saat Pengesettan Menu", "POS GM",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
      }
      public void simpanPengeluaran(entitas_pengeluaran A){
          try {
            String simpan = "INSERT INTO tb_pengeluaran VALUES ('"+A.getId()+"','"+A.getTgl()+"','"+A.getNama()+"','"+A.getBiaya()+"')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
}
      public void hapusPengeluaran(entitas_pengeluaran A){
          try {
            String hapus = "DELETE FROM tb_pengeluaran WHERE id_pengeluaran ='"+A.getId()+"'";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(hapus);
            JOptionPane.showMessageDialog(null, "Telah Di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
      }
      //============================================ DATA Pengeluaran =============================================
     public void simpanKeuntungan(entitas_untungbersih A){
          try {
            String simpan = "INSERT INTO tb_keuntungan VALUES ('"+A.getPengeluaran()+"','"+A.getUtgktr()+"','"+A.getUtgbrs()+"','"+A.getTahun()+"')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }
     //============================================== DATA Pindah ===============================================
     public void simpanpindah(entitas_pindah A){
          try {
            String simpan = "INSERT INTO tb_pindah VALUES (null,'"+A.getId_produk()+"','"+A.getTanggal()+"','"+A.getNama_barang()+"','"+A.getKuantitas()+"','"+A.getDari_toko()+"','"+A.getKe_toko()+"')";
            sta =  con.createStatement();
            int UA = sta.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }
    
    
}
