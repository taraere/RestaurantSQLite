package com.example.tara.restaurantrevisitedsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tara on 01/12/2017.
 */

public class MyOrderDatabase extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "myorder";
    private static final String KEY_ID = "_id";
    public static final String COL_1 = "name";
//    private static final String COL_2 = "price";
    private static final String COL_3 = "amount";
    private static final String[] COLUMNS = {KEY_ID, COL_1, COL_3};
    private static final String TAG = "ToDoDatabase";
    private static MyOrderDatabase instance;

    public static MyOrderDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new MyOrderDatabase(context);
        }
        return instance;
    }

    private MyOrderDatabase(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // sql statement creates table
        String CREATE_MYORDER_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_1 + " TEXT, " +
//                COL_2 + " TEXT, " +
                COL_3 + " INTEGER )";

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, "test");
        contentValues.put(COL_3, 3);

        // execute sql query
        db.execSQL(CREATE_MYORDER_TABLE);

        db.insert(TABLE_NAME, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(COL_1, "test2");
        contentValues.put(COL_3, 3);

        db.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop older todos table if applicable
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);

        // create fresh table when starting app.
        this.onCreate(db);
    }

    public boolean addItem(String s, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, s);
        Log.d(TAG, "addData: Adding " + s + " to " + TABLE_NAME);

        contentValues.put(COL_3, amount);
        Log.d(TAG, "addData: Adding " + amount + " to " + TABLE_NAME);

        // long var for if data is correctly or incorrectly inserted
        long result = db.insert(TABLE_NAME, null, contentValues);

        // result = -1 if incorrect
        return (result != -1);
    }

    public void clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        // sql query
        String query = "DELETE " + TABLE_NAME;

        // execute query
        db.execSQL(query);

        // some logs
        Log.d(TAG, "clear: query: " + query);
        Log.d(TAG, "clear: Deleting table " + TABLE_NAME);
    }

    /**
     * return all data from db
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Deletes info from database
     */
    public void delete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + KEY_ID + " = '" + id + "'";

        Log.d(TAG, "delete: query: " + query);
        Log.d(TAG, "delete: Deleting " + id + " from database.");
        db.execSQL(query);
    }
}
