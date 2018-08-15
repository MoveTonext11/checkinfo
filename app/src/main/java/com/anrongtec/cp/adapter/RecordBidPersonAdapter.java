package com.anrongtec.cp.adapter;

import android.support.annotation.Nullable;

import com.anrongtec.cp.R;
import com.anrongtec.cp.manager.CheckHestory;
import com.anrongtec.cp.utils.DateTools;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class RecordBidPersonAdapter extends BaseQuickAdapter<CheckHestory.ListBidPersionRecordBean, BaseViewHolder> {


    public RecordBidPersonAdapter(int layoutResId, @Nullable List<CheckHestory.ListBidPersionRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckHestory.ListBidPersionRecordBean item) {
        helper.setText(R.id.tv_person_record_item_sfzid, item.sfzh)
//                .setText(R.id.tv_person_record_item_gender, item.getXb())
//                .setText(R.id.tv_person_record_item_name, item.sfhm)
                .setText(R.id.tv_person_record_item_check_time, DateTools.getStrTime(item
                        .pckssj))
//                .setText(R.id.tv_person_record_item_rylb, item.getRylb())
//                .setGone(R.id.tv_person_record_item_rylb, isBid);
                .setGone(R.id.iv_person_record_item_tag, true);
    }
}
