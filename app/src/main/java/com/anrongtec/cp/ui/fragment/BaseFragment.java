package com.anrongtec.cp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anrongtec.cp.ui.BaseActivity;

/**
 * @author libo
 * @Description: BaseFragment
 * @date 2018/5/13
 */

public abstract class BaseFragment extends Fragment {
    public BaseActivity currentActivity;
    public View currentRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getArgs(getArguments());
        currentRootView = inflater.inflate(setView(), null);
        currentActivity = (BaseActivity) getActivity();
        return currentRootView;
    }
    /**
     * 获得传入的参数
     */
    private void getArgs(Bundle arguments) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initView();

        initListener();


    }

    public void initView() {

    }

    public void initListener() {
    }

    protected abstract int setView();


}