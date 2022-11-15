package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TimePicker;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    TextClock textClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker=findViewById(R.id.TimePicker);
        textClock=findViewById(R.id.displayTime);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(12);
            timePicker.setMinute(0);
        }


        Timer t=new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String alarm=AlarmTime();
                System.out.println("time @ textClock"+textClock.getText().toString());
                System.out.println("time @ alarm"+alarm);
                if(textClock.getText().toString().equals(alarm)){
                    Intent i=new Intent(MainActivity.this,AlarmNotifier.class);
                    i.putExtra("alarm",alarm);
                    startActivity(i);
                    t.cancel();
                }
            }
        },100,1000);

    }

    private String AlarmTime() {
        String alarm,m,min,hr;
        alarm="12:00";
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
            alarm=hr+":"+min;

            /*System.out.println("My Alarm"+alarm);
*/

        }


        return alarm;
    }
}