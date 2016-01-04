package com.durga.sph.todoapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by durga on 1/3/16.
 */
public class TodoComparator implements Comparator<TodoItem> {
    public int compare(TodoItem item1, TodoItem item2) {

        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date Pdate = null;
        Date Qdate = null;
        int compare;
        if (item1.duedate == null || item1.duedate.equals(""))
        {
            if (item2.duedate == null || item2.duedate.equals(""))
            {
                compare = comparePriority(item1, item2);
            } else
            {
                compare = 1;
            }
        } else {
            try {
                Pdate = df.parse(item1.duedate);
                Qdate = df.parse(item2.duedate);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                compare = 0;
            }
            compare = Pdate.compareTo(Qdate);
            if(compare == 0)
            {
                compare = comparePriority(item1, item2);
            }
        }
        return compare;
    }

    private int comparePriority(TodoItem item1, TodoItem item2)
    {
        int compare = item1.priority.compareTo(item2.priority);
        if (compare == 0)
        {
            int rt1 = 0;
            int rt2 = 0;
            try {
                if (item1.rawtime != null || !item1.rawtime.equals("")) {
                    rt1 = Integer.parseInt(item1.rawtime.replace(":", ""));
                }
                if (item2.rawtime != null || !item2.rawtime.equals("")) {
                    rt2 = Integer.parseInt(item2.rawtime.replace(":", ""));
                }
            }catch (Exception ex)
            {
                rt1 = 0;
                rt2 = 0;
            }
            compare = rt1 - rt2;
        }
        return compare;
    }
}
