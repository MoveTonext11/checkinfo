package com.anrongtec.cp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anrongtec.cp.R;
import com.anrongtec.cp.entity.CheckInfoManager;
import com.anrongtec.cp.interfaces.HttpInterfaces;
import com.anrongtec.cp.interfaces.HttpUrl;
import com.anrongtec.cp.interfaces.callback.StringDialogCallback;
import com.anrongtec.cp.ui.fragment.CheckPersonFragment;
import com.anrongtec.cp.utils.DicDataCache;
import com.anrongtec.cp.utils.GsonUtil;
import com.anrongtec.cp.utils.IDCardUtil;
import com.anrongtec.cp.utils.NFCReadTask;
import com.anrongtec.cp.utils.NFCReaderHelper;
import com.anrongtec.cp.utils.NFCUtils;
import com.anrongtec.cp.view.CustomKeyBoard;
import com.anrongtec.ocr.OcrCarActivity;
import com.anrongtec.ocr.OcrPersonActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.carkeyboard.KeyboardInputController;
import com.carkeyboard.support.PopupKeyboard;
import com.carkeyboard.view.InputView;
import com.carkeyboard.view.KeyboardCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.senter.model.IdentityCardZ;


/**
 * 无证安检
 * <p>
 * 流程： 1.核查车牌，检测是否有安检证（返回状态：1未核查，2已核查，3已关注，4重点。   2已核查的有安检证，可以不用再次核查。 其他3种情况需要人车绑定进行核查）
 * 1.1无安检证则进行人车绑定核查
 * 1.1.1核查正常（后台服务会生成安检证）
 * 1.1.2核查中标，则进行中标处理
 * 1.2有安检证（已经核查过）
 * 1.2.1可以放行
 * 1.2.2再次进行人车核查
 */
public class AnJianActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_anjian_CarNum)
    TextView tvCarNum;
    //车牌号输入
    @BindView(R.id.anjian_inputView)
    InputView inputView;
    //车牌号输入切换
    @BindView(R.id.btn_anjian_lock_type)
    Button lockType;
    //车牌号扫描
    @BindView(R.id.iv_anjian_carNum_scan)
    ImageView ivAnjianCarNumScan;
    //号牌种类
    @BindView(R.id.tv_anjian_check_carPlateTypeValue)
    TextView tvAnjianCheckCarPlateTypeValue;
    //身份证输入父布局
    @BindView(R.id.ll_anjian_inputEditText)
    LinearLayout inputEditLinearLayout;
    //添加身份证输入列的按钮
    @BindView(R.id.btn_anjian_addPerCardInput)
    Button btnAddInput;
    //移除身份证输入列的按钮
    @BindView(R.id.btn_anjian_delPerCardInput)
    Button btnDelInput;

    //核查结果按钮的父布局
    @BindView(R.id.cl_anjian_showButton)
    LinearLayout clAnjianShowButton;
    //人员中标信息按钮
    @BindView(R.id.btn_anjian_checkedPerson)
    Button btnAnjianCheckedPerson;
    //车辆中标信息按钮
    @BindView(R.id.btn_anjian_checkedCar)
    Button btnAnjianCheckedCar;
    //本次核查人员信息
    @BindView(R.id.btn_anjian_thisPersonInfo)
    Button btnAnjianPersonInfoThis;
    //核查按钮
    @BindView(R.id.btn_anjian_submitSelect)
    Button btnAnjianSubmitSelect;
    //安检证状态
    @BindView(R.id.tv_anjian_state)
    TextView tvAnjianState;
    //加入黑名单
    @BindView(R.id.btn_anjian_add_blacklist)
    Button btnAnjianAddBlacklist;
    //重置按钮
    @BindView(R.id.btn_anjian_reSet)
    Button btnReset;
    //OCR扫描车主身份证
    @BindView(R.id.iv_mc_scan_czID)
    ImageView OCRIDCard;
    //驾驶人身份证输入
    @BindView(R.id.et_anjian_check_czIDCardValue)
    CustomKeyBoard etAnjianCheckCzIDCardValue;

    public String driverID = "";
    //车牌号
    private String carNumber;
    private final List<String> mTestNumber = new ArrayList<>();

    private final Random mRandom = new Random();
    private PopupKeyboard mPopupKeyboard;
    private Unbinder unbinder;


    /**
     * 身份证输入控件的集合
     */
    private SparseArray<CustomKeyBoard> sfzViews;
    /**
     * 点击的第几个ocr身份证扫描
     */
    private int sfzOcrPosition;
    private int sfznfcPosition;

    //号牌种类对应的值。  用于接口请求
    private String hpzl;
    private List<String> hpzls;
    private List<String> hpzlNames;
    //白转黑理由
    private List<String> changeListNames;
    private List<String> changeListKeys;
    private String reasion;

    //身份证号集合， 用“,”分割开
    private StringBuilder sfzIdBuilder;
    public final static String TAG = AnJianActivity.class.getSimpleName();
    private NFCReaderHelper nfcHelper;
    private AsyncTask<Void, Void, String> nfcTask;
    private NFCReadTask nfcReadTask;
    private int childCount;
    private int numFlog;
    //获取各种人员信息的集合
    private ArrayList<CheckInfoManager.DataBean.RyxxListBean> ryxxList;
    private ArrayList<CheckInfoManager.DataBean.ZbryListBean> zbryList;
    private ArrayList<CheckInfoManager.DataBean.ZbclListBean> zbclList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_jian);
        ButterKnife.bind(this);
        setTitle("快速安检");
        initData();
        initView();

        //触发点击事件NFC（NFC需要厂家单独调用）
        NFCUtils getinstance = NFCUtils.getinstance();
        //创建helper对象
        nfcHelper = getinstance.createHfcHelper(AnJianActivity.this);
        //创建服务器
        getinstance.initShareReference(AnJianActivity.this);
    }

    private void initData() {
        sfzViews = new SparseArray<>();
    }

    private void initView() {
        initKeyboard();
        addSfzIdInputView();
        tvAnjianCheckCarPlateTypeValue.setText("小型汽车");
        btnAnjianSubmitSelect.setOnClickListener(this);
        btnAddInput.setOnClickListener(this);
        btnDelInput.setOnClickListener(this);
        btnAnjianPersonInfoThis.setOnClickListener(this);
        btnAnjianCheckedPerson.setOnClickListener(this);
        btnAnjianCheckedCar.setOnClickListener(this);
        ivAnjianCarNumScan.setOnClickListener(this);
        tvAnjianCheckCarPlateTypeValue.setOnClickListener(this);
        OCRIDCard.setOnClickListener(this);
//        btnAnjianAddBlacklist.setOnClickListener(this);//车辆存疑点击事件
        btnReset.setOnClickListener(this);
        etAnjianCheckCzIDCardValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //读取
                    numFlog = 1;
                    nfcHelper.read();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nfcTask = null;
    }

    /**
     * NFC事件回调
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (nfcHelper.isNFC(intent)) {
            nfcReadTask = new NFCReadTask(intent, nfcHelper);
            nfcTask = nfcReadTask.executeOnExecutor(Executors.newCachedThreadPool());
            nfcReadTask.textmessage(new NFCReadTask.NfcMessage() {
                @Override
                public void BackMessage(IdentityCardZ s) {
                    if (numFlog == 1) {
                        etAnjianCheckCzIDCardValue.setText(s.cardNo);
                    }
                    if (numFlog == 2) {
                        CustomKeyBoard customKeyBoard = sfzViews.get(sfznfcPosition);
                        customKeyBoard.setText(s.cardNo);
                    }
                    //每次获取数据之后置空线程和对象
                    nfcReadTask = null;
                    nfcTask = null;
                }
            });
        }
    }

    /**
     * 初始化身份证出入键盘
     */
    private void initKeyboard() {
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(this);
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(inputView, this);
        mPopupKeyboard.getKeyboardView().addKeyboardCallback(new KeyboardCallback.Simple() {
            @Override
            public void onConfirmKey() {
                super.onConfirmKey();
                mPopupKeyboard.dismiss(AnJianActivity.this);
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

    private void hindKeyBoard() {
        int visibility = mPopupKeyboard.getKeyboardView().getVisibility();

        if (visibility == View.VISIBLE) {
            mPopupKeyboard.dismiss(AnJianActivity.this);
        }
    }

    /**
     * 检查输入的参数
     *
     * @return
     */
    private boolean checkInput() {
        carNumber = inputView.getNumber();
        String jsrcard = etAnjianCheckCzIDCardValue.getText().toString().trim();
        //获取车牌输入信息进行判断    判断为  车牌号与驾驶人身份证号不能同时为空
        if ((TextUtils.isEmpty(carNumber) || carNumber.length() < 7) && TextUtils.isEmpty(jsrcard)) {
            ToastUtils.showShort("车牌号或驾驶人信息输入错误");
            return false;
        }
        //判断号牌类型信息
        if (TextUtils.isEmpty(hpzl)) {
            hpzl = "02";
        }
        if (sfzViews.size() != 0) {
            sfzIdBuilder = new StringBuilder();
            //循环添加乘车人身份证号拼接
            for (int i = 0; i < sfzViews.size(); i++) {
                CustomKeyBoard customKeyBoard = sfzViews.get(i);
                String sfzId = customKeyBoard.getText().toString();
                if (!TextUtils.isEmpty(sfzId)) {
                    if (IDCardUtil.managerIDCard(sfzId)) {
                        sfzIdBuilder.append(sfzId);
                        sfzIdBuilder.append(",");
                    } else {
                        ToastUtils.showShort("人员" + (i + 1) + "身份证号输入错误");
                        return false;
                    }
                }
            }
            if (sfzIdBuilder.length() != 0) {
                sfzIdBuilder.deleteCharAt(sfzIdBuilder.lastIndexOf(","));
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        hindKeyBoard();
        switch (v.getId()) {
            case R.id.tv_anjian_check_carPlateTypeValue:
                selectCarType();
                break;
            case R.id.iv_anjian_carNum_scan:
                scanCarId();
                break;
            case R.id.iv_mc_scan:
                scanCarId();
                break;
            case R.id.iv_mc_scan_czID:
                scanSfzId(-1);
                break;
            case R.id.btn_anjian_addPerCardInput:
                addSfzIdInputView();
                break;
            case R.id.btn_anjian_delPerCardInput:
                if (sfzViews.size() != 1) {
                    new MaterialDialog.Builder(this)
                            .title("提示")
                            .content("确定删除?")
                            .positiveText("确定")
                            .negativeText("取消")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull
                                        DialogAction which) {
                                    delSfzIdInputView();
                                }
                            })
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                }
                            }).show();
                }
                break;
            case R.id.btn_anjian_thisPersonInfo:    //本次核查信息
                AnJianListActivity.startPerson(AnJianActivity.this, ryxxList);
                break;
            case R.id.btn_anjian_checkedCar:        //车辆中标
                AnJianListActivity.startCarBid(AnJianActivity.this, zbclList);
                break;
            case R.id.btn_anjian_checkedPerson:     //人员中标
                AnJianListActivity.startPersonBid(AnJianActivity.this, zbryList);
                break;

            case R.id.btn_anjian_reSet://完成
                cleanValues();
                break;
            case R.id.btn_anjian_submitSelect:      //完成安检  提交查询
                //首先进行数据空判断  是否输入数据（）
                if (checkInput()) {
                    if (etAnjianCheckCzIDCardValue.length() != 0) {
                        //身份证确认
                        if (IDCardUtil.managerIDCard(etAnjianCheckCzIDCardValue)) {
                            driverID = etAnjianCheckCzIDCardValue.getText().toString();
                            if (sfzIdBuilder.length() != 0) {
                                driverID = "," + driverID;
                            }
                            sfzIdBuilder.append(driverID);
                        } else {
                            etAnjianCheckCzIDCardValue.setError("驾驶人身份证输入有误");
                            return;
                        }
                    }
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("hphm", carNumber);
                    hashMap.put("hpzl", hpzl);
                    hashMap.put("sfzh", String.valueOf(sfzIdBuilder));
                    hashMap.put("userId", "110");//待定  终端设备或者统一认证警员号

                    //将信息获取出来之后封装到map集合中  传递到接口中
                    HttpInterfaces.checkInfo(HttpUrl.CheckInfo, hashMap, new StringDialogCallback(this, "查询中...") {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            //获取的核查信息
                            CheckInfoManager infoManager = GsonUtil.decode(body, CheckInfoManager.class);
                            CheckInfoManager.DataBean data = infoManager.data;
//                            请求核查成功之后显示核查的状态按钮
                            //（PS:后台添加字段是否安检的状态，核查获取之后设定UI界面安检状态的信息）
                            showButton("是否安检状态接口未加  待定", data);
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    /**
     * 完成本次安检
     * 清空数据
     */
    private void cleanValues() {
        Intent intent = new Intent(this, AnJianActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 移除身份证输入列
     */
    private void delSfzIdInputView() {
        int childCount = inputEditLinearLayout.getChildCount();
        if (childCount > 1) {
            inputEditLinearLayout.removeViewAt(childCount - 1);
        }
        sfzViews.remove(childCount - 1);
    }

    /**
     * 新增身份证输入列
     */
    private void addSfzIdInputView() {
        childCount = inputEditLinearLayout.getChildCount();
        View inputView = LayoutInflater.from(this).inflate(R.layout.item_sfz_input_layout, null);
        TextView tvSfz = inputView.findViewById(R.id.tv_sfz_input);
        tvSfz.setText("人员" + (childCount + 1));
        final CustomKeyBoard customKeyBoard = inputView.findViewById(R.id.et_sfz_input);
        ImageView imageView = inputView.findViewById(R.id.iv_sfz_input_scan);
        //将新创建的子view添加到线性布局
        inputEditLinearLayout.addView(inputView);
        //将创建的子view添加到集合中  用于NFC和OCR的扫描数据赋值
        sfzViews.put(childCount, customKeyBoard);
        //NFC和OCR的点击启动事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanSfzId(sfzViews.indexOfValue(customKeyBoard));
            }
        });
        customKeyBoard.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //获取具体某个view在集合中的下表，用于在NFC和OCR启动之后拿取返回值的赋值选项
                sfznfcPosition = sfzViews.indexOfValue(customKeyBoard);
                numFlog = 2;//标识符，用于判断给定某个具体的输入框赋值
                nfcHelper.read();
            }
        });
    }

    /**
     * 扫描车牌
     */
    public void scanCarId() {
        Intent intent = new Intent(this, OcrCarActivity.class);
        startActivityForResult(intent, CheckPersonFragment.REQUEST_CAR_CODE);
    }

    /**
     * 扫描身份证
     *
     * @param currentCount
     */
    public void scanSfzId(int currentCount) {
        sfzOcrPosition = currentCount;
        Intent intent = new Intent(this, OcrPersonActivity.class);
        startActivityForResult(intent, CheckPersonFragment.REQUEST_PERSON_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CheckPersonFragment.REQUEST_CAR_CODE && resultCode == RESULT_OK) {
            String carResult = data.getStringExtra("carResult");
            inputView.updateNumber(carResult);
        }
        if (requestCode == CheckPersonFragment.REQUEST_PERSON_CODE && resultCode == RESULT_OK) {
            String recogResult = data.getStringExtra("recogResult");
            if (sfzOcrPosition == -1) {
                etAnjianCheckCzIDCardValue.setText(recogResult);
            } else {
                CustomKeyBoard c = sfzViews.get(sfzOcrPosition);
                c.setText(recogResult);
            }
        }
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
        new MaterialDialog.Builder(this)
                .items(hpzlNames)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        tvAnjianCheckCarPlateTypeValue.setText(hpzlNames.get(position));
                        hpzl = hpzls.get(position);
                    }
                })
                .show();
    }

    /**
     * 显示核查结果的按钮
     */
    private void showButton(String state, CheckInfoManager.DataBean data) {
        //获取到三种集合类别
        ryxxList = data.ryxxList;
        zbclList = data.zbclList;
        zbryList = data.zbryList;
        int ryxxsize = getListSize(ryxxList);
        int zbrysize = getListSize(zbryList);
        int zbclsize = getListSize(zbclList);

        if (zbrysize != 0) {
            btnAnjianCheckedPerson.setVisibility(View.VISIBLE);
            btnAnjianCheckedPerson.setText("本次中标\n人员:" + zbrysize);
        }

        if (zbclsize != 0) {
            btnAnjianCheckedCar.setVisibility(View.VISIBLE);
            btnAnjianCheckedCar.setText("本次中标\n车辆:" + zbclsize);
        }
        if (ryxxsize != 0) {
            btnAnjianPersonInfoThis.setVisibility(View.VISIBLE);
            btnAnjianPersonInfoThis.setText("本次核查\n人员:" + ryxxsize);
        }

        //显示安检证状态 返回结果
        tvAnjianState.setVisibility(View.VISIBLE);
        tvAnjianState.setText(getState(state));
        btnAnjianSubmitSelect.setVisibility(View.GONE);
        btnReset.setVisibility(View.VISIBLE);
    }

    private int getTextColor(String state) {
        switch (state) {
            case "2":
                return Color.GREEN;
            case "5":
                return Color.GREEN;

            default:
                return Color.RED;
        }

    }

    /**
     * 隐藏结果控件
     */
    private void hidenButton() {
        clAnjianShowButton.setVisibility(View.GONE);
        tvAnjianState.setVisibility(View.GONE);
        btnAnjianAddBlacklist.setVisibility(View.GONE);
    }


    private int getListSize(List list) {
        return list == null ? 0 : list.size();
    }

    /**
     * 根据code获取安检证相关状态信息
     *
     * @param state
     * @return
     */
    private String getState(String state) {
        String strState = "未安检";
        switch (state) {
            case "1":
                strState = "未核查";
                break;
            case "2":
                strState = "已核查";
                break;
            case "3":
                strState = "一级车";
                break;
//            case "4":
//                strState = "重点！";
//                break;
            case "5":
                strState = "三级车";
                break;
            default:
                break;
        }
        return strState;
    }


}
