//package laptop;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Main {
//    private Connection conn;
//    private final Koneksi k = new Koneksi();
//
//    // register Akun
//    public void insertUser(User user) throws SQLException {
//        conn = k.getConnection();
//        String query = "INSERT INTO user(username, password) VALUES (?,?)";
//        PreparedStatement ps = conn.prepareStatement(query);
//
//        ps.setString(1, user.getNama());
//        ps.setString(2, user.getPassword());
//
//        ps.executeUpdate();
//        System.out.println("Berhasil register");
//    }
//
//    // login Akun
//    public void loginUser(User user) throws SQLException {
//        conn = k.getConnection();
//        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
//        PreparedStatement ps = conn.prepareStatement(query);
//
//        ps.setString(1, user.getNama());
//        ps.setString(2, user.getPassword());
//
//        ps.executeQuery();
//        System.out.println("Berhasil login");
//    }
//
//    // Insert Laptop
//    public void insertLaptop(ManageLaptop laptop) throws SQLException {
//        conn = k.getConnection();
//        String query = "INSERT INTO laptop(nama, os) VALUES (?,?)";
//        PreparedStatement ps = conn.prepareStatement(query);
//
//        ps.setString(1, laptop.getNama());
//        ps.setInt(2, laptop.getKategori());
//
//        ps.executeUpdate();
//        System.out.println("Berhasil insert laptop");
//    }
//
//    // Read Laptop
//    public void readLaptop(ManageLaptop laptop) throws SQLException {
//        conn = k.getConnection();
//        String query = "SELECT * FROM laptop JOIN kategori ON laptop.os = kategori.id";
//        PreparedStatement ps = conn.prepareStatement(query);
//
//        ps.executeQuery();
//        System.out.println("Berhasil read laptop");
//    }
//
//    // Update Laptop
//    public void updateLaptop(ManageLaptop laptop) throws SQLException {
//        conn = k.getConnection();
//        String query = "UPDATE laptop SET nama = ?, os = ? WHERE id = ?";
//        PreparedStatement ps = conn.prepareStatement(query);
//
//        ps.setString(1, laptop.getNama());
//        ps.setInt(2, laptop.getKategori());
//        ps.setInt(3, laptop.getId());
//
//        ps.executeUpdate();
//    }
//
//    // Delete Laptop
//    public void deleteLaptop(ManageLaptop laptop) throws SQLException {
//        conn = k.getConnection();
//        String query = "DELETE FROM laptop WHERE id = ?";
//        PreparedStatement ps = conn.prepareStatement(query);
//
//        ps.setInt(1, laptop.getId());
//
//        ps.executeUpdate();
//        System.out.println("Berhasil delete laptop");
//    }
//
//    public static void main(String[] args) {
//        Main main = new Main();
//        ManageLaptop laptop = new ManageLaptop( 4, "bopal ngok", 2);
//        try {
//            main.updateLaptop(laptop);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
