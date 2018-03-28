package com.example.rakesh.gallery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.rakesh.gallery.adapter.ImageAdapter;
import com.example.rakesh.gallery.helper.Utils;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    /** The images. */
    private ArrayList<String> images;
    private Utils utils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        utils = new Utils(this);

        GridView gallery = (GridView) findViewById(R.id.galleryGridView);

        images = utils.getAllShownImagesPath(this);

        gallery.setAdapter(new ImageAdapter(this, images));

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                if (null != images && !images.isEmpty()) {
                    Toast.makeText(
                            getApplicationContext(),
                            "position " + position + " " + images.get(position),
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ViewImageActivity.class);
                    intent.putExtra("selectedImage", images.get(position));
                    startActivity(intent);

                }

            }
        });
    }
}
