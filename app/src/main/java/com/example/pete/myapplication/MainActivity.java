package com.example.pete.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

// For working with database
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import com.example.pete.myapplication.business.*;
import com.example.pete.myapplication.database.*;

import static com.example.pete.myapplication.R.id.email_input;

public class MainActivity extends AppCompatActivity {

    public Button signUp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void signinpage() {
        signUp = (Button) findViewById(R.id.sign_up_tap);

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText usernameEditText = null;
                EditText passwordEditText = null;
                boolean usernameEntered = false;
                boolean passwordEntered = false;

                usernameEditText = (EditText) findViewById(R.id.email_input);
                passwordEditText = (EditText) findViewById(R.id.pass_input);
                if (isEmpty(usernameEditText)) {
                    usernameEditText.setError("You must enter a username");
                }
                else
                    usernameEntered = true;

                if (isEmpty(passwordEditText)) {
                    passwordEditText.setError("You must enter a password");
                }
                else
                    passwordEntered = true;

                if(usernameEntered && passwordEntered) {
                    Intent page = new Intent(MainActivity.this, login.class);
                    startActivity(page);
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSignUp = (Button) findViewById(R.id.sign_up_tap);
        signinpage();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
