package com.example.comics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.comics.R;
import com.example.comics.model.Chap;

import java.util.ArrayList;
import java.util.List;

public class ChapAdapter extends ArrayAdapter<Chap> {
private Context context;
private ArrayList<Chap> arrayListChap ;

    public ChapAdapter(@NonNull Context context, int resource, @NonNull List<Chap> objects) { // contructor
        super(context, resource, objects);
        this.context = context;
        this.arrayListChap = new ArrayList<>(objects);
    }
    private static class ViewHolder{
        TextView name;
        TextView date;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       Chap chap = getItem(position);
       ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_chap, parent, false);
            viewHolder.name = convertView.findViewById(R.id.tvnameChapItem);
            viewHolder.date = convertView.findViewById(R.id.tvDateChap);
            convertView.setTag(viewHolder);
        }
        else {
        viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(chap.getTenChap());
        viewHolder.date.setText(chap.getNgayDang());
      return convertView;
        }
    }

