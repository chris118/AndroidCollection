package com.example.xiaopeng.androiddemo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.xiaopeng.androiddemo.Dagger.DaggerActivityComponent;
import com.example.xiaopeng.androiddemo.Dagger.UserModel;
import com.example.xiaopeng.androiddemo.R;


import com.example.xiaopeng.androiddemo.Dagger.ClassA;
import com.google.gson.Gson;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    private final String TAG = DaggerActivity.class.getSimpleName();

    @Inject
    ClassA a;

    @Inject
    Gson gson;

    @Inject
    UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        DaggerActivityComponent.builder()
                .build().inject(this);

        a.field = "test";
        String aStr = gson.toJson(a);
        Log.d(TAG,"astr = "+aStr);


        ////
    }
}
