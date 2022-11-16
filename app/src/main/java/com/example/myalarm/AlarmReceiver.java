package com.example.myalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.provider.AlarmClock;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("qasdfgvbhnm,nbvfcxzaxcvbnm,mnbvfcxsza");
        Intent i=new Intent(context,AlarmNotifier.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent myIntent=PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder nb=new NotificationCompat.Builder(context,"myAlarm");
        nb.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                .setPriority(NotificationCompat.PRIORITY_HIGH).setAutoCancel(true);
        nb.setSmallIcon(R.drawable.alarm)
                .setContentText("Alarm Ringing").setContentIntent(myIntent);
        NotificationManagerCompat nm= NotificationManagerCompat.from(context);
        nm.notify(1,nb.build());

    }
}
