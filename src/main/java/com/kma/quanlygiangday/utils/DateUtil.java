package com.kma.quanlygiangday.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String FORMAT_DATE_TIME = "HH:mm dd/MM/yyyy";
    public static final String FORMAT_DATE_TIME_SECOND = "yyyy-MM-dd hh:mm:ss";

    public static Date now() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date addDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, days);
        }
        return calendar.getTime();
    }

    public static Date addOneDay(Date date) {
        return addDay(date, 1);
    }

    public static Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
        return date;
    }

    public static Date lastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        return endTime(date);
    }

    public static Date firtDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        date = calendar.getTime();
        return truncateTime(date);
    }

    public static Date endTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 99);
        date = calendar.getTime();
        return date;
    }

    public static int compare(Date source, Date destination) {
        return truncateTime(source).compareTo(truncateTime(destination));
    }

    public static Date parseDate(String date, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

    public static Date parseDate(String date) throws ParseException {
        if (date == null || "".equals(date.trim())) {
            return null;
        }
        return DateUtil.parseDate(date, FORMAT_DATE);
    }

    public static String toString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
        if (date == null) {
            throw new IllegalArgumentException("Method argument must be not null");
        }
        return dateFormat.format(date);
    }

    public static String toStringWithTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
        if (date == null) {
            throw new IllegalArgumentException("Method argument must be not null");
        }
        return dateFormat.format(date);
    }

    public static String toStringWithTimeSecond(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME_SECOND);
        if (date == null) {
            throw new IllegalArgumentException("Method argument must be not null");
        }
        return dateFormat.format(date);
    }
/**
 * Ham nay dung de lay ra nam hoc mong muon
 * Co the lay nhung nam truoc day
 * 
 * @param date - Ngay muon lay nam hoc (Thuong la today)
 * @param namBiTru - Nam hoc muon lay
 * @return namHoc
 */
    public static String getNamHoc(Date date, int namBiTru) {
        String namHoc = "";
        int year = getYear(date) - namBiTru;
        int month = getMonth(date);
        switch (month) {
            case 9:
            case 10:
            case 11:
            case 12:
                namHoc = year + " - " + (year + 1);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                namHoc = (year - 1) + " - " + year;
                break;
        }

        return namHoc;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1; // thang trong calendar tu 0 -> 11
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
