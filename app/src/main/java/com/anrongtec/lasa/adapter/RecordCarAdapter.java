package com.anrongtec.lasa.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.anrongtec.lasa.R;
import com.anrongtec.lasa.entity.CarCheckEntity;
import com.anrongtec.lasa.utils.DateTools;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author libo
 * @Description: RecordPersonAdapter
 * @date 2018/5/16
 */

public class RecordCarAdapter extends BaseQuickAdapter<CarCheckEntity, BaseViewHolder> {


    public RecordCarAdapter(int layoutResId, @Nullable List<CarCheckEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarCheckEntity item) {
//        helper.setText(R.id.person_nameIn, item.getXm())
////                .setText(R.id.iv_detail_person_photo,item.getZp())
//                .setText(R.id.person_idCardIn, item.getSfhm())
////                .setText(R.id.person_genderIn, item.getXb())
////    .setText(R.id.person_mzIn, item.getMz())
//                .setText(R.id.person_infoSourceIn, item.getCph())
//                .setText(R.id.person_typeIn, item.getCllb())
//                .setText(R.id.person_levelIn, item.getClys())
//                .setText(R.id.person_linkIn, item.getContacts())
////        .setText(R.id.person_dealTypeIn, item.getCzfs())
//                .setText(R.id.person_linkPhoneIn, item.getContactCall());

        boolean isBid = !TextUtils.isEmpty(item.getTaskNames());

        helper.setText(R.id.tv_car_record_item_name, item.getCzxm())
                .setText(R.id.tv_car_record_item_gender, item.getXb())
//                .setImageResource(R.id.iv_car_record_item_photo,R.drawable.car)
                .setText(R.id.tv_car_record_item_sfzid, item.getCzsfhm())
                .setText(R.id.tv_car_record_item_carid, item.getCph())
//                .setText(R.id.tv_car_record_item_car_type, item.getHpzl())
                .setText(R.id.tv_car_record_item_cllb, item.getCllb())
                .setText(R.id.tv_car_record_item_check_time, DateTools.getStrTime(item.getInAppDate()))
                .setGone(R.id.tv_car_record_item_cllb, isBid)
                .setGone(R.id.iv_car_record_item_tag, isBid);


    }
}
