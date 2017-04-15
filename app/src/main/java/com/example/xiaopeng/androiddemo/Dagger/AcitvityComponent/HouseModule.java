package com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaopeng on 2017/4/11.
 */

@Module
public class HouseModule {

    private String houseName;
    public HouseModule(String houseName){
        this.houseName = houseName;
    }

    @Provides
    public HouseModel provideHouseModel(){
        return new HouseModel(this.houseName );
    }
}
