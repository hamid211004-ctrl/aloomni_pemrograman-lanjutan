/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumni_202557201020.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class koneksi {
    //mendeklarasikan variabel koneksi sebagai static agar bisa diakses dari mana saja didalam class
    private static Connection mysqlconfig;
    
    //method static untuk membuka koneksi ke database MySQL
    public static Connection konek(){
        try {
            //URL koneksi ke database: jdbc:mysql://[host]:[port]/[nama_database]
            String url = "jdbc:mysql://localhost:3306/alumni_202557201020";

            //username database
            String user = "root";

            //password database
            String pass = "";

            //membuka koneksi ke database dan menyimpannya divariabel mysqlconfig
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (SQLException sQLException) {
            //menampilkan pesan error jika koneksi gagal
            System.err.println(sQLException.getMessage());
        }
        
        //mengembalikan objek koneksi (bisa null jika gagal)
        return mysqlconfig;
    }
}
