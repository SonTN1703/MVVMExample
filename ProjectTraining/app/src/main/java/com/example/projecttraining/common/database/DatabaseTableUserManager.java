package com.example.projecttraining.common.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.projecttraining.models.SwitchModel;
import com.example.projecttraining.models.User;

import java.util.List;

public class DatabaseTableUserManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DatabaseTableUserManager(Context context) {
        this.context = context;
    }

    public DatabaseTableUserManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public boolean searchUserByEmail(User user) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_USER + " WHERE " + dbHelper.USER_EMAIL + " like ?",
                new String[]{user.getEmail()});
        int count = cursor.getCount();
        if (cursor.getCount() > 0) {
            return false;
        } else return true;
    }

    public boolean searchUserByName(User user) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_USER + " WHERE " + dbHelper.USER_NAME + " like ?",
                new String[]{user.getUserName()});
        if (cursor.getCount() > 0) {
            return false;
        } else return true;
    }

    public User searchUser(User user) {
        User user1 = null;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_USER + " WHERE " + dbHelper.USER_NAME + " = ? AND " + dbHelper.USER_PASSWORD + " = ?",
                new String[]{user.getUserName(), user.getPassword()});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String sdt = cursor.getString(3);
            String password = cursor.getString(4);
            user1 = new User(id,email,sdt,name,password);
        }
        cursor.close();
        return user1;
    }

    public boolean insertUser(User user) {
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.USER_NAME, user.getUserName());
        cv.put(dbHelper.USER_EMAIL, user.getEmail());
        cv.put(dbHelper.USER_SDT, user.getSdt());
        cv.put(dbHelper.USER_PASSWORD, user.getPassword());
        long result = db.insert(dbHelper.TABLE_USER, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
