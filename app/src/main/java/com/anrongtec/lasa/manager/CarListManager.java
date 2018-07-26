package com.anrongtec.lasa.manager;

import android.content.Context;

import com.anrongtec.lasa.entity.CarControlInfoEntity;
import com.anrongtec.lasa.interfaces.HttpInterfaces;
import com.anrongtec.lasa.interfaces.callback.StringDialogCallback;
import com.anrongtec.lasa.interfaces.result.PagingCarDataResult;
import com.anrongtec.lasa.utils.GsonUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * @author libo
 * @Description: 车辆列表
 * @date 2018/5/15
 */

public class CarListManager {
    private static final String TAG = "CarListManager";

    public static CarListManager getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static CarListManager instance = new CarListManager();
    }

    public void getCarList(Context context, String carNumber, String plateNum, int pageNumber, int
            pageRows, final
                           CallBackCarList backCarList) {
        HttpInterfaces.ListCar(carNumber, plateNum, String.valueOf(pageNumber), String.valueOf
                (pageRows), new StringDialogCallback(context, "加载中") {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (body != null) {
                    try {
                        PagingCarDataResult decode = GsonUtil.decode(body, PagingCarDataResult
                                .class);
                        if (decode == null) {
                            return;
                        } else if (decode.getCode().equals("010")) {
                            ToastUtils.showLong(decode.getMsg());
                            return;
                        }

                        PagingCarDataResult.PaingData data = decode.getData();
                        List<CarControlInfoEntity> carList = data.getResultList();
                        backCarList.callBackSucceed(carList);
                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                backCarList.callBackError(response);
            }
        }, CarListManager.class.hashCode());
    }

    /**
     * 回调接口
     */
    public interface CallBackCarList {
        void callBackSucceed(List<CarControlInfoEntity> listCar);

        void callBackError(Response<String> response);
    }
}
