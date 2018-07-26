package com.anrongtec.lasa.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.anrongtec.lasa.R;
import com.anrongtec.lasa.ui.ControlListActivity;
import com.anrongtec.lasa.utils.IDCardUtil;
import com.anrongtec.lasa.utils.NFCReadTask;
import com.anrongtec.lasa.utils.NFCReaderHelper;
import com.anrongtec.lasa.view.CustomKeyBoard;
import com.anrongtec.ocr.OcrPersonActivity;
import com.blankj.utilcode.util.ToastUtils;

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.senter.model.IdentityCardZ;

import static android.app.Activity.RESULT_OK;

/**
 * @author libo
 * @ClassName: QueryPersonControlFragment
 * @Description: 人员布控人查询
 * @date 2018/5/17
 **/
@SuppressLint("ValidFragment")
public class QueryPersonControlFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = QueryPersonControlFragment.class.getSimpleName();
    @BindView(R.id.et_control_query_person_name)
    EditText etPersonNameIn;        //姓名输入
    @BindView(R.id.rv_control_person)
    RecyclerView recyclerView;
    @BindView(R.id.et_control_query_person_sfzid)
    CustomKeyBoard etSfzId;         //身份证输入
    @BindView(R.id.iv_mc_scan)
    ImageView ivMcScan;             //身份证ocr
    @BindView(R.id.btn_submitSelect)
    Button btnSubmitSelect;         //提交

    private Unbinder unbinder;
    //身份证号
    private String sfzId;
    //姓名
    private String name;
    private NFCReaderHelper nfcHelper;
    public static final int REQUEST_PERSON_CODE = 10;
    public static final int REQUEST_CAR_CODE = 20;
    private NFCReadTask nfcReadTask;
    private AsyncTask<Void, Void, String> nfcTask;

    @SuppressLint("ValidFragment")
    public QueryPersonControlFragment(NFCReaderHelper nfc) {
        // Required empty public constructor
        nfcHelper = nfc;
    }


    @Override
    protected int setView() {
        return R.layout.fragment_control_query_person;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        etSfzId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    nfcHelper.read();
                }
            }
        });
        return rootView;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        ivMcScan.setOnClickListener(this);
        btnSubmitSelect.setOnClickListener(this);
    }

    public void scanIdCard(View view) {
        Intent intent = new Intent(getActivity(), OcrPersonActivity.class);
        startActivityForResult(intent, REQUEST_PERSON_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PERSON_CODE && resultCode == RESULT_OK) {
            String recogResult = data.getStringExtra("recogResult");
            etSfzId.setText(recogResult);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mc_scan:
                scanIdCard(v);
                break;
            case R.id.btn_submitSelect:

                sfzId = etSfzId.getText().toString();
                name = etPersonNameIn.getText().toString();

                if (IDCardUtil.managerIDCard(sfzId) || !TextUtils.isEmpty(name)) {
                    //跳转列表页进行查询并显示
                    ControlListActivity.startPerson(getActivity(), sfzId, name);
                } else {
                    ToastUtils.showShort("输入有误，请检查");
                }
                break;
            default:
                break;
        }

    }

    public void NfcMessage(Intent intent) {
        if (nfcTask == null) {
            nfcReadTask = new NFCReadTask(intent, nfcHelper);
            nfcTask = nfcReadTask.executeOnExecutor(Executors.newCachedThreadPool());
        }
        nfcReadTask.textmessage(new NFCReadTask.NfcMessage() {
            @Override
            public void BackMessage(IdentityCardZ s) {
                etSfzId.setText(s.cardNo);
                etPersonNameIn.setText(s.name);
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
