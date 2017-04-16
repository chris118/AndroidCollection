package com.example.xiaopeng.androiddemo.Dagger.DependentComponent;


import com.example.xiaopeng.androiddemo.Dagger.scope.ActivityOtherScope;

import dagger.Component;

/**
 * Created by xiaopeng on 2017/4/12.
 */


//@ActivityOtherScope // **** AppComponent 依赖了 DependentComponent, 他们不能有相同的 scope
@Component(modules = DependentModule.class)
public interface DependentComponent {

    // 使用DependentModule注入依赖
    DependentModel DependentModel();


    //使用Inject 构造函数注入依赖 这里不用向外暴露类型
    DependentModelOther DependentModelOther();
}
