package com.example.alena_adm.cafesapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    AutoCompleteTextView tw;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void onClickLogin(View view) {
        tw = findViewById(R.id.email);
        String login = tw.getText().toString();
        password = findViewById(R.id.password);
        String pass = password.getText().toString();

        if (login.equals(""))
        {
            Intent intObj = new Intent(this, MainActivity.class);
            startActivity(intObj);
        }
        if (login.equals("admin@gmail.com"))
        {
            if (pass.equals("123456"))
            {
                Intent intObj = new Intent(this, MainActivity.class);
                startActivity(intObj);
            }
            else
                Toast.makeText(getApplicationContext(),
                        "Wrong password, try again", Toast.LENGTH_SHORT).show();
        }
    }
}

