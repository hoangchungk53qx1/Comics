package com.example.comics.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.comics.callback.GetChap;
import com.example.comics.callback.GetTruyen;

public class ApiGetChap {
    private GetChap getChap;
    private String idTruyen;
    public ApiGetChap(GetChap getChap,String idTruyen){
        this.getChap = getChap;
        this.idTruyen = idTruyen;
        this.getChap.startChap();
        init();
    }

    private void init() {
        RequestQueue requestQueue = Volley.newRequestQueue((Context) getChap);
        String url = "https://medicalvn.000webhostapp.com/LayChap.php?id="+idTruyen;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getChap.completeChap(response);
                        Log.d("TAG1111", "onResponse: "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getChap.ErrorChap();
            }
        });

        requestQueue.add(stringRequest);
    }


}
