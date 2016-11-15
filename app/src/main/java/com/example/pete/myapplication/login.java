package com.example.pete.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// For working with database
import android.util.Log;
import android.widget.EditText;

import java.util.List;
import com.example.pete.myapplication.business.*;
import com.example.pete.myapplication.database.*;

public class login extends AppCompatActivity {

    public Button loginButton;

    public void login(){
        loginButton = (Button)findViewById(R.id.enter_app);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameLoginEditText = null;
                EditText passwordLoginEditText = null;
                boolean usernameEnteredForLogin = false;
                boolean passwordEnteredForLogin = false;

                usernameLoginEditText = (EditText) findViewById(R.id.login_username);
                passwordLoginEditText = (EditText) findViewById(R.id.login_password);
                if (isEmpty(usernameLoginEditText)) {
                    usernameLoginEditText.setError("You must enter a username");
                }
                else
                    usernameEnteredForLogin = true;

                if (isEmpty(passwordLoginEditText)) {
                    passwordLoginEditText.setError("You must enter a password");
                }
                else
                    passwordEnteredForLogin = true;

                if(usernameEnteredForLogin && passwordEnteredForLogin) {
                    Intent tin = new Intent(login.this, unlockstart.class);
                    startActivity(tin);
                }
                //DBHandler db = new DBHandler();
                //db.onCreate();
                //db.getUserInfo()';
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login();
        getSupportActionBar().setHomeButtonEnabled(true); //this makes back button go back to home
    }


    public boolean isEmpty(EditText et)
    {
        String s = et.getText().toString();

        if (s.matches(""))
        {
            return true;
        }
        return false;
    }
}



