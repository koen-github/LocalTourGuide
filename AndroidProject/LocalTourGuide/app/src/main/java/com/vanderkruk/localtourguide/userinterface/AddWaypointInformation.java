package com.vanderkruk.localtourguide.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.vanderkruk.localtourguide.datamodel.Tour;
import com.vanderkruk.localtourguide.datamodel.WayPoint;

/**
 * Created by koen on 12-6-2017.
 */

public class AddWaypointInformation extends AppCompatActivity {

    private EditText waypointOrder;
    private EditText currentText;
    private WayPoint currentWaypoint;

    private Tour currentTour; //todo give object from addtourinformation.

    int PLACE_PICKER_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_waypoint_information);

        waypointOrder = (EditText) findViewById(R.id.waypointOrder);
        currentText = (EditText) findViewById(R.id.textInformation);
        currentTour = (Tour)getIntent().getSerializableExtra("currentTour");
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                WayPoint wp = new WayPoint(place.getLatLng().latitude, place.getLatLng().longitude,place.getName().toString(), currentTour.getId(), Integer.parseInt(waypointOrder.getText().toString()));
                currentTour.addWaypoint(wp);
                Log.d("Tourie", Integer.toString(currentTour.getId()));
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onSelectWaypoint(View v){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public void onSaveWaypoint(View v){
        Intent intent = this.getIntent();
        intent.putExtra("currentTour", currentTour);
        this.setResult(RESULT_OK, intent);

        finish();
    }

}
