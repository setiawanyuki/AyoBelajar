package com.kukuh.ayobelajar.Model;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class Lagu {

    private String judulLagu;
    private int laguPutar;

    public Lagu(String judulLagu, int laguPutar) {
        this.judulLagu = judulLagu;
        this.laguPutar = laguPutar;
    }

    public String getJudulLagu() {
        return judulLagu;
    }

    public int getLaguPutar() {
        return laguPutar;
    }
}
