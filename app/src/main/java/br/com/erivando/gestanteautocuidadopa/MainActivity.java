package br.com.erivando.gestanteautocuidadopa;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout linearLayout;

    private ImageButton leftBtn;
    private ImageButton rightBtn;
    private HorizontalScrollView hsv;
    int currentScrollX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Nada definido aqui ainda, ainda muda!", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        leftBtn = (ImageButton) findViewById(R.id.btn_left);
        rightBtn = (ImageButton) findViewById(R.id.btn_right);
        hsv = (HorizontalScrollView) findViewById(R.id.horizontal_scrollview);
        //linearLayout = (LinearLayout) findViewById(R.id.dynamic_generation);

        rightBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hsv.scrollTo((int) hsv.getScrollX() + 425, (int) hsv.getScrollY());
            }
        });

        leftBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hsv.scrollTo((int) hsv.getScrollX() - 425, (int) hsv.getScrollY());
            }
        });


//        rightBtn.setOnTouchListener(new View.OnTouchListener() {
//
//            private Handler mHandler;
//            private long mInitialDelay = 300;
//            private long mRepeatDelay = 100;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        if (mHandler != null)
//                            return true;
//                        mHandler = new Handler();
//                        mHandler.postDelayed(mAction, mInitialDelay);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        if (mHandler == null)
//                            return true;
//                        mHandler.removeCallbacks(mAction);
//                        mHandler = null;
//                        break;
//                }
//                return false;
//            }
//
//            Runnable mAction = new Runnable() {
//                @Override
//                public void run() {
//                    hsv.scrollTo((int) hsv.getScrollX() + 80, (int) hsv.getScrollY());
//                    //hsv.scrollTo((int) hsv.getScrollX() - 80, (int) hsv.getScrollY());
//                    mHandler.postDelayed(mAction, mRepeatDelay);
//                }
//            };
//        });
//
//        leftBtn.setOnTouchListener(new View.OnTouchListener() {
//
//            private Handler mHandler;
//            private long mInitialDelay = 300;
//            private long mRepeatDelay = 100;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        if (mHandler != null)
//                            return true;
//                        mHandler = new Handler();
//                        mHandler.postDelayed(mAction, mInitialDelay);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        if (mHandler == null)
//                            return true;
//                        mHandler.removeCallbacks(mAction);
//                        mHandler = null;
//                        break;
//                }
//                return false;
//            }
//
//            Runnable mAction = new Runnable() {
//                @Override
//                public void run() {
//                    //hsv.scrollTo((int) hsv.getScrollX() + 80, (int) hsv.getScrollY());
//                    hsv.scrollTo((int) hsv.getScrollX() - 80, (int) hsv.getScrollY());
//                    mHandler.postDelayed(mAction, mRepeatDelay);
//                }
//            };
//        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
