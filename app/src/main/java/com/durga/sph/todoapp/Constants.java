package com.durga.sph.todoapp;


/**
 * Created by durga on 12/29/15.
 */
public class Constants
{
    public static enum Priority
    {
        High,
        Medium,
        Low
    }
    public static enum Status
    {
        Done,
        Yet_to_complete,
        Unable_to_complete;
        //private String displayName;

//        Status(String displayName)
//        {
//            this.displayName = displayName;
//        }
//        public String displayName() { return displayName; }
//        @Override public String toString() { return displayName; }
    }
    public static int REQUEST_CODE = 20;
}
