package com.example.xiaopeng.androiddemo.Reciever;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.Activity.MainActivity;
import com.example.xiaopeng.androiddemo.R;
import com.socks.library.KLog;

public class StaticReceiver extends BroadcastReceiver {
    private  final String TAG  = StaticReceiver.class.getSimpleName();
    public StaticReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        KLog.i(TAG, "StaticReceiver " + intent.getAction());
//
//        Toast.makeText(context,rr
//                "StaticReceiver", Toast.LENGTH_SHORT).show();

//        launchMainActivity(context);

        sendNotification(context, "hello world");
    }

    /**
     *
     * @author xiaopeng
     *
     *  create at 11/01/2017 7:10 PM
     *
     *  直接启动MainActivity
     */

    private void launchMainActivity(Context context){
            Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    /**
     *
     * @author xiaopeng
     *
     *  create at 11/01/2017 7:07 PM
     *
     * 发送广播 启动应用
     */

    private void sendNotification(Context context, String j){
        //【1】获取Notification 管理器的参考
        NotificationManager notifyMgr= (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //【2】设置通知。PendingIntent表示延后触发，是在用户下来状态栏并点击通知时触发，触发时PendingIntent发送intent
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Note: need FLAG_ACTIVITY_NEW_TASK
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.drawable.logo)
                .setTicker("Hello")
                .setContentTitle("Title")
                .setContentText("Content text")
                .setContentIntent(pi)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL; //点击后删除，如果是FLAG_NO_CLEAR则不删除，FLAG_ONGOING_EVENT用于某事正在进行，例如电话，具体查看参考。
        //【3】发送通知到通知管理器。第一个参数是这个通知的唯一标识，通过这个id可以在以后cancel通知，更新通知（发送一个具有相同id的新通知）。这个id在应用中应该是唯一的。
        notifyMgr.notify(1000, notification);
    }
}
