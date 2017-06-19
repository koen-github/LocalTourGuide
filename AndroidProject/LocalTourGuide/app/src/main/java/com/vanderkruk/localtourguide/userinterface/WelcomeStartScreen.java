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
import com.vanderkruk.localtourguide.database.MediaDatabaseHelper;
import com.vanderkruk.localtourguide.database.TourDatabaseHelper;
import com.vanderkruk.localtourguide.database.WayPointDatabaseHelper;
import com.vanderkruk.localtourguide.datamodel.Tour;
import com.vanderkruk.localtourguide.datamodel.WayPoint;

import java.util.ArrayList;
import java.util.List;

public class WelcomeStartScreen extends AppCompatActivity {

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
        setContentView(R.layout.app_bar_welcome_start_screen);
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
        allTours = new ArrayList<Tour>();
        if(tours != null){
            WayPointDatabaseHelper wdh = new WayPointDatabaseHelper(this);
            MediaDatabaseHelper mdh = new MediaDatabaseHelper(this);

            for(Tour cu : tours){
               // for(WayPoint wappie : cu.getAllWaypoints()){
                    //todo get waypoint and add to database.
               //     WayPoint bakkie = mdh.connectWaypointsAndMedia(wappie);
                //}
                allTours.add(wdh.connectToursAndWaypoint(cu));
            }
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




}
