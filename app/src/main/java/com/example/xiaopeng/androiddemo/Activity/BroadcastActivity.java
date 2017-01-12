package com.example.xiaopeng.androiddemo.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.xiaopeng.androiddemo.Base.BaseActivity;
import com.example.xiaopeng.androiddemo.LogUtil.LogUtil;
import com.example.xiaopeng.androiddemo.R;
import com.example.xiaopeng.androiddemo.Reciever.DynamicalReceiver;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;

public class BroadcastActivity extends BaseActivity {

    private  final String TAG  = BroadcastActivity.class.getSimpleName();

    DynamicalReceiver dynamical_reciever;

    @Override
    public int getContentViewId() {
        return R.layout.activity_broadcast;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(dynamical_reciever);
}

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("com.example.xiaopeng.dynamicalbroadcast");
        dynamical_reciever = new DynamicalReceiver();
        registerReceiver(dynamical_reciever, intentFilter );
    }

    @OnClick(R.id.send_broadcast)
    void SendBroadcastClicked(){
        Intent broadcastIntent = new Intent("com.example.xiaopeng.dynamicalbroadcast");
        sendBroadcast(broadcastIntent);
    }

    @OnClick(R.id.send_broadcast2)
    void SendBroadcast2Clicked(){
        Intent broadcastIntent = new Intent("com.example.xiaopeng.staticbroadcast");
        sendBroadcast(broadcastIntent);
    }

    @OnClick(R.id.unorder_bc_btn)
    void UnOrderBroadcastClicked(){
        Intent bi = new Intent("com.example.xiaopeng.unorder_broadcast");
        sendBroadcast(bi);
    }

    @OnClick(R.id.order_broadcast_btn)
    void OrderBroadcastClicked(){
        Intent bi = new Intent("com.example.xiaopeng.order_broadcast");
        sendOrderedBroadcast(bi, null);
    }
}
