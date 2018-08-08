package com.anrongtec.cp.adapter;

import android.support.annotation.Nullable;

import com.anrongtec.cp.R;
import com.anrongtec.cp.entity.PersonControlInfoEntity;
import com.anrongtec.cp.utils.DateTools;
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
        List<PersonControlInfoEntity.DataBean.ResultListBean> resultList= item.data.resultList;
        if (resultList.size()!=0&&resultList!=null){
            for (int i = 0; i <resultList.size() ; i++) {
                helper.setText(R.id.tv_person_control_item_name, item.data.resultList.get(0).xm)
                        .setText(R.id.tv_person_control_item_sfzid, item.data.resultList.get(0).sfzh)
                        .setText(R.id.tv_person_control_item_gender, item.data.resultList.get(0).xb.equals("1") ? "男" : "女")
                        .setText(R.id.tv_person_control_item_rylb, item.data.resultList.get(0).rylb)
                        .setText(R.id.tv_person_control_item_control_time, DateTools.getStrTime(item.data.resultList.get(0).entryTime))
                        .setText(R.id.tv_person_control_item_control_person, "列控人:" + item.data.resultList.get(0).lxr + "/"
                                + item.data.resultList.get(0).lxdh)
                        .addOnClickListener(R.id.btn_person_control_item);
            }

        }
    }
}
