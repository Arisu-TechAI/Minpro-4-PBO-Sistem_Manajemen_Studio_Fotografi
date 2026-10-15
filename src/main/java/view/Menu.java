/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Transaksi_Controller;
import model.*;
import java.util.Scanner;

/**
 *
 * @author USER
 */


public class Menu {
    private Transaksi_Controller controller = new Transaksi_Controller();
    private Scanner scanner = new Scanner(System.in);
    private Admin adminApp = new Admin("ADM-001", "Atmin");

    public void mulaiAplikasi() {
        String pilihan;
        do {
            System.out.println("\n======================================");
            System.out.println("JASA STUDIO FOTOGRAFI");
            System.out.println("======================================");
            System.out.println("1. Login / Masuk Akun");
            System.out.println("0. Keluar");
            System.out.print("Pilih Menu: ");
            pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1":
                    prosesLogin();
                    break;
                case "0":
                    System.out.println("\n Menutup Program.......");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (!pilihan.equals("0"));
    }

    private void prosesLogin() {
        System.out.println("\n--- HALAMAN LOGIN ---");
        System.out.print("Masukkan Username Anda: ");
        String username = scanner.nextLine().trim();

        // Validasi input username tidak boleh kosong
        while (!username.matches("^[a-zA-Z0-9_]{3,}$")) {
            System.out.print("Username minimal 3 karakter (huruf/angka/underscore). Masukkan ulang: ");
            username = scanner.nextLine().trim();
        }

        // Cek Username sudah ada atau belum
        Akun akunDitemukan = controller.cariAkunByUsername(username);

        if (akunDitemukan != null) {
            // Periksa Role Akun
            if (akunDitemukan instanceof Admin) {
                System.out.println("Login Berhasil! Selamat datang, Endministrator.");
                panelAdmin();
            } else if (akunDitemukan instanceof Client) {
                System.out.println("Login Berhasil! Selamat datang kembali, " + akunDitemukan.getNama() + " .");
                panelKlien((Client) akunDitemukan);
            }
        } else {
            // Jika username belum terdaftar
            System.out.println("Username '" + username + "' belum terdaftar dalam sistem.");
            System.out.print("Apakah Anda ingin membuat akun baru dengan username ini? (Y/N): ");
            String buat = scanner.nextLine().trim();

            if (buat.equalsIgnoreCase("Y")) {
                System.out.print("Masukkan No Telepon (10-14 digit angka): ");
                String noTelp = scanner.nextLine().trim();
                while (!noTelp.matches("^\\d{10,14}$")) {
                    System.out.print("Format salah! Masukkan 10-14 digit angka: ");
                    noTelp = scanner.nextLine().trim();
                }

                System.out.print("Daftar sebagai Member VIP (Dapatkan Potongan Harga 15%)? (Y/N): ");
                boolean isVip = scanner.nextLine().trim().equalsIgnoreCase("Y");

                // Registrasi akun baru
                Client akunBaru = controller.registrasiKlien(username, noTelp, isVip);
                System.out.println("Akun berhasil dibuat!");
                panelKlien(akunBaru);
            } else {
                System.out.println("Proses login dibatalkan.");
            }
        }
    }

    private void panelAdmin() {
        String menu;
        do {
            System.out.println("\n--- MENU ATMIN ---");
            adminApp.tampilkanPeran();
            System.out.println("1. Lihat Semua Pesanan Masuk");
            System.out.println("2. Validasi Pembayaran Transaksi");
            System.out.println("3. Lihat Statistik Pendapatan Studio");
            System.out.println("0. Logout ");
            System.out.print("Pilih: ");
            menu = scanner.nextLine().trim();

            switch (menu) {
                case "1":
                    controller.lihatSemuaPesanan();
                    break;
                case "2":
                    System.out.print("Masukkan ID Transaksi yang ingin divalidasi (Cth: TRX-001): ");
                    String id = scanner.nextLine().trim();
                    if (controller.validasiPembayaran(id)) {
                        System.out.println("tatus pembayaran transaksi " + id + " berhasil diubah menjadi LUNAS!");
                    } else {
                        System.out.println("ID Transaksi tidak ditemukan.");
                    }
                    break;
                case "3":
                    controller.tampilkanStatistik();
                    break;
                case "0":
                    System.out.println("Logout berhasil.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (!menu.equals("0"));
    }

    private void panelKlien(Client userAktif) {
        String menu;
        do {
            System.out.println("\n--- MENU USER " + userAktif.getNama().toUpperCase() + " ---");
            userAktif.tampilkanPeran();
            System.out.println("1. Buat Pesanan Jasa Fotografi Baru");
            System.out.println("2. Cek Status Pesanan Saya");
            System.out.println("0. Logout ");
            System.out.print("Pilih: ");
            menu = scanner.nextLine().trim();

            switch (menu) {
                case "1":
                    formPesananBaru(userAktif);
                    break;
                case "2":
                    controller.lihatPesananKlien(userAktif.getNama());
                    break;
                case "0":
                    System.out.println("Logout berhasil.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (!menu.equals("0"));
    }

    private void formPesananBaru(Client userAktif) {
        System.out.println("\n--- FORM PEMESANAN BARU ---");
        System.out.println("Pemesan : " + userAktif.getNama() + " (" + userAktif.getNoTelp() + ")");

        System.out.println("\n=== KATALOG PAKET FOTOGRAFI ===");
        for (LayananFotografi p : controller.getDaftarPaket()) {
            System.out.println(p.getInfoDasar());
        }

        LayananFotografi paketPilih = null;
        while (paketPilih == null) {
            System.out.print("\nMasukkan Kode Paket yang dipilih: ");
            String kode = scanner.nextLine().trim();
            paketPilih = controller.cariPaket(kode);

            if (paketPilih == null) {
                System.out.println("Kode paket tidak ditemukan. Silakan cek kembali katalog!");
            }
        }

        System.out.print("Masukkan Tanggal Sesi (Format: DD-MM-YYYY): ");
        String tgl = scanner.nextLine().trim();
        // Validasi tanggal ketat
        while (!tgl.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(20\\d\\d)$")) {
            System.out.print("Format tanggal salah! Gunakan format DD-MM-YYYY (Contoh: 01-10-2026): ");
            tgl = scanner.nextLine().trim();
        }
        controller.tambahTransaksi(userAktif, paketPilih, tgl);
        System.out.println("\n Pesanan berhasil dibuat! Silakan lakukan pembayaran dan konfirmasi ke Atmin.");
    }
}