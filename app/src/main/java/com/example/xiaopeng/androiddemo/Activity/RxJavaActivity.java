package com.example.xiaopeng.androiddemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.xiaopeng.androiddemo.R;
import com.jakewharton.rxbinding.view.RxView;


import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    private final String TAG = RxJavaActivity.class.getSimpleName();

    @BindView(R.id.simple1)Button btn1;
    @BindView(R.id.simple2)Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);

        init();
//        simpleSample1();
//        simpleSample2();
    }



    private void init(){

        RxView.clicks(btn1).subscribe(new Action1<Void>() {
            @Override
            public void call(Void v) {
                simpleSample1();
            }
        });

        RxView.clicks(btn2).subscribe(new Action1<Void>() {
            @Override
            public void call(Void v) {
                simpleSample2();
            }
        });
    }

    private void simpleSample1(){
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "simpleSample1  onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
            }
        };

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("h");
                subscriber.onNext("e");
                subscriber.onNext("l");
                subscriber.onNext("l");
                subscriber.onNext("o");
                subscriber.onCompleted();

            }
        })
        .subscribe(subscriber);
    }


    // Func1 and Action1
    private void simpleSample2(){
        Observable.just(1,2,3,4)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return String.valueOf(integer);
            }
        })
        .subscribe(new Action1<String>() {
            @Override
            public void call(String string) {
                Log.d(TAG, string);
            }
        });
    }
}
