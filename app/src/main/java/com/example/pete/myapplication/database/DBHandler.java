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
    private static final String TABLE_USER = "USER";

    // Table Columns names
    private static final String KEY_USER_ID = "USER_ID",
            KEY_FIRST_NAME = "USER_FIRST_NAME",
            KEY_LAST_NAME = "USER_LAST_NAME",
            KEY_USERNAME = "USER_USERNAME",
            KEY_PASSWORD = "USER_PASSWORD";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " (" +
                KEY_USER_ID + " INTEGER PRIMARY KEY, " +
                KEY_FIRST_NAME + " VARCHAR, " +
                KEY_LAST_NAME + " VARCHAR, " +
                KEY_USERNAME + " VARCHAR, " +
                KEY_PASSWORD + "VARCHAR" + ")";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Creating tables again
        onCreate(db);
    }
    // Methods to Read, Insert, Update and Delete data in database
}