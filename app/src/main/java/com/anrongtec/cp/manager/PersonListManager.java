package com.anrongtec.cp.manager;

import android.content.Context;
import android.util.Log;

import com.anrongtec.cp.entity.PersonControlInfoEntity;
import com.anrongtec.cp.interfaces.HttpInterfaces;
import com.anrongtec.cp.interfaces.callback.StringDialogCallback;
import com.anrongtec.cp.interfaces.result.PagingPersonDataResult;
import com.anrongtec.cp.utils.GsonUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * @author libo
 * @Description: 人员列表
 * @date 2018/5/15
 */

public class PersonListManager {
    private static final String TAG = "PersonListManager";

    public static PersonListManager getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static PersonListManager instance = new PersonListManager();
    }

    public void getPersonsList(Context context, String name, String idCard, int pageNumber, int
            pageRows, final CallBackPerList
                                       backCall) {
        HttpInterfaces.ListPerson(name, idCard, String.valueOf(pageNumber), String.valueOf
                (pageRows), new StringDialogCallback(context, "加载中") {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (body != null) {
                    try {
                        PagingPersonDataResult decode = GsonUtil.decode(body, PagingPersonDataResult
                                .class);
                        if (decode == null) {
                            return;
                        } else if (decode.getResultCode().equals("010")) {
                            ToastUtils.showLong(decode.getResultMsg());
                            return;
                        }

                        PagingPersonDataResult.PaingData data = decode.getData();
                        List resultList = data.getResultList();
                        backCall.callBackSucceed(resultList);
                    } catch (Exception e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                backCall.callBackError(response);
            }
        }, PersonListManager.class.hashCode());

    }

    /**
     * 回调接口
     */
    public interface CallBackPerList {
        void callBackSucceed(List<PersonControlInfoEntity> listPerson);

        void callBackError(Response<String> response);
    }
}
