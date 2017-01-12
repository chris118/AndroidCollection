package com.example.xiaopeng.androiddemo.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ReceiverD extends BroadcastReceiver {
    public ReceiverD() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,
                "ReceiverD", Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putString("msg", "i am from ReceiverD");
        setResultExtras(bundle);
    }
}
