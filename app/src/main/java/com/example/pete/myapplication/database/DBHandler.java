package com.example.pete.myapplication.database;

/**
 * Created by theNextThing on 10/24/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android-app";

    // Table name
    private static final String TABLE_USER = "USER",
                                TABLE_FINGERPRINT = "FINGERPRINT";

    // Table Columns names
    private static final String KEY_USER_ID = "USER_ID",
                    KEY_FIRST_NAME = "USER_FIRST_NAME",
                    KEY_LAST_NAME = "USER_LAST_NAME",
                    KEY_EMAIL_NAME = "USER_EMAIL_NAME",
                    KEY_PASSWORD = "USER_PASSWORD";

    private static final String KEY_FINGERPRINT_ID = "FINGERPRINT_ID",
                                KEY_FINGERPRINT = "FINGERPRINT";



    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " (" +
                        KEY_USER_ID + " INTEGER PRIMARY KEY, " +
                        KEY_FIRST_NAME + " VARCHAR, " +
                        KEY_LAST_NAME + " VARCHAR, " +
                        KEY_EMAIL_NAME + " VARCHAR, " +
                        KEY_PASSWORD + "VARCHAR" + ")";

        final String CREATE_FINGERPRINT_TABLE = "CREATE TABLE " + TABLE_FINGERPRINT + " (" +
                KEY_FINGERPRINT_ID + " INTEGER PRIMARY KEY, " +
                KEY_USER_ID + " INTEGER, " +
                KEY_FINGERPRINT + " VARCHAR, " +
                " FOREIGN KEY(" + KEY_USER_ID + ")" +
                " REFERENCES "  + TABLE_USER + "(" +
                KEY_USER_ID + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FINGERPRINT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FINGERPRINT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    // Creating tables again
        onCreate(db);
    }

    // Methods to Read, Insert, Update and Delete data in database
}
