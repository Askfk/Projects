package com.bjtu.etransfer.etransfer;

/**
 * Created by liyiming on 2017/3/28.
 */

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //得到日期对象
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
        String hour,minute;
        if (hourOfDay<10){
            hour=""+"0"+hourOfDay;
        }
        else{
            hour=""+hourOfDay;
        }
        if (minuteOfDay<10){
            minute=""+"0"+minuteOfDay;
        }
        else{
            minute=""+minuteOfDay;
        }
        String time=hour + ":" + minute+":00";
        Toast.makeText(getActivity(),time, Toast.LENGTH_LONG).show();
    }
}
