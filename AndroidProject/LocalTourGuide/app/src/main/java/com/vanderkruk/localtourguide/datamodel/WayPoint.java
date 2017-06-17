package com.vanderkruk.localtourguide.datamodel;

import com.vanderkruk.localtourguide.datamodel.media.Media;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private int id;

    public WayPoint(double LAT, double LNG, String nam, int tour_id, int waypoint_order){
        Lat = LAT;
        Lng = LNG;
        name = nam;
        Tour_ID = tour_id;
        waypointOrder = waypoint_order;
        mediaComponents = new ArrayList<Media>();
        Random rand = new Random();
        id = rand.nextInt(300000);
    }

    public void addMedia(Media component){
        mediaComponents.add(component);
    }

    public List<Media> getAllMediaComponents(){
        return mediaComponents;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
