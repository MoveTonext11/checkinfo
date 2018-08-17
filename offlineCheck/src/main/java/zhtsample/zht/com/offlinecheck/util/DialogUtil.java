package zhtsample.zht.com.offlinecheck.util;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import zhtsample.zht.com.offlinecheck.R;

/**
 * 对话框工具类
 * 
 * @ClassName: DialogUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongtianhao
 * @date 2016年7月12日 上午10:49:10
 *
 */
@SuppressLint("NewApi")
public class DialogUtil {

	/***
	 * 创建单选对话框
	 * 
	 */
	public static Dialog createSingleSelectionDialog(Context context,
	                                                 String title, String[] datas,
	                                                 OnClickListener linister) {
		Builder builder = new Builder(context);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		} else {
			builder.setTitle("温馨提示");
		}
		builder.setItems(datas, linister);
		Dialog dialog = builder.create();
		builder.setCancelable(false);
		builder.show();
		return dialog;
	}

	/**
	 * 
	 * @Title: createLoadingDialog
	 * @Description:创建进度条对话框
	 * @param @param context 上下文
	 * @param @return
	 * @return ProgressDialog 返回类型
	 * @param @param msg
	 * @throws
	 */
	public static ProgressDialog createLoadingDialog(Context context, String msg) {
		ProgressDialog dialog = new ProgressDialog(context, R.style.AlertDialogTheme);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(false);
		if (TextUtils.isEmpty(msg)) {
			dialog.setMessage("正在验证...");
		} else {
			dialog.setMessage(msg);
		}
		dialog.show();
		return dialog;

	}

	/**
	 * 单按钮的dialog
	 * @param context
	 * @param title
	 * @param msg
	 * @return
	 */
	public static void createAlertDialogSingle(Context context, String title,
	                                           String msg, boolean hasNegativeButton, DialogInterface.OnClickListener listener) {
		Builder builder = new Builder(context, R.style.AlertDialogTheme);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton("确定", listener);
		if (hasNegativeButton)
			builder.setNegativeButton("取消", null);
		builder.setCancelable(false);
		builder.show();
//		Dialog dialog = builder.create();
//		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
//		dialog.show();
	}
	public static void createAlertDialogSingle(Context context, String title,
	                                           String msg, DialogInterface.OnClickListener listener) {
		createAlertDialogSingle(context, title, msg, false, listener);
	}

	public interface OnClickListenerWithString{
		void onClick(DialogInterface dialog, int which, String text);
	}

}
