package com.example.comics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPageAdapter extends PagerAdapter {
    Context context;
    List<String> imgLinks;
    LayoutInflater layoutInflater;

    public ViewPageAdapter(Context context, List<String> imgLinks) {
        this.context = context;
        this.imgLinks = imgLinks;
       layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imgLinks.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
