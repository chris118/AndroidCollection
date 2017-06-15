package com.example.xiaopeng.androiddemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.xiaopeng.androiddemo.R;
import com.jakewharton.rxbinding.view.RxView;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    private final String TAG = RxJavaActivity.class.getSimpleName();

    @BindView(R.id.simple1)Button btn1;
    @BindView(R.id.simple2)Button btn2;

    private List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);

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

        init();
    }

    private void init(){
        just();
        from();
        zip();
        flatMap();
        rang();
        distinct();
        merg();
    }

    private void from(){
        Observable.from(words).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, s);
            }
        });
    }

    private void just(){
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
    
    private void zip(){
        Observable<Integer> ob1 = Observable.just(1,2,3,4);
        Observable<String> ob2 =  Observable.from(words);
        
        Observable.zip(ob1, ob2, new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String s) {
                return "zip: " + String.valueOf(integer) + " " + s;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, s);
            }
        });

        ob1.zipWith(ob2, new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String s) {
                return "zipWith: " + String.valueOf(integer) + " " + s;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, s);
            }
        });
    }

    private void flatMap(){
        Observable.from(words)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.from(s.split(""));
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG,"flatMap: " + s);
                    }
                });
    }

    private void rang(){
        Observable.from(words)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        Log.e(TAG, s);
                        return Observable.from(s.split(""));
                    }
                })
                .zipWith(Observable.range(1, Integer.MAX_VALUE), new Func2<String, Integer, String>() {
                    @Override
                    public String call(String s, Integer integer) {
                        return "rang: " + String.format("%2d. %s", integer, s);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, s);
                    }
                });
    }

    private void distinct(){
        Observable.from(words)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.from(s.split(""));
                    }
                })
                .distinct()
                .zipWith(Observable.range(1, Integer.MAX_VALUE), new Func2<String, Integer, String>() {
                    @Override
                    public String call(String s, Integer integer) {
                        return "distinct: " + String.format("%2d. %s", integer, s);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, s);
                    }
                });
    }

    private void merg(){
        Observable<Long> fast = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> slow = Observable.interval(3, TimeUnit.SECONDS);

        Observable.merge(fast, slow.map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return "ob2: " + String.valueOf(aLong);
                    }
                })
        ).subscribe(new Action1<Serializable>() {
            @Override
            public void call(Serializable serializable) {
                Log.d(TAG, serializable.toString());
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

    }
}
