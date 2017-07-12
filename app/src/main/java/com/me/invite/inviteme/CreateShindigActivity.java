package com.me.invite.inviteme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CreateShindigActivity: Make another event item
 */

public class CreateShindigActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    private DatabaseStorage database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shindig);
        database = database.getInstance();
    }

    public void displayToast(CharSequence message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * createNewShindig: Lets the user create a new event and saves it to firebase and shared preferences and goes to the main feed page
     * @param view
     */
     public void createNewShindig(View view) {
         // Pull fields from the create events page

         int spots = 0;
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
         if (!spotsString.isEmpty())
             spots = Integer.parseInt(spotsString);
         else {
             displayToast("Number of spots is empty!");
             return;
         }

         if (title.isEmpty() || description.isEmpty() || location.isEmpty() || date_time.isEmpty() || spots > 50 || spots < 2)
         {
             if (title.isEmpty())
                 displayToast("Title is empty!");
             if (description.isEmpty())
                 displayToast("Description is empty!");
             if (location.isEmpty())
                 displayToast("Location is empty!");
             if (date_time.isEmpty())
                 displayToast("Date/Time is empty!");
             if (spots > 50 || spots < 2)
                 displayToast("# of spots must be 2-50!");
             return;
         }
         // Pull from shared host name.
         PartyAnimal partyAnimal = new PartyAnimal("Guest", "No Number");

         // Create a class
         Shindig shindig = new Shindig(title, description, location, date_time, spots, partyAnimal);

         // Pull the array of Shindigs created for the My Events page
         SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
         String objNull = mPrefs.getString(Pref, null);
         Gson gson = new Gson();

         if (objNull != null) {
             Log.i("ShindigCreated", "CREATE NEW SHINDIG!");
             String json = mPrefs.getString(Pref, "");
             Type type = new TypeToken<ArrayList<Shindig>>() {}.getType();
             ArrayList<Shindig> shindigList = gson.fromJson(json, type);
             shindigList.add(shindig);
             String message = "This is a title: " + shindig.getTitle() + " Desc: " + shindig.getDescription();
             Log.d("Tag", message);
             SharedPreferences.Editor editor = mPrefs.edit();
             String json2 = gson.toJson(shindigList);
             editor.putString(Pref, json2);
             editor.commit();
         }

         database.pushShindig(shindig);
         Log.i("Sent", "Sent to database");
         Intent intent = new Intent(this, MainFeedActivity.class);
         startActivity(intent);
    }

}
