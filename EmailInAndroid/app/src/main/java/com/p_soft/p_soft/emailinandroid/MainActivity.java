package com.p_soft.p_soft.emailinandroid;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnShowLocation;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnShowLocation = (Button) findViewById(R.id.button);


        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                gps = new GPSTracker(MainActivity.this);


                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();


                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                            + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{

                    gps.showSettingsAlert();
                }

            }
        });
    }
}