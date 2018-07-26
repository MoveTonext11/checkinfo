package com.anrongtec.lasa.utils;


import android.text.TextUtils;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class IDCardUtil {
    static final Map<Integer, String> zoneNum = new HashMap();
    static final int[] PARITYBIT;
    static final int[] POWER_LIST;

    static {
        zoneNum.put(Integer.valueOf(11), "北京");
        zoneNum.put(Integer.valueOf(12), "天津");
        zoneNum.put(Integer.valueOf(13), "河北");
        zoneNum.put(Integer.valueOf(14), "山西");
        zoneNum.put(Integer.valueOf(15), "内蒙古");
        zoneNum.put(Integer.valueOf(21), "辽宁");
        zoneNum.put(Integer.valueOf(22), "吉林");
        zoneNum.put(Integer.valueOf(23), "黑龙江");
        zoneNum.put(Integer.valueOf(31), "上海");
        zoneNum.put(Integer.valueOf(32), "江苏");
        zoneNum.put(Integer.valueOf(33), "浙江");
        zoneNum.put(Integer.valueOf(34), "安徽");
        zoneNum.put(Integer.valueOf(35), "福建");
        zoneNum.put(Integer.valueOf(36), "江西");
        zoneNum.put(Integer.valueOf(37), "山东");
        zoneNum.put(Integer.valueOf(41), "河南");
        zoneNum.put(Integer.valueOf(42), "湖北");
        zoneNum.put(Integer.valueOf(43), "湖南");
        zoneNum.put(Integer.valueOf(44), "广东");
        zoneNum.put(Integer.valueOf(45), "广西");
        zoneNum.put(Integer.valueOf(46), "海南");
        zoneNum.put(Integer.valueOf(50), "重庆");
        zoneNum.put(Integer.valueOf(51), "四川");
        zoneNum.put(Integer.valueOf(52), "贵州");
        zoneNum.put(Integer.valueOf(53), "云南");
        zoneNum.put(Integer.valueOf(54), "西藏");
        zoneNum.put(Integer.valueOf(61), "陕西");
        zoneNum.put(Integer.valueOf(62), "甘肃");
        zoneNum.put(Integer.valueOf(63), "青海");
        zoneNum.put(Integer.valueOf(64), "宁夏");
        zoneNum.put(Integer.valueOf(65), "新疆");
        zoneNum.put(Integer.valueOf(71), "台湾");
        zoneNum.put(Integer.valueOf(81), "香港");
        zoneNum.put(Integer.valueOf(82), "澳门");
        zoneNum.put(Integer.valueOf(91), "国外");
        PARITYBIT = new int[]{49, 48, 88, 57, 56, 55, 54, 53, 52, 51, 50};
        POWER_LIST = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    }

    private static boolean VerifyIDCard(String s) {
        if (s == null || s.length() != 15 && s.length() != 18) {
            return false;
        } else {
            char[] cs = s.toUpperCase().toCharArray();
            int power = 0;

            for (int i = 0; i < cs.length && (i != cs.length - 1 || cs[i] != 88); ++i) {
                if (cs[i] < 48 || cs[i] > 57) {
                    return false;
                }

                if (i < cs.length - 1) {
                    power += (cs[i] - 48) * POWER_LIST[i];
                }
            }

            if (!zoneNum.containsKey(Integer.valueOf(s.substring(0, 2)))) {
                return false;
            } else {
                String year = s.length() == 15 ? "19" + s.substring(6, 8) : s.substring(6, 10);
                int iyear = Integer.parseInt(year);
                if (iyear < 1800) {
                    return false;
                } else {
                    String month = s.length() == 15 ? s.substring(8, 10) : s.substring(10, 12);
                    int imonth = Integer.parseInt(month);
                    if (imonth >= 1 && imonth <= 12) {
                        String day = s.length() == 15 ? s.substring(10, 12) : s.substring(12, 14);
                        int iday = Integer.parseInt(day);
                        return iday >= 1 && iday <= 31 ? (!validate(iyear, imonth, iday) ? false
                                : (s
                                .length() == 15 ? true : cs[cs.length - 1] == PARITYBIT[power %
                                11]))
                                : false;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public static boolean managerIDCard(Object param) {
        String strId;
        if (param instanceof EditText) {
            EditText editText = (EditText) param;
            if (!TextUtils.isEmpty(editText.getText())) {
                strId = editText.getText().toString().trim();
                return VerifyIDCard(strId);

            } else {
                return false;
            }
        } else {
            return VerifyIDCard(String.valueOf(param).trim());
        }
    }

    static boolean validate(int year, int month, int day) {
        return true;
    }
}

