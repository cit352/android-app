package com.example.pete.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class unlockstart extends AppCompatActivity {
    public Button unlock1;

    public void init1(){
        unlock1 = (Button)findViewById(R.id.unlock1);
        unlock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent (unlockstart.this,swipefinger.class) ;
                startActivity(toy);
            }
        });
    }

    public Button Start1;

    public void init2(){
        Start1 = (Button)findViewById(R.id.Start1);
        Start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent boat = new Intent (unlockstart.this,swipefinger.class) ;
                startActivity(boat);
            }
        });
    }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlockstart);
        init1();
        init2();
        init3();
    }
}
