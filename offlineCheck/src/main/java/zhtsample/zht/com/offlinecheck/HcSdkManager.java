package zhtsample.zht.com.offlinecheck;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.anrong.sdk.CheckCarInfo;
import com.anrong.sdk.CheckPersonInfo;
import com.anrong.sdk.SDKService;
import com.anrong.sdk.callback.InitCallBack;
import com.anrong.sdk.callback.ZipCallBack;
import com.anrong.sdk.util.log.LogUtil;
import com.arong.swing.db.dao.EquipmentSDAO;
import com.arong.swing.db.entity.AsAllGenerate;
import com.arong.swing.db.entity.CrBidCar;
import com.arong.swing.db.entity.CrBidPerson;
import com.arong.swing.db.entity.CrCarRecord;
import com.arong.swing.db.entity.CrPersionRecord;
import com.arong.swing.db.entity.Equipment;
import com.arong.swing.db.entity.JsonDataResult;
import com.arong.swing.db.entity.KeyCar;
import com.arong.swing.db.entity.KeyPerson;
import com.arong.swing.db.entity.PagingInfo;
import com.arong.swing.db.entity.PagingResult;
import com.arong.swing.db.entity.StaffUserVO;
import com.arong.swing.exception.AppException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import zhtsample.zht.com.offlinecheck.util.DialogUtil;
import zhtsample.zht.com.offlinecheck.util.MediaScannerUtil;
import zhtsample.zht.com.offlinecheck.util.Utils;


/**
 * 离线核查sdk管理类
 */

public class HcSdkManager {
    private static final String TAG = "HcSdkManager";

    private static volatile HcSdkManager mInstance;
    private static Object LOCK = new Object();
    private Activity activity;
    private ProgressDialog progressDialog, zipProgressDialog;
    private Application application;
    private boolean isOnline, useLocalData;
    private HcSdkInitCallback callback;

    private String explodeMsg = "", explodeTitle = "发现布控包";

	/**
	 * 是否初始化
	 */
	private boolean isInit;

    Handler handler = new Handler(new Handler.Callback() {
	    @Override
	    public boolean handleMessage(Message msg) {
		    MediaScannerUtil.scanDbFiles(activity);
		    switch (msg.what) {
			    case InitCallBack.CODE_DEVICE_REGISTER_EXCEPTION:// 设备注册信息IMEI非法！
			    case InitCallBack.CODE_ORGANIZATION_REGISTER_EXCEPTION:// 不存在注册的组织机构，请同步更新注册设备信息！
			    case InitCallBack.CODE_COMPANYID_EMPTY_EXCEPTION:// companyID 为空！
			    case InitCallBack.CODE_UPLOAD_NOTINTIME_EXCEPTION:// 请及时上传核查数据！
			    case InitCallBack.CODE_NO_USER://没有用户信息
			    case InitCallBack.CODE_CTRL_PACKET_NOTEXIST_EXCEPTION:// 布控包不存在，请同步最新布控包！
				    //TODO 这里判断解压后的布控包存在不存在，其实是从anrongtec.db文件来判断的。  因为正常流程是这个文件和布控包同时从pc端同步过来的。
				    //TODO 后续修改sdk，从解压之后的文件中判断是否有布控包。

				    cancelDialog();
				    showAlertDialog((String)msg.obj);
				    break;
			    case InitCallBack.CODE_NEED_EXPLODE:
				    explodeTitle = "【完整布控包】";
				    explodeMsg = (String)msg.obj;
				    SDKService.explode(activity, myZipCallBack);
				    break;
			    case InitCallBack.CODE_NEED_EXPLODE_LOCAL:
				    explodeTitle = "【本地布控包】";
				    explodeMsg = (String)msg.obj;
				    SDKService.explodeLocalPackage(activity, myZipCallBack);
				    break;

			    case 9998:
				    progressDialog.cancel();
				    Toast.makeText(activity, "未检测到SD卡,请在设置-储存设备和USB 中确认是否装载", Toast.LENGTH_SHORT).show();
				    break;



			    case InitCallBack.CODE_INIT_SUCCESS:
				    //检测一下解压后的
				    File file = new File(Environment.getExternalStorageDirectory()+"/package/package.db");
				    if (!file.exists()){
				    	showAlertDialog("未找到解压后的布控文件");
				    	isInit = false;
					    cancelDialog();
				    	return false;
				    }
					isInit = true;

				    if (AppConfig.DEMO_MODE){
					    login(getUsers().get(0), "111111");
				    }
				    cancelDialog();

				    if (callback!=null)
					    callback.onSuccess(InitCallBack.CODE_INIT_SUCCESS);

				    break;

			    default:
				    break;
		    }
		    return false;
	    }
    });

	/**
	 * 解压缩回调方法
	 */
    private ZipCallBack myZipCallBack = new ZipCallBack() {
        @Override
        public boolean onPrepare() {
            if (progressDialog != null
                    && progressDialog.isShowing()) {
                progressDialog.setTitle("发现布控包");
                progressDialog.setMessage(explodeMsg);
            }
            return false;
        }

        @Override
        public void onReckonSizeFinished(long size) {
            if (size != -1) {
                zipProgressDialog = new ProgressDialog(activity);
                zipProgressDialog.setTitle("正在解压缩"+explodeTitle);
                zipProgressDialog
                        .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                zipProgressDialog.setMax(100);
                zipProgressDialog.setCancelable(false);
                zipProgressDialog.show();
            } else {
                progressDialog.setTitle("温馨提示");
                progressDialog.setMessage("布控包出错, 请重试");
            }
        }

        @Override
        public void onProgress(int i, long currentSize,
                               long maxSize) {
            zipProgressDialog.setProgress(i);
            float cs = currentSize / 1024 / 1024;
            float ms = maxSize / 1024 / 1024;
            zipProgressDialog.setProgressNumberFormat(String
                    .format("%.2fM/%.2fM", cs, ms));
        }
        @Override
        public void onSuccess() {
            MediaScannerUtil.scanPackages(activity);
            cancelDialog();
            init();
        }

        @Override
        public void onError(int i, String s) {
            LogUtil.trace(Log.ERROR, TAG, "Code:" + i + ",Msg:" + s);
            cancelDialog();
            switch (i) {
                case CODE_EXPLODE_PACKET_NOTEXIST_EXCEPTION:
                case CODE_EXPLODE_PACKET_FAILED_EXCEPTION:
                    showAlertDialog(s);
                    break;
            }
        }
    };


	public static HcSdkManager getInstance(){
        if (mInstance == null)
            synchronized (LOCK){
                if (mInstance == null)
                    mInstance = new HcSdkManager();
            }
        return mInstance;
    }


    /**
     * 初始化sdk
     */
    public void init(Activity activity, Application application, HcSdkInitCallback callback){
        this.activity = activity;
        this.application = application;
        //默认离线
        this.isOnline = false;
        //默认不使用本地个性化的布控数据包
        this.useLocalData = false;
        this.callback = callback;
        initDialog();

        if (AppConfig.DEMO_MODE) {
	        initYanShi(activity);//正式模式（通过pc端能导入db文件）不用初始化测试模式
        }

        init();
    }

	/**
	 * 初始化
	 */
	private void init(){
        showDialog();

        //离线核查才会有以下设置
        if (!isOnline){
            SDKService.setSynchronizedTime(AppConfig.SYNCHRONIZATION_TIME);//设置更新布控信息的频率
            if (AppConfig.LOCAL_DATA_MODE)
                SDKService.setUseLocalPackage(useLocalData);//是否使用本地数据
        }
        //自动删除核查记录
		SDKService.setAutoDeleteRecordTime(AppConfig.AUTO_DELETE_RECORD_TIME);

        SDKService.init(application, AppConfig.DB_PATH, AppConfig.LOG_PATH, Utils.getImei(activity), "anrongtec", isOnline, new InitCallBack() {
            @Override
            public void onSuccess(int i) {
                handler.obtainMessage(i).sendToTarget();
            }
            @Override
            public void onError(int i, String s) {
                handler.obtainMessage(i, s).sendToTarget();
                if (callback!=null)
                    callback.onFail(i, s);
            }
            @Override
            public void onNeedExplode(int i, String s) {
                handler.obtainMessage(i, s).sendToTarget();
            }
            @Override
            public void onNeedExplodeLocal(int i, String s) {
                handler.obtainMessage(i, s).sendToTarget();
            }
        });
    }

	/**
	 * 获取用户信息
	 * 初始化之后调用
	 * @return
	 */
	public List<StaffUserVO> getUsers(){
		if (checkInit()) {
			try {
				return SDKService.getStaffUsrs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    return null;
    }

	/**
	 * 获取布控包信息
	 */
	private AsAllGenerate getPackageInfo() {
		if (checkInit()) {
			try {
				return SDKService.getCurAsAllGenerate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 登录
	 * @param staffUserVO
	 * @param pwd
	 * @return
	 */
	private boolean login(StaffUserVO staffUserVO, String pwd){
		if (checkInit()) {
			try {
				JsonDataResult<String> result = SDKService.login(staffUserVO, pwd);
				if ("021".equals(result.getCode()) || "020".equals(result.getCode())) {
					//登录失败
					return false;
				} else {
					//登录成功
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}


    /****************************************************************************************/
	/**
	 * 检测是否初始化
	 * @return
	 */
    private boolean checkInit(){
    	if (!isInit){
		    Log.d("HcSdkManager", "未初始化");
    		return false;
	    }
	    return true;
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("温馨提示");
        progressDialog.setMessage("正在初始化...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
    }
    private void showDialog(){
        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
        else if(progressDialog == null){
            initDialog();
            progressDialog.show();
        }
    }
    private void cancelDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
        if (zipProgressDialog != null && zipProgressDialog.isShowing()) {
            zipProgressDialog.cancel();
        }
    }

    /**
     * 提示对话框
     * @param msg
     */
    private void showAlertDialog(String msg){
        DialogUtil.createAlertDialogSingle(activity, "提示", msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                activity.finish();
            }
        });
    }



	/******************************************  核查相关  **********************************************/

	/**
	 * 核查人员
	 * @param sfhm  身份证号码
	 * @param eventId 人车绑定的id（关联人车，人车核查用相同的eventId），单独做核查的时候不用传。
	 */
	public List<KeyPerson> checkPerson(String sfhm, String eventId){
		if (checkInit()) {
			try {
				CheckPersonInfo personInfo = new CheckPersonInfo();
				personInfo.sfhm = sfhm;
				return SDKService.checkPerson(TextUtils.isEmpty(eventId)? SDKService.uuid32():eventId, SDKService.uuid32(), personInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public List<KeyPerson> checkPerson(String sfhm){
		return checkPerson(sfhm, null);
	}

	/**
	 * 车辆核查
	 * @param cphm  车牌号码
	 * @param hpzl  号牌种类
	 * @param eventId   人车绑定的id（关联人车，人车核查用相同的eventId），单独做核查的时候不用传。
	 * @return
	 */
	public List<KeyCar> checkCar(String cphm, String hpzl, String eventId){
		if (checkInit()) {
			try {
				CheckCarInfo carInfo = new CheckCarInfo();
				carInfo.cphm = cphm;
				carInfo.hpzl = hpzl;
				return SDKService.checkCar(TextUtils.isEmpty(eventId)? SDKService.uuid32():eventId, SDKService.uuid32(), carInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public List<KeyCar> checkCar(String cphm, String hpzl){
		return checkCar(cphm, hpzl, null);
	}


	/*******************************************  核查记录相关  *********************************************/

    /**
     * 查询人员核查记录
     * @param pageSize
     * @param currentPage
     * @param name
     * @param startTime
     * @param endTime
     * @return
     */
    public PagingResult<CrPersionRecord> getPersonRecord (int pageSize, int currentPage, String name, long startTime, long endTime){
        CrPersionRecord pr = new CrPersionRecord();
        PagingInfo pi = new PagingInfo();
        //默认当前页1, 每页数量10
        pi.setPageRows(pageSize);//分页, 每页展示的数量
        pi.setPageNum(currentPage);//当前页
        return SDKService.getPersonRecord(pi, pr, name, startTime, endTime);
    }

    /**
     * 按id查询人员记录
     * @param id
     * @return
     */
    public CrPersionRecord getPersonRecordById(String id){
        CrPersionRecord pr = new CrPersionRecord();
        pr.setId(id);
        //根据ID查询到该人的其他信息
        return SDKService.getPersonRecord(pr);
    }

    public PagingResult<CrBidPerson> getBidPersonRecord (int pageSize, int currentPage, String name, long startTime, long endTime){
        CrBidPerson bp = new CrBidPerson();
        PagingInfo pi = new PagingInfo();
        //默认当前页1, 每页数量10
        pi.setPageRows(pageSize);//分页, 每页展示的数量
        pi.setPageNum(currentPage);//当前页
        return SDKService.getBidPersonRecord(pi, bp, name, startTime, endTime);
    }


    /**
     * 获取全部车辆记录
     * @param pageSize
     * @param currentPage
     * @param name
     * @param startTime
     * @param endTime
     * @return
     */
    public PagingResult<CrCarRecord> getCarRecord (int pageSize, int currentPage, String name, long startTime, long endTime){
        CrCarRecord cr = new CrCarRecord();
        PagingInfo pi = new PagingInfo();
        //默认当前页1, 每页数量10
        pi.setPageRows(pageSize);//分页, 每页展示的数量
        pi.setPageNum(currentPage);//当前页
        return SDKService.getCarRecord(pi, cr, name, startTime, endTime);
    }

    /**
     * 按id查询车辆记录
     * @param id
     * @return
     */
    public CrCarRecord getCarRecordById(String id){
        CrCarRecord cr = new CrCarRecord();
        cr.setId(id);
        //根据ID查询到该人的其他信息
        return SDKService.getCarRecord(cr);
    }

    /**
     * 获取所有中标车辆记录
     * @param pageSize
     * @param currentPage
     * @param name
     * @param startTime
     * @param endTime
     * @return
     */
    public PagingResult<CrBidCar> getBidCarRecord (int pageSize, int currentPage, String name, long startTime, long endTime){
        CrBidCar bc = new CrBidCar();
        PagingInfo pi = new PagingInfo();
        //默认当前页1, 每页数量10
        pi.setPageRows(pageSize);//分页, 每页展示的数量
        pi.setPageNum(currentPage);//当前页
        return SDKService.getBidCarRecord(pi, bc, name, startTime, endTime);
    }


    /***************************************  演示版本相关  *********************************************/
	//生成的db文件名称
    private static final String OUTPUT_DB_FILE_NAME = "anrongtec.db";

	/**
	 * 初始化演示模块
	 */
	private void initYanShi(Context context){
		File f = new File(AppConfig.DB_PATH + OUTPUT_DB_FILE_NAME);
		f.delete();
		unzipDB(context);
		//得到空的db,再创建测试账户
		if (f.exists())
			HcSdkManager.getInstance().initTestDB(context);
	}

	/**
	 * 指定位置生成db文件
	 * @param context
	 */
	private void unzipDB(Context context){
		try {
			InputStream inputStream = context.getResources().getAssets().open(AppConfig.DEFAULT_DB);

			File file = new File(AppConfig.DB_PATH);
			if(!file.exists()){
				file.mkdirs();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(AppConfig.DB_PATH + OUTPUT_DB_FILE_NAME);
			byte[] buffer = new byte[1024];
			int count = 0;
			while((count = inputStream.read(buffer)) > 0){
				fileOutputStream.write(buffer, 0 ,count);
			}
			fileOutputStream.flush();
			fileOutputStream.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 初始化测试用的db文件
     * @param context
     */
    private void initTestDB(Context context){
        EquipmentSDAO dao = new EquipmentSDAO(AppConfig.DB_PATH+"anrongtec.db");
        try {
            String imei = Utils.getImei(context);
            Equipment e = new Equipment();
            e.setEquiptId(imei);
            e.setDevClassId("2");
            e.setDeptId("130000000000");
            e.setImsi(imei);
            e.setUseState("1");
            e.setBelonguser("001");
            e.setUseduser("测试账户");
            e.setRegistTime(new Timestamp(System.currentTimeMillis()));
            e.setStsTime(new Timestamp(System.currentTimeMillis()));

            dao.insert(e);
        } catch (AppException e1) {
            e1.printStackTrace();
        }
    }

    //





}
