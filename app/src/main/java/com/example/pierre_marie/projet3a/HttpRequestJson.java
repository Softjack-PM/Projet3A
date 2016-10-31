package com.example.pierre_marie.projet3a;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre-Marie on 30/10/2016.
 */

public class HttpRequestJson {

    private List<Monument> monumentInfo;

    public HttpRequestJson() {
        this.monumentInfo = new ArrayList<>();
    }

    public void LaunchHttpRequestJson (final RequestQueue requestQueue, final MainActivity CurrentActivity, String URL_API) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_API, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {
                    Gson gson = new GsonBuilder().create();


                    for (int i = 0; i < response.length(); i++) {
                        JSONObject informations = response.getJSONObject(i);
//                        String proprietes = informations.getString("");
                        String nom = informations.getString("name");
                        String latitude = informations.getString("latitude");
                        String longitude = informations.getString("longitude");

                        Monument monumentInfo = new Monument();
                        monumentInfo.setName(nom);
                        monumentInfo.setLatitude(Double.parseDouble(latitude));
                        monumentInfo.setLongitude(Double.parseDouble(longitude));
//                        Monument monumentInfo = gson.fromJson(proprietes,Monument.class);

                        getMonumentList().add(monumentInfo);
                    }
                    Log.d("Monument : ", monumentInfo.toString());

                    Toast.makeText(CurrentActivity, "Données recupérées", Toast.LENGTH_SHORT).show();
                    CurrentActivity.httpRequestReceived(true);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() { // Erreur dans la requete

            @Override
            public void onErrorResponse(VolleyError error) {
                CurrentActivity.httpRequestReceived(false);
                Toast.makeText(CurrentActivity, "Impossible de récupérer les données", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    public List<Monument> getMonumentList() {
        return monumentInfo;
    }


}