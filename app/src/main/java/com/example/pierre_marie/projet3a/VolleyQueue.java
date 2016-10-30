package com.example.pierre_marie.projet3a;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Pierre-Marie on 30/10/2016.
 */

public class VolleyQueue {
    private static RequestQueue ourInstance;

    public static RequestQueue getInstance (Context context) {
        if(ourInstance == null) {
            ourInstance = Volley.newRequestQueue(context);
        }
        return ourInstance;
    }

    private VolleyQueue(){
    }
}
