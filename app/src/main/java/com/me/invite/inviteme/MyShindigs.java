package com.me.invite.inviteme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * MyShindigs: Display a list of the events the user has created/joined
 */
public class MyShindigs extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    @Override

    /**
     * onCreate: Reads all the events from shared preferences and displays them
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shindigs);

        SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String objNull = mPrefs.getString(Pref, null);

        if (objNull != null) {
            Log.d("Test", "Your Events Test");
            Gson gson = new Gson();
            String json = mPrefs.getString(Pref, "");
            Type type = new TypeToken<ArrayList<Shindig>>() {}.getType();
            ArrayList<Shindig> shindigList = gson.fromJson(json, type);

            for (int i = 0; i < shindigList.size(); i++)
            {
                Shindig myShindig = shindigList.get(i);
                String message = "This is a title: " + myShindig.getTitle() + " Desc: " + myShindig.getDescription();
                Log.d("Test2", message);
            }

            CustomAdapter adapter = new CustomAdapter(this, shindigList);
            ListView mListView = (ListView) findViewById(R.id.listView2);
            mListView.setAdapter(adapter);
        }
    }

    /**
     * createAnEvent: Goes to the Create Event page
     * @param view
     */
    public void createAnEvent(View view){
        // Create an event
        Log.i("TestCreateButton", "Create Button was pressed");
        Intent intent = new Intent(this, CreateShindigActivity.class);
        startActivity(intent);
    }

    /**
     * goToMainFeed: Goes back to the Main Feed page
     * @param view
     */
    public void goToMainFeed(View view){
        // Go to the Main Feed
        Log.i("TestMainFeedButton", "Main Feed button was pressed");
        Intent intent = new Intent(this, MainFeedActivity.class);
        startActivity(intent);
    }

}


