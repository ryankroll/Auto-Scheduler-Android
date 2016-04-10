package com.example.rkroll.auto_scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserReaderDbHelper extends SQLiteOpenHelper {
    //If you change the database schema, you must increment the databse version
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserReader.db";
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_NAME ="name";
    public static final String USER_COLUMN_OBJECT_ID = "object_Id";

    public UserReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DataBase Operations", "Database created...");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("Database operations", "Table Created...");

        /*String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USER_TABLE_NAME + "(" + USER_COLUMN_ID +
                " INTEGER PRIMARY KEY, " + USER_COLUMN_NAME + " TEXT," + USER_COLUMN_OBJECT_ID +
                " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        */
        db.execSQL("CREATE TABLE USER(" +
                "id INT PRIMARY KEY NOT NULL, " +
                "name TEXT, " +
                "object_Id CHAR(50));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //This database is only a cache for online data, so its upgrade policy is to simply
        // discard the data and start over.
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String name, String objectId){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_NAME, name);
        contentValues.put(USER_COLUMN_OBJECT_ID, objectId);
        db.insert(USER_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getUser(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " +
                USER_COLUMN_NAME + "=?", new String[]{name});
        return res;
    }

    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME, null);
        return res;
    }

    public Integer deleteUser(String objectId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER_TABLE_NAME, USER_COLUMN_OBJECT_ID + "=?", new String[]{objectId});
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
