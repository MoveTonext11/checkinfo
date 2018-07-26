package com.anrongtec.lasa.manager;

import android.content.Context;
import android.util.Log;

import com.anrongtec.lasa.entity.CheckInfoBaseEntity;
import com.anrongtec.lasa.entity.CheckInfoParamtersEntity;
import com.anrongtec.lasa.entity.CheckInfoZBCarEntity;
import com.anrongtec.lasa.entity.CheckInfoZBPersonEntity;
import com.anrongtec.lasa.interfaces.HttpInterfaces;
import com.anrongtec.lasa.interfaces.callback.StringDialogCallback;
import com.anrongtec.lasa.interfaces.result.CheckInfoDataResult;
import com.anrongtec.lasa.utils.GsonUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * 人员核查管理
 *
 * @author cxy
 */
public class VerifyManager {
    private static final String TAG = VerifyManager.class.getSimpleName();

    public static VerifyManager getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static VerifyManager instance = new VerifyManager();
    }

    /**
     * 人或车核查比对信息接口
     *
     * @param entity
     */
    public void verifyPersonAndCar(Context context, CheckInfoParamtersEntity entity, final
    CallBackHeCha callback) {
        HttpInterfaces.checkInfo(entity, new StringDialogCallback(context, "加载中...") {
            @Override
            public void onSuccess(Response<String> response) {
                //请求成功，解析返回的数据
                String body = response.body();
                if (body != null) {
                    CheckInfoDataResult decode = GsonUtil.decode(body, CheckInfoDataResult.class);
                    if (decode == null) {
                        return;
                    } else if (decode.getResultCode().equals("010")) {
                        ToastUtils.showLong(decode.getResultMsg());
                        return;
                    }
                    CheckInfoBaseEntity checkInfoBaseEntity = null;
                    CheckInfoZBPersonEntity checkInfoZBPersonEntity = null;
                    CheckInfoZBCarEntity checkInfoZBCarEntity = null;
                    try {
                        CheckInfoDataResult.ListData data = decode.getData();
                        List<CheckInfoBaseEntity> ryxxList = data.getRyxxList();
                        List<CheckInfoZBPersonEntity> zbryList = data.getZbryList();
                        List<CheckInfoZBCarEntity> zbclList = data.getZbclList();

                        if (ryxxList != null && ryxxList.size() != 0) {
                            checkInfoBaseEntity = ryxxList.get(0);
                        }
                        if (zbryList != null && zbryList.size() != 0) {
                            checkInfoZBPersonEntity = zbryList.get(0);
                        }
                        if (zbclList != null && zbclList.size() != 0) {
                            checkInfoZBCarEntity = zbclList.get(0);
                        }
                    } catch (Exception e) {
                        Log.d(TAG, "报错信息: " + decode.getResultMsg() + "\t" + e
                                .getLocalizedMessage());
                    }
                    callback.callBackSucceed(checkInfoBaseEntity, checkInfoZBPersonEntity,
                            checkInfoZBCarEntity);
                }

            }

            @Override
            public void onError(Response<String> response) {
                //请求异常
                super.onError(response);
                callback.callBackError(response);
            }
        }, VerifyManager.class.hashCode());
    }


    public interface CallBackHeCha {
        void callBackSucceed(CheckInfoBaseEntity baseEntity, CheckInfoZBPersonEntity
                zbPersonEntity, CheckInfoZBCarEntity zbCarEntity);

        void callBackError(Response<String> response);
    }

}
