package com.example.xiaopeng.androiddemo.Dagger;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by xiaopeng on 16/8/10.
 */
public class ClassA {

    final static String TAG = Class.class.getSimpleName();

    @Inject
    public  ClassA(){
        Log.d(TAG, "Class A Create!!!");
    }

    public String field;

    public String getFiled() {
        return field;
    }

    public void setFiled(String filed) {
        this.field = filed;
    }
}


