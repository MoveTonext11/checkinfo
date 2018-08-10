package com.anrongtec.cp.adapter;

import android.support.annotation.Nullable;

import com.anrongtec.cp.manager.CheckHestory;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author libo
 * @Description: RecordPersonAdapter
 * @date 2018/5/16
 */

public class RecordCarAdapter extends BaseQuickAdapter<CheckHestory.ListCarRecordBean, BaseViewHolder> {


    public RecordCarAdapter(int layoutResId, @Nullable List<CheckHestory.ListCarRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckHestory.ListCarRecordBean item) {

    }
}
