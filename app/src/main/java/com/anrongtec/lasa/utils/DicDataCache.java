package com.anrongtec.lasa.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 数据字典表
 */
public class DicDataCache {

    private Map<String, String> mzMap;//民族map
    private Map<String, String> csysMap;//车身颜色map
    private Map<String, String> clztMap;//车辆状态
    private Map<String, String> hpzlMap;//车辆号牌种类
    private Map<String, String> ryzlMap;//人员种类
    private Map<String, String> djzjzlMap;//登记证件种类
    private Map<String, String> sfzmzlMap;//身份证明种类
    private Map<String, String> jszztMap;//驾驶证状态
    private Map<String, String> zjcxMap;//准驾车型
    private Map<String, String> yhzgxMap;//与户主的关系
    private Map<String, String> changeBlackMap;//白转黑理由

    private DicDataCache() {
        initMZ();
        initCSYS();
        initClzt();
        initHpzl();
        initRyzl();
        initDjzjzl();
        initChangeBlackReason();

        initSfzmzl();
        initJszzt();
        initZjcx();
    }


    /**
     * 获取身份证明种类
     *
     * @param code
     * @return
     */
    public String getSfzmzl(String code) {
        if (code == null) {
            return null;
        }
        String name = sfzmzlMap.get(code.trim());
        return name == null ? code : name;
    }

    /**
     * 获取驾驶证状态
     *
     * @param code
     * @return
     */
    public String getJszzt(String code) {
        if (code == null) {
            return null;
        }
        String name = jszztMap.get(code.trim());
        return name == null ? code : name;
    }

    /**
     * 获取准驾车型
     *
     * @param code
     * @return
     */
    public String getZjcx(String code) {
        if (code == null) {
            return null;
        }
        String name = zjcxMap.get(code.trim());
        return name == null ? code : name;
    }

    /**
     * 获取与户主关系
     *
     * @param code
     * @return
     */
    public String getYhzgx(String code) {
        if (code == null) {
            return null;
        }
        String name = yhzgxMap.get(code.trim());
        return name == null ? code : name;
    }

    /**
     * 获取登记证件种类
     *
     * @param code
     * @return
     */
    public String getDjzjzl(String code) {
        if (code == null) {
            return null;
        }
        String name = djzjzlMap.get(code.trim());
        return name == null ? code : name;
    }

    /**
     * 获取车辆号牌种类
     *
     * @param code
     * @return
     */
    public String getHpzl(String code) {
        if (code == null) {
            return null;
        }
        String name = hpzlMap.get(code.trim());
        return name == null ? code : name;
    }

    public Map<String, String> gethpzlMap() {
        return hpzlMap;
    }


    /**
     * 获取人员类别
     *
     * @param code
     * @return
     */
    public String getRyzl(String code) {
        if (code == null) {
            return null;
        }
        String name = ryzlMap.get(code.trim());
        return name == null ? code : name;
    }

    /**
     * 获取白转黑枚举值
     *
     * @return
     */
    public Map<String, String> getChangeBlack() {
        return changeBlackMap;
    }

    public Map<String, String> getRyzlMap() {
        return ryzlMap;
    }


    /**
     * 获取车辆状态的字典数据
     *
     * @param code
     * @return
     */
    public String getClzt(String code) {
        if (code == null) {
            return null;
        }
        String name = clztMap.get(code.trim());
        return name == null ? code : name;
    }


    /**
     * 获取民族的名称
     *
     * @param code
     * @return
     */
    public String getMZ(String code) {
        if (code == null) {
            return null;
        }

        String name = mzMap.get(code.trim());
        return name == null ? code : name;
    }

    public String getMZCode(String name) {
        if (name == null) {
            return null;
        }

        for (Map.Entry<String, String> entry : mzMap.entrySet()) {
            if (entry.getValue().equals(name.trim())) {
                return entry.getKey();
            }
        }
        return name;
    }

    /**
     * 性别名称,1男2女
     *
     * @param code
     * @return
     */
    public String getXB(String code) {
        if (code == null) {
            return null;
        }

        if (code.trim().equals("1")) {
            return "男";
        } else if (code.trim().equals("2")) {
            return "女";
        } else {
            return code;
        }
    }

    public String getXBCode(String name) {
        if (name == null) {
            return null;
        }
        if (name.trim().equals("男")) {
            return "1";
        } else if (name.trim().equals("女")) {
            return "2";
        } else {
            return name;
        }
    }

    /**
     * 获取婚姻状况，10未婚，20已婚
     *
     * @return
     */
    public String getHYZK(String cd) {
        if (cd == null) {
            return null;
        }
        String code = cd.trim();
        if (code.equals("20")) {
            return "已婚";
        } else if (code.equals("21")) {
            return "初婚";
        } else if (code.equals("22")) {
            return "再婚";
        } else if (code.equals("23")) {
            return "复婚";
        } else if (code.equals("30")) {
            return "丧偶";
        } else if (code.equals("40")) {
            return "离婚";
        } else if (code.equals("10")) {
            return "未婚";
        } else {
            return code;
        }
    }

    /**
     * 10	未婚
     * 20	有配偶
     * 21	初婚
     * 22	再婚
     * 23	复婚
     * 30	丧偶
     * 40	离婚
     * 90	其它
     *
     * @return
     */
    public String getHYZKCode(String nm) {
        if (nm == null) {
            return null;
        }
        String name = nm.trim();
        if (name.equals("已婚")) {
            return "20";
        } else if (name.equals("初婚")) {
            return "21";
        } else if (name.equals("再婚")) {
            return "22";
        } else if (name.equals("复婚")) {
            return "23";
        } else if (name.equals("丧偶")) {
            return "30";
        } else if (name.equals("离婚")) {
            return "40";
        } else if (name.equals("未婚")) {
            return "10";
        } else {
            return name;
        }
    }


    /**
     * 获取车身颜色
     *
     * @param code
     * @return
     */
    public String getCSYS(String code) {
        if (code == null) {
            return null;
        }

        String name = csysMap.get(code.trim());
        return name == null ? code : name;
    }


    private static DicDataCache instance;

    public static DicDataCache get() {
        if (instance == null) {
            instance = new DicDataCache();
        }

        return instance;
    }

    //A:白  B：灰  C:黄  D:粉  E:红  F:紫  G:绿  H：蓝  I：棕  J：黑  Z：其他
    private void initCSYS() {
        csysMap = new HashMap<String, String>();
        csysMap.put("A", "白");
        csysMap.put("B", "灰");
        csysMap.put("C", "黄");
        csysMap.put("D", "粉");
        csysMap.put("E", "红");
        csysMap.put("F", "紫");

        csysMap.put("G", "绿");
        csysMap.put("H", "蓝");
        csysMap.put("I", "棕");
        csysMap.put("J", "黑");
        csysMap.put("Z", "其他");
    }

    private void initMZ() {
        mzMap = new HashMap<String, String>();
        mzMap.put("01", "汉族");
        mzMap.put("02", "蒙古族");
        mzMap.put("03", "回族");
        mzMap.put("04", "藏族");
        mzMap.put("05", "维吾尔族");
        mzMap.put("06", "苗族");
        mzMap.put("07", "彝族");
        mzMap.put("08", "壮族");
        mzMap.put("09", "布依族");
        mzMap.put("10", "朝鲜族");
        mzMap.put("11", "满族");
        mzMap.put("12", "侗族");
        mzMap.put("13", "瑶族");
        mzMap.put("14", "白族");
        mzMap.put("15", "土家族");
        mzMap.put("16", "哈尼族");
        mzMap.put("17", "哈萨克族");
        mzMap.put("18", "傣族");
        mzMap.put("19", "黎族");
        mzMap.put("20", "傈僳族");
        mzMap.put("21", "佤族");
        mzMap.put("22", "畲族");
        mzMap.put("23", "高山族");
        mzMap.put("24", "拉祜族");
        mzMap.put("25", "水族");
        mzMap.put("26", "东乡族");
        mzMap.put("27", "纳西族");
        mzMap.put("28", "景颇族");
        mzMap.put("29", "柯尔克孜族");
        mzMap.put("30", "土族");
        mzMap.put("31", "达斡尔族");
        mzMap.put("32", "仫佬族");
        mzMap.put("33", "羌族");
        mzMap.put("34", "布朗族");
        mzMap.put("35", "撒拉族");
        mzMap.put("36", "毛南族");
        mzMap.put("37", "仡佬族");
        mzMap.put("38", "锡伯族");
        mzMap.put("39", "阿昌族");
        mzMap.put("40", "普米族");
        mzMap.put("41", "塔吉克族");
        mzMap.put("42", "怒族");
        mzMap.put("43", "乌孜别克族");
        mzMap.put("44", "俄罗斯族");
        mzMap.put("45", "鄂温克族");
        mzMap.put("46", "德昂族");
        mzMap.put("47", "保安族");
        mzMap.put("48", "裕固族");
        mzMap.put("49", "京族");
        mzMap.put("50", "塔塔尔族");
        mzMap.put("51", "独龙族");
        mzMap.put("52", "鄂伦春族");
        mzMap.put("53", "赫哲族");
        mzMap.put("54", "门巴族");
        mzMap.put("55", "珞巴族");
        mzMap.put("56", "基诺族");
        mzMap.put("97", "其它未识别民族");
        mzMap.put("98", "外国人入中国籍");
    }

    private void initClzt() {
        clztMap = new HashMap<String, String>();
        clztMap.put("A", "正常");
        clztMap.put("B", "转出");
        clztMap.put("C", "被盗抢");
        clztMap.put("D", "停驶");
        clztMap.put("E", "注销");
        clztMap.put("G", "违法未处理");
        clztMap.put("H", "海关监管");
        clztMap.put("I", "事故未处理");
        clztMap.put("J", "嫌疑车");
        clztMap.put("K", "查封");
        clztMap.put("L", "暂扣");
        clztMap.put("OMK", "强制注销;查封");
        clztMap.put("OM", "锁定;强制注销");
        clztMap.put("EG", "注销;违法未处理");
        clztMap.put("MG", "强制注销;违法未处理");
        clztMap.put("CG", "被盗抢;违法未处理");
        clztMap.put("CM", "被盗抢;强制注销");
        clztMap.put("M", "强制注销");
        clztMap.put("N", "事故逃逸");
        clztMap.put("O", "锁定");
    }

    private void initHpzl() {
        hpzlMap = new LinkedHashMap<String, String>();
        hpzlMap.put("02", "小型汽车");
        hpzlMap.put("01", "大型汽车");
        hpzlMap.put("03", "使馆汽车");
        hpzlMap.put("04", "领馆汽车");
        hpzlMap.put("05", "境外汽车");
        hpzlMap.put("06", "外籍汽车");
        hpzlMap.put("07", "两/三轮摩托车");
        hpzlMap.put("08", "轻便摩托车");
        hpzlMap.put("09", "使馆摩托车");
        hpzlMap.put("10", "领馆摩托车");
        hpzlMap.put("11", "境外摩托车");
        hpzlMap.put("12", "外籍摩托车");
        hpzlMap.put("13", "农用运输车");
        hpzlMap.put("14", "拖拉机");
        hpzlMap.put("15", "挂车");
        hpzlMap.put("16", "教练汽车");
        hpzlMap.put("17", "教练摩托车");
        hpzlMap.put("18", "试验汽车");
        hpzlMap.put("19", "试验摩托车");
        hpzlMap.put("20", "临时入境汽车");
        hpzlMap.put("21", "临时入境摩托车");
        hpzlMap.put("22", "临时行驶车");
        hpzlMap.put("23", "警用汽车");
        hpzlMap.put("24", "警用摩托");
        hpzlMap.put("25", "原农机号牌");
        hpzlMap.put("99", "其它");
    }

    /**
     * 获取人员类别
     */
    private void initRyzl() {
        ryzlMap = new HashMap<>();
        ryzlMap.put("01", "进京非访");
        ryzlMap.put("02", "其他重点非访");
        ryzlMap.put("03", "涉公安访");
        ryzlMap.put("04", "国保涉稳");
        ryzlMap.put("05", "极端行为倾向");
        ryzlMap.put("06", "经侦涉稳");
        ryzlMap.put("07", "前科");
        ryzlMap.put("08", "在逃");
        ryzlMap.put("09", "精神病");
        ryzlMap.put("10", "涉恐");
    }

    private void initChangeBlackReason() {
        changeBlackMap = new HashMap<>();
        changeBlackMap.put("3", "发现为网约车");
        changeBlackMap.put("4", "发现为非公务租赁公司车");
        changeBlackMap.put("5", "发现为黑出租");
        changeBlackMap.put("6", "发现为重点人员外埠车辆");
        changeBlackMap.put("7", "发现为极端行为倾向");
        changeBlackMap.put("8", "发现为网逃");
        changeBlackMap.put("9", "发现为涉稳");
        changeBlackMap.put("10", "发现为涉毒");
        changeBlackMap.put("11", "发现为涉恐");
        changeBlackMap.put("12", "发现为犯罪前科");
        changeBlackMap.put("13", "发现为重点上访人员");
        changeBlackMap.put("14", "发现为涉军访");
        changeBlackMap.put("15", "发现为重性精神病");
        changeBlackMap.put("16", "发现为邪教");
    }

    private void initDjzjzl() {
        djzjzlMap = new HashMap<String, String>();
        djzjzlMap.put("A", "居民身份证 ");
        djzjzlMap.put("F", "中国护照 ");
        djzjzlMap.put("G", "外国护照 ");
        djzjzlMap.put("H", "居民户口簿 ");
        djzjzlMap.put("I", "旅行证 ");
        djzjzlMap.put("J", "回乡证 ");
        djzjzlMap.put("K", "居留证件 ");
        djzjzlMap.put("L", "驻华机构证明 ");
        djzjzlMap.put("M", "使领馆人员身份证明");
        djzjzlMap.put("E", "军官离退休证");
        djzjzlMap.put("D", "士兵证");
        djzjzlMap.put("C", "军官证");
        djzjzlMap.put("B", "组织机构代码证书");
    }

    private void initSfzmzl() {
        sfzmzlMap = new HashMap<String, String>();
        sfzmzlMap.put("A", "居民身份证");
        sfzmzlMap.put("B", "组织机构代码证书");
        sfzmzlMap.put("C", "军官证");
        sfzmzlMap.put("D", "士兵证");
        sfzmzlMap.put("E", "军官离退休证");
        sfzmzlMap.put("F", "境外人员身份证明");
        sfzmzlMap.put("G", "外交人员身份证明");
        sfzmzlMap.put("H", "居民户口簿");
        sfzmzlMap.put("I", "旅行证");
        sfzmzlMap.put("J", "单位注销证明");
        sfzmzlMap.put("K", "居住暂住证明");
        sfzmzlMap.put("L", "驻华机构证明");
        sfzmzlMap.put("M", "使领馆人员身份证明");
    }

    private void initJszzt() {
        jszztMap = new HashMap<String, String>();
        jszztMap.put("A", "正常");
        jszztMap.put("B", "超分");
        jszztMap.put("C", "转出");
        jszztMap.put("E", "撤销");
        jszztMap.put("F", "吊销");
        jszztMap.put("G", "注销");
        jszztMap.put("Z", "其他");
        jszztMap.put("M", "逾期未换证");
        jszztMap.put("H", "违法未处理");
        jszztMap.put("R", "注销可恢复");
        jszztMap.put("L", "锁定");
        jszztMap.put("D", "暂扣");
        jszztMap.put("K", "协查");
        jszztMap.put("N", "延期换证");
        jszztMap.put("J", "停止使用");
        jszztMap.put("P", "延期体检");
    }

    private void initZjcx() {
        zjcxMap = new HashMap<String, String>();
        zjcxMap.put("A1", "大型客车");
        jszztMap.put("A2", "牵引车");
        jszztMap.put("A3", "城市公共汽车");
        jszztMap.put("B1", "中型客车");
        jszztMap.put("B2", "大型货车");
        jszztMap.put("C1", "小型汽车");
        jszztMap.put("C2", "小型自动档汽车");
        jszztMap.put("C3", "三轮汽车");
        jszztMap.put("C4", "低速载货汽车");
        jszztMap.put("D ", "三轮摩托车");
        jszztMap.put("E ", "二轮摩托车");
        jszztMap.put("F ", "轻便摩托车");
        jszztMap.put("M ", "轮式自行机械车");
        jszztMap.put("N ", "无轨电车");
        jszztMap.put("P ", "有轨电车");
    }
}
