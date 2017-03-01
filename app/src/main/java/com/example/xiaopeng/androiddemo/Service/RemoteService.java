package com.example.xiaopeng.androiddemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.SubMenu;

public class RemoteService extends Service {
    public RemoteService() {
    }

    private  static final String TAG = RemoteService.class.getSimpleName();
    Boolean bStop = false;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate() executed");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (!bStop){
//                    try {
//                        Log.d(TAG,  "RemoteService started: " + "   " + String.valueOf(System.currentTimeMillis()));
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

//        while (!bStop){
//            try {
//                Log.d(TAG,  "RemoteService started: " + "   " + String.valueOf(System.currentTimeMillis()));
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
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
        return mBinder;
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {

        @Override
        public void doSomething(final String str) throws RemoteException {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!bStop){
                        try {
                            Log.d(TAG,  "RemoteService running: " + str + "   " + String.valueOf(System.currentTimeMillis()));
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

//            while (!bStop){
//                try {
//                    Log.d(TAG,  "RemoteService running: " + str + "   " + String.valueOf(System.currentTimeMillis()));
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    };
}
