package com.example.xiaopeng.androiddemo.Dagger;


import dagger.Component;

/**
 * Created by xiaopeng on 2017/4/12.
 */


@Component(modules = DependentModule.class)
public interface DependentComponent {

    // 使用DependentModule注入依赖
    DependentModel DependentModel();


    //使用Inject 构造函数注入依赖
    DependentModelOther DependentModelOther();
}
