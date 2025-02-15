package com.mysystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static SimpleDateFormat dateFormat_1 = new SimpleDateFormat("yyyy-MM-dd");

    public static Date strToDate(String dateStr) {

        Date parse = null;
        try {
            parse = dateFormat_1.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return parse;
    }
}
