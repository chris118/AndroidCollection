package com.example.xiaopeng.androiddemo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.xiaopeng.androiddemo.Dagger.ActivityModule;
import com.example.xiaopeng.androiddemo.Dagger.ConstructorInject;
import com.example.xiaopeng.androiddemo.Dagger.DaggerDependentComponent;
import com.example.xiaopeng.androiddemo.Dagger.DependentComponent;
import com.example.xiaopeng.androiddemo.Dagger.DependentModel;
import com.example.xiaopeng.androiddemo.Dagger.DaggerActivityComponent;
import com.example.xiaopeng.androiddemo.Dagger.DependentModelOther;
import com.example.xiaopeng.androiddemo.Dagger.DependentModule;
import com.example.xiaopeng.androiddemo.Dagger.HouseModel;
import com.example.xiaopeng.androiddemo.Dagger.HouseModule;
import com.example.xiaopeng.androiddemo.Dagger.UserModel;
import com.example.xiaopeng.androiddemo.Dagger.qualifier.ForBoy;
import com.example.xiaopeng.androiddemo.Dagger.qualifier.ForGirl;
import com.example.xiaopeng.androiddemo.R;


import com.google.gson.Gson;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    private final String TAG = DaggerActivity.class.getSimpleName();

    @Inject
    ConstructorInject constructorInject;

    @Inject
    Gson gson;

    @Inject
    UserModel userModel;

    @Inject
    UserModel userModel2;

    @Inject
    @ForBoy
    UserModel userModelBoy;

    @Inject
    @ForGirl
    UserModel userModelGirl;


    @Inject
    HouseModel houseModel;

    @Inject
    DependentModel dependentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        DependentComponent dependentComponent = DaggerDependentComponent
                                                .builder()
                                                .dependentModule(new DependentModule("hello"))
                                                .build();
        DaggerActivityComponent.builder()
                .dependentComponent(dependentComponent)
                .houseModule(new HouseModule("hahah"))
                .activityModule(new ActivityModule())
                .build().inject(this);

//        DependentModel model1 = dependentComponent.DependentModel();
//
//        DependentModelOther model2 = dependentComponent.DependentModelOther();


        constructorInject.field = "test";
        String aStr = gson.toJson(constructorInject);
        Log.d(TAG,"astr = "+aStr);
    }
}
