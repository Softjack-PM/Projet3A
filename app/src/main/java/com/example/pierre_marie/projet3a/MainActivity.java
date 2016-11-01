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

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Interf   {


    private static String URL_API = "https://www.data.gouv.fr/s/resources/monuments-historiques-francais/20150408-163911/monuments.json";

    private ArrayList<ListSample> list;
    private List<Monument> monumentInfos;

    private RequestQueue mRequestQueue;
    private HttpRequestJson mHttpRequestJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_list = (Button) findViewById(R.id.btn_liste);
        Button btn_mp = (Button) findViewById(R.id.btn_map);
        Button btn_au = (Button) findViewById(R.id.btn_autre);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        monumentInfos=new ArrayList<>();
        mRequestQueue = VolleyQueue.getInstance(MainActivity.this);
        mHttpRequestJson = new HttpRequestJson();
        mHttpRequestJson.LaunchHttpRequestJson(mRequestQueue, MainActivity.this, URL_API);
        monumentInfos = mHttpRequestJson.getMonumentList();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        com.example.pierre_marie.projet3a.InfoFragment fragment3 = new com.example.pierre_marie.projet3a.InfoFragment();
        fragmentTransaction.add(R.id.container, fragment3);
        fragmentTransaction.commit(); //force le démarage de l'application sur le fragment info

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

        btn_au.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                com.example.pierre_marie.projet3a.InfoFragment fragment3 = new com.example.pierre_marie.projet3a.InfoFragment();
                fragmentTransaction.add(R.id.container, fragment3);
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

    public HttpRequestJson getHttpRequestJson() {
        return mHttpRequestJson;
    }

    public void httpRequestReceived(boolean requestReceived) {

        if (requestReceived) {
            monumentInfos = mHttpRequestJson.getMonumentList();
            if (!monumentInfos.isEmpty()) {
                list = new ArrayList<>();

                for(Monument monument : monumentInfos) {
                    ListSample item = new ListSample(monument.getName(), monument.getLatitude(), monument.getLongitude());
                    list.add(item);
                }
            }
        }
    }


    public void sendHttpRequestFromFragment() {
        final MainActivity activity = this;
        getHttpRequestJson().LaunchHttpRequestJson(mRequestQueue, activity, URL_API);
    }


    public List<Monument> getMonumentList() {
        return monumentInfos;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}