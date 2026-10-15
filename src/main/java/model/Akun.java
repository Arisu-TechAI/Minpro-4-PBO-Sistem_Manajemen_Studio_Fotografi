/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author USER
 */

// Penerapan Abstract Class
public abstract class Akun {
    protected String id;
    protected String nama;

    public Akun(String id_akun, String nama) {
        this.id = id_akun;
        this.nama = nama;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }

    // Abstract method 
    public abstract void tampilkanPeran();
}
