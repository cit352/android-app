package com.example.pete.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class swipefinger extends AppCompatActivity {

    public Button logout1;
    public void init(){
        logout1 = (Button)findViewById(R.id.logout1);
        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent (swipefinger.this,MainActivity.class) ;
                startActivity(toy);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipefinger);
        init();
    }
}
