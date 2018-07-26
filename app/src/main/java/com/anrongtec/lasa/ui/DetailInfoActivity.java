package com.anrongtec.lasa.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anrongtec.lasa.R;
import com.anrongtec.lasa.entity.CarCheckEntity;
import com.anrongtec.lasa.entity.CheckInfoManager;
import com.anrongtec.lasa.entity.PersonCheckEntity;
import com.anrongtec.lasa.manager.CheckHestory;
import com.anrongtec.lasa.utils.DateTools;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 详情页面， 用于展示其他地方跳转过来的人员或车辆详情
 */
public class DetailInfoActivity extends BaseActivity {

    public static final String TAG = DetailInfoActivity.class.getSimpleName();
    //**************** 人员 *****************
    @BindView(R.id.tv_detail_person_info_nameIn)
    TextView tvDetailPersonInfoNameIn;
    @BindView(R.id.tv_detail_person_info_idCardIn)
    TextView tvDetailPersonInfoIdCardIn;
    @BindView(R.id.tv_detail_person_info_genderIn)
    TextView tvDetailPersonInfoGenderIn;
    @BindView(R.id.tv_detail_person_info_nationIn)
    TextView tvDetailPersonInfoNationIn;
    @BindView(R.id.tv_detail_person_info_birthdayIn)
    TextView tvDetailPersonInfoBirthdayIn;
    @BindView(R.id.tv_detail_person_info_organIn)
    TextView tvDetailPersonInfoOrganIn;
    @BindView(R.id.tv_detail_person_info_timeIn)
    TextView tvDetailPersonInfoTimeIn;
    @BindView(R.id.tv_detail_person_info_checkTimeIn)
    TextView tvDetailPersonInfoCheckTimeIn;
    @BindView(R.id.tv_detail_person_info_sourceIn)
    TextView tvDetailPersonInfoSourceIn;
    @BindView(R.id.tv_detail_person_info_policeNumIn)
    TextView tvDetailPersonInfoPoliceNumIn;
    @BindView(R.id.tv_detail_person_info_checkPointIn)
    TextView tvDetailPersonInfoCheckPointIn;
    @BindView(R.id.tv_detail_person_info_taskNameIn)
    TextView tvDetailPersonInfoTaskNameIn;
    @BindView(R.id.tv_detail_person_info_jyaqIn)
    TextView tvDetailPersonInfoJyaqIn;
    @BindView(R.id.tv_detail_person_info_addressIn)
    TextView tvDetailPersonInfoAddressIn;
    @BindView(R.id.iv_detail_person_info_avatar)
    ImageView photoImage;
    @BindView(R.id.rl_detail_person_info)
    RelativeLayout rlDetailPersonInfo;

    //**************** 人员 中标 *****************
    @BindView(R.id.tv_detail_person_bid_TypeIn)
    TextView tvDetailPersonBidTypeIn;
    @BindView(R.id.tv_detail_person_bid_levelIn)
    TextView tvDetailPersonBidLevelIn;
    @BindView(R.id.tv_detail_person_bid_dealTypeIn)
    TextView tvDetailPersonBidDealTypeIn;
    @BindView(R.id.tv_detail_person_bid_remarkIn)
    TextView tvDetailPersonBidRemarkIn;
    @BindView(R.id.tv_detail_person_bid_linkIn)
    TextView tvDetailPersonBidLinkIn;
    @BindView(R.id.tv_detail_person_bid_linkPhoneIn)
    TextView tvDetailPersonBidLinkPhoneIn;
    @BindView(R.id.tv_detail_person_bid_linkDeptIn)
    TextView tvDetailPersonBidLinkDeptIn;
    @BindView(R.id.rl_detail_person_bid_info)
    RelativeLayout rlDetailPersonBidInfo;

    //****************  车辆  *****************
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
    @BindView(R.id.tv_detail_car_checkTimeIn)
    TextView tvDetailCarCheckTimeIn;
    @BindView(R.id.tv_detail_car_carColorIn)
    TextView tvDetailCarCarColorIn;
    @BindView(R.id.tv_detail_car_engineNumIn)
    TextView tvDetailCarEngineNumIn;
    @BindView(R.id.tv_detail_car_addressIn)
    TextView tvDetailCarAddressIn;
    @BindView(R.id.rl_detail_car_info)
    RelativeLayout rlDetailCarInfo;

    //**************** 车辆中标 *****************
    @BindView(R.id.tv_detail_car_bid_carTypeIn)
    TextView tvDetailCarBidCarTypeIn;
    @BindView(R.id.tv_detail_car_bid_policeNameIn)
    TextView tvDetailCarBidPoliceNameIn;
    @BindView(R.id.tv_detail_car_bid_taskNameIn)
    TextView tvDetailCarBidTaskNameIn;
    @BindView(R.id.tv_detail_car_bid_taskDescIn)
    TextView tvDetailCarBidTaskDescIn;
    @BindView(R.id.tv_detail_car_bid_linkPersonIn)
    TextView tvDetailCarBidLinkPersonIn;
    @BindView(R.id.tvdetail_car_bid_linkPhoneIn)
    TextView tvDetailCarBidLinkPhoneIn;
    @BindView(R.id.tv_detail_car_bid_checkedDescIn)
    TextView tvDetailCarBidCheckedDescIn;
    @BindView(R.id.rl_detail_car_bid_info)
    RelativeLayout rlDetailCarBidInfo;
    @BindView(R.id.tv_detail_person_info_locationXIn)
    TextView tvLocationX;
    @BindView(R.id.tv_detail_person_info_locationYIn)
    TextView tvLocationY;

    private PersonCheckEntity mPerson;
    private CarCheckEntity mCar;
    private int mType;

    private static final String EXTRA_PERSON = "extra_person";
    //人员
    public static final int TYPE_PERSON = 1;
    //车辆
    public static final int TYPE_CAR = 2;
    private CheckInfoManager infoManager;

    /**
     * 跳转方法，  显示人员或者车辆的详情
     * @param context
     * @param checkInfoManager
     */
    public static void start(Context context, CheckHestory checkInfoManager) {
        Intent intent = new Intent(context, DetailInfoActivity.class);
        Bundle bundle = new Bundle();
        if (checkInfoManager != null) {
            bundle.putSerializable(EXTRA_PERSON, checkInfoManager);
        }
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        ButterKnife.bind(this);
        if (getIntentData()) {
            ButterKnife.bind(this);
            showView();
        }
    }


    /**
     * 显示和隐藏布局
     * 区域特殊性，将所有设置信息md停用
     */
    private void showView() {
        if (mType == TYPE_PERSON) {
            //人员信息
            rlDetailPersonInfo.setVisibility(View.VISIBLE);
            //设置人员基本信息
//            setPersonInfo();
            if (!TextUtils.isEmpty(infoManager.data.zbryList.get(0).rwmc)) {//如果任务名称不为空则说明是中标人员
                //中标信息
                rlDetailPersonBidInfo.setVisibility(View.VISIBLE);
                //设置人员中标信息
//                setPersonBidInfo();
            }
        } else {
            //车辆信息
            setTitle("车辆详情");
            rlDetailCarInfo.setVisibility(View.VISIBLE);
            //设置车辆信息   暂无
//            setCarInfo();
            //判断中标车辆中的中标任务描述   暂无  使用车牌号码代替
            if (!TextUtils.isEmpty(infoManager.data.zbclList.get(0).cphm)) {
                //中标信息
                rlDetailCarBidInfo.setVisibility(View.VISIBLE);
                //设置车辆中标信息
//                setCarBidInfo();
            }
        }
    }

    /**
     * 获取传递参数. 判断是显示人员还是车辆详情
     */
    private boolean getIntentData() {
        Bundle bundle = getIntent().getExtras();
        //获取传递数据对象
        infoManager = (CheckInfoManager) bundle.getSerializable(EXTRA_PERSON);
        //详细信息界面出现的人员信息集合
        List<CheckInfoManager.DataBean.RyxxListBean> ryxxList = infoManager.data.ryxxList;
        //中标车辆集合  ？？没有车辆查询？？
        List<CheckInfoManager.DataBean.ZbclListBean> zbclList = infoManager.data.zbclList;
        //中标人员集合
        List<CheckInfoManager.DataBean.ZbryListBean> zbryList = infoManager.data.zbryList;
        //如果中标车辆的集合不为空  则说明是查车？？？逻辑不通   没有单独查车的

        if (infoManager==null){//如果传递对象为空则直接return
            return false;
        }
        if (zbclList!=null&&zbclList.size()!=0){
            mType=TYPE_CAR;
        }else{
            mType=TYPE_PERSON;
        }
        return true;
    }


    /**
     * 设置人员基本信息
     */
    private void setPersonInfo() {
        tvDetailPersonInfoNameIn.setText(mPerson.getXm());
        Glide.with(this).load(mPerson.getZp()).into(photoImage);
        tvDetailPersonInfoIdCardIn.setText(mPerson.getSfhm());
        tvDetailPersonInfoGenderIn.setText(mPerson.getXb());
        tvDetailPersonInfoNationIn.setText(mPerson.getMz());
        tvDetailPersonInfoBirthdayIn.setText(mPerson.getCsrq());
        tvDetailPersonInfoOrganIn.setText(mPerson.getQfjg());
        tvDetailPersonInfoTimeIn.setText(mPerson.getYxqx());
        tvDetailPersonInfoSourceIn.setText(mPerson.getSjly());
        tvDetailPersonInfoCheckTimeIn.setText(DateTools.getStrTime(mPerson.getInAppDate()));
        tvDetailPersonInfoPoliceNumIn.setText(mPerson.getUserid());
        tvDetailPersonInfoCheckPointIn.setText(mPerson.getAddres());
        tvDetailPersonInfoTaskNameIn.setText(mPerson.getTaskNames());
        tvDetailPersonInfoJyaqIn.setText(mPerson.getYxqx());
        tvDetailPersonInfoAddressIn.setText(mPerson.getAddressName());
        tvDetailPersonInfoPoliceNumIn.setText(mPerson.getUserid());
        tvLocationX.setText(mPerson.getLocationX());
        tvLocationY.setText(mPerson.getLocationY());
    }

    /**
     * 设置人员中标信息
     */
    private void setPersonBidInfo() {
        tvDetailPersonBidTypeIn.setText(mPerson.getRylb());
        tvDetailPersonBidLevelIn.setText(mPerson.getPerLevel());
        tvDetailPersonBidDealTypeIn.setText(mPerson.getCzfs());
        tvDetailPersonBidRemarkIn.setText(mPerson.getTaskNames());
        tvDetailPersonBidLinkIn.setText(mPerson.getBklxrName());
        tvDetailPersonBidLinkPhoneIn.setText(mPerson.getBkrCall());
        tvDetailPersonBidLinkDeptIn.setText(mPerson.getBkdw());
    }

    /**
     * 设置车辆信息
     */
    private void setCarInfo() {
        tvDetailCarOwnNameIn.setText(mCar.getCzxm());
        tvDetailCarOwnerCardIn.setText(mCar.getCzsfhm());
        tvDetailCarOwnerGenderIn.setText(mCar.getXb());
        tvDetailCarOwnerMZIn.setText(mCar.getMz());
        tvDetailCarNumIn.setText(mCar.getCph());
        tvDetailCarPlateTypeIn.setText(mCar.getHpzl());
        tvDetailCarIdentifyCodeIn.setText(mCar.getClsbdm());
        tvDetailCarCheckTimeIn.setText(DateTools.getStrTime(mCar.getInAppDate()));
        tvDetailCarCarColorIn.setText(mCar.getClys());
        tvDetailCarEngineNumIn.setText(mCar.getFdjh());
        tvDetailCarAddressIn.setText(mCar.getDsName());
    }

    /**
     * 设置车辆中标信息
     */
    private void setCarBidInfo() {
        tvDetailCarBidCarTypeIn.setText(mCar.getCllb());
        tvDetailCarBidPoliceNameIn.setText(mCar.getUserid());
        tvDetailCarBidTaskNameIn.setText(mCar.getTaskNames());
        tvDetailCarBidTaskDescIn.setText(mCar.getDescCar());
        tvDetailCarBidLinkPersonIn.setText(mCar.getContacts());
        tvDetailCarBidLinkPhoneIn.setText(mCar.getContactCall());
        tvDetailCarBidCheckedDescIn.setText(mCar.getContactCall());
    }


}
