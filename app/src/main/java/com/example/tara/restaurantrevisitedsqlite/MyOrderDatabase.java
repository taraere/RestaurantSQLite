package com.example.tara.restaurantrevisitedsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tara on 01/12/2017.
 */

public class MyOrderDatabase extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "myorder";
    private static final String KEY_ID = "_id";
    private static final String COL_1 = "name";
    private static final String COL_2 = "price";
    private static final String COL_3 = "amount";
    private static final String[] COLUMNS = {KEY_ID, COL_1, COL_2, COL_3};
    private static final String TAG = "ToDoDatabase";
    private static MyOrderDatabase instance;

    private MyOrderDatabase(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // sql statement creates table
        String CREATE_MYORDER_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_1 + " TEXT, " +
                COL_2 + " TEXT, " +
                COL_3 + " INTEGER )";
        // execute sql query
        db.execSQL(CREATE_MYORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop older todos table if applicable
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);

        // create fresh table when starting app.
        this.onCreate(db);
    }

    public void addItem(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // sql query
        String query = "INSERT INTO  " + TABLE_NAME + " WHERE "
                + KEY_ID + " = '" + id + "'";

        // carry out query
        db.execSQL(query);

        // log event
        Log.d(TAG, "addItem: query: " + query);
        Log.d(TAG, "addItem: Adding " + id + " to database.");
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
}
