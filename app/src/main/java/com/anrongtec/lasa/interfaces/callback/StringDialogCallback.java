package com.anrongtec.lasa.interfaces.callback;

import android.content.Context;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;


/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/4/8
 * 描    述：我的Github地址  https://github.com/jeasonlzy0216
 * 修订历史：
 * ================================================
 */
public abstract class StringDialogCallback extends StringCallback {

    private MaterialDialog dialog;

    public StringDialogCallback(Context context) {
        this(context, null);
    }
    public StringDialogCallback(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) {
//    		dialog = DialogUtil.createLoadingDialog(context, "请稍候...");
            msg = "请稍候...";
        }
        dialog = new MaterialDialog.Builder(context)
                .progress(true, 0)
                .cancelable(false)
                .content(msg)
                .show();
    }

    public StringDialogCallback() {

    }


    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        dismissDialog();
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        dismissDialog();
    }

    private void dismissDialog() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
