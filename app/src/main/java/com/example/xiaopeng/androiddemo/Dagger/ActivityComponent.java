package com.example.xiaopeng.androiddemo.Dagger;

import com.example.xiaopeng.androiddemo.Activity.DaggerActivity;

import dagger.Component;

/**
 * Created by xiaopeng on 2017/4/11.
 */

// 多Module
@Component(modules = {ActivityModule.class, AModule.class, HouseModule.class})
public interface ActivityComponent {
    void inject(DaggerActivity daggerActivity);
}