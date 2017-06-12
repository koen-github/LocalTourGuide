package com.vanderkruk.localtourguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper theInstance = null;

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LocalTourDatabase.db";


    private SQLiteDatabase db;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();

    }

    public static DatabaseHelper getInstance(Context ctx){
        if (theInstance == null)
            theInstance = new DatabaseHelper(ctx);
        return theInstance;
    }

    public void runSQLQuery(String query){
        if(db != null)
        {
            Log.e("DatabaseHelper", "Running SQL query: " + query);

            db.execSQL(query);
        }
        else{
            Log.e("DatabaseHelper", "Database is still zero: " + query);
        }
    }


    public void onCreate(SQLiteDatabase qdb) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}
