package com.example.projecttraining.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BookRoom";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_USER = "user";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_SDT = "sdt";
    public static final String USER_PASSWORD = "password";
    public static final String TABLE_SWITCH = "switch";
    public static final String SWITCH_ID = "id";
    public static final String SWITCH_NAME = "name";
    public static final String SWITCH_ICON = "icon";
    public static final String SWITCH_LINK = "link";
    public static final String REQUIRE_DESKTOP = "require_desktop";
    public static final String CATEGORY = "category";
    public static final String CHECKED = "checked";
    private Context context;

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
            + TABLE_USER + " ( " + USER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + " TEXT ,"
            + USER_EMAIL + " TEXT ,"
            + USER_SDT + " TEXT ,"
            + USER_PASSWORD + " TEXT )";
    private static final String CREATE_TABLE_SWITCH = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SWITCH + " ( " + SWITCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SWITCH_NAME + " TEXT ,"
            + SWITCH_ICON + " TEXT ,"
            + SWITCH_LINK + " TEXT ,"
            + REQUIRE_DESKTOP + " TEXT,"
            + CATEGORY + " TEXT ,"
            + CHECKED + " INTEGER )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_SWITCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
