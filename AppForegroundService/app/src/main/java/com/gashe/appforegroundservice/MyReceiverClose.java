package com.gashe.appforegroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiverClose extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        Log.d(getClass().getCanonicalName(), "RECIBO CLOSE");

        Intent intentService = new Intent(context, MyServiceForeground.class);
        intentService.setAction("STOP");
        context.startService(intentService);

    }
}