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


        // Setting up the ListView
        ListView mListView = (ListView) findViewById(R.id.listView2);

        final ArrayList<Shindig> shindigList = new ArrayList<Shindig>(); // ONLINE CODE HAD "=SHINDIG.GETRECIPESFROMFILE("FILE.JSON", THIS);"

        String[] listItems = new String[shindigList.size()];
        //Vector<Shindig> listShindigs = new Vector<Shindig>();

        for (int i = 0; i < 1; i++)//shindigList.size(); i++)
        {
            /*Shindig _shindig = shindigList.get(i);
            listShindigs.add(_shindig);
            listItems[i] = _shindig.getTitle();*/

            listItems[i] = myShindig.getTitle();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);



    }

    public void createAnEvent(View view){
        // Create an event
        Intent intent = new Intent(this, CreateShindigActivity.class);
        startActivity(intent);
    }

    public void seeYourEvents(View view){
        Intent intent = new Intent(this, MyShindigs.class);
        startActivity(intent);
    }
}
