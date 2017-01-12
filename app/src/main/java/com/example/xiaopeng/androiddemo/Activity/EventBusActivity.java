package com.example.xiaopeng.androiddemo.Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.xiaopeng.androiddemo.Base.BaseActivity;
import com.example.xiaopeng.androiddemo.Bean.PTEvent;
import com.example.xiaopeng.androiddemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class EventBusActivity extends BaseActivity {

    @BindView(R.id.infotext)
    TextView textView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_event_bus;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @OnClick(R.id.post_btn)
    public void listenerClicked() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PTEvent.UserEvent event = new PTEvent.UserEvent("hello world");
                EventBus.getDefault().post(event);
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(PTEvent.UserEvent event){
        textView.setText(event.username);
    }
}
