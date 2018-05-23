package com.example.alena_adm.cafesapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Alena_Adm on 23.05.2018.
 */

public class EditActivity extends Activity {

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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

        int cafe_id = 0;

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            cafe_id = extras.getInt("id");
        }

        String name = "";
        String note = "";
        Integer rating = 0;
        String address = "";

        EditText e_name = (EditText) findViewById(R.id.edit_name);
        EditText e_note = (EditText) findViewById(R.id.edit_note);
        RatingBar e_rating = (RatingBar) findViewById(R.id.edit_rating);
        EditText e_address = (EditText) findViewById(R.id.edit_address);

        String q = "select * from " + "cafes" + " where " +
                "_id" + "=?";

        float r = 0;

        if (cafe_id > 0) {
            // получаем элемент по id из бд
            cursor = mDb.rawQuery(q, new String[]{String.valueOf(cafe_id)});
            cursor.moveToFirst();
            e_name.setText(cursor.getString(1));
            e_rating.setRating(cursor.getInt(2));
            e_note.setText(cursor.getString(3));
            e_address.setText(cursor.getString(5));
            cursor.close();
        }
    }

    public void onClickSave(View v)
    {
        EditText e_name = (EditText) findViewById(R.id.edit_name);
        EditText e_note = (EditText) findViewById(R.id.edit_note);
        RatingBar e_rating = (RatingBar) findViewById(R.id.edit_rating);
        EditText e_address = (EditText) findViewById(R.id.edit_address);

        String name = e_name.getText().toString();
        String note = e_note.getText().toString();
        String address = e_address.getText().toString();
        Integer rating = Math.round(e_rating.getRating());

        int cafe_id = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cafe_id = extras.getInt("id");
        }
        String q = "UPDATE table_name +" +
                "SET column1 = value1, column2 = value2...., columnN = valueN +" +
                "WHERE [condition];";
        q = "update cafes set name = '" + name +"', note = '"
                + note + "', address = '" + address + "'" +
                ", rating = " + rating + " where _id=" + cafe_id + ";";

        try {
            cursor = mDb.rawQuery(q, null);
            mDb.execSQL(q);
            Toast.makeText(getApplicationContext(),
                    "You edited " + name, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),
                    "Sorry, something went wrong.", Toast.LENGTH_SHORT).show();
        }

        Intent intObj = new Intent(this, MainActivity.class);
        startActivity(intObj);
    }

    public void onClickDelete(View view) {
        int cafe_id = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cafe_id = extras.getInt("id");
        }
        String q = "DELETE FROM table_name\n" +
                "WHERE [condition];";
        q = "delete from cafes where _id=" + cafe_id + ";";
        try {
            //cursor = mDb.rawQuery(q, null);
            //mDb.execSQL(q);
            mDb.delete("cafes", "_id = ?", new String[]{String.valueOf(cafe_id)});
            Toast.makeText(getApplicationContext(),
                    "Deleted.", Toast.LENGTH_SHORT).show();
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