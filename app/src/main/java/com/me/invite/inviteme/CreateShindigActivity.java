package com.me.invite.inviteme;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CreateShindigActivity: Make another event item
 */

public class CreateShindigActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    private DatabaseStorage database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shindig);
        database = database.getInstance();
    }

    public void displayToast(CharSequence message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    //Begin of change
    Calendar date;
    final Context context = this;
    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }
    //End of change

    /**
     * createNewShindig: Lets the user create a new event and saves it to firebase and shared preferences and goes to the main feed page
     * @param view
     */
     public void createNewShindig(View view) {
         // Pull fields from the create events page

         int spots = 0;
         EditText titleText = (EditText) findViewById(R.id.eventTitle);
         String title = titleText.getText().toString();
         EditText descriptionText = (EditText) findViewById(R.id.eventDesc);
         String description = descriptionText.getText().toString();
         EditText locationText = (EditText) findViewById(R.id.eventLocation);
         String location = locationText.getText().toString();
         //Begin of change
         SimpleDateFormat dateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm");
         String date_time = dateTime.format(date.getTime());
         //End of change
         EditText spotsText = (EditText) findViewById(R.id.eventSpots);
         String spotsString = spotsText.getText().toString();
         Spinner categories = (Spinner) findViewById(R.id.spinnerCategory);
         String category = categories.getSelectedItem().toString();
         ArrayList <PartyAnimal> partyAnimals = new ArrayList<PartyAnimal>();
         if (!spotsString.isEmpty())
             spots = Integer.parseInt(spotsString);
         else {
             displayToast("Number of spots is empty!");
             return;
         }

         if (title.isEmpty() || description.isEmpty() || location.isEmpty() || date_time.isEmpty() || spots > 50 || spots < 2 || category.isEmpty())
         {
             if (title.isEmpty())
                 displayToast("Title is empty!");
             if (description.isEmpty())
                 displayToast("Description is empty!");
             if (location.isEmpty())
                 displayToast("Location is empty!");
             if (date_time.isEmpty())
                 displayToast("Date/Time is empty!");
             if (spots > 50 || spots < 2)
                 displayToast("# of spots must be 2-50!");
             if (category.isEmpty())
                 displayToast("Category has not been selected!");
             return;
         }
         // Pull from shared host name
         PartyAnimal partyAnimal = new PartyAnimal("Daniel", "555-555-555");

         // Create a class
         Shindig shindig = new Shindig(title, description, location, date_time, spots, partyAnimal, partyAnimals, category);

         // Pull the array of Shindigs created for the My Events page
         SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
         String objNull = mPrefs.getString(Pref, null);
         Gson gson = new Gson();

         if (objNull != null) {
             Log.i("ShindigCreated", "CREATE NEW SHINDIG!");
             String json = mPrefs.getString(Pref, "");
             Type type = new TypeToken<ArrayList<Shindig>>() {}.getType();
             ArrayList<Shindig> shindigList = gson.fromJson(json, type);
             shindigList.add(shindig);
             String message = "This is a title: " + shindig.getTitle() + " Desc: " + shindig.getDescription();
             Log.d("Tag", message);
             SharedPreferences.Editor editor = mPrefs.edit();
             String json2 = gson.toJson(shindigList);
             editor.putString(Pref, json2);
             editor.commit();
         }

         database.pushShindig(shindig);
         Log.i("Sent", "Sent to database");
         Intent intent = new Intent(this, MainFeedActivity.class);
         startActivity(intent);
    }

}
