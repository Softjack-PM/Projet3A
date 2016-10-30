package com.example.pierre_marie.projet3a;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.pierre_marie.projet3a.R.id.btn_liste;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_list = (Button) findViewById(btn_liste);
        Button btn_mp = (Button) findViewById(R.id.btn_map);
        Button btn_au = (Button) findViewById(R.id.btn_autre);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
               .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                com.example.pierre_marie.projet3a.ItemFragment fragment2 = new com.example.pierre_marie.projet3a.ItemFragment();
                fragmentTransaction.add(R.id.container, fragment2);
                fragmentTransaction.commit();
            }


        });

        btn_mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                com.example.pierre_marie.projet3a.MapFragment fragment = new com.example.pierre_marie.projet3a.MapFragment();
                fragmentTransaction.add(R.id.container, fragment);
                fragmentTransaction.commit();
            }


        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onMapReady(GoogleMap GoogleMap) {
        mMap = GoogleMap;
        LatLng gardanne = new LatLng(43.45, 5.46);
        mMap.addMarker(new MarkerOptions().position(gardanne).title("Mines St Etienne"));
        //mMap.getUiSettings().setZoomGesturesEnabled(true);
        //moovecamera
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
// User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_load:
// User chose the refresh action, refresh the page
                return true;
            default: // If we got here, the user's action was not recognized.
// Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }




}
