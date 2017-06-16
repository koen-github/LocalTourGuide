package com.vanderkruk.localtourguide.datamodel;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.List;

/**
 * Created by koen on 8-5-2017.
 */

public class WayPoint implements Serializable{
    private double Lat;
    private double Lng;
    private List<Media> mediaComponents;
    private String name;
    private int Tour_ID;
    private int waypointOrder;

    public WayPoint(double LAT, double LNG, String nam, int tour_id, int waypoint_order){
        Lat = LAT;
        Lng = LNG;
        name = nam;
        Tour_ID = tour_id;
        waypointOrder = waypoint_order;
    }

    public void addMedia(Media component){
        mediaComponents.add(component);
    }

    @Override
    public String toString() {
        return "Lat: " + Lat + "\nLong: " + Lng;
    }

    public int getWaypointOrder(){
        return waypointOrder;
    }

    public double getLat(){
        return Lat;
    }

    public double getLng(){
        return Lng;
    }

    public String getName(){
        return name;
    }

    public int getTour_ID(){
        return Tour_ID;
    }
}
