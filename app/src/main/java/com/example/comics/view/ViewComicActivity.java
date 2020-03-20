package com.example.comics.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.comics.R;
import com.example.comics.api.ApiGetImage;
import com.example.comics.callback.GetAnh;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewComicActivity extends AppCompatActivity implements GetAnh {
    private ImageView viewPager;
    private TextView numberPage;
    private ArrayList<String> arrUrl = new ArrayList<>();
    int sotrang, sotrangdangdoc;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comic);
       // createDemo();
        initView();
        getData();
        setup();
        new ApiGetImage(this,id);
//        arrUrl.add("https://1.bp.blogspot.com/-SDwhRZreGIQ/XmXElAYOOZI/AAAAAAADRWE/aYM3dzf1GQkrBRD2heenQXjqTQwu-yFlgCLcBGAsYHQ/s1600/1.jpg?imgmax=0");
////
//new ImageViewer.Builder<String>(this,)
//    }
    }

    private void getData() {

        id = getIntent().getStringExtra("idChap");
    }

    private void setup() {
     //   ReadflowPage(0);
    }

    private void initView() {
        viewPager = findViewById(R.id.imgView);
        numberPage = findViewById(R.id.numberPage);
    }

    private void createDemo() {

        try {
            arrUrl.add("https://1.bp.blogspot.com/-SDwhRZreGIQ/XmXElAYOOZI/AAAAAAADRWE/aYM3dzf1GQkrBRD2heenQXjqTQwu-yFlgCLcBGAsYHQ/s1600/1.jpg?imgmax=0");
            arrUrl.add("https://1.bp.blogspot.com/-reG7N9Z59-g/XmXEl8Yqi8I/AAAAAAADRWM/ZkbfNw7k6HIA8h60ksShaQShJs5z1Ha0wCLcBGAsYHQ/s1600/2.jpg?imgmax=0");
            arrUrl.add("https://1.bp.blogspot.com/-xIvy11igLzI/XmXEmRPzcwI/AAAAAAADRWQ/nJRyIO2t3l4Uk78Xlx2V6cdhrWXflHZBACLcBGAsYHQ/s1600/3.jpg?imgmax=0");
            //       loadImages(arrUrl);
            sotrangdangdoc = 1;
            sotrang = arrUrl.size();
        } catch (Exception x){
            x.printStackTrace();
        }
//

        Log.d("TAG111", "createDemo: " + viewPager);
    }

    public void loadImages(ArrayList<String> list) {

        for (int i = 0; i < list.size(); i++) {
            Glide
                    .with(getApplicationContext())
                    .load(list.get(i))
                    .into(viewPager);
        }

    }

    public void backHome(View view) {
        ReadflowPage(-1);
    }

    public void nextHomee(View view) {
        ReadflowPage(1);

    }
    @SuppressLint("SetTextI18n")
    private void ReadflowPage(int i){
        sotrangdangdoc = sotrangdangdoc + i;
        if(sotrangdangdoc == 0){
            sotrangdangdoc =1;
        }
        if(sotrangdangdoc > sotrang)
        {
            sotrangdangdoc = sotrang;
        }

     //   loadImages(arrUrl);
        numberPage.setText(sotrangdangdoc+"/"+sotrang);
        Glide.with(getApplicationContext()).load(arrUrl.get(sotrangdangdoc-1)).into(viewPager);
    }


    @Override
    public void StartLoadingImage() {
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void CompleteLoadingImage(String link) {
        try {
            arrUrl.clear();
            JSONArray jsonArray = new JSONArray(link);
            for(int i =0 ;i <jsonArray.length();i++){
                String js = jsonArray.getString(i);
              arrUrl.add(js);
            }
            sotrangdangdoc=1;
            sotrang = arrUrl.size();
            ReadflowPage(0);

        }
        catch (JSONException e){
            e.printStackTrace();

        }

    }

    @Override
    public void Error() {

    }
}