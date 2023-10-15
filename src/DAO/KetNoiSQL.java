package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KetNoiSQL {
    public static Connection getConnection() {
    String url = "net.sourceforge.jtds.jdbc.Driver";
        try {
            Class.forName(url);
            String dbUrl = "jdbc:sqlserver://DESKTOP-KRD2V7D: 1433;" +
            "user=sa; password=123; databaseName=QuanLyThuVien; encrypt=false";
            return DriverManager.getConnection(dbUrl);
//          return DriverManager.getConnection(dbUrl,user, pass);
        } catch (Exception ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args) {
        KetNoiSQL.getConnection();
    }
}
