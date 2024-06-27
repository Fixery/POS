/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mod_pindah_barang.java
 *
 * Created on Jul 2, 2015, 2:08:05 PM
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
 * @author Julian Christian
 */
public class mod_pindah_barang extends javax.swing.JDialog {
Connection con = null;
Statement sta = null;
String data[]=new String[5];
String idpro;
int stkaw, stkak, stkaw2, stkak2, coba;

    /** Creates new form mod_pindah_barang */
    public mod_pindah_barang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/db_pos","root","");
            sta=con.createStatement();
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
     }
        this.setLocationRelativeTo(null);
        cbTgl.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        tbpindah.setModel(tblModel);
        Tabel(tbpindah, new int[]{75,75,100,150,150});
        setDefaultTable();
        awal.setEnabled(false);
        nonAktif();
        kunciTombol();
    }

    private javax.swing.table.DefaultTableModel getdeDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String []{"Tanggal","Nama Produk","Kuantitas","Dari Toko","Ke Toko"}){
                    boolean[] canEdit=new boolean[]{
                     false,false,false,false,false
                    };
            @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                    }
                };
    }

private javax.swing.table.DefaultTableModel tblModel=getdeDefaultTableModel();

public void tabel_kosong(){
       int count= tbpindah.getRowCount();
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

void nonAktif(){
        cbTgl.setEnabled(false);
        txtNama.setEnabled(false);
        txtKuantitas.setEnabled(false);
        awal.setEnabled(false);
        akhir.setEnabled(false);

    }

void kunciTombol(){
        btnsave.setEnabled(false);
        //btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
        btnexit.setEnabled(false);
        btnBrowse.setEnabled(false);
    }

void Aktif(){
        cbTgl.setEnabled(true);
        txtNama.setEnabled(true);
        txtKuantitas.setEnabled(true);
        awal.setEnabled(true);
        akhir.setEnabled(true);
    }

void bukaTombol(){
    btnsave.setEnabled(true);
    //btnupdate.setEnabled(true);
    //btndelete.setEnabled(true);
    btnexit.setEnabled(true);
    btnBrowse.setEnabled(true);
}

void bersih(){
        txtNama.setText("");
        txtKuantitas.setText("");
        awal.setText("");
        //txtbiaya.setText("");
    }

private void setDefaultTable() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM `tb_pindah`";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                data[0]=res.getString("tanggal");
                data[1]=res.getString("nama_barang");
                data[2]=res.getString("kuantitas");
                data[3]=res.getString("dari_toko");
                data[4]=res.getString("ke_toko");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

void id(){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE nama ='"+txtNama.getText()+"' AND toko='"+awal.getText()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                idpro = res.getString("id_produk");
                stkak = Integer.parseInt(res.getString("stock"));
                asd.setText(Integer.toString(stkak));
                id.setText(idpro);
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

void id2(){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE nama ='"+txtNama.getText()+"' AND toko='"+akhir.getSelectedItem().toString()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                idpro = res.getString("id_produk");
                stkak2 = Integer.parseInt(res.getString("stock"));
                asd2.setText(Integer.toString(stkak2));
                id2.setText(idpro);
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

void cari(){
    tabel_kosong();
    try{
            sta=con.createStatement();
            String SQL="SELECT * FROM `tb_pindah` WHERE nama_barang LIKE '%"+txtcari.getText()+"%'"
                    + " OR tanggal LIKE '%"+txtcari.getText()+"%' ORDER BY nama_barang ASC";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                data[0]=res.getString("tanggal");
                data[1]=res.getString("nama_barang");
                data[2]=res.getString("kuantitas");
                data[3]=res.getString("dari_toko");
                data[4]=res.getString("ke_toko");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
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

        headernya1 = new panelMakeOver.headernya();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtKuantitas = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        akhir = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cbTgl = new org.freixas.jcalendar.JCalendarCombo();
        awal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbpindah = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnnew = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        id = new javax.swing.JLabel();
        asd = new javax.swing.JLabel();
        id2 = new javax.swing.JLabel();
        asd2 = new javax.swing.JLabel();
        lbqty = new javax.swing.JLabel();
        idpndh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        headernya1.setName("headernya1"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 23));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("POS GM");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 18));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Modul Pindah Barang");
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
                .addContainerGap(775, Short.MAX_VALUE))
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Produk :."));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel6.setText("Nama");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel7.setText("Kuantitas");
        jLabel7.setName("jLabel7"); // NOI18N

        txtNama.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNama.setName("txtNama"); // NOI18N

        txtKuantitas.setFont(new java.awt.Font("Tahoma", 0, 14));
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

        btnBrowse.setText("Browse...");
        btnBrowse.setName("btnBrowse"); // NOI18N
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Dari Toko");
        jLabel8.setName("jLabel8"); // NOI18N

        akhir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kartini", "Imam Bonjol", "Mobil Box" }));
        akhir.setName("akhir"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("Ke Toko");
        jLabel9.setName("jLabel9"); // NOI18N

        cbTgl.setName("cbTgl"); // NOI18N

        awal.setName("awal"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Tanggal");
        jLabel11.setName("jLabel11"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBrowse))
                    .addComponent(txtKuantitas, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbTgl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(awal, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9)
                        .addGap(30, 30, 30)
                        .addComponent(akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnBrowse))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(akhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(awal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tbpindah.setModel(new javax.swing.table.DefaultTableModel(
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
        tbpindah.setName("tbpindah"); // NOI18N
        tbpindah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpindahMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbpindah);

        txtcari.setName("txtcari"); // NOI18N
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        jLabel10.setText("Cari :");
        jLabel10.setName("jLabel10"); // NOI18N

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setName("jPanel3"); // NOI18N

        btnnew.setText("New");
        btnnew.setName("btnnew"); // NOI18N
        btnnew.setPreferredSize(new java.awt.Dimension(75, 25));
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });

        btnsave.setText("Save");
        btnsave.setName("btnsave"); // NOI18N
        btnsave.setPreferredSize(new java.awt.Dimension(75, 25));
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnupdate.setText("Print");
        btnupdate.setName("btnupdate"); // NOI18N
        btnupdate.setPreferredSize(new java.awt.Dimension(75, 25));
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setText("Delete");
        btndelete.setName("btndelete"); // NOI18N
        btndelete.setPreferredSize(new java.awt.Dimension(75, 25));
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnexit.setText("Cancel");
        btnexit.setName("btnexit"); // NOI18N
        btnexit.setPreferredSize(new java.awt.Dimension(75, 25));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnupdate, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        id.setForeground(new java.awt.Color(240, 240, 240));
        id.setText("jLabel1");
        id.setName("id"); // NOI18N

        asd.setForeground(new java.awt.Color(240, 240, 240));
        asd.setText("jLabel1");
        asd.setName("asd"); // NOI18N

        id2.setForeground(new java.awt.Color(240, 240, 240));
        id2.setText("jLabel1");
        id2.setName("id2"); // NOI18N

        asd2.setForeground(new java.awt.Color(240, 240, 240));
        asd2.setText("jLabel2");
        asd2.setName("asd2"); // NOI18N

        lbqty.setForeground(new java.awt.Color(240, 240, 240));
        lbqty.setText("jLabel1");
        lbqty.setName("lbqty"); // NOI18N

        idpndh.setForeground(new java.awt.Color(240, 240, 240));
        idpndh.setText("jLabel1");
        idpndh.setName("idpndh"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headernya1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id)
                            .addComponent(asd)
                            .addComponent(idpndh))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(asd2)
                            .addComponent(id2)
                            .addComponent(lbqty))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headernya1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id)
                            .addComponent(id2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(asd)
                            .addComponent(asd2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbqty)
                            .addComponent(idpndh))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKuantitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKuantitasActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtKuantitasActionPerformed

    private void txtKuantitasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKuantitasKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_txtKuantitasKeyReleased

    private void txtKuantitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKuantitasKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_txtKuantitasKeyPressed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        new mod_search_pindah(null, true).setVisible(true);
}//GEN-LAST:event_btnBrowseActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        cari();
}//GEN-LAST:event_txtcariKeyReleased

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
        // TODO add your handling code here:
        //autoNumber();
        Aktif();
        bukaTombol();
        //tabel_kosong();
}//GEN-LAST:event_btnnewActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        if (akhir.getSelectedItem().toString().equals(awal.getText())){
            JOptionPane.showMessageDialog(rootPane, "Nama Toko Tidak Boleh Sama","POS GM",JOptionPane.WARNING_MESSAGE);
        }
        else{
        id();
        id2();
        Integer stk = Integer.parseInt(asd.getText());
        Integer stk2 = Integer.parseInt(asd2.getText());
        Integer kuant = Integer.parseInt(txtKuantitas.getText());
        lbqty.setText(kuant.toString());
        Integer qty = Integer.parseInt(lbqty.getText());
        if(stkak<kuant){
            JOptionPane.showMessageDialog(rootPane, "Stok Produk Ini Tidak Cukup","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
        int hitung = stk-qty;
        int hitung2 = stk2+qty;
        stkaw = hitung;
        stkaw2 = hitung2;

        pndh.setTanggal(cbTgl.getSelectedItem().toString());
        pndh.setNama_barang(txtNama.getText());
        pndh.setKuantitas(txtKuantitas.getText());
        pndh.setDari_toko(awal.getText());
        pndh.setKe_toko(akhir.getSelectedItem().toString());
        pndh.setId_produk(id.getText());
        ma.simpanpindah(pndh);
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+stkaw+"' WHERE nama='"+txtNama.getText()+"' AND toko='"+awal.getText()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+stkaw2+"' WHERE nama='"+txtNama.getText()+"' AND toko='"+akhir.getSelectedItem().toString()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        bersih();
        nonAktif();
        tabel_kosong();
        setDefaultTable();
        }
        }
}//GEN-LAST:event_btnsaveActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        /*if (akhir.getSelectedItem().toString().equals(awal.getText())){
            JOptionPane.showMessageDialog(rootPane, "Nama Toko Tidak Boleh Sama","POS GM",JOptionPane.WARNING_MESSAGE);
        }
        else{
        /*id();
        id2();*/
        /*Integer stk = Integer.parseInt(asd.getText());
        Integer stk2 = Integer.parseInt(asd2.getText());
        Integer kuant = Integer.parseInt(txtKuantitas.getText());
        Integer qty = Integer.parseInt(lbqty.getText());
        if(stkak<kuant){
            JOptionPane.showMessageDialog(rootPane, "Stok Produk Ini Tidak Cukup","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
        if(kuant==qty){

        }
        else if(kuant > qty)
        {
        int qty2 = kuant-qty;
        int hitung = stk-qty2;
        int hitung2 = stk2+qty2;
        stkaw = hitung;
        stkaw2 = hitung2;
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_pindah SET kuantitas='"+kuant+"' WHERE id_pindah='"+idpndh.getText()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }

        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+stkaw+"' WHERE nama='"+txtNama.getText()+"' AND toko='"+awal.getText()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+stkaw2+"' WHERE nama='"+txtNama.getText()+"' AND toko='"+akhir.getSelectedItem().toString()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        }
        else if(kuant<qty){
        int qty2 = kuant-qty;
        int hitung = stk+qty2;
        int hitung2 = stk2-qty2;
        stkaw = hitung;
        stkaw2 = hitung2;
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_pindah SET kuantitas='"+kuant+"' WHERE id_pindah='"+idpndh.getText()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }

        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+stkaw2+"' WHERE nama='"+txtNama.getText()+"' AND toko='"+awal.getText()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+stkaw+"' WHERE nama='"+txtNama.getText()+"' AND toko='"+akhir.getSelectedItem().toString()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        }
        /*pndh.setTanggal(cbTgl.getSelectedItem().toString());
        pndh.setNama_barang(txtNama.getText());
        pndh.setKuantitas(txtKuantitas.getText());
        pndh.setDari_toko(awal.getText());
        pndh.setKe_toko(akhir.getSelectedItem().toString());
        pndh.setId_produk(id.getText());
        ma.simpanpindah(pndh);
        
        bersih();
        tabel_kosong();
        setDefaultTable();
        }
        }*/
        
}//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        Integer stk = Integer.parseInt(asd.getText());
        Integer stk2 = Integer.parseInt(asd2.getText());
        Integer kuant = Integer.parseInt(txtKuantitas.getText());
        Integer qty = Integer.parseInt(lbqty.getText());

        int hitung = stk+qty;
        int hitung2 = stk2-qty;
        stkaw = hitung;
        stkaw2 = hitung2;
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+stkaw+"' WHERE nama='"+txtNama.getText()+"' AND toko='"+awal.getText()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        try{
            sta=con.createStatement();
            String SQL="UPDATE tb_barang SET stock='"+stkaw2+"' WHERE nama='"+txtNama.getText()+"' AND toko='"+akhir.getSelectedItem().toString()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        try{
            sta=con.createStatement();
            String SQL="DELETE FROM tb_pindah WHERE id_pindah='"+idpndh.getText()+"'";
            int UA = sta.executeUpdate(SQL);
            //JOptionPane.showMessageDialog(rootPane, "Berhasil");
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        }
        bersih();
        tabel_kosong();
        setDefaultTable();
        btndelete.setEnabled(false);
        // jual.setId_Penjualan(txtFaktur.getText());
        /*jual.setId(txtid.getText());
        jual.setTgl(cbTgl.getSelectedItem().toString());
        jual.setNama(txtnama.getText());
        jual.setBiaya(txtbiaya.getText());
        ma.hapusPengeluaran(jual);
        bersih();
        tabel_kosong();
        setDefaultTable();*/
}//GEN-LAST:event_btndeleteActionPerformed

    private void tbpindahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpindahMouseClicked
        // TODO add your handling code here:
        int baris = tbpindah.getSelectedRow();
        btndelete.setEnabled(true);
        if(evt.getClickCount()==1){
            txtNama.setText(tbpindah.getValueAt(baris, 1).toString());
            txtKuantitas.setText(tbpindah.getValueAt(baris, 2).toString());
            lbqty.setText(tbpindah.getValueAt(baris, 2).toString());
            awal.setText(tbpindah.getValueAt(baris, 3).toString());
            akhir.setSelectedItem(tbpindah.getValueAt(baris, 4).toString());
            cbTgl.setSelectedItem(tbpindah.getValueAt(baris, 0).toString());
            Integer kuant = Integer.parseInt(txtKuantitas.getText());
            lbqty.setText(kuant.toString());
            //txtId.setText(tb_penjualan.getValueAt(baris, 1).toString());
            id();
            id2();
            try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_pindah WHERE id_produk='"+id.getText()+"' AND tanggal='"+cbTgl.getSelectedItem().toString()+"'"
                    + " AND nama_barang='"+txtNama.getText()+"' AND kuantitas='"+txtKuantitas.getText()+"'"
                    + " AND dari_toko='"+awal.getText()+"' AND ke_toko='"+akhir.getSelectedItem().toString()+"'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                  coba = Integer.parseInt(res.getString("id_pindah"));
                  idpndh.setText(Integer.toString(coba));
                  //System.out.println(st);
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        }
    }//GEN-LAST:event_tbpindahMouseClicked

manager ma = new manager();
entitas.entitas_pindah pndh = new entitas.entitas_pindah();
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mod_pindah_barang dialog = new mod_pindah_barang(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox akhir;
    private javax.swing.JLabel asd;
    private javax.swing.JLabel asd2;
    public static javax.swing.JTextField awal;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private org.freixas.jcalendar.JCalendarCombo cbTgl;
    private panelMakeOver.headernya headernya1;
    private javax.swing.JLabel id;
    private javax.swing.JLabel id2;
    private javax.swing.JLabel idpndh;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbqty;
    private javax.swing.JTable tbpindah;
    public static javax.swing.JTextField txtKuantitas;
    public static javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables

}
