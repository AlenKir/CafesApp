package com.example.alena_adm.cafesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExampleActivity extends AppCompatActivity {
    TextView textView;
    SampleAPI sampleAPI;
    Context mContext;
    TextView result;
    String name;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);
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

    public void onClickGet(View view) {

        EditText editText = (EditText) findViewById(R.id.get_clicked);
        editText.setText("Button Get Clicked.");

        mContext = ExampleActivity.this;
        sampleAPI = SampleAPI.Factory.getIstance(mContext);
        textView = (TextView) findViewById(R.id.get_textview);
        sampleAPI.getCafes().enqueue(new Callback<List<Cafe>>() {
            @Override
            public void onResponse(Call<List<Cafe>> call, Response<List<Cafe>> response) {
                EditText editText2 = (EditText) findViewById(R.id.get_on);
                editText2.setText("on response");
                if (response.isSuccessful())
                {
                    EditText editText3 = (EditText) findViewById(R.id.get_success);
                    editText3.setText("inside of is success");
                    List<Cafe> cafeList = response.body();
                    /*Toast.makeText(getApplicationContext(),
                            "It should be " + response.body().toString(), Toast.LENGTH_SHORT).show();*/
                    textView.setText(cafeList.toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Oooops, GET.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cafe>> call, Throwable t) {
                EditText editText2 = (EditText) findViewById(R.id.get_on);
                editText2.setText("on failure");
            }
        });
    }

    public void onClickUpdate(View view) {
        EditText editText = (EditText) findViewById(R.id.get_clicked);
        editText.setText("Button Update Clicked.");
        mContext = ExampleActivity.this;
        sampleAPI = SampleAPI.Factory.getIstance(mContext);
        textView = (TextView) findViewById(R.id.update_textview);
        sampleAPI.getUpdate().enqueue(new Callback<List<Cafe>>() {
            @Override
            public void onResponse(Call<List<Cafe>> call, Response<List<Cafe>> response) {
                EditText editText2 = (EditText) findViewById(R.id.get_on);
                editText2.setText("on response update");
                if (response.isSuccessful()) {
                    EditText editText3 = (EditText) findViewById(R.id.get_success);
                    editText3.setText("inside of update success");
                    List<Cafe> cafeList = response.body();
                    if (!cafeList.isEmpty()) {
                        String name = response.body().get(0).getName();
                        String note = response.body().get(0).getNote();
                        String address = response.body().get(0).getAddress();
                        int rating = response.body().get(0).getRating();
                    /*Toast.makeText(getApplicationContext(),
                            "It should be " + response.body().toString(), Toast.LENGTH_SHORT).show();*/
                        String q = "INSERT INTO TABLE_NAME [(column1, column2, column3,...columnN)]  \n" +
                                "VALUES (value1, value2, value3,...valueN);";
                        q = "insert into cafes (name, rating, note, address) values " +
                                "('" + name + "', " + rating + ", '" + note + "', '" + address + "');";

                        try {
                            Cursor cursor = mDb.rawQuery(q, null);
                            mDb.execSQL(q);
                            sampleAPI.update().enqueue(new Callback<List<Cafe>>() {
                                @Override
                                public void onResponse(Call<List<Cafe>> call, Response<List<Cafe>> response) {

                                    if (response.isSuccessful()) {
                                    } else {
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Cafe>> call, Throwable t) {
                                    EditText editText2 = (EditText) findViewById(R.id.get_on);
                                    editText2.setText("on failure");
                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    "Sorry, something went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    textView.setText(cafeList.toString());
                }
                        else {
                        Toast.makeText(getApplicationContext(),
                                "Oooops, GET.", Toast.LENGTH_SHORT).show();
                    }
                }

            @Override
            public void onFailure(Call<List<Cafe>> call, Throwable t) {
                EditText editText2 = (EditText) findViewById(R.id.get_on);
                editText2.setText("on failure");
            }
        });
    }
}