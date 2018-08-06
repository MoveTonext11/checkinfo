package com.anrongtec.cp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.anrongtec.cp.R;
import com.anrongtec.cp.entity.CheckInfoBaseEntity;
import com.anrongtec.cp.entity.CheckInfoZBCarEntity;
import com.anrongtec.cp.entity.CheckInfoZBPersonEntity;
import com.anrongtec.cp.utils.DicDataCache;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 无证化安检   列表展示页面
 */
public class AnJianListActivity extends BaseActivity implements BaseQuickAdapter
        .OnItemClickListener {


    @BindView(R.id.rv_anjian_list)
    RecyclerView rvAnjianList;
    //人员adapter
    BaseQuickAdapter<CheckInfoBaseEntity, BaseViewHolder> mPersonAdapter;
    //中标人员adapter
    BaseQuickAdapter<CheckInfoZBPersonEntity, BaseViewHolder> mPersonBidAdapter;
    //中标车辆adapter
    BaseQuickAdapter<CheckInfoZBCarEntity, BaseViewHolder> mCarBidAdapter;


    /**
     * 上次核查的人员信息
     */
    private List<CheckInfoBaseEntity> mPersons;
    /**
     * 本次核查中标人员信息
     */
    private List<CheckInfoZBPersonEntity> mPersonBids;
    /**
     * 本次核查中标车辆信息
     */
    private List<CheckInfoZBCarEntity> mCarBids;


    //当前页
    private int page = 1;
    //每页展示数据数量
    private static final int pageSize = 20;


    //展示布控 车辆还是人员信息
    private int mType;
    public static final int TYPE_THISPERSON = 1;
    public static final int TYPE_AGOPERSON = 3;
    private static final int TYPE_PERSON_BID = 2;
    private static final int TYPE_CAR_BID = 4;

    private static final String EXTRA_PERSON = "extra_person";
    private static final String EXTRA_PERSON_BID = "extra_person_bid";
    private static final String EXTRA_CAR_BID = "extra_car_bid";
    private static final String EXTRA_TYPE = "extra_type";

    public static void startPerson(Context context, ArrayList<CheckInfoBaseEntity> baseEntity,
                                   int flag) {
        Intent intent = new Intent(context, AnJianListActivity.class);
        intent.putExtra(EXTRA_PERSON, baseEntity);
        if (flag == TYPE_THISPERSON) {
            intent.putExtra(EXTRA_TYPE, TYPE_THISPERSON);
        } else {
            intent.putExtra(EXTRA_TYPE, TYPE_AGOPERSON);
        }
        context.startActivity(intent);
    }

    public static void startPersonBid(Context context, ArrayList<CheckInfoZBPersonEntity>
            zbPersonEntity) {
        Intent intent = new Intent(context, AnJianListActivity.class);
        intent.putExtra(EXTRA_PERSON_BID, zbPersonEntity);
        intent.putExtra(EXTRA_TYPE, TYPE_PERSON_BID);
        context.startActivity(intent);
    }

    public static void startCarBid(Context context, ArrayList<CheckInfoZBCarEntity> zbCarEntity) {
        Intent intent = new Intent(context, AnJianListActivity.class);
        intent.putExtra(EXTRA_CAR_BID, zbCarEntity);
        intent.putExtra(EXTRA_TYPE, TYPE_CAR_BID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anjian_list);
        ButterKnife.bind(this);
        setTitle("列表信息");
        if (getIntentData()) {
            initView();
        }

    }

    /**
     * 获取传递参数
     */
    private boolean getIntentData() {

        mType = getIntent().getIntExtra(EXTRA_TYPE, 0);
        if (mType == TYPE_THISPERSON) {
            mPersons = (List<CheckInfoBaseEntity>) getIntent().getSerializableExtra(EXTRA_PERSON);
        } else if (mType == TYPE_PERSON_BID) {
            mPersonBids = (List<CheckInfoZBPersonEntity>) getIntent().getSerializableExtra
                    (EXTRA_PERSON_BID);
        } else if (mType == TYPE_CAR_BID) {
            mCarBids = (List<CheckInfoZBCarEntity>) getIntent().getSerializableExtra(EXTRA_CAR_BID);
        } else if (mType == TYPE_AGOPERSON) {
            mPersons = (List<CheckInfoBaseEntity>) getIntent().getSerializableExtra(EXTRA_PERSON);
        } else {
            ToastUtils.showShort("参数传递错误");
            return false;
        }
        return true;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        initRecyclerView();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        rvAnjianList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        if (mType == TYPE_THISPERSON) {
            mPersonAdapter = new BaseQuickAdapter<CheckInfoBaseEntity, BaseViewHolder>(R.layout
                    .item_anjian_list_rv_person, mPersons) {
                @Override
                protected void convert(BaseViewHolder helper, CheckInfoBaseEntity item) {
                    helper.setText(R.id.tv_anjian_list_person_name, item.getXM())
                            .setText(R.id.tv_anjian_list_person_gender, DicDataCache.get().getXB
                                    (item.getXb()))
                            .setText(R.id.tv_anjian_list_person_sfzid, item.getSFZH());

                    Glide.with(mContext)
                            .load(item.getXpUrl())
                            .into((ImageView) helper.getView(R.id.iv_anjian_list_person_photo));
                }
            };
            mPersonAdapter.setOnItemClickListener(this);
            rvAnjianList.setAdapter(mPersonAdapter);
        } else if (mType == TYPE_PERSON_BID) {
            mPersonBidAdapter = new BaseQuickAdapter<CheckInfoZBPersonEntity, BaseViewHolder>(R
                    .layout.item_anjian_list_rv_person_bid, mPersonBids) {
                @Override
                protected void convert(BaseViewHolder helper, CheckInfoZBPersonEntity item) {
                    helper.setText(R.id.tv_anjian_list_person_bid_name, item.getXM())
                            .setText(R.id.tv_anjian_list_person_bid_sfzid, item.getSFZH())
                            .setText(R.id.tv_anjian_list_person_bid_lxr, "布控联系人:" + item.getBKLXR
                                    () + "/" + item.getBKLXFS());
                }
            };
            mPersonBidAdapter.setOnItemClickListener(this);
            rvAnjianList.setAdapter(mPersonBidAdapter);
        } else if (mType == TYPE_CAR_BID) {
            mCarBidAdapter = new BaseQuickAdapter<CheckInfoZBCarEntity, BaseViewHolder>(R.layout
                    .item_anjian_list_rv_car_bid, mCarBids) {
                @Override
                protected void convert(BaseViewHolder helper, CheckInfoZBCarEntity item) {

//					iv_anjian_list_car_bid_photo

                    helper.setText(R.id.tv_anjian_list_car_bid_name, item.getCZXM())
                            .setText(R.id.tv_anjian_list_car_bid_sfzid, item.getCZSFZH())
                            .setText(R.id.tv_anjian_list_car_bid_cphm, item.getCPHM())
                            .setText(R.id.tv_anjian_list_car_bid_cllb, item.getCLLB())
                            .setText(R.id.tv_anjian_list_car_bid_lxr, "布控联系人:" + item.getBKLXR()
                                    + "/" + item.getBKLXFS());
                    Glide.with(mContext).load(R.drawable.car).into((ImageView) helper.getView(R
                            .id.iv_anjian_list_car_bid_photo));

                }
            };
            mCarBidAdapter.setOnItemClickListener(this);
            rvAnjianList.setAdapter(mCarBidAdapter);
        } else if (mType == TYPE_AGOPERSON) {
            mPersonAdapter = new BaseQuickAdapter<CheckInfoBaseEntity, BaseViewHolder>(R.layout
                    .item_anjian_list_rv_person, mPersons) {
                @Override
                protected void convert(BaseViewHolder helper, CheckInfoBaseEntity item) {
                    helper.setText(R.id.tv_anjian_list_person_name, item.getXM())
                            .setText(R.id.tv_anjian_list_person_gender, DicDataCache.get().getXB
                                    (item.getXb()))
                            .setText(R.id.tv_anjian_list_person_sfzid, item.getSFZH());

                    Glide.with(mContext)
                            .load(item.getXpUrl())
                            .into((ImageView) helper.getView(R.id.iv_anjian_list_person_photo));
                }
            };
            mPersonAdapter.setOnItemClickListener(this);
            rvAnjianList.setAdapter(mPersonAdapter);
        }
    }

    /**
     * item 点击回调
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {



        if (mType == TYPE_THISPERSON) {
            AnJianDetailsActivity.startPerson(this, mPersons.get(position));
        } else if (mType == TYPE_PERSON_BID) {
            AnJianDetailsActivity.startPersonBid(this, mPersonBids.get(position));
        } else if (mType == TYPE_CAR_BID) {
            AnJianDetailsActivity.startCarBid(this, mCarBids.get(position));
        } else if (mType == TYPE_AGOPERSON) {
            AnJianDetailsActivity.startPerson(this, mPersons.get(position));
        } else {
            ToastUtils.showShort("跳转异常");
        }
    }
}
