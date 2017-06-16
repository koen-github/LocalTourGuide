package com.vanderkruk.localtourguide.userinterface;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.vanderkruk.localtourguide.R;
import com.vanderkruk.localtourguide.database.TourDatabaseHelper;
import com.vanderkruk.localtourguide.database.WayPointDatabaseHelper;
import com.vanderkruk.localtourguide.datamodel.Tour;
import com.vanderkruk.localtourguide.datamodel.WayPoint;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MapTourScreen extends AppCompatActivity implements OnMapReadyCallback {

    private MarkerActions markerActions;
    private Tour currentTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_tour_screen);
        markerActions = new MarkerActions();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        currentTour = (Tour)getIntent().getSerializableExtra("currentTour");

    }


    @Override
    public void onMapReady(GoogleMap map) {
        PolylineOptions polyoptions = new PolylineOptions().geodesic(true);

        for(WayPoint wap : currentTour.getAllWaypoints()){
            LatLng currentlatlng = new LatLng(wap.getLat(), wap.getLng());
            map.addMarker(new MarkerOptions().position(currentlatlng).title(Integer.toString(wap.getWaypointOrder())));
            polyoptions.add(currentlatlng);
        }
        map.setMinZoomPreference(11);
        WayPoint zoomWaypoint = currentTour.getAllWaypoints().get(0);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(zoomWaypoint.getLat(), zoomWaypoint.getLng()), 10));
        map.setOnMarkerClickListener(markerActions);
        Polyline polygon = map.addPolyline(polyoptions);


    }
}