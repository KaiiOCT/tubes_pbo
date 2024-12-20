package laptop;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

class Transaksi {
    int id;
    LocalDate tanggalSewa;
    LocalDate tanggalKembali;
    int totalHarga;
    int idLaptop;

    public Transaksi(int id, LocalDate tanggalSewa, LocalDate tanggalKembali, int totalHarga, int idLaptop) {
        this.id = id;
        this.tanggalSewa = tanggalSewa;
        this.tanggalKembali = tanggalKembali;
        this.totalHarga = totalHarga;
        this.idLaptop = idLaptop;
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggalSewa() {
        return tanggalSewa;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public int getIdLaptop() {
        return idLaptop;
    }
}