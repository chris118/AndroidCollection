package com.example.xiaopeng.androiddemo.Application;

import android.app.Application;
import android.content.Context;

import com.example.xiaopeng.androiddemo.BuildConfig;
import com.example.xiaopeng.androiddemo.HttpService.RetrofitManager;
import com.example.xiaopeng.androiddemo.LogUtil.LogUtil;
import com.example.xiaopeng.androiddemo.Utils.AppContextUtil;
import com.example.xiaopeng.androiddemo.gen.DaoMaster;
import com.example.xiaopeng.androiddemo.gen.DaoSession;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;

/**
 * Created by xiaopeng on 16/6/16.
 */
public class MainApplication extends Application {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;
    private DaoSession daoSession;

    @Inject
    Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        Logger.init("Android Demo")                 // default PRETTYLOGGER or use just init()
                .methodCount(2)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(0)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional

        AppContextUtil.init(this);

        // build the Retrofit Manager
        RetrofitManager.getInstance().builder();

        // init log
        LogUtil.init(BuildConfig.LOG_DEBUG);

        // init GreenDAO

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return AppContextUtil.getInstance();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
