package com.example.pete.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

<<<<<<< HEAD
// For working with database
import android.util.Log;
import java.util.List;
import com.example.pete.myapplication.business.*;
import com.example.pete.myapplication.database.*;

public class unlockstart extends AppCompatActivity {
    public Button unlock1;

    public void init(){
=======
public class unlockstart extends AppCompatActivity {
    public Button unlock1;

    public void init1(){
>>>>>>> my_changes
        unlock1 = (Button)findViewById(R.id.unlock1);
        unlock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

<<<<<<< HEAD
                Intent toy = new Intent (unlockstart.this, ScanFinger.class) ;
=======
                Intent toy = new Intent (unlockstart.this,swipefinger.class) ;
>>>>>>> my_changes
                startActivity(toy);
            }
        });
    }

    public Button Start1;

<<<<<<< HEAD
    public void initi(){
=======
    public void init2(){
>>>>>>> my_changes
        Start1 = (Button)findViewById(R.id.Start1);
        Start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

<<<<<<< HEAD
                Intent boat = new Intent (unlockstart.this, ScanFinger.class) ;
=======
                Intent boat = new Intent (unlockstart.this,swipefinger.class) ;
>>>>>>> my_changes
                startActivity(boat);
            }
        });
    }

<<<<<<< HEAD
=======
    public Button logout1;
    public void init3(){
        logout1 = (Button)findViewById(R.id.logout1);
        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent (unlockstart.this,MainActivity.class) ;
                startActivity(toy);
            }
        });
    }
>>>>>>> my_changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlockstart);
<<<<<<< HEAD
        init();
        initi();
=======
        init1();
        init2();
        init3();
>>>>>>> my_changes
    }
}
