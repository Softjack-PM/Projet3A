package com.example.pierre_marie.projet3a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView latitude = (TextView) findViewById(R.id.textView2);

        String dataFromActivity = getIntent().getStringExtra("Monument");
        latitude.setText(dataFromActivity);
    }
}
