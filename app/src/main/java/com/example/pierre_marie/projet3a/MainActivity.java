package com.example.pierre_marie.projet3a;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private static String URL_API = "https://www.data.gouv.fr/s/resources/monuments-historiques-francais/20150408-163911/monuments.json";

    private ArrayList<ListSample> list;
    private ListView listView;
    private List<Monument> monumentInfos;

    private RequestQueue mRequestQueue;
    private HttpRequestJson mHttpRequestJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        ListFragment fragment = new ListFragment();
//        fragmentTransaction.add(R.id.fragmentContainer, fragment);
//        fragmentTransaction.commit();

        listView = (ListView) findViewById(R.id.listView);//à mettre dans le fragment
        mRequestQueue = VolleyQueue.getInstance(MainActivity.this);
        mHttpRequestJson = new HttpRequestJson();

        getHttpRequestJson().LaunchHttpRequestJson(mRequestQueue, MainActivity.this, URL_API);
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
                ListSampleAdapter adapter = new ListSampleAdapter(MainActivity.this, list);
                listView.setAdapter(adapter);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
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
