package com.atpos.api.commons.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.vavr.API.Option;

public final class Utils {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
    public static final DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
    public static final String DEFAULT_STRING_VALUE = "";

    public static String getStackTrace(Throwable ex) {
        StringBuffer sb = new StringBuffer(5000);
        StackTraceElement[] st = ex.getStackTrace();
        sb.append(ex.getClass().getName() + ": " + ex.getMessage() + "\n");
        for (int i = 0; i < st.length; i++) {
            sb.append("\t at " + st[i].toString() + "\n");
        }
        return sb.toString();
    }

    public static String convertToString(Object date) {
        return Option(date)
                .map(d -> new DateTime(d))
                .map(dateTime -> dateTime.toString(formatter))
                .getOrElse(StringUtils.EMPTY);
    }

    public static Date stringToDate( String date ) throws ParseException {
        return ( null == date ? null : dateFormatter.parse( date ) );
    }

    public static String defaultValueString(String value) {
        return Option(value).getOrElse(DEFAULT_STRING_VALUE);
    }

    public static Date stringToDateOrNull(String date ) {
        return Option(date).map(d -> {
            try {
                return formatter.parseDateTime(date).toDate();
            } catch (Exception e) {
                return null;
            }
        }).getOrNull();
    }
}
