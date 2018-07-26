package com.anrongtec.lasa.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.anrongtec.lasa.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author libo
 * @ClassName: ControlNavigationActivity
 * @Description: 布控操作导航页面
 * @date 2018/5/14
 **/

public class ControlNavigationActivity extends BaseActivity {

    @BindView(R.id.rv_control_navigation)
    RecyclerView rvControlNavigation;
    private List<String> listControlNames;
    private BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_navigation);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        setTitle(R.string.main_function_control);
        listControlNames = new ArrayList<>();
        listControlNames.add(getString(R.string.main_control_apply_navigation));
        listControlNames.add(getString(R.string.main_control_query_navigation));


        baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout
                .item_rv_control_function, listControlNames) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item_rv_control_function_name, item);
            }
        };
        rvControlNavigation.setLayoutManager(new LinearLayoutManager(ControlNavigationActivity
                .this, LinearLayoutManager.VERTICAL, false));
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        start(ControlNavigationActivity.this, ControlApplyActivity.class);
                        break;
                    case 1:
                        start(ControlNavigationActivity.this, ControlQueryActivity.class);
                        break;
                }
            }
        });
        rvControlNavigation.setAdapter(baseQuickAdapter);
    }
}
