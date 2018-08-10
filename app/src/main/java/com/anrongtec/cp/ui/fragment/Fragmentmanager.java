package com.anrongtec.cp.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class Fragmentmanager extends FragmentPagerAdapter {
    public Fragmentmanager(FragmentManager fm) {
        super(fm);
    }

    //设置集合
    private List<Fragment> lists;

    public void setFragments(List<Fragment> list) {
        lists = list;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
