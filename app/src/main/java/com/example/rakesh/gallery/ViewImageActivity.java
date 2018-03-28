package com.example.rakesh.gallery;

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
        String getImageSource = getIntent().getStringExtra("selectedImage");
        Log.i(TAG, "getImageSource" + getImageSource);
        Glide.with(this).load(getImageSource).into(imageView);

    }


}
