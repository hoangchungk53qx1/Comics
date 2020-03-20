package com.example.comics.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.comics.callback.GetTruyen;
import com.example.comics.view.HouseActivity;

public class ApiGetTruyen  {
    private GetTruyen getTruyen;

    public ApiGetTruyen(GetTruyen getTruyen) {
        this.getTruyen = getTruyen;
        init();
    }
    private void init() {
        RequestQueue queue = Volley.newRequestQueue((Context) getTruyen);
        String url ="https://medicalvn.000webhostapp.com/LayTruyen.php";

// Request a string response from the provided URL.
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    getTruyen.Complemet(response);
                        Log.d("TAG", "onResponse: "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getTruyen.Error();
            }
        });

        queue.add(stringRequest);

}

}
