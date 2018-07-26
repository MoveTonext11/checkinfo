package com.anrongtec.lasa.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.anrongtec.lasa.R;
import com.anrongtec.lasa.adapter.NavigationViewPagerAdapter;
import com.anrongtec.lasa.ui.fragment.CheckCarFragment;
import com.anrongtec.lasa.ui.fragment.CheckPersonFragment;
import com.anrongtec.lasa.utils.NFCReadTask;
import com.anrongtec.lasa.utils.NFCReaderHelper;
import com.anrongtec.lasa.utils.NFCUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author libo
 * @ClassName: InfoCheckActivity
 * @Description: 信息核查页面
 * @date 2018/5/15
 **/

public class InfoCheckActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp_tab)
    ViewPager viewPager;
    private Fragment[] fragments;
    private String[] titles;
    private NFCReaderHelper nfcHelper;
    private NFCReadTask nfcReadTask;
    private AsyncTask<Void, Void, String> nfcTask;
    private CheckPersonFragment checkPersonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_info);
        ButterKnife.bind(this);

        NFCUtils getinstance = NFCUtils.getinstance();
        //创建helper对象
        nfcHelper = getinstance.createHfcHelper(InfoCheckActivity.this);
        //创建服务器
        getinstance.initShareReference(InfoCheckActivity.this);
        initData();
    }

    private void initData() {
        setTitle(R.string.title_activity_check_info);
        titles = new String[]{getResources().getString(R.string.main_check_person_navigation), getResources().getString(R.string.main_check_car_navigation)};
        checkPersonFragment = new CheckPersonFragment(nfcHelper);
        fragments = new Fragment[]{checkPersonFragment,
                CheckCarFragment.getInstance()};
        viewPager.setAdapter(new NavigationViewPagerAdapter(getSupportFragmentManager(),
                fragments, titles));
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (nfcHelper.isNFC(intent)) {
            checkPersonFragment.NfcMessage(intent);
        }
    }
}
