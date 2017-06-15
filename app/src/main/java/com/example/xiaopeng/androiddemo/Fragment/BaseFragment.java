package com.example.xiaopeng.androiddemo.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaopeng on 2017/6/12.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {
    private static final String TAG = LazyFragment.class.getSimpleName();

    protected abstract int getFragmentLayoutId();

    /**
     * 初始化layout里的view
     *
     * @param view
     */
    protected void setupView(View view) {
    }

    /**
     * 获取数据，此时fragment的view已经准备完毕
     */
    protected void fetchData() {
        Log.v(TAG, getClass().getName() + "------>fetchData");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.v(TAG, getClass().getName() + "------>onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG, getClass().getName() + "------>onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v(TAG, getClass().getName() + "------>onCreateView");

        View view = inflater.inflate(getFragmentLayoutId(), container, false);

        setup(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.v(TAG, getClass().getName() + "------>onViewCreated");
        fetchData();
        // setup(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.v(TAG, getClass().getName() + "------>onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.v(TAG, getClass().getName() + "------>onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.v(TAG, getClass().getName() + "------>onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.v(TAG, getClass().getName() + "------>onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.v(TAG, getClass().getName() + "------>onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.v(TAG, getClass().getName() + "------>onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.v(TAG, getClass().getName() + "------>onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.v(TAG, getClass().getName() + "------>onDetach");
    }

    protected void setup(View view) {
        setupView(view);
    }

}
