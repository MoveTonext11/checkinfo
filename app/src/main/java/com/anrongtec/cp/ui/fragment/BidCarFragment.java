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
import com.anrongtec.cp.adapter.RecordBidCarAdapter;
import com.anrongtec.cp.manager.CheckHestory;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * Created by wuwenbo
 * 2018/8/10
 */
@SuppressLint("ValidFragment")
public class BidCarFragment extends BaseFragment {
    private RecyclerView rv_control_checkquery;
    private List<CheckHestory.ListCarRecordBean> listBidCarRecord;

    @Override
    protected int setView() {
        return R.layout.check_record_person_fragment;
    }

    private List<CheckHestory> listhestory;
    private Context context;

    @SuppressLint("ValidFragment")
    public BidCarFragment(Context context, List<CheckHestory> listhestory) {
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
            listBidCarRecord = listhestory.get(0).listCarRecord;
        }
        RecordBidCarAdapter baseQuickAdapter = new RecordBidCarAdapter(R.layout.item_check_record_car, listBidCarRecord);
        rv_control_checkquery.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到详情页面
//                DetailInfoActivity.start(CheckQueryActivity.this, listPerson.get(position));
            }
        });
        baseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
//                getDate(checkDate);
            }
        }, rv_control_checkquery);
        rv_control_checkquery.setAdapter(baseQuickAdapter);
    }

    private void initView(View rootView) {
        rv_control_checkquery = (RecyclerView) rootView.findViewById(R.id.rv_control_person);
    }
}
