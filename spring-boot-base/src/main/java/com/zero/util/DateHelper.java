package com.zero.util;

/**
 * @Description:
 * @author: yezhaoxing
 * @date: 2017/5/11
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class DateHelper {
    public DateHelper() {
    }

    public static Timestamp strToTimestamp(String time) {
        Timestamp ts = null;

        try {
            SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss");
            Date d = (Date) e.parseObject(time);
            ts = new Timestamp(d.getTime());
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return ts;
    }

    public static Date getCurrentDateTime() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    public static Date getCurrentDateTime(String pattern) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String dateStr = dateFormat.format(new Date());
        return dateFormat.parse(dateStr);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date format(String source, String pattern) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(source);
    }

    public static int getYear(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(1);
    }

    public static int getMonth(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(2) + 1;
    }

    public static int getDay(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(5);
    }

    public static Date addYear(Date date, int years) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(1, years);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int months) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(2, months);
        return cal.getTime();
    }

    public static Date addDay(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(6, days);
        return cal.getTime();
    }

    public static Date addHour(Date date, int hours) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(10, hours);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int minutes) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(12, minutes);
        return cal.getTime();
    }

    public static Date addSecond(Date date, int seconds) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(13, seconds);
        return cal.getTime();
    }

    public static String dateDiff(String startTime, String endTime, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 86400000L;
        long nh = 3600000L;
        long nm = 60000L;
        long day = 0L;
        long hour = 0L;
        long min = 0L;

        try {
            long diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;
            hour = diff % nd / nh + day * 24L;
            min = diff % nd % nh / nm + day * 24L * 60L;
            return day < 1L ? (hour < 1L ? min + "分钟" : hour + "小时") : day + "天";
        } catch (ParseException var19) {
            var19.printStackTrace();
            return null;
        }
    }

    public static long getTime(String dateTime, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date = sdf.parse(dateTime);
            long e = date.getTime();
            return e;
        } catch (ParseException var6) {
            var6.printStackTrace();
            return 0L;
        }
    }

    public static long getDiffDays(String time1, String time2) {
        long diffDays = 0L;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date e = ft.parse(time1);
            Date date2 = ft.parse(time2);
            diffDays = e.getTime() - date2.getTime();
            diffDays = diffDays / 1000L / 60L / 60L / 24L;
        } catch (ParseException var7) {
            var7.printStackTrace();
        }

        return diffDays;
    }

    public static long getDiffMinute(String startTime, String endTime) {
        long diffMinute = 0L;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date e = ft.parse(endTime);
            Date dateStartTime = ft.parse(startTime);
            diffMinute = e.getTime() - dateStartTime.getTime();
            diffMinute = diffMinute / 1000L / 60L;
        } catch (ParseException var7) {
            var7.printStackTrace();
        }

        return diffMinute;
    }

    public static boolean beforeDate(String lastDate, String nowDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date d = sdf.parse(lastDate);
        Date d2 = sdf.parse(nowDate);
        boolean flag = d.before(d2);
        return flag;
    }

    public static int getMonthDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        return cal.getActualMaximum(5);
    }

    public static List<Date> getDateList(Date dateStart, Date dateEnd) {
        ArrayList dateList = new ArrayList();
        dateList.add(dateStart);
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(dateStart);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dateEnd);

        while (dateEnd.after(calStart.getTime())) {
            calStart.add(5, 1);
            dateList.add(calStart.getTime());
        }

        return dateList;
    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        return isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static void main(String[] args) throws ParseException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate1 = dateFormat2.parse("2009-06-01");
        Date myDate2 = dateFormat1.parse("2009-06-02 06:50:50");
        System.out.println(daysBetween(myDate1, myDate2));
    }
}