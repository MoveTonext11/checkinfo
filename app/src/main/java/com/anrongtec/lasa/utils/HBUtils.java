package com.anrongtec.lasa.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import com.anrongtec.lasa.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @author libo
 * @Description: 工具类，每一个方法为一个工具
 * @date 2018/5/26
 */

public class HBUtils {
    private static HBUtils UTILS;
    private static final String TAG = "HBUTILS";
    private static Context context;

    private static HBUtils getInstance() {
        if (UTILS == null) {
            UTILS = new HBUtils();
            context = MyApplication.appContextInstance;
        }
        return UTILS;
    }

    /**
     * @param name 需要设置红星的全部文本
     * @return
     * @Description: 设置必填字段红*
     */
    public static SpannableString requiredVerify(String name) {
        SpannableString spannableString = new SpannableString(name);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned
                .SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(1.2f), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获取指定路径下的证件照头像
     *
     * @param pathUrl
     * @return
     */
    public static Bitmap getBitmapRes(String pathUrl) {
        File imageFile;
        InputStream inputStream = null;
        Bitmap bitmap = null;

        imageFile = new File(pathUrl);
        if (!imageFile.exists()) {
            Log.d(TAG, pathUrl + "不存在");
        } else {
            try {
                inputStream = new FileInputStream(imageFile);
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bitmap;
    }

    public static String getDefaultIMEI(Context context1) {
        String strIMEI = "";
        TelephonyManager telephonyManager;
        try {
            telephonyManager = (TelephonyManager) context1.getSystemService(Context
                    .TELEPHONY_SERVICE);
            strIMEI = telephonyManager.getDeviceId();

            if (null == strIMEI || strIMEI.equals("")) {
                Class clazz = telephonyManager.getClass();
                Method getImei = clazz.getDeclaredMethod("getImei", int.class);//(int slotId)
                getImei.setAccessible(true);
                strIMEI = (String) getImei.invoke(telephonyManager);
                return strIMEI;
            }
        } catch (Exception e) {

        }
        return strIMEI;


    }

}
