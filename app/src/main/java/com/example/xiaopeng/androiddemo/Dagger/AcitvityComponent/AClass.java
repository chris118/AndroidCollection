package com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent;

import android.util.Log;

import com.example.xiaopeng.androiddemo.Dagger.scope.ActivityScope;

import javax.inject.Inject;

/**
 *
 * @author xiaopeng
 *
 *  create at 2017/4/10 下午12:49
 *
 * 定义一个类AClass 并在其构造函数用@Inject标注，表示告诉Dagger2这是我的构造函数，如果有地方要用到我，就用该构造函数对我实例化；
 */

@ActivityScope
public class AClass {

    final static String TAG = Class.class.getSimpleName();

    @Inject
    public AClass(){
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


