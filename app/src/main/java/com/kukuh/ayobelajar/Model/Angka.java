package com.kukuh.ayobelajar.Model;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class Angka {

    private String daftarAngka;
    private String angkaIndo, angkaEngl;
    private int laguPutar;

    public Angka(String daftarAngka, String angkaIndo, String angkaEngl, int laguPutar) {
        this.daftarAngka = daftarAngka;
        this.angkaIndo = angkaIndo;
        this.angkaEngl = angkaEngl;
        this.laguPutar = laguPutar;
    }

    public String getDaftarAngka() {
        return daftarAngka;
    }

    public String getAngkaIndo() {
        return angkaIndo;
    }

    public String getAngkaEngl() {
        return angkaEngl;
    }

    public int getLaguPutar() {
        return laguPutar;
    }
}
