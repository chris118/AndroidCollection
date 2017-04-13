package com.example.xiaopeng.androiddemo.Dagger;

import javax.inject.Inject;

/**
 * Created by xiaopeng on 2017/4/13.
 */

public class DependentModelOther {

//    @Inject
//    public DependentModelOther(String name){
//        this.name = name;
//    }

    @Inject
    public DependentModelOther(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
