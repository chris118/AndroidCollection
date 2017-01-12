package com.example.xiaopeng.androiddemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xiaopeng.androiddemo.Dagger.ClassA;
import com.example.xiaopeng.androiddemo.Dagger.DaggerAComponent;
import com.example.xiaopeng.androiddemo.R;
import com.google.gson.Gson;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    private final String TAG = DaggerActivity.class.getSimpleName();

    @Inject
    ClassA a;

    @Inject
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        DaggerAComponent.builder().build().inject(this);

        a.field = "test";
        String aStr = gson.toJson(a);
        Log.d(TAG,"astr = "+aStr);
    }
}
