package com.durga.sph.todoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cengalabs.flatui.views.FlatButton;

import java.util.ArrayList;

/**
 * Created by durga on 12/29/15.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.AppViewHolder>
{
    Context ctx;
    LayoutInflater inflater;
    ArrayList<TodoItem> todoItemsArray;
    SQLiteDBHelper dbHelper;

    public TodoAdapter(Context context, ArrayList<TodoItem> itemsArray, SQLiteDBHelper dbhelper)
    {
        super();
        ctx = context;
        inflater =  LayoutInflater.from(ctx);
        todoItemsArray = itemsArray;
        dbHelper = dbhelper;
    }


    public static class AppViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title;
        public FlatButton priority;
        public TextView dueDate;
        public TextView dueTime;
        public ImageButton edittoolBarBtn;

        public AppViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById(R.id.todoname);
            priority = (FlatButton) view.findViewById(R.id.todopriority);
            dueDate = (TextView) view.findViewById(R.id.tododuedate);
            dueTime = (TextView) view.findViewById(R.id.tododuetime);
            edittoolBarBtn = (ImageButton) view.findViewById(R.id.edittoolBarBtn);
        }
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final View view = inflater.inflate(R.layout.todo_row_grid, parent, false);
        ((ImageButton)view.findViewById(R.id.edittoolBarBtn)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TodoQueries todoquery = new TodoQueries(ctx, dbHelper);
                        TodoItem todoObj;
                        Intent editIntent = new Intent(ctx, EditItemActivity.class);
                        int position = (int) (view.getTag());
                        String title = ((TextView) (view.findViewById(R.id.todoname))).getText().toString();
                        Constants.Priority priority = Constants.Priority.valueOf(((TextView) view.findViewById(R.id.todopriority)).getTag().toString());
                        String duedate = ((TextView) (view.findViewById(R.id.todopriority))).getText().toString();
                        //String n, Constants.Priority pr, String ddate, String dt, Constants.Status sts, String cdate, String tnotes
                        todoObj = todoquery.GetItem(title);
                        EditItem editObj = new EditItem(todoObj, position);
                        editIntent.putExtra(ctx.getResources().getString(R.string.itemparams), editObj);
                        ((MainActivity) ctx).startActivityForResult(editIntent, Constants.REQUEST_CODE);
                    }
                });
                view.setOnLongClickListener(
                        new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int position = (int) (view.getTag());
                        TodoQueries queriesObj = new TodoQueries(ctx, dbHelper);
                        queriesObj.RemoveTodoItem(todoItemsArray.get(position).getName());
                        todoItemsArray.remove(position);
                        notifyDataSetChanged();
                        return false;
                    }
                });

        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position)
    {
        TodoItem todoitem = todoItemsArray.get(position);
        holder.itemView.setTag(position);
        holder.title.setText(todoitem.getName());
        //holder.edittoolBarBtn.setTag(position);
        holder.priority.setTag(todoitem.getPriority().toString());
        if(todoitem.getPriority().equals(Constants.Priority.High))
        {
            holder.priority.setBackground(ctx.getResources().getDrawable(R.drawable.red_icon));
        }
        else if(todoitem.getPriority().equals(Constants.Priority.Medium))
        {
            holder.priority.setBackground(ctx.getResources().getDrawable(R.drawable.green_icon));
        }
        else if(todoitem.getPriority().equals(Constants.Priority.Low))
        {
            holder.priority.setBackground(ctx.getResources().getDrawable(R.drawable.blue_icon));
        }


        holder.dueDate.setText(todoitem.getDuedate());
        holder.dueTime.setText(todoitem.getDuetime());
    }

    @Override
    public int getItemCount()
    {
        if(todoItemsArray == null)
            return 0;
        return todoItemsArray.size();
    }
}
