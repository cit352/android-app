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

public class unlockstart extends AppCompatActivity {
    public Button unlock1;
    private Button logoutButton;

    public void logout() {
        logoutButton = (Button) findViewById(R.id.logout_tap);
        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent page = new Intent(unlockstart.this, MainActivity.class);
                startActivity(page);
            }
        });
    }

    public void init(){
        unlock1 = (Button)findViewById(R.id.unlock1);
        unlock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent (unlockstart.this, ScanFinger.class) ;
                startActivity(toy);
            }
        });
    }

    public Button Start1;

    public void initi(){
        Start1 = (Button)findViewById(R.id.Start1);
        Start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent boat = new Intent (unlockstart.this, ScanFinger.class) ;
                startActivity(boat);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlockstart);

        init();
        initi();
        logout();
    }
}
