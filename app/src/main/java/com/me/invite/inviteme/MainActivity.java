package com.me.invite.inviteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "com.me.invite.inviteme.MESSAGE";
    // Comments gone
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Continue as Guest to the Main feed
    public void continueAsGuest(View view){
        // Go to the Main Feed
        Intent intent = new Intent(this, MainFeedActivity.class);
        startActivity(intent);
    }

}
