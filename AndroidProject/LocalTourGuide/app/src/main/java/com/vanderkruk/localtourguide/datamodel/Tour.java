package com.vanderkruk.localtourguide.datamodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.vanderkruk.localtourguide.database.TourDatabaseHelper;

import java.util.List;

/**
 * Created by koen on 8-5-2017.
 */

public class Tour {

    private int rating;
    private String city;
    private String user;
    private String name;
    private List<WayPoint> allWaypoints;
    private int id;

    private Context ct;


    public Tour(Context c){
        ct = c;
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

    public List<WayPoint> getAllWaypoints(){
        return allWaypoints;
    }
}
