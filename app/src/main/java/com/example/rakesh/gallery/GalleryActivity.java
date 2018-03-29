package com.example.rakesh.gallery;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.rakesh.gallery.adapter.ImageAdapter;
import com.example.rakesh.gallery.helper.Utils;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = GalleryActivity.class.getSimpleName();

    private static final int GRID_PADDING = 2;
    private static final int NUM_OF_COLUMNS = 3;
    /** The images. */
    private ArrayList<String> images;
    private Utils utils;
    private int columnWidth;

    private GridView gridView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = (GridView) findViewById(R.id.galleryGridView);

        utils = new Utils(this);
        getFastScrollBar();

//        initializeGridLayout();

        images = utils.getAllShownImagesPath(this);

        gridView.setAdapter(new ImageAdapter(this, images, columnWidth));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getFastScrollBar() {
        gridView.setFastScrollEnabled(true);
        gridView.setFastScrollStyle(R.style.FastScrollBarStyle);
    }

//    public void fullScreen(){
//        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
//        int newUiOptions = uiOptions;
//        // END_INCLUDE (get_current_ui_flags)
//        // BEGIN_INCLUDE (toggle_ui_flags)
//        boolean isImmersiveModeEnabled =
//                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
//        if (isImmersiveModeEnabled) {
//            Log.i(TAG, "Turning immersive mode mode off. ");
//        } else {
//            Log.i(TAG, "Turning immersive mode mode on.");
//        }
//
//        // Navigation bar hiding:  Backwards compatible to ICS.
//        if (Build.VERSION.SDK_INT >= 14) {
//            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//        }
//
//        // Status bar hiding: Backwards compatible to Jellybean
//        if (Build.VERSION.SDK_INT >= 16) {
//            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
//        }
//        if (Build.VERSION.SDK_INT >= 18) {
//            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//        }
//        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
//    }

    public void initializeGridLayout(){
        Resources resources = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                GRID_PADDING, resources.getDisplayMetrics());

        columnWidth = (int) (utils.getScreenWidth() - ((NUM_OF_COLUMNS + 1) * padding));

        int padd = (int) padding;
        gridView.setNumColumns(NUM_OF_COLUMNS);
        gridView.setColumnWidth(280);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setLayoutParams(new GridView.LayoutParams(240, 240));
        gridView.setPadding(padd, padd, padd, padd);

        gridView.setHorizontalSpacing(8);
        gridView.setVerticalSpacing(8);

    }
}
