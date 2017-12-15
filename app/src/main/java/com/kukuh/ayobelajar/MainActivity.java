package com.kukuh.ayobelajar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout pindahLagu = findViewById(R.id.lagu);
        pindahLagu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LaguActivity.class);
                startActivity(i);
            }
        });
        LinearLayout pindahHuruf = findViewById(R.id.huruf);
        pindahHuruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, HurufActivity.class);
                startActivity(i);
            }
        });
        LinearLayout pindahBinatang = findViewById(R.id.binatang);
        pindahBinatang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BinatangActivity.class);
                startActivity(i);
            }
        });
        LinearLayout pindahAngka = findViewById(R.id.angka);
        pindahAngka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AngkaActivity.class);
                startActivity(i);
            }
        });
        LinearLayout pindahBuah = findViewById(R.id.buah);
        pindahBuah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BuahActivity.class);
                startActivity(i);
            }
        });
    }
}