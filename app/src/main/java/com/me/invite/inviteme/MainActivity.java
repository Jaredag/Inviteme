package com.me.invite.inviteme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * MainActivity: Start the app.
 */


public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "com.me.invite.inviteme.MESSAGE";
    public static final String PREFS_NAME = "MyPrefs";
    public static final String Pref = "Event";
    LoginButton loginButton;
    CallbackManager callbackManager;


    @Override

    /**
     * onCreate: Creates a default item in Shared Preferences
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = (LoginButton) findViewById(R.id.fb_login_bn);

        // Pull the array of Shindigs created for the My Events page
        SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        ArrayList<Shindig> shindigList = new ArrayList<Shindig>();
        SharedPreferences.Editor editor = mPrefs.edit();
        String json = gson.toJson(shindigList);
        editor.putString(Pref, json);
        editor.commit();

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userName = loginResult.getAccessToken().getUserId();
                PartyAnimal host = new PartyAnimal(userName, "No Number");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    /**
     * ContinueAsGuest: Starts the Main Feed Activity
     * @param view
     */
    public void continueAsGuest(View view){
        // Go to the Main Feed
        Intent intent = new Intent(this, MainFeedActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
