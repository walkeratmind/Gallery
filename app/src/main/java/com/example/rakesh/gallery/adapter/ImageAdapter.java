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
    private Activity activity;
    private int imageWidth;

    public ImageAdapter(Activity activity, ArrayList<String> imageList, int imageWidth) {
        this.activity = activity;
        this.imageList = imageList;
        this.imageWidth = imageWidth;
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
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(activity);
//            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            imageView
//                    .setLayoutParams(new GridView.LayoutParams(imageWidth, imageWidth));
            imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(4, 4, 4,4);

        } else {
            imageView = (ImageView) convertView;
        }

        Glide.with(activity).load(imageList.get(position)).into(imageView);
        return imageView;
    }

}
