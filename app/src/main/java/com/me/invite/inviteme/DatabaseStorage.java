package com.me.invite.inviteme;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * DatabaseStorage: Creates a Singleton for a Database instance
 * Created by Jared on 6/12/2017.
 */

public class DatabaseStorage {

    private static DatabaseStorage singleton = null;
    private FirebaseDatabase database;
    private ArrayList<Shindig> shindigs;

    protected DatabaseStorage(){
        // Just to defeat instantiations
        database = FirebaseDatabase.getInstance();
    }

    public static DatabaseStorage getInstance(){
        if (singleton == null){
            singleton = new DatabaseStorage();
        }
        return singleton;
    }

    public ArrayList<Shindig> getShindigs() {
        return shindigs;
    }
    public void setShindigs(ArrayList<Shindig> shindigs) {
        this.shindigs = shindigs;
    }

    public DatabaseReference pullDatabaseReference(){
        return database.getReference("Shindig");
    }

    public void pushShindig(Shindig shindig){
        DatabaseReference key  = database.getReference("Shindig").push();
        shindig.setKeyShin(key.getKey() + "DONOTUSE");
        Log.d("KeySet", "database key: " + shindig.getKeyShin() + "other item: " + shindig.getTitle());
        key.setValue(shindig);
    }

    public void pushShindigToCategory(Shindig shindig, String category){
        DatabaseReference key  = database.getReference(category).push();
        shindig.setKeyShin(key.getKey() + "DONOTUSE");
        Log.d("KeySet", "database key: " + shindig.getKeyShin() + "other item: " + shindig.getTitle());
        key.setValue(shindig);
    }

    public void pushUser(){

    }

    public void pullUser(){

    }
}
