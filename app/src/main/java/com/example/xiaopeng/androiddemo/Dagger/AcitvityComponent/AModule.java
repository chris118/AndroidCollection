package com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaopeng on 16/8/10.
 */

@Module
public class AModule {
    @Provides
    public Gson provideGson(){
        return new Gson();
    }

//    @Provides
//    public AClass provideClassA(){
//        return new AClass();
//    }
}
