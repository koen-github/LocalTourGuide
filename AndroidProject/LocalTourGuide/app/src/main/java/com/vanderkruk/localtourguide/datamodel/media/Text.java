package com.vanderkruk.localtourguide.datamodel.media;

import com.vanderkruk.localtourguide.datamodel.media.Media;

/**
 * Created by koen on 8-5-2017.
 */

public class Text extends Media {
    private String waypointText;


    public Text(String title, int wapsId, String writtenText){
        super(title, wapsId);
        waypointText = writtenText;
    }

    public void setText(String tex){
        waypointText = tex;
    }

    public String getText(){
        return waypointText;
    }
}
