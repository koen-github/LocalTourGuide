package com.vanderkruk.localtourguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vanderkruk.localtourguide.datamodel.Tour;

/**
 * Created by koen on 8-6-2017.
 */

public class TourDatabaseHelper extends DatabaseWriter {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE TourInformation (" +
                    "id INTEGER PRIMARY KEY," +
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


    public void addTourToDatabase(Tour t){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put("City", t.getCity());
        values.put("Rating", t.getRating());
        values.put("Name", t.getName());
        values.put("User", t.getUser());

        insertInformationToDatabase("TourInformation", values);
    }

}
