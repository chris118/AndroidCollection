package com.example.xiaopeng.androiddemo.JNIClass;

/**
 * Created by xiaopeng on 16/6/22.
 */
public class NdkJniUtils {
    static {
        System.loadLibrary("emJniLibName"); //defaultConfig.ndk.moduleName
    }

    public native String getCLanguageString();
}
