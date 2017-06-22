package com.example.guest.bh2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LocationListener{
    @Bind(R.id.locate) Button locate;
    @Bind(R.id.location) TextView locationText;

    protected LocationManager locationManager;
    public final String TAG = this.getClass().getSimpleName();
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
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 100, this); //starts the listener think about moving this
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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            try{
//                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                mLongitude = location.getLongitude()+"";
//                mLatitude = location.getLatitude()+"";
////                locationText.setText("Long: " + mLongitude + "    |    " + " Lat: "+ mLatitude);
//            }catch (NullPointerException e){
//                Log.d(TAG, "onStart: " +e);
//                Toast.makeText(this, "Can't Get Location", Toast.LENGTH_SHORT).show();
//            }
//
//            Log.d(TAG, "onStart: listening");
//        }
//    }

    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude()+"";
        mLongitude = location.getLongitude()+"";
        Log.d(TAG, "onLocationChanged: " + mLatitude + "," + mLongitude);
        locationText.setText("Long: " + mLongitude + "    |    " + " Lat: "+ mLatitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}



