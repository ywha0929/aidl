package com.example.aidltests;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.host.IMyAidlInterface;
import com.example.host.MyData2;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "[APP]";
    IMyAidlInterface myService;
    Connection mConnection;
    static MyData2 mydata = new MyData2(400);
    int input;
    boolean isbind=false;
    class Connection implements ServiceConnection
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: Service Connected?");
            myService = IMyAidlInterface.Stub.asInterface(service);
            Log.d(TAG, "onServiceConnected: Service Connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    }
    /*
    final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: Service Connected?");
            myService = IMyAidlInterface.Stub.asInterface((IBinder)service);
            Log.d(TAG, "onServiceConnected: Service Connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };


     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textview = findViewById(R.id.textview);

        Log.d(TAG, "onCreate: Bind Service");
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() //get data
        {
            @Override
            public void onClick(View v)
            {
                try {
                    if(isbind)
                    {
                        input = myService.getData();

                        textview.append("received data from server : "+input+"\n");
                    }
                    else
                    {
                        textview.append("Service not bound\n");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() //bind
        {
            @Override
            public void onClick(View v)
            {
                mConnection = new Connection();
                Intent intent = new Intent("com.example.host.MY_SERVICE");
                intent.setPackage("com.example.host");
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
//        intent.setClassName("com.example.host","MyService");
                isbind = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                textview.append("Service bound!\n");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() //unbind
        {
            @Override
            public void onClick(View v)
            {
                if(isbind)
                {
                    unbindService(mConnection);
                    isbind = false;
                    textview.append("unbinding service\n");
                }
                else
                {
                    textview.append("Service already not bound\n");
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() //reset
        {
            @Override
            public void onClick(View v)
            {
                try {
                    if(isbind)
                    {
                        myService.resetData();
                    }
                    else
                    {
                        textview.append("service not bound");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() //set parcelable
        {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v)
            {
                try {
                    if(isbind)
                    {

//                        MyData2 mydata = new MyData2(100);
//
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("data", mydata);

                        Log.d(TAG,"data : "+mydata.getnum());
                        //MyData2 mydata2 = (MyData2) bundle.getParcelable("data");
                        Log.d(TAG,"bundle : " + mydata);
                        //myService.setMyData2(mydata);
                        myService.setMyData(bundle);
//                        mydata.setnum(900);
//                        Log.d(TAG,"data : "+mydata.getnum());
//                        myService.setMyData2(mydata);
//                        //Log.d(TAG,"data2 : "+data2.getnum());
                    }
                    else
                    {
                        textview.append("service not bound");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() //set primitive
        {
            @Override
            public void onClick(View v)
            {
                try {
                    if(isbind)
                    {
                        myService.setData(10);
                    }
                    else
                    {
                        textview.append("service not bound");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}