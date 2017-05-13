package com.gashe.appseleccionarfecha;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by cice on 13/5/17.
 */

public class HoraDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(getClass().getCanonicalName(), "HORA SELECCIONADA " + hourOfDay + ":" + minute);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        Dialog timePicker = null;

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        timePicker = new TimePickerDialog(getActivity(), this, hour, minute, true);

        return timePicker;

    }
}
