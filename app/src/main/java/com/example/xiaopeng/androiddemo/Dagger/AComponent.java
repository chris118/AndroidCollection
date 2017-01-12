package com.example.xiaopeng.androiddemo.Dagger;

import com.example.xiaopeng.androiddemo.Activity.DaggerActivity;
import com.example.xiaopeng.androiddemo.Activity.MainActivity;

import dagger.Component;

/**
 * Created by xiaopeng on 16/8/10.
 */

@Component(modules = AModule.class)
public interface AComponent{
    void inject(DaggerActivity daggerActivity);
}
