package com.example.pete.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// For working with database
import android.util.Log;
import java.util.List;
import com.example.pete.myapplication.business.*;
import com.example.pete.myapplication.database.*;

public class ScanFinger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanfinger);
    }
}
