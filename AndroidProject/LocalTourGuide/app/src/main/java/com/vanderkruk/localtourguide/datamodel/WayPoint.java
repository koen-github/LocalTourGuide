package com.vanderkruk.localtourguide.datamodel;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by koen on 8-5-2017.
 */

public class WayPoint {
    private LatLng waypointMarker;
    private List<Media> mediaComponents;

    public WayPoint(LatLng wp){
        waypointMarker = wp;
    }

    public void addMedia(Media component){
        mediaComponents.add(component);
    }
}
