package com.durga.sph.todoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends BaseActivity
{

    ArrayList<TodoItem> todoItemsArray;
    TodoAdapter todoAdapter;
    RecyclerView lvItems;
    // EditText etEditText;
    Context ctx;
    SQLiteDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.customtoolbar);
        ctx = this;
        dbHelper = new SQLiteDBHelper(this);
        todoItemsArray = new ArrayList<>();
        todoAdapter = new TodoAdapter(ctx, todoItemsArray, dbHelper);
        lvItems = (RecyclerView) findViewById(R.id.lvItems);
        //etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setAdapter(todoAdapter);
        PopulateArrayItems();
        SetUpToolBar();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        lvItems.setLayoutManager(gridLayoutManager);
        //lvItems.setHasFixedSize(true);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        lvItems.setHorizontalScrollBarEnabled(true);
        lvItems.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        TodoQueries queryObj = new TodoQueries(this, dbHelper);
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == Constants.REQUEST_CODE)
        {
            // Extract name value from result extras
            EditItem editObj = (EditItem)(data.getExtras().getSerializable(getResources().getString(R.string.itemparams)));
            int position = editObj.getPosition();
            int size = todoItemsArray.size();
            if(position < size)
            {
                TodoItem todoObj = todoItemsArray.get(position);
                String name = todoObj.getName();
                queryObj.UpdateItem(editObj, name);
                todoItemsArray.set(position, editObj);
            }
            else
            {
                queryObj.AddItem(editObj);
                todoItemsArray.add(editObj);
            }
            PopulateAdapter();
        }
    }

    public void PopulateAdapter()
    {
        Collections.sort(todoItemsArray, new TodoComparator());
        todoAdapter.todoItemsArray = todoItemsArray;
        //writeItems();
        todoAdapter.notifyDataSetChanged();
    }

    public void PopulateArrayItems()
    {
        todoItemsArray = new ArrayList<>();
        readItems();
        todoAdapter = new TodoAdapter(ctx, todoItemsArray, dbHelper);
    }

    public void onAddItem(View view)
    {
        int position = todoItemsArray.size();
        //Add a new task
        TodoItem todoObj;
        Intent editIntent = new Intent(ctx, EditItemActivity.class);
        todoObj = new TodoItem();
        EditItem editObj = new EditItem(todoObj, position);
        editIntent.putExtra(ctx.getResources().getString(R.string.itemparams), editObj);
        ((MainActivity) ctx).startActivityForResult(editIntent, Constants.REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void readItems()
    {
        TodoQueries todoquery = new TodoQueries(ctx, dbHelper);
        todoItemsArray = todoquery.ReadItems(dbHelper);
        PopulateAdapter();
//        todoAdapter.todoItemsArray = todoItemsArray;
//        todoAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

//    private void showEditDialog(EditItem editObj)
//    {
//        FragmentManager fm = getSupportFragmentManager();
//        EditNameDialog editNameDialog = EditNameDialog.newInstance(editObj);
//        editNameDialog.show(fm, "fragment_edit_name");
//    }
//
//    @Override
//    public void onFinishEditDialog(String name, int position)
//    {
//        todoItems.set(position, name);
//        writeItems();
//        todoAdapter.notifyDataSetChanged();
//    }
}
