package com.anrongtec.cp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.anrongtec.cp.R;
import com.anrongtec.cp.interfaces.result.DataResult;
import com.anrongtec.cp.manager.ControlManager;
import com.anrongtec.cp.utils.DicDataCache;
import com.anrongtec.cp.utils.HBUtils;
import com.anrongtec.cp.utils.IDCardUtil;
import com.anrongtec.cp.utils.NFCReadTask;
import com.anrongtec.cp.utils.NFCReaderHelper;
import com.anrongtec.cp.view.CustomKeyBoard;
import com.anrongtec.ocr.OcrCarActivity;
import com.anrongtec.ocr.OcrPersonActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.carkeyboard.KeyboardInputController;
import com.carkeyboard.support.PopupKeyboard;
import com.carkeyboard.view.InputView;
import com.carkeyboard.view.KeyboardCallback;
import com.lzy.okgo.model.Response;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.senter.model.IdentityCardZ;

import static android.app.Activity.RESULT_OK;

/**
 * @author libo
 * @Description: 车辆布控申请
 * @date 2018/5/13
 */

@SuppressLint("ValidFragment")
public class ApplyCarControlFragment extends BaseFragment implements View.OnClickListener,
        ControlManager.CallBackControl {
    public static final String TAG = ApplyCarControlFragment.class.getSimpleName();
    @BindView(R.id.tv_carControl_PlateNum)
    TextView tvCarPlateNum;
    @BindView(R.id.et_carControl_PlateNumValue)
    InputView etCarPlateNumValue;               //车牌号
    @BindView(R.id.btn_lock_type)
    Button btnLockType;        //切换车牌输入
    @BindView(R.id.et_carControl_personIDValue)
    CustomKeyBoard etPersonIDValue;             // 车主身份证
    //    @BindView(R.id.et_carControl_identifyCodeValue)
//    MaterialEditText etCarIdentifyCode;     //车辆识别代码
    @BindView(R.id.et_carControl_ownerNameValue)
    MaterialEditText etOwnerName;           //车主姓名
    //    @BindView(R.id.et_carControl_carEngineNameValue)
//    MaterialEditText etCarEngineNameValue;      //发动机号
    @BindView(R.id.et_carControl_carColorValue)
    MaterialEditText etCarColor;        //车辆颜色
    @BindView(R.id.et_carControl_DealTypeValue)
    MaterialEditText etCarDealType;     //处置方式
    @BindView(R.id.et_carControl_carTypeValue)
    MaterialEditText etCarType;         //车辆类别（盗抢、等等）
    @BindView(R.id.tv_carControl_carPlateTypeValue)
    TextView tvCarControlCarPlateTypeValue;     //号牌种类
    @BindView(R.id.et_carControl_linkPersonValue)
    MaterialEditText etLinkPerson;      //列控人
    @BindView(R.id.et_carControl_linkPersonPhoneValue)
    MaterialEditText etLinkPersonPhone;     //列控人联系电话
    @BindView(R.id.et_carControl_linkPersonRemarkValue)
    MaterialEditText etRemark;          //描述
    @BindView(R.id.iv_mc_scan)
    ImageView OCRIDCard;        //OCR图像扫描
    @BindView(R.id.iv_car_scan)
    ImageView OCRCarCard;
    @BindView(R.id.btn_sub)
    Button btnSub;          //提交
    @BindView(R.id.et_carControl_czPhoneValue)
    MaterialEditText etCarControlCzPhoneValue;


    private Unbinder unbinder;
    private Map<String, String> params;
    private String ownerIDCard;

    private String hpzl;
    private List<String> hpzls;
    private List<String> hpzlNames;
    private PopupKeyboard mPopupKeyboard;
    private NFCReadTask nfcReadTask;
    private AsyncTask<Void, Void, String> nfcTask;
    public NFCReaderHelper nfcHelper;

    @SuppressLint("ValidFragment")
    public ApplyCarControlFragment(NFCReaderHelper nfc) {
        nfcHelper = nfc;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_apply_car_control;
    }

    @Override
    public void initView() {
        params = new HashMap<>();
        tvCarControlCarPlateTypeValue.setText("小型汽车");
        initKeyboard();
    }

    @Override
    public void initListener() {
        btnSub.setOnClickListener(this);
        OCRIDCard.setOnClickListener(this);
        OCRCarCard.setOnClickListener(this);
    }

    @OnClick({R.id.tv_carControl_carPlateTypeValue})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sub:

                if (isNotEmpty()) {
                    params.put("carNumber", etCarPlateNumValue.getNumber());
                    params.put("carPerId", ownerIDCard);
                    params.put("clsbdm", "");
                    params.put("carPerName", etOwnerName.getText().toString());
                    params.put("fdjh", "");
                    params.put("clys", etCarColor.getText().toString());
                    params.put("dispose", etCarDealType.getText().toString());
                    params.put("keyType", etCarType.getText().toString());
                    params.put("hpzl", hpzl);
                    params.put("lxr", TextUtils.isEmpty(etLinkPerson.getText()) ? "" :
                            etLinkPerson.getText().toString().trim());
                    params.put("lxdh", etLinkPersonPhone.getText().toString());
                    params.put("remark", etRemark.getText().toString());

                    ControlManager.getInstance().carControl(getActivity(), params, this);
                }
                break;
            case R.id.tv_carControl_carPlateTypeValue:
                selectCarType();
                break;
            case R.id.iv_mc_scan:
                scanIdCard(v);
                break;
            case R.id.iv_car_scan:
                scanCarId();
                break;
            default:
                break;
        }
    }

    /**
     * 输入参数的非空的校验
     *
     * @return
     */
    private boolean isNotEmpty() {
        if (TextUtils.isEmpty(etCarPlateNumValue.getNumber())) {
            ToastUtils.showShort("车牌号不能为空");
            return false;
        }

        if (IDCardUtil.managerIDCard(etPersonIDValue)) {
            ownerIDCard = etPersonIDValue.getText().toString();
        } else {
            etPersonIDValue.setError("身份证输入有误请检查");
            return false;
        }

        if (TextUtils.isEmpty(etOwnerName.getText())) {
            etOwnerName.setError("车主姓名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etCarType.getText())) {
            etCarType.setError("车辆类型不能为空");
            return false;
        }
        if (TextUtils.isEmpty(hpzl)) {
            hpzl = "02";
        }
        if (TextUtils.isEmpty(etCarDealType.getText())) {
            etCarDealType.setError("处置方式不能为空");
            return false;
        }
        return true;
    }

    public void scanIdCard(View view) {
        Intent intent = new Intent(getActivity(), OcrPersonActivity.class);
        startActivityForResult(intent, CheckPersonFragment.REQUEST_PERSON_CODE);
    }

    /**
     * 扫描车牌
     */
    public void scanCarId() {
        Intent intent = new Intent(getActivity(), OcrCarActivity.class);
        startActivityForResult(intent, CheckPersonFragment.REQUEST_CAR_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CheckPersonFragment.REQUEST_PERSON_CODE && resultCode == RESULT_OK) {
            String recogResult = data.getStringExtra("recogResult");
            etPersonIDValue.setText(recogResult);
        }
        if (requestCode == CheckPersonFragment.REQUEST_CAR_CODE && resultCode == RESULT_OK) {
            String carResult = data.getStringExtra("carResult");
            etCarPlateNumValue.updateNumber(carResult);
        }
    }


    @Override
    public void callBackSucceed(DataResult dataResult) {
        if (dataResult.getResultCode().equals(DataResult.DATARESULTSUCCESS)) {
            ToastUtils.showShort("车辆布控申请成功");
        } else {
            ToastUtils.showShort("车辆布控申请失败，" + dataResult.getData().toString());
        }

    }

    @Override
    public void callBackError(Response<String> response) {
        Log.e(TAG, response.getException().getLocalizedMessage());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        String czIDCard = "*请输入车主身份证号码";
        etPersonIDValue.setHint(HBUtils.requiredVerify(czIDCard));
        String name = "*车主姓名";
        etOwnerName.setHint(HBUtils.requiredVerify(name));
        String dealType = "*处置方式";
        etCarDealType.setHint(HBUtils.requiredVerify(dealType));
        String plate = "*号牌种类";
        tvCarControlCarPlateTypeValue.setText(HBUtils.requiredVerify(plate));
        String carType = "*车辆类别";
        etCarType.setHint(HBUtils.requiredVerify(carType));
        etPersonIDValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    nfcHelper.read();
                }
            }
        });
        return rootView;
    }

    public void NfcMessage(Intent intent) {
        if (nfcTask == null) {
            nfcReadTask = new NFCReadTask(intent, nfcHelper);
            nfcTask = nfcReadTask.executeOnExecutor(Executors.newCachedThreadPool());
        }
        nfcReadTask.textmessage(new NFCReadTask.NfcMessage() {
            @Override
            public void BackMessage(IdentityCardZ s) {
                etPersonIDValue.setText(s.cardNo);
//                etOwnerName.setText(s.name);//????姓名下移了
//                nfcHelper.disable();
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        nfcTask = null;
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
        new MaterialDialog.Builder(getActivity())
                .items(hpzlNames)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position,
                                            CharSequence text) {
                        tvCarControlCarPlateTypeValue.setText(hpzlNames.get(position));
                        hpzl = hpzls.get(position);
                    }
                })
                .show();
    }

    private void initKeyboard() {
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(getActivity());
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(etCarPlateNumValue, getActivity());
//        mPopupKeyboard.getKeyboardView().setKeyboardType(KeyboardType.CIVIL);
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
                            tvCarPlateNum.setText(getString(R.string.newCar_plate_number));
                        } else {
                            btnLockType.setTextColor(getResources().getColor(android.R.color
                                    .black));
                            tvCarPlateNum.setText(getString(R.string.car_plate_number));
                        }
                    }
                });
    }
}
