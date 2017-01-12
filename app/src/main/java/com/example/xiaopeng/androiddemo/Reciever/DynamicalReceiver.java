package com.example.xiaopeng.androiddemo.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

import com.socks.library.KLog;

public class DynamicalReceiver extends BroadcastReceiver {
    private  final String TAG  = DynamicalReceiver.class.getSimpleName();

    public DynamicalReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        KLog.i(TAG, "DynamicalReceiver " + intent.getAction());
        Toast.makeText(context,
                "DynamicalReceiver", Toast.LENGTH_SHORT).show();
    }
}
