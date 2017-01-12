package com.example.xiaopeng.androiddemo.Bean;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by xiaopeng on 16/7/27.
 */
public class MyUsers {
    public static final String AUTHORITY  = "com.example.xiaopeng.androiddemo.PTContentProvider";

    // BaseColumn类中已经包含了 _id字段
    public static final class User implements BaseColumns {
        public static final Uri CONTENT_URI  = Uri.parse("content://com.example.xiaopeng.androiddemo.PTContentProvider");
        // 表数据列
        public static final String  USER_NAME  = "USER_NAME";
    }
}

