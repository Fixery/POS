/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mod_pengeluaran.java
 *
 * Created on Apr 24, 2015, 6:27:28 PM
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
public class mod_pengeluaran extends javax.swing.JDialog {
Connection con = null;
Statement sta = null;
String idPlg, namaPlg, kategori;
String data[]=new String[4];
    /** Creates new form mod_pengeluaran */
    public mod_pengeluaran(java.awt.Frame parent, boolean modal) {
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
        //nonAktif();
        //kunciTombol();
        tbpengeluaran.setModel(tblModel);
        Tabel(tbpengeluaran, new int[]{75,75,150,150});
        setDefaultTable();
        nonAktif();
    }

 
 
 private javax.swing.table.DefaultTableModel getdeDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String []{"Id Pengeluaran","Tanggal","Nama Pengeluaran","Biaya"}){
                    boolean[] canEdit=new boolean[]{
                     false,false,false,false
                    };
            @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                    }
                };
    }

private javax.swing.table.DefaultTableModel tblModel=getdeDefaultTableModel();

public void tabel_kosong(){
       int count= tbpengeluaran.getRowCount();
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

void autoNumber(){
		try{
			sta=con.createStatement();
			String query = "SELECT MAX(RIGHT(id_Pengeluaran , 3)) AS kode FROM tb_pengeluaran";
			ResultSet rs = sta.executeQuery(query);
			while(rs.next())
			{
				if(rs.first() == false)
				{
					txtid.setText("PE 1");
				}
				else
				{
					rs.last();
					int noKirim = rs.getInt(1) + 1;
					String no = String.valueOf(noKirim);
					int noLong = no.length();
					for(int a=0;a<2-noLong;a++)
					{
						no = "00" + no;
					}
					txtid.setText("PE" + no);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

void nonAktif(){
        cbTgl.setEnabled(false);
        txtid.setEnabled(false);
        txtnama.setEnabled(false);
        txtbiaya.setEnabled(false);

    }

void kunciTombol(){
        btnsave.setEnabled(false);
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
        btnexit.setEnabled(false);
    }

void Aktif(){
        txtid.setEnabled(true);
        cbTgl.setEnabled(true);
        txtnama.setEnabled(true);
        txtbiaya.setEnabled(true);
    }

void bukaTombol(){
    btnsave.setEnabled(true);
    btnupdate.setEnabled(true);
    btndelete.setEnabled(true);
    btnexit.setEnabled(true);
}

private void setDefaultTable() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM `tb_pengeluaran`";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                data[0]=res.getString("id_pengeluaran");
                data[1]=res.getString("Tanggal");
                data[2]=res.getString("nama_pengeluaran");
                data[3]=res.getString("Pengeluaran");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    void bersih(){
        txtid.setText("");
        txtnama.setText("");
        txtbiaya.setText("");        
    }

    void cari(){
    tabel_kosong();
    try{
            sta=con.createStatement();
            String SQL="SELECT * FROM `tb_pengeluaran` WHERE nama_pengeluaran LIKE '%"+txtcari.getText()+"%'"
                    + " OR tanggal LIKE '%"+txtcari.getText()+"%' ORDER BY nama_pengeluaran ASC";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                data[0]=res.getString("id_pengeluaran");
                data[1]=res.getString("Tanggal");
                data[2]=res.getString("nama_pengeluaran");
                data[3]=res.getString("Pengeluaran");
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtbiaya = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        cbTgl = new org.freixas.jcalendar.JCalendarCombo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbpengeluaran = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnnew = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        headernya1.setName("headernya1"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 23));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("POS GM");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 18));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Modul Pengeluaran");
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
                .addContainerGap(761, Short.MAX_VALUE))
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

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText("Tanggal");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Nama Pengeluaran");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Biaya");
        jLabel3.setName("jLabel3"); // NOI18N

        txtbiaya.setName("txtbiaya"); // NOI18N

        txtnama.setName("txtnama"); // NOI18N
        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });

        cbTgl.setName("cbTgl"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tbpengeluaran.setModel(new javax.swing.table.DefaultTableModel(
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
        tbpengeluaran.setName("tbpengeluaran"); // NOI18N
        tbpengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpengeluaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbpengeluaran);

        jLabel6.setText("Id Pengeluaran");
        jLabel6.setName("jLabel6"); // NOI18N

        txtid.setName("txtid"); // NOI18N

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setName("jPanel2"); // NOI18N

        btnnew.setText("NEW");
        btnnew.setName("btnnew"); // NOI18N
        btnnew.setPreferredSize(new java.awt.Dimension(75, 25));
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });

        btnsave.setText("SAVE");
        btnsave.setName("btnsave"); // NOI18N
        btnsave.setPreferredSize(new java.awt.Dimension(75, 25));
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnupdate.setText("UPDATE");
        btnupdate.setName("btnupdate"); // NOI18N
        btnupdate.setPreferredSize(new java.awt.Dimension(75, 25));
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setText("DELETE");
        btndelete.setName("btndelete"); // NOI18N
        btndelete.setPreferredSize(new java.awt.Dimension(75, 25));
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnexit.setText("CANCEL");
        btnexit.setName("btnexit"); // NOI18N
        btnexit.setPreferredSize(new java.awt.Dimension(75, 25));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnupdate, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel7.setText("Cari :");
        jLabel7.setName("jLabel7"); // NOI18N

        txtcari.setName("txtcari"); // NOI18N
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtbiaya)
                            .addComponent(txtnama)
                            .addComponent(txtid)
                            .addComponent(cbTgl, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(txtbiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(37, 37, 37)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headernya1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headernya1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        jual.setTgl(cbTgl.getSelectedItem().toString());
            jual.setNama(txtnama.getText());
            jual.setBiaya(txtbiaya.getText());
            jual.setId(txtid.getText());
            ma.simpanPengeluaran(jual);
            bersih();
            tabel_kosong();
            setDefaultTable();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
            jual.setId(txtid.getText());
            jual.setTgl(cbTgl.getSelectedItem().toString());
            jual.setNama(txtnama.getText());
            jual.setBiaya(txtbiaya.getText());
            ma.updatePeng(jual);
            bersih();
            tabel_kosong();
            setDefaultTable();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
       // jual.setId_Penjualan(txtFaktur.getText());
            jual.setId(txtid.getText());
            jual.setTgl(cbTgl.getSelectedItem().toString());
            jual.setNama(txtnama.getText());
            jual.setBiaya(txtbiaya.getText());
            ma.hapusPengeluaran(jual);
            bersih();
            tabel_kosong();
            setDefaultTable();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void tbpengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpengeluaranMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            int baris = tbpengeluaran.getSelectedRow();
            txtid.setText(tbpengeluaran.getValueAt(baris, 0).toString());
            txtbiaya.setText(tbpengeluaran.getValueAt(baris, 3).toString());
            txtnama.setText(tbpengeluaran.getValueAt(baris, 2).toString());
        }
    }//GEN-LAST:event_tbpengeluaranMouseClicked

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
        // TODO add your handling code here:
        autoNumber();
        Aktif();
        bukaTombol();
        //tabel_kosong();
    }//GEN-LAST:event_btnnewActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txtcariKeyReleased
manager ma = new manager();
entitas.entitas_pengeluaran jual = new entitas.entitas_pengeluaran();

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mod_pengeluaran dialog = new mod_pengeluaran(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private org.freixas.jcalendar.JCalendarCombo cbTgl;
    private panelMakeOver.headernya headernya1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbpengeluaran;
    private javax.swing.JTextField txtbiaya;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    // End of variables declaration//GEN-END:variables

}
