package com.example.alena_adm.cafesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alena_Adm on 22.05.2018.
 */

public class AddActivity extends Activity {

    //dbase
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    SampleAPI sampleAPI;
    Context mContext;

    //camera
    private final int CAMERA_RESULT = 0;
    private ImageView mImageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    private static int TAKE_PICTURE = 1;
    private Uri mOutputFileUri;

    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

       // mImageView = findViewById(R.id.add_imageview);

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

        mContext = AddActivity.this;
        sampleAPI = SampleAPI.Factory.getIstance(mContext);
        sampleAPI.addCafe(name, note, address, rating).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),
                            "It should be added.", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Oooops.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
            }
        });


        Intent intObj = new Intent(this, MainActivity.class);
        startActivity(intObj);
    }


}