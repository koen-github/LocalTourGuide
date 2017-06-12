package com.vanderkruk.localtourguide.datamodel;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by koen on 8-5-2017.
 */

public class WayPoint {
    private LatLng waypointMarker;
    private List<Media> mediaComponents;
    private String name;
    private int Tour_ID;
    private int waypointOrder;

    public WayPoint(LatLng wp, String nam, int tour_id, int waypoint_order){
        waypointMarker = wp;
        name = nam;
        Tour_ID = tour_id;
        waypointOrder = waypoint_order;
    }

    public void addMedia(Media component){
        mediaComponents.add(component);
    }

    @Override
    public String toString() {
        return "Lat: " + waypointMarker.latitude + "\nLong: " + waypointMarker.longitude;
    }


    public int getWaypointOrder(){
        return waypointOrder;
    }

    public LatLng getWaypointMarker(){
        return waypointMarker;
    }

    public String getName(){
        return name;
    }

    public int getTour_ID(){
        return Tour_ID;
    }
}
