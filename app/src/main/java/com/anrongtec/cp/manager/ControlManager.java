package com.anrongtec.cp.manager;

import android.content.Context;
import android.util.Log;

import com.anrongtec.cp.interfaces.HttpInterfaces;
import com.anrongtec.cp.interfaces.callback.StringDialogCallback;
import com.anrongtec.cp.interfaces.result.DataResult;
import com.anrongtec.cp.utils.GsonUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;

import java.util.Map;

/**
 * @author libo
 * @Description: 布控管理：人员，车辆的布控和撤控
 * @date 2018/5/15
 */

public class ControlManager {
    private static final String TAG = "ControlManager";

    public static ControlManager getInstance() {
        return ControlManager.LazyHolder.instance;
    }

    private static class LazyHolder {
        private static ControlManager instance = new ControlManager();
    }

    /**
     * 人员布控申请
     *
     * @param context
     * @param name
     * @param gender
     * @param idCard
     * @param perType
     * @param address
     * @param anqing
     * @param linkPer
     * @param linkPhone
     * @param remark
     * @param callBackControl
     */
    public void personControl(Context context, String name, String gender, String idCard, String
            perType, String address, String anqing, String linkPer, String linkPhone, final String
                                      remark, final ControlManager.CallBackControl
                                      callBackControl) {
        HttpInterfaces.PersonControl(name, gender, idCard, perType, address, anqing, linkPer,
                linkPhone, remark, new StringDialogCallback(context, "加载中...") {


                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body();
                        if (json != null) {
                            DataResult<String> dataResult = GsonUtil.decode(json, new
                                    TypeToken<DataResult<String>>() {
                                    }.getType());
                            callBackControl.callBackSucceed(dataResult);

                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBackControl.callBackError(response);
                    }
                }, ControlManager.class.hashCode());
    }


    /**
     * 人员布控申请
     *
     * @param context
     * @param maps
     * @param callBackControl
     */
    public void personControl(Context context, Map<String, String> maps, final ControlManager
            .CallBackControl
            callBackControl) {
        HttpInterfaces.PersonControl(maps, new StringDialogCallback(context, "加载中...") {


            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body();
                if (json != null) {
                    DataResult<String> dataResult = GsonUtil.decode(json, new
                            TypeToken<DataResult<String>>() {
                            }.getType());
                    if (dataResult == null) {
                        return;
                    } else if (dataResult.getResultCode().equals("010")) {
                        ToastUtils.showLong(dataResult.getResultMsg());
                        return;
                    }
                    callBackControl.callBackSucceed(dataResult);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                callBackControl.callBackError(response);
            }
        }, ControlManager.class.hashCode());
    }


    /**
     * 人员撤控
     *
     * @param context
     * @param lxr
     * @param sfzh
     * @param ckyj
     * @param callBackControl
     */
    public void personReControl(Context context, String lxr, String sfzh, String ckyj
            , final ControlManager.CallBackControl callBackControl) {
        HttpInterfaces.RecontrolPerson(lxr, sfzh, ckyj, new StringDialogCallback(context,
                "加载中") {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (body != null) {
                    DataResult<String> decode = GsonUtil.decode(body, new
                            TypeToken<DataResult<String>>() {
                            }.getType());
                    if (decode == null) {
                        return;
                    } else if (decode.getResultCode().equals("010")) {
                        ToastUtils.showLong(decode.getResultMsg());
                        return;
                    }
                    try {
                        callBackControl.callBackSucceed(decode);
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                callBackControl.callBackError(response);
            }
        }, ControlManager.class.hashCode());

    }

    /**
     * 车辆布控
     *
     * @param context
     * @param carNumber
     * @param carPerId
     * @param clsbdm
     * @param carPerName
     * @param fdjh
     * @param clys
     * @param dispose
     * @param keyType
     * @param hpzl
     * @param lxr
     * @param lxdh
     * @param remark
     * @param callBackControl
     */
    public void carControl(Context context, String carNumber, String carPerId, String clsbdm, String
            carPerName, String fdjh, String clys, String dispose, String keyType, String hpzl,
                           String lxr, String lxdh, String remark, final ControlManager
            .CallBackControl callBackControl) {
        HttpInterfaces.CarControl(carNumber, carPerId, clsbdm, carPerName, fdjh, clys, dispose,
                keyType, hpzl, lxr, lxdh, remark, new StringDialogCallback(context, "加载中") {


                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        if (body != null) {
                            DataResult<String> dataResult = GsonUtil.decode(body, new
                                    TypeToken<DataResult<String>>() {
                                    }.getType());

                            callBackControl.callBackSucceed(dataResult);

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBackControl.callBackError(response);
                    }
                }, ControlManager.class.hashCode());


    }


    /**
     * 车辆布控
     *
     * @param context
     * @param maps            参数集合
     * @param callBackControl
     */
    public void carControl(Context context, Map<String, String> maps, final ControlManager
            .CallBackControl callBackControl) {
        HttpInterfaces.CarControl(maps, new StringDialogCallback(context, "加载中") {


            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                Log.d(TAG, "输出的数据" + body);
                if (body != null) {
                    DataResult<String> dataResult = GsonUtil.decode(body, new
                            TypeToken<DataResult<String>>() {
                            }.getType());
                    if (dataResult == null) {
                        return;
                    } else if (dataResult.getResultCode().equals("010")) {
                        ToastUtils.showLong(dataResult.getResultMsg());
                        return;
                    }
                    callBackControl.callBackSucceed(dataResult);

                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                callBackControl.callBackError(response);
            }
        }, ControlManager.class.hashCode());


    }


    /**
     * 车辆撤控
     *
     * @param context
     * @param carNumber
     * @param lxr
     * @param ckyj
     * @param backControl
     */
    public void carReControl(Context context, String carNumber, String plateNum, String lxr,
                             String ckyj,
                             final ControlManager.CallBackControl backControl) {
        HttpInterfaces.RecontrolCar(carNumber, plateNum, lxr, ckyj, new StringDialogCallback
                (context,
                        "加载中") {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (body != null) {
                    DataResult<String> decode = GsonUtil.decode(body, new
                            TypeToken<DataResult<String>>() {
                            }.getType());
                    if (decode == null) {
                        return;
                    } else if (decode.getResultCode().equals("010")) {
                        ToastUtils.showLong(decode.getResultMsg());
                        return;
                    }
                    backControl.callBackSucceed(decode);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                backControl.callBackError(response);
            }

        }, ControlManager.class.hashCode());

    }

    /**
     * 回调接口
     */
    public interface CallBackControl {
        void callBackSucceed(DataResult dataResult);

        void callBackError(Response<String> response);
    }

}
