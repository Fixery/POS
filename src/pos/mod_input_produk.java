/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mod_input_produk.java
 *
 * Created on Mar 12, 2015, 12:58:03 AM
 */

package pos;
import entitas.manager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
/**
 *
 * @author User
 */
public class mod_input_produk extends javax.swing.JDialog {
Connection con = null;
Statement sta = null;
String data[]=new String[12];
//String check = "<html><body><input type='checkbox checked'></body></html>";
//String notcheck = "<html><body><input type='checkbox'></body></html>";
//String asd = null;
    /** Creates new form mod_input_produk */
    public mod_input_produk(java.awt.Frame parent, boolean modal) {
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
        Tabel(tb_barang,new int[]{125,100,75,75,75,75,75,75,75,50,125});
        setDefaultTable();
        cbTglMasuk.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        //txt_id_produk.setEnabled(false);
        btnSimpan.setEnabled(false);
        nonAktif();
    }

    /*void autoNumber(){
		try{
                        sta=con.createStatement();
			String query = "SELECT MAX(right(id_produk,4)) AS kode FROM tb_barang";
			ResultSet rs = sta.executeQuery(query);
			while(rs.next())
			{
				if(rs.first() == false)
				{
					txt_id_produk.setText("HLM 1");
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
					txt_id_produk.setText("HLM " + no);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}*/


    private javax.swing.table.DefaultTableModel getdeDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String []{"Produk","Toko","Harga Beli","Harga A","Harga B","Harga C","Harga D","Harga R","Harga P","Stock","Tanggal Masuk"}){
                    boolean[] canEdit=new boolean[]{
                     false,false,false,false,false,false,false,false,false,false,false
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
void isiProduk(){
        try{
            sta=con.createStatement();
            String SQL="SELECT DISTINCT(Kategori) AS produk FROM `tb_barang` WHERE nama = 'GM Imprezza'";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
               //asd = res.getString("produk");
            }
            res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

private void setDefaultTable() {
       //cariBy();
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang ORDER BY nama ASC";
            ResultSet res=sta.executeQuery(SQL);
            while(res.next()){
                //JComboBox combobox=new JComboBox();
                //combobox.addItem(asd);
                //data[0]=combobox.getSelectedItem().toString();
                //data[0]=res.getString("id_produk");
                data[0]=res.getString("nama");
                data[1]=res.getString("toko");
                data[2]=res.getString("harga_produk");
                data[3]=res.getString("Harga_A");
                data[4]=res.getString("Harga_B");
                data[5]=res.getString("Harga_C");
                data[6]=res.getString("Harga_D");
                data[7]=res.getString("Harga_R");
                data[8]=res.getString("Harga_P");
                data[9]=res.getString("stock");
                data[10]=res.getString("tgl_masuk");
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
                //data[0]=res.getString("id_produk");
                data[0]=res.getString("nama");
                data[1]=res.getString("toko");
                data[2]=res.getString("harga_produk");
                data[3]=res.getString("Harga_A");
                data[4]=res.getString("Harga_B");
                data[5]=res.getString("Harga_C");
                data[6]=res.getString("Harga_D");
                data[7]=res.getString("Harga_R");
                data[8]=res.getString("Harga_P");
                data[9]=res.getString("stock");
                data[10]=res.getString("tgl_masuk");
                tblModel.addRow(data);
            }
            res.close();
            sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
}

void bersih(){
//    txt_id_produk.setText("");
    txt_nama.setText("");
    txt_harga.setText("");
    txt_stok.setText("");
    txtjual.setText("");
    cbTglMasuk.setSelectedIndex(0);
}

void aktif(){

    txt_nama.requestFocus();
//    txt_id_produk.setEnabled(true);
    txt_nama.setEnabled(true);
    txt_harga.setEnabled(true);
    txt_stok.setEnabled(true);
    txtjual.setEnabled(true);
    cbTglMasuk.setEnabled(true);
    cbktg.setEnabled(true);
    cbtoko.setEnabled(true);
}

void nonAktif(){
    txt_nama.requestFocus();
//    txt_id_produk.setEnabled(false);
    txt_nama.setEnabled(false);
    txt_harga.setEnabled(false);
    txt_stok.setEnabled(false);
    txtjual.setEnabled(false);
    cbTglMasuk.setEnabled(false);
    cbktg.setEnabled(false);
    cbtoko.setEnabled(false);
    btnSimpan.setEnabled(false);
    btnUbah.setEnabled(false);
    btnHapus.setEnabled(false);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_stok = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbTglMasuk = new org.freixas.jcalendar.JCalendarCombo();
        cbtoko = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbktg = new javax.swing.JComboBox();
        txtjual = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_barang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 23));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("POS GM");

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 18));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Modul Input Data Produk");

        javax.swing.GroupLayout headernya1Layout = new javax.swing.GroupLayout(headernya1);
        headernya1.setLayout(headernya1Layout);
        headernya1Layout.setHorizontalGroup(
            headernya1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headernya1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headernya1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1074, Short.MAX_VALUE))
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(".: Input Data Produk :."));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Nama");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel3.setText("Harga Pokok");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel6.setText("Harga Jual");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, 20));

        txt_stok.setFont(new java.awt.Font("Tahoma", 0, 14));
        jPanel1.add(txt_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 200, -1));

        txt_harga.setFont(new java.awt.Font("Tahoma", 0, 14));
        jPanel1.add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 569, -1));

        txt_nama.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 569, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel8.setText("Tanggal");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jPanel1.add(cbTglMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 200, -1));

        cbtoko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kartini", "Imam Bonjol", "Mobil Box" }));
        jPanel1.add(cbtoko, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 200, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel9.setText("Toko");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel10.setText("Kategori");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        cbktg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "R", "P" }));
        jPanel1.add(cbktg, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 200, -1));
        jPanel1.add(txtjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 200, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel11.setText("Stock");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, -1, -1));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton1.setText("NEW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSimpan.setText("SAVE");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnUbah.setText("EDIT");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapus.setText("DELETE");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton5.setText("CANCEL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel7.setText("Cari");

        txtCari.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

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
        tb_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_barangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_barang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headernya1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headernya1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                        .addGap(69, 69, 69))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(385, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //autoNumber();
        bersih();
        aktif();
        cbktg.setSelectedItem("A");
        cbktg.setEnabled(false);
        btnSimpan.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (cbktg.getSelectedItem().equals("A")){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE nama='"+txt_nama.getText()+"' AND toko='"+cbtoko.getSelectedItem().toString()+"'";
            /*AND Harga_A='"+txtjual.getText()+"' AND toko='"+cbtoko.getSelectedItem()+"'"*/
            ResultSet res=sta.executeQuery(SQL);
            if(res.next()){
                    //System.out.println("Data Lama BISMILLAH");
                    JOptionPane.showMessageDialog(rootPane, "Data Sudah Pernah Terinput");
            }else{
                if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty() || txtjual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            //data_pro.setId_produk(txt_id_produk.getText());
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_produk(txt_harga.getText());
            data_pro.setStock(txt_stok.getText());
            data_pro.setTgl_masuk(cbTglMasuk.getSelectedItem().toString());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            data_pro.setHarga_a(txtjual.getText());
            ma.simpanBarangA(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
            }
            //res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        }
        else if (cbktg.getSelectedItem().equals("B")){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE nama='"+txt_nama.getText()+"' AND Harga_B!='0' AND toko='"+cbtoko.getSelectedItem().toString()+"'";
            ResultSet res=sta.executeQuery(SQL);
            if(res.next()){
                    //System.out.println("Data Lama BISMILLAH");
                    JOptionPane.showMessageDialog(rootPane, "Data Sudah Pernah Di Input");
            }else{
                if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_b(txtjual.getText());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            ma.ubahBarangB(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
            }
            //res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        }
        else if (cbktg.getSelectedItem().equals("C")){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE nama='"+txt_nama.getText()+"' AND Harga_C!='0' AND toko='"+cbtoko.getSelectedItem().toString()+"'";
            ResultSet res=sta.executeQuery(SQL);
            if(res.next()){
                    //System.out.println("Data Lama BISMILLAH");
                    JOptionPane.showMessageDialog(rootPane, "Data Sudah Pernah Di Input");
            }else{
                if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_c(txtjual.getText());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            ma.ubahBarangC(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
            }
            //res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        }
        else if (cbktg.getSelectedItem().equals("D")){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE nama='"+txt_nama.getText()+"' AND Harga_D!='0' AND toko='"+cbtoko.getSelectedItem().toString()+"'";
            ResultSet res=sta.executeQuery(SQL);
            if(res.next()){
                    //System.out.println("Data Lama BISMILLAH");
                    JOptionPane.showMessageDialog(rootPane, "Data Sudah Pernah Di Input");
            }else{
                if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_d(txtjual.getText());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            ma.ubahBarangD(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
            }
            //res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        }

        else if (cbktg.getSelectedItem().equals("R")){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE nama='"+txt_nama.getText()+"' AND Harga_R!='0' AND toko='"+cbtoko.getSelectedItem().toString()+"'";
            ResultSet res=sta.executeQuery(SQL);
            if(res.next()){
                    //System.out.println("Data Lama BISMILLAH");
                    JOptionPane.showMessageDialog(rootPane, "Data Sudah Pernah Di Input");
            }else{
                if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_r(txtjual.getText());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            ma.ubahBarangR(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
            }
            //res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        }

        else if (cbktg.getSelectedItem().equals("P")){
        try{
            sta=con.createStatement();
            String SQL="SELECT * FROM tb_barang WHERE nama='"+txt_nama.getText()+"' AND Harga_P!='0' AND toko='"+cbtoko.getSelectedItem().toString()+"'";
            ResultSet res=sta.executeQuery(SQL);
            if(res.next()){
                    //System.out.println("Data Lama BISMILLAH");
                    JOptionPane.showMessageDialog(rootPane, "Data Sudah Pernah Di Input");
            }else{
                if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_p(txtjual.getText());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            ma.ubahBarangP(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
            }
            //res.close();
            //sta.close();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        }

        /*if(txt_id_produk.getText().isEmpty() || txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            data_pro.setId_produk(txt_id_produk.getText());
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_produk(txt_harga.getText());
            data_pro.setStock(txt_stok.getText());
            data_pro.setTgl_masuk(cbTglMasuk.getSelectedItem().toString());
            ma.simpanBarang(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }*/
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txtCariKeyReleased

    private void tb_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_barangMouseClicked
        // TODO add your handling code here:
        int baris = tb_barang.getSelectedRow();
        if(evt.getClickCount()==1){
            aktif();
            btnSimpan.setEnabled(true);
            btnUbah.setEnabled(true);
            btnHapus.setEnabled(true);
            //txt_id_produk.setText(tb_barang.getValueAt(baris, 0).toString());
            txt_nama.setText(tb_barang.getValueAt(baris, 0).toString());
            txt_harga.setText(tb_barang.getValueAt(baris, 2).toString());
            txt_stok.setText(tb_barang.getValueAt(baris, 9).toString());
            cbTglMasuk.setSelectedItem(tb_barang.getValueAt(baris, 10).toString());
            cbtoko.setSelectedItem(tb_barang.getValueAt(baris, 1).toString());
            txt_nama.setEnabled(false);
        }
    }//GEN-LAST:event_tb_barangMouseClicked

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        if (cbktg.getSelectedItem().equals("A")){
        if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty() || txtjual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Ada Yang Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            //data_pro.setId_produk(txt_id_produk.getText());
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_produk(txt_harga.getText());
            data_pro.setStock(txt_stok.getText());
            data_pro.setTgl_masuk(cbTglMasuk.getSelectedItem().toString());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            data_pro.setHarga_a(txtjual.getText());
            ma.ubahDataBarangA(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
        }
        else if (cbktg.getSelectedItem().equals("B")){
        if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty() || txtjual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Ada Yang Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            //data_pro.setId_produk(txt_id_produk.getText());
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_produk(txt_harga.getText());
            data_pro.setStock(txt_stok.getText());
            data_pro.setTgl_masuk(cbTglMasuk.getSelectedItem().toString());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            data_pro.setHarga_b(txtjual.getText());
            ma.ubahDataBarangB(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
        }
        else if (cbktg.getSelectedItem().equals("C")){
        if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty() || txtjual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Ada Yang Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            //data_pro.setId_produk(txt_id_produk.getText());
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_produk(txt_harga.getText());
            data_pro.setStock(txt_stok.getText());
            data_pro.setTgl_masuk(cbTglMasuk.getSelectedItem().toString());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            data_pro.setHarga_c(txtjual.getText());
            ma.ubahDataBarangC(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
        }
        else if (cbktg.getSelectedItem().equals("D")){
        if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty() || txtjual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Ada Yang Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            //data_pro.setId_produk(txt_id_produk.getText());
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_produk(txt_harga.getText());
            data_pro.setStock(txt_stok.getText());
            data_pro.setTgl_masuk(cbTglMasuk.getSelectedItem().toString());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            data_pro.setHarga_d(txtjual.getText());
            ma.ubahDataBarangD(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
        }
        else if (cbktg.getSelectedItem().equals("R")){
        if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty() || txtjual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Ada Yang Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            //data_pro.setId_produk(txt_id_produk.getText());
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_produk(txt_harga.getText());
            data_pro.setStock(txt_stok.getText());
            data_pro.setTgl_masuk(cbTglMasuk.getSelectedItem().toString());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            data_pro.setHarga_r(txtjual.getText());
            ma.ubahDataBarangR(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
        }
        else if (cbktg.getSelectedItem().equals("P")){
        if(txt_nama.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_stok.getText().isEmpty() || txtjual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Ada Yang Kosong","POS GM",JOptionPane.WARNING_MESSAGE);
        }else{
            //data_pro.setId_produk(txt_id_produk.getText());
            data_pro.setNama(txt_nama.getText());
            data_pro.setHarga_produk(txt_harga.getText());
            data_pro.setStock(txt_stok.getText());
            data_pro.setTgl_masuk(cbTglMasuk.getSelectedItem().toString());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            data_pro.setHarga_p(txtjual.getText());
            ma.ubahDataBarangP(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int pilihan = JOptionPane.showConfirmDialog(this, "Yakin Ingin Menghapus Data ?", "POS GM", JOptionPane.OK_CANCEL_OPTION);
        if(pilihan == JOptionPane.OK_OPTION){
            //data_pro.setId_produk(txt_id_produk.getText());
            data_pro.setNama(txt_nama.getText());
            data_pro.setToko(cbtoko.getSelectedItem().toString());
            ma.hapusBarang(data_pro);
            bersih();
            nonAktif();
            tabel_kosong();
            setDefaultTable();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        bersih();
        nonAktif();
//        txt_id_produk.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed
manager ma = new manager();
entitas.entitas_produk data_pro = new entitas.entitas_produk();
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mod_input_produk dialog = new mod_input_produk(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private org.freixas.jcalendar.JCalendarCombo cbTglMasuk;
    private javax.swing.JComboBox cbktg;
    private javax.swing.JComboBox cbtoko;
    private panelMakeOver.headernya headernya1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_barang;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_stok;
    private javax.swing.JTextField txtjual;
    // End of variables declaration//GEN-END:variables

}
