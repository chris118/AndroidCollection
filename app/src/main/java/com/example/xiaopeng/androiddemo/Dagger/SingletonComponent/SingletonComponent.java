package com.example.xiaopeng.androiddemo.Dagger.SingletonComponent;

import android.content.Context;

import com.example.xiaopeng.androiddemo.Activity.DaggerActivity;
import com.example.xiaopeng.androiddemo.Dagger.scope.AppScope;

import dagger.Component;

/**
 * Created by xiaopeng on 2017/4/15.
 */
@AppScope
@Component(modules = SgModule.class)
public interface SingletonComponent {

    Context ApplictionContext();
}
