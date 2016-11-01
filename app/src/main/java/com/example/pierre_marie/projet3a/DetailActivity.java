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
        TextView name = (TextView) findViewById(R.id.textViewName);
        TextView latitude = (TextView) findViewById(R.id.textViewLat);
        TextView longitude = (TextView) findViewById(R.id.textViewLong);
        TextView image = (TextView) findViewById(R.id.textViewImage);
        String dataFromActivity = getIntent().getStringExtra("Monument_latitude");
        String dataFromActivity2 = getIntent().getStringExtra("Monument_longitude");
        String dataFromActivity3 = getIntent().getStringExtra("Monument_name");

        latitude.setText("Latitude : " + dataFromActivity);
        longitude.setText("Longitude : " + dataFromActivity2);
        name.setText("Nom du Monument : " + dataFromActivity3);
        image.setText("No picture");
    }
}
