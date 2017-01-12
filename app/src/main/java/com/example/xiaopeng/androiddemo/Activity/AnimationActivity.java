package com.example.xiaopeng.androiddemo.Activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.xiaopeng.androiddemo.Base.BaseActivity;
import com.example.xiaopeng.androiddemo.R;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimationActivity extends BaseActivity {
    private final String TAG = AnimationActivity.class.getSimpleName();

    @BindView(R.id.targetView)
    ImageView imageView;

    @BindView(R.id.imageView_drawable_ani)
    ImageView imageView_drawable_ani;

    @BindView(R.id.imageView_PA)
    ImageView imageView_PA;

    private  AnimationDrawable loadingAnimation;

    @Override
    public int getContentViewId() {
        return R.layout.activity_animation;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        imageView_drawable_ani.setBackgroundResource(R.drawable.loading);
        loadingAnimation = (AnimationDrawable) imageView_drawable_ani.getBackground();
    }

    @OnClick(R.id.fadein)
    public void fadeinClicked(){
        AlphaAnimation anim = (AlphaAnimation)AnimationUtils.loadAnimation(this, R.anim.fade_in);
        imageView.startAnimation(anim);
    }

    @OnClick(R.id.scalebtn)
    public void scaleClicked(){
        ScaleAnimation anim = (ScaleAnimation)AnimationUtils.loadAnimation(this, R.anim.scale);
        imageView.startAnimation(anim);
    }

    @OnClick(R.id.translatebtn)
    public void translateClicked(){

        TranslateAnimation tAnim = new TranslateAnimation(0, 400, 0, 0);//横向位移400个单位
//        RotateAnimation rAnima = new RotateAnimation(0, 70);//顺时针旋转70度
//        ScaleAnimation sAnima = new ScaleAnimation(0, 5, 0, 5);//横向放大5倍，纵向放大5倍
//        AlphaAnimation aAnima = new AlphaAnimation(1.0f, 0.0f);//从全不透明变为全透明
        // 3: 确定持续时间
        tAnim.setDuration(2000);
//        rAnima.setDuration(2000);
//        sAnima.setDuration(2000);
//        aAnima.setDuration(2000);
        imageView.startAnimation(tAnim);
//        imageView.startAnimation(rAnima);
//        imageView.startAnimation(sAnima);
//        imageView.startAnimation(aAnima);


//        TranslateAnimation anim = (TranslateAnimation)AnimationUtils.loadAnimation(this, R.anim.translate);
//        imageView.startAnimation(anim);
    }

    @OnClick(R.id.rotatebtn)
    public void rotateClicked(){
        RotateAnimation anim = (RotateAnimation)AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(anim);
    }

    @OnClick(R.id.animationsetBtn)
    public void animationSetClicked(){
        AnimationSet anim = (AnimationSet)AnimationUtils.loadAnimation(this, R.anim.animation_set);
        imageView.startAnimation(anim);
    }



    /////////// drawable animation : ObjectAnimator////////////////////////
    /**
     * activity显示到屏幕则开启动画
     */
    @OnClick(R.id.drawableAniBtn)
    public void drawableAnimationStartClicked(){
        loadingAnimation.start();
    }


    /////////// property animation ////////////////////////
    @OnClick(R.id.simplePA)
    public void simplePAClicked(){

        ObjectAnimator.ofFloat(imageView_PA, "rotationX", 0.0F, 360.0F)
                .setDuration(500)
                .start();
    }

    @OnClick(R.id.animationset)
    public void animationsetClicked(){
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(imageView_PA, "zhy", 1.0F,  0.0F, 1.0F)//
                .setDuration(500);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float cVal = (Float) animation.getAnimatedValue();
                imageView_PA.setAlpha(cVal);
                imageView_PA.setScaleX(cVal);
                imageView_PA.setScaleY(cVal);
            }
        });
    }

    @OnClick(R.id.animationset2)
    public void animationset2Clicked(){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(imageView_PA, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }

    /////////// property animation : ValueAnimator////////////////////////
    @OnClick(R.id.simple_ValueAnimation)
    public void simple_ValueAnimationClicked(){
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setTarget(imageView_PA);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                imageView_PA.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }

    @OnClick(R.id.complex_ValueAnimation)
    public void complex_ValueAnimationClicked(){
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
        {
            // fraction = t / duration  
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue)
            {
                Log.i(TAG, fraction * 3 + "");
                // x方向200px/s ，则y方向0.5 * 10 * t  
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                imageView_PA.setX(point.x);
                imageView_PA.setY(point.y);

            }
        });
    }
}
