package laptop;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Transaksi {
    int id;
    com.toedter.calendar.JDateChooser tanggalSewa;
    com.toedter.calendar.JDateChooser tanggalKembali;
    int idLaptop;
    private Connection conn;
    private static final Koneksi k = new Koneksi();

    public Transaksi(int id, com.toedter.calendar.JDateChooser tanggalSewa, com.toedter.calendar.JDateChooser tanggalKembali, int idLaptop) {
        this.id = id;
        this.tanggalSewa = tanggalSewa;
        this.tanggalKembali = tanggalKembali;
        this.idLaptop = idLaptop;
    }

    public int getId() {
        return id;
    }

    public Date getTanggalSewa() {
        return new Date(tanggalSewa.getDate().getTime());
    }

    public Date getTanggalKembali() {
        return new Date(tanggalKembali.getDate().getTime());
    }

    public int getIdLaptop() {
        return idLaptop;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Insert Transaksi
    public boolean insertTransaksi() {
        try {
            conn = k.getConnection();
            String query = """
                INSERT INTO transaksi (tanggal_sewa, tanggal_kembali, total_harga, id_laptop)
                SELECT ?, ?, DATEDIFF(?, ?) * harga_sewa, ?
                FROM laptop
                WHERE id = ?
            """;

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setDate(1, getTanggalSewa());
            ps.setDate(2, getTanggalKembali());
            ps.setDate(3, getTanggalKembali()); // Untuk DATEDIFF
            ps.setDate(4, getTanggalSewa()); // Untuk DATEDIFF
            ps.setInt(5, getIdLaptop());
            ps.setInt(6, getIdLaptop());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal insert data transaksi: " + e.getMessage());
            return false;
        }
    }

    // Update Transaksi
    public boolean updateTransaksi() {
        try {
            conn = k.getConnection();
            String query = """
                UPDATE transaksi t
                JOIN laptop l ON t.id_laptop = l.id
                SET t.tanggal_sewa = ?,
                    t.tanggal_kembali = ?,
                    t.total_harga = DATEDIFF(?, ?) * l.harga_sewa,
                    t.id_laptop = ?
                WHERE t.id = ?
            """;

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setDate(1, getTanggalSewa());
            ps.setDate(2, getTanggalKembali());
            ps.setDate(3, getTanggalKembali()); // Untuk DATEDIFF
            ps.setDate(4, getTanggalSewa()); // Untuk DATEDIFF
            ps.setInt(5, getIdLaptop());
            ps.setInt(6, getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal update data transaksi: " + e.getMessage());
            return false;
        }
    }

    // Delete Transaksi
    public boolean deleteTransaksi() {
        try {
            conn = k.getConnection();
            String query = "DELETE FROM transaksi WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal delete data transaksi: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // Testing
        com.toedter.calendar.JDateChooser sewa = new com.toedter.calendar.JDateChooser();
        sewa.setDate(new java.util.Date());
        com.toedter.calendar.JDateChooser kembali = new com.toedter.calendar.JDateChooser();
        kembali.setDate(new java.util.Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000)); // 3 hari setelah tanggal sewa

        Transaksi transaksi = new Transaksi(1, sewa, kembali, 2);

        // Insert
        if (transaksi.insertTransaksi()) {
            System.out.println("Data transaksi berhasil ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan data transaksi.");
        }

        // Update
        sewa.setDate(new java.util.Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)); // 1 hari dari sekarang
        kembali.setDate(new java.util.Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000)); // 5 hari dari sekarang
        transaksi = new Transaksi(1, sewa, kembali, 2);
        if (transaksi.updateTransaksi()) {
            System.out.println("Data transaksi berhasil diperbarui.");
        } else {
            System.out.println("Gagal memperbarui data transaksi.");
        }

        // Delete
        if (transaksi.deleteTransaksi()) {
            System.out.println("Data transaksi berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus data transaksi.");
        }
    }
}
