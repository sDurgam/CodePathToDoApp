package com.durga.sph.todoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import java.util.Calendar;

/**
 * Created by durga on 12/27/15.
 */
public class EditItemActivity extends BaseActivity
{
    EditItem editObj;
    EditText edititemtxt;
    EditText duedateTxt;
    EditText completedateTxt;
    EditText timepickerTxt;
    Spinner prioritySpinner;
    Spinner statusSpinner;
    EditText notesTxt;
    int itemposition;
    String duedatestr = "duedate";
    String completiondatestr = "completiondate";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        toolbar = (Toolbar) findViewById(R.id.customedittoolbar);
        edititemtxt = (EditText) findViewById(R.id.taskTxt);
        duedateTxt = (EditText) findViewById(R.id.duedateTxt);
        completedateTxt = (EditText) findViewById(R.id.completiondateTxt);
        timepickerTxt = (EditText) findViewById(R.id.picktimeTxt);
        prioritySpinner = (Spinner) findViewById(R.id.prioritySpinner);
        statusSpinner = (Spinner) findViewById(R.id.doneSpinner);
        notesTxt = (EditText) findViewById(R.id.notesTxt);
        editObj = (EditItem) getIntent().getSerializableExtra(getResources().getString(R.string.itemparams));
        if(editObj != null)
        {
            itemposition = editObj.getPosition();
            if(editObj.getName() != null) {
                edititemtxt.setText(editObj.getName());
                duedateTxt.setText(editObj.getDuedate());
                completedateTxt.setText(editObj.getCompletiondate());
                timepickerTxt.setText(editObj.getDuetime());
                //prioritySpinner.setSelection();
                if(editObj.getPriority().equals(Constants.Priority.High))
                {
                    prioritySpinner.setSelection(0);
                }
                else if(editObj.getPriority().equals(Constants.Priority.Medium))
                {
                    prioritySpinner.setSelection(1);
                }
                else if(editObj.getPriority().equals(Constants.Priority.Low))
                {
                    prioritySpinner.setSelection(2);
                }

                if(editObj.getStatus().equals(Constants.Status.Done))
                {
                    statusSpinner.setSelection(0);
                }
                else if(editObj.getStatus().equals(Constants.Status.Unable_to_complete))
                {
                    statusSpinner.setSelection(1);
                }
                if(editObj.getStatus().equals(Constants.Status.Yet_to_complete))
                {
                    statusSpinner.setSelection(2);
                }

                notesTxt.setText(editObj.getNotes());
            }
        }
        duedateTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                {
                    showDatePickerDialog(v);
                }
            }
        });
        completedateTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    showDatePickerDialog(v);
                }
            }
        });
        timepickerTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    showTimePickerDialog(v);
                }
            }
        });
        SetUpToolBar();
    }
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            // Do something with the time chosen by the user
            EditText pickTimeTxt = (EditText) getActivity().findViewById(R.id.picktimeTxt);
            Calendar datetime = Calendar.getInstance();
            String am_pm = "";
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);
            if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                am_pm = "AM";
            else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                am_pm = "PM";
            String minutestr = (minute < 10) ? ("0" + minute) : String.valueOf(minute);
                    String strHrsToShow = ((datetime.get(Calendar.HOUR) == 0) ?"12":datetime.get(Calendar.HOUR)+":") + minutestr + " ";
            pickTimeTxt.setText(strHrsToShow + " " + am_pm);
            this.getDialog().dismiss();
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        String type;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            if(this.getArguments().get("type") != null)
            {
                type = this.getArguments().get("type").toString();
            }
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            // Do something with the date chosen by the user
            if(type.equals("duedate"))
            {
                EditText duedateTxt = (EditText) getActivity().findViewById(R.id.duedateTxt);
                duedateTxt.setText((month + 1) + "-" + day + "-" + year);
            }
            else if(type.equals("completiondate"))
            {
                EditText completiondateTxt = (EditText) getActivity().findViewById(R.id.completiondateTxt);
                completiondateTxt.setText((month + 1) + "-" + day + "-" + year);
            }
            this.getDialog().dismiss();
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putString("type", v.getTag().toString());
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    //save button clicked
    public void SaveClick(View view)
    {
        String name = edititemtxt.getText().toString();
        Constants.Priority priority = Constants.Priority.valueOf(prioritySpinner.getSelectedItem().toString());
        Constants.Status status = Constants.Status.valueOf(statusSpinner.getSelectedItem().toString());
        String duedate = duedateTxt.getText().toString();
        String completiondate = completedateTxt.getText().toString();
        String duetime = timepickerTxt.getText().toString();
        String notes = notesTxt.getText().toString();
        //public TodoItem(String n, Constants.Priority pr, String ddate, String dt, Constants.Status sts, String cdate, String tnotes)
        TodoItem todoObj = new TodoItem(name, priority, duedate, duetime, status, completiondate, notes);
        editObj = new EditItem(todoObj, itemposition);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra(getResources().getString(R.string.itemparams), editObj);
        // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
