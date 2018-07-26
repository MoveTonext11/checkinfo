package com.anrongtec.lasa.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.anrongtec.lasa.R;
import com.anrongtec.lasa.entity.CarCheckEntity;
import com.anrongtec.lasa.entity.CheckInfoBaseEntity;
import com.anrongtec.lasa.entity.CheckInfoParamtersEntity;
import com.anrongtec.lasa.entity.CheckInfoZBCarEntity;
import com.anrongtec.lasa.entity.CheckInfoZBPersonEntity;
import com.anrongtec.lasa.manager.VerifyManager;
import com.anrongtec.lasa.utils.DateTools;
import com.anrongtec.lasa.utils.DicDataCache;
import com.anrongtec.lasa.utils.HBUtils;
import com.anrongtec.ocr.OcrCarActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.carkeyboard.KeyboardInputController;
import com.carkeyboard.support.PopupKeyboard;
import com.carkeyboard.view.InputView;
import com.carkeyboard.view.KeyboardCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * @author libo
 * @ClassName: CheckCarFragment
 * @Description: 车辆核查
 * @date 2018/5/17
 **/
public class CheckCarFragment extends BaseFragment implements View.OnClickListener,
        VerifyManager.CallBackHeCha {

    public static final String TAG = CheckCarFragment.class.getSimpleName();
    //车辆核查相关
    @BindView(R.id.tv_sfzid)
    TextView tvCarNum;
    @BindView(R.id.inputView)
    InputView inputView;
    @BindView(R.id.lock_type)
    Button lockType;
    @BindView(R.id.iv_mc_scan)
    ImageView ivMcScan;
    @BindView(R.id.tv_check_carPlateType)
    TextView tvCheckCarPlateType;
    @BindView(R.id.tv_check_carPlateTypeValue)
    TextView tvCheckCarPlateTypeValue;
    @BindView(R.id.btn_submitSelect)
    Button btnSubmitSelect;

    //车辆相关信息
    @BindView(R.id.tv_detail_car_ownNameIn)
    TextView tvDetailCarOwnNameIn;
    @BindView(R.id.tv_detail_car_ownerCardIn)
    TextView tvDetailCarOwnerCardIn;
    @BindView(R.id.tv_detail_car_ownerGenderIn)
    TextView tvDetailCarOwnerGenderIn;
    @BindView(R.id.tv_detail_car_ownerMZIn)
    TextView tvDetailCarOwnerMZIn;
    @BindView(R.id.tv_detail_car_numIn)
    TextView tvDetailCarNumIn;
    @BindView(R.id.tv_detail_car_plateTypeIn)
    TextView tvDetailCarPlateTypeIn;
    @BindView(R.id.tv_detail_car_identifyCodeIn)
    TextView tvDetailCarIdentifyCodeIn;
    @BindView(R.id.tv_detail_car_carColorIn)
    TextView tvDetailCarCarColorIn;
    @BindView(R.id.tv_detail_car_engineNumIn)
    TextView tvDetailCarEngineNumIn;
    @BindView(R.id.tv_detail_car_addressIn)
    TextView tvDetailCarAddressIn;
    @BindView(R.id.rl_detail_car_info)
    RelativeLayout rlDetailCarInfo;

    //中标车辆相关信息
    @BindView(R.id.tv_detail_car_bid_carTypeIn)
    TextView tvDetailCarBidCarTypeIn;
    @BindView(R.id.tv_detail_car_bid_policeNameIn)
    TextView tvDetailCarBidPoliceNameIn;
    @BindView(R.id.tv_detail_car_bid_taskNameIn)
    TextView tvDetailCarBidTaskNameIn;
    @BindView(R.id.tv_detail_car_bid_linkPersonIn)
    TextView tvDetailCarBidLinkPersonIn;
    @BindView(R.id.tvdetail_car_bid_linkPhoneIn)
    TextView tvdetailCarBidLinkPhoneIn;
    @BindView(R.id.tv_detail_car_bid_checkedDescIn)
    TextView tvDetailCarBidCheckedDescIn;
    @BindView(R.id.rl_detail_car_bid_info)
    RelativeLayout rlDetailCarBidInfo;
    @BindView(R.id.tv_detail_car_bid_taskDescIn)
    TextView tvDetailCarBidTaskDescIn;


    private String carNumber;
    private String json;
    private final List<String> mTestNumber = new ArrayList<>();

    private final Random mRandom = new Random();
    private PopupKeyboard mPopupKeyboard;
    private Unbinder unbinder;
    private List<String> hpzls;
    private List<String> hpzlNames;
    //号牌种类对应的值。  用于接口请求
    private String hpzl;


    public CheckCarFragment() {
    }

    public static CheckCarFragment getInstance() {
        return new CheckCarFragment();
    }


    @Override
    protected int setView() {
        return R.layout.fragment_check_car;
    }

    public void scanCarId(View view) {
        Intent intent = new Intent(getActivity(), OcrCarActivity.class);
        startActivityForResult(intent, CheckPersonFragment.REQUEST_CAR_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CheckPersonFragment.REQUEST_CAR_CODE && resultCode == RESULT_OK) {
            String carResult = data.getStringExtra("carResult");
//            etSfzid.setText(carResult);
            inputView.updateNumber(carResult);
        }
    }

    @Override
    public void initView() {
        super.initView();
        initKeyboard();
        tvCheckCarPlateTypeValue.setText("小型汽车");

    }

    private void initKeyboard() {
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(getActivity());
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(inputView, getActivity());
//        mPopupKeyboard.getKeyboardView()
//                .setKeyboardType(KeyboardType.CIVIL);
        mPopupKeyboard.getKeyboardView().addKeyboardCallback(new KeyboardCallback.Simple() {
            @Override
            public void onConfirmKey() {
                super.onConfirmKey();
                mPopupKeyboard.dismiss(getActivity());
            }
        });

        // KeyboardInputController提供一个默认实现的新能源车牌锁定按钮
        mPopupKeyboard.getController()
                .setDebugEnabled(true)
                .bindLockTypeProxy(new KeyboardInputController.ButtonProxyImpl(lockType) {
                    @Override
                    public void onNumberTypeChanged(boolean isNewEnergyType) {
                        super.onNumberTypeChanged(isNewEnergyType);
                        if (isNewEnergyType) {
                            lockType.setTextColor(getResources().getColor(android.R.color
                                    .holo_green_light));
                            tvCarNum.setText(getString(R.string.newCar_plate_number));
                        } else {
                            lockType.setTextColor(getResources().getColor(android.R.color
                                    .black));
                            tvCarNum.setText(getString(R.string.car_plate_number));
                        }
                    }
                });
    }

    @Override
    public void initListener() {
        super.initListener();
        btnSubmitSelect.setOnClickListener(this);
        ivMcScan.setOnClickListener(this);
    }

    @OnClick({R.id.tv_check_carPlateTypeValue})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mc_scan:
                scanCarId(v);
                break;
            case R.id.btn_submitSelect:
                if (checkInput()) {
                    String defaultIMEI = HBUtils.getDefaultIMEI(getActivity());
                    CheckInfoParamtersEntity paramters = new CheckInfoParamtersEntity
                            (defaultIMEI, "", carNumber, hpzl);

                    VerifyManager.getInstance().verifyPersonAndCar(getActivity(), paramters, this);
                }
                break;
            case R.id.tv_check_carPlateTypeValue:
                selectCarType();
                break;
            default:
                break;

        }

    }

    /**
     * 检查输入的参数
     *
     * @return
     */
    private boolean checkInput() {
        carNumber = inputView.getNumber();
        if (TextUtils.isEmpty(carNumber) || carNumber.length() < 7) {
            ToastUtils.showShort("车牌号输入错误");
            return false;
        }
        if (TextUtils.isEmpty(hpzl)) {
            hpzl = "02";
        }
        return true;
    }

    @Override
    public void callBackSucceed(CheckInfoBaseEntity baseEntity, CheckInfoZBPersonEntity
            zbPersonEntity, CheckInfoZBCarEntity zbCarEntity) {
        String taskName = null;
        CarCheckEntity car = new CarCheckEntity();
        if (baseEntity != null) {
//            CarCheckEntity car = baseEntity.BaseEntityToCarCheckEntity(car1);

            //车辆相关信息
            rlDetailCarInfo.setVisibility(View.VISIBLE);
            tvDetailCarOwnNameIn.setText(car.getCzxm());
            tvDetailCarOwnerCardIn.setText(car.getCzsfhm());
            tvDetailCarOwnerGenderIn.setText(car.getXb());
            tvDetailCarOwnerMZIn.setText(car.getMz());
            tvDetailCarNumIn.setText(carNumber);
            tvDetailCarPlateTypeIn.setText(hpzl);
            tvDetailCarIdentifyCodeIn.setText(car.getClsbdm());
//		tvDetailCarCheckTimeIn.setText(car.);
            tvDetailCarCarColorIn.setText(car.getClys());
            tvDetailCarEngineNumIn.setText(car.getFdjh());
            tvDetailCarAddressIn.setText(car.getAddres());
        }
        if (zbCarEntity != null) {
            car = zbCarEntity.addZBCarInfo(new CarCheckEntity());

            car.setInAppDate(DateTools.getLongCurryDate());

            try {
                car.saveThrows();
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage());
            }

            //中标车辆相关信息
            rlDetailCarBidInfo.setVisibility(View.VISIBLE);

            tvDetailCarBidCarTypeIn.setText(car.getCllb());
            tvDetailCarBidPoliceNameIn.setText(car.getPoliceName());
            tvDetailCarBidTaskDescIn.setText(car.getTaskNames());
            tvDetailCarBidLinkPersonIn.setText(car.getContacts());
            tvdetailCarBidLinkPhoneIn.setText(car.getContactCall());
            tvDetailCarBidCheckedDescIn.setText(car.getDescCar());
            //如果车辆中标后，弹框提醒
            new MaterialDialog.Builder(getActivity())
                    .title("提示")
                    .contentColor(Color.RED)
                    .content(car.getCllb().equals("null") ? "无" : car.getCllb())

                    .show();

        } else {
            car.setCph(carNumber);
            car.setHpzl(tvCheckCarPlateTypeValue.getText().toString());
            car.setInAppDate(DateTools.getLongCurryDate());
            try {
                car.saveThrows();
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
            rlDetailCarBidInfo.setVisibility(View.GONE);
            ToastUtils.showShort("未查到中标车辆相关信息");
        }

    }

    @Override
    public void callBackError(Response<String> response) {
        Log.e(TAG, "异常信息：" + response.getException().getLocalizedMessage());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 获取车辆类型
     */
    private void selectCarType() {
        Map<String, String> hpzlMap = DicDataCache.get().gethpzlMap();
        hpzls = new ArrayList<>();
        hpzlNames = new ArrayList<>();
        for (Map.Entry<String, String> entry : hpzlMap.entrySet()) {
            hpzls.add(entry.getKey());
            hpzlNames.add(entry.getValue());
        }

        String[] datas = new String[hpzlNames.size()];

        new MaterialDialog.Builder(getActivity())
                .items(hpzlNames)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position,
                                            CharSequence text) {
                        tvCheckCarPlateTypeValue.setText(hpzlNames.get(position));
                        hpzl = hpzls.get(position);
                    }
                })
                .show();
    }
}
