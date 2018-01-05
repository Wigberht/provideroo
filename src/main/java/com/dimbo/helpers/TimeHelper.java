package com.dimbo.helpers;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeHelper {
    public static int getHoursOverUTC(Locale clientLocale) {
        Calendar calendar = Calendar.getInstance(clientLocale);
        TimeZone clientTimeZone = calendar.getTimeZone();

        return clientTimeZone.getRawOffset() / 1000 / 60 / 60;
    }
}
