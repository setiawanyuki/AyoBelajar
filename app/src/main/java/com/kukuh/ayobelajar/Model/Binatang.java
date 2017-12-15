package com.kukuh.ayobelajar.Model;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class Binatang {
    private String binatangIndonesia;
    private String binatangEnglish;
    private String daftarBinatang;

    public Binatang(String daftarBinatang, String binatangIndonesia, String binatangEnglish) {
        this.binatangIndonesia = binatangIndonesia;
        this.binatangEnglish = binatangEnglish;
        this.daftarBinatang = daftarBinatang;
    }

    public String getBinatangIndonesia() {
        return binatangIndonesia;
    }

    public String getBinatangEnglish() {
        return binatangEnglish;
    }

    public String getDaftarBinatang() {
        return daftarBinatang;
    }
}
