/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.StudioDAO;
import model.*;
import java.util.ArrayList;

/**
 *
 * @author USER
 */


public class Transaksi_Controller {
    private StudioDAO dao;

    public Transaksi_Controller() {
        // Mengambil data dari DAO
        dao = new StudioDAO();
    }

    public String generateIdTransaksi() {
        // Generate ID transaksi otomatis
        return String.format("TRX-%03d", dao.getAllTransaksi().size() + 1);
    }

    public String generateIdClient() {
        return "CLN-" + System.currentTimeMillis(); // Menggunakan timestamp agar unik
    }

    public Akun cariAkunByUsername(String username) {
        return dao.getAkunByUsername(username); // Langsung ke DB
    }

    public Client registrasiKlien(String username, String noTelp, boolean isVip) {
        Client klienBaru = isVip ? new ClientVIP(generateIdClient(), username, noTelp) 
                                 : new Client(generateIdClient(), username, noTelp);
        dao.insertAkun(klienBaru); // Simpan ke DB
        return klienBaru;
    }

    // mengambil daftar paket dari database
    public ArrayList<LayananFotografi> getDaftarPaket() {
        return dao.getAllPaket();
    }

    public LayananFotografi cariPaket(String kode) {
        for (LayananFotografi p : dao.getAllPaket()) {
            if (p.getKodePaket().equalsIgnoreCase(kode)) return p;
        }
        return null;
    }

    // menyimpan data transaksi ke database
    public void tambahTransaksi(Client client, LayananFotografi paket, String tanggal) {
        Transaksi t = new Transaksi(generateIdTransaksi(), client, paket, tanggal);
        dao.insertTransaksi(t);
    }

    public void lihatPesananKlien(String namaKlien) {
        boolean ada = false;
        for (Transaksi t : dao.getAllTransaksi()) {
            if (t.getClient().getNama().equalsIgnoreCase(namaKlien)) {
                t.cetakStruk(true);
                ada = true;
            }
        }
        if (!ada) System.out.println(">> Belum ada riwayat pesanan. <<");
    }

    public void lihatSemuaPesanan() {
        ArrayList<Transaksi> list = dao.getAllTransaksi();
        if (list.isEmpty()) System.out.println(">> Belum ada transaksi masuk. <<");
        else for (Transaksi t : list) t.cetakStruk(false);
    }

    // update status transaksi ke database
    public boolean validasiPembayaran(String idTransaksi) {
        return dao.updateStatusTransaksi(idTransaksi);
    }

    // menampilkan statistik transaksi
    public void tampilkanStatistik() {
        double totalPendapatan = 0;
        int menungguValidasi = 0;
        int jumlahTransaksiVIP = 0;

        // Mengambil data transaksi dari database
        ArrayList<Transaksi> semuaTransaksi = dao.getAllTransaksi();

        for (Transaksi t : semuaTransaksi) {
            // Hitung pendapatan dari transaksi yang sudah lunas
            if (t.getStatus().contains("Lunas")) {
                double harga = t.getPaket().getHarga();

                // Cek apakah klien ini VIP untuk menghitung potongan diskon
                if (t.getClient() instanceof PotonganHarga) {
                    harga -= ((PotonganHarga) t.getClient()).hitungDiskon(harga);
                }
                totalPendapatan += harga;
            } else {
                menungguValidasi++;
            }

            // Hitung berapa kali klien VIP melakukan pemesanan
            if (t.getClient() instanceof ClientVIP) {
                jumlahTransaksiVIP++;
            }
        }

        System.out.println("\n======================================");
        System.out.println("STATISTIK KINERJA STUDIO FOTOGRAFI");
        System.out.println("======================================");
        System.out.println("Total Transaksi Masuk : " + semuaTransaksi.size() + " Pesanan");
        System.out.println("Pesanan dari Klien VIP: " + jumlahTransaksiVIP + " Pesanan");
        System.out.println("Menunggu Validasi     : " + menungguValidasi + " Antrean");
        System.out.println("Total Pendapatan      : Rp" + totalPendapatan);
        System.out.println("======================================");
        }
}