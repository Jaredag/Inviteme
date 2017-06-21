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
import java.util.Set;

public class MyShindigs extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shindigs);

        SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String objNull = mPrefs.getString(Pref, null);

        if (objNull != null) {
            Log.d("Test", "TESTING!!!!!!!!!!!!!!!!!!");
            Gson gson = new Gson();
            String json = mPrefs.getString(Pref, "");
            Set<Shindig> shindigList = gson.fromJson(json, Set.class);
            Shindig myShindig;
            Log.d("Test3", "Before for loop");
            for (int i = 0; i < shindigList.size(); i++)
            {
                Log.d("Test4", "Inside for loop");
                //myShindig = shindigList.get(i);
                Log.d("Test5", "Really inside of loop");
                //String message = "This is a title: " + myShindig.getTitle() + " Desc: " + myShindig.getDescription();
                //Log.d("Tag", message);
            }
            //Shindig myShindig = gson.fromJson(json, Shindig.class);



            // Setting up the ListView
            ListView mListView = (ListView) findViewById(R.id.listView2);

           // List<Shindig> shindigList = new ArrayList<Shindig>(); // ONLINE CODE HAD "=SHINDIG.GETRECIPESFROMFILE("FILE.JSON", THIS);"

            //String[] shindigList = new String[10];
            //Vector<Shindig> listShindigs = new Vector<Shindig>();

            //for (int i = 0; i < 1; i++)//shindigList.size(); i++)
            //{
                //Shindig _shindig = shindigList.get(i);
             //   shindigList.add(myShindig);
                //listItems[i] = _shindig.getTitle();
                // listItems[i] = myShindig.getTitle();
            //}

            //ArrayAdapter adapter = new ArrayAdapter<Shindig>(this, android.R.layout.simple_list_item_1, shindigList);
            //mListView.setAdapter(adapter);

        }
        /* Access shared preferences     NEEDS TO BE MODIFIED FOR MULTIPLE EVENTS
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Gson gson = new Gson();
        String json = settings.getString(PREFS_NAME, "");
        Shindig myShindig = gson.fromJson(json, Shindig.class);


        // Setting up the ListView
        ListView mListView = (ListView) findViewById(R.id.listView2);

        final ArrayList<Shindig> shindigList = new ArrayList<Shindig>(); // ONLINE CODE HAD "=SHINDIG.GETRECIPESFROMFILE("FILE.JSON", THIS);"

        String[] listItems = new String[shindigList.size()];
        //Vector<Shindig> listShindigs = new Vector<Shindig>();

        for (int i = 0; i < 1; i++)//shindigList.size(); i++)
        {
            //Shindig _shindig = shindigList.get(i);
            //listShindigs.add(_shindig);
            //listItems[i] = _shindig.getTitle();

            listItems[i] = myShindig.getTitle();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);*/
    }

    public void createAnEvent(View view){
        // Create an event
        Intent intent = new Intent(this, CreateShindigActivity.class);
        startActivity(intent);
    }

    public void continueAsGuest(View view){
        // Go to the Main Feed
        Intent intent = new Intent(this, MainFeedActivity.class);
        startActivity(intent);
    }

}


