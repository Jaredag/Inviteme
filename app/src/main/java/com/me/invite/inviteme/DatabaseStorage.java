package com.me.invite.inviteme;

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
        // Just to defeat instantiation
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

    public void pushShindig(Shindig shindig){
        database.getReference("Shindig").push().setValue(shindig);
    }

    public void pushUser(){

    }

    public void pullShindigs(){

    }

    public void pullUser(){

    }
}
