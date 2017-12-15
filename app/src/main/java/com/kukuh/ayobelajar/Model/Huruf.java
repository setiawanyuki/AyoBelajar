package com.kukuh.ayobelajar.Model;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class Huruf {

    private String daftarHuruf;
    private int laguPutar;

    public Huruf(String daftarHuruf, int laguPutar) {
        this.daftarHuruf = daftarHuruf;
        this.laguPutar = laguPutar;
    }

    public String getDaftarHuruf() {
        return daftarHuruf;
    }

    public int getLaguPutar() {
        return laguPutar;
    }
}
