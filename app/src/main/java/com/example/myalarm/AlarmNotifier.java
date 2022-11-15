package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmNotifier extends AppCompatActivity {
    TextClock textClock;
    String alarm;
    Button stop;
    Ringtone r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_notifier);
        textClock=findViewById(R.id.clockDisplay);
        stop=findViewById(R.id.stopButton);
        Intent i=getIntent();
        alarm=i.getStringExtra("alarm");
        r= RingtoneManager.getRingtone(AlarmNotifier.this
                ,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        Timer t=new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("textClocK: "+textClock.getText().toString());
                System.out.println("alarm : "+alarm);
                if(textClock.getText().toString().equals(alarm)){
                    r.play();
                }
                else{
                    r.stop();
                }
            }
        },0,1000);


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r.stop();
                t.cancel();
                Intent i=new Intent(AlarmNotifier.this,MainActivity.class);
                startActivity(i);
            }
        });




    }
}