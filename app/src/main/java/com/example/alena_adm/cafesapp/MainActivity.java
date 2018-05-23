package com.example.alena_adm.cafesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.show_favorite)
        {
            Intent intObj = new Intent(this, FavoriteActivity.class);
            startActivity(intObj);
        }
        else if (id == R.id.show_all_item)
        {
            Intent intObj = new Intent(this, AllActivity.class);
            startActivity(intObj);
        }

        return true;
    }

   public void onClickToMaps(View v) {
        Intent intObj = new Intent(this, MapsActivity.class);
        startActivity(intObj);
    }

    public void onClickToAdd(View v) {
        Intent intObj = new Intent(this, AddActivity.class);
        startActivity(intObj);
    }

    public void onClickToEdit(View v) {
        Intent intObj = new Intent(this, EditActivity.class);
        startActivity(intObj);
    }
}
