/*
 * Copyright(c) 2010
 * pizaini.wordpress.com
 */

/*
 * FrameBrowse.java
 *
 * Created on Nov 15, 2010, 9:25:51 PM
 */

package pos;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pizaini
 */
public class FrameBrowse extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    private static Connection connection;    

    /** Creates new form FrameBrowse */
    public FrameBrowse() {
        setLocationRelativeTo(null);
        initComponents();
        this.setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonBrowse = new javax.swing.JButton();
        textPath = new javax.swing.JTextField();
        buttonLoad = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbSeparator = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Load Data to MySQL");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        buttonBrowse.setText("Browse");
        buttonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBrowseActionPerformed(evt);
            }
        });

        buttonLoad.setText("Load Data to MySQL");
        buttonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoadActionPerformed(evt);
            }
        });

        jLabel1.setText("PATH");

        jLabel2.setText("Tanda Separator");

        cbSeparator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ";", "," }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(textPath, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBrowse))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(buttonLoad))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonBrowse)
                    .addComponent(textPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonLoad)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBrowseActionPerformed
        browseCSV();
    }//GEN-LAST:event_buttonBrowseActionPerformed

    private void buttonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoadActionPerformed
      if(textPath.getText().equals("")){
          JOptionPane.showMessageDialog(null, "No File Choiced !", "Error", JOptionPane.ERROR_MESSAGE);
      }else
           loadData();
    }//GEN-LAST:event_buttonLoadActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void browseCSV(){
        JFileChooser jfc = new JFileChooser();
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.getName().toLowerCase().endsWith(".csv") || f.isDirectory() ){
                    return true;
                }else{
                    return false;
                }
            }
            @Override
            public String getDescription() {
                return "*.csv";
            }
        };

        jfc.addChoosableFileFilter(fileFilter);
        jfc.setMultiSelectionEnabled(false);
        jfc.showOpenDialog(this);
        //mengambil path file
        String path = jfc.getSelectedFile().getAbsolutePath();
        textPath.setText(path);
    }
    
    private void loadData(){        
        connection = KoneksiMySQL.getConnection();
        Statement statement = null;
        String path = validatePath(textPath.getText().toString());
        final String delimiter = ""+cbSeparator.getSelectedItem()+"";
        final String query = "LOAD DATA INFILE '"+path+"' INTO TABLE tb_barang FIELDS TERMINATED BY '"+delimiter+"'"
                    + "LINES TERMINATED BY '\r\n' (id_produk, nama, harga_produk, stock, tgl_masuk);";
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(query);         
            JOptionPane.showMessageDialog(null, "Sukses");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal Import Karena "+ex);
            System.out.println(ex.getMessage());
        }
    }

    private String validatePath(String invalidPath){
        String validPath;
        validPath = invalidPath.replace('\\', '/');
        return validPath;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBrowse;
    private javax.swing.JButton buttonLoad;
    private javax.swing.JComboBox cbSeparator;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField textPath;
    // End of variables declaration//GEN-END:variables
}