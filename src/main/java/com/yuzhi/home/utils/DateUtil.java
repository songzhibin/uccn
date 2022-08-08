package com.yuzhi.home.utils;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
    public static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public DateUtil() {
    }

    @Value("${spring.mvc.date-format:}")
    public static void setDateFormat(String dateFormat) {
        if (dateFormat.length() > 0) {
            DateUtil.dateFormat = dateFormat;
        }

    }

    @Value("${spring.jackson.date-format:}")
    public static void setDateTimeFormat(String dateFormat) {
        if (dateFormat.length() > 0) {
            DateUtil.dateFormat = dateFormat;
        }

    }

    public static Date getNowWithSqlDate() {
        return new Date(Calendar.getInstance().getTimeInMillis());
    }

    public static java.util.Date getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String source = sdf.format(Calendar.getInstance().getTime());

        try {
            return sdf.parse(source);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static String getDateStr(java.util.Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    public static String getDateStr(java.util.Date date, String formatStr) {
        return (new SimpleDateFormat(formatStr)).format(date);
    }

    public static String getDateTimeStr(java.util.Date date) {
        return (new SimpleDateFormat(dateFormat)).format(date);
    }

    public static String getTimeStr(java.util.Date date) {
        return (new SimpleDateFormat("HH:mm:ss")).format(date);
    }

    public static String getDateTimeStr() {
        return (new SimpleDateFormat(dateFormat)).format(Calendar.getInstance().getTime());
    }

    public static String getDateStr() {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(Calendar.getInstance().getTime());
    }

    public static String getTimeStr() {
        return (new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime());
    }

    public static java.util.Date parseDateTime(String source) throws ParseException {
        return (new SimpleDateFormat(dateFormat)).parse(source);
    }

    public static java.util.Date parseDateTime(String source, String format) throws ParseException {
        return (new SimpleDateFormat(format)).parse(source);
    }
}
