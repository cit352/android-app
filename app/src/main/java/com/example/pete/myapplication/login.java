package com.example.pete.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// For working with database
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import com.example.pete.myapplication.business.*;
import com.example.pete.myapplication.database.*;

public class login extends AppCompatActivity {

    public Button loginButton;
    DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBHandler db = new DBHandler(this);

        login();
        getSupportActionBar().setHomeButtonEnabled(true); //this makes back button go back to home
    }

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


                    if(isRegistered(usernameLoginEditText.getText().toString(),
                            passwordLoginEditText.getText().toString())) {
                        Intent tin = new Intent(login.this, unlockstart.class);
                        startActivity(tin);
                    }
                    else {
                        Context context = getApplicationContext();
                        CharSequence text = "No user with this username and password exists.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                }
            }
        });


    }

    private boolean isRegistered(String username, String password) {

        User user = dbHandler.getUser(username, password);
        dbHandler.getAllUsers();

        if(user != null) {
            return true;
        }
        else
            return false;
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



