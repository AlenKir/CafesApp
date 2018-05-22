package com.example.alena_adm.cafesapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Alena_Adm on 22.05.2018.
 */

public class ShowAllActivity extends Activity {

    String[] cafes = { "Cafe1", "Vegbox"};

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showall);

        // находим список
        ListView list = (ListView) findViewById(R.id.showall_list);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, cafes);

        // присваиваем адаптер списку
        list.setAdapter(adapter);

    }
}