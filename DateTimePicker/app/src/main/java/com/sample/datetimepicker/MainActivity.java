package com.sample.datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute, mSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews() {
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        txtDate = (EditText) findViewById(R.id.in_date);
        txtTime = (EditText) findViewById(R.id.in_time);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date:
                datePicker();
                break;

            case R.id.btn_time:
                timePicker();
                break;
        }
    }

    private void timePicker() {

        // Get Current Time
        final Calendar cal = Calendar.getInstance();
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);
        mSeconds = cal.get(Calendar.SECOND);
        // Launch Time Picker Dialog

        TimePickerDialog time = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
                String strDate = sdf.format(cal.getTimeInMillis());

                txtTime.setText(strDate);
            }
        }, mHour, mMinute, false);

        time.show();
    }

    //the other way of doing date customization
   /* private void dateCustomize() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyy");
        Date da = dateFormat.parse(txtDate.getText().toString());

        SimpleDateFormat output = new SimpleDateFormat("E/y");
        String outputString = output.format(da);
        txtDate.setText(outputString);
    }*/


    private void datePicker() {
        // Get Current Date
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get((Calendar.MONTH));
        mDay = cal.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog date = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        // Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.DAY_OF_MONTH, view.getDayOfMonth());
                        cal.set(Calendar.MONTH, view.getMonth());
                        cal.set(Calendar.YEAR, view.getYear());

                        SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yy EEEE");
                        String strDate = sdf.format(cal.getTimeInMillis());


                        txtDate.setText(strDate);
                        Log.e("DDDD  ", " " + strDate);

                    }
                }, mYear, mMonth, mDay);

        date.show();

    }
}


//january - 20(2) - 16 HH:mm:ss Monday am/pm

//january-20-16 22:10:46 Tuesday
