package com.vanderkruk.localtourguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vanderkruk.localtourguide.datamodel.Tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koen on 8-6-2017.
 */

public class TourDatabaseHelper extends DatabaseWriter {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE TourInformation (" +
                    "id INTEGER," +
                    "Name TEXT," +
                    "Rating INTEGER," +
                    "User VARCHAR(300)," +
                    "City VARCHAR(300))";



    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS TourInformation";

    public TourDatabaseHelper(Context x){
        super(x, SQL_CREATE_ENTRIES, SQL_DELETE_ENTRIES);
    }


    public void removeTourWithID(int id){
        writeInformationToDatbase("delete from TourInformation where id='"+id+"'");
    }

    public List<Tour> getAllTours(){
        List<Tour> allTours = new ArrayList<Tour>();

        try {
            Cursor cursor = dbh.getQuery("SELECT * FROM TourInformation");

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Tour tourObject = new Tour();

                    tourObject.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    tourObject.setName(cursor.getString(cursor.getColumnIndex("Name")));
                    tourObject.setRating(cursor.getInt(cursor.getColumnIndex("Rating")));
                    tourObject.setCity(cursor.getString(cursor.getColumnIndex("City")));
                    tourObject.setUser(cursor.getString(cursor.getColumnIndex("User")));

                    allTours.add(tourObject);

                } while (cursor.moveToNext());
            }
        }
        catch(Exception e){

        }
        return allTours;

    }


    public void addTourToDatabase(Tour t){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put("City", t.getCity());
        values.put("Rating", t.getRating());
        values.put("Name", t.getName());
        values.put("User", t.getUser());
        values.put("id", t.getId());

        insertInformationToDatabase("TourInformation", values);
    }

}
