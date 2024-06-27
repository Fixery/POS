/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entitas;

/**
 *
 * @author Julian Christian
 */
public class entitas_pindah {
String id_pindah, id_produk, tanggal, nama_barang, kuantitas, dari_toko, ke_toko;

    public String getDari_toko() {
        return dari_toko;
    }

    public void setDari_toko(String dari_toko) {
        this.dari_toko = dari_toko;
    }

    public String getId_pindah() {
        return id_pindah;
    }

    public void setId_pindah(String id_pindah) {
        this.id_pindah = id_pindah;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getKe_toko() {
        return ke_toko;
    }

    public void setKe_toko(String ke_toko) {
        this.ke_toko = ke_toko;
    }

    public String getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(String kuantitas) {
        this.kuantitas = kuantitas;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

}
