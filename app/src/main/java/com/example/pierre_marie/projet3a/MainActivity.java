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
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Interf   {

    //Adresse le d'API récupérée
    private static String URL_API = "https://www.data.gouv.fr/s/resources/monuments-historiques-francais/20150408-163911/monuments.json";


    private ArrayList<ListSample> list;
    private List<Monument> monumentInfos;

    private RequestQueue mRequestQueue; //Queue de requête
    private HttpRequestJson mHttpRequestJson; //Requète Json

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Boutons situés en dessous de la toolbar
        Button btn_list = (Button) findViewById(R.id.btn_liste);
        Button btn_mp = (Button) findViewById(R.id.btn_map);
        Button btn_au = (Button) findViewById(R.id.btn_autre);
        //Toolbar contenant le titre de l'application ainsi qu'un bouton refresh qui redirrige vers la liste
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Recupération des données depuis le fichier JSON
        monumentInfos=new ArrayList<>();
        mRequestQueue = VolleyQueue.getInstance(MainActivity.this);
        mHttpRequestJson = new HttpRequestJson();
        mHttpRequestJson.LaunchHttpRequestJson(mRequestQueue, MainActivity.this, URL_API);
        monumentInfos = mHttpRequestJson.getMonumentList();

        //On accède au fragment Info comme page d'accueil
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        com.example.pierre_marie.projet3a.InfoFragment fragment3 = new com.example.pierre_marie.projet3a.InfoFragment();
        fragmentTransaction.add(R.id.container, fragment3);
        fragmentTransaction.commit();

        //On définit la redirection des boutons vers les fragments concernés
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

    //Reception d'une requete HTTP
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
            case R.id.action_load:
                refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void refresh(){
        Toast toast = Toast.makeText(this, "Reload", Toast.LENGTH_SHORT);
        toast.show();

        mHttpRequestJson.LaunchHttpRequestJson(mRequestQueue, MainActivity.this, URL_API);
        monumentInfos = mHttpRequestJson.getMonumentList();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        com.example.pierre_marie.projet3a.ItemFragment fragment2 = new com.example.pierre_marie.projet3a.ItemFragment();
        fragmentTransaction.add(R.id.container, fragment2);
        fragmentTransaction.commit();
    }

}