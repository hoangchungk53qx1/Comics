package com.example.comics.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.comics.R;
import com.example.comics.model.Commic;

import java.util.ArrayList;
import java.util.List;

public class TruyenTranhAdapter extends ArrayAdapter<Commic> {
    private Context context;
    private ArrayList<Commic> arrayList;
    public TruyenTranhAdapter(@NonNull Context context, int resource, @NonNull List<Commic> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = new ArrayList<>(objects);
    }
    public void Sort(String s){
        s= s.toLowerCase();
        int khoitao = 0;
        for(int i = 0;i<arrayList.size();i++){
            Commic truyentranh = arrayList.get(i);
            String ten = truyentranh.getTenTruyen().toLowerCase();
            if(ten.indexOf(s) >=0){ // nếu chứa chữ
                arrayList.set(i,arrayList.get(khoitao)); // đổi vị trí thứ i lên vị trí đầu tiên
                arrayList.set(khoitao,truyentranh); //đổi vị trí tiep theo , chứ ko thể thứ 0 nữa
                khoitao++;
            }
        }
        notifyDataSetChanged(); // update
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       if(convertView ==null){
         LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView =inflater.inflate(R.layout.item_truyen, parent, false);
       }
       if(arrayList.size() >0){
           Commic commic = this.arrayList.get(position);
           TextView tenTruyen = convertView.findViewById(R.id.tenTruyen);
           TextView tenChap = convertView.findViewById(R.id.txvChapter);
           ImageView imgAnh = convertView.findViewById(R.id.imageTruyen);

           tenTruyen.setText(commic.getTenTruyen());
           tenChap.setText("Số Chap:"+commic.getTenChap());
           Glide.with(context).load(commic.getLinkAnh()).into(imgAnh);

       }
       return convertView;
    }
}
