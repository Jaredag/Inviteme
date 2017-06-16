package com.me.invite.inviteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateShindigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shindig);
    }

     public void createNewShindig(View view) {
        // Go to the create Event page
        Intent intent = new Intent(this, MainFeedActivity.class);
        startActivity(intent);
    }
}
