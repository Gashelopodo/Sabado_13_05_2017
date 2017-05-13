package com.gashe.appseleccionarfecha;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*DialogFragment fragmentCalendario = new CalendarioDialog();
        fragmentCalendario.show(getSupportFragmentManager(), "calendario");*/

        DialogFragment fragmentCalendario = new HoraDialog();
        fragmentCalendario.show(getSupportFragmentManager(), "hora");


    }

    //TODO
    public void setFecha(int year, int month, int day){

    }

}
