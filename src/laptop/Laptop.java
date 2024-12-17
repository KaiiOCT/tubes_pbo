package laptop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


abstract class Laptop {
    protected int id;
    protected String nama;
    protected int kategori;

    public Laptop(int id, String nama, int kategori) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
    }

    public int getId() {
        return this.id;
    }

    public String getNama() {
        return this.nama;
    }

    public int getKategori() {
        return this.kategori;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public String setNama(String nama) {
        return this.nama = nama;
    }

    public int setKategori(int kategori) {
        return this.kategori = kategori;
    }

    public abstract boolean updateLaptop();
}

class ManageLaptop extends Laptop {
    private Connection conn;
    private final Koneksi k = new Koneksi();

    public ManageLaptop(int id, String nama, int kategori) {
        super(id, nama, kategori);
    }

    // Insert Laptop
    public boolean insertLaptop() {
        try {
            conn = k.getConnection();
            String query = "INSERT INTO laptop(nama, os) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, getNama());
            ps.setInt(2, getKategori());

            int rowAffected = ps.executeUpdate();

            return rowAffected == 1;

        } catch (SQLException e) {
            return false;
        }
    }

    // update Laptop
    @Override
    public boolean updateLaptop() {
        try{
            conn = k.getConnection();
            String query = "UPDATE laptop SET nama = ?, os = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            if(id == getId()){
                ps.setString(1, this.nama);
                ps.setInt(2, this.kategori);
                ps.setInt(3, this.id);

                ps.executeUpdate();

                return true;
            } else {
                System.out.println("ID tidak ditemukan");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Gagal update laptop: " + e.getMessage());
            return false;
        }
    }

    // Delete Laptop
    public boolean deleteLaptop() {
        try {
            conn = k.getConnection();
            String query = "DELETE FROM laptop WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, this.getId());

            int rowAffected = ps.executeUpdate();

            System.out.println(this.getId());
            return rowAffected == 1;

        } catch (SQLException e) {
            System.err.println("Gagal delete laptop: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        ManageLaptop laptop = new ManageLaptop(9, "berman abraham", 2);
//        laptop.updateLaptop(9, "abraham ngok ngok", 2);
    }
}