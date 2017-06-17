package com.me.invite.inviteme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyShindigs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shindigs);

        // ListView 
        ListView mListView = (ListView) findViewById(R.id.listView2);
        /*
        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);

        String[] listItems = new String[recipeList.size()];

        for(int i = 0; i < recipeList.size(); i++){
            Recipe recipe = recipeList.get(i);
            listItems[i] = recipe.title;
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
        */
    }
}
