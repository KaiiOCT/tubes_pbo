package laptop;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Koneksi {
    private final MysqlDataSource dataSource = new MysqlDataSource();
    private final String DB_URL = "jdbc:mysql://localhost:3306/" + "laptopers?serverTimezone=Asia/Jakarta";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "";



    public Connection getConnection() throws SQLException {
        dataSource.setUrl(DB_URL);
        dataSource.setUser(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        Connection conn = dataSource.getConnection();

        return conn;
    }
    
    public static void main(String[] args) {
        Koneksi koneksi = new Koneksi();
        
        try (Connection conn = koneksi.getConnection()) {
            if (conn != null) {
                System.out.println("Koneksi ke database berhasil!");
            }
        } catch (SQLException e) {
            System.err.println("Gagal terhubung ke database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


