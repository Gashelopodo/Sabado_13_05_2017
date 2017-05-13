package com.gashe.appforegroundservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button)findViewById(R.id.btn_1);
        Button stopButton = (Button)findViewById(R.id.btn_2);

    }


    public void startService(View view){
        Intent intentService = new Intent(this, MyServiceForeground.class);
        intentService.setAction("START");
        startService(intentService);
    }

    public void stopService(View view){
        Intent intentService = new Intent(this, MyServiceForeground.class);
        intentService.setAction("STOP");
        startService(intentService);
    }

}
