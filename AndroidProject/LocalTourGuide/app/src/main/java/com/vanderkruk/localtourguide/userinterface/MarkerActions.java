package com.vanderkruk.localtourguide.userinterface;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by koen on 8-6-2017.
 */

public class MarkerActions implements GoogleMap.OnMarkerClickListener{
    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        Log.d("Marker: ", "You pressed the marker." + marker.getTitle());
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}
