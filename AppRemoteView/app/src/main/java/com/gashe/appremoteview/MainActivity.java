package com.gashe.appremoteview;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // preparo la vista para la notificación
        RemoteViews notificationViews = new RemoteViews(getPackageName(), R.layout.notificacion);

        Intent buttonPlayIntent = null;
        Intent buttonNextIntent = null;
        Intent buttonPreviousIntent = null;
        Intent buttonCloseIntent = null;

        // asocio los botones con los receivers
        buttonPlayIntent = new Intent(this, MyReceiverPlay.class);
        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(this, 200, buttonPlayIntent, 0);
        notificationViews.setOnClickPendingIntent(R.id.btn_play, pendingIntentPlay);

        buttonNextIntent = new Intent(this, MyReceiverNext.class);
        PendingIntent pendingIntentNext = PendingIntent.getBroadcast(this, 200, buttonNextIntent, 0);
        notificationViews.setOnClickPendingIntent(R.id.btn_next, pendingIntentNext);

        buttonPreviousIntent = new Intent(this, MyReceiverPrevious.class);
        PendingIntent pendingIntentPrevious = PendingIntent.getBroadcast(this, 200, buttonPreviousIntent, 0);
        notificationViews.setOnClickPendingIntent(R.id.btn_previous, pendingIntentPrevious);

        buttonCloseIntent = new Intent(this, MyReceiverClose.class);
        PendingIntent pendingIntentClose = PendingIntent.getBroadcast(this, 200, buttonCloseIntent, 0);
        notificationViews.setOnClickPendingIntent(R.id.btn_close, pendingIntentClose);

        // preparar la notificación
        PendingIntent pendingIntentActivity = obtenerPendingIntentAcitivy();
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("PLAYER title")
                .setContentText("PLAYER text")
                .setContent(notificationViews)
                .setContentIntent(pendingIntentActivity)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        notificationManager.notify(200, notification);

    }


    public PendingIntent obtenerPendingIntentAcitivy(){
        PendingIntent pendingIntent = null;

        Intent intent = new Intent(this, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        pendingIntent = PendingIntent.getActivity(this, 200, intent, 0);


        return pendingIntent;
    }

}
