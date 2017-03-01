package com.example.xiaopeng.androiddemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import android.support.annotation.Nullable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiaopeng on 13/01/2017.
 */

public class AIDLService extends Service {

    private  final String TAG = AIDLService.class.getSimpleName();

    private List<Book> mBooks = new ArrayList<>();

    private RemoteCallbackList<ISimpleCallback> mCallbacks = new RemoteCallbackList<>();

    private BookManager.Stub bookManager = new BookManager.Stub() {

        @Override
        public List<Book> getBooks() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            // TODO
        }

        @Override
        public void startCount(final int to, final ISimpleCallback callback) throws RemoteException {
            Thread t = new Thread()
            {
                /*睡！ */
                public void preciseSleep(long millis)
                {
                    long endTime = System.currentTimeMillis() + millis;

                    do {
                        try
                        {
                            Thread.sleep(endTime - System.currentTimeMillis());
                        }
                        catch (InterruptedException e)
                        {}
                    } while (System.currentTimeMillis() < endTime);
                }

                public void run()
                {
                    for (int i = 1; i <= to; i++)
                    {
                        preciseSleep(1000);

                        //simpleCallbackToClient(callback, i);

                        callbackToClient(i);
                    }
                }
            };

            t.start();
        }

        private void simpleCallbackToClient(ISimpleCallback callback, int counter){
            try
            {
                // simple callback
                callback.handleCount(counter);
            }
            // AIDL中唯一可能抛出的异常
            catch (DeadObjectException e)
            {
                Log.d(TAG, "Dead peer, aborting...", e);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private void callbackToClient(int counter){
            int count = mCallbacks.beginBroadcast();
            try {
                for (int i = 0; i < count; i++) {
                    Log.d(TAG, "callbackToClient...");
                    mCallbacks.getBroadcastItem(i).handleCount(counter);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "E/onUpdateMetaDatas", e);
            } finally {
                mCallbacks.finishBroadcast();
            }
        }

        @Override
        public void registerCallback(int id, ISimpleCallback callback) throws RemoteException {
            mCallbacks.register(callback);
        }

        @Override
        public void unregisterCallback(int id, ISimpleCallback callback) throws RemoteException {
            mCallbacks.unregister(callback);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("Android开发艺术探索");
        book.setPrice(28);
        mBooks.add(book);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 取消掉所有的回调
        mCallbacks.kill();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }
}
