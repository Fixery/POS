/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Menu.java
 *
 * Created on Mar 22, 2015, 10:56:09 PM
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
import javax.swing.Timer;
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
public class Menu extends javax.swing.JFrame {
javax.swing.Timer waktu;
Connection con=null;
Statement sta=null;
String kunci,buka;
    /** Creates new form Menu */
    public Menu() {
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

        setExtendedState(MAXIMIZED_BOTH);
         BISMILLAH.setText("Selamat datang di Aplikasi Point of Sale Distributor GM - POS GM V.1.0. Pemberitahuan : apabila terjadi system crash segera hubungi pengembang sistem kami | "
                + " Pengembangan sistem versi 2 akan dilengkapi dengan modul-modul pembukuan akuntansi.  \t\t\t\t\t");
        waktu = new Timer(160,new LabelListener(BISMILLAH));
        BISMILLAH.addMouseListener(new LabelMouseListener(waktu));
	waktu.start();
        isiIdUser();
        //ManagementMenu();
    }


    void isiIdUser(){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_user WHERE user_name='"+mod_logsys.txtUS.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                 lblNama.setText(res.getString("nama_lengkap"));
                 lblUs.setText(res.getString("user_name"));
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    void ManagementMenu(){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM `tb_menu`";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                 buka=res.getString("aksi");
                 if(buka.equals("true")){
                     link_mod_barang.setEnabled(true);
                     link_mod_pelanggan.setEnabled(true);
                     link_mod_pembelian.setEnabled(true);
                     link_mod_penjualan.setEnabled(true);
                     link_mod_lp_penjualan.setEnabled(true);
                     link_mod_lp_pembelian.setEnabled(true);
                     link_mod_lp_inventory.setEnabled(true);
                     link_mod_lp_pelanggan.setEnabled(true);
                     link_mod_lp_import.setEnabled(true);
                     link_mod_lp_set_pass.setEnabled(true);
                 }else if(buka.equals("false")){
                     link_mod_barang.setEnabled(false);
                     link_mod_pelanggan.setEnabled(false);
                     link_mod_pembelian.setEnabled(false);
                     link_mod_penjualan.setEnabled(false);
                     link_mod_lp_penjualan.setEnabled(false);
                     link_mod_lp_pembelian.setEnabled(false);
                     link_mod_lp_inventory.setEnabled(false);
                     link_mod_lp_pelanggan.setEnabled(false);
                     link_mod_lp_import.setEnabled(false);
                     link_mod_lp_set_pass.setEnabled(false);
                 }
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel1 = new panelMakeOver.backgroundPanel();
        jPanel1 = new javax.swing.JPanel();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jLabel1 = new javax.swing.JLabel();
        link_mod_lp_penjualan = new org.jdesktop.swingx.JXHyperlink();
        link_mod_lp_pembelian = new org.jdesktop.swingx.JXHyperlink();
        link_mod_lp_inventory = new org.jdesktop.swingx.JXHyperlink();
        link_mod_lp_pelanggan = new org.jdesktop.swingx.JXHyperlink();
        jXHyperlink11 = new org.jdesktop.swingx.JXHyperlink();
        jXHyperlink12 = new org.jdesktop.swingx.JXHyperlink();
        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        jLabel2 = new javax.swing.JLabel();
        link_mod_lp_import = new org.jdesktop.swingx.JXHyperlink();
        link_mod_lp_set_pass = new org.jdesktop.swingx.JXHyperlink();
        link_mod_management_menu = new org.jdesktop.swingx.JXHyperlink();
        jXTaskPane3 = new org.jdesktop.swingx.JXTaskPane();
        jLabel3 = new javax.swing.JLabel();
        link_mod_penjualan = new org.jdesktop.swingx.JXHyperlink();
        link_mod_pembelian = new org.jdesktop.swingx.JXHyperlink();
        link_mod_pembelian1 = new org.jdesktop.swingx.JXHyperlink();
        jXTaskPane4 = new org.jdesktop.swingx.JXTaskPane();
        jLabel7 = new javax.swing.JLabel();
        link_mod_barang = new org.jdesktop.swingx.JXHyperlink();
        link_mod_pelanggan = new org.jdesktop.swingx.JXHyperlink();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        BISMILLAH = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblUs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        backgroundPanel1.setName("backgroundPanel1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jXTaskPane1.setBackground(new java.awt.Color(204, 204, 204));
        jXTaskPane1.setName("jXTaskPane1"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("::Modul Laporan");
        jLabel1.setName("jLabel1"); // NOI18N
        jXTaskPane1.add(jLabel1);

        link_mod_lp_penjualan.setText("Laporan Penjualan Per Periode");
        link_mod_lp_penjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_lp_penjualan.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_lp_penjualan.setName("link_mod_lp_penjualan"); // NOI18N
        link_mod_lp_penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_lp_penjualanActionPerformed(evt);
            }
        });
        jXTaskPane1.add(link_mod_lp_penjualan);

        link_mod_lp_pembelian.setText("Laporan Pembelian Per Periode");
        link_mod_lp_pembelian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_lp_pembelian.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_lp_pembelian.setName("link_mod_lp_pembelian"); // NOI18N
        link_mod_lp_pembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_lp_pembelianActionPerformed(evt);
            }
        });
        jXTaskPane1.add(link_mod_lp_pembelian);

        link_mod_lp_inventory.setText("Laporan Inventory");
        link_mod_lp_inventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_lp_inventory.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_lp_inventory.setName("link_mod_lp_inventory"); // NOI18N
        link_mod_lp_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_lp_inventoryActionPerformed(evt);
            }
        });
        jXTaskPane1.add(link_mod_lp_inventory);

        link_mod_lp_pelanggan.setText("Laporan Data Pelanggan");
        link_mod_lp_pelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_lp_pelanggan.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_lp_pelanggan.setName("link_mod_lp_pelanggan"); // NOI18N
        link_mod_lp_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_lp_pelangganActionPerformed(evt);
            }
        });
        jXTaskPane1.add(link_mod_lp_pelanggan);

        jXHyperlink11.setText("Laporan Pendapatan Kotor");
        jXHyperlink11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jXHyperlink11.setFont(new java.awt.Font("Tahoma", 0, 13));
        jXHyperlink11.setName("jXHyperlink11"); // NOI18N
        jXHyperlink11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXHyperlink11ActionPerformed(evt);
            }
        });
        jXTaskPane1.add(jXHyperlink11);

        jXHyperlink12.setText("Laporan Pendapatan Bersih dan Pengeluaran");
        jXHyperlink12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jXHyperlink12.setFont(new java.awt.Font("Tahoma", 0, 13));
        jXHyperlink12.setName("jXHyperlink12"); // NOI18N
        jXHyperlink12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXHyperlink12ActionPerformed(evt);
            }
        });
        jXTaskPane1.add(jXHyperlink12);

        jXTaskPane2.setBackground(new java.awt.Color(204, 204, 204));
        jXTaskPane2.setName("jXTaskPane2"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("::Modul Utility");
        jLabel2.setName("jLabel2"); // NOI18N
        jXTaskPane2.add(jLabel2);

        link_mod_lp_import.setText("Modul Import Database From Ms. Excell (tb_barang)");
        link_mod_lp_import.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_lp_import.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_lp_import.setName("link_mod_lp_import"); // NOI18N
        link_mod_lp_import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_lp_importActionPerformed(evt);
            }
        });
        jXTaskPane2.add(link_mod_lp_import);

        link_mod_lp_set_pass.setText("Modul Setting Password");
        link_mod_lp_set_pass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_lp_set_pass.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_lp_set_pass.setName("link_mod_lp_set_pass"); // NOI18N
        link_mod_lp_set_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_lp_set_passActionPerformed(evt);
            }
        });
        jXTaskPane2.add(link_mod_lp_set_pass);

        link_mod_management_menu.setText("Modul Management Menu");
        link_mod_management_menu.setEnabled(false);
        link_mod_management_menu.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_management_menu.setName("link_mod_management_menu"); // NOI18N
        link_mod_management_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_management_menuActionPerformed(evt);
            }
        });
        jXTaskPane2.add(link_mod_management_menu);

        jXTaskPane3.setBackground(new java.awt.Color(204, 204, 204));
        jXTaskPane3.setName("jXTaskPane3"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("::Modul Transaksi");
        jLabel3.setName("jLabel3"); // NOI18N
        jXTaskPane3.add(jLabel3);

        link_mod_penjualan.setText("Modul Penjualan");
        link_mod_penjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_penjualan.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_penjualan.setName("link_mod_penjualan"); // NOI18N
        link_mod_penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_penjualanActionPerformed(evt);
            }
        });
        jXTaskPane3.add(link_mod_penjualan);

        link_mod_pembelian.setText("Modul Pembelian");
        link_mod_pembelian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_pembelian.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_pembelian.setName("link_mod_pembelian"); // NOI18N
        link_mod_pembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_pembelianActionPerformed(evt);
            }
        });
        jXTaskPane3.add(link_mod_pembelian);

        link_mod_pembelian1.setText("Modul Pengeluaran Lain-Lain");
        link_mod_pembelian1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_pembelian1.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_pembelian1.setName("link_mod_pembelian1"); // NOI18N
        link_mod_pembelian1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_pembelian1ActionPerformed(evt);
            }
        });
        jXTaskPane3.add(link_mod_pembelian1);

        jXTaskPane4.setBackground(new java.awt.Color(204, 204, 204));
        jXTaskPane4.setName("jXTaskPane4"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("::Modul Master Data");
        jLabel7.setName("jLabel7"); // NOI18N
        jXTaskPane4.add(jLabel7);

        link_mod_barang.setText("Modul Input Barang");
        link_mod_barang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_barang.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_barang.setName("link_mod_barang"); // NOI18N
        link_mod_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_barangActionPerformed(evt);
            }
        });
        jXTaskPane4.add(link_mod_barang);

        link_mod_pelanggan.setText("Modul Input Pelanggan");
        link_mod_pelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link_mod_pelanggan.setFont(new java.awt.Font("Tahoma", 0, 13));
        link_mod_pelanggan.setName("link_mod_pelanggan"); // NOI18N
        link_mod_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link_mod_pelangganActionPerformed(evt);
            }
        });
        jXTaskPane4.add(link_mod_pelanggan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jXTaskPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jXTaskPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTaskPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jXTaskPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTaskPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTaskPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("MS Gothic", 1, 30));
        jLabel4.setText("Point of Sale GM Helmet");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(new java.awt.Font("MS Gothic", 1, 80));
        jLabel5.setText("POS GM");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/GM_LOGONYA.png"))); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Name : ");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Login as :");
        jLabel9.setName("jLabel9"); // NOI18N

        BISMILLAH.setFont(new java.awt.Font("Tahoma", 1, 11));
        BISMILLAH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BISMILLAH.setText("---");
        BISMILLAH.setName("BISMILLAH"); // NOI18N

        lblNama.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNama.setText("Name : ");
        lblNama.setName("lblNama"); // NOI18N

        lblUs.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblUs.setText("Name : ");
        lblUs.setName("lblUs"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUs, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BISMILLAH, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(BISMILLAH)
                    .addComponent(lblNama)
                    .addComponent(lblUs)))
        );

        javax.swing.GroupLayout backgroundPanel1Layout = new javax.swing.GroupLayout(backgroundPanel1);
        backgroundPanel1.setLayout(backgroundPanel1Layout);
        backgroundPanel1Layout.setHorizontalGroup(
            backgroundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(backgroundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(backgroundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(backgroundPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        backgroundPanel1Layout.setVerticalGroup(
            backgroundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundPanel1Layout.createSequentialGroup()
                .addGroup(backgroundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(backgroundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 395, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void link_mod_lp_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_lp_penjualanActionPerformed
        // TODO add your handling code here:
        new mod_laporan_penjualan(this, true).setVisible(true);
}//GEN-LAST:event_link_mod_lp_penjualanActionPerformed

    private void link_mod_lp_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_lp_pembelianActionPerformed
        // TODO add your handling code here:
        new mod_laporan_pembelian(this, true).setVisible(true);
}//GEN-LAST:event_link_mod_lp_pembelianActionPerformed

    private void link_mod_lp_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_lp_inventoryActionPerformed
        // TODO add your handling code here:
        new mod_tgl_barang(this, true).setVisible(true);
}//GEN-LAST:event_link_mod_lp_inventoryActionPerformed

    private void link_mod_lp_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_lp_pelangganActionPerformed
        // TODO add your handling code here:
        //this.dispose();
        new mod_penjualan_pelangga(this, true).setVisible(true);
}//GEN-LAST:event_link_mod_lp_pelangganActionPerformed

    private void link_mod_lp_importActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_lp_importActionPerformed
        // TODO add your handling code here:
        new FrameBrowse().setVisible(true);
}//GEN-LAST:event_link_mod_lp_importActionPerformed

    private void link_mod_lp_set_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_lp_set_passActionPerformed
        // TODO add your handling code here:
        new mod_set_pass(this, true).setVisible(true);
}//GEN-LAST:event_link_mod_lp_set_passActionPerformed

    private void link_mod_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_penjualanActionPerformed
        // TODO add your handling code here:
        new mod_penjualan2(this, true).setVisible(true);
}//GEN-LAST:event_link_mod_penjualanActionPerformed

    private void link_mod_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_pembelianActionPerformed
        // TODO add your handling code here:
        new mod_pembelian(this, true).setVisible(true);
}//GEN-LAST:event_link_mod_pembelianActionPerformed

    private void link_mod_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_barangActionPerformed
        // TODO add your handling code here:
        new mod_input_produk(this, true).setVisible(true);
}//GEN-LAST:event_link_mod_barangActionPerformed

    private void link_mod_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_pelangganActionPerformed
        // TODO add your handling code here:
        new mod_input_pelanggan(this, true).setVisible(true);
}//GEN-LAST:event_link_mod_pelangganActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
         int keluarSystem = JOptionPane.showConfirmDialog(this, "Apakah Anda Ingin Keluar ?", "POS GM", JOptionPane.OK_CANCEL_OPTION);
        if(keluarSystem == JOptionPane.OK_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
         int keluarSystem = JOptionPane.showConfirmDialog(this, "Apakah Anda Ingin Keluar ?", "POS GM", JOptionPane.OK_CANCEL_OPTION);
        if(keluarSystem == JOptionPane.OK_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void link_mod_management_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_management_menuActionPerformed
        // TODO add your handling code here:
        new mod_management_menu(this, true).setVisible(true);
    }//GEN-LAST:event_link_mod_management_menuActionPerformed

    private void jXHyperlink11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXHyperlink11ActionPerformed
        // TODO add your handling code here:
        new mod_laporan_pendapatan_kotor(this, true).setVisible(true);
}//GEN-LAST:event_jXHyperlink11ActionPerformed

    private void jXHyperlink12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXHyperlink12ActionPerformed
        // TODO add your handling code here:
        new mod_untungbersih(this, true).setVisible(true);
}//GEN-LAST:event_jXHyperlink12ActionPerformed

    private void link_mod_pembelian1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link_mod_pembelian1ActionPerformed
        // TODO add your handling code here:
        new mod_pengeluaran(this, true).setVisible(true);
    }//GEN-LAST:event_link_mod_pembelian1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BISMILLAH;
    private panelMakeOver.backgroundPanel backgroundPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink11;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink12;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane3;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane4;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblUs;
    private org.jdesktop.swingx.JXHyperlink link_mod_barang;
    private org.jdesktop.swingx.JXHyperlink link_mod_lp_import;
    private org.jdesktop.swingx.JXHyperlink link_mod_lp_inventory;
    private org.jdesktop.swingx.JXHyperlink link_mod_lp_pelanggan;
    private org.jdesktop.swingx.JXHyperlink link_mod_lp_pembelian;
    private org.jdesktop.swingx.JXHyperlink link_mod_lp_penjualan;
    private org.jdesktop.swingx.JXHyperlink link_mod_lp_set_pass;
    private org.jdesktop.swingx.JXHyperlink link_mod_management_menu;
    private org.jdesktop.swingx.JXHyperlink link_mod_pelanggan;
    private org.jdesktop.swingx.JXHyperlink link_mod_pembelian;
    private org.jdesktop.swingx.JXHyperlink link_mod_pembelian1;
    private org.jdesktop.swingx.JXHyperlink link_mod_penjualan;
    // End of variables declaration//GEN-END:variables

}
