package com.example.rakesh.gallery;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rakesh.gallery.adapter.ImageViewPagerAdapter;
import com.example.rakesh.gallery.helper.Utils;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class ViewImageActivity extends AppCompatActivity {

    private static final String TAG = ViewImageActivity.class.getSimpleName();

    ViewPager imagePager;
    ImageViewPagerAdapter imageViewPagerAdapter;
    Utils utils;
    private ArrayList<String> imagePaths;
    private int selectedPosition = 0;

    private String[] setViewStyle = {"simpleView", "horizontalViewPager", "verticalViewPager"};

    private HorizontalInfiniteCycleViewPager infiniteCycleViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_pager);

        utils = new Utils(this);
        imagePaths = new ArrayList<>();

        imagePaths = utils.getAllShownImagesPath(this);
        Log.i(TAG, "imagelist: " + imagePaths + " : " + imagePaths.size());

        String getImageSource = getIntent().getStringExtra("selectedImage");

        imageViewPagerAdapter = new ImageViewPagerAdapter(this, imagePaths);

        selectedPosition = imagePaths.indexOf(getImageSource);
        Log.i(TAG, "selected position: " + selectedPosition);

        setSimpleViewPager(selectedPosition);

        //putting image in a file
//        ImageView imageView = findViewById(R.id.full_image);
//        Log.i(TAG, "getImageSource" + getImageSource);
//        Glide.with(this).load(getImageSource).into(imageView);

    }

    private void setSimpleViewPager(int selectedPosition) {
        imagePager = findViewById(R.id.pager_image);
        imagePager.setAdapter(imageViewPagerAdapter);
        imagePager.setCurrentItem(selectedPosition, false);

    }

    public void setInfiniteViewPager(){
        infiniteCycleViewPager = findViewById(R.id.infinte_cycle_pager);
        infiniteCycleViewPager.setAdapter(imageViewPagerAdapter);
        infiniteCycleViewPager.setScrollDuration(500);
        infiniteCycleViewPager.startAutoScroll(true);
        infiniteCycleViewPager.notifyDataSetChanged();

    }



}
