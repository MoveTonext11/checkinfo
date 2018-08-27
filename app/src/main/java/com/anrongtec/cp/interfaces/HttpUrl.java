package com.anrongtec.cp.interfaces;

/**
 * 衡水：172.168.30.50:7040 /保定：192.168.20.70:10055  请求URL
 */
public class HttpUrl {

    public static String PORT = ":8012";
    public static String IP_PORT = "192.168.1.16" + PORT;
//    public static String IP_PORT = "192.168.1.199" + PORT;

    public static String getUrl() {
        return "http://" + IP_PORT;
    }

    //人，车核查比对信息接口
    public static String CHECKINFO = "http://172.168.30.50:7042/webCache/oc/kstgCheck_checkInfo.action";
    //无证化安检接口
    public static String CHECKANJIAN = "http://172.168.30.50:7042/webCache/oc/kstgCheck_checkWZAJInfo.action";
    //三级车转一级车
    public static String CHANGEBLACK = "http://172.168.30.50:7042/webCache/oc/kstgCheck_andriodWhiteTurnBlack.action";

    //人员布控
    public static String PERSON_CONTROL = getUrl() + "/AnRongI/oc/AppCheckInfo_doAddPerson.action";
    //车辆布控
    public static String CAR_CONTROL = getUrl() + "/AnRongI/oc/AppCheckInfo_doAddCar.action";
    //撤控人员
    public static String RECONTROL_PERSON = getUrl() + "/AnRongI/oc/AppCheckInfo_deletePerson.action";
    //撤控人员
    public static String RECONTROL_CAR = getUrl() + "/AnRongI/oc/AppCheckInfo_deleteCar.action";
    //布控人   查询人员列表
    public static String PERSON_CONTROLLIST = getUrl() + "/AnRongI/oc/AppCheckInfo_listPerson.action";
    //布控人   查询车辆列表
    public static String CAR_CONTROLLIST = getUrl() + "/AnRongI/oc/AppCheckInfo_listCar.action";

    //查人查车信息   (默认本地查询   接口布控之后更改访问地址)
    public static String CheckInfo = getUrl() + "/AnRongI/as/whiteListAction_checkInfo.action";
    //核查记录消息返回（默认本地   发布修改）
    public static String CheckHestory = getUrl() + "/AnRongI/as/whiteListAction_checkAllEvent.action";
}
