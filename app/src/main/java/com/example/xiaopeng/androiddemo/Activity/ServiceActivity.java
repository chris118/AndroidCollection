package com.example.xiaopeng.androiddemo.Activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.R;
import com.example.xiaopeng.androiddemo.Service.ForgroundService;
import com.example.xiaopeng.androiddemo.Service.IMyAidlInterface;
import com.example.xiaopeng.androiddemo.Service.LocalBindService;
import com.example.xiaopeng.androiddemo.Service.LocalStartService;
import com.example.xiaopeng.androiddemo.Service.PTIntentService;
import com.example.xiaopeng.androiddemo.Service.RemoteService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends AppCompatActivity {

    private final String TAG = ServiceActivity.class.getSimpleName();
    private LocalStartServiceBroadcastReciever reciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        reciever = new LocalStartServiceBroadcastReciever();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.Broadcast.LocalStartServiceBroadcastReciever");
        registerReceiver(reciever, filter);
    }

    @Override
    protected void onPause(){
        unregisterReceiver(reciever);
        super.onPause();
    }

    @BindView(R.id.info)
    TextView infoView;

    @OnClick(R.id.start_local_service)
    public void onStartLocalServiceClicked(){
        Intent startIntent = new Intent(this, LocalStartService.class);
        startService(startIntent);
    }

    @OnClick(R.id.stop_local_service)
    public void onStopLocalServiceClicked(){
        Intent stopIntent = new Intent(this, LocalStartService.class);
        stopService(stopIntent);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalBindService.MyBinder binder = (LocalBindService.MyBinder)service;
            binder.startRun("bind service");
        }

        /*
        该方法只在Service 被破坏了或者被杀死的时候调用. 例如, 系统资源不足, 要关闭一些Services,
        刚好连接绑定的 Service 是被关闭者之一,  这个时候onServiceDisconnected() 就会被调用。
        */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected excute");
        }
    };

    @OnClick(R.id.bind_service)
    public void onBindLocalServiceClicked(){
        Intent bindIntent = new Intent(this, LocalBindService.class);
        bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.unbind_service)
    public void onUnbindLocalServiceClicked(){
        Intent unBindIntent = new Intent(this, LocalBindService.class);
        unbindService(connection);
    }

    private ServiceConnection connection_forground_service = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ForgroundService.MyBinder binder = (ForgroundService.MyBinder)service;
            binder.startRun("forground");
        }

        /*
        该方法只在Service 被破坏了或者被杀死的时候调用. 例如, 系统资源不足, 要关闭一些Services,
        刚好连接绑定的 Service 是被关闭者之一,  这个时候onServiceDisconnected() 就会被调用。
        */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected excute");
        }
    };

    @OnClick(R.id.start_forground_service)
    public  void onStartForgroundService(){
        Intent bindIntent = new Intent(this, ForgroundService.class);
        bindService(bindIntent, connection_forground_service, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.stop_forground_service)
    public void onStopForgroundService(){
        Intent unBindIntent = new Intent(this, ForgroundService.class);
        unbindService(connection_forground_service);

    }

    @OnClick(R.id.start_intent_service)
    public  void onStartIntentService(){
        Intent intent = new Intent(this, PTIntentService.class);
        intent.setAction(PTIntentService.TASK_1);
        intent.putExtra(PTIntentService.EXTRA_PARAM1, "_task1_hello");
        intent.putExtra(PTIntentService.EXTRA_PARAM2, "_task1_world");
        startService(intent);

        Intent intent2 = new Intent(this, PTIntentService.class);
        intent2.setAction(PTIntentService.TASK_2);
        intent2.putExtra(PTIntentService.EXTRA_PARAM1, "_task2_hello");
        intent2.putExtra(PTIntentService.EXTRA_PARAM2, "_task2_world");
        startService(intent2);
    }

    @OnClick(R.id.stop_intent_service)
    public void onStopIntentService(){
        Intent stopIntent = new Intent(this, PTIntentService.class);
        stopService(stopIntent);

    }

    private ServiceConnection connection_remote_service = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAidlInterface myAIDLService = IMyAidlInterface.Stub.asInterface(service);
            try {
                myAIDLService.doSomething("bla bla");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        /*
        该方法只在Service 被破坏了或者被杀死的时候调用. 例如, 系统资源不足, 要关闭一些Services,
        刚好连接绑定的 Service 是被关闭者之一,  这个时候onServiceDisconnected() 就会被调用。
        */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected excute");
        }
    };


    @OnClick(R.id.start_remote_service)
    public  void onStartRemoteService(){
//        Intent bindIntent = new Intent(this, RemoteService.class);
//        bindService(bindIntent, connection_remote_service, BIND_AUTO_CREATE);

        Intent startIntent = new Intent(this, RemoteService.class);
//        startService(startIntent);
        bindService(startIntent, connection_remote_service, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.stop_remote_service)
    public void onStopRemoteService(){
        Intent unBindIntent = new Intent(this, RemoteService.class);
        unbindService(connection_remote_service);
    }


    public class LocalStartServiceBroadcastReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
//            Toast.makeText(context, "静态:"+action, 1000).show();

            infoView.setText("静态:"+action);
        }
    }
}
