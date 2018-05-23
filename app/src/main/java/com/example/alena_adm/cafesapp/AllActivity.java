package com.example.alena_adm.cafesapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Alena_Adm on 23.05.2018.
 */



public class AllActivity extends ListActivity {

    final String[] cafesNames = new String[] { "VeggieBox", "BerryBlin", "Укроп" };

    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cafesNames);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //номер пункта
       // Toast.makeText(getApplicationContext(),
              //  "Вы выбрали " + (position + 1) + " элемент", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),
                "You chose " + l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        Intent intObj = new Intent(this, EditActivity.class);
        startActivity(intObj);
    }
}
