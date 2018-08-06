package com.anrongtec.cp.adapter;

import android.support.annotation.Nullable;

import com.anrongtec.cp.R;
import com.anrongtec.cp.manager.CheckHestory;
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

        CheckHestory.ListCarRecordBean beanCar = item.listCarRecord.get(0);
        if (beanCar!=null){
            //显示数据根据现场条件再次进行调节   暂为待定
            helper.setText(R.id.tv_person_record_item_name, beanCar.cp)
                    .setText(R.id.tv_person_record_item_gender, beanCar.allpackageVer)
                    .setText(R.id.tv_person_record_item_sfzid, beanCar.id)
                    .setText(R.id.tv_person_record_item_check_time, String.valueOf(beanCar.createTime))
                    .setText(R.id.tv_person_record_item_rylb, beanCar.hpzl);
        }
    }
}
