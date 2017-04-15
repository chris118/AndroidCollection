package com.example.xiaopeng.androiddemo.Dagger.DependentComponent;

/**
 * Created by xiaopeng on 2017/4/12.
 */

public class DependentModel {
    public DependentModel(DependentModelOther dependentModelOther, String name){
        this.name = name;
        this.dependentModelOther = dependentModelOther;
    }

    public DependentModelOther getDependentModelOther() {
        return dependentModelOther;
    }

    public void setDependentModelOther(DependentModelOther dependentModelOther) {
        this.dependentModelOther = dependentModelOther;
    }

    DependentModelOther dependentModelOther;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
