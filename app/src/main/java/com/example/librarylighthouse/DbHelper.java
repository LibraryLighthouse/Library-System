package com.example.librarylighthouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "LDB";
    private static final int DB_VERSION = 1;
    private static final String USER_TABLE = "user";
    private static final String BOOK_TABLE = "book";

    // User table constants
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirm_password";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + EMAIL + " TEXT, "
                + PASSWORD + " TEXT, "
                + CONFIRM_PASSWORD + " TEXT"
                + ")";
        db.execSQL(createUserTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE);
        onCreate(db);
    }

    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        boolean isLoggedIn = false;

        try {
            String selectQuery = "SELECT * FROM " + USER_TABLE + " WHERE " + EMAIL + " = ? AND " + PASSWORD + " = ?";
            cursor = db.rawQuery(selectQuery, new String[]{email, password});
            if (cursor != null && cursor.getCount() > 0) {
                isLoggedIn = true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return isLoggedIn;
    }

    public void registerUser(String name, String email, String password, String confirmPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);
        values.put(CONFIRM_PASSWORD, confirmPassword);
        db.insert(USER_TABLE, null, values);
        db.close();
    }

}
