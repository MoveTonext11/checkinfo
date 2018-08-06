package com.anrongtec.cp.manager;

import android.content.Context;
import android.util.Log;

import com.anrongtec.cp.entity.AnjianParametersEntity;
import com.anrongtec.cp.interfaces.HttpInterfaces;
import com.anrongtec.cp.interfaces.callback.StringDialogCallback;
import com.anrongtec.cp.interfaces.result.DataResult;
import com.anrongtec.cp.utils.GsonUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.Response;

/**
 * 无证化三级车转一级车 ---存疑
 *
 * @author libo
 * @Description:
 * @date 2018/6/10
 */

public class ThirdToFirstManager {
    private static final String TAG = ThirdToFirstManager.class.getSimpleName();

    public static ThirdToFirstManager getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static ThirdToFirstManager instance = new ThirdToFirstManager();
    }

    /**
     * 车辆三级转一级存疑接口
     *
     * @param parametersEntity
     */
    public void CarThirdToFirst(Context context, AnjianParametersEntity
            parametersEntity, final CallBackHeCha callback) {
        HttpInterfaces.thirdToFirst(parametersEntity, new StringDialogCallback(context,
                "加载中...") {
            @Override
            public void onSuccess(Response<String> response) {
                //请求成功，解析返回的数据
                String body = response.body();
                if (body != null) {
                    try {
                        DataResult decode = GsonUtil.decode(body, DataResult.class);
                        if (decode == null) {
                            return;
                        } else if (decode.getResultCode().equals("010")) {
                            ToastUtils.showLong(decode.getResultMsg());
                            return;
                        }

                        callback.callBackSucceed(decode.getResultCode());
                    } catch (Exception e) {
                        Log.d(TAG, "报错信息: " + e
                                .getLocalizedMessage());
                    }
                }

            }

            @Override
            public void onError(Response<String> response) {
                //请求异常
                super.onError(response);
                callback.callBackError(response);
            }
        }, ThirdToFirstManager.class.hashCode());
    }


    public interface CallBackHeCha {
        void callBackSucceed(String code);

        void callBackError(Response<String> response);
    }
}
