package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    TextClock textClock;
    Calendar calendar;
    Button setAlarm;
    String alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.TimePicker);
        textClock = findViewById(R.id.displayTime);
        setAlarm=findViewById(R.id.setAlarm);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(12);
            timePicker.setMinute(0);
        }
        Timer t=new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                alarm=AlarmTime();
            }
        },100,1000);


        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel nc=new NotificationChannel("myAlarm","AlarmApp",NotificationManager.IMPORTANCE_HIGH);
                    NotificationManager nm=getSystemService(NotificationManager.class);
                    nm.createNotificationChannel(nc);
                }
                AlarmManager a=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent i=new Intent(MainActivity.this,AlarmReceiver.class);
                System.out.println("alarm :"+alarm);
                i.putExtra("alarm",alarm);
                PendingIntent p=PendingIntent.getBroadcast(MainActivity.this,0,i,PendingIntent.FLAG_IMMUTABLE);
                a.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),p);
            }
        });

    }

    private String AlarmTime() {
        String alarm1,m,min,hr;

        alarm1="12:00";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hours=timePicker.getHour();
            int minutes=timePicker.getMinute();
            if(minutes<10){
                m=Integer.toString(minutes);
                min="0"+m;
            }else{
                m=Integer.toString(minutes);
                min=m;
            }

            hr=Integer.toString(hours);

            alarm1= hr+":"+min;
            calendar=Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hr));
            calendar.set(Calendar.MINUTE, Integer.parseInt(min));
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            System.out.println("Calendar"+calendar.getTimeInMillis());
        }

        return alarm1;
    }
}