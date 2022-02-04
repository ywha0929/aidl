package com.example.host;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;
public class MyService extends Service {
    public static final String TAG = "[MyService]";
    static int num;
    static MyData2 mydata = new MyData2(30);
    public IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int getData() throws RemoteException {
            return num++;
        }
        public void resetData() throws RemoteException
        {
            Log.d(TAG,"reset data running");
            num = 0;
            return;
        }
        public void setData(int i) throws RemoteException
        {
            Log.d(TAG,"set data running");
            num = i;
            return;
        }

        @Override
        public void setMyData(Bundle bundle) throws RemoteException
        {
            Log.d(TAG,"setmydata running");
            bundle.setClassLoader(getClass().getClassLoader());
            MyData2 data = (MyData2) bundle.getParcelable("data");

            Log.d(TAG,"received data : "+data.getnum());
            Log.d(TAG,"class name test : " + data.getClass().toString().equals("class com.example.host.MyData2"));

            num = data.getnum();
            return;
        }

        @Override
        public void setMyData2(MyData2 data) throws RemoteException {

            Log.d(TAG,""+data.getnum());
            num=data.getnum();
            return;
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyService : onBind");

        //Toast.makeText(getApplicationContext(),"onBind",Toast.LENGTH_SHORT).show();
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService : onCreate0");

        //Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_SHORT).show();
        num = 0;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "MyService : onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService : onStartCommand");

        //Toast.makeText(getApplicationContext(),"onStarted",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

}