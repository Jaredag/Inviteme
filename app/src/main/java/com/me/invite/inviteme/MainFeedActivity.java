package com.me.invite.inviteme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * MainFeedActivity: Display all the events going on
 */
public class MainFeedActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    private FirebaseDatabase database;
    @Override
    /**
     * onCreate: Read from Firebase the different events and display them using a custom adapter
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);
        database = database.getInstance();

       //pullCategoryEvents("Shindig");

        final Context context = this;
        database.getReference("Shindig").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                ArrayList<Shindig> shindigList = new ArrayList<Shindig>();
                Iterable<DataSnapshot>  it = dataSnapshot.getChildren();
                for (DataSnapshot snap : it) {
                    Shindig shindig = new Shindig(snap.getValue(Shindig.class));
                    String message = "This is a key: " + shindig.getKeyShin() + " Desc: " + shindig.getDescription();
                    Log.d("Tag", message);
                    shindigList.add(shindig);
                }
                CustomAdapter adapter = new CustomAdapter(context, shindigList);
                ListView mListView = (ListView) findViewById(R.id.listView);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error){
                Log.w("Failed", "Failed to read value", error.toException());
            }

        });
    }

    /**
     * createAnEvent: Go to the Create Event page
     * @param view
     */
    public void createAnEvent(View view){
        // Create an event
        Log.i("CreateButtonMainFeed", "Create Button was pressed");
        Intent intent = new Intent(this, CreateShindigActivity.class);
        startActivity(intent);
    }

    /**
     * seeYourEvents: Go to the My Events page
     * @param view
     */
    public void seeYourEvents(View view){
        Log.i("TestYourFeedButton", "Your Feed button was pressed");
        Intent intent = new Intent(this, MyShindigs.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.categories_menu, menu);
        return true;
    }

    /*public void pullCategoryEvents(String category){
         final Context context = this;
        database.getReference(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                ArrayList<Shindig> shindigList = new ArrayList<Shindig>();
                Iterable<DataSnapshot>  it = dataSnapshot.getChildren();
                for (DataSnapshot snap : it) {
                    Shindig shindig = new Shindig(snap.getValue(Shindig.class));
                    String message = "This is a key: " + shindig.getKeyShin() + " Desc: " + shindig.getDescription();
                    Log.d("Tag", message);
                    shindigList.add(shindig);
                }
                CustomAdapter adapter = new CustomAdapter(context, shindigList);
                ListView mListView = (ListView) findViewById(R.id.listView);
                mListView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError error){
                Log.w("Failed", "Failed to read value", error.toException());
            }

        });
    }*/

    public boolean onOptionsItemSelected(MenuItem item) {
        final String category = item.getTitle().toString();
        Log.d("ChosenCategory", "Category was chosen: " + category);
        //pullCategoryEvents(category);
        /*
        if (category == "Athletics" || category == "Movies, Board Games, etc." || category == "Party (Dancing, Music, etc.)" || category == "Outdoors (Fishing, Camping, etc.)" || category == "Cookouts or BBQs") {
            return true;
        }*/
        return true;
    }
}
