package com.vanderkruk.localtourguide.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vanderkruk.localtourguide.R;
import com.vanderkruk.localtourguide.database.TourDatabaseHelper;
import com.vanderkruk.localtourguide.database.WayPointDatabaseHelper;
import com.vanderkruk.localtourguide.datamodel.Tour;

import java.util.ArrayList;
import java.util.List;

public class WelcomeStartScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private List<Tour> allTours;
    private TourOverviewAdapter mAdapter;

    private TourDatabaseHelper tdh;
    private WayPointDatabaseHelper wdh;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message inputMessage) {
            int id = Integer.parseInt((String)inputMessage.obj);
            Tour curTour = getTourById(id);
            if(curTour != null){
                openTourScreen(curTour);
            }
            else{
                Log.d("TOUR", "WAS EMPTY!!!");
            }
        }


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_start_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeStartScreen.this, AddTourInformation.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        getNewToursAndWaypoints();
        mAdapter = new TourOverviewAdapter(allTours, this, mHandler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    public Tour getTourById(int id){
        for(Tour cu : allTours){
            if(cu.getId() == id){
                return cu;
            }
        }
        return null;
    }

    public void getNewToursAndWaypoints(){
        TourDatabaseHelper tdh = new TourDatabaseHelper(this);
        List<Tour> tours = tdh.getAllTours();
        WayPointDatabaseHelper wdh = new WayPointDatabaseHelper(this);

        allTours = new ArrayList<Tour>();
        for(Tour cu : tours){
            allTours.add(wdh.connectToursAndWaypoint(cu));
        }

    }


    public void openTourScreen(Tour currentTour){
        Intent intent = new Intent(WelcomeStartScreen.this, MapTourScreen.class);
        intent.putExtra("currentTour",currentTour);

        startActivityForResult(intent, 2);
    }

    @Override
    public void onResume() {

        getNewToursAndWaypoints();
        synchronized (mAdapter){
            mAdapter.updateAdapterWithNewData(allTours);
            mAdapter.notifyDataSetChanged();
            mAdapter.notify();
        }


        super.onResume();

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
        getMenuInflater().inflate(R.menu.welcome_start_screen, menu);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
          //  MapTourScreen vjp = new MapTourScreen();
            Intent intent = new Intent(WelcomeStartScreen.this, MapTourScreen.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
