package com.example.rakesh.gallery;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rakesh.gallery.adapter.ImageViewPagerAdapter;
import com.example.rakesh.gallery.helper.Utils;
import com.example.rakesh.gallery.pageTransformer.DepthPageTransformer;
import com.example.rakesh.gallery.pageTransformer.ZoomOutPageTransformer;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class ViewImageActivity extends Activity {

    private static final String TAG = ViewImageActivity.class.getSimpleName();

    private ViewPager imagePager;
    private ImageViewPagerAdapter imageViewPagerAdapter;
    private Utils utils;
    private ArrayList<String> imagePaths;
    private int selectedPosition = 0;

    private String[] setViewStyle = {"simpleView", "horizontalViewPager", "verticalViewPager"};

    private HorizontalInfiniteCycleViewPager infiniteCycleViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_pager);

//        hideActionBar();

        utils = new Utils(this);
        imagePaths = new ArrayList<>();

        imagePaths = utils.getAllShownImagesPath(this);
        Log.i(TAG, "imagelist: " + imagePaths + " : " + imagePaths.size());

        String getImageSource = getIntent().getStringExtra("selectedImage");

        imageViewPagerAdapter = new ImageViewPagerAdapter(this, imagePaths);

        selectedPosition = imagePaths.indexOf(getImageSource);
        Log.i(TAG, "selected position: " + selectedPosition);

        setSimpleViewPager(selectedPosition);
//        setInfiniteViewPager(selectedPosition);

        //putting image in a file
//        ImageView imageView = findViewById(R.id.full_image);
//        Log.i(TAG, "getImageSource" + getImageSource);
//        Glide.with(this).load(getImageSource).into(imageView);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        hideActionBar();
    }

    private void setSimpleViewPager(int selectedPosition) {
        imagePager = findViewById(R.id.pager_image);
        imagePager.setPageTransformer(true, new DepthPageTransformer());
        imagePager.setAdapter(imageViewPagerAdapter);
        imagePager.setCurrentItem(selectedPosition, false);
        imagePager.setOffscreenPageLimit(5);
    }

    public void setInfiniteViewPager(int selectedPosition) {
        infiniteCycleViewPager = findViewById(R.id.infinte_cycle_pager);
        infiniteCycleViewPager.setCurrentItem(selectedPosition, true);
        infiniteCycleViewPager.setAdapter(imageViewPagerAdapter);
        infiniteCycleViewPager.setScrollDuration(5000);
        infiniteCycleViewPager.setPageTransformer(true, new DepthPageTransformer());
        infiniteCycleViewPager.notifyDataSetChanged();
    }

    public void hideActionBar() {
        View decorView = getWindow().getDecorView();
        //hide status bar
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //never show action bar if status bar is hidden
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
    }

}
