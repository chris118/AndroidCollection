package com.example.xiaopeng.androiddemo.Dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaopeng on 2017/4/11.
 */

// 如果UserModel依赖另外的类，需要提供另外类的Provides(UserTool)
@Module
public class ActivityModule {
    @Provides
    public UserModel provideUserModel(UserTool tool){
        return new UserModel("chris", tool);
    }

    @Provides
    public UserTool provideUserTool(){
        return new UserTool("i am tool");
    }
}