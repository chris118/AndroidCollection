package com.example.xiaopeng.androiddemo.Dagger.AcitvityComponent;

/**
 * Created by xiaopeng on 2017/4/11.
 */

public class UserModel {

    public UserModel(String name, UserTool tool){
        this.name = name;
        this.tool = tool;
    }

    public UserTool getTool() {
        return tool;
    }

    public void setTool(UserTool tool) {
        this.tool = tool;
    }

    private UserTool tool;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}