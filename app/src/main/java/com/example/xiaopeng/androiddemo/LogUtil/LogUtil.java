package com.example.xiaopeng.androiddemo.LogUtil;

import android.os.Environment;
import com.socks.library.KLog;

import java.io.File;

/**
 * Created by xiaopeng on 16/8/22.
 */
public class LogUtil{

    private static File logDir = null;
    public static void init(boolean isShowLog) {

        KLog.init(isShowLog);

        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }

        File destDir = new File(Environment.getExternalStorageDirectory().getPath() + "/AndroidDemo/Logs");
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        logDir = destDir;
    }

    public static void i(Object msg) {
        KLog.i(msg);
    }

    public static void e(Object msg) {

        KLog.e(msg);
        file(msg);
    }

    public static void file(Object msg){
        if(logDir != null){
            KLog.file(logDir, msg);
        }
    }
}
