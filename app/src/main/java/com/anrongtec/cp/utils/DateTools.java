package com.anrongtec.cp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author libo
 * @Description: 时间转换工具
 * @date 2018/5/15
 */

public class DateTools {

    /**
     * 获取当前日期时间
     *
     * @param type 格式，null为默认的 yyyy-MM-dd HH:mm:ss
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String getStrCurryTime(String type) {
        SimpleDateFormat format = new SimpleDateFormat(type == null ? "yyyy-MM-dd HH:mm:ss" : type,
                Locale.getDefault());
        return format.format(new Date());
    }

    public static Long getLongCurryDate() {
        Date date = new Date();
        return date.getTime();
    }

    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     *
     * @param time "2014-06-14-16-09-00"
     * @return 时间戳
     */
    public static String getStrTimeLine(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
                Locale.getDefault());
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time 时间戳
     * @return 2014年06月14日16时09分00秒
     */
    public static String getTimeName(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒", Locale.getDefault());
        long lcc = Long.valueOf(time);
        return sdr.format(new Date(lcc));

    }

    /**
     * 输入Long类型参数返回String 格式：yyyy-MM-dd HH:mm:ss
     *
     * @param longTime
     * @return
     */
    public static String getStrTime(Long longTime) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(new Date(longTime));
    }


    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time 时间戳
     * @return 2014-06-14  16:09:00
     */
    public static String getStrLine(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        long lcc = Long.valueOf(time);
        return sdr.format(new Date(lcc));
    }

    /**
     * 根据传递的类型格式化时间
     *
     * @param str  时间戳
     * @param type 例如：yy-MM-dd
     * @return
     */
    public static String getDateTimeByType(String str, String type) {
        Date date = new Date(Long.valueOf(str));
        SimpleDateFormat format = new SimpleDateFormat(type, Locale.getDefault());
        return format.format(date);
    }

    /**
     * 将时间戳转换日
     *
     * @param s
     * @return
     */
    public static String stampToDay(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将时间戳转换为年月日
     * 起始时间戳
     *
     * @param s
     * @return
     */
    public static String startToDate(String s) {
        String res = "";
        String timemonth = stampToMonth(s);//年月
        String timeday = stampToDay(s);//日
        String timemm = getStrTime(new Long(s));//时分秒
        int day = Integer.parseInt(timeday);
        if (day <= 3) {//如果当天时间日期小于3  则起始时间定位01号
            timeday = "01";
        } else {
            timeday = String.valueOf(day - 3);
        }
        res = timemonth + "-" + timeday + " " + timemm;
        return res;
    }

    /**
     * 结束时间戳  查询到当天日期
     *
     * @param s
     * @return
     */
    public static String endToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = Long.valueOf(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将时间戳转换为年月日
     *
     * @param s
     * @return
     */
    public static String stampToMonth(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        long lt = Long.valueOf(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    //获得当天0点时间
    public static String getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return String.valueOf(cal.getTimeInMillis());
    }

    //获得当天24点时间
    public static String getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return String.valueOf(cal.getTimeInMillis());
    }


}
