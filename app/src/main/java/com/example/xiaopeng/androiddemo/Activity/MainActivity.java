package com.example.xiaopeng.androiddemo.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.Adapter.MainAdapter;
import com.example.xiaopeng.androiddemo.Adapter.RepoAdapter;
import com.example.xiaopeng.androiddemo.Base.BaseActivity;
import com.example.xiaopeng.androiddemo.Bean.RepoEntity;
import com.example.xiaopeng.androiddemo.Fragment.FourFragment;
import com.example.xiaopeng.androiddemo.Fragment.HomeFragment;
import com.example.xiaopeng.androiddemo.Fragment.SecondFragment;
import com.example.xiaopeng.androiddemo.Fragment.ThreeFragment;
import com.example.xiaopeng.androiddemo.HttpService.RetrofitManager;
import com.example.xiaopeng.androiddemo.LogUtil.LogUtil;
import com.example.xiaopeng.androiddemo.R;
import com.example.xiaopeng.androiddemo.Views.DividerItemDecoration;
import com.example.xiaopeng.androiddemo.Views.LockableViewPager;
import com.putao.ptx.push.core.GPushClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tabLayout)
    TabLayout mTablayout;

    @BindView(R.id.viewPager)
    LockableViewPager mViewPager;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        initViews();
    }

    private void initViews(){
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"1", "2", "3","4"};

            @Override
            public Fragment getItem(int position) {
                if (position == 1) {
                    return new SecondFragment();
                } else if (position == 2) {
                    return new ThreeFragment();
                }else if (position==3){
                    return new FourFragment();
                }
                return new HomeFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

        });

        mTablayout.setupWithViewPager(mViewPager);

        mTablayout.getTabAt(0).setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        mTablayout.getTabAt(1).setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        mTablayout.getTabAt(2).setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        mTablayout.getTabAt(3).setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
    }
}
