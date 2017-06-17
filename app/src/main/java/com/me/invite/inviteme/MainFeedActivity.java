package com.me.invite.inviteme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

public class MainFeedActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(PREFS_NAME, "");
        Shindig myShindig = gson.fromJson(json, Shindig.class);
        String message = "This is a title: " + myShindig.getTitle() + " Desc: " + myShindig.getDescription();
        Log.d("Tag", message);

    }

    public void createAnEvent(View view){
        // Create an Event
        Intent intent = new Intent(this, CreateShindigActivity.class);
        startActivity(intent);
    }

    public void seeYourEvents(View view){
        Intent intent = new Intent(this, MyShindigs.class);
        startActivity(intent);
    }
}
