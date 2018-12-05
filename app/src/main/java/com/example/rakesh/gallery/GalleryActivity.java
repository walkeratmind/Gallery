package com.example.rakesh.gallery;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.rakesh.gallery.adapter.ImageAdapter;
import com.example.rakesh.gallery.adapter.RecyclerGridViewAdapter;
import com.example.rakesh.gallery.helper.RecyclerGridView;
import com.example.rakesh.gallery.helper.Utils;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = GalleryActivity.class.getSimpleName();
    private static final String DEBUG_TAG = GalleryActivity.class.getSimpleName();

    private static final int GRID_PADDING = 2;
    private static final int NUM_OF_COLUMNS = 3;
    /** The images. */
    private ArrayList<String> images;
    private Utils utils;
    private int columnWidth;

//    private GridView recyclerGridView;
    private RecyclerView recyclerGridView;

    private android.support.v7.widget.Toolbar mToolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerGridView =  findViewById(R.id.recycler_image_view);

        showToolbar();

        utils = new Utils(this);

//        initializeGridLayout();

        fetchGallery();

        recyclerGridView.addOnItemTouchListener(
                new RecyclerGridViewAdapter.RecyclerTouchListener(getApplicationContext(), recyclerGridView,
                        new RecyclerGridViewAdapter.ClickListener(){
                            Context context = getApplicationContext();

                            @Override
                            public void onClick(View view, int position) {
                                String image = images.get(position);
                                if (null != image && !image.isEmpty()) {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "position " + position + " " + image,
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, ViewImageActivity.class);
                                    intent.putExtra("selectedImage", image);
                                    context.startActivity(intent);
                                }
                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchGallery();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        android.widget.SearchView searchView =
                (android.widget.SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }



    //    public boolean onTouch(View view, MotionEvent event) {
//        return false;
//    }

    public void getFastScrollBar() {
//        recyclerGridView.Fas
//        recyclerGridView.setFastScrollStyle(R.style.FastScrollBarStyle);
    }

    public void showToolbar(){
        mToolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle("Gallery");
        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Web View");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    public void fetchGallery() {
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL);
        recyclerGridView.setLayoutManager(staggeredGridLayoutManager);
        getFastScrollBar();
        images = utils.getAllShownImagesPath(this);
        recyclerGridView.setAdapter(new RecyclerGridViewAdapter(this, images));
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

//    public void initializeGridLayout(){
//        Resources resources = getResources();
//        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                GRID_PADDING, resources.getDisplayMetrics());
//
//        columnWidth = (int) (utils.getScreenWidth() - ((NUM_OF_COLUMNS + 1) * padding));
//
//        int padd = (int) padding;
//        recyclerGridView.setNumColumns(NUM_OF_COLUMNS);
//        recyclerGridView.setColumnWidth(280);
//        recyclerGridView.setStretchMode(GridView.NO_STRETCH);
//        recyclerGridView.setLayoutParams(new GridView.LayoutParams(240, 240));
//        recyclerGridView.setPadding(padd, padd, padd, padd);
//
//        recyclerGridView.setHorizontalSpacing(8);
//        recyclerGridView.setVerticalSpacing(8);
//
//    }
}
