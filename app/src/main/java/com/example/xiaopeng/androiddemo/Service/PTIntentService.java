package com.example.xiaopeng.androiddemo.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class PTIntentService extends IntentService {
    private static final String TAG = PTIntentService.class.getSimpleName();

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String TASK_1 = "com.example.xiaopeng.androiddemo.Service.action.task1";
    public static final String TASK_2 = "com.example.xiaopeng.androiddemo.Service.action.task2";

    public static final String EXTRA_PARAM1 = "com.example.xiaopeng.androiddemo.Service.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.xiaopeng.androiddemo.Service.extra.PARAM2";

    public PTIntentService() {
        super("PTIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (TASK_1.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionTask1(param1, param2);
            } else if (TASK_2.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionTask2(param1, param2);
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionTask1(String param1, String param2) {
        Log.d(TAG, "handleActionTask1" + param1 + param2);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionTask2(String param1, String param2) {

        Log.d(TAG, "handleActionTask2" + param1 + param2);

    }


}
