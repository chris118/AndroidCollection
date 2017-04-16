package com.example.xiaopeng.androiddemo.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xiaopeng.androiddemo.Application.MainApplication;
import com.example.xiaopeng.androiddemo.Base.BaseActivity;
import com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent.AClass;
import com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent.DaggerActivityComponent;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent.DaggerDependentComponent;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent.DependentComponent;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent.DependentModel;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent.DependentModelOther;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent.DependentModule;
import com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent.HouseModel;
import com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent.HouseModule;
import com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent.UserModel;
import com.example.xiaopeng.androiddemo.Dagger.SingletonComponent.SingletonComponent;
import com.example.xiaopeng.androiddemo.Dagger.qualifier.ForBoy;
import com.example.xiaopeng.androiddemo.Dagger.qualifier.ForGirl;
import com.example.xiaopeng.androiddemo.R;


import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.OnClick;

public class DaggerActivity extends BaseActivity {

    private final String TAG = DaggerActivity.class.getSimpleName();

    // Step 1: 给构造函数添加@Inject，提供注入功能
    @Inject
    AClass AClass_1;
    @Inject
    AClass AClass_2; // Note:  AClass_1 != AClass_2, 如果想两个对象相等，在AClass使用@ActivityScope


    // Step 2: 使用Module， @Provides 提供注入功能
    @Inject
    Gson gson; //使用AModule， @Provides 提供注入功能


    // Step 3: Scope
    // **** AppComponent 依赖了 DependentComponent, 他们不能有相同的 scope
    @Inject
    UserModel userModel1; // 使用 @ActivityScope 然后 userModel1 = userModel2

    @Inject
    UserModel userModel2; // 使用 @ActivityScope 然后 userModel1 = userModel2


    // Step 4:  @Qualifier
    @Inject
    @ForBoy
    UserModel userModelBoy; // 使用  @ForBoy 和  @ForGirl 来区别 ActivityModule 提供的相同类型(UserModel)的不同实例

    @Inject
    @ForGirl
    UserModel userModelGirl;// 使用  @ForBoy 和  @ForGirl 来区别 ActivityModule 提供的相同类型(UserModel)的不同实例


    // Step 5:多Modules演示
    @Inject
    HouseModel houseModel; // HouseModel HouseModule提供， modules = {ActivityModule.class, AModule.class, HouseModule.class}


    // Step 6: @Component(dependencies=DependentComponent.class,modules={xxxx} 演示
    @Inject
    DependentModel dependentModel; //DependentModel由 DependentModule提供，DependentComponent注入DependentModule，
                                    // DependentComponent 提供给 ActivityComponent 依赖
    DependentModel dependentModel_2;
    DependentModelOther dependentModel_Other;

    @Inject
    Context mApplictionContext_1;

    @Inject
    Context mApplictionContext_2;

    @Override
    public int getContentViewId() {
        return R.layout.activity_dagger;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
//   super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

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


        //dependentModel_Other 在DependentModule里没有标记 @ActivityOtherScope，所以dependentModel_Other != dependentModel.dependentModelOther
        dependentModel_2 = dependentComponent.DependentModel();
        dependentModel_Other = dependentComponent.DependentModelOther();

        Button btn = (Button)this.findViewById(R.id.daggerBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, dependentModel.getName());
                Intent intent = new Intent();
                intent.setClass(DaggerActivity.this, DaggerTestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.daggerBtn)
    void OnBtnClicked(){
        Log.d(TAG, dependentModel.getName());
    }
}
