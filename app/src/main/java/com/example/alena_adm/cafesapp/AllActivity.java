package com.example.alena_adm.cafesapp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alena_Adm on 23.05.2018.
 */

public class AllActivity extends ListActivity {

    ArrayList<String> cafesNames = new ArrayList<String>();

    private ArrayAdapter<String> mAdapter;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        Cursor cursor = mDb.rawQuery("SELECT * FROM cafes", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cafesNames.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

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
        intObj.putExtra("id", position + 1);
        startActivity(intObj);
    }
}
