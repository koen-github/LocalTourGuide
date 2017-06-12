package com.vanderkruk.localtourguide.userinterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.vanderkruk.localtourguide.R;
import com.vanderkruk.localtourguide.database.TourDatabaseHelper;
import com.vanderkruk.localtourguide.datamodel.Tour;
import com.vanderkruk.localtourguide.datamodel.WayPoint;

/**
 * Created by koen on 8-6-2017.
 */

public class AddTourInformation extends AppCompatActivity{

    private EditText inputTitle;
    private EditText inputAuthor;
    private EditText inputCity;
    int PLACE_PICKER_REQUEST = 1;
    Tour currentTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tour_information);

        inputTitle = (EditText) findViewById(R.id.tourTitleId);
        inputAuthor = (EditText) findViewById(R.id.tourAuthorId);
        inputCity = (EditText) findViewById(R.id.tourCityId);
        currentTour = new Tour(this);
    }

    public void addWaypoint(View v){

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                WayPoint wp = new WayPoint(place.getLatLng());
                currentTour.addWaypoint(wp);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void saveTour(View v){
        String title = inputTitle.getText().toString();
        String author = inputAuthor.getText().toString();
        String city = inputCity.getText().toString();

        currentTour.setName(title);
        currentTour.setUser(author);
        currentTour.setCity(city);
        currentTour.setRating(5);
        TourDatabaseHelper tdh = new TourDatabaseHelper(this);
        tdh.addTourToDatabase(currentTour);
        Log.d("AddTourInformation", "Saving Tour...");
    }

}


