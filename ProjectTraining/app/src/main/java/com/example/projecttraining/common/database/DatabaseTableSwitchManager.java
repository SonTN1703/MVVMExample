package com.example.projecttraining.common.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.projecttraining.models.SwitchModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTableSwitchManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DatabaseTableSwitchManager(Context context) {
        this.context = context;
    }

    public DatabaseTableSwitchManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertSwitch(SwitchModel switchModel) {
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.SWITCH_NAME, switchModel.getName());
        cv.put(dbHelper.SWITCH_ICON, switchModel.getIcon());
        cv.put(dbHelper.SWITCH_LINK, switchModel.getLink());
        cv.put(dbHelper.CATEGORY, switchModel.getCategory());
        cv.put(dbHelper.REQUIRE_DESKTOP, switchModel.getRequireDesktop());
        if (switchModel.isChecked()) {
            cv.put(dbHelper.CHECKED, 1);
        } else {
            cv.put(dbHelper.CHECKED, 0);
        }

        long result = db.insert(dbHelper.TABLE_SWITCH, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void updateSwitch(SwitchModel switchModel) {
        db = dbHelper.getWritableDatabase();
        if (switchModel.isChecked()) {
            db.execSQL("UPDATE " + dbHelper.TABLE_SWITCH + " SET " + dbHelper.CHECKED + " = ? WHERE " + dbHelper.SWITCH_ID + " =?",
                    new String[]{1 + "", switchModel.getId() + ""});
        } else {
            db.execSQL("UPDATE " + dbHelper.TABLE_SWITCH + " SET " + dbHelper.CHECKED + " = ? WHERE " + dbHelper.SWITCH_ID + " =? ",
                    new String[]{0 + "", switchModel.getId() + ""});
        }
    }

    public List<SwitchModel> getAllSwitch() {
        List<SwitchModel> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_SWITCH + " ORDER BY " + dbHelper.CHECKED +" DESC", null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                SwitchModel switchModel = new SwitchModel();
                switchModel.setId(cursor.getInt(0));
                switchModel.setName(cursor.getString(1));
                switchModel.setLink(cursor.getString(3));
                switchModel.setIcon(cursor.getString(2));
                switchModel.setRequireDesktop(cursor.getString(4));
                switchModel.setCategory(cursor.getString(5));
                if (cursor.getInt(6) == 1) {
                    switchModel.setChecked(true);
                } else {
                    switchModel.setChecked(false);
                }
                list.add(switchModel);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
