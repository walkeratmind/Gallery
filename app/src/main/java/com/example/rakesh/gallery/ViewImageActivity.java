package com.example.rakesh.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ViewImageActivity extends AppCompatActivity {

    private static final String TAG = "ViewImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        //putting image in a file
        ImageView imageView = findViewById(R.id.full_image);
        String getImageString = getIntent().getStringExtra("selectedImage");
        Log.i(TAG, "getImageString" + getImageString);
        Glide.with(this).load(getImageString).into(imageView);

        //setting zooming for image


    }


}