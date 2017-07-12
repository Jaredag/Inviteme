package com.me.invite.inviteme;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * CustomAdapter: Displays events in a listview with all the information from an event
 * Created by Jared on 6/19/2017.
 * Found a tutorial on how to create a custom array adapter and implement it in our application
 * Guide:  https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

public class CustomAdapter extends ArrayAdapter<Shindig> {
    private ArrayList arrayList;
    private FirebaseDatabase database;
    public CustomAdapter(Context context, ArrayList<Shindig> shindigs) {
        super(context, 0, shindigs);
        this.arrayList = shindigs;
        database = database.getInstance();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Shindig shindig = getItem(position);

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

            public void onClick(View view){

                /*DatabaseReference shin  = database.getReference("Shindig");

                shin.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //for (DataSnapshot objSnapshot: snapshot.getChildren()) {
                            //Object obj = objSnapshot.getKey();
                            Shindig shindig = new Shindig(snapshot.getValue(Shindig.class));
                            String message = "FREAK!!!!: " + shindig.getTitle() + " Desc: " + shindig.getDescription();
                            Log.d("testing124", message);

                    }
                    @Override
                    public void onCancelled(DatabaseError firebaseError) {
                        Log.e("Read failed", firebaseError.getMessage());
                    }
                });
                //String key = shins.getKey();
                //TextView shindigKey = (TextView) finalConvertView.findViewById(R.id.key);
                //String key = shin.getText().toString();
                //Log.d("TestKey", "Got the key: ");*/
            }
        });

        return convertView;
    }

    public void continueAsGuest(View view){

    }
}
