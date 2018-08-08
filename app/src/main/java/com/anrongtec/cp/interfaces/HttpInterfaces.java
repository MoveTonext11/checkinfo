package com.anrongtec.cp.interfaces;

import com.anrongtec.cp.entity.AnjianParametersEntity;
import com.anrongtec.cp.entity.CheckInfoParamtersEntity;
import com.anrongtec.cp.utils.GsonUtil;
import com.anrongtec.cp.utils.ObtainInfo;
import com.lzy.okgo.callback.AbsCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求接口类
 *
 * @author libo
 */
public class HttpInterfaces {

    private static Map<String, String> getParams() {
        return new HashMap<>();
    }

    private static Map<String, String> commonInfoMap = ObtainInfo.getInstance().getCommonInfoMap();

    /**
     *人、车、人+车核查信息
     * 2018年7月20日10:59:23
     */
    public static void checkInfo(String url,HashMap<String,String> map,AbsCallback
            callback){
        HttpUtil.post(url,map,callback,null);
    }


    /**
     * 核查记录
     * 2018年7月26日
     */
    public static void checkhestory(String url,HashMap<String,String> map,AbsCallback
            callback){
        HttpUtil.post(url,map,callback,null);
    }


    /**
     * 人，车核查比对信息接口--InfoCheckNew
     * @param infoParamtersEntity
     * @param callback
     * @param tag
     */
    public static void checkInfo(CheckInfoParamtersEntity infoParamtersEntity, AbsCallback
            callback, Object tag) {
        Map<String, String> jsonObj = getParams();

        jsonObj.put("sfzh", infoParamtersEntity.getSfzh());
        jsonObj.put("username", "anrong");
        jsonObj.put("password", "anrong");
        jsonObj.put("sbid", infoParamtersEntity.getSbid());
        jsonObj.put("cphm", infoParamtersEntity.getCphm());
        jsonObj.put("hpzl", infoParamtersEntity.getHpzl());
        jsonObj.put("checkId", commonInfoMap.get("checkId"));
        jsonObj.put("checkName", commonInfoMap.get("checkName"));

        HttpUtil.post(HttpUrl.CHECKINFO, GsonUtil.toJson(jsonObj), callback, tag);
    }


    /**
     * 无证化安检
     * @param parametersEntity
     * @param callback
     * @param tag
     */
    public static void anjianCheckInfo(AnjianParametersEntity parametersEntity, AbsCallback
            callback, Object tag) {
        Map<String, String> jsonObj = getParams();
        jsonObj.put("username", "anrong");
        jsonObj.put("password", "anrong");
        jsonObj.put("sfzh", parametersEntity.getSfzh());
        jsonObj.put("sbid", parametersEntity.getSbid());
        jsonObj.put("cphm", parametersEntity.getCphm());
        jsonObj.put("hpzl", parametersEntity.getHpzl());
        jsonObj.put("checkId", commonInfoMap.get("checkId"));
        jsonObj.put("checkName", commonInfoMap.get("checkName"));
        jsonObj.put("date", String.valueOf(parametersEntity.getDate()));
        jsonObj.put("gz", String.valueOf(parametersEntity.getGz()));
        jsonObj.put("sszdld", parametersEntity.getSszdld());
        jsonObj.put("name", parametersEntity.getName());
        jsonObj.put("ldqy", String.valueOf(parametersEntity.getLdqy()));
        jsonObj.put("excuse", parametersEntity.getExcuse());
        HttpUtil.post(HttpUrl.CHECKANJIAN, GsonUtil.toJson(jsonObj), callback, tag);
    }


    /**
     * 车辆布控 Map 参数
     *
     * @param map      需要参数封装Map集合里
     * @param callback
     * @param tag
     */
    public static void CarControl(Map<String, String> map, AbsCallback callback,
                                  Object tag) {
        HttpUtil.post(HttpUrl.CAR_CONTROL, map, callback,tag);
    }


    /**
     * 人员布控 Map参数
     * @param map
     * @param callback
     * @param tag
     */
    public static void PersonControl(Map<String, String> map, AbsCallback callback, Object tag) {
        HttpUtil.post(HttpUrl.PERSON_CONTROL, map, callback, tag);
    }


    /**
     * 布控人员列表
     *
     * @param name       姓名
     * @param idCard     身份证号
     * @param pageNumber 第几页
     * @param pageRows   本页条数
     * @param callback
     * @param tag
     */
    public static void ListPerson(String name, String idCard, String pageNumber, String pageRows,
                                  AbsCallback callback, Object tag) {
        Map<String, String> params = getParams();
        params.put("xm", name);
        params.put("sfzh", idCard);
        params.put("pageNum", pageNumber);
        params.put("pageRows", pageRows);
        HttpUtil.post(HttpUrl.PERSON_CONTROLLIST, params, callback, tag);
    }

    /**
     * 布控车辆列表
     *
     * @param carNumber  车牌号码
     * @param plateNum   号牌种类
     * @param pageNumber 第几页
     * @param pageRows   本页条数
     * @param callback
     * @param tag
     */
    public static void ListCar(String carNumber, String plateNum, String pageNumber, String
            pageRows, AbsCallback callback, Object tag) {
        Map<String, String> params = getParams();
        params.put("carNumber", carNumber);
        params.put("carPerId", plateNum);
        params.put("pageNum", pageNumber);
        params.put("pageRows", pageRows);
        HttpUtil.post(HttpUrl.CAR_CONTROLLIST, params, callback, tag);
    }

    /**
     * 人员撤控
     *
     * @param lxr      布控联系人
     * @param sfzh     被车控人的身份证号（嫌疑犯的身份证）
     * @param ckyj     撤控理由
     * @param callback
     * @param tag
     */
    public static void RecontrolPerson(String lxr, String sfzh, String ckyj,String ckr,
                                       AbsCallback callback, Object tag) {
        Map<String, String> paramsMap = getParams();
        paramsMap.put("lxr", lxr);
        paramsMap.put("sfzh", sfzh);
        paramsMap.put("ckr", ckr);
        paramsMap.put("ckyj", ckyj);
        HttpUtil.post(HttpUrl.RECONTROL_PERSON, paramsMap, callback, tag);
    }

    /**
     * 车辆撤控
     * @param carNumber 车牌号
     * @param ckyj      撤控理由
     * @param callback
     */
    public static void Recontrol(String carNumber, String lxr,String ckyj,String ckr, AbsCallback callback) {
        Map<String, String> params = getParams();
        params.put("carNumber", carNumber);
        params.put("lxr", lxr);
        params.put("ckr", ckr);
        params.put("ckyj", ckyj);
        HttpUtil.post(HttpUrl.RECONTROL_CAR, params, callback, null);
    }

}
