package com.example.pete.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// For working with database
import android.util.Log;
import java.util.List;
import com.example.pete.myapplication.business.*;
import com.example.pete.myapplication.database.*;

public class login extends AppCompatActivity {

    public Button enter_app;

    public void init(){
        enter_app = (Button)findViewById(R.id.enter_app);
        enter_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent tin = new Intent (login.this, unlockstart.class) ;
                startActivity(tin);
                DBHandler db = new DBHanlder();
                db.onCreate();
                db.getUserInfo

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        getSupportActionBar().setHomeButtonEnabled(true); //this makes back button go back to home
    }
}



