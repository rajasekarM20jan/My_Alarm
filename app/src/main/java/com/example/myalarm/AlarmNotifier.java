package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextClock;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmNotifier extends AppCompatActivity {
    TextClock textClock;
    Button stop;
    Ringtone r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_notifier);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O_MR1){
            setShowWhenLocked(true);
            setTurnScreenOn(true);
            KeyguardManager k=(KeyguardManager) getSystemService(this.KEYGUARD_SERVICE);
            k.requestDismissKeyguard(this,null);
        }else {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        textClock=findViewById(R.id.clockDisplay);
        stop=findViewById(R.id.stopButton);
        r= RingtoneManager.getRingtone(AlarmNotifier.this
                ,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        r.play();



        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r.stop();
                finish();
            }
        });
    }
}