package com.vanderkruk.localtourguide.datamodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.vanderkruk.localtourguide.database.TourDatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by koen on 8-5-2017.
 */

public class Tour implements Serializable {

    private int rating;
    private String city;
    private String user;
    private String name;
    private List<WayPoint> allWaypoints;
    private int id;

    public Tour(){
        Random rand = new Random();
        int value = rand.nextInt(3000);
        id = value;
        allWaypoints = new ArrayList<WayPoint>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void addWaypoint(WayPoint theWaypoint){
        allWaypoints.add(theWaypoint);
    }

    public void removeWaypoint(WayPoint theWaypoint){
        allWaypoints.remove(theWaypoint);
    }

    public void addAllWaypoints(List<WayPoint> theWaypoints){
        allWaypoints = theWaypoints;
    }

    public List<WayPoint> getAllWaypoints(){
        return allWaypoints;
    }

    public int getId(){
        return id;
    }

    public void setId(int ifo){
        id = ifo;
    }

    public List<String> getAllWaypointsAsString(){
        List<String> stringsList = new ArrayList<String>(allWaypoints.size());
        for (WayPoint wp : allWaypoints) {
            stringsList.add(wp.toString());
        }
        return stringsList;
    }
}
