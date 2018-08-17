package com.anrongtec.cp.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.anrongtec.cp.R;
import com.anrongtec.cp.entity.Function;
import com.anrongtec.cp.utils.DialogUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhtsample.zht.com.offlinecheck.HcSdkInitCallback;
import zhtsample.zht.com.offlinecheck.HcSdkManager;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_main_function)
    RecyclerView mRvFunction;
    @BindView(R.id.iv_title_left)
    ImageView titleLeft;
    List<Function> functions;

    private BaseQuickAdapter<Function, BaseViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initofflinecheck();
        initView();
    }

    private void initofflinecheck() {
        //增加离线核查功能
        HcSdkManager.getInstance().init(this, getApplication(), new HcSdkInitCallback() {
            @Override
            public void onSuccess(int code) {
                ToastUtils.showShort("离线核查初始化成功");
            }

            @Override
            public void onFail(int code, String msg) {
                ToastUtils.showShort("离线核查初始化失败:" + msg);
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        titleLeft.setVisibility(View.VISIBLE);
        setTitle(getString(R.string.title_activity_mainIndex));
        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

        initFunctionData();
        mRvFunction.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new BaseQuickAdapter<Function, BaseViewHolder>(R.layout.item_rv_main_function,
                functions) {
            @Override
            protected void convert(BaseViewHolder helper, Function item) {
                helper.setText(R.id.tv_item_rv_main_function_name, item.getName());
                helper.setImageDrawable(R.id.iv_item_rv_main_function_pic, ContextCompat
                        .getDrawable(MainActivity.this, item.getIcon()));
            }
        };
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //快速安检
                        start(MainActivity.this, AnJianActivity.class);
                        break;
                    case 1:
                        //紧急列控
                        start(MainActivity.this, ControlNavigationActivity.class);
                        break;
                    case 2:
                        //核查记录
                        start(MainActivity.this, CheckQueryActivity.class);
                        break;
                    case 3:
                        //统计分析
                        start(MainActivity.this, CountAnalyzeActivity.class);
                        break;

                    default:
                        break;
                }
            }
        });
        mRvFunction.setAdapter(mAdapter);
    }

    /**
     * 初始化模块信息
     */
    private void initFunctionData() {
        functions = new ArrayList<>();
        //无证化安检
        functions.add(new Function(1, getString(R.string.main_function_anjian), R.drawable
                .ic_icon_main_anjian));
        //布控
        functions.add(new Function(2, getString(R.string.main_function_control), R.drawable
                .ic_icon_main_control));
        //核查记录查询
        functions.add(new Function(3, getString(R.string.main_function_check_record), R.drawable
                .ic_icon_main_record));
        //统计分析
        functions.add(new Function(4, getString(R.string.main_function_count_analyze), R.drawable
                .ic_icon_main_tongji));
    }

    /**
     * f返回键的监听
     * 弹窗询问是否确定退出APP
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == 4)) {
            DialogUtil.createTipDialog(this, "温馨提示", "是否退出核查核录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }, null);
        } else {
            return super.onKeyDown(keyCode, event);
        }
        return false;
    }
}
