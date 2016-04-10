package com.example.rkroll.auto_scheduler;

import android.util.Log;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Java SimpleDateFormat - convert a Java String to a Date
 *
 * Uses a String pattern to define the expected date format.
 *
 */
public class SimpleDateFormatStringToDate {

    public static Date parseDate(String dateString, String[] formats) {
        Date date = null;
        boolean success = false;

        for (int i = 0; i < formats.length; i++){
            String format = formats[i];
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);

            try {
               // parse() will throw an exception if the given dateString doesn't match
                // the current format
                date = dateFormat.parse(dateString);
                success = true;
                break;
            }
            catch(ParseException e)
            {
                Log.d("Failed", e.getMessage());
            }
        }
        return date;
    }
}
