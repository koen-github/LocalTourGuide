package com.vanderkruk.localtourguide.datamodel.media;

import java.io.Serializable;

/**
 * Created by koen on 8-5-2017.
 */

public abstract class Media implements Serializable{
    private int WayPointID;
    private String title;

    public Media(String title, int wapID){
        WayPointID = wapID;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWayPointID() {
        return WayPointID;
    }

    public void setWayPointID(int wayPointID) {
        WayPointID = wayPointID;
    }
}
