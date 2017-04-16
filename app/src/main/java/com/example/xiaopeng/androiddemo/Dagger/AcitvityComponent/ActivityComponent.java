package com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent;

import com.example.xiaopeng.androiddemo.Activity.DaggerActivity;
import com.example.xiaopeng.androiddemo.Activity.DaggerTestActivity;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent.DependentComponent;
import com.example.xiaopeng.androiddemo.Dagger.SingletonComponent.SingletonComponent;
import com.example.xiaopeng.androiddemo.Dagger.scope.ActivityScope;

import dagger.Component;

/**
 * Created by xiaopeng on 2017/4/11.
 */

// 多Module
@ActivityScope
@Component(dependencies={DependentComponent.class, SingletonComponent.class}, modules = {ActivityModule.class, AModule.class, HouseModule.class})
public interface ActivityComponent {
    void inject(DaggerActivity daggerActivity);
    void inject(DaggerTestActivity daggerTestActivity);
}