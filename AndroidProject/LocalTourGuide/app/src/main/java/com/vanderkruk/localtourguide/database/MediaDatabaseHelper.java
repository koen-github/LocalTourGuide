package com.vanderkruk.localtourguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.vanderkruk.localtourguide.datamodel.Tour;
import com.vanderkruk.localtourguide.datamodel.WayPoint;
import com.vanderkruk.localtourguide.datamodel.media.Media;
import com.vanderkruk.localtourguide.datamodel.media.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koen on 12-6-2017.
 */

public class MediaDatabaseHelper extends DatabaseWriter {


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE MediaInformation (" +
                    "id INTEGER AUTO INCREMENT," +
                    "WaypointId INTEGER," +
                    "MediaType TEXT," +
                    "Title TEXT," +
                    "AudioFile TEXT," +
                    "PictureFile TEXT," +
                    "WrittenText TEXT," +
                    "FOREIGN KEY (WaypointId) REFERENCES WaypointInformation(id))";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS MediaInformation";

    public MediaDatabaseHelper(Context x){
        super(x, SQL_CREATE_ENTRIES, SQL_DELETE_ENTRIES);
    }



    public WayPoint connectWaypointsAndMedia(WayPoint waps){


        Cursor cursor = dbh.getQuery("SELECT  * FROM MediaInformation WHERE WaypointId='" + waps.getId() + "'");


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(3);
                int waypointId = cursor.getInt(1);
                String writtenText = cursor.getString(6);

                Text infoComponent = new Text(title, waypointId, writtenText);
                infoComponent.setText(writtenText);

                waps.addMedia(infoComponent);


            } while (cursor.moveToNext());
        }

        return waps;

    }


    public void removeMediaItemWithID(int id){
        writeInformationToDatbase("delete from MediaInformation where id='"+id+"'");
    }

    public void addMediaItemToDatabase(Media item){

        if(item instanceof Text){
            Text cT = (Text) item;

            Log.d("MediaDatabaseHelper", "Writing Text to Database" + cT.getTitle());
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();

            values.put("MediaType", "Text");
            values.put("Title", cT.getTitle());
            values.put("WaypointId", cT.getWayPointID());
            values.put("WrittenText", cT.getText());

            insertInformationToDatabase("MediaInformation", values);
        }

    }

}

