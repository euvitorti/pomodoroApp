package com.example.pomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textTimer;
    CountDownTimer timer;
    Button buttonStartTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTimer = findViewById(R.id.textViewTimer);
        buttonStartTimer = findViewById(R.id.buttonStart);

        buttonStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime();
            }

            private void startTime() {
                timer = new CountDownTimer(10000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long hours = (millisUntilFinished / 1000) / 3600;
                        long minutes = ((millisUntilFinished / 1000) % 3600) / 60;
                        long seconds = (millisUntilFinished / 1000) % 60;
                        String timeFormated = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours,minutes,seconds);
                        textTimer.setText(timeFormated);
                    }

                    @Override
                    public void onFinish() {
                        textTimer.setText("00:00:00");
                        Toast.makeText(MainActivity.this,"Time's up",Toast.LENGTH_SHORT).show();
                        MediaPlayer alarm = MediaPlayer.create(MainActivity.this, R.raw.alarmClock);
                        alarm.start();
                    }
                }.start();
            }
        });
    }
}