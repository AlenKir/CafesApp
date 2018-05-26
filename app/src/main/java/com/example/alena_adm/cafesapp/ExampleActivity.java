package com.example.alena_adm.cafesapp;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alena_Adm on 23.05.2018.
 */

public class ExampleActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/show")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    CafeService service = retrofit.create(CafeService.class);

    String res;

    Call<List<Cafe>> call;

    /*call.enqueue(new Callback<List<Cafe>>() {
        @Override
        public void onResponse(Call<List<Cafe>> call, Response<List<Cafe>> response) {
            String name, note, address;
            int rating;
            for (int i = 0; i < response.body().size(); i++) {
                name = response.body().get(i).getName();
                note = response.body().get(i).getNote();
                address = response.body().get(i).getAddress();
            }
            res = response.body().toString();
            Toast.makeText(getApplicationContext(),
                    "GET failed!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<List<Cafe>> call, Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "GET failed!", Toast.LENGTH_SHORT).show();
        }
    });
*/


    Button button;
    TextView textView;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_example);

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

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = "";

                /*Cursor cursor = mDb.rawQuery("SELECT * FROM cafes", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(1) + " " + cursor.getInt(0) + " | ";
                    cursor.moveToNext();
                }
                cursor.close();*/
                product = service.listRepos().toString();

                textView.setText(product);
            }
        });
    }
}
