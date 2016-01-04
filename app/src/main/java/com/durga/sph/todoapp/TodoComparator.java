package com.durga.sph.todoapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by durga on 1/3/16.
 */
public class TodoComparator implements Comparator<TodoItem>
{
    public int compare(TodoItem item1, TodoItem item2)
    {

        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date Pdate = null;
        Date Qdate= null;
        try {
            Pdate = df.parse(item1.duedate);
            Qdate = df.parse(item2.duedate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int dateCompare = Pdate.compareTo(Qdate);
        if (dateCompare != 0)
        {
            return dateCompare;
        } else
        {
            int compare2 = item1.priority.compareTo(item2.priority);
            if(compare2 != 0)
            {
                return compare2;
            }
            else
            {
                return  item1.duetime.compareToIgnoreCase(item2.duetime);
            }
        }
    }
}
