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

import java.util.ArrayList;
import java.util.List;

public class MainFeedActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);
        // This will change to the firebase code.
        /*SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String objNull = mPrefs.getString(Pref, null);

        if (objNull != null) {
            Log.d("Test", "TESTING!!!!!!!!!!!!!!!!!!");
            Gson gson = new Gson();
            String json = mPrefs.getString(Pref, "");
            Shindig myShindig = gson.fromJson(json, Shindig.class);
            String message = "This is a title: " + myShindig.getTitle() + " Desc: " + myShindig.getDescription();
            Log.d("Test2", message);

            ArrayList<Shindig> shindigList = new ArrayList<Shindig>(); // ONLINE CODE HAD "=SHINDIG.GETRECIPESFROMFILE("FILE.JSON", THIS);"


            for (int i = 0; i < 1; i++)//shindigList.size(); i++)
            {
                //Shindig _shindig = shindigList.get(i);
                shindigList.add(myShindig);
                //listItems[i] = _shindig.getTitle();
                // listItems[i] = myShindig.getTitle();
            }

            CustomAdapter adapter = new CustomAdapter(this, shindigList);
            ListView mListView = (ListView) findViewById(R.id.listView);
            mListView.setAdapter(adapter);
        }*/

    }

    public void createAnEvent(View view){
        // Create an event
        Log.i("CreateButtonMainFeed", "Create Button was pressed");
        Intent intent = new Intent(this, CreateShindigActivity.class);
        startActivity(intent);
    }

    public void seeYourEvents(View view){
        Log.i("TestYourFeedButton", "Your Feed button was pressed");
        Intent intent = new Intent(this, MyShindigs.class);
        startActivity(intent);
    }
}
