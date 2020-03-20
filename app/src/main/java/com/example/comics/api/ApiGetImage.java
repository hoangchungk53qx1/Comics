package com.example.comics.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.comics.callback.GetAnh;

public class ApiGetImage {
    GetAnh getAnh;
    String idChap;

    public ApiGetImage(GetAnh getAnh,String idChap) {
        this.getAnh = getAnh;
        this.idChap = idChap;
        init();
    }

    private void init() {

        RequestQueue requestQueue = Volley.newRequestQueue((Context) getAnh);
        String url = "https://medicalvn.000webhostapp.com/LayAnh.php?idChap="+idChap;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getAnh.CompleteLoadingImage(response);
                        Log.d("TAG111221", "onResponse: "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getAnh.Error();
            }
        });

        requestQueue.add(stringRequest);
    }

}

