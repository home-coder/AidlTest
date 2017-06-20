package com.example.jiangxiujie.aidltest;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by jiangxiujie on 17-6-20.
 */

public class AIDLService extends Service{

    private static final String TAG = "AIDL_Service";

    public void onCreate() {
        Log.i(TAG,"service create");
    }
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i(TAG,"service onStartCommand id=" + startId);
        return startId;

    }

    public IBinder onBind(Intent t) {
        Log.i(TAG,"service on bind");
        return mBinder;
    }


    public void onDestroy() {
        Log.i(TAG,"service on destroy");
        super.onDestroy();
    }


    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"service on unbind");
        return super.onUnbind(intent);
    }

    public void onRebind(Intent intent) {
        Log.i(TAG,"service on rebind");
        super.onRebind(intent);
    }
    public void onStart(Intent intent, int startId) {
        Log.i(TAG,"service onStart id=" + startId);
    }

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub(){

        @Override
        public int add(int a, int b) throws RemoteException{
            Log.i(TAG,"add a = " + a + "b = " + b);
            return a+b;
        }
    };
}