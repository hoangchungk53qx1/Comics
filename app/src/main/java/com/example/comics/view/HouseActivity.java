package com.example.comics.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.comics.R;
import com.example.comics.adapter.TruyenTranhAdapter;
import com.example.comics.api.ApiGetTruyen;
import com.example.comics.callback.GetTruyen;
import com.example.comics.model.Commic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HouseActivity extends AppCompatActivity implements GetTruyen {
private GridView GrdLvTruyen;
private TruyenTranhAdapter adapter;
private EditText sreachTruyen;
private ImageView imgRefresh;
private ArrayList<Commic> arrayList = new ArrayList<>();
private GetTruyen getTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        initView();
        //createDemo();
        setupAdapter();
        search();
        new ApiGetTruyen(this);
        setClick();

    }

    private void setClick() {
        GrdLvTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Commic truyentranh = arrayList.get(position);
                Intent intent = new Intent(getApplicationContext(),ChapActivity.class);
                intent.putExtra("truyentranh",truyentranh);
                startActivity(intent);

            }
        });
    }


    private void search() {
        sreachTruyen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = sreachTruyen.getText().toString();
                adapter.Sort(s1);
            }

            @Override
            public void afterTextChanged(Editable s) {

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupAdapter() {
        adapter = new TruyenTranhAdapter(this,0,arrayList);
        GrdLvTruyen.setAdapter(adapter);
    }

    private void createDemo() {
        arrayList.add(new Commic("KIÊU NGẠO VƯƠNG GIA CHỦNG ĐIỀN PHI","Chap 1","http://st.nettruyen.com/data/comics/144/kieu-ngao-vuong-gia-chung-dien-phi.jpg"));
        arrayList.add(new Commic("BOYS RUN THE RIOT","Chap 01","http://st.nettruyen.com/data/comics/143/boys-run-the-riot.jpg"));
        arrayList.add(new Commic("ĐM LỖI CỦA ĐỊNH MỆNH","Chap 34","http://st.nettruyen.com/data/comics/218/dm-loi-cua-dinh-menh.jpeg"));
        arrayList.add(new Commic("VẠN CỔ THIÊN ĐẾ","Chap 03","http://st.nettruyen.com/data/comics/221/van-co-thien-de.jpg"));

        arrayList.add(new Commic("KIÊU NGẠO VƯƠNG GIA CHỦNG ĐIỀN PHI","Chap 1","http://st.nettruyen.com/data/comics/144/kieu-ngao-vuong-gia-chung-dien-phi.jpg"));
        arrayList.add(new Commic("BOYS RUN THE RIOT","Chap 01","http://st.nettruyen.com/data/comics/143/boys-run-the-riot.jpg"));
        arrayList.add(new Commic("ĐM LỖI CỦA ĐỊNH MỆNH","Chap 34","http://st.nettruyen.com/data/comics/218/dm-loi-cua-dinh-menh.jpeg"));
        arrayList.add(new Commic("VẠN CỔ THIÊN ĐẾ","Chap 03","http://st.nettruyen.com/data/comics/221/van-co-thien-de.jpg"));

        arrayList.add(new Commic("KIÊU NGẠO VƯƠNG GIA CHỦNG ĐIỀN PHI","Chap 1","http://st.nettruyen.com/data/comics/144/kieu-ngao-vuong-gia-chung-dien-phi.jpg"));
        arrayList.add(new Commic("BOYS RUN THE RIOT","Chap 01","http://st.nettruyen.com/data/comics/143/boys-run-the-riot.jpg"));
        arrayList.add(new Commic("ĐM LỖI CỦA ĐỊNH MỆNH","Chap 34","http://st.nettruyen.com/data/comics/218/dm-loi-cua-dinh-menh.jpeg"));
        arrayList.add(new Commic("VẠN CỔ THIÊN ĐẾ","Chap 03","http://st.nettruyen.com/data/comics/221/van-co-thien-de.jpg"));

        adapter = new TruyenTranhAdapter(this,0,arrayList);

    }

    private void initView() {
        GrdLvTruyen = findViewById(R.id.GrdLvTruyen);
        sreachTruyen = findViewById(R.id.sreachTruyen);
    }


    @Override
    public void Start() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Complemet(String data) {

  try {
      arrayList.clear();
      JSONArray jsonArray = new JSONArray(data);
      for (int i =0 ;i < jsonArray.length() ; i++){
          JSONObject jsonObject = jsonArray.getJSONObject(i);
          arrayList.add(new Commic(jsonObject));  // contructor của json
          Log.d("TAG", "Complemet: " + arrayList.size());
      }
      adapter = new TruyenTranhAdapter(this,0,arrayList);
      GrdLvTruyen.setAdapter(adapter);
  }
  catch (JSONException e){
      e.printStackTrace();
  }
    }

    @Override
    public void Error() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clickRefresh(View view) {
        Toast.makeText(this, "Upload...", Toast.LENGTH_SHORT).show();
        new ApiGetTruyen(this);
    }
}

