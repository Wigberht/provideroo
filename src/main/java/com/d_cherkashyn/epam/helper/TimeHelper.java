package com.d_cherkashyn.epam.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeHelper {
    
    private static final String dateFormat = "yyy-MM-dd";
    
    public static int getHoursOverUTC(Locale clientLocale) {
        Calendar calendar = Calendar.getInstance(clientLocale);
        TimeZone clientTimeZone = calendar.getTimeZone();
        
        return clientTimeZone.getRawOffset() / 1000 / 60 / 60;
    }
    
    public static String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        
        return getFormattedDate(localDate);
    }
    
    public static String getFormattedDate(LocalDate localDate) {
        return DateTimeFormatter.ofPattern(dateFormat).format(localDate);
    }
    
    public static String addDaysToDate(LocalDate localDate, int days) {
        return getFormattedDate(localDate.plusDays(days));
    }
}
