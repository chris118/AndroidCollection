package com.example.xiaopeng.androiddemo.Activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xiaopeng.androiddemo.Fragment.BlankLazyFragment;
import com.example.xiaopeng.androiddemo.Fragment.LazyFragment;
import com.example.xiaopeng.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

public class LazyFragmentActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mPager;
    private List<android.support.v4.app.Fragment> fragments;
    private String[]titles={"page1","page2","page3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_fragment);

        mTab= (TabLayout) findViewById(R.id.id_tab);
        mPager= (ViewPager) findViewById(R.id.id_viewpager);
        mPager.setOffscreenPageLimit(1);
        initDatas();
    }

    private void initDatas() {
        fragments= new ArrayList<>(4);
        fragments.add(BlankLazyFragment.instantiate(this, BlankLazyFragment.class.getName()));
        fragments.add(BlankLazyFragment.instantiate(this, BlankLazyFragment.class.getName()));
        fragments.add(BlankLazyFragment.instantiate(this, BlankLazyFragment.class.getName()));


        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTab.setTabMode(TabLayout.MODE_FIXED);
        mTab.setupWithViewPager(mPager);
        mTab.setTabTextColors(Color.GRAY,Color.RED);
    }
}
