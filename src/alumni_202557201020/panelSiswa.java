/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package alumni_202557201020;

import alumni_202557201020.config.koneksi;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class panelSiswa extends javax.swing.JPanel {

    /**
     * Creates new form panelSiswa
     */
    public panelSiswa() {
        initComponents();
        
        //Memanggil method reset unruk mengosongkan
        reset();
        
        //memanggil method untuk menampilkan data siswa ke dalam tabel
        load_tabel_siswa();
        
        //memanggil method untuk mengisi combo box kelas dari database
        comboKelas();
    }
    
    // Method untuk mengosongkan semua input pada form siswa
    void reset() {

        // Mengosongkan field NIS
        tNIS.setText(null);
        
        //aktifkan kembali text field NIP agar bisa diedit
        tNIS.setEditable(true);

        // Mengosongkan field Nama Siswa
        tNamaSiswa.setText(null);

        // Mengosongkan pilihan pada combo box Jenis Kelamin
        cJK.setSelectedItem(null);

        // Mengosongkan field Tempat Lahir
        tTempatLahir.setText(null);

        // Mengosongkan pilihan pada komponen kalender Tanggal Lahir
        tTanggal.setCalendar(null);

        // Mengosongkan field Nomor HP
        tHP.setText(null);

        // Mengosongkan pilihan pada combo box Kelas
        cKelas.setSelectedItem(null);

        // Mengosongkan field Alamat
        tAlamat.setText(null);

        // Menghapus icon pada label foto
        tFoto.setIcon(null);

        // Mengatur teks label foto menjadi "Foto"
        tFoto.setText("Foto");

        // Mengosongkan path file foto yang disimpan
        tFotoPath.setText(null);
    }

    // Method untuk mengisi combo box kelas dari tabel 'kelas' di database
    void comboKelas() {
        try {

            // Query SQL untuk mengambil semua data kelas
            String sql = "SELECT * FROM kelas";

            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Membuat statement untuk menjalankan query
            Statement statement = conn.createStatement();

            // Menjalankan query dan menyimpan hasilnya
            ResultSet resultSet = statement.executeQuery(sql);

            // Mengambil data satu per satu dan menambahkannya ke combo box
            while (resultSet.next()) {
                cKelas.addItem(resultSet.getString("id_kelas"));
            }

        } catch (SQLException e) {
            // Jika terjadi kesalahan, tampilkan pesan error
            e.printStackTrace();
        }

        // Mengosongkan pilihan combo box setelah diisi
        cKelas.setSelectedItem(null);
    }
    
    // Method untuk menampilkan data siswa ke dalam tabel
    void load_tabel_siswa() {

        // Membuat model tabel baru
        DefaultTableModel model = new DefaultTableModel();

        // Menambahkan kolom ke dalam model tabel
        model.addColumn("NIS");
        model.addColumn("Nama Siswa");
        model.addColumn("L/P");
        model.addColumn("Tempat Lahir");
        model.addColumn("Tgl Lahir");
        model.addColumn("Kelas");
        model.addColumn("HP");
        model.addColumn("Alamat");

        // Query SQL untuk mengambil semua data siswa
        String sql = "SELECT * FROM siswa";

        try {

            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Membuat statement SQL
            Statement st = conn.createStatement();

            // Menjalankan query dan mengambil hasilnya
            ResultSet rs = st.executeQuery(sql);

            // Melakukan iterasi untuk setiap baris hasil query
            while (rs.next()) {

                // Mengambil data dari setiap kolom
                String nis = rs.getString("nis");
                String namaSiswa = rs.getString("nama_siswa");
                String jenisKelamin = rs.getString("gender");
                String tempatLahir = rs.getString("tempat_lahir");
                String tglLahir = rs.getString("tgl_lahir");
                String kelas = rs.getString("id_kelas");
                String hp = rs.getString("no_hp");
                String alamat = rs.getString("alamat");

                // Menyimpan data ke dalam array
                Object[] baris = {
                    nis,
                    namaSiswa,
                    jenisKelamin,
                    tempatLahir,
                    tglLahir,
                    kelas,
                    hp,
                    alamat
                };

                // Menambahkan data sebagai baris baru di model tabel
                model.addRow(baris);
            }

        } catch (SQLException sqlException) {

            // Menampilkan pesan error jika gagal mengambil data
            JOptionPane.showMessageDialog(
                    null,
                    "Gagal mengambil data!"
            );
        }

        // Menampilkan model yang sudah diisi ke tabel GUI
        tblSiswa.setModel(model);
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
        jPanel5 = new javax.swing.JPanel();
        tFoto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tNIS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tNamaSiswa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cJK = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tHP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tTempatLahir = new javax.swing.JTextField();
        tTanggal = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        cKelas = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        tAlamat = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        tFotoPath = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSiswa = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 20));
        setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(68, 130, 251));
        jPanel2.setPreferredSize(new java.awt.Dimension(733, 60));

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Data Siswa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(922, Short.MAX_VALUE))
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

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 20));
        jPanel4.setPreferredSize(new java.awt.Dimension(976, 485));

        jPanel5.setLayout(new java.awt.BorderLayout());

        tFoto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tFoto.setText("Foto");
        tFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(151, 151, 151)));
        tFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tFotoMouseClicked(evt);
            }
        });
        jPanel5.add(tFoto, java.awt.BorderLayout.CENTER);

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("NIS");

        tNIS.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setText("Nama");

        tNamaSiswa.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Jenis kelamin");

        cJK.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cJK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki - laki", "Perempuan" }));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setText("Tempat Lahir");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("Tanggal Lahir");

        tHP.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setText("HP");

        tTempatLahir.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setText("Alamat");

        cKelas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setText("Kelas");

        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        btnTambah.setBackground(new java.awt.Color(0, 181, 53));
        btnTambah.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni_202557201020/img/typcn_plus.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        jPanel7.add(btnTambah);

        btnUbah.setBackground(new java.awt.Color(255, 153, 0));
        btnUbah.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni_202557201020/img/mdi_edit.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(this::btnUbahActionPerformed);
        jPanel7.add(btnUbah);

        btnHapus.setBackground(new java.awt.Color(255, 0, 0));
        btnHapus.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni_202557201020/img/material-symbols_delete-rounded (1).png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(this::btnHapusActionPerformed);
        jPanel7.add(btnHapus);

        btnReset.setBackground(new java.awt.Color(58, 153, 250));
        btnReset.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni_202557201020/img/ri_reset-left-fill.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);
        jPanel7.add(btnReset);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(tNamaSiswa)
                    .addComponent(tNIS)
                    .addComponent(cJK, 0, 366, Short.MAX_VALUE)
                    .addComponent(tTempatLahir)
                    .addComponent(tTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tHP)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(cKelas, 0, 368, Short.MAX_VALUE)
                    .addComponent(tAlamat)
                    .addComponent(jLabel10)))
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tNIS, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tHP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cKelas)
                            .addComponent(tNamaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cJK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tAlamat))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 20, 20, 20, new java.awt.Color(242, 242, 242)));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel8.setPreferredSize(new java.awt.Dimension(1032, 30));

        tFotoPath.setText("jLabel11");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(tFotoPath, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 935, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tFotoPath, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        tblSiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSiswa.setRowHeight(35);
        tblSiswa.setShowHorizontalLines(true);
        tblSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSiswa);

        jPanel6.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void tblSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaMouseClicked
        // TODO add your handling code here:
        // Mengambil indeks baris yang diklik pada tabel siswa
        int baris = tblSiswa.rowAtPoint(evt.getPoint());

        // Mengambil nilai dari kolom pertama (NIS) pada baris yang diklik dan mengubah ke String
        String nis = tblSiswa.getValueAt(baris, 0).toString();

        // Mengambil nilai dari kolom kedua (Nama Siswa)
        String namaSiswa = tblSiswa.getValueAt(baris, 1).toString();

        // Mengambil objek dari kolom ketiga (Jenis Kelamin)
        Object jkObj = tblSiswa.getValueAt(baris, 2);

        // Mengambil objek dari kolom keempat (Tempat Lahir)
        Object tempatObj = tblSiswa.getValueAt(baris, 3);

        // Mengambil objek dari kolom kelima (Tanggal Lahir)
        Object tglObj = tblSiswa.getValueAt(baris, 4);

        // Mengambil objek dari kolom keenam (Kelas)
        Object kelasObj = tblSiswa.getValueAt(baris, 5);

        // Mengambil objek dari kolom ketujuh (Nomor HP)
        Object hpObj = tblSiswa.getValueAt(baris, 6);

        //mengambil objek dari kolom kedelapan (alamat)
        Object alamatObj = tblSiswa.getValueAt(baris, 7);

        // Menampilkan nilai NIS pada field input dan membuatnya tidak bisa diubah
        tNIS.setText(nis);
        tNIS.setEditable(false);

        // Menampilkan nama siswa ke field input
        tNamaSiswa.setText(namaSiswa);

        // Mengonversi objek menjadi string, jika null maka hasilnya null atau string kosong
        String jenisKelamin = (jkObj != null) ? jkObj.toString() : "";
        String tempatLahir = (tempatObj != null) ? tempatObj.toString() : "";
        String tglLahir = (tglObj != null) ? tglObj.toString() : null;
        String idKelas = (kelasObj != null) ? kelasObj.toString() : null;
        String noHP = (hpObj != null) ? hpObj.toString() : "";
        String alamat = (alamatObj != null) ? alamatObj.toString() : "";

        // Menampilkan tempat lahir, no HP, dan memilih kelas sesuai data
        tTempatLahir.setText(tempatLahir);
        tHP.setText(noHP);
        cKelas.setSelectedItem(idKelas);

        // Jika tanggal lahir tidak null dan tidak kosong, ubah ke format Date dan tampilkan di komponen kalender
        if (tglLahir != null && !tglLahir.isEmpty()) {
            try {
                tTanggal.setDate(java.sql.Date.valueOf(tglLahir));
            } catch (IllegalArgumentException e) {
                // Jika gagal parsing tanggal, kosongkan field tanggal
                tTanggal.setDate(null);
            }
        } else {
            tTanggal.setDate(null);
        }

        // Konversi kode jenis kelamin ke bentuk tampilan yang dipahami pengguna
        switch (jenisKelamin) {
            case "L":
                cJK.setSelectedItem("Laki - laki");
                break;
            case "P":
                cJK.setSelectedItem("Perempuan");
                break;
            default:
                cJK.setSelectedItem(null);
                break;
        }

        try {
            // Query untuk mengambil data alamat dan foto berdasarkan NIS
            String sql = "SELECT alamat, foto FROM siswa WHERE nis = ?";

            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Menyiapkan statement SQL dengan parameter
            PreparedStatement ps = conn.prepareStatement(sql);

            // Mengisi parameter dengan NIS
            ps.setString(1, nis);

            // Menjalankan query dan menyimpan hasilnya
            ResultSet rs = ps.executeQuery();

            // Jika data ditemukan
            if (rs.next()) {
                // Mengambil alamat dan foto dari hasil query
                //String alamat = rs.getString("alamat");
                String foto = rs.getString("foto");

                // Menampilkan alamat ke field input
                tAlamat.setText(alamat);

                // Jika path foto tidak kosong, tampilkan gambar ke label foto
                if (foto != null && !foto.isEmpty()) {
                    ImageIcon icon = new ImageIcon(foto);
                    Image image = icon.getImage().getScaledInstance(tFoto.getWidth(), tFoto.getHeight(), Image.SCALE_SMOOTH);
                    tFotoPath.setText(foto);
                    tFoto.setText(null);
                    tFoto.setIcon(new ImageIcon(image));
                } else {
                    // Jika tidak ada foto, set teks "Foto" dan hapus icon
                    tFoto.setText("Foto");
                    tFoto.setIcon(null);
                }
            }
        } catch (SQLException e) {
            // Menampilkan error ke konsol jika terjadi kesalahan SQL
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_tblSiswaMouseClicked

    private void tFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tFotoMouseClicked
        // TODO add your handling code here:
        // Blok try digunakan untuk menangani kemungkinan error saat memilih dan memuat file gambar
        try {

            // Membuat objek JFileChooser untuk membuka dialog pemilihan file
            JFileChooser chooser = new JFileChooser();

            // Menampilkan dialog pemilihan file dan menyimpan hasil aksi pengguna
            int result = chooser.showOpenDialog(null);

            // Mengecek apakah pengguna menekan tombol "Open"
            if (result == JFileChooser.APPROVE_OPTION) {

                // Mengambil file yang dipilih oleh pengguna
                File file = chooser.getSelectedFile();

                // Memastikan file yang dipilih tidak bernilai null
                if (file != null) {

                    // Membuat objek ImageIcon dari file gambar yang dipilih
                    ImageIcon icon = new ImageIcon(file.toString());

                    // Mengubah ukuran gambar agar sesuai dengan ukuran JLabel tFoto
                    Image image = icon.getImage().getScaledInstance(
                            tFoto.getWidth(),      
                            tFoto.getHeight(),    
                            Image.SCALE_SMOOTH   
                    );

                    // Membuat ImageIcon baru dari gambar yang sudah di-resize
                    ImageIcon ic = new ImageIcon(image);

                    // Menghapus teks yang ada pada JLabel
                    tFoto.setText(null);

                    // Menampilkan gambar pada JLabel
                    tFoto.setIcon(ic);

                    // Mengambil lokasi (path) lengkap file gambar
                    String filename = file.getAbsolutePath();

                    // Menampilkan path gambar ke dalam field tFotoPath
                    tFotoPath.setText(filename);
                }

            } else {

                // Jika pengguna menekan tombol Cancel atau menutup dialog
                System.out.println("Pemilihan file dibatalkan oleh pengguna.");
            }

        } catch (HeadlessException e) {

            // Menampilkan pesan jika terjadi kesalahan saat proses upload gambar
            JOptionPane.showMessageDialog(null, "Error Upload : " + e.getMessage());
        }
    }//GEN-LAST:event_tFotoMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        // Mengambil teks dari field NIS
        String nis = tNIS.getText();

        // Mengambil teks dari field Nama Siswa
        String namaSiswa = tNamaSiswa.getText();

        if (cJK.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Jenis kelamin harus dipilih!");
            return;
        }
        // Mengambil item yang dipilih dari combo box jenis kelamin dan mengubahnya menjadi string
        String jenisKelamin = cJK.getSelectedItem().toString();

        // Variabel untuk menyimpan hasil konversi jenis kelamin (L/P)
        String jK = null;

        // Mengambil teks dari field Tempat Lahir
        String tempatLahir = tTempatLahir.getText();

        // Mengambil tanggal dari komponen kalender
        Date tglLahirDate = tTanggal.getDate();

        // Mengubah tanggal lahir menjadi format "yyyy-MM-dd"
        String tglLahir = new SimpleDateFormat("yyyy-MM-dd").format(tglLahirDate);

        // Mengambil teks dari field nomor HP
        String hp = tHP.getText();

        if (cKelas.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Kelas harus dipilih!");
            return;
        }
        // Mengambil item yang dipilih dari combo box kelas
        String kelas = cKelas.getSelectedItem().toString();

        // Mengambil teks dari field alamat
        String alamat = tAlamat.getText();

        // Mengambil path file dari label path foto
        Object filePathObj = tFotoPath.getText();
        
        String filePath = (filePathObj != null) ? filePathObj.toString() : "";

        // Konversi jenis kelamin dari teks menjadi kode (L atau P)
        switch (jenisKelamin) {
            case "Laki - laki":
                jK = "L";
                break;
            case "Perempuan":
                jK = "P";
                break;
            default:
                jK = null;
                break;
        }

        // Variabel untuk menyimpan path file foto tujuan
        String foto = null;

        // Mengecek apakah ada path file foto yang dipilih
        if (filePath.length() != 0) {
            try {
                // Menyimpan path sumber file
                String sourcePath = filePath;
                File sourceFile = new File(sourcePath);

                // Menentukan folder tujuan untuk menyimpan foto
                String destinationFolderPath = "src/foto/";
                File destinationFolder = new File(destinationFolderPath);

                // Jika folder tujuan belum ada, buat folder tersebut
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdir();
                }

                // Mengambil ekstensi file (contoh: jpg, png, dll)
                String extension = sourcePath.substring(sourcePath.lastIndexOf('.') + 1);

                // Membuat nama file baru yang unik berdasarkan timestamp
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String destinationFileName = "foto-" + timestamp + "." + extension;

                // Membuat file tujuan dengan path dan nama file baru
                File destinationFile = new File(destinationFolderPath + destinationFileName);

                // Menyalin file dari sumber ke tujuan
                Files.copy(sourceFile.toPath(), destinationFile.toPath());

                // Menyimpan path foto yang telah dipindahkan
                foto = destinationFile.getPath();

            } catch (Exception e) {
                // Menampilkan pesan error jika gagal mengupload file
                JOptionPane.showMessageDialog(null, "Gagal upload file: " + e.getMessage());
            }
        } else {
            // Jika tidak ada file yang dipilih, set null
            foto = null;
        }

        try {
            // Query SQL untuk menyimpan data siswa ke database
            String sql = "INSERT INTO siswa(nis,nama_siswa,gender,tempat_lahir,tgl_lahir,alamat,no_hp,id_kelas,foto)"
                    + " VALUES(?,?,?,?,?,?,?,?,?)";

            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Menyiapkan statement SQL dengan parameter
            PreparedStatement statement = conn.prepareStatement(sql);

            // Mengisi nilai parameter satu per satu
            statement.setString(1, nis);
            statement.setString(2, namaSiswa);
            statement.setString(3, jK);
            statement.setString(4, tempatLahir);
            statement.setString(5, tglLahir);
            statement.setString(6, alamat);
            statement.setString(7, hp);
            statement.setString(8, kelas);
            statement.setString(9, foto);

            // Menjalankan query penyimpanan
            statement.execute();

            // Menampilkan pesan bahwa data berhasil disimpan
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        } catch (SQLException e) {
            // Menampilkan pesan jika terjadi kesalahan saat menyimpan data
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!");
        }

        // Memuat ulang data siswa ke tabel
        load_tabel_siswa();

        // Mengosongkan semua input form setelah data disimpan
        reset();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        // Mengambil NIS dari field input
        String nis = tNIS.getText();

        // Mengambil Nama Siswa dari field input
        String namaSiswa = tNamaSiswa.getText();

        // Mengambil nilai dari combo box jenis kelamin dan mengubah menjadi String
        String jenisKelamin = cJK.getSelectedItem().toString();

        // Variabel untuk menyimpan kode jenis kelamin ('L' atau 'P')
        String jK = null;

        // Mengambil Tempat Lahir dari field input
        String tempatLahir = tTempatLahir.getText();

        // Mengambil tanggal lahir dari komponen kalender
        Date tglLahirDate = tTanggal.getDate();

        // Mengubah tanggal lahir menjadi format "yyyy-MM-dd"
        String tglLahir = new SimpleDateFormat("yyyy-MM-dd").format(tglLahirDate);

        // Mengambil Nomor HP dari field input
        String hp = tHP.getText();

        // Mengambil Kelas dari combo box
        String kelas = cKelas.getSelectedItem().toString();

        // Mengambil Alamat dari field input
        String alamat = tAlamat.getText();

        // Mengambil path file dari label path foto
        Object filePathObj = tFotoPath.getText();
        
        String filePath = (filePathObj != null) ? filePathObj.toString() : "";

        // Mengonversi pilihan jenis kelamin ke kode (L/P)
        switch (jenisKelamin) {
            case "Laki - laki":
                jK = "L";
                break;
            case "Perempuan":
                jK = "P";
                break;
            default:
                jK = null;
                break;
        }

        // Variabel untuk menyimpan path foto asli yang tersimpan di database
        String fotoAsli = null;

        try {
            // Query untuk mengambil path foto berdasarkan NIS
            String sql = "SELECT foto FROM siswa WHERE nis = ?";
            Connection conn = koneksi.konek();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nis);
            ResultSet rs = ps.executeQuery();

            // Jika data ditemukan, simpan path foto ke variabel fotoAsli
            if (rs.next()) {
                fotoAsli = rs.getString("foto");
            }
        } catch (SQLException e) {
            // Tampilkan pesan jika gagal mengambil foto dari database
            JOptionPane.showMessageDialog(null, "Gagal mengambil foto asli: " + e.getMessage());
        }

        // Menentukan apakah foto diubah oleh pengguna
        boolean fotoDiubah = (fotoAsli == null && !filePath.isEmpty())
                || (fotoAsli != null && !fotoAsli.equals(filePath));

        // Jika foto diubah, variabel 'foto' akan diisi dengan path baru
        String foto = fotoAsli;

        if (fotoDiubah) {
            try {
                // Ambil file dari path baru
                File sourceFile = new File(filePath);

                // Dapatkan ekstensi file
                String extension = filePath.substring(filePath.lastIndexOf('.') + 1);

                // Buat nama file baru berdasarkan waktu agar unik
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String destinationPath = "src/foto/foto-" + timestamp + "." + extension;

                // Salin file ke folder tujuan
                File destFile = new File(destinationPath);
                Files.copy(sourceFile.toPath(), destFile.toPath());

                // Simpan path tujuan ke variabel 'foto'
                foto = destinationPath;

            } catch (Exception e) {
                // Tampilkan pesan jika gagal upload file
                JOptionPane.showMessageDialog(null, "Gagal upload file: " + e.getMessage());
            }
        }

        try {
            // Query SQL berbeda tergantung apakah foto diubah atau tidak
            String sql2;
            if (fotoDiubah) {
                sql2 = "UPDATE siswa SET nama_siswa=?, gender=?, tempat_lahir=?, "
                        + "tgl_lahir=?, alamat=?, no_hp=?, id_kelas=?, foto=? WHERE nis=?";
            } else {
                sql2 = "UPDATE siswa SET nama_siswa=?, gender=?, tempat_lahir=?, "
                        + "tgl_lahir=?, alamat=?, no_hp=?, id_kelas=? WHERE nis=?";
            }

            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Menyiapkan statement untuk eksekusi SQL
            PreparedStatement statement = conn.prepareStatement(sql2);

            // Menetapkan parameter umum
            statement.setString(1, namaSiswa);
            statement.setString(2, jK);
            statement.setString(3, tempatLahir);
            statement.setString(4, tglLahir);
            statement.setString(5, alamat);
            statement.setString(6, hp);
            statement.setString(7, kelas);

            // Jika foto diubah, tetapkan parameter tambahan
            if (fotoDiubah) {
                statement.setString(8, foto);
                statement.setString(9, nis);
            } else {
                statement.setString(8, nis);
            }

            // Eksekusi perintah update
            statement.execute();

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");

        } catch (SQLException e) {
            // Tampilkan pesan jika update gagal
            JOptionPane.showMessageDialog(null, "Gagal memperbarui data: " + e.getMessage());
        }

        // Muat ulang tabel agar perubahan terlihat
        load_tabel_siswa();

        // Kosongkan form setelah proses selesai
        reset();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        // Mengambil nilai NIS dari field input
        String nis = tNIS.getText();

        // Menyiapkan perintah SQL untuk menghapus data siswa berdasarkan NIS
        String sql = "DELETE FROM siswa WHERE nis=?";

        try {

            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Menyiapkan statement SQL untuk dieksekusi
            PreparedStatement statement = conn.prepareStatement(sql);

            // Mengisi parameter pertama (tanda ?) dengan nilai NIS
            statement.setString(1, nis);

            // Menjalankan perintah DELETE
            statement.execute();

            // Menampilkan pesan bahwa data berhasil dihapus
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");

        } catch (SQLException e) {

            // Menampilkan pesan jika terjadi kesalahan saat menghapus
            JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e.getMessage());
        }

        // Memuat ulang data tabel agar tampilan diperbarui
        load_tabel_siswa();

        // Mengosongkan semua input form setelah data dihapus
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
    private javax.swing.JComboBox<String> cJK;
    private javax.swing.JComboBox<String> cKelas;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tAlamat;
    private javax.swing.JLabel tFoto;
    private javax.swing.JLabel tFotoPath;
    private javax.swing.JTextField tHP;
    private javax.swing.JTextField tNIS;
    private javax.swing.JTextField tNamaSiswa;
    private com.toedter.calendar.JDateChooser tTanggal;
    private javax.swing.JTextField tTempatLahir;
    private javax.swing.JTable tblSiswa;
    // End of variables declaration//GEN-END:variables
}
