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

    private Button signUpButton;
    private Button signInButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpPage();
        signInPage();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void signUpPage() {
        signUpButton = (Button) findViewById(R.id.sign_up_tap);


        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DBHandler db = new DBHandler(this);
                EditText usernameSignUpEditText = null;
                EditText passwordSignUpEditText = null;
                boolean usernameEnteredForSignUp = false;
                boolean passwordEnteredForSignUp = false;

                usernameSignUpEditText = (EditText) findViewById(R.id.email_input);
                passwordSignUpEditText = (EditText) findViewById(R.id.pass_input);
                if (isEmpty(usernameSignUpEditText)) {
                    usernameSignUpEditText.setError("You must enter a username");
                }
                else
                    usernameEnteredForSignUp = true;

                if (isEmpty(passwordSignUpEditText)) {
                    passwordSignUpEditText.setError("You must enter a password");
                }
                else
                    passwordEnteredForSignUp = true;

                if(usernameEnteredForSignUp && passwordEnteredForSignUp) {
                    Intent page = new Intent(MainActivity.this, login.class);
                    //db.addUser(usernameSignUpEditText, passwordSignUpEditText);


                    startActivity(page);
                }
            }
        });

    }

    public void signInPage() {
        signInButton = (Button) findViewById(R.id.sign_in_tap);

        signInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent page = new Intent(MainActivity.this, login.class);
                startActivity(page);
            }
        });
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
