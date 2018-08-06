package com.anrongtec.cp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anrongtec.cp.R;

/**
 * BaseActivity
 */
public class BaseActivity extends AppCompatActivity {
    private LinearLayout mDectorView = null;//根布局
    private FrameLayout mContentView = null;//activity内容布局
    //是否显示自定义toolbar
    private boolean isShowTitle = true;

    /**
     * 自定义toolbar title
     */
    protected TextView mTvTitle;
    /**
     * 自定义toolbar 左边图片按钮
     */
    protected ImageView mIvTitleLeft;
    /**
     * 自定义toolbar 右边图片按钮
     */
    protected TextView mTvTitleRight;
    /**
     * 自定义toolbar 右边文字按钮
     */
    protected ImageView mIvTitleRight;

    /**
     * 是否显示title
     *
     * @param isShowTitle
     */
    protected void setTitleShow(boolean isShowTitle) {
        this.isShowTitle = isShowTitle;
    }

    static {
        //兼容低版本使用svg
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    /**
     * 页面跳转方法
     * @param context 当前页面上下文对象
     * @param clz     目标页面
     */
    public void start(Context context, Class clz) {
        context.startActivity(new Intent(context, clz));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void setContentView(int layoutResID) {
        if (!isShowTitle) {
            super.setContentView(layoutResID);
            return;
        }

        if (mDectorView == null) {
            mDectorView = new LinearLayout(this);
            mDectorView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mDectorView.setOrientation(LinearLayout.VERTICAL);
            mDectorView.setBackgroundColor(ContextCompat.getColor(this, R.color.background));

            addTitle();

            mContentView = new FrameLayout(this);
            mContentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mDectorView.addView(mContentView);
        }

        //如果已经创建就先把内容清空，再添加
        if (mContentView != null) {
            mContentView.removeAllViews();//mContentview清空里面的view
        }

        getLayoutInflater().inflate(layoutResID, mContentView);
        super.setContentView(mDectorView);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    /**
     * 设置标题
     *
     * @param titleId
     */
    @Override
    public void setTitle(int titleId) {
        mTvTitle.setText(titleId);
    }


    /**
     * 添加title控件
     */
    protected void addTitle() {
        View view = getLayoutInflater().inflate(R.layout.view_title_bar, mDectorView);
        mIvTitleLeft = view.findViewById(R.id.iv_title_left);
        mIvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle = view.findViewById(R.id.tv_title_center);
        mTvTitle.setText(getTitle());
        mTvTitleRight = view.findViewById(R.id.tv_title_right);
        mIvTitleRight = view.findViewById(R.id.iv_title_right);
    }
}
