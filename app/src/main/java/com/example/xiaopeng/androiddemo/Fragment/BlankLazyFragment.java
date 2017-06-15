package com.example.xiaopeng.androiddemo.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiaopeng.androiddemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankLazyFragment extends LazyFragment {
    private static final String TAG = LazyFragment.class.getSimpleName();

    private TextView mTvData;
    private long mData;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_blank_lazy;
    }

    @Override
    protected void setupView(View view) {
        super.setupView(view);

        mTvData = (TextView) view.findViewById(R.id.data);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mData = System.currentTimeMillis();
                mTvData.setText("" + mData);

            }
        }, 2000);

    }

}
