package com.vanderkruk.localtourguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.vanderkruk.localtourguide.datamodel.Tour;
import com.vanderkruk.localtourguide.datamodel.WayPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koen on 12-6-2017.
 */

public class WayPointDatabaseHelper extends DatabaseWriter {


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE WaypointInformation (" +
                    "id INTEGER," +
                    "Tour_ID INTEGER," +
                    "WaypointOrder INTEGER," +
                    "Name TEXT," +
                    "Lat TEXT," +
                    "Lon TEXT," +
                    "FOREIGN KEY (Tour_ID) REFERENCES TourInformation(id))";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS WaypointInformation";

    public WayPointDatabaseHelper(Context x){
        super(x, SQL_CREATE_ENTRIES, SQL_DELETE_ENTRIES);
    }


    public void removeWaypointWith(int id){
        writeInformationToDatbase("delete from WaypointInformation where id='"+id+"'");
    }

    public Tour connectToursAndWaypoint(Tour connectTour){


        Cursor cursor = dbh.getQuery("SELECT  * FROM WaypointInformation WHERE Tour_ID='" + connectTour.getId() + "'");

        List<WayPoint> wapObjectList = new ArrayList<WayPoint>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(3);
                int wayPointOrder = cursor.getInt(2);
                String Lat = cursor.getString(4);
                String Lon = cursor.getString(5);


                WayPoint wapObject = new WayPoint(Double.parseDouble(Lat),Double.parseDouble(Lon), name, connectTour.getId(), wayPointOrder);
                wapObject.setId(cursor.getInt(0));

                wapObjectList.add(wapObject);


            } while (cursor.moveToNext());
        }
        connectTour.addAllWaypoints(wapObjectList);
        return connectTour;

    }


    public void addWaypointToDatabase(WayPoint wp){
        Log.d("WayPointDatabaseHelper", "Waypoint writing to database: " + wp);
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("id", wp.getId());
        values.put("Tour_ID", wp.getTour_ID());
        values.put("Name", wp.getName());
        values.put("WaypointOrder", wp.getWaypointOrder());
        values.put("Lat", wp.getLat());
        values.put("Lon", wp.getLng());

        insertInformationToDatabase("WaypointInformation", values);
    }

}

