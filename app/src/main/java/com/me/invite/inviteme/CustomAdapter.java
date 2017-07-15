package com.me.invite.inviteme;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * CustomAdapter: Displays events in a listview with all the information from an event
 * Created by Jared on 6/19/2017.
 * Found a tutorial on how to create a custom array adapter and implement it in our application
 * Guide:  https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

public class CustomAdapter extends ArrayAdapter<Shindig> {
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    private ArrayList arrayList;
    public static Context context;
    private FirebaseDatabase database;
    public CustomAdapter(Context context, ArrayList<Shindig> shindigs) {
        super(context, 0, shindigs);
        this.arrayList = shindigs;
        database = database.getInstance();
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final Shindig shindig = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customized_event_box, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView desc = (TextView) convertView.findViewById(R.id.txtDesc);
        TextView host = (TextView) convertView.findViewById(R.id.txtHost);
        TextView location = (TextView) convertView.findViewById(R.id.txtLocation);
        TextView time = (TextView) convertView.findViewById(R.id.txtTime);
        TextView spots = (TextView) convertView.findViewById(R.id.txtSpots);

        title.setText(shindig.getTitle());
        desc.setText(shindig.getDescription());
        host.setText(shindig.getHost().getUserName());
        location.setText(shindig.getLocation());
        time.setText(shindig.getDate());
        spots.setText(String.valueOf(shindig.getNumSpots()));
        Log.d("isKey", "Key is: " + shindig.getKeyShin() + " : " + shindig.getTitle());

        Button rsvp = (Button) convertView.findViewById(R.id.button_RSVP);

        final View finalConvertView = convertView;
        final Shindig shins = (Shindig) arrayList.get(position);

        rsvp.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Log.d("locationTest", "location: " + shins.getLocation() + "key: " + shins.getKeyShin());
                DatabaseReference shin = database.getReference("Shindig");

                final Gson gson = new Gson();

                shin.orderByChild("location").equalTo(shins.getLocation()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot childSnap : dataSnapshot.getChildren()) {
                            Shindig shindig = new Shindig(childSnap.getValue(Shindig.class));
                            String message = "FREAK!!!!: " + childSnap.child("location").getValue();
                            Log.d("testing124", message);
                            final SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                            final String objNull = mPrefs.getString(Pref, null);
                            if (objNull != null) {
                                Log.i("ShindigCreated", "CREATE NEW SHINDIG!");
                                String json = mPrefs.getString(Pref, "");
                                Type type = new TypeToken<ArrayList<Shindig>>() {}.getType();
                                ArrayList<Shindig> shindigList = gson.fromJson(json, type);
                                shindigList.add(shindig);
                                String message1 = "This is a title: " + shindig.getTitle() + " Desc: " + shindig.getDescription();
                                Log.d("Tag", message1);
                                SharedPreferences.Editor editor = mPrefs.edit();
                                String json2 = gson.toJson(shindigList);
                                editor.putString(Pref, json2);
                                editor.commit();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("customAdError", "Error on rsvp occurred");
                    }
                });
            }
        });

        return convertView;
    }
}
