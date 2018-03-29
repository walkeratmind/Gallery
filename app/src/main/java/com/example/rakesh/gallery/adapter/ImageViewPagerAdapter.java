package com.example.rakesh.gallery.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rakesh.gallery.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

/**
 * Created by Rakesh on 3/28/2018.
 */

public class ImageViewPagerAdapter extends PagerAdapter {

    private Activity activity;
    private ArrayList<String> imagePaths;
    private LayoutInflater inflater;

    public ImageViewPagerAdapter(Activity activity, ArrayList<String> imagePaths) {
        this.activity = activity;
        this.imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //method for instantiating item
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) activity.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_view_image,
                container, false);
        PhotoView imageView = itemView.findViewById(R.id.full_image);

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        Bitmap bitmap = BitmapFactory.decodeFile(imagePaths.get(position), options);
//        imageView.setImageBitmap(bitmap);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;
//        imageView.setMinimumHeight(height);
//        imageView.setMinimumWidth(width);

        Log.i("Tag", "paths: " + imagePaths.size() + " : " + imagePaths);

        String image = imagePaths.get(position);

        Glide.with(activity).load(imagePaths.get(position)).into(imageView);
//        imageView.setImageResource(mThumbId[position]);

        container.addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    //    private Integer[] mThumbId = {
//            R.mipmap.sample_0, R.mipmap.sample_1,
//            R.mipmap.sample_2, R.mipmap.sample_3,
//            R.mipmap.sample_4, R.mipmap.sample_5,
//            R.mipmap.sample_6, R.mipmap.sample_7
//    };
}
