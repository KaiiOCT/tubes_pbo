package laptop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class User {
    private String nama;
    private String password;

    public User(String nama, String password) {
        this.nama = nama;
        this.password = password;
    }

    public String getNama() {
        return this.nama;
    }

    public String getPassword() {
        return this.password;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh null");
        } else {
            this.nama = nama;
        }
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh null");
        } else {
            this.password = password;
        }
    }

    // Register Akun
    public static boolean register(String nama, String password) {
        Connection conn;
        PreparedStatement ps;
        final Koneksi k = new Koneksi();

        try {
            User user = new User(nama, password);
            conn = k.getConnection();

            String query = "INSERT INTO user(username, password) VALUES (?,?)";

            ps = conn.prepareStatement(query);

            ps.setString(1, user.getNama());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            System.out.println("Registrasi berhasil");
            return true;
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan saat menyimpan data ke database: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Gagal registrasi: " + e.getMessage());
        }
        return false;
    }

    // Login Akun
    public static boolean login(String nama, String password) {
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        final Koneksi k = new Koneksi();

        try {
            conn = k.getConnection();

            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, nama);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login berhasil untuk user: " + nama);
                return true;
            } else {
                System.out.println("Login gagal: Nama atau password salah.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat login: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        User.register("berman", "admin");
    }
}


