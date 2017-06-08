package com.vanderkruk.localtourguide.userinterface;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.vanderkruk.localtourguide.R;
import com.vanderkruk.localtourguide.datamodel.Tour;

/**
 * Created by koen on 8-6-2017.
 */

public class AddTourInformation extends AppCompatActivity{

    private EditText inputTitle;
    private EditText inputAuthor;
    private EditText inputCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tour_information);

        inputTitle = (EditText) findViewById(R.id.tourTitleId);
        inputAuthor = (EditText) findViewById(R.id.tourAuthorId);
        inputCity = (EditText) findViewById(R.id.tourCityId);
    }

    public void saveTour(View v){
        String title = inputTitle.getText().toString();
        String author = inputAuthor.getText().toString();
        String city = inputCity.getText().toString();
        Tour currentTour = new Tour();
        currentTour.setName(title);
        currentTour.setUser(author);
        currentTour.setCity(city);




        Log.d("AddTourInformation", "Saving Tour...");
    }

}


