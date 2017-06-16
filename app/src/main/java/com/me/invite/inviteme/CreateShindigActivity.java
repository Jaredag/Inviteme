package com.me.invite.inviteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateShindigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shindig);
    }

     public void createNewShindig(View view) {
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
         PartyAnimal partyAnimal = new PartyAnimal("Daniel", "555-555-555");
         Shindig shindig = new Shindig(title, description, location, date_time, spots, partyAnimal);
         Intent intent = new Intent(this, MainFeedActivity.class);
         startActivity(intent);
    }
}
