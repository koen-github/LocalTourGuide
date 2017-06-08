package com.vanderkruk.localtourguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.vanderkruk.localtourguide.database.DatabaseHelper.Database.TABLE_NAME;

/**
 * Created by koen on 8-6-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper theInstance = null;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LocalTourDatabase.db";

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    /* Inner class that defines the table contents */
    public static class Database implements BaseColumns {
        public static final String TABLE_NAME = "TourInformation";
        public static final String COLUMN_NAME_TITLE = "Name";
        public static final String COLUMN_NAME_ = "Rating";
        public static final String COLUMN_NAME_ = "Rating";
    }


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE TourInformation (" +
                    "id INTEGER PRIMARY KEY," +
                    "Name TEXT," +
                    "Rating INTEGER," +
                    " INTEGER," +
                    "" +

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static DatabaseHelper getInstance(Context ctx){
        if (theInstance == null)
            theInstance = new DatabaseHelper(ctx);
        return theInstance;
    }

    public void removeVerjaardagWithId(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_NAME+" where _ID='"+id+"'");
        db.close();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void addItemToDatabase(String name, String date){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NAME_TITLE, name);
        values.put(Database.COLUMN_NAME_, date);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = this.getWritableDatabase().insert(TABLE_NAME, null, values);
        this.getWritableDatabase().close();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
