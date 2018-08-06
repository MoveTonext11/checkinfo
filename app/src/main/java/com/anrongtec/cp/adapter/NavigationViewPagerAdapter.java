package com.anrongtec.cp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author libo
 * @Description: 申请布控Adapter
 * @date 2018/5/12
 */

public class NavigationViewPagerAdapter extends FragmentPagerAdapter {
    Fragment[] fragments;
    String[] titles;

    public NavigationViewPagerAdapter(FragmentManager fm, Fragment[] arrayFragments, String[] arrayTitle) {
        super(fm);
        this.fragments = arrayFragments;
        this.titles = arrayTitle;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.length : 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
