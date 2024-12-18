package laptop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class Laptop {
    protected int id;
    protected String nama;
    protected int prosesor;
    protected String deskripsi;
    protected int hargaSewa;
    protected int idStatus;

    public Laptop(int id, String nama, int prosesor, String deskripsi, int hargaSewa, int idStatus) {
        this.id = id;
        this.nama = nama;
        this.prosesor = prosesor;
        this.deskripsi = deskripsi;
        this.hargaSewa = hargaSewa;
        this.idStatus = idStatus;
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

    public int getHargaSewa() {
        return hargaSewa;
    }

    public int getIdStatus() {
        return idStatus;
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

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public abstract boolean updateLaptop();
}

class ManageLaptop extends Laptop {
    private Connection conn;
    private final Koneksi k = new Koneksi();

    public ManageLaptop(int id, String nama, int prosesor, String deskripsi, int hargaSewa, int idStatus) {
        super(id, nama, prosesor, deskripsi, hargaSewa, idStatus);
    }

    // Insert Laptop
    public boolean insertLaptop() {
        try {
        conn = k.getConnection();
            String query = "INSERT INTO laptop (nama, prosesor, deskripsi, harga_sewa, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, nama);
            ps.setInt(2, prosesor);
            ps.setString(3, deskripsi);
            ps.setInt(4, hargaSewa);
            ps.setInt(5, idStatus);

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

            ps.setString(1, nama);
            ps.setInt(2, prosesor);
            ps.setString(3, deskripsi);
            ps.setInt(4, hargaSewa);
            ps.setInt(5, idStatus);
            ps.setInt(6, id);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal update data laptop: " + e.getMessage());
            return false;
        }
    }
}