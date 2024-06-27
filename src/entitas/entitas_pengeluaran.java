/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entitas;

/**
 *
 * @author Julian Christian
 */
public class entitas_pengeluaran {
 String Tgl,Nama,Biaya, id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBiaya() {
        return Biaya;
    }

    public void setBiaya(String Biaya) {
        this.Biaya = Biaya;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getTgl() {
        return Tgl;
    }

    public void setTgl(String Tgl) {
        this.Tgl = Tgl;
    }
}
