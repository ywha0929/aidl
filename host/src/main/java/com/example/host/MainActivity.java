package com.example.host;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "[Host]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity : onCreate0");
        startService(new Intent(this,MyService.class));
        Log.d(TAG, "MainActivity : onCreate1");
        /*
        SystemClock.sleep(1000);
        mConnection = new Connection();
        Intent intent = new Intent("com.example.host.MY_SERVICE");
        intent.setPackage("com.example.host");
        intent.setClassName("com.example.host","MyService");
        Log.d(TAG,"temp: " + bindService(intent, mConnection, Context.BIND_AUTO_CREATE));
         */
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}