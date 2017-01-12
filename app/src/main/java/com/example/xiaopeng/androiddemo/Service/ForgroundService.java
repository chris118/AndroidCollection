package com.example.xiaopeng.androiddemo.Service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.example.xiaopeng.androiddemo.Activity.ServiceActivity;
import com.example.xiaopeng.androiddemo.R;

public class ForgroundService extends Service {

    public static final String TAG = ForgroundService.class.getSimpleName();
    boolean bStop = false;
    MyBinder binder = new MyBinder();

    public ForgroundService() {
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();

        Intent notificationIntent = new Intent(this, ServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("这是通知的标题")
                .setContentText("这是通知的内容")
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
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
                            Log.d(TAG, "ForgroundService running " + param);
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
