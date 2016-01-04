package com.durga.sph.todoapp;

import java.io.Serializable;
import java.util.Calendar;

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
    String rawtime;
    String completiondate;
    String notes;

    public Constants.Status getStatus() {
        return status;
    }

    public void setStatus(Constants.Status status) {
        this.status = status;
    }

    public String getDuetime() {
        if(duetime == null)
        {
            GetDueTime(rawtime);
        }
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

    public TodoItem(String n, Constants.Priority pr, String ddate, String rt, Constants.Status sts, String cdate, String tnotes)
    {
        name = n;
        priority = pr;
        status = sts;
        duedate = ddate;
        rawtime = rt;
        GetDueTime(rt);
        completiondate = cdate;
        notes = tnotes;
    }

    public void GetDueTime(String rawtime)
    {
        if(rawtime != null && !rawtime.equals(""))
        {
            String[] timesplit = rawtime.split(":");
            Calendar datetime = Calendar.getInstance();
            int hour = Integer.valueOf(timesplit[0]);
            int minute = Integer.valueOf(timesplit[1]);
            String am_pm = "";
            datetime.set(Calendar.HOUR_OF_DAY, hour);
            datetime.set(Calendar.MINUTE, minute);
            if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                am_pm = "AM";
            else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                am_pm = "PM";
            String minutestr = (minute < 10) ? ("0" + minute) : String.valueOf(minute);
            String strHrsToShow = ((datetime.get(Calendar.HOUR) == 0) ? "12" : datetime.get(Calendar.HOUR)) + ":" + minutestr + " ";
            duetime = strHrsToShow + " " + am_pm;
        }
        else
        {
            duetime = "";
        }
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getRawtime() {
        return rawtime;
    }

    public void setRawtime(String rawtime) {
        this.rawtime = rawtime;
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
