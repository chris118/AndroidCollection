package com.example.xiaopeng.androiddemo.Dagger;

import com.example.xiaopeng.androiddemo.Dagger.qualifier.ForBoy;
import com.example.xiaopeng.androiddemo.Dagger.qualifier.ForGirl;
import com.example.xiaopeng.androiddemo.Dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaopeng on 2017/4/11.
 */

// 如果UserModel依赖另外的类，需要提供另外类的Provides(UserTool)
@Module
public class ActivityModule {

    //ActivityComponent 也需要添加 @ActivityScope
    // 如果去掉了 在DaggerActivity里 实力化2个 UserModel 对象 则是不同的两个对象，否则相同
    @ActivityScope
    @Provides
    public UserModel provideUserModel(UserTool tool){
        return new UserModel("chris", tool);
    }

    @ForBoy
    @Provides
    public UserModel provideUserModelBoy(UserTool tool){
        return new UserModel("chris", tool);
    }

    @ForGirl
    @Provides
    public UserModel provideUserModelGirl(UserTool tool){
        return new UserModel("Sophia", tool);
    }

    @Provides
    public UserTool provideUserTool(){
        return new UserTool("i am tool");
    }
}