package com.me.invite.inviteme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

public class MainFeedActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);
        // stuff more changes
    }

    // Continue as Guest to the Main feed
    public void createAnEvent(View view){
        // Create an Eventf
        Intent intent = new Intent(this, CreateShindigActivity.class);
        startActivity(intent);
    }

    public void seeYourEvents(View view){
        Intent intent = new Intent(this, myShindigsActivity.class);
        startActivity(intent);
    }

    // This will be used for the myEventsActivity
    // this will not be a function though, just code in the
    // activity itself in the onCreate
    public void getYourEvents(View view){
        SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        String json = mPrefs.getString("shindig", "");
        Shindig myShindigs = gson.fromJson(json, Shindig.class);
        // Display them on the screen. 
    }

    public void saveEvent(View view){
        // Get id of the shindig
        String shindig = "Event";
        SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(shindig);
        editor.putString(shindig, json);
        editor.commit();
    }

}
