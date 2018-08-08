package com.anrongtec.cp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.anrongtec.cp.R;
import com.anrongtec.cp.adapter.NavigationViewPagerAdapter;
import com.anrongtec.cp.ui.fragment.ApplyCarControlFragment;
import com.anrongtec.cp.ui.fragment.ApplyPersonControlFragment;
import com.anrongtec.cp.utils.NFCReaderHelper;
import com.anrongtec.cp.utils.NFCUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author libo
 * @ClassName: ControlApplyActivity
 * @Description: 布控显示界面
 * @date 2018/5/17
 **/

public class ControlApplyActivity extends BaseActivity {

    @BindView(R.id.vp_tab)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private Fragment[] fragments;

    private String[] titles;
    private NFCReaderHelper nfcHelper;
    private ApplyPersonControlFragment applyPersonControlFragment;
    private ApplyCarControlFragment applyCarControlFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_apply);
        ButterKnife.bind(this);

        NFCUtils getinstance = NFCUtils.getinstance();
        //创建helper对象
        nfcHelper = getinstance.createHfcHelper(ControlApplyActivity.this);
        //创建服务器
        getinstance.initShareReference(ControlApplyActivity.this);

        initData();

    }


    private void initData() {
        setTitle(R.string.main_control_apply_navigation);
        titles = new String[]{getString(R.string.main_control_person), getString(R.string
                .main_control_car)};
        applyPersonControlFragment = new ApplyPersonControlFragment(nfcHelper);
        applyCarControlFragment = new ApplyCarControlFragment(nfcHelper);
        fragments = new Fragment[]{applyPersonControlFragment, applyCarControlFragment};
        viewPager.setAdapter(new NavigationViewPagerAdapter(getSupportFragmentManager(),
                fragments, titles));
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (nfcHelper.isNFC(intent)) {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem==0){
                applyPersonControlFragment.NfcMessage(intent);//人员布控
            }
            if (currentItem==1){
                applyCarControlFragment.NfcMessage(intent);//车辆布控
            }
        }
    }
}
