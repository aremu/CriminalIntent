package com.example.leodavinci360.criminalintent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.Dialog;
import android.view.View;
import android.widget.DatePicker;
import android.content.Intent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.widget.DatePicker.OnDateChangedListener;


public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";

    private Date mDate;

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void sendResult(int resultCode){
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
        //Create a calendar to get the year, month, and day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_date, null);

        DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new OnDateChangedListener(){

            public void onDateChanged(DatePicker view, int year,
                                      int month, int day){

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(mDate);
                int hour   = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                mDate = new GregorianCalendar(year, month, day, hour,
                        minute, second).getTime();

                getArguments().putSerializable(EXTRA_DATE, mDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(
                        android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .create();
    }
}
