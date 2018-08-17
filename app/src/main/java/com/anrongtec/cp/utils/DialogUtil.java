package com.anrongtec.cp.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

/**
 * 各种dialog
 *
 * @author dongtianhao
 */
public class DialogUtil {

    /***
     * 创建单选对话框
     *
     */
    public static Dialog createSingleSelectionDialog(Context context, String title, String[] datas,
                                                     DialogInterface.OnClickListener linister) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        } else {
            builder.setTitle("温馨提示");
        }
        builder.setItems(datas, linister);
        Dialog dialog = builder.create();
        builder.show();
        return dialog;
    }

    /**
     * 确定框
     */
    public static Dialog createNoticeDialog(Context context, String title, String message,
                                            DialogInterface.OnClickListener linister) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setPositiveButton("重试", linister);
        Dialog dialog = builder.create();
        builder.setCancelable(false);
        builder.show();
        return dialog;

    }

    /**
     * 确定框
     */
    public static Dialog createNoticeDialog(Context context, String title, String message, String btnName,
                                            DialogInterface.OnClickListener linister) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setPositiveButton(btnName, linister);
        Dialog dialog = builder.create();
        builder.setCancelable(false);
        builder.show();
        return dialog;

    }

    /**
     * 选择框
     */
    public static Dialog createSelectDialog(Context context, String title, String message,
                                            DialogInterface.OnClickListener listener1,
                                            DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setPositiveButton("重试", listener1);
        builder.setTitle(title).setMessage(message).setNegativeButton("游客模式", listener);
        Dialog dialog = builder.create();
        builder.setCancelable(false);
        builder.show();
        return dialog;

    }


    /**
     * 确定框
     */
    public static Dialog createNoticeDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("解决方法").setMessage(message).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        builder.show();
        dialog.setCancelable(true);
        return dialog;

    }


    /**
     * 提示框
     */
    public static Dialog createTipDialog(Context context, String title, String message,
                                         DialogInterface.OnClickListener linister,
                                         DialogInterface.OnClickListener onCancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setPositiveButton("确定", linister)
                .setNegativeButton("取消", onCancelListener);
        Dialog dialog = builder.create();
        builder.show();
        return dialog;

    }

}
