package com.anrongtec.cp.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.anrongtec.cp.R;

/**
 * Created by wuwenbo
 * 2018/9/13
 */
public class StartActivity extends AppCompatActivity {
    private TextView tv_tec_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        initView();

        setAnimator();
    }

    /**
     * 设置属性动画
     */
    private void setAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(tv_tec_start, "scaleX", 1f, 2f, 1f);
        //沿y轴放大
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(tv_tec_start, "scaleY", 1f, 2f, 1f);
        //透明动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv_tec_start, "alpha", 1f, 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        //设置属性动画集合
        set.play(scaleXAnimator).with(scaleYAnimator).with(animator);
        //持续时间
        set.setDuration(2000);
        set.start();
        //设置动画监听
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void initView() {
        tv_tec_start = (TextView) findViewById(R.id.tv_tec_start);
    }
}
