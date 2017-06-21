package com.me.invite.inviteme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "com.me.invite.inviteme.MESSAGE";
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pull the array of Shindigs created for the My Events page
        SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        Set<Shindig> shindigList = (Set<Shindig>) new ArrayList<Shindig>();
        SharedPreferences.Editor editor = mPrefs.edit();
        String json2 = gson.toJson(shindigList);
        editor.putString(Pref, json2);
        editor.commit();
    }

    // Continue as Guest to the Main feed
    public void continueAsGuest(View view){
        // Go to the Main Feed
        Intent intent = new Intent(this, MainFeedActivity.class);
        startActivity(intent);
    }

}
