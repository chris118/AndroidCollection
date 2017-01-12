package com.example.xiaopeng.androiddemo.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.xiaopeng.androiddemo.Bean.PTEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by xiaopeng on 16/8/23.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(getContentViewId());

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        initContentView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EvnetDump(PTEvent.UserEvent event){
    }

    // 返回ContentId
    public abstract int getContentViewId();

    // 初始化UI，
    protected abstract void initContentView(Bundle savedInstanceState);
}
