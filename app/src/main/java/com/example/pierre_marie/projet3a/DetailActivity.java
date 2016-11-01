package com.example.pierre_marie.projet3a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(myToolbar);
        TextView name = (TextView) findViewById(R.id.Name);
        TextView latitude = (TextView) findViewById(R.id.Lat);
        TextView longitude = (TextView) findViewById(R.id.Long);
        String dataFromActivity = getIntent().getStringExtra("Monument_lat");
        String dataFromActivity2 = getIntent().getStringExtra("Monument_long");
        String dataFromActivity3 = getIntent().getStringExtra("Monument_name");

        latitude.setText("Latitude : " + dataFromActivity);
        longitude.setText("Longitude : " + dataFromActivity2);
        name.setText("Nom du Monument : " + dataFromActivity3);
    }
}
