package com.anrongtec.cp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anrongtec.cp.R;
import com.anrongtec.cp.adapter.ControlCarAdapter;
import com.anrongtec.cp.adapter.ControlPersonAdapter;
import com.anrongtec.cp.entity.CarControlInfoEntity;
import com.anrongtec.cp.entity.PersonControlInfoEntity;
import com.anrongtec.cp.interfaces.HttpInterfaces;
import com.anrongtec.cp.interfaces.callback.StringDialogCallback;
import com.anrongtec.cp.interfaces.result.DataResult;
import com.anrongtec.cp.manager.ControlManager;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 布控查询  列表页面
 */
public class ControlListActivity extends BaseActivity {


    @BindView(R.id.rv_control_list)
    RecyclerView rvControlList;
    @BindView(R.id.tv_null_mesg)
    TextView tv_numm_msg;
    //人员adapter
    BaseQuickAdapter<PersonControlInfoEntity, BaseViewHolder> mPersonAdapter;
    //车辆adapter
    BaseQuickAdapter<CarControlInfoEntity, BaseViewHolder> mCarAdapter;


    //人员布控信息集合
    private List<PersonControlInfoEntity> mPersons;
    //车辆布控信息集合
    private List<CarControlInfoEntity> mCars;


    //身份证号
    private String mSfzId;
    //姓名
    private String mName;
    //号牌种类
    private String mNumPlate;
    //车牌号
    private String mCarId;

    //当前页
    private int page = 1;
    //每页展示数据数量
    private static final int pageSize = 20;


    //展示布控 车辆还是人员信息
    private int mType;
    private static final int TYPE_PERSON = 1;
    private static final int TYPE_CAR = 2;

    private static final String EXTRA_SFZ = "extra_sfz";
    private static final String EXTRA_NAME = "extra_name";
    private static final String EXTRA_NUMPLATE = "extra_numplate";
    private static final String EXTRA_CARID = "extra_carid";
    private static final String EXTRA_TYPE = "extra_type";

    public static void startPerson(Context context, String sfzId, String name) {
        Intent intent = new Intent(context, ControlListActivity.class);
        intent.putExtra(EXTRA_SFZ, sfzId);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_TYPE, TYPE_PERSON);
        context.startActivity(intent);
    }

    public static void startCar(Context context, String carId, String plateNum) {
        Intent intent = new Intent(context, ControlListActivity.class);
        intent.putExtra(EXTRA_CARID, carId);
        intent.putExtra(EXTRA_NUMPLATE, plateNum);
        intent.putExtra(EXTRA_TYPE, TYPE_CAR);
        context.startActivity(intent);
    }

    private static final int HANDLER_UPDATE = 1;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_UPDATE:
                    if (mPersonAdapter != null) {
                        mPersonAdapter.notifyDataSetChanged();
                    }
                    if (mCarAdapter != null) {
                        mCarAdapter.notifyDataSetChanged();
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_list);
        ButterKnife.bind(this);
        setTitle("查询结果列表");

        if (getIntentData()) {
            initData();
        }
    }

    /**
     * 获取传递参数
     */
    private boolean getIntentData() {
        mPersons = new ArrayList<>();
        mCars = new ArrayList<>();

        mType = getIntent().getIntExtra(EXTRA_TYPE, 0);
        if (mType == TYPE_PERSON) {
            mSfzId = getIntent().getStringExtra(EXTRA_SFZ);
            mName = getIntent().getStringExtra(EXTRA_NAME);
//            ToastUtils.showShort(mSfzId + mName);
        } else if (mType == TYPE_CAR) {
            mNumPlate = getIntent().getStringExtra(EXTRA_NUMPLATE);
            mCarId = getIntent().getStringExtra(EXTRA_CARID);
        } else {
            ToastUtils.showShort("参数传递错误");
            return false;
        }
        return true;
    }

    /**
     * 获取布控信息
     */
    private void initData() {

        if (mType == TYPE_PERSON) {
            //获取人员布控信息
            getPersonInfo();
        } else {
            //获取车辆布控信息
            getCarInfo();
        }

    }

    /**
     * 获取车辆布控信息
     */
    private void getCarInfo() {
        HttpInterfaces.ListCar(mCarId, mNumPlate, String.valueOf(page), String.valueOf(pageSize), new StringDialogCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                CarControlInfoEntity carControlInfoEntity = gson.fromJson(response.body(), CarControlInfoEntity.class);
                mCars.add(carControlInfoEntity);
                initView();
            }
        }, null);
    }

    /**
     * 获取人员布控信息
     */
    private void getPersonInfo() {
        HttpInterfaces.ListPerson(mName, mSfzId, String.valueOf(page), String.valueOf(pageSize), new StringDialogCallback(this, "人员信息获取中...") {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                PersonControlInfoEntity personControlInfoEntity = gson.fromJson(response.body(), PersonControlInfoEntity.class);
                mPersons.add(personControlInfoEntity);
                initView();
            }
        }, null);
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
        rvControlList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL, false));
        if (mType == TYPE_PERSON) {
            mPersonAdapter = new ControlPersonAdapter(R.layout.item_controlperson_info, mPersons);
            if (mPersons.size() != 0) {
                mPersonAdapter.setOnItemChildClickListener(new BaseQuickAdapter
                        .OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                        switch (view.getId()) {
                            case R.id.btn_person_control_item://撤控事件
                                showReControlDialog(position);
                                break;
                            default:
                                break;
                        }
                    }
                });
                rvControlList.setAdapter(mPersonAdapter);
            }
        } else if (mType == TYPE_CAR) {
            mCarAdapter = new ControlCarAdapter(R.layout.item_controlcar_info, mCars);
            if (mCars.size() != 0) {
                mCarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, final int
                            position) {
                        switch (view.getId()) {
                            case R.id.btn_car_control_item://车辆撤控
                                showReControlDialog(position);
                                break;
                            default:
                                break;
                        }
                    }
                });
                rvControlList.setAdapter(mCarAdapter);
            }
        }
    }

    /**
     * 显示撤控对话框
     *
     * @param position
     */
    private void showReControlDialog(final int position) {
        new MaterialDialog.Builder(ControlListActivity.this)
                .input("撤控原因", "", false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        if (input.toString().trim().length() == 0) {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                        } else {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                        }
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction
                            which) {
                        String reason = dialog.getInputEditText().getText().toString().trim();
                        if (mType == TYPE_PERSON) {
                            personReControl(reason, position);
                        } else {
                            carReControl(reason, position);
                        }
                    }
                }).show();
    }

    /**
     * 人员撤控
     *
     * @param reason
     * @param position
     */
    private void personReControl(@NonNull String reason, int position) {
        PersonControlInfoEntity person = mPersons.get(position);
        ControlManager.getInstance().personReControl(ControlListActivity.this,
                person.data.resultList.get(0).lxr, person.data.resultList.get(0).sfzh, reason, "cp", new ReControlCallback(position));
    }

    /**
     * 车辆撤控
     *
     * @param reason
     * @param position 备注：撤控人字段为终端信息中的警员信息。暂定为默认字段
     */
    private void carReControl(@NonNull String reason, int position) {
        CarControlInfoEntity car = mCars.get(position);
        ControlManager.getInstance().carReControl(ControlListActivity.this, car.data.resultList.get(0).carNumber, car.data.resultList.get(0).lxr, reason, "cp", new ReControlCallback(position));
    }

    /**
     * 撤控的回调
     */
    class ReControlCallback implements ControlManager.CallBackControl {
        int position;

        public ReControlCallback(int position) {
            this.position = position;
        }

        @Override
        public void callBackSucceed(DataResult dataResult) {
            if (mType == TYPE_PERSON) {
                if (dataResult.getResultCode().equals("000"))
                    ToastUtils.showShort("人员撤控成功");
                mPersons.remove(position);
                mPersonAdapter.notifyDataSetChanged();
            } else if (mType == TYPE_CAR) {
                if (dataResult.getResultCode().equals("000"))
                    ToastUtils.showShort("车辆撤控成功");
                mCars.remove(position);
                mCarAdapter.notifyDataSetChanged();
            }

        }

        @Override
        public void callBackError(Response<String> response) {
            ToastUtils.showShort("撤控失败：" + response.message() + response.body());
        }
    }


}
