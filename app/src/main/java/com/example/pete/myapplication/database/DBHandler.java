package com.example.pete.myapplication.database;


/**
 * Created by theNextThing on 10/24/2016.
 */

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.view.View;

        import com.example.pete.myapplication.business.User;

        import java.util.ArrayList;
        import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android-app-db";

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
                KEY_PASSWORD + " VARCHAR " + ")";

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
    public void addUser(User user) {

      /*  SQLiteDatabase db = this.getReadableDatabase();
        Cursor dbCursor = db.query(TABLE_USER, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();

        for(int i = 0; i < columnNames.length; i++) {
            System.out.println(columnNames[i]);
        }*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, user.getFirstName());
        values.put(KEY_LAST_NAME, user.getLastName());
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());

       // Inserting Row
        db.insert(TABLE_USER, null, values);
        System.out.println("New user added to database" + user.getFirstName() + " "  +
                user.getLastName() + " " + user.getUsername());



        db.close(); // Closing database connection
    }

    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_USER_ID,
                        KEY_FIRST_NAME, KEY_LAST_NAME, KEY_USERNAME, KEY_PASSWORD }, KEY_USERNAME + "=?",
                new String[] { String.valueOf(username) }, null, null, null, null);

        User user = null;
        try {
            if (cursor != null) {
                cursor.moveToNext();
                user = new User(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4));
            }
        } catch (Exception e) {
              return user;
        }
        // return User
        return user;
    }

    public User getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_USER_ID,
                        KEY_FIRST_NAME, KEY_LAST_NAME, KEY_USERNAME, KEY_PASSWORD }, KEY_USERNAME + "=?" +
                " AND " + KEY_PASSWORD + "=?",
                new String[] { String.valueOf(username), String.valueOf(password) }, null, null, null, null);

        User user = null;

        try {
            if (cursor != null) {
                cursor.moveToNext();
                user = new User(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4));
            }
        } catch (Exception e) {
            return user;
        }
        // return User
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();

        String selectQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserId(Integer.parseInt(cursor.getString(0)));
                user.setFirstName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
                user.setUsername(cursor.getString(3));
                user.setPassword(cursor.getString(4));

                System.out.println("The following users are in the database: ");
                System.out.println("ID: " + user.getUserId() +
                                    " first name: " + user.getFirstName() +
                                    " last name: " + user.getLastName() +
                                    " username: " + user.getUsername() +
                                    " password: " + user.getPassword());

                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }
}