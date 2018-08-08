package com.anrongtec.cp.adapter;

import android.support.annotation.Nullable;

import com.anrongtec.cp.R;
import com.anrongtec.cp.entity.CarControlInfoEntity;
import com.anrongtec.cp.utils.DateTools;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author libo
 * @Description: ControlCarAdapter
 * @date 2018/5/16
 */

public class ControlCarAdapter extends BaseQuickAdapter<CarControlInfoEntity, BaseViewHolder> {


    public ControlCarAdapter(int layoutResId, @Nullable List<CarControlInfoEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarControlInfoEntity item) {
        int size = item.data.resultList.size();
        List<CarControlInfoEntity.DataBean.ResultListBean> resultList = item.data.resultList;
        if (size != 0 && resultList != null) {
            for (int i = 0; i <size ; i++) {
                helper.setText(R.id.tv_car_control_item_name, resultList.get(i).carPerName).
                        setText(R.id.tv_car_control_item_sfzid, resultList.get(i).carPerId)
                        .setText(R.id.tv_car_control_item_carNum, resultList.get(i).carNumber)
                        .setText(R.id.tv_person_car_item_control_time, DateTools.getStrTime(resultList.get(i).sourceTime))
                        .setText(R.id.tv_car_control_item_link, resultList.get(i).lxr)
                        .setText(R.id.tv_car_control_item_cllb, resultList.get(i).keyType)
                        .setText(R.id.tv_car_control_item_linkPhone, resultList.get(i).lxdh)
                        .addOnClickListener(R.id.btn_car_control_item);
            }
        }
    }
}
