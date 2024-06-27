/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mod_search_produk_jual2.java
 *
 * Created on Mar 22, 2015, 11:15:12 PM
 */

package pos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class mod_search_produk_jual2 extends javax.swing.JDialog {
Connection con = null;
Statement sta = null;
String data[]=new String[5];
    /** Creates new form mod_search_produk_jual2 */
    public mod_search_produk_jual2(java.awt.Frame parent, boolean modal) {
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
        tb_barang.setModel(tblModel);
        Tabel(tb_barang,new int[]{225,125,100,125,80});

        if (mod_penjualan2.txtKategori.getText().equals("A")){
            setDefaultTableA();
        }
        /*else if (mod_penjualan2.txtKategori.getText().equals("A") || (mod_penjualan2.txt_toko.getText().equals("Kartini"))){
            setDefaultTableA1();
        }*/
        else if (mod_penjualan2.txtKategori.getText().equals("B")){
            setDefaultTableB();
        }
        else if (mod_penjualan2.txtKategori.getText().equals("C")){
            setDefaultTableC();
        }
        else if (mod_penjualan2.txtKategori.getText().equals("D")){
            setDefaultTableD();
        }
        else if (mod_penjualan2.txtKategori.getText().equals("R")){
            setDefaultTableR();
        }
        else if (mod_penjualan2.txtKategori.getText().equals("P")){
            setDefaultTableP();
        }
    }

     private javax.swing.table.DefaultTableModel getdeDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String []{"Produk","Harga Jual","Stock","Toko","Tanggal Masuk"}){
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
       int count=tb_barang.getRowCount();
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

private void setDefaultTableA() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                //data[0]=res.getString("id_produk");
                data[0]=res.getString("nama");
                data[1]=res.getString("Harga_A");
                data[2]=res.getString("stock");
                data[3]=res.getString("toko");
                data[4]=res.getString("tgl_masuk");
                
                //data[5]=res.getString("Kategori");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

private void setDefaultTableB() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                //data[0]=res.getString("id_produk");
                data[0]=res.getString("nama");
                data[1]=res.getString("Harga_B");
                data[2]=res.getString("stock");
                data[3]=res.getString("toko");
                data[4]=res.getString("tgl_masuk");
                //data[5]=res.getString("Kategori");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

private void setDefaultTableC() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                //data[0]=res.getString("id_produk");
                data[0]=res.getString("nama");
                data[1]=res.getString("Harga_C");
                data[2]=res.getString("stock");
                data[3]=res.getString("toko");
                data[4]=res.getString("tgl_masuk");
                //data[5]=res.getString("Kategori");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

private void setDefaultTableD() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                //data[0]=res.getString("id_produk");
                data[0]=res.getString("nama");
                data[1]=res.getString("Harga_D");
                data[2]=res.getString("stock");
                data[3]=res.getString("toko");
                data[4]=res.getString("tgl_masuk");
                //data[5]=res.getString("Kategori");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

private void setDefaultTableR() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                //data[0]=res.getString("id_produk");
                data[0]=res.getString("nama");
                data[1]=res.getString("Harga_R");
                data[2]=res.getString("stock");
                data[3]=res.getString("toko");
                data[4]=res.getString("tgl_masuk");
                //data[5]=res.getString("Kategori");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

private void setDefaultTableP() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                //data[0]=res.getString("id_produk");
                data[0]=res.getString("nama");
                data[1]=res.getString("Harga_P");
                data[2]=res.getString("stock");
                data[3]=res.getString("toko");
                data[4]=res.getString("tgl_masuk");
                //data[5]=res.getString("Kategori");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

void cari(){
    tabel_kosong();
    try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE nama LIKE '%"+txtCari.getText()+"%' ORDER BY nama ASC";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                data[0]=res.getString("nama");
                data[1]=res.getString("Harga_A");
                data[2]=res.getString("stock");
                data[3]=res.getString("toko");
                data[4]=res.getString("tgl_masuk");
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

        jLabel7 = new javax.swing.JLabel();
        headernya1 = new panelMakeOver.headernya();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_barang = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel7.setText("Cari");
        jLabel7.setName("jLabel7"); // NOI18N

        headernya1.setName("headernya1"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 23));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("POS GM");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 18));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Modul Pencarian Data Produk");
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
                .addContainerGap(326, Short.MAX_VALUE))
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

        jButton1.setText("OK");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtCari.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtCari.setName("txtCari"); // NOI18N
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tb_barang.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_barang.setName("tb_barang"); // NOI18N
        tb_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_barangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_barang);

        jButton2.setText("CANCEL");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
            .addComponent(headernya1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headernya1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int baris = tb_barang.getSelectedRow();
        this.dispose();
        //mod_penjualan2.txtId.setText(tb_barang.getValueAt(baris, 0).toString());
        mod_penjualan2.txtNama.setText(tb_barang.getValueAt(baris, 0).toString());
        mod_penjualan2.txtHarga.setText(tb_barang.getValueAt(baris, 1).toString());
        mod_penjualan2.lblStock.setText(tb_barang.getValueAt(baris, 2).toString());
        mod_penjualan2.txt_toko.setText(tb_barang.getValueAt(baris, 3).toString());
        mod_penjualan2.txtSubtotal.setText("");
        mod_penjualan2.txt_toko.setEnabled(false);
        mod_penjualan2.txtNama.setEnabled(true);
        mod_penjualan2.txtKuantitas.setEnabled(true);
}//GEN-LAST:event_jButton1ActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        cari();
}//GEN-LAST:event_txtCariKeyReleased

    private void tb_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_barangMouseClicked
        // TODO add your handling code here
}//GEN-LAST:event_tb_barangMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mod_search_produk_jual2 dialog = new mod_search_produk_jual2(new javax.swing.JFrame(), true);
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
    private panelMakeOver.headernya headernya1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_barang;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables

}
