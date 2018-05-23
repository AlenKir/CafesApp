package com.example.alena_adm.cafesapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Alena_Adm on 22.05.2018.
 */

public class ShowAllActivity extends AppCompatActivity {

    String[] cafes = { "Cafe1", "Vegbox"};
    ListView showAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_showall);

        showAll = findViewById(R.id.list);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, cafes);
        //setListAdapter(adapter);
        showAll.setAdapter(adapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " +
                        parent.getItemAtPosition(position).toString(),  Toast.LENGTH_SHORT).show();
            }
        };
    }
}