package com.example.xiaopeng.androiddemo.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

public class ReceiverB extends BroadcastReceiver {
    public ReceiverB() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,
                "ReceiverB", Toast.LENGTH_SHORT).show();
    }
}
