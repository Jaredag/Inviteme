package com.me.invite.inviteme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
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
    //LoginButton loginButton;
    //CallbackManager callbackManager;
    //AccessTokenTracker accessTokenTracker;
    //ProfileTracker profileTracker;


    @Override

    /**
     * onCreate: Creates a default item in Shared Preferences
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FacebookSdk.sdkInitialize(getApplicationContext());
        //loginButton = (LoginButton) findViewById(R.id.fb_login_bn);

        // Pull the array of Shindigs created for the My Events page
        SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String objNull = mPrefs.getString(Pref, null);
        if (objNull == null) {
            Gson gson = new Gson();
            ArrayList<Shindig> shindigList = new ArrayList<Shindig>();
            SharedPreferences.Editor editor = mPrefs.edit();
            String json = gson.toJson(shindigList);
            editor.putString(Pref, json);
            editor.commit();
        }

        //String jsonUser = fb.request
        /*callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker(){
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken currentToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };


        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //String userName = loginResult.getAccessToken().getUserId();
                //PartyAnimal host = new PartyAnimal(userName, "No Number");
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }

        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);*/
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


    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //callbackManager.onActivityResult(requestCode, resultCode, data);
    //}

    /*private void nextActivity(Profile profile){
        if(profile != null){
            Intent main = new Intent(MainActivity.this, UserProfile.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
            main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
            startActivity(main);
        }
    }*/
}
