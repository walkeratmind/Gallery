package com.example.rakesh.gallery.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by rakesh on 3/28/18.
 */

public class ImageAdapter extends BaseAdapter {
    private ArrayList<String> imageList;
    private Activity context;

    public ImageAdapter(Activity context, ArrayList<String> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    public int getCount() {
        return imageList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        ImageView picturesView;
        if (convertView == null) {
            picturesView = new ImageView(context);
            picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            picturesView
                    .setLayoutParams(new GridView.LayoutParams(270, 270));

        } else {
            picturesView = (ImageView) convertView;
        }

        Glide.with(context).load(imageList.get(position))
                .into(picturesView);

        return picturesView;
    }
}
