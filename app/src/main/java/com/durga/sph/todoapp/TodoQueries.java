package com.durga.sph.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by durga on 12/31/15.
 */
public class TodoQueries
{
    Context ctx;
    SQLiteDBHelper dbHelper;

    public TodoQueries(Context ct, SQLiteDBHelper helper)
    {
        ctx = ct;
        dbHelper = helper;
    }

    public int GetItemCount(SQLiteDatabase writer, String title)
    {
        TodoItem todoObj;
        String[] args = new String[] {SQLiteDBHelper.KEY_ID, SQLiteDBHelper.KEY_NAME, SQLiteDBHelper.KEY_PRIORITY, SQLiteDBHelper.KEY_DUEDATE, SQLiteDBHelper.KEY_DUETIME, SQLiteDBHelper.KEY_STATUS, SQLiteDBHelper.KEY_COMPLETIONDATE, SQLiteDBHelper.KEY_NOTES};
        String whereClause = SQLiteDBHelper.KEY_NAME + " = ?";
        String[] whereArgs = new String[] {title};
        Cursor cursor = writer.query(SQLiteDBHelper.TABLE_TODOITEM, args, whereClause, whereArgs, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public TodoItem GetItem(String title)
    {
        TodoItem todoObj;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = new String[] {SQLiteDBHelper.KEY_ID, SQLiteDBHelper.KEY_NAME, SQLiteDBHelper.KEY_PRIORITY, SQLiteDBHelper.KEY_DUEDATE, SQLiteDBHelper.KEY_DUETIME, SQLiteDBHelper.KEY_STATUS, SQLiteDBHelper.KEY_COMPLETIONDATE, SQLiteDBHelper.KEY_NOTES};
        String whereClause = SQLiteDBHelper.KEY_NAME + " = ?";
        String[] whereArgs = new String[] {title};
        Cursor cursor = db.query(SQLiteDBHelper.TABLE_TODOITEM, args, whereClause, whereArgs, null, null, null, null);
        int count = cursor.getCount();
        if(count > 0) {
            cursor.moveToFirst();
            todoObj = new TodoItem(cursor.getString(1), Constants.Priority.valueOf(cursor.getString(2)), cursor.getString(3), cursor.getString(4), Constants.Status.valueOf(cursor.getString(5)), cursor.getString(6), cursor.getString(7));
        }
        else
        {
            todoObj = new TodoItem();
        }
        cursor.close();
        db.close();
        return todoObj;
    }

    public ArrayList<TodoItem> ReadItems(SQLiteDBHelper dbHelper)
    {
        ArrayList<TodoItem> todoItemsArray = new ArrayList<>();
        TodoItem todoObj;
        SQLiteDatabase  db = dbHelper.getReadableDatabase();
        String[] args = new String[] {SQLiteDBHelper.KEY_NAME, SQLiteDBHelper.KEY_PRIORITY, SQLiteDBHelper.KEY_DUEDATE, SQLiteDBHelper.KEY_DUETIME, SQLiteDBHelper.KEY_STATUS, SQLiteDBHelper.KEY_COMPLETIONDATE, SQLiteDBHelper.KEY_NOTES};
        Cursor cursor = db.query(SQLiteDBHelper.TABLE_TODOITEM, args, null, null, null, null, null);
        cursor.moveToFirst();
        Toast.makeText(ctx, String.valueOf(cursor.getCount()), Toast.LENGTH_LONG).show();
        do
        {
            //(String n, Constants.Priority pr, String ddate, String dt, Constants.Status sts, String cdate, String tnotes)
            todoObj = new TodoItem(cursor.getString(0), Constants.Priority.valueOf(cursor.getString(1)), cursor.getString(2), cursor.getString(3), Constants.Status.valueOf(cursor.getString(4)), cursor.getString(5), cursor.getString(6));
            todoItemsArray.add(todoObj);
        }while(cursor.moveToNext());
        cursor.close();
        db.close();
        return todoItemsArray;
    }

    public void UpdateItem(TodoItem todoObj, String oldname)
    {
        SQLiteDatabase writer = dbHelper.getWritableDatabase();
        if(GetItemCount(writer, oldname) > 0)
        {
            //, String whereClause, String[] whereArgs) {
            //update todo list
            String whereClause = SQLiteDBHelper.KEY_NAME + " = ?";
            String[] whereArgs = new String[] {oldname};
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            ContentValues cv = new ContentValues();
            cv.put(SQLiteDBHelper.KEY_NAME, todoObj.getName());
            cv.put(SQLiteDBHelper.KEY_PRIORITY, todoObj.getPriority().toString());
            cv.put(SQLiteDBHelper.KEY_DUEDATE, todoObj.getDuedate());
            cv.put(SQLiteDBHelper.KEY_DUETIME, todoObj.getDuetime());
            cv.put(SQLiteDBHelper.KEY_COMPLETIONDATE, todoObj.getCompletiondate());
            cv.put(SQLiteDBHelper.KEY_STATUS, todoObj.getStatus().toString());
            cv.put(SQLiteDBHelper.KEY_NOTES, todoObj.getNotes());
            writer.update(SQLiteDBHelper.TABLE_TODOITEM, cv, whereClause, whereArgs);
        }
        else
        {
            //insert todo list
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            ContentValues cv = new ContentValues();
            cv.put(SQLiteDBHelper.KEY_NAME, todoObj.getName());
            cv.put(SQLiteDBHelper.KEY_PRIORITY, todoObj.getPriority().toString());
            cv.put(SQLiteDBHelper.KEY_DUEDATE, todoObj.getDuedate());
            cv.put(SQLiteDBHelper.KEY_DUETIME, todoObj.getDuetime());
            cv.put(SQLiteDBHelper.KEY_COMPLETIONDATE, todoObj.getCompletiondate());
            cv.put(SQLiteDBHelper.KEY_STATUS, todoObj.getStatus().toString());
            cv.put(SQLiteDBHelper.KEY_NOTES, todoObj.getNotes());
            writer.insert(SQLiteDBHelper.TABLE_TODOITEM, null, cv);
        }
        writer.close();
    }

    public void AddItem(TodoItem todoObj)
    {
        SQLiteDatabase writer = dbHelper.getWritableDatabase();
        if(GetItemCount(writer, todoObj.getName()) > 0)
        {
            //, String whereClause, String[] whereArgs) {
            //update todo list
            String whereClause = SQLiteDBHelper.KEY_NAME + " = ?";
            String[] whereArgs = new String[] {todoObj.getName()};
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            ContentValues cv = new ContentValues();
            cv.put(SQLiteDBHelper.KEY_NAME, todoObj.getName());
            cv.put(SQLiteDBHelper.KEY_PRIORITY, todoObj.getPriority().toString());
            cv.put(SQLiteDBHelper.KEY_DUEDATE, todoObj.getDuedate());
            cv.put(SQLiteDBHelper.KEY_DUETIME, todoObj.getDuetime());
            cv.put(SQLiteDBHelper.KEY_COMPLETIONDATE, todoObj.getCompletiondate());
            cv.put(SQLiteDBHelper.KEY_STATUS, todoObj.getStatus().toString());
            cv.put(SQLiteDBHelper.KEY_NOTES, todoObj.getNotes());
            writer.update(SQLiteDBHelper.TABLE_TODOITEM, cv, whereClause, whereArgs);
        }
        else
        {
            //insert todo list
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            ContentValues cv = new ContentValues();
            cv.put(SQLiteDBHelper.KEY_NAME, todoObj.getName());
            cv.put(SQLiteDBHelper.KEY_PRIORITY, todoObj.getPriority().toString());
            cv.put(SQLiteDBHelper.KEY_DUEDATE, todoObj.getDuedate());
            cv.put(SQLiteDBHelper.KEY_DUETIME, todoObj.getDuetime());
            cv.put(SQLiteDBHelper.KEY_COMPLETIONDATE, todoObj.getCompletiondate());
            cv.put(SQLiteDBHelper.KEY_STATUS, todoObj.getStatus().toString());
            cv.put(SQLiteDBHelper.KEY_NOTES, todoObj.getNotes());
            writer.insert(SQLiteDBHelper.TABLE_TODOITEM, null, cv);
        }
        writer.close();
    }

    public void RemoveTodoItem(String name)
    {
        SQLiteDatabase writer = dbHelper.getWritableDatabase();
        if(GetItemCount(writer, name) > 0)
        {
            //remove todo list
            String whereClause = SQLiteDBHelper.KEY_NAME + " = ?";
            String[] whereArgs = new String[] {name};
            writer.delete(SQLiteDBHelper.TABLE_TODOITEM, whereClause, whereArgs);
        }
        writer.close();
    }
}
