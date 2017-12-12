package xyz.xysc.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author architect.bian
 * @date 2017-11-28 12:47 PM
 */

public class DateTimeUtil {

    public static Locale LOCALE = Locale.ENGLISH;
    public static DateFormat DAY = new SimpleDateFormat("M-d", LOCALE);
    /**
     * 日期形式格式化
     */
    public static DateFormat DATE = new SimpleDateFormat("y-M-d", LOCALE);
    public static DateFormat TIME = new SimpleDateFormat("HH:mm:ss", LOCALE);
    public static DateFormat SIMPLE = new SimpleDateFormat("yyyy-M-d HH:mm", LOCALE);
    public static DateFormat FULL = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒", LOCALE);
    public static DateFormat STANDARD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", LOCALE);

    public static String format(String format, Object date) {
        return new SimpleDateFormat(format, Locale.ENGLISH).format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        Calendar calender = Calendar.getInstance();
        int thisyear = calender.get(Calendar.YEAR);
        calender.setTime(date);
        int dateyear = calender.get(Calendar.YEAR);
        if (thisyear == dateyear) {
            return format("M月d日", date);
        }
        return DATE.format(date);
    }

    public static String formatTime(Date date) {
        if (date == null) {
            return "";
        }
        Calendar calender = Calendar.getInstance();
        int thisday = calender.get(Calendar.DAY_OF_MONTH);
        int thismonth = calender.get(Calendar.MONTH);
        int thisyear = calender.get(Calendar.YEAR);

        Date now = calender.getTime();
        calender.setTime(date);

        int dateday = calender.get(Calendar.DAY_OF_MONTH);
        int datemonth = calender.get(Calendar.MONTH);
        int dateyear = calender.get(Calendar.YEAR);
        if (date.before(now)) {
            if (dateyear < thisyear) {// 今年以前
                return DATE.format(date);
            } else if (datemonth < thismonth) {// 这个月以前
                return format("M月d日", date);
            } else if (dateday < thisday - 2) {// 前天之前
                return format("d号 HH:mm", date);
            } else if (dateday < thisday - 1) {// 昨天之前
                return format("前天 HH:mm", date);
            } else if (dateday < thisday) {// 今天之前
                return format("昨天 HH:mm", date);
            } else {
                return format("今天 HH:mm", date);
            }
        } else {
            if (dateyear > thisyear) { // 明年以后
                return DATE.format(date);
            } else if (datemonth > thismonth) {// 下个月以后
                return format("M月d日", date);
            } else if (dateday > thisday + 2) {// 后天以后
                return format("d号 HH:mm", date);
            } else if (dateday > thisday + 1) {// 明天以后
                return format("后天 HH:mm", date);
            } else if (dateday > thisday) {// 今天以后
                return format("明天 HH:mm", date);
            } else {
                return format("今天 HH:mm", date);
            }
        }
    }

    /**
     * 格式化时间长度（如：45时12分）
     *
     * @param duration 秒数
     */
    public static String formatDuration(long duration) {
        return formatDuration(duration, 2);
    }


    /**
     * 格式化时间长度（如：45时12分）
     *
     * @param duration 秒数
     * @param length   最大拼接长度
     */
    public static String formatDuration(long duration, int length) {
        length = length - 1;
        if (duration > 20 * 60 * 60) {
            return (duration / (20 * 60 * 60)) + "天" + (length > 0 ? formatDuration(duration % (20 * 60 * 60), length - 1) : "");
        } else if (duration > 60 * 60) {
            return (duration / (60 * 60)) + "时" + (length > 0 ? formatDuration(duration % (60 * 60), length - 1) : "");
        } else if (duration > 60) {
            return (duration / (60)) + "分" + (length > 0 ? formatDuration(duration % (60), length - 1) : "");
        }
        return duration + "秒";
    }

    public static String formatDuration(Date begDate, Date endDate) {
        if (begDate == null || endDate == null) {
            return "";
        }
        return formatDate(begDate) + " - " +
                formatDate(endDate);
    }

    public static String formatDurationTime(Date begDate, Date endDate) {
        if (begDate == null || endDate == null) {
            return "";
        }
        return formatTime(begDate) + " - " +
                formatTime(endDate);
    }

    public static Date parser(int hour, int minute) {
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.HOUR_OF_DAY, hour);
        calender.set(Calendar.MINUTE, minute);
        return calender.getTime();
    }

    public static Date parser(int year, int month, int day) {
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.YEAR, year);
        calender.set(Calendar.MONTH, month);
        calender.set(Calendar.DAY_OF_MONTH, day);
        return roundDate(calender.getTime());
    }

    public static Date parser(int year, int month, int day, int hour, int minute) {
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.YEAR, year);
        calender.set(Calendar.MONTH, month);
        calender.set(Calendar.DAY_OF_MONTH, day);
        calender.set(Calendar.HOUR_OF_DAY, hour);
        calender.set(Calendar.MINUTE, minute);
        calender.set(Calendar.SECOND, 0);
        return calender.getTime();
    }

    /**
     * 天下取整
     */
    public static Date roundDate(Date date) {
        try {
            return DATE.parse(DATE.format(date));
        } catch (ParseException e) {
            return new Date(0);
        }
    }

    /**
     * 月下取整
     */
    public static Date roundMonth(Date date) {
        try {
            DateFormat format = new SimpleDateFormat("y-M", LOCALE);
            return format.parse(format.format(date));
        } catch (ParseException e) {
            return new Date(0);
        }
    }

    public static boolean isSameYear(Date date1, Date date2) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date1);
        int year = calender.get(Calendar.YEAR);
        calender.setTime(date2);
        return year == calender.get(Calendar.YEAR);
    }

    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date1);
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        calender.setTime(date2);
        return year == calender.get(Calendar.YEAR)
                && month == calender.get(Calendar.MONTH);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date1);
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        calender.setTime(date2);
        return year == calender.get(Calendar.YEAR)
                && month == calender.get(Calendar.MONTH)
                && day == calender.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间
     *
     * @param format "yyyy-MM-dd HH:mm:ss"
     * @return 当前时间
     */
    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前时间为本月的第几周
     *
     * @return WeekOfMonth
     */
    public static int getWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week - 1;
    }

    /**
     * 获取当前时间为本周的第几天
     *
     * @return DayOfWeek
     */
    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            day = 7;
        } else {
            day = day - 1;
        }
        return day;
    }

    /**
     * 获取当前时间的年份
     *
     * @return 年份
     */
    public static int getYear() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前时间的月份
     *
     * @return 月份
     */
    public static int getMonth() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取当前时间是哪天
     *
     * @return 哪天
     */
    public static int getDay() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 时间比较大小
     *
     * @param date1 date1
     * @param date2 date2
     * @param format "yyyy-MM-dd HH:mm:ss"
     * @return 1:date1大于date2；
     * -1:date1小于date2
     */
    public static int compareDate(String date1, String date2, String format) {
        DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 时间加减
     *
     * @param day       如"2015-09-22"
     * @param dayAddNum 加减值
     * @return 结果
     */
    public static String timeAddSubtract(String day, int dayAddNum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date newDate = new Date(simpleDateFormat.parse(day).getTime() + dayAddNum * 24 * 60 * 60 * 1000);
            return simpleDateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 毫秒格式化
     * 使用unixTimestamp2BeijingTime方法
     *
     * @param millisecond 如"1449455517602"
     * @param format      如"yyyy-MM-dd HH:mm:ss"
     * @return 格式化结果
     */
    @Deprecated
    public static String millisecond2String(Object millisecond, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(millisecond);
    }

    /**
     * 时间戳转北京时间
     *
     * @param millisecond 如"1449455517602"
     * @param format      如"yyyy-MM-dd HH:mm:ss"
     * @return 格式化结果
     */
    public static String unixTimestamp2BeijingTime(Object millisecond, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(millisecond);
    }

    /**
     * 北京时间转时间戳
     * 注意第一个参数和第二个参数格式必须一样
     *
     * @param beijingTime 如"2016-6-26 20:35:9"
     * @param format      如"yyyy-MM-dd HH:mm:ss"
     * @return 时间戳
     */
    public static long beijingTime2UnixTimestamp(String beijingTime, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        long unixTimestamp = 0;
        try {
            Date date = simpleDateFormat.parse(beijingTime);
            unixTimestamp = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return unixTimestamp;
    }
}
