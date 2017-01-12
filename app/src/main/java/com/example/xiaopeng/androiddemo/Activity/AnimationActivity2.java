package com.example.xiaopeng.androiddemo.Activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.xiaopeng.androiddemo.Base.BaseActivity;
import com.example.xiaopeng.androiddemo.R;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimationActivity2 extends BaseActivity implements
        CompoundButton.OnCheckedChangeListener {
    private final String TAG = AnimationActivity2.class.getSimpleName();
    private GridLayout mGridLayout;
    private LayoutTransition mTransition;
    private int mVal;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.id_container)
    ViewGroup viewGroup;

    @BindView(R.id.id_appear)
    CheckBox mAppear;

    @BindView(R.id.id_change_appear)
    CheckBox mChangeAppear;

    @BindView(R.id.id_disappear)
    CheckBox mDisAppear;

    @BindView(R.id.id_change_disappear)
    CheckBox mChangeDisAppear;

    @Override
    public int getContentViewId() {
        return R.layout.activity_animation2;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        mAppear.setOnCheckedChangeListener(this);
        mChangeAppear.setOnCheckedChangeListener(this);
        mDisAppear.setOnCheckedChangeListener(this);
        mChangeDisAppear.setOnCheckedChangeListener(this);

        // 创建一个GridLayout
        mGridLayout = new GridLayout(this);
        // 设置每列5个按钮
        mGridLayout.setColumnCount(5);
        // 添加到布局中
        viewGroup.addView(mGridLayout);
        //默认动画全部开启
        mTransition = new LayoutTransition();
        mGridLayout.setLayoutTransition(mTransition);
    }

    @OnClick(R.id.listenerbtn)
    public void listenerClicked(){
        ObjectAnimator anim = ObjectAnimator.ofFloat(imageView, "alpha", 0.5f);
        anim.setRepeatCount(5);

        anim.addListener(new Animator.AnimatorListener()
        {

            @Override
            public void onAnimationStart(Animator animation)
            {
                Log.e(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {
                // TODO Auto-generated method stub
                Log.e(TAG, "onAnimationRepeat");
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                Log.e(TAG, "onAnimationEnd");
                ViewGroup parent = (ViewGroup) imageView.getParent();
                if (parent != null)
                    parent.removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {
                // TODO Auto-generated method stub
                Log.e(TAG, "onAnimationCancel");
            }
        });

//        anim.addListener(new AnimatorListenerAdapter()
//        {
//            @Override
//            public void onAnimationEnd(Animator animation)
//            {
//                Log.e(TAG, "onAnimationEnd");
//                ViewGroup parent = (ViewGroup) imageView.getParent();
//                if (parent != null)
//                    parent.removeView(imageView);
//            }
//        });

        anim.start();
    }

    @OnClick(R.id.animationset1)
    public void animationset1Clicked(){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView, "scaleX",
                1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView, "scaleY",
                1.0f, 2f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        //两个动画同时执行
        animSet.playTogether(anim1, anim2);
        animSet.start();
    }

    @OnClick(R.id.animationset2)
    public void animationset2Clicked(){
        float cx = imageView.getX();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView, "scaleX",
                1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView, "scaleY",
                1.0f, 2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(imageView,
                "x",  cx ,  0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(imageView,
                "x", cx);

        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim4).after(anim3);
        animSet.setDuration(1000);
        animSet.start();
    }

    @OnClick(R.id.xmlPropertyAnim)
    public void xmlPropertyAnimClicked() {
        // 加载动画
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scalex);
        anim.setTarget(imageView);
        anim.start();
    }

    @OnClick(R.id.xmlPropertyAnim2)
    public void xmlPropertyAnim2Clicked() {
        // 加载动画
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scalexy);
        imageView.setPivotX(0);
        imageView.setPivotY(0);
        //显示的调用invalidate
        imageView.invalidate();
        anim.setTarget(imageView);
        anim.start();
    }


    ///////////布局动画（Layout Animations）///////////
    public void addBtn(View view)
    {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                mGridLayout.removeView(button);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mTransition = new LayoutTransition();
        mTransition.setAnimator(
                LayoutTransition.APPEARING,
                (mAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.APPEARING) : null));

        mTransition.setAnimator(
                        LayoutTransition.CHANGE_APPEARING,
                        (mChangeAppear.isChecked() ? mTransition
                                .getAnimator(LayoutTransition.CHANGE_APPEARING)
                                : null));

        mTransition.setAnimator(
                LayoutTransition.DISAPPEARING,
                (mDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.DISAPPEARING) : null));

        mTransition.setAnimator(
                LayoutTransition.CHANGE_DISAPPEARING,
                (mChangeDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.CHANGE_DISAPPEARING)
                        : null));
        mGridLayout.setLayoutTransition(mTransition);
    }

    @OnClick(R.id.id_view_animation)
    public void viewAnimationClicked(){
        // need API12
        imageView.animate()//
                .alpha(0)//
                .y(300).setDuration(1000)
                // need API 12
                .withStartAction(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Log.e(TAG, "START");
                    }
                    // need API 16
                }).withEndAction(new Runnable()
        {

            @Override
            public void run()
            {
                Log.e(TAG, "END");
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        imageView.setY(0);
                        imageView.setAlpha(1.0f);
                    }
                });
            }
        }).start();

    }
}
