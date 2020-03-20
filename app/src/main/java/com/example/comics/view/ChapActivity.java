package com.example.comics.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.comics.R;
import com.example.comics.adapter.ChapAdapter;
import com.example.comics.api.ApiGetChap;
import com.example.comics.callback.GetChap;
import com.example.comics.model.Chap;
import com.example.comics.model.Commic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity implements GetChap {
private ListView lvChap;
private TextView tvnameTruyen;
private ImageView imgAnhChap;
private Commic truyentranh;
private ArrayList<Chap> chapArrayList = new ArrayList<>();
private ChapAdapter chapAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        initView();
        getDataSeriable();
        setUpData();
      //  createDemo();
        setupAdapter();
        new ApiGetChap(this,truyentranh.getId()); // chọc cái id vào . mà id bên kia.
     //   sendData();
        setClick();
    }



    private void setClick() {
        lvChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChapActivity.this,ViewComicActivity.class);
                intent.putExtra("idChap",chapArrayList.get(position).getId());// lấy id tại ví
                startActivity(intent);
            }
        });
    }

    private void createDemo() {
        Chap chap = new Chap();
        chap.setTenChap("võ công cái thế");
        chap.setNgayDang("19-03-2020");

        Chap chap1 = new Chap();
        chap1.setTenChap("đéo biết viết gì");
        chap1.setNgayDang("19-03-2020");

        Chap chap2 = new Chap();
        chap2.setTenChap("dẽ vl");
        chap2.setNgayDang("19-03-2020");

        for(int i =0 ;i < 25 ;i++){
            chapArrayList.add(new Chap("chapter"+i,"19-03-2020"));
        }

        chapArrayList.add(chap);
        chapArrayList.add(chap1);
        chapArrayList.add(chap2);

    }

    private void setupAdapter() {
        chapAdapter = new ChapAdapter(this,0,chapArrayList);
        lvChap.setAdapter(chapAdapter);
    }

    private void setUpData() {
        tvnameTruyen.setText(truyentranh.getTenTruyen());
        Glide.with(this).load(truyentranh.getLinkAnh()).fitCenter().into(imgAnhChap);

    }

    private void getDataSeriable() {
        truyentranh = (Commic) getIntent().getSerializableExtra("truyentranh");
    }

    private void initView() {
        lvChap = findViewById(R.id.lvChap);
        tvnameTruyen = findViewById(R.id.tvnameTruyen);
        imgAnhChap = findViewById(R.id.imgAnhChap);
    }

    @Override
    public void startChap() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void completeChap(String data) {
        chapArrayList.clear();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0 ;i < jsonArray.length() ;i++){
           JSONObject jsonObject = jsonArray.getJSONObject(i);
                chapArrayList.add(new Chap(jsonObject));
                Log.d("ssss", "completeChap: "+jsonObject);
            }
            chapAdapter = new ChapAdapter(this,0,chapArrayList);
            lvChap.setAdapter(chapAdapter);

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void ErrorChap() {
        Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();

    }
}
