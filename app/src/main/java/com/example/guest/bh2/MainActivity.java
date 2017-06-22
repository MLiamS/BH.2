package com.example.guest.bh2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.locate) Button locate;
    @Bind(R.id.location) TextView locationText;

    protected LocationManager locationManager;
    private final int PERMISSION_ACCESS_COARSE_LOCATION = 1;
    private String mLatitude;
    private String mLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        locate.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_ACCESS_COARSE_LOCATION);
        }
    }

    @Override
        public void onClick(View v) {
            if (v == locate) {  // Start location search
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                                PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "I'm in", Toast.LENGTH_LONG).show();
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    mLongitude = location.getLongitude()+"";
                    mLatitude = location.getLatitude()+"";

                    locationText.setText("Long: " + mLongitude + "    |    " + " Lat: "+ mLatitude);
                    Log.d("Long, Lat", mLongitude + ", " + mLatitude);
                } else {
                    Toast.makeText(this, "nope", Toast.LENGTH_LONG).show();
                }
            }

        }
}


