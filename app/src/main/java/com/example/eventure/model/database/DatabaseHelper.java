package com.example.eventure.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "EVENTURE_DATABASE.db";

    public final static int DATABASE_VERSION= 1;

//    explorers table
    public final static String TABLE_EXPLORER = "explorer";
    public final static String COLUMN_EXPLORER_USERNAME= "username";
    public final static String COLUMN_EXPLORER_EMAIL = "email";
    public final static String COLUMN_EXPLORER_PASSWORD = "password";
    public final static String COLUMN_GENDER= "gender";
    public final static String COLUMN_BIRTHDATE= "birthdate";

    public static final String CREATE_EXPLORER_TABLE = "CREATE TABLE " + TABLE_EXPLORER + "("
            + COLUMN_EXPLORER_USERNAME + "TEXT PRIMARY KEY, "
            + COLUMN_EXPLORER_EMAIL + "TEXT, "
            + COLUMN_EXPLORER_PASSWORD + "TEXT, "
            + COLUMN_GENDER + "TEXT, "
            + COLUMN_BIRTHDATE + "TEXT" + ")";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EXPLORER_TABLE);
    }
    public void registerExplorer(String username, String email, String password, String gender, String birthdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_EXPLORER_USERNAME, username);
        contentValues.put(COLUMN_EXPLORER_EMAIL, email);
        contentValues.put(COLUMN_EXPLORER_PASSWORD, password);
        contentValues.put(COLUMN_GENDER, gender);
        contentValues.put(COLUMN_BIRTHDATE,birthdate);
        db.insert(TABLE_EXPLORER, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPLORER);
        onCreate(sqLiteDatabase);
    }

}
