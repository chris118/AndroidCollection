package com.example.xiaopeng.androiddemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.orhanobut.logger.Logger;

public class LocalStartService extends Service {
    private final String TAG = LocalStartService.class.getSimpleName();
    Boolean bStop = false;
    int index = 0;

    public LocalStartService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");

        bStop = false;
        index = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!bStop){
                    try {
                        Log.d(TAG, "LocalStartService running");
                        Thread.sleep(1000);
                        index++;
                        if(index == 5){
                            Intent broadcast = new Intent("com.Broadcast.LocalStartServiceBroadcastReciever");
                            sendBroadcast(broadcast,null);

                            bStop = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


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
        return null;
    }
}
