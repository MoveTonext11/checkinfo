package com.anrongtec.lasa.adapter;

import android.support.annotation.Nullable;

import com.anrongtec.lasa.manager.CheckHestory;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * @author libo
 * @Description: RecordPersonAdapter
 * @date 2018/5/16
 */

public class RecordPersonAdapter extends BaseQuickAdapter<CheckHestory, BaseViewHolder> {


    public RecordPersonAdapter(int layoutResId, @Nullable List<CheckHestory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckHestory item) {
        //适配器数据展示信息

        //是否中标
//        boolean isBid = !TextUtils.isEmpty(item.getTaskNames());
//
//        helper.setText(R.id.tv_person_record_item_name, item.getXm())
//                .setText(R.id.tv_person_record_item_gender, item.getXb())
//                .setText(R.id.tv_person_record_item_sfzid, item.getSfhm())
//                .setText(R.id.tv_person_record_item_check_time, DateTools.getStrTime(item.getInAppDate()))
//                .setText(R.id.tv_person_record_item_rylb, item.getRylb())
//                .setGone(R.id.tv_person_record_item_rylb, isBid)
//                .setGone(R.id.iv_person_record_item_tag, isBid);
    }
}
