package com.me.invite.inviteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import static com.me.invite.inviteme.R.id.scrollView;

public class MainFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);
        // stuff more changes

        // Read in events from database
        DatabaseStorage dbstorage = new DatabaseStorage();

        // Pull array of events from database
        ArrayList<Shindig> shindigs = new ArrayList<Shindig>();
        shindigs = dbstorage.getShindigs();

        // Display the events in the correct format in the list

        //
    }

    /*public void createShindig(View view) {
        // Go to the create Event page
        Intent intent = new Intent(this, Shindig.class);
        startActivity(intent);
    }*/
}
