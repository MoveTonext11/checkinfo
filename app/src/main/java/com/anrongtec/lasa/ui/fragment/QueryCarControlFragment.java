package com.anrongtec.lasa.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.anrongtec.lasa.R;
import com.anrongtec.lasa.entity.CarControlInfoEntity;
import com.anrongtec.lasa.ui.ControlListActivity;
import com.anrongtec.lasa.utils.DicDataCache;
import com.anrongtec.ocr.OcrCarActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.carkeyboard.KeyboardInputController;
import com.carkeyboard.support.PopupKeyboard;
import com.carkeyboard.view.InputView;
import com.carkeyboard.view.KeyboardCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * @author libo
 * @ClassName: QueryCarControlFragment
 * @Description: 车辆布控人查询
 * @date 2018/5/17
 **/
public class QueryCarControlFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_carNum)
    TextView tvCarID;           //车牌名称
    @BindView(R.id.inputView)
    InputView inputCarId;     //车牌号输入
    @BindView(R.id.iv_mc_scan)
    ImageView ivMcScanCar;  //车牌OCR
    @BindView(R.id.btn_submitSelect)
    Button btnSubmitSelect;         //查询按钮
    @BindView(R.id.lock_type)
    Button btnLockType;     //车牌号输入切换

    @BindView(R.id.tv_check_carPlateTypeValue)
    TextView carPlateType;  //选择号牌种类

    private Unbinder unbinder;
    private String plateNum;
    private String carId;
    private BaseQuickAdapter<CarControlInfoEntity, BaseViewHolder> adapter;
    private static final String TAG = QueryCarControlFragment.class.getSimpleName();
    private PopupKeyboard mPopupKeyboard;

    private List<String> hpzls;
    private List<String> hpzlNames;

    public QueryCarControlFragment() {
        // Required empty public constructor
    }

    public static QueryCarControlFragment getInstance() {
        return new QueryCarControlFragment();
    }


    @Override
    protected int setView() {
        return R.layout.fragment_control_query_car;
    }

    @Override
    public void initView() {
        initKeyboard();
        carPlateType.setText("小型汽车");
    }

    @Override
    public void initListener() {
        carPlateType.setOnClickListener(this);
        ivMcScanCar.setOnClickListener(this);
        btnSubmitSelect.setOnClickListener(this);
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
            inputCarId.updateNumber(carResult);
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_check_carPlateTypeValue:
                selectCarType();
                break;
            case R.id.iv_mc_scan:
                scanCarId(v);
                break;
            case R.id.btn_submitSelect:

                if (checkInput()) {
                    ControlListActivity.startCar(getContext(), carId, TextUtils.isEmpty(plateNum)
                            ? "02" : plateNum);
                } else {
                    ToastUtils.showShort("请输入正确的车牌号");
                }
                break;
        }
    }

    /**
     * 检查输入
     */
    private boolean checkInput() {
        carId = inputCarId.getNumber();
//        sfzId = etSfzid.getText().toString();
        int i = 1;
        if (TextUtils.isEmpty(carId) || carId.length() < 7) {
            carId = null;
            i = i + 4;
        }
        return i == 7 ? false : true;
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
                        carPlateType.setText(hpzlNames.get(position));
                        plateNum = hpzls.get(position);
                    }
                })
                .show();
    }


    private void initKeyboard() {
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(getActivity());
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(inputCarId, getActivity());
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
                .bindLockTypeProxy(new KeyboardInputController.ButtonProxyImpl(btnLockType) {
                    @Override
                    public void onNumberTypeChanged(boolean isNewEnergyType) {
                        super.onNumberTypeChanged(isNewEnergyType);
                        if (isNewEnergyType) {
                            btnLockType.setTextColor(getResources().getColor(android.R.color
                                    .holo_green_light));
                            tvCarID.setText(getString(R.string.newCar_plate_number));
                        } else {
                            btnLockType.setTextColor(getResources().getColor(android.R.color
                                    .black));
                            tvCarID.setText(getString(R.string.car_plate_number));
                        }
                    }
                });
    }
}