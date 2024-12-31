package laptop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class Laptop {
    protected int id;
    protected String nama;
    protected int prosesor;
    protected String deskripsi;

    public Laptop(int id, String nama, int prosesor, String deskripsi) {
        this.id = id;
        this.nama = nama;
        this.prosesor = prosesor;
        this.deskripsi = deskripsi;
    }

    public Laptop(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getProsesor() {
        return prosesor;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setProsesor(int prosesor) {
        this.prosesor = prosesor;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public abstract boolean updateLaptop();
}

class ManageLaptop extends Laptop {
    private int hargaSewa;
    private int idStatus;

    private Connection conn;
    private final Koneksi k = new Koneksi();

    public ManageLaptop(int id, String nama, int prosesor, String deskripsi, int hargaSewa, int idStatus) {
        super(id, nama, prosesor, deskripsi);
        this.hargaSewa = hargaSewa;
        this.idStatus = idStatus;
    }

    public ManageLaptop(int id, String nama) {
        super(id, nama);
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    // Insert Laptop
    public boolean insertLaptop() {
        try {
            conn = k.getConnection();
            String query = "INSERT INTO laptop (nama, prosesor, deskripsi, harga_sewa, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, getNama());
            ps.setInt(2, getProsesor());
            ps.setString(3, getDeskripsi());
            ps.setInt(4, getHargaSewa());
            ps.setInt(5, getIdStatus());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal insert data laptop: " + e.getMessage());
            return false;
        }
    }

    // Update Laptop
    @Override
    public boolean updateLaptop() {
        try {
            conn = k.getConnection();
            String query = "UPDATE laptop SET nama = ?, prosesor = ?, deskripsi = ?, harga_sewa = ?, status = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, getNama());
            ps.setInt(2, getProsesor());
            ps.setString(3, getDeskripsi());
            ps.setInt(4, getHargaSewa());
            ps.setInt(5, getIdStatus());
            ps.setInt(6, getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal update data laptop: " + e.getMessage());
            return false;
        }
    }

    // Delete Laptop
    public boolean deleteLaptop() {
        try {
            conn = k.getConnection();
            String query = "DELETE FROM laptop WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal delete data laptop: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return this.getNama(); // Sesuaikan dengan nama field untuk nama laptop
    }
}
