/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import model.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author USER
 */
public class StudioDAO {
    private Connection conn;

    public StudioDAO() {
        conn = Koneksi.getKoneksi();
    }

    // Menerapkan Prepared Statement
    public Akun getAkunByUsername(String username) {
        String sql = "SELECT * FROM akun WHERE nama = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id_akun");
                String noTelp = rs.getString("no_telp");
                String role = rs.getString("role");

                if (role.equals("ADMIN")) return new Admin(id, username);
                else if (role.equals("VIP")) return new ClientVIP(id, username, noTelp);
                else return new Client(id, username, noTelp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertAkun(Client client) {
        String sql = "INSERT INTO akun (id_akun, nama, no_telp, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getId());
            stmt.setString(2, client.getNama());
            stmt.setString(3, client.getNoTelp());
            stmt.setString(4, (client instanceof ClientVIP) ? "VIP" : "CLIENT");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<LayananFotografi> getAllPaket() {
        ArrayList<LayananFotografi> list = new ArrayList<>();
        String sql = "SELECT * FROM layanan";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String kode = rs.getString("kode_paket");
                String nama = rs.getString("nama_paket");
                double harga = rs.getDouble("harga");
                String tipe = rs.getString("tipe");
                int detail = rs.getInt("detail_tambahan");

                if (tipe.equals("REGULER")) list.add(new PaketReguler(kode, nama, harga, detail));
                else list.add(new PaketEvent(kode, nama, harga, detail));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void insertTransaksi(Transaksi t) {
        String sql = "INSERT INTO transaksi (id_transaksi, id_akun, kode_paket, tanggal_sesi, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getIdTransaksi());
            stmt.setString(2, t.getClient().getId());
            stmt.setString(3, t.getPaket().getKodePaket());
            stmt.setString(4, t.getTanggalSesi());
            stmt.setString(5, t.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Mengambil transaksi gabungan dengan table akun dan layanan
    public ArrayList<Transaksi> getAllTransaksi() {
        ArrayList<Transaksi> list = new ArrayList<>();
        String sql = "SELECT t.*, a.nama, a.no_telp, a.role, l.nama_paket, l.harga, l.tipe, l.detail_tambahan " +
             "FROM transaksi t JOIN akun a ON t.id_akun = a.id_akun JOIN layanan l ON t.kode_paket = l.kode_paket";
        
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client client;
                if (rs.getString("role").equals("VIP")) client = new ClientVIP(rs.getString("id_akun"), rs.getString("nama"), rs.getString("no_telp"));
                else client = new Client(rs.getString("id_akun"), rs.getString("nama"), rs.getString("no_telp"));

                LayananFotografi paket;
                if (rs.getString("tipe").equals("REGULER")) paket = new PaketReguler(rs.getString("kode_paket"), rs.getString("nama_paket"), rs.getDouble("harga"), rs.getInt("detail_tambahan"));
                else paket = new PaketEvent(rs.getString("kode_paket"), rs.getString("nama_paket"), rs.getDouble("harga"), rs.getInt("detail_tambahan"));

                Transaksi t = new Transaksi(rs.getString("id_transaksi"), client, paket, rs.getString("tanggal_sesi"));
                t.setStatus(rs.getString("status"));
                list.add(t);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean updateStatusTransaksi(String idTransaksi) {
        String sql = "UPDATE transaksi SET status = 'Lunas (Divalidasi Atmin)' WHERE id_transaksi = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idTransaksi);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}
