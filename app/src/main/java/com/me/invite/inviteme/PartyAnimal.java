package com.me.invite.inviteme;

import java.util.ArrayList;

/**
 * Created by Jared on 6/12/2017.
 */

public class PartyAnimal {

    private String userName;
    private String phoneNum;
    private ArrayList<Shindig> shindigs;

    public PartyAnimal(String userName, String phoneNum) {
        this.userName = userName;
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public ArrayList<Shindig> getShindigs() {
        return shindigs;
    }

    public void setShindigs(ArrayList<Shindig> shindigs) {
        this.shindigs = shindigs;
    }
}
