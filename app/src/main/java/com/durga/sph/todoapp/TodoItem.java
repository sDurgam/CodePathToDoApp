package com.durga.sph.todoapp;

import java.io.Serializable;

/**
 * Created by durga on 12/29/15.
 */
public class TodoItem implements Serializable
{
    String name;
    Constants.Priority priority;
    Constants.Status status;
    String duedate;
    String duetime;
    String completiondate;
    String notes;

    public Constants.Status getStatus() {
        return status;
    }

    public void setStatus(Constants.Status status) {
        this.status = status;
    }

    public String getDuetime() {
        return duetime;
    }

    public void setDuetime(String duetime) {
        this.duetime = duetime;
    }

    public String getCompletiondate() {
        return completiondate;
    }

    public void setCompletiondate(String completiondate) {
        this.completiondate = completiondate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private static final long serialVersionUID = 5177222050535318633L;

    public TodoItem()
    {}

    public TodoItem(String n, Constants.Priority pr, String ddate, String dt, Constants.Status sts, String cdate, String tnotes)
    {
        name = n;
        priority = pr;
        status = sts;
        duedate = ddate;
        duetime = dt;
        completiondate = cdate;
        notes = tnotes;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constants.Priority getPriority() {
        return priority;
    }

    public void setPriority(Constants.Priority priority) {
        this.priority = priority;
    }

}
