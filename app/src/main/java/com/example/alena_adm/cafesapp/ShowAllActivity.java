package com.example.alena_adm.cafesapp;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;

import java.util.function.ToIntBiFunction;

/**
 * Created by Alena_Adm on 22.05.2018.
 */

public class ShowAllActivity extends Activity implements View.OnTouchListener {

    String[] cafes = { "Cafe1", "Vegbox"};
    ListView showAll;
    float x;
    float y;

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

        showAll.setOnTouchListener(this);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " +
                        parent.getItemAtPosition(position).toString(),  Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();

        int id;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                id = v.getId();
                Toast.makeText(getApplicationContext(),
                        "You chose " + showAll.getItemAtPosition(id).toString(),
                         Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}