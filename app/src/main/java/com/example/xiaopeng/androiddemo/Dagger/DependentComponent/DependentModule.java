package com.example.xiaopeng.androiddemo.Dagger.DependentComponent;

import com.example.xiaopeng.androiddemo.Dagger.scope.ActivityOtherScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaopeng on 2017/4/12.
 */

@Module
public class DependentModule {

    private  String mName;
    public DependentModule(String name){

        mName = name;
    }

    @Provides
    @ActivityOtherScope
    DependentModel provideDependentModel(DependentModelOther dependentModelOther) {
        return new DependentModel(dependentModelOther, mName);
    }

    @Provides
    //@ActivityOtherScope
    DependentModelOther provideDependentModelOther() {
        return new DependentModelOther();
    }
}
