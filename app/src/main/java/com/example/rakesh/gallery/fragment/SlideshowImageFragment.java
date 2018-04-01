package com.example.rakesh.gallery.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakesh.gallery.ViewImageActivity;
import com.example.rakesh.gallery.adapter.ImageViewPagerAdapter;
import com.example.rakesh.gallery.helper.Utils;

import java.util.ArrayList;

/**
 * Created by Rakesh on 3/29/2018.
 */

public class SlideshowImageFragment extends DialogFragment {
    private static final String TAG = ViewImageActivity.class.getSimpleName();

    private ViewPager imagePager;
    private ImageViewPagerAdapter imageViewPagerAdapter;
    private Utils utils;
    private ArrayList<String> imagePaths;
    private int selectedPosition = 0;

    static SlideshowImageFragment newInstance() {
        SlideshowImageFragment f = new SlideshowImageFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
