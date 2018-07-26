package com.anrongtec.lasa.adapter;

import android.support.annotation.Nullable;

import com.anrongtec.lasa.R;
import com.anrongtec.lasa.entity.CarControlInfoEntity;
import com.anrongtec.lasa.utils.DateTools;
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
        helper.setText(R.id.tv_car_control_item_name, item.getCarPerName()).
                setText(R.id.tv_car_control_item_sfzid, item.getCarPerId())
                .setText(R.id.tv_car_control_item_carNum, item.getCarNumber())
                .setText(R.id.tv_person_car_item_control_time, DateTools.getStrTime(item
                        .getSourceTime()))
                .setText(R.id.tv_car_control_item_link, item.getLxr())
                .setText(R.id.tv_car_control_item_cllb, item.getKeyType())
                .setText(R.id.tv_car_control_item_linkPhone, item.getLxdh())
                .addOnClickListener(R.id.btn_car_control_item);

//                Glide.with(mContext).load("").into((ImageView) helper.getView(R.id
//         .iv_car_control_item_photo));

    }
}
