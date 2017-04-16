package com.example.xiaopeng.androiddemo.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xiaopeng.androiddemo.Application.MainApplication;
import com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent.DaggerActivityComponent;
import com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent.HouseModule;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent.DaggerDependentComponent;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent.DependentComponent;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent.DependentModule;
import com.example.xiaopeng.androiddemo.Dagger.SingletonComponent.SingletonComponent;
import com.example.xiaopeng.androiddemo.R;

import javax.inject.Inject;

public class DaggerTestActivity extends AppCompatActivity {

    @Inject
    Context mApplictionContext_1;

    @Inject
    Context mApplictionContext_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        DependentComponent dependentComponent = DaggerDependentComponent
                .builder()
                .dependentModule(new DependentModule("hello")) // 使用dependentModule构造函数传入需要的参数
                .build();

        SingletonComponent singletonComponent = ((MainApplication)getApplication()).getApplicationComponent();
        DaggerActivityComponent.builder()
                .singletonComponent(singletonComponent)
                .dependentComponent(dependentComponent) // 注入依赖component
                .houseModule(new HouseModule("hahaha"))  // 使用HouseModule构造函数传入需要的参数
                //.activityModule(new ActivityModule()) // 如果使用默认构函数，这句可以省略
                .build().inject(this);

        Log.d("DaggerTestActivity", mApplictionContext_1.getPackageName());
    }
}
