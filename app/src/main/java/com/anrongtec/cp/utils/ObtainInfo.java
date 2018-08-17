package com.anrongtec.cp.utils;

import android.content.Context;
import android.os.Build;

import com.anrongtec.cp.MyApplication;
import com.yckj.ydjw.policelibrary.PoilceUserManage;
import com.yckj.ydjw.policelibrary.PoliceManage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author libo
 * @ClassName: ObtainInfo
 * @date 2018/5/25
 **/
public class ObtainInfo {

    private Context context;
    private PoilceUserManage policeManage;


    private ObtainInfo() {
        this.context = MyApplication.appContextInstance;
    }

    public static ObtainInfo getInstance() {
        return new ObtainInfo();
    }

    /**
     * 获取公共参数
     *
     * @return
     */
    public Map<String, String> getCommonInfoMap() {
        Map<String, String> commonMap = new HashMap<String, String>();
        try {
            policeManage = PoliceManage.getPoliceUserManage(context);
            commonMap.put("checkName", policeManage.getRealname());
            commonMap.put("checkId", policeManage.getSfz());
            commonMap.put("Policeid", policeManage.getPoliceid());
            commonMap.put("LocationX", policeManage.getLongitude());
            commonMap.put("LocationY", policeManage.getLatitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonMap;
    }

    /**
     * 检查终端类型
     * @return
     */
    public static boolean checkPhoneModel() {
        String model = Build.MODEL;
        if (model.equals("UniStrong HD508") || model.equals("HD808")) {
            return true;
        }
        return false;
    }
}
