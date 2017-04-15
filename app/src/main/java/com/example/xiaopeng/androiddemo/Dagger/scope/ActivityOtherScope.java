package com.example.xiaopeng.androiddemo.Dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by xiaopeng on 2017/4/15.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityOtherScope {
}