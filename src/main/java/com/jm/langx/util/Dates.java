package com.jm.langx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description
 * @Create by yangjm
 * @CreateTime 21.3.6 10:53
 */
public class Dates {
    private static final String defFmt = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(defFmt);

    //yyyy-MM-dd HH:mm:ss
    public static final String format(Date date, String fmt) {
        if (Emptys.isEmpty(date)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(date);
    }

    //yyyy-MM-dd HH:mm:ss
    public static final String format(Long date, String fmt) {
        if (Emptys.isEmpty(date)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(new Date(date));
    }

    //yyyy-MM-dd HH:mm:ss
    public static final String format(Long date) {
        return sdf.format(new Date(date));
    }

    public static final String format(Date date) {
        return sdf.format(date);
    }

    public static final Long str2Long(String timeStr,String fmt) throws ParseException {
        if (Emptys.isEmpty(timeStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.parse(timeStr).getTime();

    }

    public static final Date str2Date(String timeStr, String fmt) throws ParseException {
        if (Emptys.isEmpty(timeStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.parse(timeStr);

    }

    public static final boolean isYesterday(Long time) {
        if (Emptys.isEmpty(time)) {
            return false;
        }
        Long currentTime = System.currentTimeMillis();
        return (currentTime - time)/86400000L == 1 ;
    }

    public static final int getCurrentWeekEnd(){
        Calendar c = Calendar.getInstance();
        // Long time = System.currentTimeMillis() + 86400000L * 2;
       // System.out.println("当前时间：" + format(time));
        c.setTime(new Date());
        int weekday = c.get(Calendar.DAY_OF_WEEK) -1;
        // System.out.println("星期几：" + weekday);
        int monthDay = c.get(Calendar.DAY_OF_MONTH);
        // System.out.println("几号：" + monthDay);
        return monthDay + (7 - weekday);
    }

    public static final int getWeekEnd(Long time){
        Calendar c = Calendar.getInstance();
        // Long time = System.currentTimeMillis() + 86400000L * 2;
        // System.out.println("当前时间：" + format(time));
        c.setTime(new Date(time));
        int weekday = c.get(Calendar.DAY_OF_WEEK) -1;
        // System.out.println("星期几：" + weekday);
        int monthDay = c.get(Calendar.DAY_OF_MONTH);
        // System.out.println("几号：" + monthDay);
        return monthDay + (7 - weekday);
    }

    public static void main(String[] args) {
        System.out.println("周末几号：" + getCurrentWeekEnd());
    }
}
