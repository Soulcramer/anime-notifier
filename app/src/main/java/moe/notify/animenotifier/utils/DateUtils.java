package moe.notify.animenotifier.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import com.freezingwind.animereleasenotifier.R;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import timber.log.Timber;

final class DateUtils {

  private static final SimpleDateFormat ISO8601DATETIMEFORMAT =
      new SimpleDateFormat("yyyy/MM/dd 'T'HH:mmZ", Locale.ENGLISH);

    private DateUtils() {
    }

    /**
     * Converts a date to the textual representation of dates used by people.
     *
     * @param date the date to be formated
     * @return If the date is of today, then this method will return 'Today's'. If its yesterday then 'Yesterday' is returned.
     * Otherwise it returns the date in the form of dd.mm
     */
    public static String dateToText(Context context, Date date) {
        String textDate;

        // clear hours, minutes and smaller time units from the date
        date = truncateHours(date);

        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        Date today = c.getTime();

        // get yesterday
        c.add(Calendar.DATE, -1);
        Date yesterday = c.getTime();

        c.add(Calendar.DATE, +1);
        Date tomorrow = c.getTime();


        if (date.equals(today)) { // test if today
            textDate = context.getString(R.string.date_anime_today);
        } else if (date.equals(yesterday)) {  // test if yesterday
            textDate = context.getString(R.string.date_anime_yesterday);
        } else if (date.equals(tomorrow)) {  // test if yesterday
            textDate = context.getString(R.string.date_anime_tomorrow);
        } else {
          textDate = formatDate(date, new SimpleDateFormat("dd.MM", Locale.ENGLISH));
        }
        // FIXME: 13/08/2016 add string ressources
        textDate = "";

        return textDate;
    }

    public static Date createDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // setup the date
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        // and get that as a Date
        return c.getTime();
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.US);
        return sdf.format(date);
    }

  private static String formatDate(Date date, SimpleDateFormat sdf) {
        return sdf.format(date);
    }

    public static Date getToday() {

        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        Date today = c.getTime();

        return today;
    }

  private static Date truncateHours(Date date) {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        return c.getTime();
    }

    public static synchronized Date iso8601ToDate(@Nullable String iso8601) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
        Date myDate = null;
        try {
            myDate = df.parse(iso8601);
        } catch (ParseException e) {
            Timber.e(e, "Error while parsing the iso8601 date to a Date object.");
        } catch (NullPointerException e) {
            Timber.w("The iso8601 date was null, so we return today's date");
            myDate = new Date();
        }
        return myDate;
    }

    public static synchronized String dateToIso8601(@Nullable Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        ISO8601DATETIMEFORMAT.setTimeZone(tz);
        return ISO8601DATETIMEFORMAT.format(date);
    }
}
