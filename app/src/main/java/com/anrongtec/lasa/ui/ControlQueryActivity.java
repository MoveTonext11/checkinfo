package com.anrongtec.lasa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.anrongtec.lasa.R;
import com.anrongtec.lasa.adapter.NavigationViewPagerAdapter;
import com.anrongtec.lasa.ui.fragment.QueryCarControlFragment;
import com.anrongtec.lasa.ui.fragment.QueryPersonControlFragment;
import com.anrongtec.lasa.utils.NFCReaderHelper;
import com.anrongtec.lasa.utils.NFCUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author libo
 * @ClassName: ControlQueryActivity
 * @Description: 布控查询页面
 * @date 2018/5/14
 **/

public class ControlQueryActivity extends BaseActivity {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp_tab)
    ViewPager viewPager;
    private Fragment[] fragments;

    private String[] titles;
    private NFCReaderHelper nfcHelper;
    private QueryPersonControlFragment queryPersonControlFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_control);
        ButterKnife.bind(this);
        NFCUtils getinstance = NFCUtils.getinstance();
        //创建helper对象
        nfcHelper = getinstance.createHfcHelper(ControlQueryActivity.this);
        //创建服务器
        getinstance.initShareReference(ControlQueryActivity.this);
        initView();
    }


    private void initView() {
        setTitle(R.string.main_control_query_navigation);
        titles = new String[]{getString(R.string.queryPerson), getString(R.string.queryCar)};
        queryPersonControlFragment = new QueryPersonControlFragment(nfcHelper);
        fragments = new Fragment[]{queryPersonControlFragment, QueryCarControlFragment.getInstance()};
        viewPager.setAdapter(new NavigationViewPagerAdapter(getSupportFragmentManager(),
                fragments, titles));
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (nfcHelper.isNFC(intent)) {
            queryPersonControlFragment.NfcMessage(intent);
        }
    }
}
