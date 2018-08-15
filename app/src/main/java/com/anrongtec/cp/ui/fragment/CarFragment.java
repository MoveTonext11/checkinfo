package com.anrongtec.cp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anrongtec.cp.R;
import com.anrongtec.cp.adapter.RecordCarAdapter;
import com.anrongtec.cp.manager.CheckHestory;

import java.util.List;

/**
 * Created by wuwenbo
 * 2018/8/10
 */
@SuppressLint("ValidFragment")
public class CarFragment extends BaseFragment {

    private RecyclerView rv_control_checkquery;
    private List<CheckHestory.ListCarRecordBean> listCarRecord;

    @Override
    protected int setView() {
        return R.layout.check_record_person_fragment;
    }

    private List<CheckHestory> listhestory;
    private Context context;

    @SuppressLint("ValidFragment")
    public CarFragment(Context context, List<CheckHestory> listhestory) {
        this.listhestory = listhestory;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        initView(rootView);
        initData();
        return rootView;
    }

    private void initData() {
        if (!listhestory.isEmpty()){
            listCarRecord = listhestory.get(0).listCarRecord;
        }
        RecordCarAdapter baseQuickAdapter = new RecordCarAdapter(R.layout.item_check_record_car, listCarRecord);
        rv_control_checkquery.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rv_control_checkquery.setAdapter(baseQuickAdapter);
        if (listCarRecord.isEmpty()){
            baseQuickAdapter.setEmptyView(getLayoutInflater().inflate(R.layout.fragment_empty, null));
        }
    }

    private void initView(View rootView) {
        rv_control_checkquery = (RecyclerView) rootView.findViewById(R.id.rv_control_person);
    }
}
