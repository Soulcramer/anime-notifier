package moe.notify.animenotifier.utils;


import java.util.Calendar;
import java.util.Date;

class TestDateUtil {

    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        // set the calendar to start of today
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(year, month, day);

        return calendar.getTime();
    }

}
