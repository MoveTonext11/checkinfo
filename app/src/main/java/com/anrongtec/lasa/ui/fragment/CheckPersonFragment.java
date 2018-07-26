package com.anrongtec.lasa.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.anrongtec.lasa.entity.CheckInfoBaseEntity;
import com.anrongtec.lasa.entity.CheckInfoParamtersEntity;
import com.anrongtec.lasa.entity.CheckInfoZBCarEntity;
import com.anrongtec.lasa.entity.CheckInfoZBPersonEntity;
import com.anrongtec.lasa.entity.PersonCheckEntity;
import com.anrongtec.lasa.manager.VerifyManager;
import com.anrongtec.lasa.utils.DateTools;
import com.anrongtec.lasa.utils.HBUtils;
import com.anrongtec.lasa.utils.IDCardUtil;
import com.anrongtec.lasa.utils.NFCReadTask;
import com.anrongtec.lasa.utils.NFCReaderHelper;
import com.anrongtec.lasa.utils.ObtainInfo;
import com.anrongtec.lasa.view.CustomKeyBoard;
import com.anrongtec.ocr.OcrCarActivity;
import com.anrongtec.ocr.OcrPersonActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.Response;

import java.util.Map;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.senter.model.IdentityCardZ;

import static android.app.Activity.RESULT_OK;

/**
 * @author libo
 * @ClassName: CheckPersonFragment
 * @Description: 人员核查
 * @date 2018/5/17
 **/
@SuppressLint("ValidFragment")
public class CheckPersonFragment extends BaseFragment implements View.OnClickListener,
        VerifyManager.CallBackHeCha {
    public static final String TAG = CheckPersonFragment.class.getSimpleName();
    public static final int REQUEST_PERSON_CODE = 10;
    public static final int REQUEST_CAR_CODE = 20;
    //身份证输入控件
    //ocr按钮
    @BindView(R.id.iv_mc_scan)
    ImageView ivMcScan;
    //提交按钮
    @BindView(R.id.btn_submitSelect)
    Button btnSubmitSelect;
    //人员信息
    @BindView(R.id.tv_detail_person_info_name)
    TextView tvDetailPersonInfoName;
    @BindView(R.id.tv_detail_person_info_nameIn)
    TextView tvDetailPersonInfoNameIn;
    @BindView(R.id.tv_detail_person_info_idCard)
    TextView tvDetailPersonInfoIdCard;
    @BindView(R.id.tv_detail_person_info_idCardIn)
    TextView tvDetailPersonInfoIdCardIn;
    @BindView(R.id.tv_detail_person_info_gender)
    TextView tvDetailPersonInfoGender;
    @BindView(R.id.tv_detail_person_info_genderIn)
    TextView tvDetailPersonInfoGenderIn;
    @BindView(R.id.tv_detail_person_info_nation)
    TextView tvDetailPersonInfoNation;
    @BindView(R.id.tv_detail_person_info_nationIn)
    TextView tvDetailPersonInfoNationIn;
    @BindView(R.id.tv_detail_person_info_birthday)
    TextView tvDetailPersonInfoBirthday;
    @BindView(R.id.tv_detail_person_info_birthdayIn)
    TextView tvDetailPersonInfoBirthdayIn;
    @BindView(R.id.tv_detail_person_info_organ)
    TextView tvDetailPersonInfoOrgan;
    @BindView(R.id.tv_detail_person_info_organIn)
    TextView tvDetailPersonInfoOrganIn;
    @BindView(R.id.tv_detail_person_info_time)
    TextView tvDetailPersonInfoTime;
    @BindView(R.id.tv_detail_person_info_timeIn)
    TextView tvDetailPersonInfoTimeIn;
    @BindView(R.id.tv_detail_person_info_checkTime)
    TextView tvDetailPersonInfoCheckTime;
    @BindView(R.id.tv_detail_person_info_checkTimeIn)
    TextView tvDetailPersonInfoCheckTimeIn;
    @BindView(R.id.tv_detail_person_info_source)
    TextView tvDetailPersonInfoSource;
    @BindView(R.id.tv_detail_person_info_sourceIn)
    TextView tvDetailPersonInfoSourceIn;
    @BindView(R.id.tv_detail_person_info_policeNum)
    TextView tvDetailPersonInfoPoliceNum;
    @BindView(R.id.tv_detail_person_info_policeNumIn)
    TextView tvDetailPersonInfoPoliceNumIn;
    @BindView(R.id.tv_detail_person_info_checkPoint)
    TextView tvDetailPersonInfoCheckPoint;
    @BindView(R.id.tv_detail_person_info_checkPointIn)
    TextView tvDetailPersonInfoCheckPointIn;
    @BindView(R.id.tv_detail_person_info_taskName)
    TextView tvDetailPersonInfoTaskName;
    @BindView(R.id.tv_detail_person_info_taskNameIn)
    TextView tvDetailPersonInfoTaskNameIn;
    @BindView(R.id.tv_detail_person_info_jyaq)
    TextView tvDetailPersonInfoJyaq;
    @BindView(R.id.tv_detail_person_info_jyaqIn)
    TextView tvDetailPersonInfoJyaqIn;
    @BindView(R.id.tv_detail_person_info_address)
    TextView tvDetailPersonInfoAddress;
    @BindView(R.id.tv_detail_person_info_addressIn)
    TextView tvDetailPersonInfoAddressIn;
    @BindView(R.id.iv_detail_person_info_avatar)
    ImageView ivDetailPersonInfoAvatar;
    @BindView(R.id.rl_detail_person_info)
    RelativeLayout rlDetailPersonInfo;
    @BindView(R.id.tv_detail_person_info_locationXIn)
    TextView tvLocationX;
    @BindView(R.id.tv_detail_person_info_locationYIn)
    TextView tvLocationY;

    //人员中标信息
    @BindView(R.id.tv_detail_person_bid_type)
    TextView tvDetailPersonBidType;
    @BindView(R.id.tv_detail_person_bid_TypeIn)
    TextView tvDetailPersonBidTypeIn;
    @BindView(R.id.tv_detail_person_bid_level)
    TextView tvDetailPersonBidLevel;
    @BindView(R.id.tv_detail_person_bid_levelIn)
    TextView tvDetailPersonBidLevelIn;
    @BindView(R.id.tv_detail_person_bid_dealType)
    TextView tvDetailPersonBidDealType;
    @BindView(R.id.tv_detail_person_bid_dealTypeIn)
    TextView tvDetailPersonBidDealTypeIn;
    @BindView(R.id.tv_detail_person_bid_remark)
    TextView tvDetailPersonBidRemark;
    @BindView(R.id.tv_detail_person_bid_remarkIn)
    TextView tvDetailPersonBidRemarkIn;
    @BindView(R.id.tv_detail_person_bid_link)
    TextView tvDetailPersonBidLink;
    @BindView(R.id.tv_detail_person_bid_linkIn)
    TextView tvDetailPersonBidLinkIn;
    @BindView(R.id.tv_detail_person_bid_linkPhone)
    TextView tvDetailPersonBidLinkPhone;
    @BindView(R.id.tv_detail_person_bid_linkPhoneIn)
    TextView tvDetailPersonBidLinkPhoneIn;
    @BindView(R.id.tv_detail_person_bid_Dept)
    TextView tvDetailPersonBidDept;
    @BindView(R.id.tv_detail_person_bid_linkDeptIn)
    TextView tvDetailPersonBidLinkDeptIn;
    @BindView(R.id.rl_detail_person_bid_info)
    RelativeLayout rlDetailPersonBidInfo;


    private Unbinder unbinder;
    private String perCardID;
    private String json;
    public NFCReaderHelper nfcHelper;
    private NFCReadTask nfcReadTask;
    private AsyncTask<Void, Void, String> nfcTask;
    private CustomKeyBoard etSfzIdValue;

    @SuppressLint("ValidFragment")
    public CheckPersonFragment(NFCReaderHelper nfc) {
        // Required empty public constructor
        nfcHelper = nfc;
    }


    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void initListener() {
        super.initListener();
        ivMcScan.setOnClickListener(this);
        btnSubmitSelect.setOnClickListener(this);
    }

    @Override
    protected int setView() {
        return R.layout.fragment_check_person;
    }

    public void scanIdCard(View view) {
        Intent intent = new Intent(getActivity(), OcrPersonActivity.class);
        startActivityForResult(intent, REQUEST_PERSON_CODE);
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
        if (requestCode == REQUEST_PERSON_CODE && resultCode == RESULT_OK) {
            String recogResult = data.getStringExtra("recogResult");
            etSfzIdValue.setText(recogResult);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mc_scan:
                scanIdCard(v);
                break;
            case R.id.iv_car_scan:
                scanCarId();
                break;
            case R.id.btn_submitSelect:

                String defaultIMEI = HBUtils.getDefaultIMEI(getContext());
                if (IDCardUtil.managerIDCard(etSfzIdValue)) {
                    perCardID = etSfzIdValue.getText().toString();

                    CheckInfoParamtersEntity paramters = new CheckInfoParamtersEntity
                            (defaultIMEI, perCardID,
                                    "", "");
                    VerifyManager.getInstance().verifyPersonAndCar(getActivity(), paramters, this);

                } else {
                    ToastUtils.showShort("输入有误，请检查");
                }
                break;
        }
    }


    @Override
    public void callBackSucceed(CheckInfoBaseEntity baseEntity, CheckInfoZBPersonEntity
            zbPersonEntity, CheckInfoZBCarEntity zbCarEntity) {
        if (baseEntity != null) {
            PersonCheckEntity person1 = new PersonCheckEntity();
            PersonCheckEntity person = baseEntity.BaseEntityToPerCheckEntity(person1);
            if (zbPersonEntity != null) {
                person = zbPersonEntity.addZBPersonInfo(person);
            }
            Map<String, String> commonInfoMap = ObtainInfo.getInstance().getCommonInfoMap();
            person.setUserid(commonInfoMap.get("Policeid"));
            person.setLocationX(commonInfoMap.get("LocationX"));
            person.setLocationY(commonInfoMap.get("LocationY"));
            person.setInAppDate(DateTools.getLongCurryDate());
            try {
                person.saveThrows();
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
            //人员信息
            rlDetailPersonInfo.setVisibility(View.VISIBLE);
            tvDetailPersonInfoIdCardIn.setText(person.getSfhm());
            tvDetailPersonInfoNameIn.setText(person.getXm());
            tvDetailPersonInfoGenderIn.setText(person.getXb());
            tvDetailPersonInfoNationIn.setText(person.getMz());
            tvDetailPersonInfoBirthdayIn.setText(person.getCsrq());
            tvDetailPersonInfoOrganIn.setText(person.getQfjg());
            tvDetailPersonInfoSourceIn.setText(DateTools.getStrTime(person.getInAppDate()));
            tvDetailPersonInfoTimeIn.setText(person.getYxqx());
            tvDetailPersonInfoAddressIn.setText(person.getAddres());
            tvDetailPersonInfoPoliceNumIn.setText(person.getUserid());
            tvLocationX.setText(person.getLocationX());
            tvLocationY.setText(person.getLocationY());
            // TODO 照片暂时不加载
//            Glide.with(this).load(person.getZp()).into(ivDetailPersonInfoAvatar);
//            ivDetailPersonInfoAvatar
            if (zbPersonEntity != null) {
                //中标人员信息
                rlDetailPersonBidInfo.setVisibility(View.VISIBLE);
                tvDetailPersonBidTypeIn.setText(person.getRylb());
                tvDetailPersonBidLevelIn.setText(person.getPerLevel());
                tvDetailPersonBidDealTypeIn.setText(person.getCzfs());
                tvDetailPersonBidRemarkIn.setText(person.getRemarkDesc());
                tvDetailPersonBidLinkIn.setText(person.getBklxrName());
                tvDetailPersonBidLinkPhoneIn.setText(person.getBkrCall());
                tvDetailPersonBidLinkDeptIn.setText(person.getBkdw());
                //如果人员中标后，弹框提醒
                new MaterialDialog.Builder(getActivity())
                        .title("提示")
                        .contentColor(Color.RED)
                        .content(person.getRylb().equals("null") ? "无" : person.getRylb())
                        .show();
            } else {
                rlDetailPersonBidInfo.setVisibility(View.GONE);
            }
        } else {
            rlDetailPersonInfo.setVisibility(View.GONE);
            rlDetailPersonBidInfo.setVisibility(View.GONE);
            ToastUtils.showShort("未查到人员相关信息");
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
        etSfzIdValue = (CustomKeyBoard) rootView.findViewById(R.id.et_sfzidValue);

        etSfzIdValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //读取
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
                etSfzIdValue.setText(s.cardNo);
                nfcHelper.disable();
            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        nfcTask = null;
    }
}
