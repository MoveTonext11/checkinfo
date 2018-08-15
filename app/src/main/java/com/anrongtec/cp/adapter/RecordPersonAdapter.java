package com.anrongtec.cp.adapter;

import com.anrongtec.cp.R;
import com.anrongtec.cp.manager.CheckHestory;
import com.anrongtec.cp.utils.DateTools;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * @author libo
 * @Description: RecordPersonAdapter
 * @date 2018/5/16
 */

public class RecordPersonAdapter extends BaseQuickAdapter<CheckHestory.ListPersionRecordBean, BaseViewHolder> {


    public RecordPersonAdapter(int layoutResId, List<CheckHestory.ListPersionRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckHestory.ListPersionRecordBean item) {
        helper.setText(R.id.tv_person_record_item_sfzid, item.sfhm)
//                .setText(R.id.tv_person_record_item_gender, item.getXb())
//                .setText(R.id.tv_person_record_item_name, item.sfhm)
                .setText(R.id.tv_person_record_item_check_time, DateTools.getStrTime(item
                        .createTime))
//                .setText(R.id.tv_person_record_item_rylb, item.getRylb())
//                .setGone(R.id.tv_person_record_item_rylb, isBid);
                .setGone(R.id.iv_person_record_item_tag, false);
    }
}
