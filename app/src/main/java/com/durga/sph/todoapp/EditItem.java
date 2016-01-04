package com.durga.sph.todoapp;

/**
 * Created by durga on 12/27/15.
 */
public class EditItem extends TodoItem
{
    int position;
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public EditItem()
    {
    }

    public EditItem(TodoItem todoitem, int pos)
    {
        name = todoitem.getName();
        priority = todoitem.getPriority();
        duedate = todoitem.getDuedate();
        rawtime = todoitem.getRawtime();
        duetime = todoitem.getDuetime();
        completiondate = todoitem.getCompletiondate();
        status = todoitem.getStatus();
        notes = todoitem.getNotes();
        position = pos;
    }

    public EditItem(int pos, String nm,Constants.Priority pr, String duedatestr, String duetimestr, String rawtimestr, String cmpdatestr, Constants.Status sts, String nts)
    {
        position = pos;
        name = nm;
        priority = pr;
        rawtime = rawtimestr;
        duedate = duedatestr;
        duetime = duetimestr;
        completiondate = cmpdatestr;
        status = sts;
        notes = nts;
    }
}
