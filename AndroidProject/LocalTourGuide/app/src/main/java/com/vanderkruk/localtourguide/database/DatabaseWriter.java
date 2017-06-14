package com.vanderkruk.localtourguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

/**
 * Created by koen on 11-6-2017.
 */

public class DatabaseWriter {
    protected DatabaseHelper dbh;
    private String SQL_CREATE;
    private String SQL_DELETE;
    protected Context currentContext;

    public DatabaseWriter(Context c, String cr, String d){
        dbh = DatabaseHelper.getInstance(c);
        SQL_CREATE = cr;
        currentContext = c;

        SQL_DELETE = d;
    }

    public void writeInformationToDatbase(String query){
        dbh.runSQLQuery(query);
    }

    public void insertInformationToDatabase(String tableName, ContentValues cv){
        try{
            Log.d("DatabaseWriter", "Well that was a succes :)");
            dbh.getWritableDatabase().insertOrThrow(tableName, null, cv);
        }
        catch(Exception e){
            //database couldn't be found.
            dbh.runSQLQuery(SQL_CREATE);
            dbh.getWritableDatabase().insertOrThrow(tableName, null, cv);
        }
    }
}
