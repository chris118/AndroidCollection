package com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent;

/**
 * Created by xiaopeng on 2017/4/11.
 */

public class HouseModel {
    public HouseModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
