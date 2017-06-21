package com.me.invite.inviteme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

public class CreateShindigActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shindig);
    }

     public void createNewShindig(View view) {
         // Pull fields from the create events page
         EditText titleText = (EditText) findViewById(R.id.eventTitle);
         String title = titleText.getText().toString();
         EditText descriptionText = (EditText) findViewById(R.id.eventDesc);
         String description = descriptionText.getText().toString();
         EditText locationText = (EditText) findViewById(R.id.eventLocation);
         String location = locationText.getText().toString();
         EditText date_timeText = (EditText) findViewById(R.id.eventDate);
         String date_time = date_timeText.getText().toString();
         EditText spotsText = (EditText) findViewById(R.id.eventSpots);
         String spotsString = spotsText.getText().toString();
         int spots = Integer.parseInt(spotsString);
         // Pull from shared host name.
         PartyAnimal partyAnimal = new PartyAnimal("Daniel", "555-555-555");

         // Create a class
         Shindig shindig = new Shindig(title, description, location, date_time, spots, partyAnimal);

         // Convert Class to json and store in shared preferences.
         SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
         SharedPreferences.Editor editor = mPrefs.edit();
         Gson gson = new Gson();
         String json = gson.toJson(shindig);
         editor.putString(Pref, json);
         editor.commit();

         Log.i("ShindigCreated", "CREATE NEW SHINDIG!");

         Intent intent = new Intent(this, MainFeedActivity.class);
         startActivity(intent);
    }

}
