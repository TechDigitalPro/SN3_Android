package com.rke.sn3.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SN3DB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "sn3.db";
    private Context mContext;

    private final static String TABLE_PREFS = "prefs";


    public SN3DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        checkDatabaseVersion();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase(db);
    }
    private void createDatabase(SQLiteDatabase db) {

        createPrefsTable(db);

    }
    public void checkDatabaseVersion() {
        SQLiteDatabase db = this.getWritableDatabase();

        // if the DATABASE_VERSION is newer
        //    onUpgrade is called before this is reached
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREFS);
        createDatabase(db);
    }
    public boolean clearAllRecords() {

        return true;

    }









    //region ## PREF Operations ##
    public boolean clearPrefsTable() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from " + TABLE_PREFS);
        db.execSQL("delete from sqlite_sequence where name='" + TABLE_PREFS + "'");
        return true;
    }
    private void createPrefsTable(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_PREFS + " (" +
                        "prefNo INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "pref TEXT," +
                        "val TEXT DEFAULT '')";
        db.execSQL(query);
    }
    public long insertPref(String pref, String val) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String query = "INSERT OR REPLACE INTO " + TABLE_PREFS +
                "(prefNo, pref, val) values " +
                "((select prefNo from " + TABLE_PREFS + " where pref = ?), ?, ?);";

        db.execSQL(query, new Object[] { pref, pref, val});
        return 0;
    }
    public Map<String, String> listPrefs() {
        Map<String, String> prefs = new HashMap<>();
        Cursor cursor;
        String pref, val;

        SQLiteDatabase db = this.getReadableDatabase();
        if (db == null) return null;

        cursor = db.query(TABLE_PREFS, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                pref = cursor.getString(cursor.getColumnIndex("pref"));
                val = cursor.getString(cursor.getColumnIndex("val"));

                prefs.put(pref, val);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return prefs;
    }
    //endregion


}
