package com.example.xiaopeng.androiddemo.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.Base.BaseActivity;
import com.example.xiaopeng.androiddemo.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

public class TimerActivity extends BaseActivity {

    ScheduledExecutorService mExcutor;
    ScheduledFuture scheduledFuture;
    int counter = 0;

    @BindView(R.id.t_tv)
    TextView info;

    class NewTask implements  Runnable{

        @Override
        public void run() {
            counter++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("TimerActivity", Thread.currentThread().getName());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    info.setText(String.valueOf(counter));
                }
            });
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_timer;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        mExcutor = Executors.newScheduledThreadPool(1);
    }

    @OnClick(R.id.btn1)
    public void btn1_Clicked(){
        scheduledFuture =  mExcutor.schedule(new NewTask(), 1000, TimeUnit.MILLISECONDS);

//        scheduledFuture.cancel(true);
    }

    @OnClick(R.id.btn2)
    public void btn2_Clicked(){ //每隔3秒打印一次
        scheduledFuture = mExcutor.scheduleAtFixedRate(new NewTask(), 0, 3000, TimeUnit.MILLISECONDS);
    }

    @OnClick(R.id.t_btn3)
    public void btn3_Clicked(){ //每隔4秒打印一次
        scheduledFuture = mExcutor.scheduleWithFixedDelay(new NewTask(), 0, 3000, TimeUnit.MILLISECONDS);
    }

    @OnClick(R.id.t_stop)
    public void btnStop_Clicked(){
        scheduledFuture.cancel(true);
    }
}
