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
     * 三级车转一级车（车辆存疑）
     *
     * @param paramtersEntity
     * @param callback
     * @param tag
     */
    public static void thirdToFirst(AnjianParametersEntity paramtersEntity, AbsCallback
            callback, Object tag) {
        Map<String, String> map = getParams();
        map.put("sbid", paramtersEntity.getSbid());
        map.put("cphm", paramtersEntity.getCphm());
        map.put("hpzl", paramtersEntity.getHpzl());
        map.put("checkId", commonInfoMap.get("checkId"));
        map.put("checkName", commonInfoMap.get("checkName"));
        map.put("excuse", paramtersEntity.getExcuse());
        HttpUtil.post(HttpUrl.CHANGEBLACK, GsonUtil.toJson(map), callback, tag);
    }


    /**
     * 车辆布控
     *
     * @param carNumber  车牌号
     * @param carPerId   车主身份证号
     * @param clsbdm     车辆识别代码
     * @param carPerName 车主姓名
     * @param fdjh       发动机号
     * @param clys       车辆颜色
     * @param dispose    处置方式
     * @param keyType    车辆类型
     * @param hpzl       号牌种类
     * @param lxr        联系人
     * @param lxdh       联系电话
     * @param remark     描述
     * @param callback
     * @param tag
     */
    public static void CarControl(String carNumber, String carPerId, String clsbdm, String
            carPerName, String fdjh, String clys, String dispose, String keyType, String hpzl,
                                  String lxr, String lxdh, String remark, AbsCallback callback,
                                  Object tag) {
        Map<String, String> params = getParams();
        params.put("carNumber", carNumber);
        params.put("carPerId", carPerId);
        params.put("clsbdm", clsbdm);
        params.put("carPerName", carPerName);
        params.put("fdjh", fdjh);
        params.put("clys", clys);
        params.put("dispose", dispose);
        params.put("keyType", keyType);
        params.put("hpzl", hpzl);
        params.put("lxr", lxr.equals("") ? commonInfoMap.get("checkName") : lxr);
        params.put("lxdh", lxdh);
        params.put("remark", remark);
        HttpUtil.get(HttpUrl.CAR_CONTROL, params, callback, tag);

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

        Map<String, String> paramsMap = getParams();
        paramsMap.put("carNumber", map.get("carNumber"));
        paramsMap.put("carPerId", map.get("carPerId"));
        paramsMap.put("clsbdm", map.get("clsbdm"));
        paramsMap.put("carPerName", map.get("carPerName"));
        paramsMap.put("fdjh", map.get("fdjh"));
        paramsMap.put("clys", map.get("clys"));
        paramsMap.put("dispose", map.get("dispose"));
        paramsMap.put("keyType", map.get("keyType"));
        paramsMap.put("hpzl", map.get("hpzl"));
        paramsMap.put("lxr", map.get("lxr").equals("") ? commonInfoMap.get("checkName") : map.get
                ("lxr"));
        paramsMap.put("lxdh", map.get("lxdh"));
        paramsMap.put("remark", map.get("remark"));
        HttpUtil.get(HttpUrl.CAR_CONTROL, paramsMap, callback, tag);
    }


    /**
     * 人员布控
     *
     * @param name      姓名
     * @param gender    性别
     * @param idCard    身份证
     * @param rylb      人员类别
     * @param hjxz      户籍详细地址
     * @param jyaq      简要案情
     * @param linkPer   联系人
     * @param linkPhone 联系电话
     * @param remark    备注
     * @param callback
     * @param tag
     */
    public static void PersonControl(String name, String gender, String idCard, String rylb,
                                     String hjxz, String jyaq, String linkPer, String linkPhone,
                                     String remark, AbsCallback callback, Object tag) {
        Map<String, String> params = getParams();
        params.put("xm", name);
        params.put("xb", gender);
        params.put("sfzh", idCard);
        params.put("rylb", rylb);
        params.put("HJDQH", hjxz);
        params.put("jyaq", jyaq);
        params.put("lxr", linkPer);
        params.put("lxdh", linkPhone);
        params.put("remark", remark);
        HttpUtil.get(HttpUrl.PERSON_CONTROL, params, callback, tag);
    }


    /**
     * 人员布控 Map参数
     *
     * @param map
     * @param callback
     * @param tag
     */
    public static void PersonControl(Map<String, String> map, AbsCallback callback, Object tag) {
        Map<String, String> paramsMap = getParams();
        paramsMap.put("xm", map.get("xm"));
        paramsMap.put("xb", map.get("xb"));
        paramsMap.put("sfzh", map.get("sfzh"));
        paramsMap.put("rylb", map.get("rylb"));
        paramsMap.put("HJDQH", map.get("HJDQH"));
        paramsMap.put("jyaq", map.get("jyaq"));
        paramsMap.put("gxmj", map.get("gxmj"));
        paramsMap.put("gxmjdh", map.get("gxmjdh"));
        paramsMap.put("lxr", map.get("lxr"));
        paramsMap.put("lxdh", map.get("lxdh"));
        paramsMap.put("remark", map.get("remark"));

        HttpUtil.get(HttpUrl.PERSON_CONTROL, paramsMap, callback, tag);
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
        HttpUtil.get(HttpUrl.PERSON_CONTROLLIST, params, callback, tag);
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
            pageRows, AbsCallback callback, Object
                                       tag) {
        Map<String, String> params = getParams();
        params.put("carNumber", carNumber);
        params.put("carPerId", plateNum);
        params.put("pageNum", pageNumber);
        params.put("pageRows", pageRows);
        HttpUtil.get(HttpUrl.CAR_CONTROLLIST, params, callback, tag);
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
    public static void RecontrolPerson(String lxr, String sfzh, String ckyj,
                                       AbsCallback callback, Object tag) {
        Map<String, String> paramsMap = getParams();
        paramsMap.put("lxr", lxr);
        paramsMap.put("sfzh", sfzh);
        paramsMap.put("ckr", commonInfoMap.get("checkName"));
        paramsMap.put("ckyj", ckyj);
        HttpUtil.get(HttpUrl.RECONTROL_PERSON, paramsMap, callback, tag);
    }

    /**
     * 车辆撤控
     *
     * @param carNumber 车牌号
     * @param lxr       联系人
     * @param ckyj      撤控理由
     * @param callback
     * @param tag
     */
    public static void RecontrolCar(String carNumber, String plateNum, String lxr, String ckyj,
                                    AbsCallback callback, Object tag) {
        Map<String, String> params = getParams();
        params.put("carNumber", carNumber);
        params.put("hpzl", plateNum);
        params.put("lxr", lxr.equals("") ? commonInfoMap.get("checkName") : lxr);
        params.put("ckr", commonInfoMap.get("checkName"));
        params.put("ckyj", ckyj);
        HttpUtil.get(HttpUrl.RECONTROL_CAR, params, callback, tag);
    }

}
