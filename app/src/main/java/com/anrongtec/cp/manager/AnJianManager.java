package com.anrongtec.cp.manager;

import android.content.Context;
import android.util.Log;

import com.anrongtec.cp.entity.AnjianParametersEntity;
import com.anrongtec.cp.entity.CheckInfoBaseEntity;
import com.anrongtec.cp.entity.CheckInfoZBCarEntity;
import com.anrongtec.cp.entity.CheckInfoZBPersonEntity;
import com.anrongtec.cp.interfaces.HttpInterfaces;
import com.anrongtec.cp.interfaces.callback.StringDialogCallback;
import com.anrongtec.cp.interfaces.result.CheckInfoDataResult;
import com.anrongtec.cp.utils.GsonUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * 人员核查管理
 *
 * @author cxy
 */
public class AnJianManager {
    private static final String TAG = AnJianManager.class.getSimpleName();

    public static AnJianManager getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static AnJianManager instance = new AnJianManager();
    }

    /**
     * 核查比对信息接口
     *
     * @param parametersEntity
     */
    public void anJianVerifyPersonAndCar(Context context, AnjianParametersEntity
            parametersEntity, final CallBackHeCha callback) {
        HttpInterfaces.anjianCheckInfo(parametersEntity, new StringDialogCallback(context,
                "加载中...") {
            @Override
            public void onSuccess(Response<String> response) {
                //请求成功，解析返回的数据
                String body = response.body();
                if (body != null) {
                    try {
                        CheckInfoDataResult decode = GsonUtil.decode(body, CheckInfoDataResult.class);
                        if (decode == null) {
                            return;
                        } else if (decode.getResultCode().equals("010")) {
                            ToastUtils.showLong(decode.getResultMsg());
                            return;
                        }
                        CheckInfoDataResult.ListData data = null;

                        data = decode.getData();

                        callback.callBackSucceed(data.getThisRyxxList(), data.getZbryList(), data
                                .getZbclList(), data.getZt(), data.getRyxxList());
                    } catch (Exception e) {
                        Log.d(TAG, "报错信息: " + e.getLocalizedMessage());
                    }
                }

            }

            @Override
            public void onError(Response<String> response) {
                //请求异常
                super.onError(response);
                callback.callBackError(response);
            }
        }, AnJianManager.class.hashCode());
    }


    public interface CallBackHeCha {
        void callBackSucceed(List<CheckInfoBaseEntity> thisBaseEntity, List<CheckInfoZBPersonEntity>
                zbPersonEntity, List<CheckInfoZBCarEntity> zbCarEntity, String state,
                             List<CheckInfoBaseEntity> baseEntity);

        void callBackError(Response<String> response);
    }

}
