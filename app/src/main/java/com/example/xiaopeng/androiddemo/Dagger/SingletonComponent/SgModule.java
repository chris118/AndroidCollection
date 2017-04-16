package com.example.xiaopeng.androiddemo.Dagger.SingletonComponent;

import android.app.Application;
import android.content.Context;

import com.example.xiaopeng.androiddemo.Dagger.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaopeng on 2017/4/16.
 */


@Module
public class SgModule {

    private  Application mAppliction;
    public SgModule(Application application){
        mAppliction = application;
    }

    @AppScope
    @Provides
    public Context provideApplicationContext(){
        return mAppliction.getApplicationContext();
    }
}
