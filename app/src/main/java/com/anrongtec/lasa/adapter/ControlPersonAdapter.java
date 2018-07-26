package com.anrongtec.lasa.adapter;

import android.support.annotation.Nullable;

import com.anrongtec.lasa.R;
import com.anrongtec.lasa.entity.PersonControlInfoEntity;
import com.anrongtec.lasa.utils.DateTools;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author libo
 * @Description: ControlPersonAdapter
 * @date 2018/5/16
 */

public class ControlPersonAdapter extends BaseQuickAdapter<PersonControlInfoEntity,
        BaseViewHolder> {


    public ControlPersonAdapter(int layoutResId, @Nullable List<PersonControlInfoEntity> data) {
        super(layoutResId, data);
    }

    public ControlPersonAdapter(@Nullable List<PersonControlInfoEntity> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonControlInfoEntity item) {
        helper.setText(R.id.tv_person_control_item_name, item.getXm()).
                setText(R.id.tv_person_control_item_sfzid, item.getSfzh())
                .setText(R.id.tv_person_control_item_gender, item.getXb().equals("1") ? "男" : "女")
                .setText(R.id.tv_person_control_item_rylb, item.getRylb())
                .setText(R.id.tv_person_control_item_control_time, DateTools.getStrTime(item
                        .getEntryTime()))
//                .setText(R.id.tv_person_control_item_link, item.getLxr())
//                .setText(R.id.tv_person_control_item_linkPhone, item.getLxdh())
                .setText(R.id.tv_person_control_item_control_person, "列控人:" + item.getLxr() + "/"
                        + item.getLxdh())
                .addOnClickListener(R.id.btn_person_control_item);
//        Glide.with(mContext).load("").into((ImageView) helper.getView(R.id
// .iv_person_control_item_photo));

    }
}
