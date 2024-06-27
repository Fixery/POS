/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mod_pembelian.java
 *
 * Created on Mar 12, 2015, 12:42:41 PM
 */

package pos;

import entitas.manager;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import org.freixas.jcalendar.JCalendarCombo;
import java.awt.EventQueue;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import pos.mod_logsys;

/**
 *
 * @author User
 */
public class mod_pembelian extends javax.swing.JDialog {
Connection con = null;
Statement sta = null;
String idUser ="", stok="", idpro="";
String data[]=new String[6];
boolean lama, baru;
int sisa=0, qty=0, coba=0, akhir=0;
    /** Creates new form mod_pembelian */
    public mod_pembelian(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/db_pos","root","");
            sta=con.createStatement();
            //JOptionPane.showMessageDialog(null, "Berhasil Koneksi");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        cbTglMasuk.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        kunci();
        tutupTombolSemua();
        btnHapus.setEnabled(false);
        isiIdUser();
        tb_pembelian.setModel(tblModel);
        Tabel(tb_pembelian, new int[]{100,200,100,100,100,150,150});
    }


    void autoNumber(){
		try{
			sta=con.createStatement();
			String query = "SELECT MAX(right(id_pembelian ,4)) AS kode FROM tb_pembelian";
			ResultSet rs = sta.executeQuery(query);
			while(rs.next())
			{
				if(rs.first() == false)
				{
					txt_faktur.setText("SLP 1");
				}
				else
				{
					rs.last();
					int noKirim = rs.getInt(1) + 1;
					String no = String.valueOf(noKirim);
					int noLong = no.length();
					for(int a=0;a<3-noLong;a++)
					{
						no = "00" + no;
					}
					txt_faktur.setText("SLP " + no);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

    /*void cekBarang(){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE id_produk='"+txt_id_produk.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            if(res.next()){
                    //System.out.println("Data Lama BISMILLAH");
                    //JOptionPane.showMessageDialog(rootPane, "Produk dengan kode "+txt_id_produk.getText()+" adalah produk LAMA, \n Sistem hanya akan melakukan penambahan stock");
                    lama=true;
            }/*else{
                JOptionPane.showMessageDialog(rootPane, "Produk dengan kode "+txt_id_produk.getText()+" adalah produk BARU, \n lakukan penambahan produk sekarang");
                baru=true;
                txt_id_produk.setEnabled(true);
                txt_nama.setEnabled(true);
                txt_kuantitas.setEnabled(true);
                txt_harga_beli.setEnabled(true);
            }*/
            /*res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }*/

    void total(){
         try{
            sta=con.createStatement();
            String SQL="SELECT SUM(sub_total) as TOTAL FROM tb_detail_pembelian WHERE id_pembelian='"+txt_faktur.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                    txt_total.setText(res.getString("TOTAL"));
                    lblTotal.setText(res.getString("TOTAL"));
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    void isiIdUser(){
        try{
            sta=con.createStatement();
            String SQL="SELECT id_user  FROM tb_user WHERE user_name ='"+mod_logsys.txtUS.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                 idUser = res.getString("id_user");
                //System.out.print(res.getString("id_user"));
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    void sissa(){
        try{
            sta=con.createStatement();
            String SQL="SELECT stock FROM tb_barang WHERE nama ='"+txt_nama.getText()+"' AND toko='"+txt_toko.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                stok = res.getString("stock");
                label.setText(stok);
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    void id(){
        try{
            sta=con.createStatement();
            String SQL="SELECT id_produk FROM tb_barang WHERE nama ='"+txt_nama.getText()+"' AND toko='"+txt_toko.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                idpro = res.getString("id_produk");
                lblstok1.setText(idpro);
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }


    private javax.swing.table.DefaultTableModel getdeDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String []{"Id Pembelian","Produk","Harga Beli","Stock","Sub Total","Toko"}){
                    boolean[] canEdit=new boolean[]{
                     false,false,false,false,false,false
                    };
            @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                    }
                };
    }

private javax.swing.table.DefaultTableModel tblModel=getdeDefaultTableModel();

public void tabel_kosong(){
       int count=tb_pembelian.getRowCount();
       int x=1;
       while(x<=count){
           tblModel.removeRow(0);
           x=x+1;
       }
   }

private void Tabel(javax.swing.JTable tb, int lebar[]){
        tb.setAutoResizeMode(tb.AUTO_RESIZE_OFF);
        int kolom=tb.getColumnCount();
        for(int i=0;i<kolom;i++){
            javax.swing.table.TableColumn tbc=tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            tb.setRowHeight(17);
        }
    }

private void setDefaultTable() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM `tb_detail_pembelian` WHERE id_pembelian = '"+txt_faktur.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                data[0]=res.getString("id_detail_pembelian");
                //data[1]=res.getString("id_produk");
                data[1]=res.getString("nama_produk");
                data[2]=res.getString("harga");
                data[3]=res.getString("kuantitas");
                data[4]=res.getString("sub_total");
                data[5]=res.getString("Toko");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    void kunci(){
        txt_faktur.setEnabled(false);
        cbTglMasuk.setEnabled(false);
        //txt_id_produk.setEnabled(false);
        //cekBarang.setEnabled(false);
        btnBrowse.setEnabled(false);
        txt_nama.setEnabled(false);
        txt_kuantitas.setEnabled(false);
        txt_harga_beli.setEnabled(false);
        txt_sub_total.setEnabled(false);
        txt_total.setEnabled(false);
    }

    void aktif(){
        txt_faktur.setEnabled(true);
        cbTglMasuk.setEnabled(true);
        //txt_id_produk.setEnabled(true);
        //cekBarang.setEnabled(true);
        btnBrowse.setEnabled(true);
        txt_nama.setEnabled(true);
        txt_kuantitas.setEnabled(true);
        txt_harga_beli.setEnabled(true);
        txt_sub_total.setEnabled(false);
        txt_total.setEnabled(false);
    }


    void bukaKeranjang(){
        //txt_id_produk.setEnabled(true);
        //cekBarang.setEnabled(true);
        btnBrowse.setEnabled(true);
        txt_nama.setEnabled(true);
        txt_kuantitas.setEnabled(true);
        txt_harga_beli.setEnabled(true);
    }

    void bukaTombolSemua(){
        btnSimpan.setEnabled(true);
        btnAdd.setEnabled(true);
        btnHapus.setEnabled(true);
    }

    void tutupTombolSemua(){
        btnSimpan.setEnabled(false);
        btn_cetAk.setEnabled(false);
        btnAdd.setEnabled(false);
//        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
    }

    void bukaTombolKeranjang(){
        btn_cetAk.setEnabled(true);
//        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }
    void  bersih(){
        txt_faktur.setText("");
        //txt_id_produk.setText("");
        txt_nama.setText("");
        txt_kuantitas.setText("");
        txt_harga_beli.setText("");
        txt_sub_total.setText("");
        txt_total.setText("");
        lblTotal.setText("0");
    }

    void bersih2(){
        //txt_id_produk.setText("");
        txt_nama.setText("");
        txt_kuantitas.setText("");
        txt_harga_beli.setText("");
        txt_sub_total.setText("");
        txt_total.setText("");
        lblTotal.setText("0");
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_kuantitas = new javax.swing.JTextField();
        txt_harga_beli = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_faktur = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pembelian = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        btn_cetAk = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        cbTglMasuk = new org.freixas.jcalendar.JCalendarCombo();
        lblTotal = new javax.swing.JLabel();
        lblTotal1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        txt_toko = new javax.swing.JTextField();
        lblkode = new javax.swing.JLabel();
        lblstok = new javax.swing.JLabel();
        txt_sub_total = new javax.swing.JFormattedTextField();
        lblstok1 = new javax.swing.JLabel();
        headernya1 = new panelMakeOver.headernya();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Transaksi Pembelian :."));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel1.setText("Faktur Pembelian");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Produk :."));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel6.setText("Nama");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel7.setText("Kuantitas");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel8.setText("Harga Beli");

        txt_nama.setFont(new java.awt.Font("Tahoma", 0, 14));
        txt_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_namaKeyPressed(evt);
            }
        });

        txt_kuantitas.setFont(new java.awt.Font("Tahoma", 0, 14));
        txt_kuantitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_kuantitasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_kuantitasKeyReleased(evt);
            }
        });

        txt_harga_beli.setFont(new java.awt.Font("Tahoma", 0, 14));
        txt_harga_beli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_harga_beliKeyPressed(evt);
            }
        });

        btnBrowse.setText("Browse...");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(53, 53, 53)
                        .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btnBrowse))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(34, 34, 34)
                        .addComponent(txt_kuantitas, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addComponent(txt_harga_beli, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBrowse)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_harga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel9.setText("Tanggal");

        txt_faktur.setFont(new java.awt.Font("Tahoma", 0, 14));

        tb_pembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_pembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pembelianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_pembelian);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel11.setText("Subtotal");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel12.setText("Total");

        txt_total.setFont(new java.awt.Font("Tahoma", 0, 14));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Simpan Transaksi :."));

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton9.setText("NEW");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnSimpan.setText("SAVE");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton11.setText("CANCEL");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        btn_cetAk.setFont(new java.awt.Font("Tahoma", 1, 11));
        btn_cetAk.setText("PRINT");
        btn_cetAk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetAkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cetAk, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cetAk, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Add to Cart :."));

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapus.setText("DELETE");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        lblTotal.setFont(new java.awt.Font("Times New Roman", 1, 80));
        lblTotal.setText("0");

        lblTotal1.setFont(new java.awt.Font("Times New Roman", 1, 60));
        lblTotal1.setText("Rp.");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel10.setText("Toko ");

        label.setForeground(new java.awt.Color(240, 240, 240));
        label.setText("Stock Awal :");

        txt_toko.setFont(new java.awt.Font("Tahoma", 0, 14));

        lblkode.setForeground(new java.awt.Color(240, 240, 240));
        lblkode.setText("jLabel2");

        lblstok.setForeground(new java.awt.Color(240, 240, 240));
        lblstok.setText("jLabel13");

        txt_sub_total.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
        txt_sub_total.setMinimumSize(new java.awt.Dimension(6, 23));

        lblstok1.setForeground(new java.awt.Color(240, 240, 240));
        lblstok1.setText("jLabel13");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_toko)
                                    .addComponent(cbTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_faktur, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblstok, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblkode))))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_sub_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_total, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                                .addGap(18, 18, 18)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(label))
                            .addComponent(lblstok1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 620, Short.MAX_VALUE)
                        .addComponent(lblTotal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_toko, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_sub_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblkode)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(label))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblstok1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(398, Short.MAX_VALUE)
                .addComponent(lblstok, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
        );

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 23));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("POS GM");

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 18));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Modul Transaksi Pembelian");

        javax.swing.GroupLayout headernya1Layout = new javax.swing.GroupLayout(headernya1);
        headernya1.setLayout(headernya1Layout);
        headernya1Layout.setHorizontalGroup(
            headernya1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headernya1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headernya1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1091, Short.MAX_VALUE))
        );
        headernya1Layout.setVerticalGroup(
            headernya1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headernya1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headernya1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headernya1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if(txt_nama.getText().isEmpty()||txt_kuantitas.getText().isEmpty()||txt_harga_beli.getText().isEmpty()||txt_sub_total.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Data Masih Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            Integer qty = Integer.parseInt(txt_kuantitas.getText());
            //label2.setText(txt_kuantitas.getText());
            Integer awal = Integer.parseInt(label.getText());
            int hitung = awal + qty;
            sisa = hitung;
            
            //detail_beli.setId_produk(txt_id_produk.getText());
            detail_beli.setNama(txt_nama.getText());
            detail_beli.setKuantitas(txt_kuantitas.getText());
            detail_beli.setHarga(txt_harga_beli.getText());
            detail_beli.setSub_total(txt_sub_total.getText());
            detail_beli.setId_pembelian(txt_faktur.getText());
            detail_beli.setId_produk(lblstok1.getText());
            detail_beli.setStok(Integer.toString(awal));
            detail_beli.setSisa(Integer.toString(sisa));
            detail_beli.setTanggal(cbTglMasuk.getSelectedItem().toString());
            detail_beli.setToko(txt_toko.getText());
            ma.simpanKeranjang(detail_beli);


            //if(baru==true){
                //data_pro.setId_produk(txt_id_produk.getText());
                //data_pro.setNama(txt_nama.getText());
                //data_pro.setHarga_produk(txt_harga_beli.getText());
                //data_pro.setStock(txt_kuantitas.getText());
                //data_pro.setTgl_masuk(cbTglMasuk.getSelectedItem().toString());
              ///  ma.simpanBarang(data_pro);
            //}else if(lama==true){
                data_pro.setNama(txt_nama.getText());
                data_pro.setStock(txt_kuantitas.getText());
                data_pro.setToko(txt_toko.getText());
                ma.ubahStock(data_pro);
            //}
            //cekBarang();
            total();
            tabel_kosong();
            setDefaultTable();
            btnHapus.setEnabled(true);
            //txt_id_produk.setText("");
            txt_nama.setText("");
            txt_kuantitas.setText("");
            txt_harga_beli.setText("");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        autoNumber();
        aktif();
        bukaTombolSemua();
        tabel_kosong();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        new mod_search_produk(null, true).setVisible(true);
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        kunci();
        tutupTombolSemua();
        bersih();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txt_kuantitasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kuantitasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kuantitasKeyReleased

    private void txt_kuantitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kuantitasKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            txt_harga_beli.requestFocus();
            id();
        }
    }//GEN-LAST:event_txt_kuantitasKeyPressed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(txt_total.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Data Masih Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{

            beli.setId_pembelian(txt_faktur.getText());
            beli.setTanggal(cbTglMasuk.getSelectedItem().toString());
            beli.setTotal(txt_total.getText());
            beli.setToko(txt_toko.getText().toString());
            beli.setId_user(idUser);
            ma.simpanPembelian(beli);
            bersih2();
            btn_cetAk.setEnabled(true);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tb_pembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pembelianMouseClicked
        // TODO add your handling code here:
        int baris = tb_pembelian.getSelectedRow();
        if(evt.getClickCount()==1){
            lblkode.setText(tb_pembelian.getValueAt(baris, 0).toString());
            //txt_id_produk.setText(tb_pembelian.getValueAt(baris, 1).toString());
            txt_nama.setText(tb_pembelian.getValueAt(baris, 1).toString());
            txt_harga_beli.setText(tb_pembelian.getValueAt(baris, 2).toString());
            txt_kuantitas.setText(tb_pembelian.getValueAt(baris, 3).toString());
            txt_sub_total.setText(tb_pembelian.getValueAt(baris, 4).toString());
            txt_toko.setText(tb_pembelian.getValueAt(baris, 5).toString());
            try{
            sta=con.createStatement();
            String SQL="SELECT stock FROM tb_barang WHERE nama='"+txt_nama.getText()+"' AND toko='"+txt_toko.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                  coba = Integer.parseInt(res.getString("stock"));
                  lblstok.setText(Integer.toString(coba));
                  //System.out.println(st);
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        }
    }//GEN-LAST:event_tb_pembelianMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        detail_beli.setId_detail(lblkode.getText());
        ma.hapusKeranjangBeli(detail_beli);
        Integer qty = Integer.parseInt(txt_kuantitas.getText());
        Integer stk = Integer.parseInt(lblstok.getText());
        akhir = stk - qty;
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+akhir+"' WHERE nama='"+txt_nama.getText()+"' AND toko='"+txt_toko.getText()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        //txt_id_produk.setText("");
        txt_nama.setText("");
        txt_harga_beli.setText("");
        txt_kuantitas.setText("");
        txt_sub_total.setText("");
        txt_toko.setText("");
        tabel_kosong();
        setDefaultTable();
        total();
        //txt_faktur.setText("");
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btn_cetAkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetAkActionPerformed
        // TODO add your handling code here:
        this.dispose();
        try {
            String NamaFile = "src/pos/lp_faktur_pembelian.jasper"; //Lokasi File jasper
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_pos","root","");
            @SuppressWarnings("rawtypes")
            HashMap hash = new HashMap(2);
            //Mengambil parameter dari ireport
            hash.put("pFakur",txt_faktur.getText());
            File file = new File(NamaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash,koneksi);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_cetAkActionPerformed

    private void txt_namaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            sissa();
        }
    }//GEN-LAST:event_txt_namaKeyPressed

    private void txt_harga_beliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_harga_beliKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            sissa();
            id();
            try {
            double kuant = Double.parseDouble(txt_kuantitas.getText());
            double hrga = Double.parseDouble(txt_harga_beli.getText());
            double sub = kuant*hrga;
            txt_sub_total.setValue(sub);
        } catch (Exception e) {
        }
        }
    }//GEN-LAST:event_txt_harga_beliKeyPressed
manager ma = new manager();
entitas.entitas_pembelian beli = new entitas.entitas_pembelian();
entitas.entitas_detail_pembelian detail_beli = new entitas.entitas_detail_pembelian();
entitas.entitas_produk data_pro = new entitas.entitas_produk();
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mod_pembelian dialog = new mod_pembelian(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btn_cetAk;
    private org.freixas.jcalendar.JCalendarCombo cbTglMasuk;
    private panelMakeOver.headernya headernya1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JLabel lblkode;
    private javax.swing.JLabel lblstok;
    public static javax.swing.JLabel lblstok1;
    private javax.swing.JTable tb_pembelian;
    private javax.swing.JTextField txt_faktur;
    public static javax.swing.JTextField txt_harga_beli;
    public static javax.swing.JTextField txt_kuantitas;
    public static javax.swing.JTextField txt_nama;
    private javax.swing.JFormattedTextField txt_sub_total;
    public static javax.swing.JTextField txt_toko;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables

}
