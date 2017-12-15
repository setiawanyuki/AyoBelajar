package com.kukuh.ayobelajar;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LocationActivity extends AppCompatActivity {

    public static final String RECEIVE_JSON = "com.kukuh.ayobelajar.RECEIVE_JSON";
    Button btnLocationSharing;
    TextView txtCoordinates, txtAddress;
    Double Latitude, Longitude;
    String Provider;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        initReference();
        initListener();

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(RECEIVE_JSON)) {
                    Provider = intent.getStringExtra("Provider");
                    Latitude = (Double) intent.getExtras().get("Latitude");
                    Longitude = (Double) intent.getExtras().get("Longitude");
                    txtAddress.setText("Provider : " + Provider);
                    txtCoordinates.setText("Lat:" + Latitude + " ,Long:" + Longitude);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("location").child("coordinates");
                    myRef.child("0").setValue(Latitude);
                    myRef.child("1").setValue(Longitude);
                }
            }
        };

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RECEIVE_JSON);
        bManager.registerReceiver(receiver, intentFilter);

    }

    private void initReference() {
        btnLocationSharing = (Button) findViewById(R.id.btnLocationSharing);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtCoordinates = (TextView) findViewById(R.id.txtCoordinates);
    }

    private void initListener() {
        btnLocationSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnLocationSharing.getText().toString().equalsIgnoreCase("Mulai")) {
                    ActivityCompat.requestPermissions(LocationActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    Intent i = new Intent(LocationActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    btnLocationSharing.setText("Mulai");
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        });
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtCoordinates = (TextView) findViewById(R.id.txtCoordinates);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(LocationActivity.this, LocationService.class);
                    startService(intent);
                    btnLocationSharing.setText("Keluar");

                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}