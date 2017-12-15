package com.kukuh.ayobelajar.Model;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class Buah {
    private String buahIndonesia;
    private String buahEnglish;
    private String daftarBuah;
    private int laguPutar;

    public Buah(String daftarBuah, String buahIndonesia, String buahEnglish, int laguPutar) {
        this.buahIndonesia = buahIndonesia;
        this.buahEnglish = buahEnglish;
        this.daftarBuah = daftarBuah;
        this.laguPutar = laguPutar;
    }

    public String getBuahIndonesia() {
        return buahIndonesia;
    }

    public String getBuahEnglish() {
        return buahEnglish;
    }

    public String getDaftarBuah() {
        return daftarBuah;
    }

    public int getLaguPutar() {
        return laguPutar;
    }
}
