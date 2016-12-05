package com.sun.abby.lab1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Abby Sun on 2016-10-13.
 */
public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "chatDatabase";
    public static int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "ChatMessage";
    public static final String COLUMN1 = "_id";
    public static final String COLUMN2 = "message";

    public ChatDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i("ChatDatabaseHelper", "onCreate" );
        String CREATE_CHAT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN2 + " TEXT)";
        Log.i("ChatDatabaseHelper", "Testing: " + CREATE_CHAT_TABLE);
        db.execSQL(CREATE_CHAT_TABLE);
        //db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('hi')");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "onUpdate version " + oldVersion +" to new database version: " +  newVersion );
    }

    public void insertData(String msg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN2, msg);
        long insertResult = db.insert(TABLE_NAME, null, contentValues);
        Log.i("ChatDatabaseHelper", "insert data result: " + insertResult );
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }
}
