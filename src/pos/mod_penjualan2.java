/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mod_penjualan2.java
 *
 * Created on Mar 22, 2015, 11:03:44 PM
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
/**
 *
 * @author User
 */
public class mod_penjualan2 extends javax.swing.JDialog {
Connection con = null;
Statement sta = null;
String idPlg, namaPlg, kategori, idpro;
String data[]=new String[6];
String id_detail = "";
int stok=0, awal=0, sisa=0, akhir=0, coba=0;
    /** Creates new form mod_penjualan2 */
    public mod_penjualan2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/db_pos","root","");
            sta=con.createStatement();
            //JOptionPane.showMessageDialog(null, "Berhasil Koneksi");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        this.setLocationRelativeTo(null);
        cbTglMasuk.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        nonAktif();
        kunciTombol();
        tb_penjualan.setModel(tblModel);
        Tabel(tb_penjualan, new int[]{100,100,200,100,100,100,120});
    }


    void autoNumber(){
		try{
			sta=con.createStatement();
			String query = "SELECT MAX(RIGHT(Id_Penjualan , 4)) AS kode FROM tb_penjualan";
			ResultSet rs = sta.executeQuery(query);
			while(rs.next())
			{
				if(rs.first() == false)
				{
					txtFaktur.setText("FKT 1");
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
					txtFaktur.setText("FKT " + no);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

    void cekPelanggan(){
         try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_pelanggan WHERE nama_plg='"+txtNamaPelanggan.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            if(res.next()){
                namaPlg = res.getString("nama_plg");
                txtKategori.setText(res.getString("kategori"));
                txtNamaPelanggan.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(rootPane, "Pelanggan Belum Terdaftar","POS GM",JOptionPane.WARNING_MESSAGE);
                txtNamaPelanggan.setText("");
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    void total(){
         try{
            sta=con.createStatement();
            String SQL="SELECT SUM(sub_total) as TOTAL FROM tb_detail_penjualan WHERE id_penjualan='"+txtFaktur.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                    txtTotal.setText(res.getString("TOTAL"));
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
            String SQL="SELECT id_produk FROM tb_barang WHERE nama ='"+txtNama.getText()+"' AND toko='"+txt_toko.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                idpro = res.getString("id_produk");
                labelid.setText(idpro);
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    

    void stockHabis(){
        try{
            sta=con.createStatement();
            String SQL="SELECT stock FROM tb_barang WHERE nama='"+txtNama.getText()+"', AND toko='"+txt_toko.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                  stok = Integer.parseInt(res.getString("stock"));
                  System.out.println(stok);
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
                new String []{"Id Penjualan","Produk","Harga Jual","Kuantitas","Subtotal","Toko"}){
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
       int count=tb_penjualan.getRowCount();
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
            String SQL="SELECT * FROM `tb_detail_penjualan` WHERE id_penjualan = '"+txtFaktur.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                data[0] = res.getString("id_detail_penjualan");
                //data[1]=res.getString("id_produk");
                data[1]=res.getString("nama_barang");
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

    void Aktif(){
        txtFaktur.setEnabled(true);
        cbTglMasuk.setEnabled(true);
        txtNamaPelanggan.setEnabled(true);
        txtKategori.setEnabled(false);
        txtHarga.setEnabled(true);
        //txtId.setEnabled(true);
        txtNama.setEnabled(true);
        txtKuantitas.setEnabled(true);
        txtKembali.setEnabled(true);
        txtdiskon.setEnabled(true);
        btnBrowse.setEnabled(true);
        txtBayar.setEnabled(true);
        txtTotal.setEnabled(false);
        txtKembali.setEnabled(true);
    }

    void nonAktif(){
        txtFaktur.setEnabled(false);
        cbTglMasuk.setEnabled(false);
        txtNamaPelanggan.setEnabled(false);
        txtHarga.setEnabled(false);
        //txtId.setEnabled(false);
        txtNama.setEnabled(false);
        txtKuantitas.setEnabled(false);
        txtSubtotal.setEnabled(false);
        txtBayar.setEnabled(false);
        txtTotal.setEnabled(false);
        txtKembali.setEnabled(false);
        txtdiskon.setEnabled(false);
        btnBrowse.setEnabled(false);
    }

    void nonAktif2(){       
        txtNama.setEnabled(false);
        txtKuantitas.setEnabled(false);
    }

    void Aktif2(){
        txtNama.setEnabled(true);
        txtKuantitas.setEnabled(true);
    }

    void kunciTombol(){
        btnSimpan.setEnabled(false);
        btnBatal.setEnabled(false);
        btnCetak.setEnabled(false);
        btnAdd.setEnabled(false);
        btnHapus.setEnabled(false);
    }

    void bukaTombol(){
        btnSimpan.setEnabled(true);
        btnBatal.setEnabled(true);
        btnCetak.setEnabled(false);
        btnAdd.setEnabled(true);
        btnHapus.setEnabled(false);
    }

    void bersih(){
        //txtId.setText("");
        txtNama.setText("");
        txtNamaPelanggan.setText("");
        txtKategori.setText("");
        txtKuantitas.setText("");
        txtHarga.setText("");
        txtdiskon.setText("");
        txtSubtotal.setText("");
        txtBayar.setText("");
        txtTotal.setText("");
        txtKembali.setText("");
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headernya1 = new panelMakeOver.headernya();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtKuantitas = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtdiskon = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFaktur = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_penjualan = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtKembali = new javax.swing.JTextField();
        txtNamaPelanggan = new javax.swing.JTextField();
        txtKategori = new javax.swing.JTextField();
        cbTglMasuk = new org.freixas.jcalendar.JCalendarCombo();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        lblStock = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_toko = new javax.swing.JTextField();
        txtIdPro = new javax.swing.JLabel();
        labelid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        headernya1.setName("headernya1"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 23));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("POS GM");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 18));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Modul Transaksi Penjualan");
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout headernya1Layout = new javax.swing.GroupLayout(headernya1);
        headernya1.setLayout(headernya1Layout);
        headernya1Layout.setHorizontalGroup(
            headernya1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headernya1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headernya1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1112, Short.MAX_VALUE))
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Transaksi Penjualan :."));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel1.setText("Faktur Penjualan");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel2.setText("Nama Pelanggan");
        jLabel2.setName("jLabel2"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Produk :."));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel6.setText("Nama");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel7.setText("Kuantitas");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel8.setText("Harga Jual");
        jLabel8.setName("jLabel8"); // NOI18N

        txtNama.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtNama.setName("txtNama"); // NOI18N

        txtKuantitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtKuantitas.setName("txtKuantitas"); // NOI18N
        txtKuantitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKuantitasActionPerformed(evt);
            }
        });
        txtKuantitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKuantitasKeyReleased(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKuantitasKeyPressed(evt);
            }
        });

        txtHarga.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtHarga.setName("txtHarga"); // NOI18N
        txtHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHargaKeyPressed(evt);
            }
        });

        btnBrowse.setText("Browse...");
        btnBrowse.setName("btnBrowse"); // NOI18N
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel13.setText("Diskon");
        jLabel13.setName("jLabel13"); // NOI18N

        txtdiskon.setName("txtdiskon"); // NOI18N
        txtdiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdiskonKeyPressed(evt);
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
                        .addComponent(jLabel13)
                        .addGap(51, 51, 51)
                        .addComponent(txtdiskon, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBrowse))
                            .addComponent(txtKuantitas, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(txtHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowse)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(txtdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel9.setText("Tanggal");
        jLabel9.setName("jLabel9"); // NOI18N

        txtFaktur.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtFaktur.setName("txtFaktur"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tb_penjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_penjualan.setName("tb_penjualan"); // NOI18N
        tb_penjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_penjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_penjualan);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel11.setText("Subtotal");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel12.setText("Total");
        jLabel12.setName("jLabel12"); // NOI18N

        txtSubtotal.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtSubtotal.setName("txtSubtotal"); // NOI18N

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtTotal.setName("txtTotal"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel10.setText("Kategori");
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel14.setText("Bayar");
        jLabel14.setName("jLabel14"); // NOI18N

        txtBayar.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtBayar.setName("txtBayar"); // NOI18N
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBayarKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel15.setText("Kembali");
        jLabel15.setName("jLabel15"); // NOI18N

        txtKembali.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtKembali.setName("txtKembali"); // NOI18N

        txtNamaPelanggan.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtNamaPelanggan.setName("txtNamaPelanggan"); // NOI18N
        txtNamaPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaPelangganKeyPressed(evt);
            }
        });

        txtKategori.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtKategori.setName("txtKategori"); // NOI18N

        cbTglMasuk.setName("cbTglMasuk"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Simpan Transaksi :."));
        jPanel4.setName("jPanel4"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setName("jPanel5"); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13));
        jButton1.setText("NEW");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnSimpan.setText("SAVE");
        btnSimpan.setName("btnSimpan"); // NOI18N
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnBatal.setText("CANCEL");
        btnBatal.setName("btnBatal"); // NOI18N
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnCetak.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnCetak.setText("PRINT");
        btnCetak.setName("btnCetak"); // NOI18N
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnCetak, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Add to Cart :."));
        jPanel6.setName("jPanel6"); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setName("jPanel7"); // NOI18N

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.setName("btnAdd"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnHapus.setText("DELETE");
        btnHapus.setName("btnHapus"); // NOI18N
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
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        lblStock.setForeground(new java.awt.Color(240, 240, 240));
        lblStock.setText("jLabel16");
        lblStock.setName("lblStock"); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel16.setText("Toko");
        jLabel16.setName("jLabel16"); // NOI18N

        txt_toko.setName("txt_toko"); // NOI18N

        txtIdPro.setForeground(new java.awt.Color(240, 240, 240));
        txtIdPro.setText("jLabel17");
        txtIdPro.setName("txtIdPro"); // NOI18N

        labelid.setForeground(new java.awt.Color(240, 240, 240));
        labelid.setText("jLabel3");
        labelid.setName("labelid"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel14))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel15))
                            .addComponent(lblStock))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                            .addComponent(txtFaktur, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                            .addComponent(cbTglMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addGap(77, 77, 77)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtKategori, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                                    .addComponent(txt_toko, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))))))
                                .addGap(27, 27, 27))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdPro))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelid)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtIdPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(cbTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txt_toko, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelid)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel14)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel12)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblStock))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headernya1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headernya1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKuantitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKuantitasKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            id();
            Integer kuant = Integer.parseInt(txtKuantitas.getText());
            //Integer hrga = Integer.parseInt(txtHarga.getText());
            Integer stk = Integer.parseInt(lblStock.getText());

            if(stk<kuant){
                 JOptionPane.showMessageDialog(rootPane, "Stok Produk Ini Tidak Cukup","POS GM",JOptionPane.WARNING_MESSAGE);
            }else{
                try {
                    txtHarga.requestFocus();
                } catch (Exception e) {
                }
            }

        }
}//GEN-LAST:event_txtKuantitasKeyPressed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        if (txtKategori.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kolom Kategori Masih Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }
        else{
        new mod_search_produk_jual2(null, true).setVisible(true);
    }
}//GEN-LAST:event_btnBrowseActionPerformed

    private void tb_penjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_penjualanMouseClicked
        // TODO add your handling code here:
        int baris = tb_penjualan.getSelectedRow();
        btnHapus.setEnabled(true);
        if(evt.getClickCount()==1){
            txtIdPro.setText(tb_penjualan.getValueAt(baris, 0).toString());
            //txtId.setText(tb_penjualan.getValueAt(baris, 1).toString());
            txtNama.setText(tb_penjualan.getValueAt(baris, 1).toString());
            txtHarga.setText(tb_penjualan.getValueAt(baris, 2).toString());
            txtKuantitas.setText(tb_penjualan.getValueAt(baris, 3).toString());
            txtSubtotal.setText(tb_penjualan.getValueAt(baris, 4).toString());
            txt_toko.setText(tb_penjualan.getValueAt(baris, 5).toString());
            btnHapus.setEnabled(true);
            try{
            sta=con.createStatement();
            String SQL="SELECT stock FROM tb_barang WHERE nama='"+txtNama.getText()+"' AND toko='"+txt_toko.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                  coba = Integer.parseInt(res.getString("stock"));
                  lblStock.setText(Integer.toString(coba));
                  //System.out.println(st);
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        }
        nonAktif2();
}//GEN-LAST:event_tb_penjualanMouseClicked

    private void txtBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            Integer bayar = Integer.parseInt(txtBayar.getText());
            Integer total = Integer.parseInt(txtTotal.getText());

            if(bayar<total){
                JOptionPane.showMessageDialog(rootPane, "Uang Pembayaran Kurang","POS GM",JOptionPane.WARNING_MESSAGE);
            }else{
                Integer kembalian = bayar-total;
                txtKembali.setText(Integer.toString(kembalian));
            }
        }
}//GEN-LAST:event_txtBayarKeyPressed

    private void txtNamaPelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPelangganKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            cekPelanggan();
            
        }
}//GEN-LAST:event_txtNamaPelangganKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txtNamaPelanggan.requestFocus();
        autoNumber();
        Aktif();
        bukaTombol();
        tabel_kosong();
}//GEN-LAST:event_jButton1ActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(txtTotal.getText().isEmpty()||txtBayar.getText().isEmpty()||txtBayar.getText().isEmpty()||txtKembali.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Data Masih Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            jual.setId_Penjualan(txtFaktur.getText());
            jual.setTgl_jual(cbTglMasuk.getSelectedItem().toString());
            jual.setId_Pelanggan(idPlg);
            jual.setNama_Pelanggan(txtNamaPelanggan.getText());
            jual.setToko(txt_toko.getText());
            jual.setKategori(txtKategori.getText());
            jual.setBayar(txtBayar.getText());
            jual.setTotal(txtTotal.getText());
            jual.setKembali(txtKembali.getText());
            ma.simpanPenjualan(jual);
            bersih();
            btnCetak.setEnabled(true);
        }
}//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        nonAktif();
        kunciTombol();
        bersih();
}//GEN-LAST:event_btnBatalActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        this.dispose();
        try {
            String NamaFile = "src/pos/lp_faktur_penjualan.jasper"; //Lokasi File jasper
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_pos","root","");
            @SuppressWarnings("rawtypes")
            HashMap hash = new HashMap(2);
            //Mengambil parameter dari ireport
            hash.put("pFak",txtFaktur.getText());
            File file = new File(NamaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash,koneksi);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
}//GEN-LAST:event_btnCetakActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if(txtNama.getText().isEmpty()||txtSubtotal.getText().isEmpty()||txtNama.getText().isEmpty()||txtKategori.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Data Masih Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            Integer kuant = Integer.parseInt(txtKuantitas.getText());
            Integer hrga = Integer.parseInt(txtHarga.getText());
            Integer stk = Integer.parseInt(lblStock.getText());
            Integer diskon = Integer.parseInt(txtdiskon.getText());
            Integer hargaJual = hrga * kuant - diskon;
            txtSubtotal.setText(Integer.toString(hargaJual));
            //Integer stk = Integer.parseInt(lblStock.getText());
            //Integer kuant = Integer.parseInt(txtKuantitas.getText());
            awal = stk;
            int hitung = awal-kuant;
            sisa = hitung;
            //detail_jual.setId_produk(txtId.getText());
            detail_jual.setNama_barang(txtNama.getText());
            detail_jual.setKuantitas(txtKuantitas.getText());
            detail_jual.setHarga(txtHarga.getText());
            detail_jual.setSub_total(txtSubtotal.getText());
            detail_jual.setId_penjualan(txtFaktur.getText());
            detail_jual.setId_produk(labelid.getText());
            detail_jual.setSisa(Integer.toString(sisa));
            detail_jual.setAwal(Integer.toString(awal));
            detail_jual.setTanggal(cbTglMasuk.getSelectedItem().toString());
            detail_jual.setToko(txt_toko.getText());
            detail_jual.setDiskon(txtdiskon.getText());
            ma.simpanKeranjangJual(detail_jual);
            total();
            data_produk.setNama(txtNama.getText());
            data_produk.setStock(txtKuantitas.getText());
            data_produk.setToko(txt_toko.getText());
            ma.kurangiStock(data_produk);
            tabel_kosong();
            setDefaultTable();
        }
}//GEN-LAST:event_btnAddActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        detail_jual.setId_detail(txtIdPro.getText());
        ma.hapusKeranjangJual(detail_jual);
        Integer kuant = Integer.parseInt(txtKuantitas.getText());
        Integer stk = Integer.parseInt(lblStock.getText());
        akhir = stk + kuant;
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+akhir+"' WHERE nama='"+txtNama.getText()+"' AND toko='"+txt_toko.getText()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        //txtId.setText("");
        txtNama.setText("");
        txtKuantitas.setText("");
        txtHarga.setText("");
        txtdiskon.setText("");
        tabel_kosong();
        setDefaultTable();
        total();
        btnHapus.setEnabled(false);
        Aktif2();;
}//GEN-LAST:event_btnHapusActionPerformed

    private void txtdiskonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiskonKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            id();
            Integer kuant = Integer.parseInt(txtKuantitas.getText());
            Integer hrga = Integer.parseInt(txtHarga.getText());
            Integer stk = Integer.parseInt(lblStock.getText());
            Integer diskon = Integer.parseInt(txtdiskon.getText());
            if(stk<kuant){
                 JOptionPane.showMessageDialog(rootPane, "Stok Produk Ini Tidak Cukup","POS GM",JOptionPane.WARNING_MESSAGE);
            }else{
            Integer hargaJual = hrga * kuant - diskon;
            txtSubtotal.setText(Integer.toString(hargaJual));
            }
        }
    }//GEN-LAST:event_txtdiskonKeyPressed

    private void txtKuantitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKuantitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKuantitasActionPerformed

    private void txtKuantitasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKuantitasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKuantitasKeyReleased

    private void txtHargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            id();
            txtdiskon.requestFocus();
        }
    }//GEN-LAST:event_txtHargaKeyPressed
manager ma = new manager();
entitas.entitas_detail_penjualan detail_jual = new entitas.entitas_detail_penjualan();
entitas.entitas_penjualan jual = new entitas.entitas_penjualan();
entitas.entitas_produk data_produk = new entitas.entitas_produk();
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mod_penjualan2 dialog = new mod_penjualan2(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private org.freixas.jcalendar.JCalendarCombo cbTglMasuk;
    private panelMakeOver.headernya headernya1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel labelid;
    public static javax.swing.JLabel lblStock;
    private javax.swing.JTable tb_penjualan;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtFaktur;
    public static javax.swing.JTextField txtHarga;
    private javax.swing.JLabel txtIdPro;
    public static javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtKembali;
    public static javax.swing.JTextField txtKuantitas;
    public static javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaPelanggan;
    public static javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txt_toko;
    private javax.swing.JTextField txtdiskon;
    // End of variables declaration//GEN-END:variables

}
