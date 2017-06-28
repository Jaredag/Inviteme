package com.me.invite.inviteme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class contains the list of the variables that will be available
 * for user input in the CreateEvent activity.
 *
 * numSpots = number of people that can RSVP to an event
 * partyAnimals = the people that RSVP
 */

public class Shindig {

    private String title;
    private String description;
    private String location;
    private String date;
    private int numSpots;
    PartyAnimal host;
    private ArrayList<PartyAnimal> partyAnimals;

    public Shindig(String title, String description, String location, String date, int numSpots, PartyAnimal host) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.numSpots = numSpots;
        this.host = host;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumSpots() {
        return numSpots;
    }

    public void setNumSpots(int numSpots) {
        this.numSpots = numSpots;
    }

    public PartyAnimal getHost() {
        return host;
    }

    public void setHost(PartyAnimal host) {
        this.host = host;
    }

    public ArrayList<PartyAnimal> getPartyAnimals() {
        return partyAnimals;
    }

    public void setPartyAnimals(ArrayList<PartyAnimal> partyAnimals) {
        this.partyAnimals = partyAnimals;
    }
}
