package com.vanderkruk.localtourguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vanderkruk.localtourguide.datamodel.Tour;
import com.vanderkruk.localtourguide.datamodel.WayPoint;

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


    public void addWaypointToDatabase(WayPoint wp){
        Log.d("WayPointDatabaseHelper", "Waypoint writing to database: " + wp);
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put("Tour_ID", wp.getTour_ID());
        values.put("Name", wp.getName());
        values.put("WaypointOrder", wp.getWaypointOrder());
        values.put("Lat", wp.getWaypointMarker().latitude);
        values.put("Lon", wp.getWaypointMarker().longitude);

        insertInformationToDatabase("WaypointInformation", values);
    }

}

