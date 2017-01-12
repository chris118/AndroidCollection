package com.example.xiaopeng.androiddemo.Bean;

/**
 * Created by xiaopeng on 2016/10/17.
 */

public class PTEvent {
    public static class UserEvent {
        public String username ;

        public UserEvent(String name){
            username = name;
        }
    }
    public static class CountEvent {
        public int count;
    }
}
