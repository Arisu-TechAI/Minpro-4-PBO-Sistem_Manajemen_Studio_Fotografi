/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */

// Class yang berfungsi untuk menangani proses Transaksi (Pembayaran) di program Jasa Fotografi

public class Transaksi {
    private String idTransaksi;
    private Client client; 
    private LayananFotografi paket; 
    private String TanggalSesi;
    private String status; 

    public Transaksi(String idTransaksi, Client client, LayananFotografi paket, String TanggalSesi) {
        this.idTransaksi = idTransaksi;
        this.client = client;
        this.paket = paket;
        this.TanggalSesi = TanggalSesi;
        this.status = "Menunggu Validasi Atmin";
    }

    // return method
    public String getIdTransaksi() { 
        return idTransaksi; }
    public Client getClient() { 
        return client; }
    public LayananFotografi getPaket() { 
        return paket; }
    public String getStatus() { 
        return status; }
    public void setStatus(String status) { 
        this.status = status; }
    public String getTanggalSesi() { 
        return TanggalSesi; }

    
    // Method Overloading
    public void cetakStruk() {
        cetakStruk(true);
    }

    public void cetakStruk(boolean detail) {
        System.out.println("------------------------------------");
        System.out.println("ID Transaksi : " + idTransaksi);
        System.out.println("Nama Klien   : " + client.getNama() + " (" + client.getNoTelp() + ")");
        client.tampilkanPeran(); // Polymorphism (Overriding)
        System.out.println("Tanggal Sesi : " + TanggalSesi);
        System.out.println("Paket        : " + paket.getInfoDasar());
        
        // Penerapan Polymorphism (Overriding)
        if(detail) {
            System.out.println("--- Spesifikasi Paket ---");
            paket.detailLayanan();
        }

        // Menerapkan logika Interface secara otomatis
        double totalAkhir = paket.getHarga();
        if (client instanceof PotonganHarga) {
            double diskon = ((PotonganHarga) client).hitungDiskon(paket.getHarga());
            totalAkhir -= diskon;
            System.out.println("Diskon VIP   : -Rp" + diskon);
        }
        
        System.out.println("Total Bayar  : Rp" + totalAkhir);
        System.out.println("Status       : " + status);
        System.out.println("------------------------------------");
    }

}