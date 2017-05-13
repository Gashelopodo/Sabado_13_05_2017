package com.gashe.appforegroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyServiceForeground extends Service {

    private static MediaPlayer mediaPlayer;

    private static void play(){

    }

    private static void stop(){
        mediaPlayer.stop();
    }

    public MyServiceForeground() {

    }

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(getClass().getCanonicalName(), "SERVICIO INICIADO");

        String action_intent = intent.getAction();

        if(action_intent.equals("START")){
            //quiero lanzar el servicio


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
                    .setContent(notificationViews)
                    .setContentIntent(pendingIntentActivity)
                    .build();

            startForeground(55, notification);
            play();

        }else{
            //quiero detener el servicio
            Toast.makeText(this, "PARANDO EL SERVICIO", Toast.LENGTH_LONG).show();
            stopForeground(true);
            stopSelf(); // detengo el servicio
            stop();
        }

        return START_STICKY;
    }

    public PendingIntent obtenerPendingIntentAcitivy(){
        PendingIntent pendingIntent = null;

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        pendingIntent = PendingIntent.getActivity(this, 200, intent, 0);


        return pendingIntent;
    }


}
