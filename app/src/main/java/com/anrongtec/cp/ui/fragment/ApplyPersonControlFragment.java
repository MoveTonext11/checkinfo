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
import android.widget.RadioButton;
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
import com.anrongtec.ocr.OcrPersonActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.Response;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.senter.model.IdentityCardZ;

import static android.app.Activity.RESULT_OK;

/**
 * @author libo
 * @Description: 人员布控申请
 * @date 2018/5/13
 */

@SuppressLint("ValidFragment")
public class ApplyPersonControlFragment extends BaseFragment implements View.OnClickListener,
        ControlManager.CallBackControl {
    public static final String TAG = ApplyPersonControlFragment.class.getSimpleName();
    @BindView(R.id.et_apply_person_nameValue)
    MaterialEditText etApplyPersonNameValue;    //姓名
    @BindView(R.id.et_apply_person_IDVCardIn)
    CustomKeyBoard etApplyPersonIDVCardIn;    //身份证号
    @BindView(R.id.et_apply_person_typeIn)
    TextView etApplyPersonTypeIn;       //人员类别
    @BindView(R.id.et_apply_person_addressIn)
    MaterialEditText etApplyPersonAddressIn;    //住址
    @BindView(R.id.et_apply_person_jyaqIn)
    MaterialEditText etApplyPersonJyaqIn;       //列控原因

    @BindView(R.id.et_apply_person_lkrIn)
    MaterialEditText etApplyPersonLkrIn;          //列控人
    @BindView(R.id.et_apply_person_lkrPhoneIn)
    MaterialEditText etApplyPersonLkrPhoneIn;      //列控联系方式
    @BindView(R.id.et_apply_person_lxrIn)
    MaterialEditText etApplyPersonLxrIn;            //管辖民警
    @BindView(R.id.et_apply_person_lxrPhoneIn)
    MaterialEditText etApplyPersonLxrPhoneIn;   //管辖民警电话
    @BindView(R.id.et_apply_person_lxrMarkIn)
    MaterialEditText etApplyPersonLxrMarkIn;    //描述
    @BindView(R.id.iv_mc_scan)
    ImageView OCRIDCard;        //OCR图像扫描
    @BindView(R.id.btn_sub)
    Button btnSub;      //提交按钮
    Unbinder unbinder;
    @BindView(R.id.rb_man)
    RadioButton rbMan;          //男
    @BindView(R.id.rb_woman)
    RadioButton rbWoman;        //女
    private Map<String, String> paramMaps;
    private String personIDCard;
    private String personType;
    private List<String> ryzl;
    private List<String> ryzlNames;
    private NFCReaderHelper nfcHelper;
    private AsyncTask<Void, Void, String> nfcTask;
    private NFCReadTask nfcReadTask;

    @SuppressLint("ValidFragment")
    public ApplyPersonControlFragment(NFCReaderHelper nfc) {
        nfcHelper = nfc;
    }


    @Override
    protected int setView() {
        return R.layout.fragment_apply_perosn_control;
    }

    @Override
    public void initView() {
        paramMaps = new HashMap<>();
    }

    @Override
    public void initListener() {
        btnSub.setOnClickListener(this);
        OCRIDCard.setOnClickListener(this);
        etApplyPersonTypeIn.setOnClickListener(this);
    }

    /**
     * 输入参数的非空的校验
     *
     * @return
     */
    private boolean isNotEmpty() {
        if (TextUtils.isEmpty(etApplyPersonNameValue.getText())) {
            etApplyPersonNameValue.setError("被列控人的姓名不能为空");
            return false;
        }

        if (IDCardUtil.managerIDCard(etApplyPersonIDVCardIn)) {
            personIDCard = etApplyPersonIDVCardIn.getText().toString();
        } else {
            etApplyPersonIDVCardIn.setError("身份证输入有误请检查");
            return false;
        }
        if (TextUtils.isEmpty(personType)) {
            ToastUtils.showShort("人员类别不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etApplyPersonJyaqIn.getText())) {
            etApplyPersonJyaqIn.setError("列控原因不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etApplyPersonLxrIn.getText())) {
            etApplyPersonLxrIn.setError("管辖民警不能为空");
            return false;
        }
        if (TextUtils.isEmpty(etApplyPersonLxrPhoneIn.getText())) {
            etApplyPersonLxrPhoneIn.setError("管辖民警电话不能为空");
            return false;
        }


        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sub:
                if (isNotEmpty()) {
                    paramMaps.put("xm", etApplyPersonNameValue.getText().toString());
                    paramMaps.put("xb", getXBCode());
                    paramMaps.put("sfzh", personIDCard);
                    paramMaps.put("rylb", personType);
                    paramMaps.put("HJDQH", etApplyPersonAddressIn.getText().toString());
                    paramMaps.put("jyaq", etApplyPersonJyaqIn.getText().toString());
                    paramMaps.put("lxr", etApplyPersonLxrIn.getText().toString());
                    paramMaps.put("lxdh", etApplyPersonLxrPhoneIn.getText().toString());
                    paramMaps.put("gxmj", etApplyPersonLkrIn.getText().toString());
                    paramMaps.put("gxmjdh", etApplyPersonLkrPhoneIn.getText().toString());
                    paramMaps.put("remark", etApplyPersonLxrMarkIn.getText().toString());
                    ControlManager.getInstance().personControl(getActivity(), paramMaps, this);
                }
                break;
            case R.id.iv_mc_scan:
                scanIdCard(v);
                break;
            case R.id.et_apply_person_typeIn:
                selectCarType();
                break;
        }
    }

    public void scanIdCard(View view) {
        Intent intent = new Intent(getActivity(), OcrPersonActivity.class);
        startActivityForResult(intent, CheckPersonFragment.REQUEST_PERSON_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CheckPersonFragment.REQUEST_PERSON_CODE && resultCode == RESULT_OK) {
            String recogResult = data.getStringExtra("recogResult");
            etApplyPersonIDVCardIn.setText(recogResult);
        }
    }


    private void selectCarType() {
        Map<String, String> ryzlMap = DicDataCache.get().getRyzlMap();
//        ryzl = new ArrayList<>();
        ryzlNames = new ArrayList<>();
        for (Map.Entry<String, String> entry : ryzlMap.entrySet()) {
//            ryzl.add(entry.getKey());
            ryzlNames.add(entry.getValue());
        }
        new MaterialDialog.Builder(getActivity())
                .items(ryzlNames)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position,
                                            CharSequence text) {
                        etApplyPersonTypeIn.setText(ryzlNames.get(position));
                        personType = ryzlNames.get(position);
//                        ryzl = ryzl.get(position);
                    }
                })
                .show();
    }


    /**
     * 获取性别对应的码
     *
     * @return
     */
    private String getXBCode() {
        String gender;
        if (rbMan.isChecked()) {
            gender = "男";
        } else {
            gender = "女";
        }
        return DicDataCache.get().getXBCode(gender);
    }

    @Override
    public void callBackSucceed(DataResult dataResult) {
        if (dataResult.getResultCode().equals(DataResult.DATARESULTSUCCESS)) {
            ToastUtils.showShort("人员列控成功");
        } else {
            ToastUtils.showShort("人员列控失败，" + dataResult.getData() == null ? "" : dataResult
                    .getData().toString());
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
        String name = "*被列控人的姓名";
        etApplyPersonNameValue.setHint(HBUtils.requiredVerify(name));
        String idCard = "*请输入身份证号码";
        etApplyPersonIDVCardIn.setHint(HBUtils.requiredVerify(idCard));
        String controlReason = "*列控原因";
        etApplyPersonJyaqIn.setHint(HBUtils.requiredVerify(controlReason));
        String personType = "*人员类别";
        etApplyPersonTypeIn.setText(HBUtils.requiredVerify(personType));
        String policeName = "*管辖民警";
        etApplyPersonLxrIn.setHint(HBUtils.requiredVerify(policeName));
        String policePhone = "*管辖民警电话";
        etApplyPersonLxrPhoneIn.setHint(HBUtils.requiredVerify(policePhone));
        etApplyPersonIDVCardIn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    nfcHelper.read();//开始执行读取nfc卡功能
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
                etApplyPersonIDVCardIn.setText(s.cardNo);
                etApplyPersonAddressIn.setText(s.address);
                etApplyPersonNameValue.setText(s.name);
                if (s.sex.equals("男")){
                    rbMan.isChecked();
                }else{
                    rbWoman.isChecked();
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        nfcHelper = null;
    }
}
