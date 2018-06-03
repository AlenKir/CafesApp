package com.example.alena_adm.cafesapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);
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
                if (response.isSuccessful())
                {
                    EditText editText3 = (EditText) findViewById(R.id.get_success);
                    editText3.setText("inside of update success");
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
}