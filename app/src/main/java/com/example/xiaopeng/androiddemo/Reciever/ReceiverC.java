package com.example.xiaopeng.androiddemo.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ReceiverC extends BroadcastReceiver {
    public ReceiverC() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,
                "ReceiverC", Toast.LENGTH_SHORT).show();

        Bundle bundle = getResultExtras(true);
        String msg = bundle.getString("msg");
        Toast.makeText(context,
                msg, Toast.LENGTH_SHORT).show();
    }
}
