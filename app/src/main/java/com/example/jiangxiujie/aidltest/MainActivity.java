package com.example.jiangxiujie.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AIDL_Example";
    private Button btnBind;
    private Button btnUnBind;
    private Button btnStart;
    private Button btnStop;
    private Button btnAdd;
    private EditText eNum1;
    private EditText eNum2;
    private EditText eNum3;

    IMyAidlInterface mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.i(TAG, "AIDLExample connect service");
            mService = IMyAidlInterface.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.i(TAG, " AIDLExample disconnect service");
            mService = null;
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"AIDLExample onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBind = (Button) findViewById(R.id.btn_bind);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnUnBind = (Button) findViewById(R.id.btn_unbinder);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnAdd = (Button) findViewById(R.id.btn_add);
        eNum1 = (EditText) findViewById(R.id.factorOne);
        eNum2 = (EditText) findViewById(R.id.factorTwo);
        eNum3 = (EditText) findViewById(R.id.factorSum);
        Bundle args = new Bundle();
        final Intent intent = new Intent();
        intent.setAction("com.example.jiangxiujie.aidltest.AIDLService");
        //intent.putExtras(args);
        btnBind.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "btnBind click");
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });
        btnStart.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "btnStart click");
                startService(intent);
            }
        });
        btnUnBind.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "btnUnBind click");
                unbindService(mConnection);
            }
        });
        btnStop.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "btnStop click");
                stopService(intent);
            }
        });
        btnAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "btnAdd click");
                int num1= Integer.parseInt(eNum1.getText().toString());
                int num2= Integer.parseInt(eNum2.getText().toString());
                Log.i(TAG, "num1 = " + num1 + "num2 = " + num2);
                int sum = 0;
                try {
                    sum = mService.add(num1, num2);
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.i(TAG, "result = " + sum);
                eNum3.setText(sum + "");

            }
        });
    }

}
