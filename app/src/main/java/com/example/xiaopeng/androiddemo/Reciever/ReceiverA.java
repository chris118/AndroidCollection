package com.example.xiaopeng.androiddemo.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

public class ReceiverA extends BroadcastReceiver {
    public ReceiverA() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,
                "ReceiverA", Toast.LENGTH_SHORT).show();
    }
}
