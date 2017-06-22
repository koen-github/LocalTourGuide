package com.vanderkruk.localtourguide.userinterface;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
import com.vanderkruk.localtourguide.datamodel.media.Text;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTourScreen extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private MarkerActions markerActions;
    private Tour currentTour;
    private HashMap<Marker, WayPoint> wapointMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_tour_screen);
        markerActions = new MarkerActions();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        currentTour = (Tour)getIntent().getSerializableExtra("currentTour");
        wapointMarker = new HashMap<Marker, WayPoint>();
    }


    @Override
    public void onMapReady(GoogleMap map) {
        PolylineOptions polyoptions = new PolylineOptions().geodesic(true);

        for(WayPoint wap : currentTour.getAllWaypoints()){
            LatLng currentlatlng = new LatLng(wap.getLat(), wap.getLng());
            MarkerOptions markerPosition = new MarkerOptions().position(currentlatlng).title(Integer.toString(wap.getWaypointOrder()));
            Marker currentMarker = map.addMarker(markerPosition);
            if(wap.getWaypointOrder() == 1){
                currentMarker.showInfoWindow();
            }
            wapointMarker.put(currentMarker, wap);
            polyoptions.add(currentlatlng);
        }

        map.setMinZoomPreference(11);

        WayPoint zoomWaypoint = currentTour.getAllWaypoints().get(0);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(zoomWaypoint.getLat(), zoomWaypoint.getLng()), 10));
        map.setOnMarkerClickListener(this);
        Polyline polygon = map.addPolyline(polyoptions);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        for (Map.Entry<Marker, WayPoint> entry : wapointMarker.entrySet()) {
            if(entry.getKey().getId().equals(marker.getId())){
                WayPoint cu = entry.getValue();
                Text ct =  (Text)cu.getAllMediaComponents().get(0) ;

                Intent intent = new Intent(MapTourScreen.this, ShowMediaItem.class);
                intent.putExtra("currentText",ct);

                startActivity(intent);
                Log.d("WAYPRESEED: ",ct.getText());

            }

        }
        return false;
    }
}