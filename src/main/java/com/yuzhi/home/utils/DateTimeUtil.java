package com.yuzhi.home.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description：时间工具类
 * @Author：song
 * @Date：2022/06/15
 */
public class DateTimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 日期转换：String转Date
     *
     * @param strDate 如：2017-05-03
     * @return Date
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat shortSdf = new SimpleDateFormat(YYYY_MM_DD);
        Date date = new Date();
        try {
            date = shortSdf.parse(strDate);
        } catch (Exception e) {
            logger.error("strToDate error:", e);
            throw new RuntimeException("strToDate error:" + e.getMessage());
        }
        return date;
    }

    /**
     * Date转String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToStr(Date date, String pattern) {
        String strDate = null;
        try {
            if (pattern == null) {
                pattern = YYYY_MM_DD;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            strDate = format.format(date);
        } catch (Exception e) {
            logger.error("dateToStr error:", e);
            throw new RuntimeException("dateToStr error:" + e.getMessage());
        }
        return strDate;
    }

    /**
     * 获得当日0点
     */
    public static Date getZero() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00"));
        } catch (ParseException e) {

        }
        return null;
    }

    /**
     * 获得当日最后时间
     */
    public static Date getEnd() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59"));
        } catch (ParseException e) {

        }
        return null;
    }

    /**
     * 日期前几天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getTimeDaysBefore(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -days);

        return c.getTime();
    }

    /**
     * 日期后几天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getTimeDaysAfter(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);

        return c.getTime();
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getFirstOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getLastOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 是否周末
     *
     * @param bDate
     * @return
     */
    public static Boolean isWeekend(Date bDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(bDate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Date firstOfYear = getFirstOfYear(2022);
        Date lastOfYear = getLastOfYear(2022);
        System.out.printf("firstOfYear: " + DateUtil.getDateStr(firstOfYear, "yyyyMMddHHssmm"));
        System.out.printf("lastOfYear: " + DateUtil.getDateStr(lastOfYear, "yyyyMMddHHssmm"));

        String dateStr = DateUtil.getDateStr(firstOfYear, "yyyy-MM-dd");
        System.out.printf("date:" + dateStr);
    }

}
