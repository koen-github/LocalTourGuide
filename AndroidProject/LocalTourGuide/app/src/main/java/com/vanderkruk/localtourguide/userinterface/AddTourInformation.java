package com.vanderkruk.localtourguide.userinterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.vanderkruk.localtourguide.R;
import com.vanderkruk.localtourguide.database.TourDatabaseHelper;
import com.vanderkruk.localtourguide.database.WayPointDatabaseHelper;
import com.vanderkruk.localtourguide.datamodel.Tour;
import com.vanderkruk.localtourguide.datamodel.WayPoint;

/**
 * Created by koen on 8-6-2017.
 */

public class AddTourInformation extends AppCompatActivity{

    private EditText inputTitle;
    private EditText inputAuthor;
    private EditText inputCity;
    private ListView allWaypoints;

    private ArrayAdapter<String> listAdapter ;

    int PLACE_PICKER_REQUEST = 1;
    int ADD_WAYPOINT_REQUEST = 2;
    Tour currentTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tour_information);

        inputTitle = (EditText) findViewById(R.id.tourTitleId);
        inputAuthor = (EditText) findViewById(R.id.tourAuthorId);
        inputCity = (EditText) findViewById(R.id.tourCityId);
        currentTour = new Tour();
        allWaypoints = (ListView) findViewById(R.id.allWaypoints);

    }

    public void addWaypoint(View v){
        Intent intent = new Intent(AddTourInformation.this, AddWaypointInformation.class);
        intent.putExtra("currentTour",currentTour);
        //todo: give object by looper and message system.
        startActivityForResult(intent, ADD_WAYPOINT_REQUEST);
        //todo
    }

    @Override
    public void onResume(){
        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_view_row, currentTour.getAllWaypointsAsString());
        // Set the ArrayAdapter as the ListView's adapter.
        allWaypoints.setAdapter( listAdapter );
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == ADD_WAYPOINT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                currentTour = (Tour)data.getSerializableExtra("currentTour");
                Log.d("Tour", "TOUR INFO IS SET");
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

        WayPointDatabaseHelper wph = new WayPointDatabaseHelper(this);

        for(WayPoint wp: currentTour.getAllWaypoints()){
            wph.addWaypointToDatabase(wp);
        }

        Log.d("AddTourInformation", "Saving Tour...");
    }

}


