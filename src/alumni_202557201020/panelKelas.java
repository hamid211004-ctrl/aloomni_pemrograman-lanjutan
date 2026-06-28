/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package alumni_202557201020;

import alumni_202557201020.config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class panelKelas extends javax.swing.JPanel {

    /**
     * Creates new form panelKelas
     */
    public panelKelas() {
        initComponents();
        
        //mengosongkan semua input form
        reset();
        
        //menampilkan combo box jurusan dari database
        load_tabel_kelas();
        
        //mengisi combo box jurusan dari database
        comboJurusan();
        
        //mengisi combo box wali kelas dari database
        comboWali();
    }
    
    //membuat method reset
    void reset(){
        //kosongkan isi text field kode kelas
        tKodeKelas.setText(null);
        
        //aktifkan kembali text field kode kelas agar bisa diedit
        tKodeKelas.setEditable(true);
        
        //kosongkan isi text field nama guru
        tNamaKelas.setText(null);
        
        //reset (combo box)
        cTingkatan.setSelectedItem(null);
        cJurusan.setSelectedItem(null);
        cWali.setSelectedItem(null);
    }
    
    //membuat method untuk menampilkan data jurusan
    void load_tabel_kelas(){
        //membuat objek model tabel baru
        DefaultTableModel model = new DefaultTableModel();
        
        //Menambahkan kolom ke dalam model table
        model.addColumn("Kode Kelas");
        model.addColumn("Nama Kelas");
        model.addColumn("Tingkatan");
        model.addColumn("Jurusan");
        model.addColumn("Wali Kelas");
        
        //Query SQL untuk mengambil semua data dari tabel kelas beserta jurusan dan wali kelas
        String sql = "SELECT k.id_kelas, k.nama_kelas, k.tingkatan, j.nama_jur, g.nama_guru " +
                "FROM kelas k " +
                "LEFT JOIN jurusan j ON k.kode_jur = j.kode_jur " +
                "LEFT JOIN guru g ON k.nip_wali_kelas = g.nip";
        
        try {
            //Membuka koneksi ke database
            Connection conn = koneksi.konek();

            //membuat statement untuk menjalankan query
            Statement st = conn.createStatement();

            //menjalankan query dan meninyimpan hasilnya dalam ResultSet
            ResultSet rs = st.executeQuery(sql);

            //melakukan iterasi untuk setiap baris data hasil query
            while (rs.next()) {
                //mengambil nilai dari masing" kolom
                String kodeKelas = rs.getString("id_kelas");
                String namaKelas = rs.getString("nama_kelas");
                String tingkatan = rs.getString("tingkatan");
                String jurusan = rs.getString("nama_jur");
                String wali = rs.getString("nama_guru");
                
                //menyusun data kedalam array objek
                Object[] baris = {kodeKelas, namaKelas, tingkatan, jurusan, wali};

                //menambahkan array baris ke dalam model table
                model.addRow(baris);
            }
        } catch (SQLException e) {
            //menampilkan pesan error jika gagal mengambil data dari database
//            JOptionPane.showMessageDialog(null, "Gagal mengambil data!");
                JOptionPane.showMessageDialog(null, e.getMessage());
                 e.printStackTrace();
        }
        //menampilkan model yang sudah diisi kedalam tabel GUI
        tblKelas.setModel(model);
    }
    
    //method untuk mengisi combobox jurusan dari database
    void comboJurusan(){
        try {
            //query untuk mengambil semua data dari tabel jurusan
            String sql = "SELECT * FROM jurusan";

            //Membuka koneksi ke database
            Connection conn = koneksi.konek();

            //membuat statement untuk menjalankan query
            Statement st = conn.createStatement();

            //menjalankan query dan meninyimpan hasilnya dalam ResultSet
            ResultSet rs = st.executeQuery(sql);

            //tambahkan setiap nama jurusan ke dalam combobox
            while (rs.next()) {
                cJurusan.addItem(rs.getString("nama_jur"));
            }
        } catch (SQLException sQLException) {
            //kosongkan (tidak ada penanganan kesalahan)
        }
        
        //kosongkan pilihan default combo box
        cJurusan.setSelectedItem(null);
    }
    
    //method untuk mengisi combobox jurusan dari database
    void comboWali(){
        try {
            //query untuk mengambil semua data dari tabel guru
            String sql = "SELECT * FROM guru";

            //Membuka koneksi ke database
            Connection conn = koneksi.konek();

            //membuat statement untuk menjalankan query
            Statement st = conn.createStatement();

            //menjalankan query dan meninyimpan hasilnya dalam ResultSet
            ResultSet rs = st.executeQuery(sql);

            //tambahkan setiap nama guru ke dalam combobox
            while (rs.next()) {
                cWali.addItem(rs.getString("nama_guru"));
            }
        } catch (SQLException sQLException) {
            //kosongkan (tidak ada penanganan kesalahan)
        }
        
        //kosongkan pilihan default combo box
        cWali.setSelectedItem(null);
    }
    
    //method untuk mengisi combobox jurusan dari database
    String kodeJurusan(String namaJurusan){
        try {
            //query dengan parameter untuk mencari jurusan berdasarkan nama
            String sql = "SELECT * FROM jurusan WHERE nama_jur = ?";

            //Membuka koneksi ke database
            Connection conn = koneksi.konek();

            //siapkan preparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            
            //isi parameter query dengan nama jurusan
            ps.setString(1, namaJurusan);

            //menjalankan query dan meninyimpan hasilnya dalam ResultSet
            ResultSet rs = ps.executeQuery();

            //jika data ditemukan, kembalikan kode jurusan
            while (rs.next()) {
                return rs.getString("kode_jur");
            }
        } catch (SQLException e) {
            //jika error, kembalikan string kosong
            return "";
        }
        
        //jika tidak ditemukan, kembalikan string kosong
        return "";
    }
    
    String NIP(String namaGuru){
        try {
            //query dengan parameter untuk mencari guru berdasarkan nama
            String sql = "SELECT * FROM guru WHERE nama_guru = ?";

            //Membuka koneksi ke database
            Connection conn = koneksi.konek();

             //siapkan preparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            
            //isi parameter query dengan nama jurusan
            ps.setString(1, namaGuru);

            //menjalankan query dan meninyimpan hasilnya dalam ResultSet
            ResultSet rs = ps.executeQuery();

            //jika data ditemukan, kembalikan NIP guru
            while (rs.next()) {
                return rs.getString("nip");
            }
        } catch (SQLException e) {
            //jika error, kembalikan string kosong
            return "";
        }
        
        //jika tidak ditemukan, kembalikan string kosong
        return "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tKodeKelas = new javax.swing.JTextField();
        tNamaKelas = new javax.swing.JTextField();
        cTingkatan = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cJurusan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cWali = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKelas = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 20));
        setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(68, 130, 251));
        jPanel2.setPreferredSize(new java.awt.Dimension(733, 60));

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Data Kelas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(849, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(400, 469));

        tKodeKelas.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        tNamaKelas.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        cTingkatan.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cTingkatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "11", "12" }));

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setText("Kode Kelas");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("Nama Kelas");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setText("Tingkatan");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Jurusan");

        cJurusan.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setText("Wali Kelas");

        cWali.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cWali.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wali kelas" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cWali, javax.swing.GroupLayout.Alignment.TRAILING, 0, 366, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(cJurusan, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(tNamaKelas)
                    .addComponent(tKodeKelas)
                    .addComponent(cTingkatan, 0, 366, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tKodeKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cTingkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cWali, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(30, 20, 1, 20, new java.awt.Color(242, 242, 242)));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        btnTambah.setBackground(new java.awt.Color(0, 181, 53));
        btnTambah.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni_202557201020/img/typcn_plus.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        jPanel8.add(btnTambah);

        btnUbah.setBackground(new java.awt.Color(255, 153, 0));
        btnUbah.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni_202557201020/img/mdi_edit.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(this::btnUbahActionPerformed);
        jPanel8.add(btnUbah);

        btnHapus.setBackground(new java.awt.Color(255, 0, 0));
        btnHapus.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni_202557201020/img/material-symbols_delete-rounded (1).png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(this::btnHapusActionPerformed);
        jPanel8.add(btnHapus);

        btnReset.setBackground(new java.awt.Color(58, 153, 250));
        btnReset.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni_202557201020/img/ri_reset-left-fill.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);
        jPanel8.add(btnReset);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        tblKelas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKelas.setRowHeight(35);
        tblKelas.setShowHorizontalLines(true);
        tblKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKelas);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void tblKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKelasMouseClicked
        // TODO add your handling code here:
        //Ambil indeks baris yang diklik oleh pengguna ditabel tblJurusan
        int barisYangDipilih = tblKelas.rowAtPoint(evt.getPoint());
        
        //Ambil nilai baris yang dipilih pada tabel guru
        String kodeKelas = tblKelas.getValueAt(barisYangDipilih, 0).toString();
        String namaKelas = tblKelas.getValueAt(barisYangDipilih, 1).toString();
        String tingkatan = tblKelas.getValueAt(barisYangDipilih, 2).toString();
        String jurusan = tblKelas.getValueAt(barisYangDipilih, 3).toString();
        String wali;
        
        //cek apakah kolom ke 4 (wali kelas) berisi data atau tidak
        if (tblKelas.getValueAt(barisYangDipilih, 4) != null){
            //jika ada data, ambil dan ubah menjadi string
            wali = tblKelas.getValueAt(barisYangDipilih, 4).toString();
        }else {
            //jika kosong(null), set nilai wali kelas juga null
            wali = null;
        }
        
        //Tampilkan kod kelas ditext field input text (tidak bisa diedit)
        tKodeKelas.setText(kodeKelas);
        tKodeKelas.setEditable(false);
        
        //tampilkan nama guru di text field tNamaKelas
        tNamaKelas.setText(namaKelas);
        
        //tampilkan tingkatan ke combo box tingkatan
        cTingkatan.setSelectedItem(tingkatan);
        
        //tampilkan tingkatan ke combo box jurusan
        cJurusan.setSelectedItem(jurusan);
        
        //tampilkan tingkatan ke combo box wali
        cWali.setSelectedItem(wali);
    }//GEN-LAST:event_tblKelasMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        //Ambil input dari text field dan combo box pada GUI
        String kodeKelas = tKodeKelas.getText();
        String namaKelas = tNamaKelas.getText();
        String tingkatan = cTingkatan.getSelectedItem().toString();
        
        //ambil nama jurusan dari combo box lalu ubah ke kode jurusan menggunakan method kodeJurusan
        String jurusan = kodeJurusan(cJurusan.getSelectedItem().toString());
        
         //ambil nama nama wali kelas dari combo box lalu ubah ke nip menggunakan method NIP
        String wali = NIP(cWali.getSelectedItem().toString());

        try {
            //Query SQL untuk menyisipkan data ke tabel jurusan
            String sql = "INSERT INTO kelas(id_kelas, nama_kelas, tingkatan, kode_jur, nip_wali_kelas) VALUES(?,?,?,?,?)";

            //Buat koneksi ke database menggunakan method koneksi() dari class koneksi
            Connection conn = koneksi.konek();

            //Siapkan query SQL untuk dieksekusi dengan parameter
            PreparedStatement ps = conn.prepareStatement(sql);

            //masukkan data ke parameter query (urutan sesuai dengan tanda tanya di query)
            ps.setString(1, kodeKelas);
            ps.setString(2, namaKelas);
            ps.setString(3, tingkatan);
            ps.setString(4, jurusan);
            ps.setString(5, wali);

            //Jalankan query untuk menyimpan data ke database
            ps.execute();

            //Tampilkan pesan bahwa data berhasil disimpan
            JOptionPane.showInternalMessageDialog(null, "Data berhasil disimpan");
        } catch (SQLException e) {
            //jika terjadi kesalahan saat menyimpan data, tampilkan pesan
            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
        }
        //panggil method untuk memuat ulang data pada tabel kelas
        load_tabel_kelas();
        
        //panggil method untuk mereset atau mengosongkan inputan
        reset();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        //Ambil input dari text field dan combo box pada GUI
        String kodeKelas = tKodeKelas.getText();
        String namaKelas = tNamaKelas.getText();
        String tingkatan = cTingkatan.getSelectedItem().toString();
        String jurusan = kodeJurusan(cJurusan.getSelectedItem().toString());
        String wali = NIP(cWali.getSelectedItem().toString());
        
        try {
            //Query SQL untuk mengubah data pada tabel kelas
            String sql = "UPDATE kelas SET nama_kelas=?, tingkatan=?, kode_jur=?, nip_wali_kelas=? WHERE id_kelas=?";

            //Buat koneksi ke database menggunakan method koneksi() dari class koneksi
            Connection conn = koneksi.konek();

            //Siapkan query SQL untuk dieksekusi dengan parameter
            PreparedStatement ps = conn.prepareStatement(sql);

            //Isi parameter
            ps.setString(1, namaKelas);
            ps.setString(2, tingkatan);
            ps.setString(3, jurusan);
            ps.setString(4, wali);
            ps.setString(5, kodeKelas);

            //Jalankan query untuk update data
            ps.execute();

            //Tampilkan pesan bahwa data berhasil diubah
            JOptionPane.showInternalMessageDialog(null, "Data berhasil diubah");
        } catch (SQLException sQLException) {
            //jika terjadi kesalahan saat mengubah data, tampilkan pesan
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
        //panggil method untuk memuat ulang data pada tabel guru
        load_tabel_kelas();
        
        //panggil method untuk mereset atau mengosongkan inputan
        reset();

    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        //Ambil input dari text field tKodeJurusan dan simpan ke variabel tKodeJurusan
        String kodeKelas = tKodeKelas.getText();
        
        try {
            //Query SQL untuk menghapus data pada tabel guru berdasarkan NIP
            String sql = "DELETE FROM kelas WHERE id_kelas=?";

            //Buat koneksi ke database menggunakan method koneksi() dari class koneksi
            Connection conn = koneksi.konek();

            //Siapkan query SQL untuk dieksekusi dengan parameter
            PreparedStatement ps = conn.prepareStatement(sql);

            //Isi parameter pertama(?) dengan kode jurusan
            ps.setString(1, kodeKelas);

            //Jalankan query untuk menyimpan data ke database
            ps.execute();

            //Tampilkan pesan bahwa data berhasil dihapus
            JOptionPane.showInternalMessageDialog(null, "Data berhasil dihapus");
        } catch (SQLException sQLException) {
            //jika terjadi kesalahan saat mengubah data, tampilkan pesan
            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
        }
        //panggil method untuk memuat ulang data pada tabel kelas
        load_tabel_kelas();
        
        //panggil method untuk mereset atau mengosongkan inputan
        reset();

    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cJurusan;
    private javax.swing.JComboBox<String> cTingkatan;
    private javax.swing.JComboBox<String> cWali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tKodeKelas;
    private javax.swing.JTextField tNamaKelas;
    private javax.swing.JTable tblKelas;
    // End of variables declaration//GEN-END:variables
}
