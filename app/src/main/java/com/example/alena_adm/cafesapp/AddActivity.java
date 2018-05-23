package com.example.alena_adm.cafesapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.drm.DrmStore;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Alena_Adm on 22.05.2018.
 */

public class AddActivity extends Activity {

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

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
    }

    public void onClickAdd(View v) {
        TextView view = (TextView) findViewById(R.id.add_name);
        String name = view.getText().toString();
        if (name.equals(""))
            return;
        RatingBar r = (RatingBar) findViewById(R.id.add_rating);
        Integer rating = Math.round(r.getRating());
        view = (TextView) findViewById(R.id.add_note);
        String note = view.getText().toString();
        view = (TextView) findViewById(R.id.add_address);
        String address = view.getText().toString();

        String q = "INSERT INTO TABLE_NAME [(column1, column2, column3,...columnN)]  \n" +
                "VALUES (value1, value2, value3,...valueN);";
        q = "insert into cafes (name, rating, note, address) values " +
                "('" + name + "', " + rating + ", '" + note + "', '" + address + "');";

        try {
            Cursor cursor = mDb.rawQuery(q, null);
            mDb.execSQL(q);
            Toast.makeText(getApplicationContext(),
                    "You added " + name, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),
                    "Sorry, something went wrong.", Toast.LENGTH_SHORT).show();
        }

        Intent intObj = new Intent(this, MainActivity.class);
        startActivity(intObj);
    }
}