package com.example.demo.module.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期相关的操作
 *
 * @author Dawei
 */

@SuppressWarnings({"AlibabaCollectionInitShouldAssignCapacity", "AlibabaUndefineMagicConstant"})
public class DateUtil {


    /**
     * 当天的开始时间
     *
     * @return
     */
    public static long startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date.getTime() / 1000;
    }

    /**
     * 当天的结束时间
     *
     * @return
     */
    public static long endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date.getTime() / 1000;
    }

    /**
     * 某天的年月日
     *
     * @param dayUntilNow 距今多少天以前
     * @return 年月日map key为 year month day
     */
    public static Map<String, Object> getYearMonthAndDay(int dayUntilNow) {

        Map<String, Object> map = new HashMap<String, Object>(3);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -dayUntilNow);
        map.put("year", calendar.get(Calendar.YEAR));
        map.put("month", calendar.get(Calendar.MONTH) + 1);
        map.put("day", calendar.get(Calendar.DAY_OF_MONTH));
        return map;
    }

    /**
     * 将一个字符串转换成日期格式
     *
     * @param date    字符串日期
     * @param pattern 日期格式
     * @return
     */
    public static Date toDate(String date, String pattern) {
        if ("".equals("" + date)) {
            return null;
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date newDate = new Date();
        try {
            newDate = sdf.parse(date);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newDate;
    }

    /**
     * 获取上个月的开始结束时间
     *
     * @return
     */
    public static Long[] getLastMonth() {
        // 取得系统当前时间
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        // 取得系统当前时间所在月第一天时间对象
        cal.set(Calendar.DAY_OF_MONTH, 1);

        // 日期减一,取得上月最后一天时间对象
        cal.add(Calendar.DAY_OF_MONTH, -1);

        // 输出上月最后一天日期
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String months = "";
        String days = "";

        if (month > 1) {
            month--;
        } else {
            year--;
            month = 12;
        }
        if (!(String.valueOf(month).length() > 1)) {
            months = "0" + month;
        } else {
            months = String.valueOf(month);
        }
        if (!(String.valueOf(day).length() > 1)) {
            days = "0" + day;
        } else {
            days = String.valueOf(day);
        }
        String firstDay = "" + year + "-" + months + "-01";
        String lastDay = "" + year + "-" + months + "-" + days + " 23:59:59";

        Long[] lastMonth = new Long[2];
        lastMonth[0] = DateUtil.getDateline(firstDay);
        lastMonth[1] = DateUtil.getDateline(lastDay, "yyyy-MM-dd HH:mm:ss");

        return lastMonth;
    }

    /**
     * 把日期转换成字符串型
     *
     * @param date    日期
     * @param pattern 类型
     * @return
     */
    public static String toString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        String dateString = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            dateString = sdf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateString;
    }

    /**
     * 时间戳转换成时间类型
     *
     * @param time    时间戳
     * @param pattern 格式
     * @return
     */
    public static String toString(Long time, String pattern) {
        if (time > 0) {
            if (time.toString().length() == 10) {
                time = time * 1000;
            }
            Date date = new Date(time);
            String str = DateUtil.toString(date, pattern);
            return str;
        }
        return "";
    }


    /**
     * 判断当前时间是否在某个时间范围
     *
     * @param start 开始时间，以秒为单位的时间戳
     * @param end   结束时间，以秒为单位的时间戳
     * @return 是否在范围内
     */
    public static boolean inRangeOf(long start, long end) {
        long now = getDateline();
        return start <= now && end >= now;
    }

    /**
     * 获取指定日期的时间戳
     *
     * @param date 指定日期
     * @return 时间戳
     */
    public static long getDateline(String date) {
        return toDate(date, "yyyy-MM-dd").getTime() / 1000;
    }

    /**
     * 为了方便mock 设置此属性
     * 如果设置了此属性，则回直接返回设置的值
     */
    public static Long mockDate;

    /**
     * 获取当前时间的时间戳
     *
     * @return 时间戳
     */
    public static long getDateline() {
        if (mockDate != null) {
            return mockDate;
        }
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 根据日期格式及日期获取时间戳
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 时间戳
     */
    public static long getDateline(String date, String pattern) {
        return toDate(date, pattern).getTime() / 1000;
    }

    /**
     * 获取几个月之前的日期时间戳
     *
     * @param beforeMonth 几个月之前
     * @return
     */
    public static long getBeforeMonthDateline(int beforeMonth) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, (0 - beforeMonth));
        Date m = c.getTime();
        String mon = format.format(m);
        return getDateline(mon, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前天的结束时间
     *
     * @return
     */
    public static Date getCurrentDayEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
        cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);
        return cal.getTime();
    }

}
