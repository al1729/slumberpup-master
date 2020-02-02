package com.example.sleephealthdog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SleepNightDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static SleepNightDbHelper sInstance;

    private static final String TABLE_NAME = "sleepnights_table";
    private static final int DATABASE_VERSION = 1;

    //private static final String COL0 = "id";
    private static final String COL1 = "day";
    private static final String COL2 = "sleep";
    private static final String COL3 = "light";
    private static final String COL4 = "deep";
    private static final String COL5 = "REM";
    private static final String COL6 = "awake";
    private static final String COL7 = "score";
    private static final String COL8 = "efficiency";


    public SleepNightDbHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " + COL2 + " INTEGER, " + COL3 + " INTEGER, " + COL4 + " INTEGER, " + COL5 + " INTEGER, " + COL6 + " INTEGER, " + COL7 + " INTEGER, " + COL8 +  "INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String day, long sleep, long light, long deep, long REM, long awake, double score, double efficiency) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1, day.toString());
        contentValues.put(COL2, sleep);
        contentValues.put(COL3, light);
        contentValues.put(COL4, deep);
        contentValues.put(COL5, REM);
        contentValues.put(COL6, awake);
        contentValues.put(COL7, score);
        contentValues.put(COL8, efficiency);

        //Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public static synchronized SleepNightDbHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new SleepNightDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    /*private SleepNightDbHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }*/

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    /*public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }*/

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    /*public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }*/

    /**
     * Delete from database
     * @param id
     * @param name
     */
    /*public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }*/

}