package com.durga.sph.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by durga on 12/29/15.
 */
public class SQLiteDBHelper extends SQLiteOpenHelper
{
    public SQLiteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version)
    {
        super(context, name, factory, version);
    }

    public SQLiteDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "todoitems.db";
    public static final String TABLE_TODOITEM = "todoitem";
    //User columns
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PRIORITY = "priority";
    public static final String KEY_DUEDATE = "duedate";
    //public static final String KEY_DUETIME = "duetime";
    public static final String KEY_RAWTIME = "rawtime";
    public static final String KEY_STATUS = "status";
    public static final String KEY_COMPLETIONDATE = "completiondate";
    public static final String KEY_NOTES = "notes";
    public static final String CREATE_TABLE_TODOITEM =  String.format("CREATE TABLE %s" +
            "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)"
            , TABLE_TODOITEM, KEY_ID, KEY_NAME, KEY_PRIORITY, KEY_DUEDATE, KEY_RAWTIME, KEY_STATUS, KEY_COMPLETIONDATE, KEY_NOTES);

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODOITEM);
        db.execSQL(CREATE_TABLE_TODOITEM);
        InsertTodoTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }

    private void InsertTodoTable(SQLiteDatabase db)
    {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        TodoItem todoObj = new TodoItem("item 1", Constants.Priority.High, dateFormat.format(calendar.getTime()).toString(), calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE), Constants.Status.Yet_to_complete, " ", " ");
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, todoObj.getName());
        cv.put(KEY_PRIORITY, todoObj.getPriority().toString());
        cv.put(KEY_DUEDATE, todoObj.getDuedate());
        cv.put(KEY_RAWTIME, todoObj.getRawtime());
        cv.put(KEY_COMPLETIONDATE, todoObj.getCompletiondate());
        cv.put(KEY_STATUS, todoObj.getStatus().toString());
        cv.put(KEY_NOTES, todoObj.getNotes());
        db.insert(TABLE_TODOITEM, null, cv);
    }
}
