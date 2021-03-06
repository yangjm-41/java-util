package com.jm.langx.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Create by yangjm
 * @CreateTime 21.3.6 10:53
 */
public class Dates {
    private static final String defFmt = "yyyy-MM-dd HH:mm:ss";


    //yyyy-MM-dd HH:mm:ss
    public static final String dateFormat(Date date, String fmt) {
        if (Emptys.isNotEmpty(date)) {
            return "";
        }
        final SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(date);
    }

    //yyyy-MM-dd HH:mm:ss
    public static final String dateFormat(Long date, String fmt) {
        if (Emptys.isNotEmpty(date)) {
            return "";
        }

        final SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(new Date(date));
    }

    //yyyy-MM-dd HH:mm:ss
    public static final String dateFormat(Long date) {
        return dateFormat(date, defFmt);
    }

    public static final String dateFormat(Date date) {
        return dateFormat(date, defFmt);
    }
}
