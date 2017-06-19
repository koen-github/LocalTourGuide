package com.vanderkruk.localtourguide.userinterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.vanderkruk.localtourguide.R;
import com.vanderkruk.localtourguide.datamodel.Tour;
import com.vanderkruk.localtourguide.datamodel.media.Media;
import com.vanderkruk.localtourguide.datamodel.media.Text;

/**
 * Created by koen on 19-6-2017.
 */

public class ShowMediaItem extends AppCompatActivity {

    private ArrayAdapter<String> listAdapter ;
    private Text currentTextItem;
    private TextView textTitle;
    private TextView textText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_media_item);
        textTitle = (TextView) findViewById(R.id.mediaTitle);
        textText = (TextView) findViewById(R.id.mediaText);
        currentTextItem = (Text)getIntent().getSerializableExtra("currentText");
        if(currentTextItem != null){
            textTitle.setText(currentTextItem.getTitle());
            textText.setText(currentTextItem.getText());
        }

    }



}
