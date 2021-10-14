package com.example.servicemusictuan6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.servicemusictuan6.MyService.MyBinder;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView btnPrev;
    private ImageView btnPlayAndPause;
    private ImageView btnStop;
    private ImageView btnNext;

    private MyService myService;
    private boolean isBound = false;
    private ServiceConnection connection;
    private boolean isPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.proBarMusic);
        btnPrev = findViewById(R.id.btnPrev);
        btnPlayAndPause = findViewById(R.id.btnPauseAndPlay);
        btnStop = findViewById(R.id.btnStop);
        btnNext = findViewById(R.id.btnNext);

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MyBinder binder = (MyBinder) iBinder;
                myService = binder.getMyService();
                Log.d("system log", "onServiceConnected: cai nay vua chay xong");
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isBound = false;
                Log.d("system log", "onServiceConnected: cai nay vua chay xong 1");
            }
        };

        final Intent intent = new Intent(MainActivity.this, MyService.class);

        btnPlayAndPause.setOnClickListener(view -> {
            Log.d("system log", "onCreate: btn play click" + intent.toString());
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        });

        btnStop.setOnClickListener(view -> {
            if (isBound) {
                unbindService(connection);
                isBound = false;
            }
        });
    }


}