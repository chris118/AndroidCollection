package com.example.xiaopeng.androiddemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalBindService extends Service {
    private  static final String TAG = LocalBindService.class.getSimpleName();
    Boolean bStop = false;

    MyBinder binder = new MyBinder();

    public LocalBindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bStop = true;
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind excute");
        return binder;
    }

    public class MyBinder extends Binder {

        public void startRun(final String param){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!bStop){
                        try {
                            Log.d(TAG, "LocalBindService running " + param);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
