package com.example.rakesh.gallery;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.rakesh.gallery.adapter.ImageViewPagerAdapter;
import com.example.rakesh.gallery.helper.Utils;
import java.util.ArrayList;

public class ViewImageActivity extends AppCompatActivity {

    private static final String TAG = "ViewImageActivity";

    ViewPager imagePager;
    ImageViewPagerAdapter imageViewPagerAdapter;
    Utils utils;
    private ArrayList<String> imagePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_pager);

        utils = new Utils(this);
//        imagePaths = new ArrayList<>();

        imagePaths = utils.getAllShownImagesPath(this);
        Log.i(TAG, "imagelist: " + imagePaths + " : " + imagePaths.size());
        imagePager = findViewById(R.id.pager_image);
        imageViewPagerAdapter = new ImageViewPagerAdapter(this, imagePaths);
        imagePager.setAdapter(imageViewPagerAdapter);

        //putting image in a file
        String getImageSource = getIntent().getStringExtra("selectedImage");
        Log.i(TAG, "getImageSource" + getImageSource);

//        ImageView imageView = findViewById(R.id.full_image);
//        Glide.with(this).load(getImageSource).into(imageView);

    }


}
